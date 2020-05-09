package com.nagarro.NagarroTravelPortal.controller;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.NagarroTravelPortal.entity.Ticket;
import com.nagarro.NagarroTravelPortal.entity.User;
import com.nagarro.NagarroTravelPortal.entity.constraint.GenerateSecurePassword;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 1800)
@RequestMapping("/")
public class WebController
{
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private GenerateSecurePassword generator;
	
	private SessionFactory sessionFactory;
	
	@Value("${admin.username}")
	private String adminname;
	
	@Value("${admin.password}")
	private String adminpass;

	 
	@Autowired
	public void SomeService(EntityManagerFactory factory) 
	{
	     if(factory.unwrap(SessionFactory.class) == null)
	     {
	        throw new NullPointerException("factory is not a hibernate factory");
	     }
	     this.sessionFactory = factory.unwrap(SessionFactory.class);
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
	
	private Session getSession() {
		// TODO Auto-generated method stub
		return sessionFactory.openSession();
	}

	@PostMapping("/ticketgeneration")
	public Ticket ticketGeneration(@RequestBody Ticket ticket) 
    {
		Session session = getSession();
		Transaction t=session.beginTransaction();
		session.saveOrUpdate(ticket);
		t.commit();
		System.out.println(ticket);
		return ticket;
	}
	
	@GetMapping("/signup")
	public String signup() 
	{
		return "";
	}
	
	@GetMapping("/gettickets")
	public List<Ticket> signin(@RequestBody User user)
	{
		Session session = getSession();
		List<Ticket> list=session.createQuery("from Ticket t where t.email='"+user.getEmail()+"'").list();
		return list;
	}
	
	@GetMapping("/signin")
	public User signin(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password)
	{
		Session session = getSession();
		User user=session.byNaturalId(User.class).using("email", email).load();
		return user;
	}
	
	@PostMapping("/adminlogin")
	public boolean AdminLogin(@RequestParam(name = "email", required = true) String username,
			@RequestParam(name = "password", required = true) String password)
	{
		if(username.equals(this.adminname)&&password.equals(this.adminpass))
		{
			return true;
		}
			return false;
	}
	
}
