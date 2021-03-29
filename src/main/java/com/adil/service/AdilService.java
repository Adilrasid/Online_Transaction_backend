package com.adil.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.adil.Exception.UserDoesNotExitException;
import com.adil.dto.LoginForm;
import com.adil.dto.RegistrationForm;
import com.adil.model.User;
import com.adil.repository.Adilrepository;
import com.adil.response.Response;
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
				adil.save(u);

			}
		}
		return new Response(200, "Hi Adil Data Inserted");
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
			String token = tokenutil.createToken(user.get().getId());
			System.out.println(token);
			return new Response(200, "Login Successfull " + token);
		}
		throw new UserDoesNotExitException(environment.getProperty("status.login.usernotexit"));

	}

	/*public Response update(RegistrationForm regii)// Service-Method for Update-API
	{
		Optional<User> user = adil.findByFirstName(regii.getFirstName());
		if (user.isPresent()) {
			
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			User u = modelMapper.map(regii, User.class);
			u.modifiedDate(LocalDateTime.now());
			adil.update(u);

		}
	}*/
	
	public List<User> getALLUser(String token)
	{
		Long all = tokenutil.decodeToken(token);
		List<User> l = adil.findById(all);
		return l;
		
	}
}
