package com.example.ProfitabilityEnahancement.controller;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProfitabilityEnahancement.entity.User;
import com.example.ProfitabilityEnahancement.services.EmailService;
import com.example.ProfitabilityEnahancement.services.GenerateSecurePassword;
import com.example.ProfitabilityEnahancement.services.Store;

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
	
	@GetMapping("/signin")
	public User signin(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password)
	{
		Session session = getSession();
		User user=(User)session.createQuery("from User u where u.username='"+username+"'").list().get(0);
		if(user.getPassword()!=password)
			return null;
		return user;
	}
	
	@PostMapping("/forgotpassword")
	public String ForgotPassword(@RequestParam(name = "email", required = true) String email)
	{
		Session session = getSession();
		
		User user=(User)session.createQuery("from User u where u.email='"+email+"'").list().get(0);
		 
		emailService.sendMail(user.getEmail(), "Welcome"+" "+user.getFirstname(),"User Name : "+user.getUsername()+"  "+"Password : "+ user.getPassword());
         
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
		emailService.sendMail(user.getEmail(), "Welcome"+" "+user.getFirstname(),"User Name : "+user.getUsername()+"  "+"Password : "+ user.getPassword());
		t.commit();
		System.out.println(user);
		return user;
	}
	
	@GetMapping("/abc")
	public void Store()
	{
		Store st=new Store();
		st.run();
	}
}