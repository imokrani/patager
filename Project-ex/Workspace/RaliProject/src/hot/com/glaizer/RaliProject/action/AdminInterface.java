package com.glaizer.RaliProject.action;

import java.util.List;

import javax.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNodeImpl;

@Local
public interface AdminInterface{
  
	public void processValueChangeTypeRessource(ValueChangeEvent arg0); 
	public TreeNodeImpl<String> getElementNode(); 
	public void processSelectionTree(NodeSelectedEvent event); 
	public DataModel getRessourcesHumaine(); 
	public DataModel getListePrestataires(); 
	public DataModel getListeRessourcesConsomables(); 
	public DataModel getListeRessourcesMaterielles();  
	public void processValueChangeDiplomePrestation(ValueChangeEvent arg0);
	public void processValueChangeTypeRessourcePRESTATAIRES(ValueChangeEvent arg0); 
	public void processValueChangeTypeRessourceMATERIELLE(ValueChangeEvent arg0);
	public void processValueChangeTypeRessourceCONSOMMABLES(ValueChangeEvent arg0);
	public List<SelectItem> AgetDiplomes(); 
	public List<SelectItem> AgetTypesPrestataires(); 
	public void processValueChangeTypePRESTATAIRES(ValueChangeEvent arg0); 
}
