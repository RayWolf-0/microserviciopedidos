package cl.duoc.MicroServicioPedidos.entity;


import java.time.LocalDate;

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
public class Pedido {

    @Id
    @Column(name = "ID_PEDIDO")
    private int idPedido;

    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "DIRECCION_ENVIO", nullable = false)
    private String direccionEnvio;
}
