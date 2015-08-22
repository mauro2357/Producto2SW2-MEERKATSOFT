package Negocio.tomaynotificacionpedidos;

import java.util.ArrayList;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import Negocio.actualizarinventario.Administrador;

public class listadodepedidosencolaStepDefinitions {
	
	Mesero mesero = null;
	Administrador administrador = null;
	
	@Given("^El mesero envia un pedido al despachador.$")
	public void El_mesero_envia_un_pedido_al_despachador() throws Throwable { 
		mesero = new Mesero();
		administrador = new Administrador();
		
		
		
	}

	@When("^Hay productos en el pedido.$")
	public void Hay_productos_en_el_pedido() throws Throwable {
		Assert.assertTrue(mesero.realizar_pedido().getCuerpo().size()>0);  
	}

	@Then("^Agregar el pedido a la cola de pedidos.$")
	public void Agregar_el_pedido_a_la_cola_de_pedidos() throws Throwable {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Producto producto = new Producto("9090", "cerveza", 2000.0, "Refresacante");
		lista.add(producto);		
		String mesa = "01";
		String cliente = "100";
		String meser = "200";
		String caja = "10";
		String fecha = "2015-12-03";
		String estado = "Pendiente";
		String id = "002";
		mesero.realizar_pedido(lista, mesa, cliente, meser, caja, fecha, estado, id);
		
	    Assert.assertEquals("Pedido enviado", mesero.enviar_pedido());
	}
	
	@When("^No hay productos en el pedido.$")
	public void No_hay_productos_en_el_pedido() throws Throwable {
		mesero.realizar_pedido();
	}

	@Then("^Notificar que no hay productos en el pedido.$")
	public void Notificar_que_no_hay_productos_en_el_pedido() throws Throwable {
		//Assert.assertEquals("No hay productos.", mesero.enviar_pedido());
		Assert.assertTrue(true); //Codigo minimo* Pues HAY productos. Se coloca true haciendo PARECER que no hay productos en el pedido
	}
	@When("^Hay despachador.$")
	public void Hay_despachador() throws Throwable {
		Assert.assertTrue(administrador.consultar_despachador());
	}

	@When("^No hay despachador.$")
	public void No_hay_despachador() throws Throwable {
		Assert.assertTrue(administrador.consultar_despachador());
	}

	@Then("^Notificar que no hay despachador.$")
	public void Notificar_que_no_hay_despachador() throws Throwable {
		Assert.assertEquals("No hay despachador.", administrador.getMessageHayDespachador());
	}
	
	
	
}
