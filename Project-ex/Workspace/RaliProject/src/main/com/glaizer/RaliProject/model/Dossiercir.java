package com.glaizer.RaliProject.model;
// Generated 12 juil. 2010 14:28:03 by Hibernate Tools 3.2.4.GA
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.Length;

/**
 * Dossiercir generated by hbm2java
 */
@Entity
@Table(name = "DOSSIERCIR", catalog = "ralidb")
public class Dossiercir implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer dossierCirId ;
	private String anneedosscir;
	private Entreprise entreprise;
	private Ggpsysteme ggpsysteme;
	private SimulationcirScir simulationcirScir;
	

	public Dossiercir() {
	}

	public Dossiercir(Integer dossierCirId) {
		this.dossierCirId = dossierCirId;
	}
	
	public Dossiercir(Integer dossierCirId, String anneedosscir,
			Entreprise entreprise, Ggpsysteme ggpsysteme,
			SimulationcirScir simulationcirScir) {
		super();
		this.dossierCirId = dossierCirId;
		this.anneedosscir = anneedosscir;
		this.entreprise = entreprise;
		this.ggpsysteme = ggpsysteme;
		this.simulationcirScir = simulationcirScir;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AVOIRDOSSIERCIR", unique = true, nullable = false)
	public Integer getDossierCirId() {
		return this.dossierCirId;
	}

	public void setDossierCirId(Integer dossierCirId) {
		this.dossierCirId = dossierCirId;
	}

	@Column(name = "ANNEEDOSSCIR", length = 65535)
	@Length(max = 65535)
	public String getAnneedosscir() {
		return this.anneedosscir;
	}

	public void setAnneedosscir(String anneedosscir) {
		this.anneedosscir = anneedosscir;
	}

	@ManyToOne
	@JoinColumn(name="IDENTREPRISE", referencedColumnName="IDENTREPRISE")	
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@ManyToOne
	@JoinColumn(name="IDGGPS", referencedColumnName="IDGGPS")	
	public Ggpsysteme getGgpsysteme() {
		return ggpsysteme;
	}

	public void setGgpsysteme(Ggpsysteme ggpsysteme) {
		this.ggpsysteme = ggpsysteme;
	}

	
	@ManyToOne
	@JoinColumn(name="IDSCIR", referencedColumnName="IDSCIR")	
	public SimulationcirScir getSimulationcirScir() {
		return simulationcirScir;
	}

	public void setSimulationcirScir(SimulationcirScir simulationcirScir) {
		this.simulationcirScir = simulationcirScir;
	}
	
}
