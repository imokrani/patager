package com.glaizer.RaliProject.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.international.StatusMessages;
import com.glaizer.RaliProject.model.Contenu;

@Stateless
@Name("AllReportingsBean")
public class AllReportingsBean implements AllReportingsInterface {
	@PersistenceContext private EntityManager em;
    @In StatusMessages statusMessages;    
    private int sessionIdEntreprise;
    private int week;
    private int year;
    
    
    
    public AllReportingsBean() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
    	sessionIdEntreprise = ((Integer) session.getAttribute("session_id_entreprise")).intValue();
		Calendar myCalendar = GregorianCalendar.getInstance();
		setWeek(myCalendar.get(Calendar.WEEK_OF_YEAR));
		setYear(myCalendar.get(Calendar.YEAR));
    }

    @SuppressWarnings({ "unchecked" })
	public List<SelectItem> getYears(){
		Calendar myCalendar = GregorianCalendar.getInstance();
		Integer anneeCourante = myCalendar.get(Calendar.YEAR);
    	List<SelectItem> listAnnees = new ArrayList();
    	listAnnees.add(new SelectItem("Années..."));
    	for (int i=1; i<20; i++)
    		listAnnees.add(new SelectItem(anneeCourante--));
    	return listAnnees;
    }
    
    @SuppressWarnings("unchecked")
	public List<SelectItem> getResources() {
		String req = "select r.idress, r.acronyme from Resshum r where r.organisation.idorg in (select o.idorg from Organisation o where o.entreprise.identreprise=:identreprise)";
		List<Object[]> res  = em.createQuery(req).setParameter("identreprise", sessionIdEntreprise).getResultList();
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem(0,"Ressources..."));
		for (Object[] elt : res)
			items.add(new SelectItem((Integer)elt[0],(String)elt[1]));
		return items;
    }

    @SuppressWarnings({ "unchecked" })
	public List<SelectItem> getWeeks(){
    	List<SelectItem> listSemaines = new ArrayList();
    	listSemaines.add(new SelectItem("Semaines..."));
    	for (int i=1; i<59; i++)
    		listSemaines.add(new SelectItem("S"+i));
    	return listSemaines;
    }
    
    @SuppressWarnings("unchecked")
	public void getContenuRA(ValueChangeEvent arg) {
    	HtmlSelectOneListbox listAnnees = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("AllReportingsForm:listAnnees");
    	HtmlSelectOneListbox listSemaines = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("AllReportingsForm:listSemaines");
    	HtmlSelectOneListbox listRessources = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("AllReportingsForm:listRessources");
    	String annee = listAnnees.getValue().toString();
    	String semaine = listSemaines.getValue().toString();
    	Integer ressource = new Integer(listRessources.getValue().toString());
    	if (annee.equals("Années...") || semaine.equals("Semaines...") || ressource ==0)
    		return;
		List<Contenu> listContenu = (List<Contenu>) em.createQuery("select object(c) from Contenu c where c.rapportactivites.idrapactv in (select ra.idrapactv from Rapportactivites ra where ra.ressource.idress=:idress and ra.anneera=:anneera and ra.semainera=:semainera)").setParameter("idress", ressource).setParameter("anneera", annee).setParameter("semainera", semaine).getResultList();		 
      	HtmlDataTable dataTableRA = (HtmlDataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("RapportsActivitesForm:dtRA");
      	DataModel dataModel = new ListDataModel(listContenu);
      	dataTableRA.setValue(dataModel);
    }

	public void setWeek(int week) {
		this.week = week;
	}

	public int getWeek() {
		return week;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}
}
