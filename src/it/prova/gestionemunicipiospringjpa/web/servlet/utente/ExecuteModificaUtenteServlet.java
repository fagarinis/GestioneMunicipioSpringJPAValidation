package it.prova.gestionemunicipiospringjpa.web.servlet.utente;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.dto.UtenteDTO;
import it.prova.gestionemunicipiospringjpa.model.StatoUtente;
import it.prova.gestionemunicipiospringjpa.model.Utente;
import it.prova.gestionemunicipiospringjpa.service.ruolo.RuoloService;
import it.prova.gestionemunicipiospringjpa.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteModificaUtenteServlet
 */
@WebServlet("/admin/ExecuteModificaUtenteServlet")
public class ExecuteModificaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteModificaUtenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding
		String idInput = request.getParameter("idInput");
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String usernameInput = request.getParameter("usernameInput");
		String passwordInput = request.getParameter("passwordInput");
		String[] idRuoliInputChecked = request.getParameterValues("ruoloInput");
		String statoInput = request.getParameter("statoInput");
		
		

		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setId(Long.parseLong(idInput));
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setPassword(passwordInput);
		utenteDTO.setDataRegistrazione(new Date());
		utenteDTO.setIdRuoli(idRuoliInputChecked);
		utenteDTO.setStato(StatoUtente.valueOf(statoInput));
		

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> utenteErrors = utenteDTO.errors();
		if (!utenteErrors.isEmpty()) {
			
			utenteDTO.setRuoli(utenteService.caricaSingoloUtenteEager(utenteDTO.getId()).getRuoli());
			request.setAttribute("utenteAttr", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.setAttribute("listaRuoliCheckedAttr", utenteDTO.getIdRuoli());
			request.setAttribute("statiListAttr", StatoUtente.values());
			request.setAttribute("ruoliListAttr", ruoloService.listAllRuoli());
			request.getRequestDispatcher("/admin/modifica.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		utenteService.aggiornaUtenteConRuoli(UtenteDTO.buildModelFromDto(utenteDTO), utenteDTO.getIdRuoli());
		
//		Utente utenteInstance = UtenteDTO.buildModelFromDto(utenteDTO);
//		utenteInstance.setRuoli(ruoloService.trovaDaListaId(utenteDTO.getIdRuoli()));
//		utenteService.aggiorna(utenteInstance);

		// vado in pagina con OK
		request.setAttribute("listaUtentiAttr", utenteService.listAllUtenti());
		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.getRequestDispatcher("/admin/result.jsp").forward(request, response);
	}

}
