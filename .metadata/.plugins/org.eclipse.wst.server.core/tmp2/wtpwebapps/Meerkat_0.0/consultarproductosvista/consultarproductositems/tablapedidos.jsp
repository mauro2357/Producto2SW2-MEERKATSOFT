<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Presentacion.*" %>
<%@ page import="Negocio.tomaynotificacionpedidos.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" width="300">
		<tr><td>Producto</td><td>Cantidad</td><td align="right">Precio</td></tr>
	<%	ArrayList<Producto> productos_pedido = (ArrayList<Producto>) session.getAttribute("productos-pedido");
		ArrayList<String> visitados = new ArrayList<String>();
		double total=0;
		double totalindividual=0;
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
</body>
</html>