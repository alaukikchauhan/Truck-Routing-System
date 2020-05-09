package com.example.ProfitabilityEnahancement.controller;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProfitabilityEnahancement.entity.User;
import com.example.ProfitabilityEnahancement.services.EmailService;
import com.example.ProfitabilityEnahancement.services.GenerateSecurePassword;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 1800)
@RequestMapping("/")
public class WebController 
{
	@Autowired
	private EmailService emailService;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private GenerateSecurePassword generator;
	
	@Autowired
	public void SomeService(EntityManagerFactory factory) 
	{
	     if(factory.unwrap(SessionFactory.class) == null)
	     {
	        throw new NullPointerException("factory is not a hibernate factory");
	     }
	     this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
	
	private Session getSession() {
		// TODO Auto-generated method stub
		return sessionFactory.openSession();
	}
	
	@PostMapping("/forgotpassword")
	public String ForgotPassword(@RequestBody User user)
	{
		 emailService.sendMail(user.getEmail(), "User Name : "+user.getEmail(),"Password : "+ user.getPassword());
         
//	     emailService.sendPreConfiguredMail("Ho ho ho");
	     
		 return "";
	}
	
	@PostMapping("/signup")
	public User signup(@RequestBody User user)
    {
		Session session = getSession();
		Transaction t=session.beginTransaction();
		user.setPassword(generator.generatePassword());
		session.saveOrUpdate(user);
		emailService.sendMail(user.getEmail(), "User Name : "+user.getEmail(),"Password : "+ user.getPassword());
		t.commit();
		System.out.println(user);
		return user;
	}
}
