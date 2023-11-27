package jsf.projekt.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jsf.projekt.Workout;

@Stateless
public class WorkoutDAO {
	private final static String UNIT_NAME = "projekt-workoutPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create( Workout workout) {
		em.persist(workout);
	}

	public  Workout merge( Workout workout) {
		return em.merge(workout);
	}

	public void remove( Workout workout) {
		em.remove(em.merge(workout));
	}

	public  Workout find(Object id) {
		return em.find( Workout.class, id);
	}
	
	public List<Workout> getFullList() {
		List<Workout> list = null;

		Query query = em.createQuery("select w from workout w");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Workout> getList(Map<String, Object> searchParams) {
		List<Workout> list = null;

		// 1. Build query string with parameters
		String select = "select w ";
		String from = "from workout w ";
		String where = "";

		// search for surname
		String name_workout = (String) searchParams.get("name_workout");
		if (name_workout != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "w.name_workout like :name_workout ";
		}
		

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (name_workout != null) {
			query.setParameter("name_workout", name_workout+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
