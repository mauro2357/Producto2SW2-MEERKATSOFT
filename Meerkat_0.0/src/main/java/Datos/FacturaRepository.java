package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Negocio.factura.Factura;
import Negocio.pedido.*;

public class FacturaRepository {
	
	public void Ingresar_pedido(Pedido x) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "INSERT INTO `future`.`venta` (`Ven_fecha`, `Ven_estado`, `Cli_id`, `Me_id`, `Mesa_id`) VALUES ('"+x.fecha+"', '"+x.estado+"', '"+x.cliente+"', '"+x.mesero.getId()+"', '"+x.mesa.getId()+"');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    query = "Select * from venta order by ven_id desc limit 1";
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    rs.first();
	    String ven_id = rs.getString("Ven_id");
		ArrayList<String> visitados = new ArrayList<String>();
		for(Producto producto: x.getCuerpo()){
			if(visitados.contains(producto.getCodigo())) continue;
			int aux=0;
			for(Producto auxproducto: x.getCuerpo()){
				if(producto.getCodigo().equalsIgnoreCase(auxproducto.getCodigo())){
					visitados.add(auxproducto.getCodigo());
					aux++;
				}
			}
			query = "INSERT INTO detalles_venta (`Pro_id`, `Ven_id`, `Dtv_cantidad`) VALUES ('"+producto.getCodigo()+"','"+ven_id+"','"+aux+"');";
			st.executeUpdate(query);
		}
	    st.close();
	}
	
	public ArrayList<Factura> Generar_factura(String aignorar) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "SELECT * FROM factura";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    ArrayList<Factura> f = new ArrayList<Factura>();
	    ProductoRepository productoRepository = new ProductoRepository();
	    MesaRepository mesaRepository = new MesaRepository();
	    ArrayList<Producto> tproductos = productoRepository.Consultar_producto(); //sI HAY ERROR ES AQU�
	    ArrayList<Producto> x = null;
	    Pedido y = null;
	    Map<Producto, Integer> z = null;
	    String auxid = null;
	    String mesero=null, cajero=null, mesa=null, cliente=null,id=null, estado=null;
	    Mesa mesam = null;
	    while (rs.next()){
	    	id=rs.getString("Ven_id");
	    	estado = rs.getString("Ven_estado");
	    	if(estado.equalsIgnoreCase(aignorar)) continue;
	    	if(auxid==null) auxid=id;
	    	if(!auxid.equalsIgnoreCase(id)){
	    		if(y==null){
		    		  y = new Pedido();
		    		  y.cuerpo = x;
		    		  y.cantidades = z;
		    		  mesam = mesaRepository.Buscar_Mesa(mesa);
		    		  Factura fi = new Factura(auxid,mesero,cajero,mesam,y,cliente);
				      f.add(fi);
		    	}
		    	x = null;
		    	z = null;
		    	y = null;
		    	auxid=id;
	    	}
	    	if(auxid.equalsIgnoreCase(id)){ 
		    	  if(x==null){ x = new ArrayList<Producto>(); z = new HashMap<Producto, Integer>(); }
	 	    	  mesero = rs.getString("Me_id"); 
	 		      cajero = rs.getString("Caj_id"); 
	 		      mesa = rs.getString("Mesa_id");
	 		      cliente = rs.getString("Cli_id"); 
	 		      for(Producto producto: tproductos){ 
	 		    	  if(producto.getCodigo().equalsIgnoreCase(rs.getString("Pro_id"))){ x.add(producto); z.put(producto, Integer.parseInt(rs.getString("Dtv_cantidad"))); break;} 
	 		      } 
	 		      auxid = id; 
	 		      continue; 
		     } 

	    }
	    y = new Pedido();
	    y.cuerpo = x;
	    y.cantidades = z;
	    if(mesa == null) return f;
	    mesam = mesaRepository.Buscar_Mesa(mesa);
	    Factura fi = new Factura(auxid,mesero, cajero,mesam,y,cliente);
		f.add(fi);
	    st.close();
	    return f;
	}
	
}
