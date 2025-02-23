package be.kdg.sa.land.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {


    public static final String TOPIC_EXCHANGE = "land-to-warehouse-exchange";
    public static final String TRUCK_QUEUE = "truckQueue";
    public static final String TRUCK_ROUTING_KEY = "late";

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue truckQueue(){
        return new Queue(TRUCK_QUEUE,true, false, false);
    }

    @Bean
    public Binding truckBinding(TopicExchange topicExchange, Queue truckQueue) {
        return new Binding(truckQueue.getName(), Binding.DestinationType.QUEUE, topicExchange.getName(), TRUCK_ROUTING_KEY, null);
    }
}
