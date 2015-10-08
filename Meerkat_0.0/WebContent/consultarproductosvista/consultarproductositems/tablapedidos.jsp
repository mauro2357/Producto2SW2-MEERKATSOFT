
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Presentacion.*" %>
<%@ page import="Negocio.pedido.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	
	<div style="max-width: 100%; height: auto;">
		
		<table border="0" cellpadding="0" cellspacing="0" width="300" style="background: rgba(300, 300, 300, 0.5);">
			<tr align="center"><td>Pedido:</td></tr>
			<tr><td>Producto</td><td>Cantidad</td><td align="right">Precio</td></tr>
		<%	
			@SuppressWarnings("unchecked")
			ArrayList<Producto> productos_pedido = (ArrayList<Producto>) session.getAttribute("productos-pedido");
			if(productos_pedido==null) productos_pedido = new ArrayList<Producto>();
			ArrayList<String> visitados = new ArrayList<String>();
			int total=0;
			int totalindividual=0;
			for(Producto producto: productos_pedido){
				if(visitados.contains(producto.getCodigo())) continue;
				int aux=0;
				for(Producto auxproducto: productos_pedido){
					if(producto.getCodigo().equalsIgnoreCase(auxproducto.getCodigo())){
						visitados.add(auxproducto.getCodigo());
						aux++;
					}
					totalindividual = producto.getValor() * aux;
				}
				total += producto.getValor() * aux;
				%>
			<tr><td><%out.print(producto.getNombre());%></td><td><%out.print(aux);%></td><td align="right"><%out.print(totalindividual);%></td></tr>
			<%}%>
			<tr><td></td><td>Total:</td><td align="right"><%out.print(total); %></td></tr>
		</table>
	</div>
		<%  //Map<Pedido,String> h = meseroControllador.consultarmeserosFacade.mesero.coladepedidos;
			//if(h.size()>0){
		 	//for (Map.Entry<Pedido, String> entry : h.entrySet())
				//{
				  //  out.println(entry.getKey().getCuerpo() + "/" + entry.getValue());
			//	}
			//}
			//else{out.println("No hay pedidos en cola");}
		%>
</body>
</html>