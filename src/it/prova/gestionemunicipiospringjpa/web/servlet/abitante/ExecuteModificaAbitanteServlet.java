package it.prova.gestionemunicipiospringjpa.web.servlet.abitante;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ExecuteModificaAbitanteServlet
 */
@WebServlet("/ExecuteModificaAbitanteServlet")
public class ExecuteModificaAbitanteServlet extends HttpServlet {
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
	public ExecuteModificaAbitanteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// controllo utente in sessione (va fatto in tutte le servlet)
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		Long idAbitante = Long.parseLong(request.getParameter("idAbitante"));
		String nome = request.getParameter("nomeInput");
		String cognome = request.getParameter("cognomeInput");
		String etaInput = request.getParameter("etaInput");
		String residenza = request.getParameter("residenzaInput");

		AbitanteDTO abitanteDTO = new AbitanteDTO(nome, cognome, etaInput, residenza);
		abitanteDTO.setId(idAbitante);

		List<String> erroreAbitante = abitanteDTO.errors();
		String codiceMunicipioDaCercare = request.getParameter("codiceInput");

		// test per vedere se il municipio non viene inserito
		String erroreInputMunicipioVuoto = abitanteDTO.erroreCodice(codiceMunicipioDaCercare);
		
		Municipio municipio = null;
		if (erroreInputMunicipioVuoto == null) {
			// test per vedere se esiste un municipio con il codice di input
			municipio = municipioService.cercaPerCodice(codiceMunicipioDaCercare);
			if (municipio == null) {
				erroreAbitante.add("Municipio con codice " + codiceMunicipioDaCercare
						+ " non presente sul sistema, inserire un codice valido");
			}
		}
		else {
			erroreAbitante.add(erroreInputMunicipioVuoto);
		}

		if (!erroreAbitante.isEmpty()) {
			request.setAttribute("abitanteAttr", abitanteDTO);
			request.setAttribute("abitanteErrors", erroreAbitante);
			request.setAttribute("listaMunicipiAttr", municipioService.listAllMunicipi());
			request.getRequestDispatcher("/abitante/modifica.jsp").forward(request, response);
			return;
		}

		Abitante abitante = AbitanteDTO.buildModelFromDto(abitanteDTO);
		abitante.setMunicipio(municipio);

		abitanteService.aggiorna(abitante);

		request.setAttribute("messaggioConferma", "Modifica avvenuto con successo");
		request.setAttribute("listaAbitantiAttributeName", abitanteService.listAllAbitanti());
		request.getRequestDispatcher("/abitante/result.jsp").forward(request, response);
	}

}
