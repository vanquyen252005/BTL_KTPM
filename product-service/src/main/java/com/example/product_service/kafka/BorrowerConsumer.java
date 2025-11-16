package com.example.product_service.kafka;

import com.example.base_domains.dto.BorrowerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BorrowerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.borrower}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(BorrowerEvent event){
        LOGGER.info(String.format("Borrower event received in product service => %s", event.toString()));

        //consume?
        //
        //

    }
}
