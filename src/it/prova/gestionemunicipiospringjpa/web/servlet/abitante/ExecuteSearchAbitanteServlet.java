package it.prova.gestionemunicipiospringjpa.web.servlet.abitante;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.dto.AbitanteDTO;
import it.prova.gestionemunicipiospringjpa.model.Abitante;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;

/**
 * Servlet implementation class ExecuteSearchAbitanteServlet
 */
@WebServlet("/ExecuteSearchAbitanteServlet")
public class ExecuteSearchAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private AbitanteService abitanteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchAbitanteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//binding
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String etaInput = request.getParameter("etaInput");
		String residenzaInput = request.getParameter("residenzaInput");
		AbitanteDTO abitanteDTO = new AbitanteDTO();
		
		abitanteDTO.setNome(nomeInput);
		abitanteDTO.setCognome(cognomeInput);
		abitanteDTO.setEtaInput(etaInput);
		abitanteDTO.setResidenza(residenzaInput);
		
		List<String> abitanteErrors = abitanteDTO.errors();
		if(!abitanteErrors.isEmpty()) {
			request.setAttribute("abitanteAttr", abitanteDTO);
			request.setAttribute("abitanteErrors", abitanteErrors);
			request.setAttribute("idMunicipioSelezionato", request.getParameter("idMunicipio"));
			request.getRequestDispatcher("/abitante/search.jsp").forward(request, response);
			return;
		}
		
		// se arrivo qui significa che l'input va bene
		Abitante abitanteInstance = AbitanteDTO.buildModelFromDto(abitanteDTO);
		
		// ricavo il municipio e lo setto all'abitante
		Long idMunicipio = Long.parseLong(request.getParameter("idMunicipio"));
		Municipio municipio = idMunicipio == 0 ? null : municipioService.caricaSingoloMunicipio(idMunicipio);
		
		abitanteInstance.setMunicipio(municipio);

		List<Abitante> abitanti = abitanteService.findByExample(abitanteInstance);

		
		request.setAttribute("listaAbitantiAttributeName", abitanti);
		RequestDispatcher rd = request.getRequestDispatcher("/abitante/result.jsp");
		rd.forward(request, response);
	}

}
