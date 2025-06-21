package com.ana.transacao.messaging;

import com.ana.transacao.config.RabbitMQConfig;
import com.ana.transacao.model.Transacao;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransacaoProducer {

    private final AmqpTemplate amqpTemplate;

    public TransacaoProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviar(Transacao transacao) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, transacao);
    }
}
