package com.glaizer.RaliProject.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.*;
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
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import com.glaizer.RaliProject.model.Diplome;
import com.glaizer.RaliProject.model.Organisation;
import com.glaizer.RaliProject.model.Phaseplanification;
import com.glaizer.RaliProject.model.Prestataire;
import com.glaizer.RaliProject.model.Ressamort;
import com.glaizer.RaliProject.model.Ressconso;
import com.glaizer.RaliProject.model.Resshum;
import com.glaizer.RaliProject.model.Typeprestataire;

@SuppressWarnings("unchecked")
@Stateless
@Name("admin")
public class AdminBean implements AdminInterface {
	//********************************************************************VARIABLES
	 @PersistenceContext
	 private EntityManager em; 
	 private int sessionIdEntreprise;	 
	 private TreeNodeImpl<String> arbreRoot;
	 private TreeNodeImpl<String> elementNode;
	 @SuppressWarnings("unused")
	private List itemList1;
	 
	 
	 //Constructeur
	 public AdminBean() {
	    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    	HttpSession session = (HttpSession) externalContext.getSession(false);
	    	sessionIdEntreprise = ((Integer) session.getAttribute("session_id_entreprise")).intValue();		
		 
	 }
	 
	 
	 //********************************************************************METHODES 
    /*
     * RETOURNE LA LISTE DES DIPLOMES
     */
	public List<SelectItem> AgetDiplomes(){
		 List<SelectItem> itemList = new ArrayList();
		 List<Diplome> dip = em.createQuery("select d from Diplome d").getResultList(); 
		 for(Iterator iterator = dip.iterator(); iterator.hasNext();){
			 Diplome dipl = (Diplome) iterator.next(); 
			 itemList.add(new SelectItem(dipl.getLbldiplome())); 
		 }
		 return itemList; 
   }
	
    /*
     * RETOURNE LA LISTE DES TYPES DE PRESTATIONS
     */
	public List<SelectItem> AgetTypesPrestataires(){
		 List<SelectItem> itemList = new ArrayList(); 
		 List<Typeprestataire> typePre = em.createQuery("select d from Typeprestataire d").getResultList(); 
		 for(Iterator iterator = typePre.iterator(); iterator.hasNext();){
			 Typeprestataire tp = (Typeprestataire) iterator.next(); 
			 itemList.add(new SelectItem(tp.getLbltypeprest())); 
		 }
		 return itemList; 
   }
	
