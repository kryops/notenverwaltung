package de.dhbw.swe.notenverwaltung;

import java.security.InvalidParameterException;

/**
 * Units sind Modulen zugeordnet. Alle Noten des Studenten bis auf die der Bachelorarbeit werden
 * in den Units gespeichert.
 * 
 * @author Michael Strobel
 *
 */
public class Unit {
	
	private String name;
	private String pruefungsform;
	private double note;
	
	/**
	 * Gewichtung der Unit in Prozent
	 * Unbenotete Units haben eine Gewichtung von 0
	 */
	private int gewichtung;
	
	/**
	 * Gibt an, ob der Student eine Unit bestanden hat
	 * Wird beim setzen einer Note mit 4 oder besser automatisch gesetzt
	 */
	private boolean bestanden;
	
	/**
	 * Das Modul, zu welchem die Unit geh�rt
	 * Wird automatisch beim Hinzuf�gen zur Unit-Liste des Moduls gesetzt
	 */
	private Modul modul;
	
	
	
	
	/**
	 * Unit anlegen; Die Gewichtung wird automatisch auf 100 gesetzt
	 * @param name
	 */
	public Unit(String name) {
		this.name = name;
		this.gewichtung = 100;
	}
	
	/**
	 * Unit anlegen
	 * @param name
	 * @param pruefungsform
	 * @param gewichtung
	 * 		zwischen 0 und 100
	 * 		0 bei unbenoteten Units
	 * @throws InvalidParameterException Gewichtung au�erhalb des g�ltigen Bereichs
	 */
	public Unit(String name, String pruefungsform, int gewichtung) {
		this.name = name;
		this.pruefungsform = pruefungsform;
		setGewichtung(gewichtung);
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
	
	/**
	 * @return Note der Unit; 0, wenn keine Note gesetzt wurde;
	 * 		bei unbenoteten Units 1 f�r bestanden und 5 f�r nicht bestanden
	 */
	public double getNote() {
		return note;
	}
	
	/**
	 * Setzt die Note einer Unit
	 * Ist sie 4 oder besser, wird die Unit als bestanden markiert und die Berechnung
	 * der Gesamtnote des Moduls angesto�en, zu welchem die Unit geh�rt
	 * @param note Note zwischen 1 und 5
	 * @throws InvalidParameterException Note au�erhalb des g�ltigen Bereichs
	 */
	public void setNote(double note) {
		this.note = note;
		
		if(note < 1 || note > 5) {
			throw new InvalidParameterException("Note au�erhalb des g�ltigen Bereichs!");
		}
		
		if(note <= 4) {
			this.bestanden = true;
			
			if(this.modul != null) {
				this.modul.modulnoteBerechnen();
			}
		}
	}

	public int getGewichtung() {
		return gewichtung;
	}
	
	/**
	 * Gewichtung setzen
	 * @param gewichtung
	 * 		zwischen 0 und 100
	 * 		0 bei unbenoteten Units
	 * @throws InvalidParameterException Gewichtung au�erhalb des g�ltigen Bereichs
	 */
	public void setGewichtung(int gewichtung) {
		
		if(gewichtung < 0 || gewichtung > 100) {
			throw new InvalidParameterException("Gewichtung au�erhalb des g�ltigen Bereichs");
		}
		
		this.gewichtung = gewichtung;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public boolean isBestanden() {
		return bestanden;
	}
	
	/**
	 * Markiert eine Unit als bestanden
	 * St��t die Berechnung der dazugeh�rigen Modulnote an
	 * @param bestanden
	 */
	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
		
		/*
		 * Auch bei unbenoteten Pr�fungsleistungen eine Note eintragen, damit �berpr�ft werden kann,
		 * ob �berhaupt etwas eingetragen wurde
		 */
		if(note == 0) {
			note = bestanden ? 1 : 5;
		}
		
		if(modul != null) {
			modul.modulnoteBerechnen();
		}
	}
	
	/**
	 * Eine Unit gilt als benotete Pr�fungsleistung, wenn eine Gewichtung gr��er 0 eingetragen wurde
	 * @return
	 */
	public boolean isBenotet() {
		return (gewichtung != 0);
	}
	
}
