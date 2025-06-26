package cl.duoc.MicroServicioPedidos.Assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.duoc.MicroServicioPedidos.controller.PedidoController;
import cl.duoc.MicroServicioPedidos.entity.Pedido;

@Component
public class PedidosAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{
    @Override
    public EntityModel<Pedido> toModel(Pedido Ped){
        return EntityModel.of(
            Ped,
            linkTo(methodOn(PedidoController.class).obtenerPorId(Ped.getIdPedido())).withRel("Lista-el-pedido-buscado"),
            linkTo(methodOn(PedidoController.class).obtenerPedidos()).withRel("Todas-los-Items"),
            linkTo(methodOn(PedidoController.class).eliminarPedido(Ped.getIdPedido())).withRel("Eliminar-un-item"),
            linkTo(methodOn(PedidoController.class).actualizarPedido(Ped.getIdPedido(), Ped)).withRel("Actualizar-un-Item")
        );
    }
}
