package jsf.projekt.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

	public void Allocation(Allocation allocation) {
		em.remove(em.merge(allocation));
	}

	public Allocation find(Object id) {
		return em.find(Allocation.class, id);
	}


}
