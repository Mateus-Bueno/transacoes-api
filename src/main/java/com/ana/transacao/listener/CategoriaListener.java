package com.ana.transacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por ouvir mensagens da fila 'categorias.fila'
 * e processá-las assim que forem recebidas.
 */
@Component
public class CategoriaListener {

    /**
     * Método que é automaticamente executado quando uma nova mensagem
     * é publicada na fila 'categorias.fila'.
     *
     * @param mensagem Conteúdo da mensagem recebida da fila (esperado em formato JSON ou texto).
     */
    @RabbitListener(queues = "categorias.fila")
    public void receberMensagem(String mensagem) {
        System.out.println("📩 Mensagem recebida da fila de categorias: " + mensagem);

        // Aqui você pode fazer o parsing do JSON e salvar no banco de dados, por exemplo.
        // Exemplo (futuro):
        // CategoriaDTO categoria = new ObjectMapper().readValue(mensagem, CategoriaDTO.class);
        // categoriaService.salvar(categoria);
    }
}
