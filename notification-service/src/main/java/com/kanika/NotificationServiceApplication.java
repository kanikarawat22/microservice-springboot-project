package com.kanika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import com.kanika.OrderPlacedEvent;

import lombok.extern.slf4j.Slf4j; // <-- ADD THIS IMPORT

@SpringBootApplication
@Slf4j // <-- ADD THIS ANNOTATION
public class NotificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(OrderPlacedEvent orderPlacedEvent) 
  {
    // The 'log' variable is now automatically available here
    log.info("Received Notification for order - {}", orderPlacedEvent.getOrderNumber()); 
  }
}