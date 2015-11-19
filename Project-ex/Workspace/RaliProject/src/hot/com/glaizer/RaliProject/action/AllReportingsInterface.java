package com.glaizer.RaliProject.action;

import java.util.List;
import javax.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

@Local
public interface AllReportingsInterface {
    // seam-gen method
    public List<SelectItem> getYears();    
    public List<SelectItem> getWeeks();    
    public List<SelectItem> getResources();
    public int getWeek();
    public int getYear();
    public void getContenuRA(ValueChangeEvent arg);
}
