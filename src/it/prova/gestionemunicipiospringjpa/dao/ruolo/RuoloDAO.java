package it.prova.gestionemunicipiospringjpa.dao.ruolo;

import java.util.List;
import java.util.Set;

import it.prova.gestionemunicipiospringjpa.dao.IBaseDAO;
import it.prova.gestionemunicipiospringjpa.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Set<Ruolo> findByListOfIds(List<String> idList);

}
