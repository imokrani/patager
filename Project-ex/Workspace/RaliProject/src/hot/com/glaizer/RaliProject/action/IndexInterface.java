package com.glaizer.RaliProject.action;

import java.util.List;

import javax.ejb.Local;
import javax.faces.model.SelectItem;

@Local
public interface IndexInterface {

	public String authentification();
	public List<SelectItem> getProfils();
}
