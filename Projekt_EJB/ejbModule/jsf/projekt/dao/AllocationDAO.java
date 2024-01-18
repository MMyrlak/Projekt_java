package jsf.projekt.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jsf.projekt.Allocation;

@Stateless
public class AllocationDAO {
	private final static String UNIT_NAME = "projekt-workoutPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Allocation allocation) {
		em.persist(allocation);
	}

	public Allocation merge(Allocation allocation) {
		return em.merge(allocation);
	}

	public void remove(Allocation allocation) {
		em.remove(em.merge(allocation));
	}

	public Allocation find(Object id) {
		return em.find(Allocation.class, id);
	}
	
	public List<Allocation> getFullList() {
		List<Allocation> list = null;

		Query query = em.createQuery("select a from Allocation a");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Allocation> getList(Map<String, Object> searchParams) {
		List<Allocation> list = null;
		String select = "select a ";
		String from = "from Allocation a ";
		String where = "";
		String name = (String) searchParams.get("nameBodyPart");
		String nameWorkout = (String) searchParams.get("nameWorkout");
		if (nameWorkout != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.workout.nameWorkout like :nameWorkout ";
		} else if(name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.bodyPart.name like :name";
		}
		
		
		Query query = em.createQuery(select + from + where);

		if (nameWorkout != null) {
			query.setParameter("nameWorkout", "%"+nameWorkout+"%");
		} else if (name !=null) {
			query.setParameter("name", "%"+name+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


}
