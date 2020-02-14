package it.prova.gestionemunicipiospringjpa.service.ruolo;

import java.util.List;
import java.util.Set;

import it.prova.gestionemunicipiospringjpa.model.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAllRuoli() ;

	public Ruolo caricaSingoloRuolo(Long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Ruolo ruoloInstance);

	public List<Ruolo> findByExample(Ruolo example);
	
	public Set<Ruolo> trovaDaListaId(List<String> listaId);

}
