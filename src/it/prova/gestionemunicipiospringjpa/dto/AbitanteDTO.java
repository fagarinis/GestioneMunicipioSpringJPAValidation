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
		Integer result = null;
		try {
			result = (Integer.parseInt(etaInput));
		} catch (Exception e) {
		}
		return result;
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

	public List<String> searchErrors() {
		List<String> result = new ArrayList<String>();

		if (!StringUtils.isNumeric(etaInput)) {
			result.add("Input eta' non valido");
		} else if (!StringUtils.isBlank(etaInput)) {
			this.eta = Integer.parseInt(etaInput);
			if (this.getEta() < 0) {
				result.add("il campo eta' deve essere maggiore o uguale a 0");
			}
		}
		return result;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("Il campo nome non pu� essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("Il campo cognome non pu� essere vuoto");
		}

		if (!erroreEta().isEmpty()) {
			result.add(erroreEta().get(0));
		}

		if (StringUtils.isBlank(this.residenza)) {
			result.add("Il campo residenza non pu� essere vuoto");
		}

		return result;
	}

	public List<String> erroreEta() {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isBlank(etaInput) && StringUtils.isNumeric(etaInput)) {
			this.eta = Integer.parseInt(etaInput);
			if (eta < 0) {
				result.add("Input eta' non valido");
			}
		} else {
			result.add("Input eta' non valido");
		}
		return result;
	}

	public static Abitante buildModelFromDto(AbitanteDTO abitanteDTO) {
		Abitante a = new Abitante();
		a.setId(abitanteDTO.getId());
		a.setNome(abitanteDTO.getNome());
		a.setCognome(abitanteDTO.getCognome());
		a.setEta(abitanteDTO.getEta());
		a.setResidenza(abitanteDTO.getResidenza());
		a.setMunicipio(abitanteDTO.getMunicipio());
		return a;
	}

}
