package com.glaizer.RaliProject.action;

import javax.ejb.Local;
import javax.faces.event.ValueChangeEvent;

@Local
public interface SecondeAuthentificationInterface
{
    // seam-gen method
    public String secondeAuthentification();
    public void setListDossiers(ValueChangeEvent e);
}
