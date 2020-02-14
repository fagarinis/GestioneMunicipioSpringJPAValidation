package it.prova.gestionemunicipiospringjpa.dao.utente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.model.Ruolo;
import it.prova.gestionemunicipiospringjpa.model.StatoUtente;
import it.prova.gestionemunicipiospringjpa.model.Utente;

@Component
public class UtenteDAOImpl implements UtenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> list() {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Utente get(long id) {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente o) {
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Utente o) {
		entityManager.persist(o);
	}

	@Override
	public void delete(Utente o) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findByExample(Utente o) {
		Session session = (Session) entityManager.getDelegate();

		@SuppressWarnings("serial")
		PropertySelector ps = new PropertySelector() {
			@Override
			public boolean include(Object object, String propertyName, Type type) {
				if (object == null)
					return false;
				// String
				if (object instanceof String)
					return StringUtils.isNotBlank((String) object);
				// Number
				if (object instanceof Integer)
					return ((Integer) object) != 0;
				
				return true;
			}
		};

		Example utenteExample = Example.create(o).setPropertySelector(ps);
		Criteria criteria = session.createCriteria(Utente.class).add(utenteExample);
		return criteria.list();
	}

	@Override
	public Utente executeLogin(String username, String password) {
		Query query = entityManager
				.createQuery("select u FROM Utente u join fetch u.ruoli where u.username = :usernameParam and u.password= :passwordParam and u.stato=:statoUtenteParam");
		query.setParameter("usernameParam", username);
		query.setParameter("passwordParam", password);
		query.setParameter("statoUtenteParam", StatoUtente.ATTIVO);

		return query.getResultList().isEmpty() ? null : (Utente) query.getSingleResult();
	}

	@Override
	public Utente getEager(long id) {
		try {
		return entityManager.createQuery("from Utente u join fetch u.ruoli where u.id ="+id, Utente.class).getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public void updateUserWithRoles(Utente utenteModel, List<String> listaIdRuoli ) {
		utenteModel = entityManager.merge(utenteModel);
		for(String idRuolo: listaIdRuoli) {
			utenteModel.addRuolo(new Ruolo(Long.parseLong(idRuolo)));
		}
		
	}

}
