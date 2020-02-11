package it.prova.gestionemunicipiospringjpa.service.abitante;

import java.util.List;

import it.prova.gestionemunicipiospringjpa.model.Abitante;

public interface AbitanteService {
	
	public List<Abitante> listAllAbitanti();

	public Abitante caricaSingoloAbitante(Long id);

	public void aggiorna(Abitante abitanteInstance);

	public void inserisciNuovo(Abitante abitanteInstance);

	public void rimuovi(Abitante abitanteInstance);

	public List<Abitante> findByExample(Abitante example);
	
}
