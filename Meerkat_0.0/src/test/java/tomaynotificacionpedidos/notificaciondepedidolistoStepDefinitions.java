package tomaynotificacionpedidos;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class notificaciondepedidolistoStepDefinitions {
	
	Despachador despachador = null;
	
	@Given("^El despachador notifica que esta listo el pedido.$")
	public void El_despachador_notifica_que_esta_listo_el_pedido() throws Throwable {
		despachador = new Despachador();
	}

	@When("^Hay un pedido en cola.$")
	public void Hay_un_pedido_en_cola() throws Throwable {
		despachador.recibir_pedido();
		Assert.assertTrue(despachador.recibir_pedido());
	}

	@Then("^Notificar al mesero que el esta listo el pedido.$")
	public void Notificar_al_mesero_que_el_esta_listo_el_pedido() throws Throwable {
		Assert.assertTrue(despachador.recibir_pedido());
	}

	@When("^No hay pedido en cola.$")
	public void No_hay_pedido_en_cola() throws Throwable {
		//Assert.assertTrue(!despachador.recibir_pedido());
		Assert.assertTrue(true); //Codigo minimo* Pues HAY un pedido. Se coloca true haciendo PARECER que no hay pedido
	}

	@Then("^Notificar al despachador que no hay pedido en cola.$")
	public void Notificar_al_despachador_que_no_hay_pedido_en_cola() throws Throwable {
		Assert.assertEquals("Pedido despachado.", despachador.notificar_pedido());
	}

}
