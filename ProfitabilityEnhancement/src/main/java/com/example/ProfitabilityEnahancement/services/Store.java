package com.example.ProfitabilityEnahancement.services;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProfitabilityEnahancement.entity.Truck;
import com.example.ProfitabilityEnahancement.jpaiml.TruckRepository;
import com.opencsv.CSVReader;
@Service
public class Store extends TimerTask
{

	
	@Autowired 
	TruckRepository truckRepository;
	
	File f = null;
    File[] paths = null;
    Truck truck;
    CSVReader reader;
    
    Transaction t;
    
    List<Truck> trucks;
	
	public void run()
	{
		
//		  System.out.println("3");
//		  SessionFactory sessionFactory;
	      
	      try
	      {
	      f = new File("E:\\PrakharDixit\\ProfitabilityEnhancement\\src\\main\\resources\\travelInformation\\");
	                                 
	        paths = f.listFiles();
	        
	        for(File st : paths)
	        {
	        	
//	        	System.out.println("4");
	        	
				reader = new CSVReader(new FileReader(st.getAbsolutePath()),',');
				

	        	
	        	List<String[]> records = reader.readAll();
	 		
	 		
	        	String fs[];

	        	Iterator<String[]> iterator = records.iterator();
	        	iterator.next();
	 		
	        	while (iterator.hasNext()) 
	        	{
	        	
	        		String[] record = iterator.next();
//	        			t=session.beginTransaction();
		        		
	        			truck=new Truck();
	        			
	        			truck.setLat(Float.parseFloat(record[0]));
	        			
	        			truck.setLongi(Float.parseFloat(record[1]));
	        			
	        			truck.setCode(Integer.parseInt(record[2]));
	        			
	        			truck.setName(record[3]);
	        			
	        			truck.setAddress(record[4]);
	        			
	        			truck.setMobile(Long.parseLong(record[5]));
	        			
	        			truck.setEmail(record[6]);
	        			
//	        			trucks.add(truck);
	        			
//	        			System.out.println(truck);
	        			System.out.println(truckRepository);
	        			truckRepository.save(truck);
	        			
//	        			session.save(truck);
	        			
//		        		t.commit();
		        		
		        		System.out.println(truck);
	 		   }
	        	
	     }
	        
	        
//	        truckRepository.saveAll(trucks);
//	        session.close();
	      }
	      catch(Exception e)
  		{
  			e.printStackTrace();
  		}
	        	
	}

}
