package Datos;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import Negocio.factura.Cajero;
import Negocio.inventario.Administrador;
import Negocio.pedido.Despachador;
import Negocio.pedido.Empleado;
import Negocio.pedido.Mesero;

public class EmpleadosRepository {
	
	
	
	public void Registrar_despachador (String id, String nombre, String apellido, String telefono) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "INSERT INTO `future`.`despachador` (`Des_id`, `Des_nombre`, `Des_apellido`, `Des_telefono`) VALUES ('"+id+"', '"+nombre+"', '"+apellido+"', '"+telefono+"');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    st.close();
	}
	
	public void Registrar_mesero (String id, String nombre, String apellido, String telefono) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "INSERT INTO `future`.`mesero` (`Me_id`, `Me_nombre`, `Me_apellido`, `Me_telefono`) VALUES ('"+id+"', '"+nombre+"', '"+apellido+"', '"+telefono+"');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    st.close();
	}

	public ArrayList<Empleado> Consultar_Empleados() throws Exception {
		ArrayList<Empleado> ans = new ArrayList<Empleado>();
		MeseroRepository mr = new MeseroRepository();
		CajeroRepository cr = new CajeroRepository();
		DespachadorRepository dr = new DespachadorRepository();
		AdministradorRepository ar = new AdministradorRepository();
		for(Mesero mesero: mr.Consultar_mesero()){
			ans.add(mesero);
		}
		for(Cajero cajero: cr.Consultar_cajeros()){
			ans.add(cajero);
		}
		for(Despachador despachador: dr.Consultar_despachador()){
			ans.add(despachador);
		}
		for(Administrador administrador: ar.Consultar_Administradores()){
			ans.add(administrador);
		}
		return ans;
	}

	public void Pagar(Empleado empleado, String id, String saldo) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = null;
		Calendar x = Calendar.getInstance();
    	String fecha = x.get(Calendar.YEAR)+"-"+Integer.toString(x.get(Calendar.MONTH)+1)+"-"+x.get(Calendar.DATE);
    	if(empleado instanceof Mesero){
    		System.out.println("ES MESERO");
			query = "INSERT INTO `future`.`pago_nomina` (`Mesero_id`, `Empleado_fecha`, `Empleado_valor`) VALUES ('"+id+"','"+fecha+"', '"+saldo+"');";
		}
		if(empleado instanceof Administrador){
			query = "INSERT INTO `future`.`pago_nomina` (`Administrador_id`, `Empleado_fecha`, `Empleado_valor`) VALUES ('"+id+"', '"+fecha+"', '"+saldo+"');";		
		}
		if(empleado instanceof Despachador){
			query = "INSERT INTO `future`.`pago_nomina` (`Despachador_id`, `Empleado_fecha`, `Empleado_valor`) VALUES ('"+id+"', '"+fecha+"', '"+saldo+"');";
		}
		if(empleado instanceof Cajero){
			query = "INSERT INTO `future`.`pago_nomina` (`Cajero_id`, `Empleado_fecha`, `Empleado_valor`) VALUES ('"+id+"', '"+fecha+"', '"+saldo+"');";
		}
		System.out.println("query: " + query);
		Statement st = con.createStatement();
	    st.executeUpdate(query);
	    st.close();
	}
}

