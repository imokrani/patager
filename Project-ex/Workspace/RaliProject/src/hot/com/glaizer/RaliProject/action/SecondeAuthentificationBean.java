package com.glaizer.RaliProject.action;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.jboss.seam.annotations.Name;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

@Stateless
@Name("SecondeAuthentificationBean")
public class SecondeAuthentificationBean implements SecondeAuthentificationInterface {
	@PersistenceContext private EntityManager em;
	private int sessionId;
	
	
	public SecondeAuthentificationBean(){
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
		sessionId = ((Integer) session.getAttribute("session_id")).intValue();		
	}
	
	
    public String secondeAuthentification(){
    	HtmlSelectOneListbox listProfils = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("secondeAuthentificationForm:listProfils");
    	return listProfils.getValue().toString();
    }
    
    public void setListDossiers(ValueChangeEvent e){
		HtmlSelectOneListbox listDossiers = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("secondeAuthentificationForm:listDossiers");
		UISelectItems uiselectitems = new UISelectItems();    	
		List<SelectItem> listItems = getListDossiers(sessionId);		
		listDossiers.getChildren().add(uiselectitems);
		uiselectitems.setValue(listItems);  	
    }
    

    @SuppressWarnings("unchecked")
	public List<SelectItem> getListDossiers(int idGgps){
		String req = "select d.entreprise.nomentreprise, d.anneedosscir from Dossiercir d where d.ggpsysteme.idggps=:idggps";
		List<Object[]> res  = em.createQuery(req).setParameter("idggps", idGgps).getResultList();    	
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem("Dossiers..."));
		for (Object[] elt : res)
			items.add(new SelectItem((String)elt[0]+" "+(String)elt[1]));	
		return items;    	
    }    
}
