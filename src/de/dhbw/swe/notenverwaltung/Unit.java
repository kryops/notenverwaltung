package de.dhbw.swe.notenverwaltung;

public class Unit {
	
	private String name;
	private String pruefungsform;
	private int note;
	private int gewichtung;
	
	private Modul modul;
	
	
	public Unit(String name) {
		this.name = name;
		this.gewichtung = 100;
	}
	
	public Unit(String name, String pruefungsform, int gewichtung) {
		this.name = name;
		this.pruefungsform = pruefungsform;
		this.gewichtung = gewichtung;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPruefungsform() {
		return pruefungsform;
	}

	public void setPruefungsform(String pruefungsform) {
		this.pruefungsform = pruefungsform;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getGewichtung() {
		return gewichtung;
	}

	public void setGewichtung(int gewichtung) {
		this.gewichtung = gewichtung;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
}
