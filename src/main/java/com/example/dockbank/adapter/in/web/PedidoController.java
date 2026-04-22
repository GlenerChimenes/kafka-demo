package com.example.dockbank.adapter.in.web;

import com.example.dockbank.application.usecase.CriarPedidoUseCase;
import com.example.dockbank.domain.model.Pedido;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase useCase;

    public PedidoController(CriarPedidoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public void criarPedido(@RequestBody Map<String, Double> body) {
        useCase.executar(body.get("valor"));
    }
}
