package com.adil.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import javax.security.auth.message.MessageInfo;

import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.adil.Exception.UserDoesNotExitException;
import com.adil.config.RabbitMQSender;
import com.adil.dto.LoginForm;
import com.adil.dto.RegistrationForm;
import com.adil.model.User;
import com.adil.repository.Adilrepository;
import com.adil.response.Response;
import com.adil.utils.MailObject;
import com.adil.utils.TokenUtil;

@PropertySource(name = "User", value = { "classpath:response.properties" })
@Service
public class AdilService {
	@Autowired
	private Adilrepository adil;

	@Autowired
	private Environment environment;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TokenUtil tokenutil;

	@Autowired
	private MailObject mailObject;

	@Autowired
	RabbitMQSender rabbitMq;

	@Autowired
	private JmsTemplate jmsTemplate;

	public String adil(String userName, String Password)// sample-login-check
	{
		if (userName.equalsIgnoreCase("Adil") && Password.equalsIgnoreCase("1234")) {
			return "login success";
		}

		else {
			return "invalid credential";
		}

	}

	public Response register(RegistrationForm regii) // Service-Method for Registration-API
	{
		Optional<User> user = adil.findByEmailId(regii.getFirstName());

		if (!user.isPresent()) {
			if (regii.getPassword().equals(regii.getConfirmPassword())) {

				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				User u = modelMapper.map(regii, User.class);
				u.setRegisteredDate(LocalDateTime.now());
				u.setVerify(0);
				adil.save(u);

				 String token = tokenutil.createToken(u.getUserId());
				rabbitMq.send(simpleMessage(u.getEmailId(), "Valid User Check", "verifyUser/"
				+ token));


				return new Response(200, "Hi Adil Data Inserted");

			}
		}
		return new Response(300, "error");

	}

	public Response login(LoginForm logi)// Service-Method for Login-API
	{
		Optional<User> user = adil.findByEmailId(logi.getEmailId());
		if (!user.isPresent()) {
			throw new UserDoesNotExitException(environment.getProperty("status.login.userexist"));
		} else if (!user.get().getPassword().equals(logi.getPassword())) {
			throw new UserDoesNotExitException(environment.getProperty("status.login.incorrectpassword"));
		}

		else if (logi.getEmailId().equals(user.get().getEmailId())
				&& logi.getPassword().equals(user.get().getPassword())) {

			String token = tokenutil.createToken(user.get().getUserId());
			System.out.println(token);
			return new Response(200, "Login Successfull " + token);
		}
		throw new UserDoesNotExitException(environment.getProperty("status.login.usernotexit"));

	}

	/*
	 * public Response update(RegistrationForm regii)// Service-Method for
	 * Update-API { Optional<User> user =
	 * adil.findByFirstName(regii.getFirstName()); if (user.isPresent()) {
	 * 
	 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
	 * ; User u = modelMapper.map(regii, User.class);
	 * u.modifiedDate(LocalDateTime.now()); adil.update(u);
	 * 
	 * } }
	 */

	public List<User> getALLUser(String token)// service method to get All-User
	{
		Long all = tokenutil.decodeToken(token);
		List<User> l = adil.findByUserId(all);
		return l;

	}

	public Response verifyUser(String token) throws UserDoesNotExitException// service-method for verify-user by Email
	{
		Long all = tokenutil.decodeToken(token);
		Optional<User> l = adil.findById(all);
		if (l.isPresent()) {
			l.get().setVerify(1);
			adil.save(l.get());
			// rabbitMq.send(mailObject);

			throw new UserDoesNotExitException(environment.getProperty("status.login.verifiedSucceed"));

		}

		return new Response(300, "Verification Failed");

	}

	public static SimpleMailMessage simpleMessage(String email, String subject, String token) //Method for sending Email.
	{
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(email);
		smm.setFrom("adilrasi9@gmail.com");
		smm.setSubject("Valid User for registration");
		smm.setText("Verification Link" + " http://localhost:8096/verifyUser/" + token);

		return smm;

	}

}
