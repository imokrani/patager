package com.glaizer.RaliProject.action;

import java.util.List;

import javax.ejb.Local;
import javax.faces.model.SelectItem;

@Local
public interface MyReportingInterface
{
    // seam-gen method
    public List<SelectItem> getYears();    
    public List<SelectItem> getWeeks();    
    public List<SelectItem> getJours();
    public List<SelectItem> getTaches();
    public List<SelectItem> getHours();
    public List<SelectItem> getMinutes();
    public int getWeek();
    public int getYear();
    public void setContenuRA();
    ElementsRA[] getContenuRA();
    
}
