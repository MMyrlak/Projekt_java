package jsf.projekt;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the favorite database table.
 * 
 */
@Entity
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_favorite")
	private int idFavorite;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_users")
	private User user;

	//bi-directional many-to-one association to Workout
	@ManyToOne
	@JoinColumn(name="id_workout")
	private Workout workout;

	public Favorite() {
	}

	public int getIdFavorite() {
		return this.idFavorite;
	}

	public void setIdFavorite(int idFavorite) {
		this.idFavorite = idFavorite;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workout getWorkout() {
		return this.workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

}