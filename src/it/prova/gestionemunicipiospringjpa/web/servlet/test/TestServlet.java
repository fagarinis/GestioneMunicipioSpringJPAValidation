package it.prova.gestionemunicipiospringjpa.web.servlet.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.model.Abitante;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.abitante.AbitanteService;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AbitanteService abitanteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public TestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipoTest = request.getParameter("tipoTest");

		String message = executeTest(tipoTest, request);

		response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n" + message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String executeTest(String tipoTest, HttpServletRequest request) {
		String result = "no result";
		switch (tipoTest) {
		case "insertAbitante":
			// URL di esempio:
			// /TestServlet?tipoTest=insertAbitante&idMunicipio=22&nomeAbitante=Gigi&cognomeAbitante=Ballo&residenzaAbitante=Milano&etaAbitante=4
			String idMunicipio = request.getParameter("idMunicipio");
			Long idMun = StringUtils.isNumeric(idMunicipio) ? Long.parseLong(idMunicipio) : 1L;
			String nomeAbitante = request.getParameter("nomeAbitante");
			String cognomeAbitante = request.getParameter("cognomeAbitante");
			String residenzaAbitante = request.getParameter("residenzaAbitante");
			String etaAbitante = request.getParameter("etaAbitante");
			int eta = StringUtils.isNumeric(etaAbitante) ? Integer.parseInt(etaAbitante) : 0;
			Abitante abitante = new Abitante(nomeAbitante, cognomeAbitante, eta, residenzaAbitante);
			abitante.setMunicipio(new Municipio(idMun));

			try {
				abitanteService.inserisciNuovo(abitante);
				result = "inserimento abitante ok con id: " + abitante.getId();
			} catch (Exception e) {
				e.printStackTrace();
				result = e.getMessage();
			}
			break;

		case "cambiaMunicipioAdAbitante":
			// URL di esempio:
			// /TestServlet?tipoTest=cambiaMunicipioAdAbitante&idAbitante=7&idMunicipioNew=27
			String idAbitante = request.getParameter("idAbitante");
			Long idAbi = StringUtils.isNumeric(idAbitante) ? Long.parseLong(idAbitante) : 1L;
			String idMunicipioNew = request.getParameter("idMunicipioNew");
			Long idMunNew = StringUtils.isNumeric(idMunicipioNew) ? Long.parseLong(idMunicipioNew) : 1L;
			

			try {
				Abitante abitanteDaModificare = abitanteService.caricaSingoloAbitante(idAbi);
				abitanteDaModificare.setMunicipio(new Municipio(idMunNew));
				abitanteService.aggiorna(abitanteDaModificare);
				result = "aggiornamento abitante ok con id: " + abitanteDaModificare.getId();
			} catch (Exception e) {
				e.printStackTrace();
				result = e.getMessage();
			}
			break;
		}

		return result;
	}

}
