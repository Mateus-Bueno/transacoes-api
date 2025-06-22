package com.ana.transacao.controller;

import com.ana.transacao.model.Transacao;
import com.ana.transacao.messaging.TransacaoProducer;
import com.ana.transacao.service.TransacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> atualizarTransacao(@PathVariable Long id, @RequestBody Transacao novaTransacao) {
        try {
            Transacao atualizada = service.atualizar(id, novaTransacao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
