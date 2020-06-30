package com.course.rabbitmqproducer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.entity.Picture;
import com.course.rabbitmqproducer.producer.MyPictureProducer;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner{
	
	/*
	@Autowired
	private HelloRabbitProducer helloRabbitProducer;
	
	@Autowired
	private FixedRateProducer fixedRateProducer;
	
	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;
	
	@Autowired
	private HumanResourceProducer employeeJsonProducer;
	
	@Autowired
	private PictureProducer pictureProducer;
	
	@Autowired
	private PictureProducerTwo pictureProducer;
	*/
	
	@Autowired
	private MyPictureProducer pictureProducer;
	
	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//helloRabbitProducer.sendHello("Tiago " + Math.random());
		/*
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee("id" + i, "Employee " + i, LocalDate.now());
			employeeJsonProducer.sendMessage(employee);
		}
		*/
		/*
		for (int i = 0; i < 10; i++) {
			var p = new Picture();
			p.setName("Picture " + i);
			p.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
			p.setSource(SOURCES.get(i % SOURCES.size()));
			p.setType(TYPES.get(i % TYPES.size()));
			
			pictureProducer.sendMessage(p);
		} 
		*/
		var p = new Picture();
		p.setName("Picture " + 1);
		p.setSize(ThreadLocalRandom.current().nextLong(9001, 10001));
		p.setSource(SOURCES.get(1 % SOURCES.size()));
		p.setType(TYPES.get(1 % TYPES.size()));
		
		System.out.println("Sending picture");
		pictureProducer.sendMessage(p);
		System.out.println("Sent picture");
		
	}

}
