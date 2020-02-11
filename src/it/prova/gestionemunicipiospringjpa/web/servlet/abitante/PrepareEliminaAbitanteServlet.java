package it.prova.gestionemunicipiospringjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.model.Abitante;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;

/**
 * Servlet implementation class PrepareEliminaAbitanteServlet
 */
@WebServlet("/PrepareEliminaAbitanteServlet")
public class PrepareEliminaAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AbitanteService abitanteService;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareEliminaAbitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("idAbitante");
		String idMunicipio = request.getParameter("idMunicipio");
		Abitante abitanteDaCancellare = null;
		Municipio municipio = municipioService.caricaSingoloMunicipio(Long.parseLong(idMunicipio));
		
		abitanteDaCancellare = abitanteService.caricaSingoloAbitante(Long.parseLong(id));
		
		request.setAttribute("nomeInput", request.getAttribute("nomeInput"));
		request.setAttribute("cognomeInput", request.getAttribute("cognomeInput"));
		request.setAttribute("indirizzoInput", request.getAttribute("indirizzoInput"));
		request.setAttribute("idMunicipio", request.getAttribute("idMunicipio") );
		
		request.setAttribute("abitanteAttr", abitanteDaCancellare);
		
		RequestDispatcher rd = request.getRequestDispatcher("abitante/delete.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
