package com.course.rabbitmqproducer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.entity.Employee;
import com.course.rabbitmqproducer.producer.RetryEmployeeProducer;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner{
	
	@Autowired
	private RetryEmployeeProducer employeeProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		for (int i = 0; i < 10; i++) {
			Employee emp = new Employee("Employee " + i, null, LocalDate.now());
			employeeProducer.sendMessage(emp);
		}
	}

}
