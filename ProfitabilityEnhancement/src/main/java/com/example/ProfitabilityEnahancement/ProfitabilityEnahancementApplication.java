package com.example.ProfitabilityEnahancement;

import java.util.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ProfitabilityEnahancement.services.Store;

@SpringBootApplication
public class ProfitabilityEnahancementApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(ProfitabilityEnahancementApplication.class, args);
		
//		Timer timer=new Timer();
//		
//		Store st=new Store();
//		
//		timer.schedule(st, 10000);
//		
//		st.run();
	}

}
