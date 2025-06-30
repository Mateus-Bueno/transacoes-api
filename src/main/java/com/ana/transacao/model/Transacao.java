package com.ana.transacao.model;

import jakarta.persistence.*;
import java.io.Serializable;  // IMPORTANTE!
import java.time.LocalDate;

@Entity
@Table(name = "transacoes")
public class Transacao implements Serializable {  // IMPLEMENTA Serializable

    private static final long serialVersionUID = 1L;  // recomendado para Serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(length = 255)
    private String descricao;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    public Transacao() {}

    public Transacao(Double valor, LocalDate data, String descricao, Long userId, Long categoriaId) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.userId = userId;
        this.categoriaId = categoriaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
