package com.glaizer.RaliProject.action;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlInputText;
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
import com.glaizer.RaliProject.model.SimulationcirScir;

@Stateless
@Name("GetCIRBean")
public class GetCIRBean implements GetCIRInterface {
	@PersistenceContext private EntityManager em;
    @In StatusMessages statusMessages;
    private int sessionIdEntreprise;
    
    
    public GetCIRBean() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
    	sessionIdEntreprise = ((Integer) session.getAttribute("session_id_entreprise")).intValue();
    }
    

    @SuppressWarnings("unchecked")
	public List<SelectItem> getDatesCIR() {
		String req = "select d.simulationcirScir.idscir, d.simulationcirScir.datescir from Dossiercir d where d.entreprise.identreprise=:identreprise ORDER BY d.simulationcirScir.datescir DESC";
		List<Object[]> res  = em.createQuery(req).setParameter("identreprise", sessionIdEntreprise).getResultList();
		List<SelectItem> items = new ArrayList();
		items.add(new SelectItem(0, "Dates..."));
		for (Object[] elt : res) {
			Integer id = (Integer)elt[0];
			Date lbl = (Date)elt[1];
			items.add(new SelectItem(id, lbl.toString()));
		}
		return items;    	
    }
    
	public void updateValuesCIR(){
    	HtmlSelectOneListbox listProfils = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:listDatesCIR");
    	Integer idScir = new Integer (listProfils.getValue().toString());
    	
		String req = "select Object(s) from SimulationcirScir s where s.idscir=:idscir";
		SimulationcirScir res  = (SimulationcirScir) em.createQuery(req).setParameter("idscir", idScir).getSingleResult();
		
		req = "select e.premierannecir from Entreprise e where e.identreprise=:identreprise";
		Integer anneeDecl  = new Integer ((String) em.createQuery(req).setParameter("identreprise", sessionIdEntreprise).getSingleResult());
		
		Calendar myCalendar = GregorianCalendar.getInstance();
		myCalendar.setTime(res.getDatescir());
		Integer anneeCIR = myCalendar.get(Calendar.YEAR);
		
		((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt1")).setValue(res.getDotamort());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt2")).setValue(res.getDepperschertech());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt3")).setValue(res.getDeppersjd());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt4")).setValue(res.getDepfct());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt5")).setValue(res.getPrisemaintbrevt());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt6")).setValue(res.getDepdefbrvt());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt7")).setValue(res.getDotamortbrvt());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt8")).setValue(res.getMoitsalchrg());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt9")).setValue(res.getAutrdepnorm());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt10")).setValue(res.getMoitdep());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt11")).setValue(res.getPrestpub());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt12")).setValue(res.getPrestprivagr());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt13")).setValue(res.getVeilltechno());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt14")).setValue(res.getTextil());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt15")).setValue(res.getSoustrait());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt16")).setValue(res.getDotamort()+res.getDepperschertech()+res.getDeppersjd()+res.getDepfct()+res.getPrisemaintbrevt()+res.getDepdefbrvt()+res.getDotamortbrvt()+res.getMoitsalchrg()+res.getAutrdepnorm()+res.getMoitdep()+res.getPrestpub()+res.getPrestprivagr()+res.getVeilltechno()+res.getTextil()+res.getSoustrait());
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt17")).setValue(getTauxCIR(anneeDecl,anneeCIR)*100);
    	((HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetCIRForm:elt18")).setValue((res.getDotamort()+res.getDepperschertech()+res.getDeppersjd()+res.getDepfct()+res.getPrisemaintbrevt()+res.getDepdefbrvt()+res.getDotamortbrvt()+res.getMoitsalchrg()+res.getAutrdepnorm()+res.getMoitdep()+res.getPrestpub()+res.getPrestprivagr()+res.getVeilltechno()+res.getTextil()+res.getSoustrait())*getTauxCIR(anneeDecl,anneeCIR));		
    }
    
    public double getTauxCIR(Integer anneePremierCIR, Integer anneeActuelCIR) {
    	if (anneeActuelCIR - anneePremierCIR < 0)
    		return 0;
    	if (anneeActuelCIR - anneePremierCIR == 0)
    		return 0.5;
    	if (anneeActuelCIR - anneePremierCIR == 1)
    		return 0.4;    	
    	return 0.3;    	
    }

    public void print() {
    	
    }

}
