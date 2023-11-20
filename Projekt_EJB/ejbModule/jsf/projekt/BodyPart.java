package jsf.projekt;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the body_parts database table.
 * 
 */
@Entity
@Table(name="body_parts")
@NamedQuery(name="BodyPart.findAll", query="SELECT b FROM BodyPart b")
public class BodyPart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_body_parts")
	private int idBodyParts;

	private String name;

	//bi-directional many-to-one association to Allocation
	@OneToMany(mappedBy="bodyPart")
	private List<Allocation> allocations;

	public BodyPart() {
	}

	public int getIdBodyParts() {
		return this.idBodyParts;
	}

	public void setIdBodyParts(int idBodyParts) {
		this.idBodyParts = idBodyParts;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Allocation> getAllocations() {
		return this.allocations;
	}

	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	public Allocation addAllocation(Allocation allocation) {
		getAllocations().add(allocation);
		allocation.setBodyPart(this);

		return allocation;
	}

	public Allocation removeAllocation(Allocation allocation) {
		getAllocations().remove(allocation);
		allocation.setBodyPart(null);

		return allocation;
	}

}