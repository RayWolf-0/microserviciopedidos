package cl.duoc.MicroServicioPedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.MicroServicioPedidos.entity.Pedido;
import cl.duoc.MicroServicioPedidos.repository.PedidoRepository;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPorId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}
