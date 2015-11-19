package com.glaizer.RaliProject.action;

import javax.ejb.Local;

@Local
public interface LogoutInterface {
    // seam-gen method
    public void doLogout();
    public String getUserName();

}
