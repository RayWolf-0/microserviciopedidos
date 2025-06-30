package cl.duoc.MicroServicioPedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.MicroServicioPedidos.Assembler.PedidosAssembler;
import cl.duoc.MicroServicioPedidos.entity.Pedido;
import cl.duoc.MicroServicioPedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para trabajar los Pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired 
    private PedidosAssembler linkapis;
    //endpoint que lista los pedidos
    @GetMapping
    @Operation(summary = "Pedidos", description = "Operación que lista los Pedidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listó correctamente los pedidos",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro nada en los pedidos",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> obtenerPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.obtenerPedidos();
            return ResponseEntity.ok(linkapis.toCollectionModel(pedidos));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener pedidos: " + e.getMessage());
        }
    }

    //endpoint que busca un pedido
    @GetMapping("/{id}")
    @Operation(summary = "Endpoint que busca un Pedido", description = "Operación que busca y lista un Pedido")
    @Parameters(value = {
        @Parameter(name = "id_Pedido", description = "Id de el Pedido que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el Pedido",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentra el Pedido",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran Pedidos")))
    })
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        try {
            Pedido pedido = pedidoService.obtenerPorId(id);
            return pedido != null
                    ? ResponseEntity.ok(linkapis.toModel(pedido))
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al buscar el pedido: " + e.getMessage());
        }
    }

    //endpoint paea guardar un pedido
    @PostMapping
    @Operation(summary = "Endpoint que registra un Pedido", description = "Endpoint que registra un Pedido", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Pedido que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = Pedido.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el Pedido", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el Pedido",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar el Pedido")))
    })
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
            return ResponseEntity.ok(linkapis.toModel(nuevoPedido));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el pedido: " + e.getMessage());
        }
    }


    //endpoint que edita un pedido
    @PutMapping("/{id}")
    @Operation(summary = "Endpoint que edita un Pedido", description = "Endpoint que edita un Pedido", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Pedido que se va a editar", required = true,
    content = @Content(schema = @Schema(implementation = Pedido.class))
    ))
    @Parameters(value = {
        @Parameter(name = "idPedido", description = "Id del Pedido que se va a editar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el Pedido", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "500", description = "Pedido no esta registrada",
        content = @Content(schema = @Schema(type = "String", example = "Pedido no esta registrado")))
    })
    public ResponseEntity<?> actualizarPedido(@PathVariable int id, @RequestBody Pedido pedidoActualizado) {
        try {
            Pedido pedidoExistente = pedidoService.obtenerPorId(id);
            if (pedidoExistente != null) {
                pedidoActualizado.setIdPedido(id);
                Pedido actualizado = pedidoService.guardarPedido(pedidoActualizado);
                return ResponseEntity.ok(actualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    //endpoint que elimina un pedido
    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint que busca y elimina un Pedido", description = "Operación que busca y elimina un Pedido")
    @Parameters(value = {
        @Parameter(name = "id_Pedido", description = "Id del Pedido que se va a eliminar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se elimina Pedido",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = "string", example = "Se elimina Pedido"))),
        @ApiResponse(responseCode = "404", description = "Pedido no esta registrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "Pedido no esta registrado")))
    })
    public ResponseEntity<?> eliminarPedido(@PathVariable int id) {
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el pedido: " + e.getMessage());
        }
    }
}
