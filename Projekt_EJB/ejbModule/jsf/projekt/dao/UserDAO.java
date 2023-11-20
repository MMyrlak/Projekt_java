package jsf.projekt.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jsf.projekt.User;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "projekt-workoutPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User person) {
		em.persist(person);
	}

	public User merge(User person) {
		return em.merge(person);
	}

	public void remove(User person) {
		em.remove(em.merge(person));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}


}
