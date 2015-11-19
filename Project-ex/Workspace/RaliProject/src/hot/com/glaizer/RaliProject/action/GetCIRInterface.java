package com.glaizer.RaliProject.action;

import java.util.List;

import javax.ejb.Local;
import javax.faces.model.SelectItem;

@Local
public interface GetCIRInterface {	
    // seam-gen method
    public List<SelectItem> getDatesCIR();
//    public List<SelectItem> getCERFA();
    public void updateValuesCIR();
    public void print();
}
