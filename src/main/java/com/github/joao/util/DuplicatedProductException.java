package com.github.joao.util;

import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;

public class DuplicatedProductException extends RuntimeException {
    private Produto produto;
    private Sabor sabor;

    public DuplicatedProductException(String message) {
        super(message);
    }

    public DuplicatedProductException(String message, Produto produto, Sabor sabor) {
        super(message);
        this.produto = produto;
        this.sabor = sabor;
    }

    public Produto getProduto() {
        return produto;
    }

    public Sabor getSabor() {
        return sabor;
    }
}
