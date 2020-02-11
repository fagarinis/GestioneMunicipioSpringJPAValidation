package it.prova.gestionemunicipiospringjpa.dao.municipio;

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

@Component
public class MunicipioDAOImpl implements MunicipioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> list() {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Municipio").getResultList();
	}

	@Override
	public Municipio get(long id) {
		Municipio result = (Municipio) entityManager.find(Municipio.class, id);
		return result;
	}

	@Override
	public void update(Municipio municipioInstance) {
		municipioInstance = entityManager.merge(municipioInstance);
	}

	@Override
	public void insert(Municipio municipioInstance) {
		entityManager.persist(municipioInstance);
	}

	@Override
	public void delete(Municipio municipioInstance) {
		entityManager.remove(entityManager.merge(municipioInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> findByExample(Municipio municipioInstance) {
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

		Example municipioExample = Example.create(municipioInstance).setPropertySelector(ps);
		Criteria criteria = session.createCriteria(Municipio.class).add(municipioExample);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> findAllByDescrizioneILike(String term) {
		term = term != null ? term.toLowerCase() : "";
		Query query = entityManager
				.createQuery("select u FROM Municipio u where  lower(u.descrizione) like :termInput ");
		query.setParameter("termInput", '%' + term + '%');

		return query.getResultList();
	}

}
