package Presentacion;

import java.util.ArrayList;

import Datos.ProductoRepository;
import Negocio.tomaynotificacionpedidos.Producto;

public class consultarproductosFacade {
	public static ArrayList<Producto> main(String []arg) throws Exception{
		ProductoRepository productorepository = new ProductoRepository();
		ArrayList<Producto> x = productorepository.ConsultarProducto();
		return x;
	}
}
