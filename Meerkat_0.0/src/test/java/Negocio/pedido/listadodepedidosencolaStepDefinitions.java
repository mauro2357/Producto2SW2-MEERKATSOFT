package Negocio.pedido;

import java.util.*;

import org.junit.Assert;

import Negocio.inventario.Administrador;
import Negocio.pedido.Mesero;
import Negocio.pedido.Producto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class listadodepedidosencolaStepDefinitions {
	
	Mesero mesero = null;
	Administrador administrador = null;
	
	ArrayList<Producto> lista = new ArrayList<Producto>();
	
	Producto producto = new Producto("9090", "cerveza", 2000, "Refresacante","a","a");
	String mesa = "10";
	String cliente = "1001";
	String meser = "800";
	String caja = "5005";
	Calendar x = Calendar.getInstance();
	String fecha = x.get(Calendar.YEAR)+"-"+Integer.toString(x.get(Calendar.MONTH)+1)+"-"+x.get(Calendar.DATE);
	String estado = "En espera";
	
	@Given("^El mesero envia un pedido al despachador.$")
	public void El_mesero_envia_un_pedido_al_despachador() throws Throwable { 
		mesero = new Mesero();
		lista.add(producto);
	}

	@When("^Hay productos en el pedido.$")
	public void Hay_productos_en_el_pedido() throws Throwable {
		Assert.assertTrue(lista.size()>0);  
	}

	@Then("^Agregar el pedido a la cola de pedidos.$")
	public void Agregar_el_pedido_a_la_cola_de_pedidos() throws Throwable {
		Pedido pedido = new Pedido();
		Mesa mesam = mesero.Definir_Mesa(mesa);
	    Assert.assertTrue("Pedido agregado" == mesero.Agregar_Pedido_a_la_cola_de_pedidos(mesam,pedido));
	}
	
	@When("^No hay productos en el pedido.$")
	public void No_hay_productos_en_el_pedido() throws Throwable {
		Assert.assertTrue(!(lista.size()<0));
	}

	@Then("^Notificar que no hay productos en el pedido.$")
	public void Notificar_que_no_hay_productos_en_el_pedido() throws Throwable {
		Pedido pedido = new Pedido();
		Mesa mesam = mesero.Definir_Mesa(mesa);
		Assert.assertTrue("No hay productos." == mesero.finiquitarpedido(pedido, cliente, mesero.getId(), mesam, caja, fecha));
	}
	@When("^Hay despachador.$")
	public void Hay_despachador() throws Throwable {
		Assert.assertTrue(administrador.Consultar_despachador());
	}
	
	
	@When("^No hay despachador.$")
	public void No_hay_despachador() throws Throwable {
		Assert.assertTrue(administrador.Consultar_despachador());
	}

	
	@Then("^Notificar que no hay despachador.$")
	public void Notificar_que_no_hay_despachador() throws Throwable {
		Assert.assertEquals("No hay despachador.", administrador.getMessageHayDespachador());
	}
	
	@Then("^Notificar que no hay mesero.$")
	public void Notificar_que_no_hay_mesero() throws Throwable {
		Assert.assertEquals("No hay mesero.", administrador.getMessageHayMesero());
	}
		

	
	
}
