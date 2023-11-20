package jsf.projekt;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the allocation database table.
 * 
 */
@Entity
@NamedQuery(name="Allocation.findAll", query="SELECT a FROM Allocation a")
public class Allocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_allocation")
	private int idAllocation;

	//bi-directional many-to-one association to BodyPart
	@ManyToOne
	@JoinColumn(name="id_body_parts")
	private BodyPart bodyPart;

	//bi-directional many-to-one association to Workout
	@ManyToOne
	@JoinColumn(name="id_workout")
	private Workout workout;

	public Allocation() {
	}

	public int getIdAllocation() {
		return this.idAllocation;
	}

	public void setIdAllocation(int idAllocation) {
		this.idAllocation = idAllocation;
	}

	public BodyPart getBodyPart() {
		return this.bodyPart;
	}

	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

	public Workout getWorkout() {
		return this.workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

}