package it.prova.gestionemunicipiospringjpa.dao.ruolo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.model.Ruolo;

@Component
public class RuoloDAOImpl implements RuoloDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruolo> list() {
		return entityManager.createQuery("from Ruolo").getResultList();
	}

	@Override
	public Ruolo get(long id) {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo o) {
		o = entityManager.merge(o);
		
	}

	@Override
	public void insert(Ruolo o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ruolo o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ruolo> findByExample(Ruolo o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Ruolo> findByListOfIds(List<String> idList) {
		if(idList == null || idList.size() == 0) {
			return null;
		}
		String cond = "";
		for(int i = 0; i < idList.size(); i++) {
			if(i == 0) {
				cond = " r.id ="+idList.get(i)+" ";
			}
			else {
				cond += " OR r.id = "+idList.get(i)+" ";
			}
		}
		//prima c'era join fetch r.utenti
		List<Ruolo> ruoliList = entityManager.createQuery("from Ruolo r  where "+cond, Ruolo.class).getResultList();
		Set<Ruolo> result = new HashSet<>();
		result.addAll(ruoliList);
		return result;
	}

}
