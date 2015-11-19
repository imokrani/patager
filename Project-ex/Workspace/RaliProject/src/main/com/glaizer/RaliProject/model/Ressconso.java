package com.glaizer.RaliProject.model;
// Generated 12 juil. 2010 14:28:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;

/**
 * Ressconso generated by hbm2java
 */
@Entity
@Table(name = "RESSCONSO", catalog = "ralidb")
@PrimaryKeyJoinColumn(name="IDRESS")
public class Ressconso extends Ressource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private Integer idress;
	private String designationrc;
	private Date dateacquisitionrc;
	private Long quantiteacquiserc;
	private Long prixunitaire;

	public Ressconso() {
	}

	public Ressconso(String designationrc, Date dateacquisitionrc,
			Long quantiteacquiserc, Long prixunitaire) {
		this.designationrc = designationrc;
		this.dateacquisitionrc = dateacquisitionrc;
		this.quantiteacquiserc = quantiteacquiserc;
		this.prixunitaire = prixunitaire;
	}

//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "IDRESS", unique = true, nullable = false)
//	public Integer getIdress() {
//		return this.idress;
//	}
//
//	public void setIdress(Integer idress) {
//		this.idress = idress;
//	}

	@Column(name = "DESIGNATIONRC", length = 65535)
	@Length(max = 65535)
	public String getDesignationrc() {
		return this.designationrc;
	}

	public void setDesignationrc(String designationrc) {
		this.designationrc = designationrc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATEACQUISITIONRC", length = 10)
	public Date getDateacquisitionrc() {
		return this.dateacquisitionrc;
	}

	public void setDateacquisitionrc(Date dateacquisitionrc) {
		this.dateacquisitionrc = dateacquisitionrc;
	}

	@Column(name = "QUANTITEACQUISERC", precision = 10, scale = 0)
	public Long getQuantiteacquiserc() {
		return this.quantiteacquiserc;
	}

	public void setQuantiteacquiserc(Long quantiteacquiserc) {
		this.quantiteacquiserc = quantiteacquiserc;
	}

	@Column(name = "PRIXUNITAIRE", precision = 10, scale = 0)
	public Long getPrixunitaire() {
		return this.prixunitaire;
	}

	public void setPrixunitaire(Long prixunitaire) {
		this.prixunitaire = prixunitaire;
	}

}
