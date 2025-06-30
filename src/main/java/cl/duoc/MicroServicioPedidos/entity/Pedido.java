package cl.duoc.MicroServicioPedidos.entity;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PEDIDOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un pedido")

public class Pedido {

    @Id
    @Column(name = "ID_PEDIDO")
    @Schema(description = "id del pedido")

    private int idPedido;

    @Column(name = "ID_USUARIO", nullable = false)
    @Schema(description = "id usuario que hace el pedido")

    private int idUsuario;

    @Column(name = "FECHA", nullable = false)
    @Schema(description = "fecha del pedido")

    private LocalDate fecha;

    @Column(name = "ESTADO", nullable = false)
    @Schema(description = "Estado del pedido")

    private String estado;

    @Column(name = "DIRECCION_ENVIO", nullable = false)
    @Schema(description = "direccion envio del pedido")

    private String direccionEnvio;
}
