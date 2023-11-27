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

		Query query = em.createQuery("select w from Workout w");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Workout> getList(Map<String, Object> searchParams) {
		List<Workout> list = null;
		String select = "select w ";
		String from = "from Workout w ";
		String where = "";

		String nameWorkout = (String) searchParams.get("nameWorkout");
		if (nameWorkout != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "w.nameWorkout like :nameWorkout ";
		}
		
		Query query = em.createQuery(select + from + where);

		if (nameWorkout != null) {
			query.setParameter("nameWorkout", "%"+nameWorkout+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
