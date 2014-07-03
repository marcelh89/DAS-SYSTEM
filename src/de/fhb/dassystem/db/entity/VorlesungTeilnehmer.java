package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VorlesungTeilnehmer")
public class VorlesungTeilnehmer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7604776995073458260L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vtid", unique = true, nullable = false)
	private Integer vtid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vid", nullable = false)
	private Vorlesung vorlesung;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid", nullable = false)
	private User user;
	@Column(name="datum")
	private Date datum;

	public VorlesungTeilnehmer() {

	}

	public Integer getVtid() {
		return vtid;
	}

	public void setVtid(Integer vtid) {
		this.vtid = vtid;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Vorlesung getVorlesung() {
		return vorlesung;
	}

	public void setVorlesung(Vorlesung vorlesung) {
		this.vorlesung = vorlesung;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VorlesungTeilnehmer [vtid=" + vtid + ", vorlesung=" + vorlesung
				+ ", user=" + user + ", datum=" + datum + "]";
	}

	
}
