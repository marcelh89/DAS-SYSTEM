package de.fhb.dassystem.db.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vorlesung")
public class Vorlesung implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8718799384980221553L;
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vid", unique = true, nullable = false)
	private Integer vid;

	@Column(name="name")
	private String name;
	@Column(name="inhalt")
	private String inhalt;
	@Column(name="anmeldecode")
	private String anmeldecode;

	public Vorlesung() {

	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public String getAnmeldecode() {
		return anmeldecode;
	}

	public void setAnmeldecode(String anmeldecode) {
		this.anmeldecode = anmeldecode;
	}

	@Override
	public String toString() {
		return "Vorlesung [vid=" + vid + ", name=" + name + ", inhalt="
				+ inhalt + ", anmeldecode=" + anmeldecode + "]";
	}
	
	
}
