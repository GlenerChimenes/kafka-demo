package com.example.dockbank.domain.model;

public class Pedido {

    private Long id;
    private Double valor;
    private boolean pago;

    public Pedido(Double valor) {
        this.valor = valor;
        this.pago = false;
    }

    public void marcarComoPago() {
        if(this.pago) {
            throw new RuntimeException("Pedido já pago");
        }
        this.pago = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Double getValor() {
        return valor;
    }

    public Long getId() {
        return this.id;
    }
}
