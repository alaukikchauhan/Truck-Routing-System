package com.example.ProfitabilityEnahancement.services;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.example.ProfitabilityEnahancement.entity.User;
import com.example.ProfitabilityEnahancement.jpaiml.UserRepository;

@Service
public class UserStorageService
{
	@Autowired
	private UserRepository userRepository;
	
	 @Bean
	    ItemReader<User> xmlFileItemReader() {
	        StaxEventItemReader<User> xmlFileReader = new StaxEventItemReader<>();
	        xmlFileReader.setResource(new ClassPathResource("data/students.xml"));
	        xmlFileReader.setFragmentRootElementName("student");
	        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
	        studentMarshaller.setClassesToBeBound(User.class);
	        xmlFileReader.setUnmarshaller(studentMarshaller);
	 
	        return xmlFileReader;
	    }
}
