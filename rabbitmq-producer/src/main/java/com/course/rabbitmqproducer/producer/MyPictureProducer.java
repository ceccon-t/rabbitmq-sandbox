package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyPictureProducer {
	
	@Autowired
	private RabbitTemplate template;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessage(Picture picture) {
		String json;
		try {
			json = objectMapper.writeValueAsString(picture);
			template.convertAndSend("x.mypicture", "", json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
