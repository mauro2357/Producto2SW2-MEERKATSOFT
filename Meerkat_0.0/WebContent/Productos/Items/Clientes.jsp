<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Negocio.cliente.*" %>
<%@ page import="java.util.*" %>
<option>Cliente</option>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Cliente> lista_clientes = (ArrayList<Cliente>) session.getAttribute("lista-clientes");
	if(lista_clientes==null) out.print("No hay clientes");
	else
	{
		for(Cliente cliente: lista_clientes){
			%><option><%out.print(cliente.id);%></option><%
		}
	}
%>