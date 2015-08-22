package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.geraciondefactura.Factura;
import Negocio.tomaynotificacionpedidos.Producto;
public class FacturaRepository {
	public void ingresarPedido(ArrayList<Producto> x, String mesa, String mesero, String cliente) throws Exception {
		

		
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "INSERT INTO `future`.`venta` (`Ven_id`, `Ven_fecha`, `Ven_estado`, `Cli_id`, `Me_id`, `Mesa_id`, `Caj_id`) VALUES ('002', '2015-03-04', 'pendiente', '30', '50', '02', '011');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
		for(Producto producto: x){
			
		}
	    st.close();
	    
	}
	
	public ArrayList<Factura> generarfactura() throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "SELECT * FROM new_view";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    ArrayList<Factura> f = new ArrayList<Factura>();
	    while (rs.next()){
	      String id = rs.getString("Ven_id");
	      String meser = rs.getString("Me_nombre");
	      String cajer = rs.getString("Caj_nombre");
	      String mesa = rs.getString("Mesa_id");
	      String producto=rs.getString("Pro_id"+""+"Pro_nombre"+"Pro_valor");
	      Factura fi = new Factura(id,meser, cajer,mesa,producto);
	      f.add(fi);
	    }
	    st.close();
	    return f;
	}
}
