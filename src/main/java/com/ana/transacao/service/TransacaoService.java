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
        return repository.findById(id).orElse(null);
    }

    public Transacao atualizar(Long id, Transacao novaTransacao) {
        Transacao existente = buscarPorId(id);
        if (existente != null) {
            existente.setValor(novaTransacao.getValor());
            existente.setData(novaTransacao.getData());
            existente.setDescricao(novaTransacao.getDescricao());
            existente.setUserId(novaTransacao.getUserId());
            existente.setCategoriaId(novaTransacao.getCategoriaId());
            return repository.save(existente);
        }
        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}