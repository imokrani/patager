package com.glaizer.RaliProject.action;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.international.StatusMessages;

@Stateless
@Name("LogoutBean")
public class LogoutBean implements LogoutInterface {
    @In StatusMessages statusMessages;

    public void doLogout() {
    	org.jboss.seam.web.Session.instance().invalidate();
    }
    
    public String getUserName() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
    	return session.getAttribute("session_prenom").toString();
    }

}
