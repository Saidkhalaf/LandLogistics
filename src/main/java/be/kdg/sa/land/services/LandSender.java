package be.kdg.sa.land.services;

import be.kdg.sa.land.config.RabbitMQTopology;
import be.kdg.sa.land.controller.dto.WarehouseTicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandSender {

    private final Logger logger = LoggerFactory.getLogger(LandSender.class);
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public LandSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToTruckQueue(WarehouseTicketDto message) {
        logger.info("Sending message to truck queue: {}", message);
        rabbitTemplate.convertAndSend(RabbitMQTopology.TOPIC_EXCHANGE, RabbitMQTopology.TRUCK_QUEUE, message);
        logger.info("Message sent to exchange: {} with routing key: {}", RabbitMQTopology.TOPIC_EXCHANGE, RabbitMQTopology.TRUCK_ROUTING_KEY);
    }
}
