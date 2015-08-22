package Negocio.tomaynotificacionpedidos;

import java.util.ArrayList;

import Datos.MeseroRepository;
import Presentacion.consultarproductosFacade;

public class Mesero {
	
	String id;
	String nombre;
	String apellido;
	String telefono;
	ArrayList<Producto> x;
	
	MeseroRepository conexion = new MeseroRepository();
	
	public Mesero(String id, String nombre, String apellido, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}

	public Mesero() {
	}

	public ArrayList<Producto> consultarproductos() throws Exception {
		consultarproductosFacade y = new consultarproductosFacade();
		ArrayList<Producto> x = new ArrayList<Producto>();
		x = y.main();
		this.x = x;
		return x;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getMensaje() {
		if(this.x.size()==0) return "No hay productos";
		return "Hay productos";
	}
	
	
	public Pedido realizar_pedido(ArrayList<Producto> lista, String mesa, String cliente, String meser, String caja, String fecha, String estado, String id){

		Pedido x = new Pedido(lista);
		return x;
	}
	
	public String enviar_pedido(){
		if(realizar_pedido().getCuerpo().size()==0){
			return "No hay productos.";
		}
		return "Pedido enviado";
	}
	

}
