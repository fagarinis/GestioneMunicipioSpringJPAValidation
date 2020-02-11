package it.prova.gestionemunicipiospringjpa.dto;

import java.util.List;

import it.prova.gestionemunicipiospringjpa.model.Abitante;
import it.prova.gestionemunicipiospringjpa.model.Municipio;

public class AbitanteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private Integer eta;
	private String etaInput;
	private String residenza;

	private Municipio municipio;

	public AbitanteDTO() {

	}

	public AbitanteDTO(String nome, String cognome, String etaInput, String residenza) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.etaInput = etaInput;
		this.residenza = residenza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEtaInput() {
		return etaInput;
	}

	public void setEtaInput(String etaInput) {
		this.etaInput = etaInput;
	}

	public Integer getEta() {
		return eta;
	}
	
	public void setEta(Integer eta) {
		this.eta = eta;
	}


	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public List<String> errors(){
		return null;
	}
	
	public static Abitante buildModelFromDto(AbitanteDTO abitanteDTO) {
		return null;
		
	}
	
}
