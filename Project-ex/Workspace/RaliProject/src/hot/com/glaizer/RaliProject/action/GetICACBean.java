package com.glaizer.RaliProject.action;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.core.Manager;
import org.jboss.seam.document.ByteArrayDocumentData;
import org.jboss.seam.document.DocumentData;
import org.jboss.seam.document.DocumentStore;
import org.jboss.seam.document.DocumentData.DocumentType;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.navigation.Pages;

@Stateless
@Name("GetICACBean")
public class GetICACBean implements GetICACInterface {

	@PersistenceContext private EntityManager em;
    @In StatusMessages statusMessages;    
    private int sessionIdEntreprise;
    
    
    public GetICACBean() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);
    	sessionIdEntreprise = ((Integer) session.getAttribute("session_id_entreprise")).intValue();    	
    }


    public void getRD() {
    	HtmlSelectOneListbox listICAC = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetICACForm:listICAC");
    	String res= (String) listICAC.getValue();
    	Integer idICAC = new Integer (res);
		String req = "select i.lienSfDeprd from Icac i where i.idicac=:idicac";
		String pathFile = (String) em.createQuery(req).setParameter("idicac", idICAC).getSingleResult();
    	saveFile(pathFile);
    }

    public void getRI() {
    	HtmlSelectOneListbox listICAC = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("GetICACForm:listICAC");
    	String res= (String) listICAC.getValue();
    	Integer idICAC = new Integer (res);
		String req = "select i.lienSfDepri from Icac i where i.idicac=:idicac";
		String pathFile = (String) em.createQuery(req).setParameter("idicac", idICAC).getSingleResult();    	
    	saveFile(pathFile);
    }
    
    @SuppressWarnings("unchecked")
	public List<SelectItem> getListICAC(){    	
		String req = "select d.dossierCacId, d.anneedosscac from Dossiercac d where d.entreprise.identreprise=:identreprise ORDER BY d.anneedosscac DESC";
		List<Object[]> res  = em.createQuery(req).setParameter("identreprise", sessionIdEntreprise).getResultList();
		List<SelectItem> items = new ArrayList();
		for (Object[] elt : res)
			items.add(new SelectItem((Integer)elt[0],(String)elt[1]));
		return items;    	
    }
    
    public void saveFile(String filePath) {
    	try {
    		FileInputStream fis = new FileInputStream(filePath);  
    		HSSFWorkbook wb = new HSSFWorkbook(fis);
	    	fis.close();
	    	String viewId = Pages.getViewId(FacesContext.getCurrentInstance());
	    	String baseName = baseNameForViewId(viewId);
	    	DocumentType documentType = new DocumentData.DocumentType("xls", "application/vnd.ms-excel");
	    	DocumentData documentData = new ByteArrayDocumentData(baseName, documentType, wb.getBytes());
	    	String id = DocumentStore.instance().newId();
	    	String url = DocumentStore.instance().preferredUrlForContent(baseName, documentType.getExtension(), id);
	    	url = Manager.instance().encodeConversationId(url, viewId);
	    	DocumentStore.instance().saveData(id, documentData);
	    	FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static String baseNameForViewId(String viewId) {
    	int pos = viewId.lastIndexOf("/");
    	if (pos != -1) {
    		viewId = viewId.substring(pos + 1);
    	}
    	pos = viewId.lastIndexOf(".");
    	if (pos != -1) {
    		viewId = viewId.substring(0, pos);
    	}
    	return viewId;
    }       
}
