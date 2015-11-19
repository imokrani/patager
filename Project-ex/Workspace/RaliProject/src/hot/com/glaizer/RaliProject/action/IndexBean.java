package com.glaizer.RaliProject.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.international.StatusMessages;

@Stateless
@Name("IndexBean")
public class IndexBean implements IndexInterface {
	@PersistenceContext private EntityManager em;
	@In StatusMessages statusMessages;
	private List<String> profils;
	
	public IndexBean() {
		profils = new ArrayList<String>();
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectItem> getProfils() {
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem("Profils..."));
		Iterator itr = profils.iterator();
		while (itr.hasNext())
			items.add(new SelectItem((String)itr.next()));
		return items;
	}

    @SuppressWarnings("unchecked")
	public String authentification() {    	
    	String toReturn;    	
    	HtmlInputText login = (HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("formAuth:login");
    	HtmlInputSecret pwd = (HtmlInputSecret) FacesContext.getCurrentInstance().getViewRoot().findComponent("formAuth:password");
    	Boolean typeUser = isGgpSysteme(login.getValue().toString(), pwd.getValue().toString());
    	String req;
    	Object[] res = null;
    	//1° Si erreur d'identification
    	if(typeUser == null)
    		toReturn = "Index";
    	//2° Si un GgpSysteme
    	else if (typeUser) {    		
    		req = "select g.idggps, g.prenomggps from Ggpsysteme g where g.emailggps=:login and g.mdpggps=:pwd";
    		res = (Object[]) em.createQuery(req).setParameter("login", login.getValue()).setParameter("pwd", pwd.getValue()).getSingleResult();
    		if (res.length == 0)
    			toReturn = "Index";    		
    		String sousReq = "select p.lblprofil from Ggpsysteme g JOIN g.profils p where g.idggps=:idress";
    		profils = em.createQuery(sousReq).setParameter("idress", res[0]).getResultList();
    		if (profils.size()==1) {
    			toReturn = profils.get(0);
    		}else if (profils.size()>1){
    			toReturn = "secondeAuthentification";
    		} else {
        		// Erreur d'authentification
    			toReturn = "Index";        			
    		}    		
    	} 
    	//3° Si une ressource client
    	else {
        	req = "select r.idress, r.prenomrh from Resshum r where r.emailrh=:login and r.pwdrh=:pwd";
        	res = (Object[]) em.createQuery(req).setParameter("login", login.getValue()).setParameter("pwd", pwd.getValue()).getSingleResult();
    		if (res.length == 0)
    			toReturn = "Index";    		
    		String sousReq = "select p.lblprofil from Resshum r JOIN r.profils p where r.idress=:idress";
    		profils = em.createQuery(sousReq).setParameter("idress", res[0]).getResultList();
    		if (profils.size()==1) {
    			toReturn = profils.get(0);
    		}else if (profils.size()>1){
    			toReturn = "secondeAuthentification";
    		} else {
        		// Erreur d'authentification
    			toReturn = "Index";        			
    		}	
    	}
    	
    	//Gestion de la session utilisateur
    	if (!toReturn.equals("Index")) {
        	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        	HttpSession session = (HttpSession) context.getSession(true);
        	session.setAttribute("session_login", login.getValue().toString());
        	session.setAttribute("session_pwd", pwd.getValue().toString());
        	session.setAttribute("session_id", res[0]);
        	session.setAttribute("session_prenom", res[1]);
        	session.setAttribute("session_id_entreprise", getIdEntreprise(((Integer)res[0]).intValue(),typeUser));
    	}    	
    	//Retourner le résultat
    	return toReturn;
    }
    
    public Boolean isGgpSysteme(String login, String pwd){
    	Boolean toReturn = null;
    	try {
    		String req = "select g.idggps from Ggpsysteme g where g.emailggps=:login and g.mdpggps=:pwd";
        	Integer idRess = (Integer)em.createQuery(req).setParameter("login", login).setParameter("pwd", pwd).getSingleResult();        	
    		if (idRess != null)
    			toReturn = true;  		
    	}catch(javax.persistence.NoResultException e1){
    		try {
        		String req = "select r.idress from Resshum r where r.emailrh=:login and r.pwdrh=:pwd";
        		Integer idRess = (Integer)em.createQuery(req).setParameter("login", login).setParameter("pwd", pwd).getSingleResult();
        		if (idRess != null)
        			toReturn = false;   		    			
    		}catch(javax.persistence.NoResultException e2){
    			//Erreur d'identification
    		}
    	}
		return toReturn;
    }
    
    public int getIdEntreprise(int idRess, Boolean typeRess) {
    	int toReturn = 0;
    	if (!typeRess) {
    		try {    			
        		String req = "select o.entreprise.identreprise from Organisation o where o.idorg IN (select r.organisation.idorg from Ressource r where r.idress=:idress)";
        		toReturn = ((Integer)em.createQuery(req).setParameter("idress", idRess).getSingleResult()).intValue();

    		}catch(javax.persistence.NoResultException e2){
    			//Erreur bd
    		}    		
    	}
    	return toReturn;
    }
}
