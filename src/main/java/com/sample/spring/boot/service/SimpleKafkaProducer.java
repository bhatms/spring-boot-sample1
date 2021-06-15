package com.sample.spring.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaProducer {

  private final Logger logger =
      LoggerFactory.getLogger(SimpleKafkaProducer.class);

  @Value("${simple.kafka.producer.topic}")
  private String topicName;

  @Autowired
  @Qualifier("simpleKafkaProducerTemplate")
  private KafkaTemplate<String, String> simpleProducerTemplate;

  public void sendMessageToKafka(String message) {
    logger.info("sending message to ->{}  at:{}", topicName, System.nanoTime());
    this.simpleProducerTemplate.send(topicName, message);
  }
}
