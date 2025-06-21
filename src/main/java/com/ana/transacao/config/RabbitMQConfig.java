package com.ana.transacao.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para a aplicação de transações.
 * Define fila, exchange e routing key.
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "transacoes-queue";
    public static final String EXCHANGE = "transacoes-exchange";
    public static final String ROUTING_KEY = "transacoes-routing-key";

    /**
     * Criação da fila.
     * @return Queue não durável (false)
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false); // false = não durável
    }

    /**
     * Criação da exchange do tipo Topic.
     * @return TopicExchange
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    /**
     * Criação do binding entre a fila e a exchange com a routing key definida.
     * @param queue fila a ser vinculada
     * @param exchange exchange que publica mensagens
     * @return Binding configurado
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