	/*
	 * L'ECOUTEUR DE LA LISTE DES TYPES DE RESSOURCES DEPUIS LE PAGE ADMIN
	 */
	public void processValueChangeTypeRessource(ValueChangeEvent arg0){
		test(); 
		HtmlOutputText monComponent = (HtmlOutputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("test:montitre");
		@SuppressWarnings("unused")
		UISelectItems it  = new UISelectItems();
	    HtmlSelectOneListbox typeDiplomePrestationList = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("CriteresRecherches:diplomePrestation");
		itemList1 =   new ArrayList<SelectItem>();	
		//****************************************************************RESSOURCE HUMAINE
		if(arg0.getNewValue().toString().equals("11")){
		}else
			if(arg0.getNewValue().toString().equals("22")){
		//****************************************************************RESSOURCE PRESTATAIRE
			    try{
		         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminPrestataires.seam");
		         	}catch (Exception e) {
					}
			}else{
				if(arg0.getNewValue().toString().equals("33")){
					//****************************************************************RESSOURCE MATERIELLE
					 try{
				         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminMaterielle.seam");
				         	}catch (Exception e) {
							}
				}else{
					if(arg0.getNewValue().toString().equals("44")){
						//****************************************************************RESSOURCE CONSOMMABLES
						 try{
					         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminConsomable.seam");
					         	}catch (Exception e) {
								}
					}else{
						monComponent.setValue("");
						typeDiplomePrestationList.getChildren().clear();
					    typeDiplomePrestationList.setDisabled(true); 
					}
				}
			}
	}
	
	/*
	 * L'ECOUTEUR DE LA LISTE DES TYPES DE RESSOURCES DEPUIS LE PAGE PRESTATAIRES
	 */
	public void processValueChangeTypeRessourcePRESTATAIRES(ValueChangeEvent arg0){
		HtmlOutputText monComponent = (HtmlOutputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("test:montitre");
		@SuppressWarnings("unused")
		UISelectItems it  = new UISelectItems();
	    HtmlSelectOneListbox typeDiplomePrestationList = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("CriteresRecherches:diplomePrestation");
		itemList1 =   new ArrayList<SelectItem>();	
		if(arg0.getNewValue().toString().equals("11")){
						//redirection vers admin 
					    try{
				         	FacesContext.getCurrentInstance().getExternalContext().redirect("admin.seam");
				         	}catch (Exception e) {
							}
			
		}else
			if(arg0.getNewValue().toString().equals("22")){	
			}else{
				if(arg0.getNewValue().toString().equals("33")){
					//****************************************************************RESSOURCE MATERIELLE
					 try{
				         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminMaterielle.seam");
				         	}catch (Exception e) {
							}
				}else{
					if(arg0.getNewValue().toString().equals("44")){
						//****************************************************************RESSOURCE CONSOMMABLES
						 try{
					         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminConsomable.seam");
					         	}catch (Exception e) {
								}
					}else{
						monComponent.setValue("");
						typeDiplomePrestationList.getChildren().clear();
					    typeDiplomePrestationList.setDisabled(true); 
					}
				}
			}
	}
	

	/*
	 * L'ECOUTEUR DE LA LISTE DES TYPES DE RESSOURCES DEPUIS LE PAGE DE RESSOURCES MATERIELLES
	 */
	public void processValueChangeTypeRessourceMATERIELLE(ValueChangeEvent arg0){
		HtmlOutputText monComponent = (HtmlOutputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("test:montitre");
		@SuppressWarnings("unused")
		UISelectItems it  = new UISelectItems();
	    HtmlSelectOneListbox typeDiplomePrestationList = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("CriteresRecherches:diplomePrestation");
		itemList1 =   new ArrayList<SelectItem>();	
		if(arg0.getNewValue().toString().equals("11")){
								    try{
							         	FacesContext.getCurrentInstance().getExternalContext().redirect("admin.seam");
							         	}catch (Exception e) {
										}
		}else
			if(arg0.getNewValue().toString().equals("22")){
			    try{
		         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminPrestataires.seam");
		         	}catch (Exception e) {
					}
				
			}else{
				if(arg0.getNewValue().toString().equals("33")){
					//****************************************************************RESSOURCE MATERIELLE
				
				}else{
					if(arg0.getNewValue().toString().equals("44")){
						//****************************************************************RESSOURCE CONSOMMABLES
						 try{
					         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminConsomable.seam");
					         	}catch (Exception e) {
								}
					}else{
						monComponent.setValue("");
						typeDiplomePrestationList.getChildren().clear();
						//it.setRendered(false); 
					    typeDiplomePrestationList.setDisabled(true); 
					}
				}
			}
	}
	
	/*
	 * L'ECOUTEUR DE LA LISTE DES TYPES DE RESSOURCES DEPUIS LE PAGE CONSOMMABLES
	 */
	public void processValueChangeTypeRessourceCONSOMMABLES(ValueChangeEvent arg0){
		HtmlOutputText monComponent = (HtmlOutputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("test:montitre");
		@SuppressWarnings("unused")
		UISelectItems it  = new UISelectItems();
	    HtmlSelectOneListbox typeDiplomePrestationList = (HtmlSelectOneListbox) FacesContext.getCurrentInstance().getViewRoot().findComponent("CriteresRecherches:diplomePrestation");
		itemList1 =   new ArrayList<SelectItem>();	
		if(arg0.getNewValue().toString().equals("11")){
								    try{
							         	FacesContext.getCurrentInstance().getExternalContext().redirect("admin.seam");
							         	}catch (Exception e) {
										}
		}else
			if(arg0.getNewValue().toString().equals("22")){
			    try{
		         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminPrestataires.seam");
		         	}catch (Exception e) {
					}
				
			}else{
				if(arg0.getNewValue().toString().equals("33")){
					//****************************************************************RESSOURCE MATERIELLE
					 try{
				         	FacesContext.getCurrentInstance().getExternalContext().redirect("adminMaterielle.seam");
				         	}catch (Exception e) {
							}
				}else{
					if(arg0.getNewValue().toString().equals("44")){
						//****************************************************************RESSOURCE CONSOMMABLES
					}else{
						monComponent.setValue("");
						typeDiplomePrestationList.getChildren().clear();
					    typeDiplomePrestationList.setDisabled(true); 
					}
				}
			}
	}
	
	
	/*
	 * L'ECOUTEUR DE LA LISTE DES DIPLOME OU LISTE DES TYPES DE PRESTATAIRES
	 */
	 public void processValueChangeDiplomePrestation(ValueChangeEvent arg0){
		 List<Resshum> resshum = new ArrayList<Resshum>(); 
		    resshum = em.createQuery("SELECT ad.resshum FROM Avoirdiplome ad WHERE ad.diplome.lbldiplome =:arg").setParameter("arg", arg0.getNewValue().toString()).getResultList();
			 //charger les ressource humaine
         	HtmlDataTable monDataTable = (HtmlDataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("aaaa:bbbb");
         	DataModel l = new ListDataModel(resshum);
         	monDataTable.setValue(l);
         	monDataTable.setWidth("1000");
         	monDataTable.setCellpadding("3"); 
	 }
	/*
	 * L'ECOUTEUR SUR LA LISTE DES TYPE DES PRESTATAIRE 
	 */
	 public void processValueChangeTypePRESTATAIRES(ValueChangeEvent arg0){
		     List<Prestataire> pres = new ArrayList<Prestataire>(); 
		    pres = em.createQuery("SELECT DISTINCT p.prestataire  FROM Prestation p    WHERE p.typeprestataire.lbltypeprest =:arg").setParameter("arg", arg0.getNewValue().toString()).getResultList();
         	HtmlDataTable monDataTable = (HtmlDataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("aaaa:bbbb");
         	DataModel l = new ListDataModel(pres);
         	monDataTable.setValue(l);
         	monDataTable.setWidth("1000");
         	monDataTable.setCellpadding("3"); 
	 }
	 
	/*
	 * METHODE QUI RENVOIE LA STRUCTURE ARBRE DE L'ENTREPRISE 
	 */
	public List<Organisation> renvoieStructureEntreprise(){
		 List<Organisation> org = em.createQuery("select o from Organisation o where o.entreprise.identreprise = :idEntr ").setParameter("idEntr", sessionIdEntreprise).getResultList();
		 return org; 
	}
	
	/*
	 *  LA METHODE QUI RETOURNE LA STRUCTURE ARBRE D'UN PROJET 
	 */
	public List<Phaseplanification> renvoieStructureProjet(Integer idp){
		 List<Phaseplanification> listephase = em.createQuery("select fp from Phaseplanification fp where fp.projet.idprojet ="+idp).getResultList();
		 return listephase; 
	}
	
	public void test(){
		List<Phaseplanification> pro = renvoieStructureProjet(1); 
		 Iterator< Phaseplanification> itr = pro.iterator(); 
		 while(itr.hasNext()){
			 System.out.println("---------------------------------------------------------------------"+(itr.next()).getLblphaseP()); 
		 }
		
	}
	
	/*
	 * METHODE QUI RETOURNE LA RACINE DE LA STRUCTURE ARBRE D'UNE ENTREPRISE
	 */
	protected int renvoieIDRacine(List<Organisation> lOr){  
		Organisation o = null; 
		boolean trouve = false; 
		Iterator iterator = lOr.iterator();
			while(iterator.hasNext() && trouve == false){
				o = (Organisation)iterator.next();
				
				if( new Integer(getPereOrganisation(o).getIdorg()) == 0){
					trouve = true; 
				}
			}
		if(trouve)
			return  o.getIdorg(); 
		else
			return -1; 
	}
	
	/*
	 * METHODE QUI RETOURNE LE NOMBRE DE FILS D'UN NOEUD 
	 */
	public int nombreFilsNoeud(List<Organisation> lOrg, int idFils){
		Iterator<Organisation> itrOrg = lOrg.iterator(); 
		Organisation o = null; 
		int nbpere = 0; 
		while(itrOrg.hasNext()){
			o = (Organisation)itrOrg.next(); 
			if(getPereOrganisation(o).getIdorg() == idFils){
				nbpere++; 
			}
		}
		return nbpere;
	}
	
	/*
	 * METHODE QUI RETOURNE LE PERE D'UNE ORGANISATION
	 */
	public Organisation getPereOrganisation(Organisation org) {
		int idorg = org.getIdorg();
		String req = "Select o.organisation from Organisation o where o.idorg = :id";
		Organisation o = new Organisation();
		try {
			o = (Organisation)em.createQuery(req).setParameter("id", idorg).getSingleResult();
		}catch(Exception e) {}
		return o;
	}
	
	/*
	 * METHODE QUI RETOURNE LA LISTE DES FILS D'UNE ORGANISATION
	 */
	public List<Organisation> getFilsOrganisation(Organisation org) {
		int idorg = org.getIdorg();
		String req = "Select o.organisations from Organisation o where o.idorg = :id";
		return (List<Organisation>)em.createQuery(req).setParameter("id", idorg).getResultList();
	}
	
	/*
	 * LA METHODE QUI RENVOIE LE TITRE DE LA RACINE
	 */
	public String renvoieTitreRacine(List<Organisation> lOrg, int idOrg){
		Iterator<Organisation> itrOrg = lOrg.iterator(); 
		Organisation o = null; 
		boolean trouve = false; 
			while(itrOrg.hasNext() && trouve == false){
				o = (Organisation)itrOrg.next(); 
				if(o.getIdorg() == idOrg){
					trouve = true; 
				}
			}
			if(trouve){
				return o.getLblorg(); 
			}else
				return null; 
	}
	
	/*
	 * METHODE QUI REMPLI LE TREE
	 */
	public void initialisationStructureEntreprise(){
		arbreRoot = new TreeNodeImpl<String>();
		elementNode = new TreeNodeImpl<String>();
		
		List<Organisation> listeOrg =  renvoieStructureEntreprise();
		Iterator<Organisation> itrOrg = listeOrg.iterator();
		Organisation o = null; 
		int rang =0; 
		//AFFECTER UN TITRE A LA RACINE
		arbreRoot.setData(renvoieTitreRacine(listeOrg, renvoieIDRacine(listeOrg))); 
		//AJOUTER UN FILS � LA RACINE 
		elementNode.addChild(0,arbreRoot); 
		while(itrOrg.hasNext()){
			o =(Organisation)itrOrg.next(); 
			//CREATION DES ENTREPRISES
			if(renvoieIDRacine(listeOrg) == getPereOrganisation(o).getIdorg()){
				TreeNodeImpl<String> child = new  TreeNodeImpl<String>(); 
				child.setData(o.getLblorg()); 
				arbreRoot.addChild(rang, child); 
				rang++; 
					//CREATION DES DEPARTEMENT
					Iterator<Organisation> itrDep = listeOrg.iterator(); 
					Organisation oDep =null; 
					int rangDep =0; 
					while(itrDep.hasNext()){
						oDep = (Organisation)itrDep.next(); 
						if(o.getIdorg() == getPereOrganisation(oDep).getIdorg()){
							TreeNodeImpl<String> childDep = new TreeNodeImpl<String>(); 
							childDep.setData(oDep.getLblorg()); 
							child.addChild(rangDep, childDep); 
							rangDep++; 
							//CREATION DES POLES
							Iterator<Organisation> itrPole = listeOrg.iterator(); 
							Organisation oPole = null; 
							int rangPole = 0; 
							while(itrPole.hasNext()){
								oPole = (Organisation)itrPole.next(); 
								if(oDep.getIdorg() == getPereOrganisation(oPole).getIdorg()){
									TreeNodeImpl<String> childPole = new TreeNodeImpl<String>(); 
									childPole.setData(oPole.getLblorg()); 
									childDep.addChild(rangPole, childPole); 
									rangPole++; 
 								}
							}
						}
					}
			}	
		}
		 
	}
	
	
	/*
	 * METHODE DE SELECTION DANS LE TREE
	 */
	public void processSelectionTree(NodeSelectedEvent event){
		HtmlTree myTree = ((HtmlTree) event.getComponent());
		TreeNode currentNode = ((HtmlTree) event.getSource()).getModelTreeNode();
		String noeudName = currentNode.getData().toString();		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ id tree : "+myTree.getId());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ neud sélectionné :  "+noeudName);
	}
	
	/*
	 * LA METHODE QUI RETOURNE LA LISTE DES RESSOURCES HUMAINES
	 */
	public DataModel getRessourcesHumaine(){
		List<Resshum> resshum = new ArrayList<Resshum>(); 
		resshum = em.createQuery("select r from Resshum r").getResultList(); 
		return (new ListDataModel(resshum)); 
	} 	
	
	/*
	 * METHODE QUI RETOURNE LA LISTE DES PRESTATAIRE + FILTRAGE
	 */
	public DataModel getListePrestataires(){
		List<Prestataire> pres = new ArrayList<Prestataire>(); 
		pres = em.createQuery("select p from Prestataire p").getResultList(); 
		return (new ListDataModel(pres)); 
	}
	
	/*
	 * METHODE QUI RETOURNE LA LISTE DES RESSOURCES MATERIELLES + FILTRAGE
	 */
	public DataModel getListeRessourcesConsomables(){
		List<Ressconso> mat = new ArrayList<Ressconso>(); 
		mat = em.createQuery("select rc from Ressconso rc").getResultList(); 
		return (new ListDataModel(mat)); 
	}
	
	/*
	 * METHODE QUI RETOURNE LA LISTE DES RESSOURCES MATERIELLES + FILTRAGE
	 */
	public DataModel getListeRessourcesMaterielles(){
		List<Ressamort> ra = new ArrayList<Ressamort>(); 
		ra = em.createQuery("select rs from Ressamort rs").getResultList(); 
		return (new ListDataModel(ra)); 
	}
	
	
	
	//********************************************************************GETTER ET SETTER
	public TreeNodeImpl<String> getArbreRoot() {
		return arbreRoot;
	}
	public void setArbreRoot(TreeNodeImpl<String> arbreRoot) {
		this.arbreRoot = arbreRoot;
	}
	public TreeNodeImpl<String> getElementNode() {
		this.initialisationStructureEntreprise();
		return elementNode;
	}
	public void setElementNode(TreeNodeImpl<String> elementNode) {
		this.elementNode = elementNode;
	}
}
