package jsf.projekt;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the workout database table.
 * 
 */
@Entity
@NamedQuery(name="Workout.findAll", query="SELECT w FROM Workout w")
public class Workout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_workout")
	private int idWorkout;

	@Lob
	private String move;

	@Column(name="name_workout")
	private String nameWorkout;

	private String photo;

	@Lob
	private String position;

	private String video;

	//bi-directional many-to-one association to Allocation
	@OneToMany(mappedBy="workout")
	private List<Allocation> allocations;

	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy="workout")
	private List<Favorite> favorites;

	public Workout() {
	}

	public int getIdWorkout() {
		return this.idWorkout;
	}

	public void setIdWorkout(int idWorkout) {
		this.idWorkout = idWorkout;
	}

	public String getMove() {
		return this.move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public String getNameWorkout() {
		return this.nameWorkout;
	}

	public void setNameWorkout(String nameWorkout) {
		this.nameWorkout = nameWorkout;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Allocation> getAllocations() {
		return this.allocations;
	}

	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	public Allocation addAllocation(Allocation allocation) {
		getAllocations().add(allocation);
		allocation.setWorkout(this);

		return allocation;
	}

	public Allocation removeAllocation(Allocation allocation) {
		getAllocations().remove(allocation);
		allocation.setWorkout(null);

		return allocation;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setWorkout(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setWorkout(null);

		return favorite;
	}

}