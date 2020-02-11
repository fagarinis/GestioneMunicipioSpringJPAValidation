package it.prova.gestionemunicipiospringjpa.dao.abitante;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import it.prova.gestionemunicipiospringjpa.model.Abitante;

@Component
public class AbitanteDAOImpl implements AbitanteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> list() {
		return entityManager.createQuery("from Abitante").getResultList();
	}

	@Override
	public Abitante get(long id) {
		Abitante result = (Abitante) entityManager.find(Abitante.class, id);
		return result;
	}

	@Override
	public void update(Abitante abitanteInstance) {
		abitanteInstance = entityManager.merge(abitanteInstance);
	}

	@Override
	public void insert(Abitante abitanteInstance) {
		entityManager.persist(abitanteInstance);
	}

	@Override
	public void delete(Abitante abitanteInstance) {
		entityManager.remove(entityManager.merge(abitanteInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> findByExample(Abitante abitanteInstance)  {
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

		Criteria abitanteCriteria = session.createCriteria(Abitante.class);
		Example abitanteExample = Example.create(abitanteInstance).setPropertySelector(ps);
		if (abitanteInstance.getMunicipio() != null) {
			abitanteCriteria.createCriteria("municipio").add(Example.create(abitanteInstance.getMunicipio()));
		}

		abitanteCriteria.add(abitanteExample);
		return abitanteCriteria.list();

	}

}
