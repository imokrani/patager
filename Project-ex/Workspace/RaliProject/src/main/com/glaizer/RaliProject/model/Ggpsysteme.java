package com.glaizer.RaliProject.model;
// Generated 12 juil. 2010 14:28:03 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.Length;

/**
 * Ggpsysteme generated by hbm2java
 */
@Entity
@Table(name = "GGPSYSTEME", catalog = "ralidb")
public class Ggpsysteme implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idggps;
	private String acroggps;
	private String nomggps;
	private String prenomggps;
	private String emailggps;
	private String mdpggps;
	private List<Profil> profils;
	private List<Dossiercir> entreprisesCir;
	private List<Dossiercac> icacs;
	private List<Dossiercac> entreprisesCac;
	private List<Dossiercir> simulationscirScirs;

	public Ggpsysteme() {
	}

	public Ggpsysteme(Integer idggps, String acroggps, String nomggps,
			String prenomggps, String emailggps, String mdpggps,
			List<Profil> profils, List<Dossiercir> simulationscirScirs,
			List<Dossiercir> entreprisesCir, List<Dossiercac> icacs,
			List<Dossiercac> entreprisesCac) {
		super();
		this.idggps = idggps;
		this.acroggps = acroggps;
		this.nomggps = nomggps;
		this.prenomggps = prenomggps;
		this.emailggps = emailggps;
		this.mdpggps = mdpggps;
		this.profils = profils;
		this.simulationscirScirs = simulationscirScirs;
		this.entreprisesCir = entreprisesCir;
		this.icacs = icacs;
		this.entreprisesCac = entreprisesCac;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDGGPS", unique = true, nullable = false)
	public Integer getIdggps() {
		return this.idggps;
	}

	public void setIdggps(Integer idggps) {
		this.idggps = idggps;
	}

	@Column(name = "ACROGGPS", length = 65535)
	@Length(max = 65535)
	public String getAcroggps() {
		return this.acroggps;
	}

	public void setAcroggps(String acroggps) {
		this.acroggps = acroggps;
	}

	@Column(name = "NOMGGPS", length = 65535)
	@Length(max = 65535)
	public String getNomggps() {
		return this.nomggps;
	}

	public void setNomggps(String nomggps) {
		this.nomggps = nomggps;
	}

	@Column(name = "PRENOMGGPS", length = 65535)
	@Length(max = 65535)
	public String getPrenomggps() {
		return this.prenomggps;
	}

	public void setPrenomggps(String prenomggps) {
		this.prenomggps = prenomggps;
	}

	@Column(name = "EMAILGGPS", length = 65535)
	@Length(max = 65535)
	public String getEmailggps() {
		return this.emailggps;
	}

	public void setEmailggps(String emailggps) {
		this.emailggps = emailggps;
	}

	@Column(name = "MDPGGPS", length = 65535)
	@Length(max = 65535)
	public String getMdpggps() {
		return this.mdpggps;
	}

	public void setMdpggps(String mdpggps) {
		this.mdpggps = mdpggps;
	}
	
	@ManyToMany
	@JoinTable(
			name="GGPS_AVOIR_PROFIL",
			joinColumns={@JoinColumn(name="IDGGPS", referencedColumnName="IDGGPS")},
			inverseJoinColumns={@JoinColumn(name="IDPROFIL", referencedColumnName="IDPROFIL")})	
	public List<Profil> getProfils() {
		return profils;
	}

	public void setProfils(List<Profil> profils) {
		this.profils = profils;
	}

	@OneToMany(mappedBy="ggpsysteme")	
	public List<Dossiercir> getSimulationscirScirs() {
		return simulationscirScirs;
	}

	public void setSimulationscirScirs(List<Dossiercir> simulationscirScirs) {
		this.simulationscirScirs = simulationscirScirs;
	}

	@OneToMany(mappedBy="ggpsysteme")		
	public List<Dossiercir> getEntreprisesCir() {
		return entreprisesCir;
	}

	public void setEntreprisesCir(List<Dossiercir> entreprisesCir) {
		this.entreprisesCir = entreprisesCir;
	}

	@OneToMany(mappedBy="ggpsysteme")	
	public List<Dossiercac> getIcacs() {
		return icacs;
	}

	public void setIcacs(List<Dossiercac> icacs) {
		this.icacs = icacs;
	}

	@OneToMany(mappedBy="ggpsysteme")
	public List<Dossiercac> getEntreprisesCac() {
		return entreprisesCac;
	}

	public void setEntreprisesCac(List<Dossiercac> entreprisesCac) {
		this.entreprisesCac = entreprisesCac;
	}
	
}
