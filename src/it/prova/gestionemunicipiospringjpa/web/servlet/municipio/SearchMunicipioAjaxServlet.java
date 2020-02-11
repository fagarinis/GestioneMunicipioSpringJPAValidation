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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;

@WebServlet("/SearchMunicipioAjaxServlet")
public class SearchMunicipioAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchMunicipioAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");

		List<Municipio> listaMunicipiByTerm = municipioService.cercaByDescrizioneILike(term);
		String json = buildJsonResponse(listaMunicipiByTerm);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
	}

	private String buildJsonResponse(List<Municipio> listaMunicipi) {
		JsonArray ja = new JsonArray();

		for (Municipio municipioElement : listaMunicipi) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", municipioElement.getId());
			jo.addProperty("label", municipioElement.getDescrizione());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
