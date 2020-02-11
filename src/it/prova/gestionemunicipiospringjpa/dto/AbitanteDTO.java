package it.prova.gestionemunicipiospringjpa.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
		List<String>result = new ArrayList<String>();
		if(this.nome.isBlank()) {
			result.add("Il campo nome non può essere vuoto");
		}
		if(this.cognome.isBlank()) {
			result.add("Il campo cognome non può essere vuoto");
		}
			
		if(!erroreEta().isEmpty()) {
			result.add(erroreEta().get(0));
		}
			
		if(this.residenza.isBlank()) {
			result.add("Il campo residenza non può essere vuoto");
		}
			
		return null;
	}
	
	public List<String> erroreEta() {
		List<String>result = new ArrayList<String>();
		if(StringUtils.isNumeric(this.etaInput)) {
			this.eta=Integer.parseInt(etaInput);
			if(eta<1) {
				result.add("Il campo età deve essere maggiore di 0");
			}
		}
		else {
			result.add("Il campo età non è un numero");
		}
		return result;
	}
	
	public static Abitante buildModelFromDto(AbitanteDTO abitanteDTO) {
		return null;
		
	}
	
}
