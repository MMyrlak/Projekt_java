package jsf.projekt.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jsf.projekt.Favorite;

@Stateless
public class FavoriteDAO {
	private final static String UNIT_NAME = "projekt-workoutPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Favorite fav) {
		em.persist(fav);
	}

	public Favorite merge(Favorite fav) {
		return em.merge(fav);
	}

	public void remove(Favorite fav) {
		em.remove(em.merge(fav));
	}

	public Favorite find(Object id) {
		return em.find(Favorite.class, id);
	}


}
