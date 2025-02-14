package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.pedido.Producto;

public class ProductoRepository {
	
	public ArrayList<Producto> Consultar_producto() throws Exception { 
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "select * from producto order by Pro_tipo, Pro_id;";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    ArrayList<Producto> a = new ArrayList<Producto>();
	    while (rs.next()){
	      String codigo = rs.getString("Pro_id");
	      String nombre = rs.getString("Pro_nombre");
	      int valor = rs.getInt("Pro_valor");
	      String descripcion = rs.getString("Pro_descripcion");
	      String tipo = rs.getString("Pro_tipo");
	      String imagen = rs.getString("Pro_imagen");
	      Producto p = new Producto(codigo, nombre, valor, descripcion, tipo, imagen);
	      a.add(p);     
	    }
	    st.close();
	    return a; 
	}
}
