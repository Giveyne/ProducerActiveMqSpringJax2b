package ru.pflb.ershov;

import com.ershov.order.MessageToQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${outbound.endpoint}")
    private String destination;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }

    @Override
    public void run(String... strings) throws Exception {
        MessageToQueue messageToQueue = new MessageToQueue();
        messageToQueue.setMessage("Hello queue");
        messageToQueue.setBodyMessage("I am body queue");
        jmsTemplate.convertAndSend(destination, messageToQueue);
    }
   
}