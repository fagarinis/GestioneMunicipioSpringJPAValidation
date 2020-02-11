package it.prova.gestionemunicipiospringjpa.service.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionemunicipiospringjpa.dao.utente.UtenteDAO;
import it.prova.gestionemunicipiospringjpa.model.Utente;

@Component
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteDAO utenteDAO;

	@Transactional(readOnly=true)
	public List<Utente> listAllUtenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=true)
	public Utente caricaSingoloUtente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteDAO.insert(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly=true)
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=true)
	public Utente eseguiAccesso(String username, String password) {
		return utenteDAO.executeLogin(username, password);
	}

}
