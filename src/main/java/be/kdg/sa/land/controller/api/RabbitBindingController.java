package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.config.RabbitMQTopology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class RabbitBindingController {

    private final Logger logger = LoggerFactory.getLogger(RabbitBindingController.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitBindingController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(   "/{message}")
    public void sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend(RabbitMQTopology.TOPIC_EXCHANGE, "late", message);
        logger.info("Message sent to exchange: {} with routing key: {}", RabbitMQTopology.TOPIC_EXCHANGE, "late");
    }
}
