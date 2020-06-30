package com.course.rabbitmqconsumer.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

@Service
public class MyPictureImageConsumer {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(MyPictureImageConsumer.class);
	
	@RabbitListener(queues = "q.mypicture.image")
	public void list(Message message, Channel channel) throws JsonParseException, JsonMappingException, IOException {
		var p = objectMapper.readValue(message.getBody(), Picture.class);
		
		if (p.getSize() > 9000) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		}
		
		log.info("On image: {}", p.toString());
		
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}
