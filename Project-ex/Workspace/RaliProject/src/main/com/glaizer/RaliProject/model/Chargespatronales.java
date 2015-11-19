package com.glaizer.RaliProject.model;
// Generated 12 juil. 2010 14:28:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chargespatronales generated by hbm2java
 */
@Entity
@Table(name = "CHARGESPATRONALES", catalog = "ralidb")
public class Chargespatronales implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idcp;
//	private int idress;
	private Date cpFrom;
	private Date cpTo;
	private Long cpValeur;
	private Resshum resshum;

	public Chargespatronales() {
	}

	public Chargespatronales(int idcp) {
		this.idcp = idcp;
	}
	
	public Chargespatronales(Integer idcp, Date cpFrom, Date cpTo,
			Long cpValeur, Resshum resshum) {
		super();
		this.idcp = idcp;
		this.cpFrom = cpFrom;
		this.cpTo = cpTo;
		this.cpValeur = cpValeur;
		this.resshum = resshum;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDCP", unique = true, nullable = false)
	public Integer getIdcp() {
		return this.idcp;
	}

	public void setIdcp(Integer idcp) {
		this.idcp = idcp;
	}

//	@Column(name = "IDRESS", nullable = false)
//	public int getIdress() {
//		return this.idress;
//	}
//
//	public void setIdress(int idress) {
//		this.idress = idress;
//	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CP_FROM", length = 10)
	public Date getCpFrom() {
		return this.cpFrom;
	}

	public void setCpFrom(Date cpFrom) {
		this.cpFrom = cpFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CP_TO", length = 10)
	public Date getCpTo() {
		return this.cpTo;
	}

	public void setCpTo(Date cpTo) {
		this.cpTo = cpTo;
	}

	@Column(name = "CP_valeur", precision = 10, scale = 0)
	public Long getCpValeur() {
		return this.cpValeur;
	}

	public void setCpValeur(Long cpValeur) {
		this.cpValeur = cpValeur;
	}
	@ManyToOne
	@JoinColumn(name="IDRESS", referencedColumnName="IDRESS")
	public Resshum getResshum() {
		return resshum;
	}

	public void setResshum(Resshum resshum) {
		this.resshum = resshum;
	}

}
