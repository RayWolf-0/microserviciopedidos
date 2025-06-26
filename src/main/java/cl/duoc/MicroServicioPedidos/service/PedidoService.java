package cl.duoc.MicroServicioPedidos.service;

import java.util.List;

import cl.duoc.MicroServicioPedidos.entity.Pedido;

public interface PedidoService {

    List<Pedido> obtenerPedidos();
    Pedido obtenerPorId(int id);
    Pedido guardarPedido(Pedido pedido);
    void eliminarPedido(int id);
}
