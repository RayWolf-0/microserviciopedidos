package cl.duoc.MicroServicioPedidos;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duoc.MicroServicioPedidos.entity.Pedido;
import cl.duoc.MicroServicioPedidos.service.PedidoServiceImpl;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    private final Faker faker = new Faker(new Locale("es","cl"));
    private final Random random = new Random();

    @Autowired
    private PedidoServiceImpl pedidoServiceImpl;

    @Override
    public void run(String... args) throws Exception{
        for (int i=0; i > 10; i ++){
            Pedido nuevorol = new Pedido();
            nuevorol.setDireccionEnvio(faker.address().fullAddress());
            nuevorol.setEstado(random.toString());
            //nuevorol.setFecha(random.wait);
            nuevorol.setIdPedido(random.nextInt());
            nuevorol.setIdUsuario(random.nextInt());

            pedidoServiceImpl.guardarPedido(nuevorol);
            System.out.println("Item Guardado" + nuevorol.getIdPedido());
        
        }
    } 
}
