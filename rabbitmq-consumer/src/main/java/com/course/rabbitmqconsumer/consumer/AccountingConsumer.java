package com.course.rabbitmqconsumer.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class AccountingConsumer {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(AccountingConsumer.class);
	
	@RabbitListener(queues = "q.hr.accounting")
	public void listen(String message) {
		
		Employee employee;
		try {
			employee = objectMapper.readValue(message, Employee.class);
			log.info("On accounting, employee is {}", employee);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
