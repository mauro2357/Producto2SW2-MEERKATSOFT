package Presentacion;

import java.util.ArrayList;

import Datos.ProductoRepository;
import Negocio.tomaynotificacionpedidos.Producto;

public class consultarproductosFacade {
	public ArrayList<Producto> main() throws Exception{
		ProductoRepository productorepository = new ProductoRepository();
		ArrayList<Producto> x = productorepository.ConsultarProducto();
		return x;
	}
	public Producto consultarproductoindividual(String id) throws Exception{
		ProductoRepository productorepository = new ProductoRepository();
		ArrayList<Producto> x= productorepository.ConsultarProducto();
		for(Producto producto: x){
			if(producto.getCodigo().equalsIgnoreCase(id)) return producto;
		}
		return null;
	}
}
