package com.pv.louvor;

import java.text.ParseException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(ApiLouvorApplication.class, args);
		
        /*Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        String nome = ""; 
        int dia = c.get(Calendar.DAY_OF_WEEK); 
        switch(dia){ 
        case Calendar.SUNDAY: nome = "Domingo"; break; 
        case Calendar.MONDAY: nome = "Segunda"; break; 
        case Calendar.TUESDAY: nome = "Terça"; break; 
        case Calendar.WEDNESDAY: nome = "Quarta"; break; 
        case Calendar.THURSDAY: nome = "Quinta"; break; 
        case Calendar.FRIDAY: nome = "Sexta"; break; 
        case Calendar.SATURDAY: nome = "sábado"; break; 
        } 
        System.out.println(nome);*/
		
	}

	@Override
	public void run(String... args) throws Exception {
	
}
	
	
}
