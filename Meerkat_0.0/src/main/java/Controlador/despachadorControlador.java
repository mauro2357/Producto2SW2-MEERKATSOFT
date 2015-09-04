package Controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio.geraciondefactura.Factura;
import Presentacion.generaciondefacturaFacade;

@WebServlet("/despachador")
public class despachadorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public despachadorControlador() {
        super();
    }
    
    generaciondefacturaFacade facturaFacade = new generaciondefacturaFacade();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("netro");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession s = request.getSession();
        String Puerta = null;
        Puerta = request.getParameter("entrar");
        System.out.println("puerta");
        System.out.println(Puerta);
        String pagina = null;
        if(Puerta.equalsIgnoreCase("Terminar"))
        {
        	s = request.getSession(false);
        	s.invalidate();
        	pagina = "index.jsp";
        }
        if(Puerta.equalsIgnoreCase("ir_despachador")){
        	System.out.println("entr�d");
        	ArrayList<Factura> x = null;
        	try {
				x = facturaFacade.main();
				System.out.println("x");
				System.out.println(x);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.setAttribute("pedidos_en_cola",x);
        	pagina = "/despachadores/cocina.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(pagina);
        rd.forward(request, response);
	}

}
