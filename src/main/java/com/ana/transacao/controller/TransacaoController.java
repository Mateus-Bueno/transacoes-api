package com.ana.transacao.controller;

import com.ana.transacao.model.Transacao;
import com.ana.transacao.messaging.TransacaoProducer;
import com.ana.transacao.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;
    private final TransacaoProducer producer;

    public TransacaoController(TransacaoService service, TransacaoProducer producer) {
        this.service = service;
        this.producer = producer;
    }

    @GetMapping
    public List<Transacao> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Transacao criar(@RequestBody Transacao transacao) {
        Transacao nova = service.salvar(transacao);
        producer.enviar(nova); // envia mensagem para a fila
        return nova;
    }

    @GetMapping("/{id}")
    public Transacao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Transacao atualizar(@PathVariable Long id, @RequestBody Transacao transacao) {
        Transacao atualizada = service.atualizar(id, transacao);
        producer.enviar(atualizada); // envia mensagem atualizada para a fila
        return atualizada;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
