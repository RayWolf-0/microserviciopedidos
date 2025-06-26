package cl.duoc.MicroServicioPedidos.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.MicroServicioPedidos.entity.Pedido;
import cl.duoc.MicroServicioPedidos.repository.PedidoRepository;
import cl.duoc.MicroServicioPedidos.service.PedidoServiceImpl;

public class TestPedidosService {
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){

        List<Pedido> lista = new ArrayList<>();

        Pedido Pedido1 = new Pedido();
        Pedido Pedido2 = new Pedido();

        Pedido1.setIdPedido(1);
        Pedido1.setDireccionEnvio("el bosque 271");
        Pedido1.setEstado("en curso");
        //Pedido1.setFecha("01/01/2025");
        Pedido1.setIdUsuario(2);

        Pedido2.setIdPedido(2);
        Pedido2.setDireccionEnvio("los captus 479");
        Pedido2.setEstado("entregadp");
        //Pedido2.setFecha("02/02/2025");
        Pedido2.setIdUsuario(3);

        lista.add(Pedido1);
        lista.add(Pedido1);

        when(pedidoRepository.findAll()).thenReturn(lista);

        List<Pedido> resultadoBusqueda = pedidoServiceImpl.obtenerPedidos();

        assertEquals(2, resultadoBusqueda.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarUnItem(){
        Pedido Pedido1 = new Pedido();
        Pedido1.setIdPedido(1);
        Pedido1.setDireccionEnvio("el bosque 271");
        Pedido1.setEstado("en curso");
        //Pedido1.setFecha("01/01/2025");
        Pedido1.setIdUsuario(2);

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(Pedido1));

        Pedido itembuscado = pedidoServiceImpl.obtenerPorId(1);
        assertEquals(1, itembuscado.getIdPedido());
        verify(pedidoRepository, times(1)).findById(1);
    }

    @Test
    public void testGuardarPedido(){
        Pedido Pedido1 = new Pedido();
        Pedido1.setIdPedido(1);
        Pedido1.setDireccionEnvio("el bosque 271");
        Pedido1.setEstado("en curso");
        //Pedido1.setFecha("01/01/2025");
        Pedido1.setIdUsuario(2);

        when(pedidoRepository.save(Pedido1)).thenReturn(Pedido1);

        Pedido PedidoGuardado = pedidoServiceImpl.guardarPedido(Pedido1);

        assertEquals(1, PedidoGuardado.getIdPedido());
        verify(pedidoRepository, times(1)).save(Pedido1);

    }

    @Test
    public void testeliminarPedido(){
        int Id_item = 1;
        doNothing().when(pedidoRepository).deleteById(Id_item);

        pedidoServiceImpl.eliminarPedido(Id_item);

        verify(pedidoRepository,times(1)).deleteById(Id_item);
    } 
}
