package com.glaizer.RaliProject.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.international.StatusMessages;
import com.glaizer.RaliProject.model.Contenu;
import com.glaizer.RaliProject.model.Jourssemaine;
import com.glaizer.RaliProject.model.Phaseplanification;
import com.glaizer.RaliProject.model.Rapportactivites;
import com.glaizer.RaliProject.model.Ressource;
import com.glaizer.RaliProject.model.Tache;

@Stateless
@Name("MyReportingBean")
public class MyReportingBean implements MyReportingInterface {
	@PersistenceContext private EntityManager em;
    @In StatusMessages statusMessages;    
    private int sessionIdEntreprise;
    private Integer idRessource;
    private int week;
    private int year;    
	private ElementsRA[] contenuRA = new ElementsRA [] {
    	new ElementsRA("Lundi","lblProjet1","lblPhase1",8.30,"Salut tout le monde"),
    	new ElementsRA("Mardi","lblProjet1","lblPhase1",8.30,"Salut tout le monde"),
    	new ElementsRA("Mercredi","lblProjet1","lblPhase1",8.30,"Salut tout le monde"),
    	new ElementsRA("Jeudi","lblProjet1","lblPhase1",8.30,"Salut tout le monde"),
    	new ElementsRA("Vendredi","lblProjet1","lblPhase1",8.30,"Salut tout le monde"),
    };
    
    public MyReportingBean() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
    	sessionIdEntreprise = ((Integer) session.getAttribute("session_id_entreprise")).intValue();
    	idRessource = (Integer) session.getAttribute("session_id");
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
    
    @SuppressWarnings({ "unchecked" })
	public List<SelectItem> getWeeks(){
    	List<SelectItem> listSemaines = new ArrayList();
    	listSemaines.add(new SelectItem("Semaines..."));
    	for (int i=1; i<59; i++)
    		listSemaines.add(new SelectItem("S"+i));
    	return listSemaines;
    }
    

    @SuppressWarnings({ "unchecked" })
	public List<SelectItem> getHours(){
    	List<SelectItem> listHours = new ArrayList();
    	listHours.add(new SelectItem("Heures..."));
    	for (int i=1; i<24; i++)
    		listHours.add(new SelectItem(i));
    	return listHours;
    }
 
    @SuppressWarnings({ "unchecked" })
	public List<SelectItem> getMinutes(){
    	List<SelectItem> listMinutes = new ArrayList();
    	listMinutes.add(new SelectItem("Minutes..."));
    	for (int i=5; i<60; i = i+5)
    		listMinutes.add(new SelectItem(i));
    	return listMinutes;
    }

