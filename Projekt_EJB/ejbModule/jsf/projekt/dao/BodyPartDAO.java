package jsf.projekt.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jsf.projekt.BodyPart;

@Stateless
public class BodyPartDAO {
	private final static String UNIT_NAME = "projekt-workoutPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public BodyPart find(Object id) {
		return em.find(BodyPart.class, id);
	}


}
