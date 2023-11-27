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
import jsf.projekt.dao.WorkoutDAO;

@Named
@RequestScoped
public class WorkoutListBB {
	private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name_workout;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	WorkoutDAO workoutDAO;
		
	public String getNameWorkout() {
		return name_workout;
	}

	public void setNameWorkout(String name_workout) {
		this.name_workout = name_workout;
	}

	public List<Workout> getFullList(){
		return workoutDAO.getFullList();
	}

	public List<Workout> getList(){
		List<Workout> list = null;
		Map<String,Object> searchParams = new HashMap<String, Object>();
		if (name_workout != null && name_workout.length() > 0){
			searchParams.put("name_workout", name_workout);
		}
		list = workoutDAO.getList(searchParams);
		return list;
	}
}
