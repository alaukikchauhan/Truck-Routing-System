package com.example.ProfitabilityEnahancement;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.ProfitabilityEnahancement.services.Store;

@SpringBootApplication
public class ProfitabilityEnahancementApplication 
{

	//@Autowired Store st;
	
	public static void main(String[] args) 
	{
		ApplicationContext ap=SpringApplication.run(ProfitabilityEnahancementApplication.class, args);
		
		Timer timer=new Timer();
		
		
		Store st=ap.getBean(Store.class);//new Store();
		
		timer.schedule(st, 900000);
		
		st.run();
	}

}
