package it.prova.gestionemunicipiospringjpa.web.servlet.municipio;

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

import it.prova.gestionemunicipiospringjpa.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;

@WebServlet("/ExecuteInsertMunicipioServlet")
public class ExecuteInsertMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteInsertMunicipioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding
		String descrizioneInput = request.getParameter("descrizione");
		String codiceInput = request.getParameter("codice");
		String ubicazioneInput = request.getParameter("ubicazione");
		MunicipioDTO municipioDTO = new MunicipioDTO(descrizioneInput, codiceInput, ubicazioneInput);
		
		//effettuoi la validazione dell'input e se non va bene rimando in pagina
		List<String> municipioErrors = municipioDTO.errors();
		if (!municipioErrors.isEmpty()) {
			request.setAttribute("municipioAttribute", municipioDTO);
			request.setAttribute("municipioErrors", municipioErrors);
			request.getRequestDispatcher("/municipio/insert.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui significa che va bene
		Municipio municipioInstance = MunicipioDTO.buildModelFromDto(municipioDTO);
		municipioService.inserisciNuovo(municipioInstance);
		
		//vado in pagina con ok
		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaMunicipi", municipioService.listAllMunicipi());
		request.getRequestDispatcher("/municipio/results.jsp").forward(request, response);
	}

}
