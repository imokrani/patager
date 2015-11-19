package com.glaizer.RaliProject.action;

import java.util.List;

import javax.ejb.Local;
import javax.faces.model.SelectItem;

@Local
public interface GetICACInterface
{
    // seam-gen method
    public void getRD();
    public void getRI();
    public List<SelectItem> getListICAC();
}
