package jsf.projekt.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;
		String select = "select u ";
		String from = "from User u ";
		String where = "";

		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :login ";
		}
		String password = (String) searchParams.get("password");
		if (password != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.password like :password ";
		}
		
		Query query = em.createQuery(select + from + where);

		if (login != null) {
			query.setParameter("login", "%"+login+"%");
		}
		if (password != null) {
			query.setParameter("password", "%"+password+"%");
		}

		try {
			list = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
