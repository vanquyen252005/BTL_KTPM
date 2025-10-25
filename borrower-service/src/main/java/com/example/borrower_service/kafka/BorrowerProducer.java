package com.example.borrower_service.kafka;

import com.example.borrower_service.dto.BorrowerEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class BorrowerProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerProducer.class);

    //topic in class KafkaTopicConfig
    private NewTopic topic;

    //Template for sending, need config pom.xml from domain
    private KafkaTemplate<String, BorrowerEvent> kafkaTemplate;

    public BorrowerProducer(NewTopic topic, KafkaTemplate<String, BorrowerEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BorrowerEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        //create Message
        Message<BorrowerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
