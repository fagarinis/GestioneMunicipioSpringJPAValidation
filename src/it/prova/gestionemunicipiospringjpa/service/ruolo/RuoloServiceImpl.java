package it.prova.gestionemunicipiospringjpa.service.ruolo;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionemunicipiospringjpa.dao.ruolo.RuoloDAO;
import it.prova.gestionemunicipiospringjpa.model.Ruolo;

@Component
public class RuoloServiceImpl implements RuoloService {
	
	@Autowired
	private RuoloDAO ruoloDAO;

	@Transactional(readOnly=true)
	@Override
	public List<Ruolo> listAllRuoli() {
		return ruoloDAO.list();
	}

	@Override
	public Ruolo caricaSingoloRuolo(Long id) {
		return ruoloDAO.get(id);
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		ruoloDAO.update(ruoloInstance);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloDAO.insert(ruoloInstance);
	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) {
		ruoloDAO.delete(ruoloInstance);
	}

	@Override
	public List<Ruolo> findByExample(Ruolo example) {
		return ruoloDAO.findByExample(example);
	}

	@Transactional(readOnly=true)
	@Override
	public Set<Ruolo> trovaDaListaId(List<String> listaId) {
		return ruoloDAO.findByListOfIds(listaId);
	}

}
