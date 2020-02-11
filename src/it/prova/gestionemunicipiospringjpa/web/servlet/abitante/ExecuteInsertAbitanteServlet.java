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
 * Servlet implementation class ExecuteInsertAbitanteServlet
 */
@WebServlet("/ExecuteInsertAbitanteServlet")
public class ExecuteInsertAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Autowired
	private AbitanteService abitanteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertAbitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String etaInput = request.getParameter("etaInput");
		String residenzaInput = request.getParameter("residenzaInput");
		Long idMunicipio = Long.valueOf(request.getParameter("idMunicipio"));
		AbitanteDTO abitanteDTO = new AbitanteDTO(nomeInput,cognomeInput,etaInput,residenzaInput);
		
		Municipio municipioDaSettare = municipioService.caricaSingoloMunicipio(idMunicipio);
		abitanteDTO.setMunicipio(municipioDaSettare);
		
		//effettuoi la validazione dell'input e se non va bene rimando in pagina
				List<String> abitanteErrors = abitanteDTO.errors();
				if (!abitanteErrors.isEmpty()) {
					request.setAttribute("abitanteAttribute", abitanteDTO);
					request.setAttribute("abitanteErrors", abitanteErrors);
					List<Municipio> listaMunicipi = municipioService.listAllMunicipi();
					request.setAttribute("listaMunicipiAttributeName", listaMunicipi);
					request.getRequestDispatcher("/abitante/inserisciNuovo.jsp").forward(request, response);
					return;
		
	}
				//se arrivo qui significa che va bene
				Abitante abitanteInstance = AbitanteDTO.buildModelFromDto(abitanteDTO);
				abitanteService.inserisciNuovo(abitanteInstance);
				
				//vado in pagina con ok
				request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
				request.setAttribute("listaAbitantiAttributeName", abitanteService.listAllAbitanti());
				request.getRequestDispatcher("/abitante/result.jsp").forward(request, response);

}
	
	
}
