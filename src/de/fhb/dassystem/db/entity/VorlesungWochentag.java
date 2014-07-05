package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VorlesungWochentag")
public class VorlesungWochentag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1527633952671439380L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vwid", unique = true, nullable = false)
	private Integer vwid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vid", nullable = false)
	private Vorlesung vorlesung;
	@Column(name="wochentag")
	private String wochentag;
	@Column(name="begin")
	private String begin;
	@Column(name="ende")
	private String ende;
	@Column(name="raumnr")
	private String raumnr;
	
	public VorlesungWochentag() {

	}

	public Integer getVwid() {
		return vwid;
	}

	public void setVwid(Integer vwid) {
		this.vwid = vwid;
	}

	public String getWochentag() {
		return wochentag;
	}

	public void setWochentag(String wochentag) {
		this.wochentag = wochentag;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnde() {
		return ende;
	}

	public void setEnde(String ende) {
		this.ende = ende;
	}

	public String getRaumnr() {
		return raumnr;
	}

	public void setRaumnr(String raumnr) {
		this.raumnr = raumnr;
	}

	public Vorlesung getVorlesung() {
		return vorlesung;
	}

	public void setVorlesung(Vorlesung vorlesung) {
		this.vorlesung = vorlesung;
	}

	@Override
	public String toString() {
		return "VorlesungWochentag [vwid=" + vwid + ", vorlesung=" + vorlesung
				+ ", wochentag=" + wochentag
				+ ", begin=" + begin + ", ende=" + ende + ", raumnr=" + raumnr
				+ "]";
	}
}
