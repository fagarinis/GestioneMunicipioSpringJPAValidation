package it.prova.gestionemunicipiospringjpa.service.municipio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionemunicipiospringjpa.dao.municipio.MunicipioDAO;
import it.prova.gestionemunicipiospringjpa.model.Municipio;

@Component
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;

	@Transactional(readOnly=true)
	public List<Municipio> listAllMunicipi() {
			return municipioDAO.list();
	}

	@Transactional(readOnly=true)
	public Municipio caricaSingoloMunicipio(Long id) {
			return municipioDAO.get(id);
	}

	@Transactional
	public void aggiorna(Municipio municipioInstance) {
			municipioDAO.update(municipioInstance);
	}

	@Transactional
	public void inserisciNuovo(Municipio municipioInstance) {
			municipioDAO.insert(municipioInstance);
	}

	@Transactional
	public void rimuovi(Municipio municipioInstance) {
			municipioDAO.delete(municipioInstance);
	}

	@Transactional(readOnly=true)
	public List<Municipio> findByExample(Municipio example) {
			return municipioDAO.findByExample(example);
	}

	@Transactional(readOnly=true)
	public List<Municipio> cercaByDescrizioneILike(String term) {
		return municipioDAO.findAllByDescrizioneILike(term);
	}

}
