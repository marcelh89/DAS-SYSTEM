package de.fhb.dassystem.valueobject.raum;

public class TeilnehmerIn {
	private Integer vorlesungId;
	// dd.MM.yyyy-HH:mm
	private String datum;
	

	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Integer getVorlesungId() {
		return vorlesungId;
	}
	public void setVorlesungId(Integer vorlesungId) {
		this.vorlesungId = vorlesungId;
	}
	
	
}
