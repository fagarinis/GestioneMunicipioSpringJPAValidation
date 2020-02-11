package it.prova.gestionemunicipiospringjpa.dao.utente;

import it.prova.gestionemunicipiospringjpa.dao.IBaseDAO;
import it.prova.gestionemunicipiospringjpa.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {

	public Utente executeLogin(String username,String password);
}
