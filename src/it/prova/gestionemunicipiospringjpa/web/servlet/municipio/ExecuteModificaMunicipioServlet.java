package it.prova.gestionemunicipiospringjpa.web.servlet.municipio;

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

import it.prova.gestionemunicipiospringjpa.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;


@WebServlet("/ExecuteModificaMunicipioServlet")
public class ExecuteModificaMunicipioServlet extends HttpServlet {
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
       
    public ExecuteModificaMunicipioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}


		String idMunicipioDaPagina = request.getParameter("idMunicipio");

		String descrizioneParameter = request.getParameter("descrizioneInput");
		String ubicazioneParameter = request.getParameter("ubicazioneInput");
		String codiceParameter = request.getParameter("codiceInput");
		
		MunicipioDTO municipioDTO=new MunicipioDTO(descrizioneParameter, codiceParameter, ubicazioneParameter);
		municipioDTO.setId(Long.parseLong(idMunicipioDaPagina));
		List<String> municipioErrors = municipioDTO.errors();
		if (!municipioErrors.isEmpty()) {
			request.setAttribute("municipioAttribute", municipioDTO);
			request.setAttribute("municipioErrors", municipioErrors);
			request.getRequestDispatcher("/municipio/modifica.jsp").forward(request, response);
			return;
		}
		
		Municipio municipioInstance = MunicipioDTO.buildModelFromDto(municipioDTO);
		municipioService.aggiorna(municipioInstance);
		
		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.setAttribute("listaMunicipi", municipioService.listAllMunicipi());
		request.getRequestDispatcher("/municipio/results.jsp").forward(request, response);
		
		
	}

}
