package com.adil.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class RabbitMQSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	
	@Value("rmq.rube.exchange")
	private String exchange;
	
	@Value("rube.key")
	private String routingkey;
	
	@Autowired
	private JavaMailSender javaMail;
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	
	
	public void send(SimpleMailMessage simpleMailMessage) {
		
		rabbitTemplate.convertAndSend(exchange, routingkey, simpleMailMessage);
	    javaMail.send(simpleMailMessage);
	}
	
	

}