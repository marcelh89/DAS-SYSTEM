package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@Column(name="vid")
	private Integer vid;
	@Column(name="uid")
	private Integer uid;
	@Column(name="anmeldecode")
	private Date datum;

	public VorlesungTeilnehmer() {

	}

	public Integer getVtid() {
		return vtid;
	}

	public void setVtid(Integer vtid) {
		this.vtid = vtid;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "VorlesungTeilnehmer [vtid=" + vtid + ", vid=" + vid + ", uid="
				+ uid + ", datum=" + datum + "]";
	}
}
