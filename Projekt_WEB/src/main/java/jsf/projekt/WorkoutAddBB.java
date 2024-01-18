package jsf.projekt;

import java.io.IOException;
import java.io.Serializable;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jsf.projekt.dao.AllocationDAO;
import jsf.projekt.dao.BodyPartDAO;
import jsf.projekt.dao.WorkoutDAO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Named
@ViewScoped
public class WorkoutAddBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_WORKOUT_LIST = "/Public/workoutList.xhtml?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private Allocation allocation = new Allocation();
	private String nameWorkout;
	private String photo;
	private String position;
	private String video;
	private String move;
	private int bodyPart;
	private UploadedFile file;
	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	String realPath = ctx.getRealPath("/");
	private String destination = ctx.getRealPath("/")+"\\resource\\img\\";

	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public int getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(int bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public String getNameWorkout() {
		return nameWorkout;
	}

	public void setNameWorkout(String nameWorkout) {
		this.nameWorkout = nameWorkout;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@EJB
	WorkoutDAO workoutDAO;
	@EJB
	AllocationDAO allocationDAO;
	@EJB
	BodyPartDAO bodyPartDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Allocation getAllocation() {
		return allocation;
	}

	public void upload() {
		try {
			this.photo = file.getFileName();
			copyFile(file.getFileName(), file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String saveData() {
		try {
			System.out.println(photo);
			Workout workout = new Workout();
			workout.setMove(move);
			workout.setNameWorkout(nameWorkout);
			workout.setPhoto(photo);
			workout.setPosition(position);
			workout.setVideo(video);
			workout = workoutDAO.create(workout);
			Allocation al = new Allocation();
			al.setBodyPart(bodyPartDAO.find(bodyPart));
			al.setWorkout(workout);
			allocationDAO.create(al);
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_WORKOUT_LIST;
	}
}