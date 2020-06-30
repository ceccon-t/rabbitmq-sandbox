package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PictureProducerTwo {
	
	@Autowired
	private RabbitTemplate template;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessage(Picture picture) {
		
		var sb = new StringBuilder();
		
		sb.append(picture.getSource());
		sb.append(".");
		
		var size = (picture.getSize() > 4000) ? "large" : "small";
		sb.append(size);
		sb.append(".");
		
		sb.append(picture.getType());
		
		try {
			var json = objectMapper.writeValueAsString(picture);
			var routingKey = sb.toString();
			template.convertAndSend("x.picture2", routingKey, json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
