package com.glaizer.RaliProject.action;

public class ElementsRA {
	String nomjour;
	String lblprojet;
	String lblphaseP;
	Double nbrheure;
	String commentaire;
	public ElementsRA(String nomjour, String lblprojet, String lblphaseP,
			Double nbrheure, String commentaire) {
		super();
		this.nomjour = nomjour;
		this.lblprojet = lblprojet;
		this.lblphaseP = lblphaseP;
		this.nbrheure = nbrheure;
		this.commentaire = commentaire;
	}
	public String getNomjour() {
		return nomjour;
	}
	public void setNomjour(String nomjour) {
		this.nomjour = nomjour;
	}
	public String getLblprojet() {
		return lblprojet;
	}
	public void setLblprojet(String lblprojet) {
		this.lblprojet = lblprojet;
	}
	public String getLblphaseP() {
		return lblphaseP;
	}
	public void setLblphaseP(String lblphaseP) {
		this.lblphaseP = lblphaseP;
	}
	public Double getNbrheure() {
		return nbrheure;
	}
	public void setNbrheure(Double nbrheure) {
		this.nbrheure = nbrheure;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}	
}
