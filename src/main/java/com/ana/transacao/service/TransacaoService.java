package com.ana.transacao.service;

import com.ana.transacao.model.Transacao;
import com.ana.transacao.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Transacao salvar(Transacao transacao) {
        System.out.println("Evento Kafka enviado: Nova Transação criada!");
        return repository.save(transacao);
    }

    public Transacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação com id " + id + " não encontrada."));
    }

    public Transacao atualizar(Long id, Transacao novaTransacao) {
        Transacao existente = buscarPorId(id); // já lança exceção se não existir

        existente.setValor(novaTransacao.getValor());
        existente.setData(novaTransacao.getData());
        existente.setDescricao(novaTransacao.getDescricao());
        existente.setUserId(novaTransacao.getUserId());
        existente.setCategoriaId(novaTransacao.getCategoriaId());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Transação com id " + id + " não encontrada.");
        }
        repository.deleteById(id);
    }
}
