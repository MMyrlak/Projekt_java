package jsf.projekt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import jsf.projekt.Workout;
import jsf.projekt.dao.AllocationDAO;
import jsf.projekt.dao.WorkoutDAO;

@Named
@RequestScoped
public class WorkoutListBB {
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String nameWorkout;
	private String nameBodyPart;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	WorkoutDAO workoutDAO;
	@EJB
	AllocationDAO allocationDAO;

	public String getNameWorkout() {
		return nameWorkout;
	}

	public void setNameWorkout(String nameWorkout) {
		this.nameWorkout = nameWorkout;
	}

	public String getNameBodyPart() {
		return nameBodyPart;
	}

	public void setNameBodyPart(String nameBodyPart) {
		this.nameBodyPart = nameBodyPart;
	}
	public List<Allocation> getFullList(){
		return allocationDAO.getFullList();
	}

	public List<Allocation> getList(){
		List<Allocation> list = null;
		Map<String,Object> searchParams = new HashMap<String, Object>();
		if (nameWorkout != null && nameWorkout.length() > 0){
			searchParams.put("nameWorkout", nameWorkout);
		}
		if (nameBodyPart != null && nameBodyPart.length() > 0){
			searchParams.put("nameBodyPart", nameBodyPart);
		}
		list = allocationDAO.getList(searchParams);
		return list;
	}


}