    @SuppressWarnings("unchecked")
	public List<SelectItem> getJours() {
		String req = "select j.joursouvresorgId, j.jourssemaine.nomjour, j.jourssemaine.idjour from Joursouvresorg j where j.isworkingdayorg=true and j.organisation.idorg in (select o.idorg from Organisation o where o.entreprise.identreprise=:identreprise) group by j.jourssemaine.nomjour order by j.jourssemaine.idjour asc";
		List<Object[]> res  = em.createQuery(req).setParameter("identreprise", sessionIdEntreprise).getResultList();
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem(0,"Jours..."));
		for (Object[] elt : res)
			items.add(new SelectItem((Integer)elt[0],(String)elt[1]));
		return items;
    }

    @SuppressWarnings("unchecked")
	public List<SelectItem> getTaches() {
		String req = "select t.idtache, t.lbltache from Tache t";
		List<Object[]> res  = em.createQuery(req).getResultList();
		
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem(0,"Tâches..."));
		for (Object[] elt : res)
			items.add(new SelectItem((Integer)elt[0],(String) elt[1]));
		return items;
    }

    public void setContenuRA() {
    	try {
	    	HtmlSelectOneListbox listAnnees = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listAnnees");
	    	HtmlSelectOneListbox listSemaines = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listSemaines");	    	
	    	HtmlSelectOneListbox listTaches = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listTaches");
	    	HtmlInputTextarea txtCommentaire = (HtmlInputTextarea) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:txtAreaCommentaire");
	    	HtmlSelectOneListbox listJours = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listJours");
	    	HtmlSelectOneListbox listHours = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listHours");
	    	HtmlSelectOneListbox listMinutes = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:listMinutes");
	    	String annee = listAnnees.getValue().toString();
	    	String semaine = listSemaines.getValue().toString();
	    	Integer phase = new Integer(listTaches.getValue().toString());;// c'est fausse cette ligne
	    	Integer tache = new Integer(listTaches.getValue().toString());
	    	String commentaire = txtCommentaire.getValue().toString();
	    	Integer jour = new Integer(listJours.getValue().toString());
	    	String heures = new String(listHours.getValue().toString());
	    	String minutes = new String(listMinutes.getValue().toString());
	    	Double duree = new Double(heures+"."+minutes);
	    	insertLineContenuRA(annee, semaine, phase, tache, commentaire, jour, duree);
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    	updateDataTableRA();
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
	
	private void insertLineContenuRA(String annee, String semaine, Integer phaseId, Integer tacheId, String commentaire, Integer jourId, Double duree) {
		//1°) Récupération de l'objet Jourssemaine connaissant son Id
		Jourssemaine jourssemaine= em.find(Jourssemaine.class, jourId);
		
		//2°) Récupération de l'objet Phaseplanification connaissant son Id
		Phaseplanification phaseplanification= em.find(Phaseplanification.class, phaseId);
		
		//3°) Récupération de l'objet Tache connaissant son Id
		Tache tache= em.find(Tache.class, tacheId);
		
		//4°) Récupération de l'objet Rapportactivites connaissant l'annee et la semaine.
		Rapportactivites rapportactivites = null;
		try {
			//4.1°) Si le rapport existe, on le récupère
			String req = "Select ra.idrapactv from Rapportactivites ra where ra.anneera=:anneera and ra.semainera=:semainera";
			Integer raId = (Integer) em.createQuery(req).setParameter("anneera", annee).setParameter("semainera", semaine).getSingleResult();
			rapportactivites= em.find(Rapportactivites.class, raId);
		}catch(Exception e41) {
			try {
				//4.2°) Sinon, si le rapport n'existe pas, on crée un nouveau RA pour la ressource connectée puis on l'insère dans la BD				
				String req = "Select Max(ra.idrapactv) from Rapportactivites ra";
				Integer raId = (Integer) em.createQuery(req).getSingleResult();
				raId++;
				Ressource ressource = em.find(Ressource.class, idRessource);				
				rapportactivites = new Rapportactivites(raId, ressource, annee, semaine);	
				em.merge(rapportactivites);
			}catch (Exception e42) {				
				e42.printStackTrace();
			}
		}		
				
		//5°) On récupère l'id du futur enregistrement de la table Contenu		
		String req = "Select Max(c.contenuId) from Contenu c";
		Integer contenuId = (Integer) em.createQuery(req).getSingleResult();
		contenuId++;
		
		//6°) On insère le nouvel enregistrement
		Contenu contenu = new Contenu(contenuId, duree, commentaire, jourssemaine, rapportactivites, phaseplanification, tache);
		em.merge(contenu);
	}
	
	private void updateDataTableRA() {
//		List<Contenu> listContenu = new ArrayList();		 
//      	HtmlDataTable dataTableRA = (HtmlDataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("MyReportingForm:dtContenuRA");
//      	DataModel dataModel = new ListDataModel(listContenu);
//      	dataTableRA.setValue(dataModel);
	}



	public void setContenuRA(ElementsRA[] contenuRA) {
		this.contenuRA = contenuRA;
	}



	public ElementsRA[] getContenuRA() {
		return contenuRA;
	}
	
}
