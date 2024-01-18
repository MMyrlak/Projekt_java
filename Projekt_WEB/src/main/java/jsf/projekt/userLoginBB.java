package jsf.projekt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;

import jsf.projekt.dao.UserDAO;

@Named
@RequestScoped
public class userLoginBB {
	private static final String PAGE_MAIN = "/Public/workoutList.xhtml?faces-redirect=true";
	private static final String PAGE_LOGIN = "/Public/loginPage.xthml";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private String login;
    private String password;
    private String mail;
    
    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Inject
	UserDAO userDAO;
    
    public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String,Object> searchParams = new HashMap<String, Object>();
		if (login != null && login.length() > 0){
			searchParams.put("login", login);
			searchParams.put("password", password);
		}
		List<User> userList = userDAO.getList(searchParams);
		User user = userList.get(0);
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		RemoteClient<User> client = new RemoteClient<User>();
		client.setDetails(user);
		client.getRoles().add(user.getRole());
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
		return PAGE_MAIN;
	}

	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}
	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (login == null && mail == null && password == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Błąd rejestracji", null));
			return PAGE_STAY_AT_THE_SAME;
		}
//		User user = new User();
//		user.setLogin(login);
//		user.setMail(mail);
//		user.setPassword(password);
//		user.setRole("user");
//		
//		userDAO.create(user);
		System.out.println("Dodano użytkownika");
		return PAGE_LOGIN;
	}
		
}
