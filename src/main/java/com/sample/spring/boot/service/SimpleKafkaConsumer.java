package com.sample.spring.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SimpleKafkaConsumer {
  private final Logger logger =
      LoggerFactory.getLogger(SimpleKafkaConsumer.class);

  @Autowired
  private SimpleKafkaProducer kafkaProducer;

  @KafkaListener(topics = "${simple.kafka.consumer.topic}",
      groupId = "${simple.kafka.consumer.group-id}")
  public void consume(String inEvent) {
    logger.info("new event received at: {}", System.nanoTime());
    kafkaProducer.sendMessageToKafka(generateResponse(inEvent));
  }

  private String generateResponse(String inEvent) {
    return new StringBuilder("update time:")
        .append(System.currentTimeMillis())
        .append("\nprocessId:")
        .append(UUID.randomUUID())
        .append("\n")
        .append(inEvent)
        .toString();
  }
}
