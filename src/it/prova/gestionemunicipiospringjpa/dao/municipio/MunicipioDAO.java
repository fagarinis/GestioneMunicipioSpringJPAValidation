package it.prova.gestionemunicipiospringjpa.dao.municipio;

import java.util.List;

import it.prova.gestionemunicipiospringjpa.dao.IBaseDAO;
import it.prova.gestionemunicipiospringjpa.model.Municipio;

public interface MunicipioDAO extends IBaseDAO<Municipio> {

	public List<Municipio> findAllByDescrizioneILike(String term);
}
