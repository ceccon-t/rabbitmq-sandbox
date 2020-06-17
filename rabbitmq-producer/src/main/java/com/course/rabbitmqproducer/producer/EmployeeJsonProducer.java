package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeJsonProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessage(Employee employee) {
		try {
			String jsonEmployee = objectMapper.writeValueAsString(employee);
			rabbitTemplate.convertAndSend("course.employee", jsonEmployee);
		} catch (AmqpException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
