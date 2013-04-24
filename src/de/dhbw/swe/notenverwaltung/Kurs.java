package de.dhbw.swe.notenverwaltung;

import java.util.List;

public class Kurs {

	private String kursname;
	private int jahrgang;
	private String raum;
	private String studiengang;
	private String studiengangsleiter;
	private List<Student> studenten;

	//Getter und Setter
	public String getKursname() {
		return kursname;
	}
	public void setKursname(String kursname) {
		this.kursname = kursname;
	}
	
	public int getJahrgang() {
		return jahrgang;
	}
	public void setJahrgang(int jahrgang) {
		this.jahrgang = jahrgang;
	}
	
	public String getRaum() {
		return raum;
	}
	public void setRaum(String raum) {
		this.raum = raum;
	}
	
	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	
	public String getStudiengangsleiter() {
		return studiengangsleiter;
	}
	public void setStudiengangsleiter(String studiengangsleiter) {
		this.studiengangsleiter = studiengangsleiter;
	}
	
	public List<Student> getStudenten() {
		return studenten;
	}
	public void setStudenten(List<Student> studenten) {
		this.studenten = studenten;
	}
}
