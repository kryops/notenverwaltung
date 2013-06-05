package de.dhbw.swe.notenverwaltung;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestObjekte{ 
	DHBW dhbw = DHBW.getDHBW();
	int matrikel = 1;
	
	public void kurseAnlegen(){
		//Kurs ohne Studenten
		Kurs kursLeer = new Kurs("kursleer", 2010, "AI");
		kursLeer.setRaum("1.101");
		kursLeer.setStudiengangsleiter("ABC");
		dhbw.addKurs(kursLeer);
		
		//Kurs mit 20Studenten ("voll")
		Kurs kursVoll = new Kurs("kursvoll", 2011, "WI");
		kursVoll.setRaum("2.034");
		kursVoll.setStudiengangsleiter("XYZ");
		kursVoll.setStudenten(studentenAnlegen(20, "aaa"));
		dhbw.addKurs(kursVoll);
		
		//Kurs mit 10Studenten (teilbefüllt)
		Kurs kursTb = new Kurs("kurstb", 2012, "TI");
		kursTb.setRaum("5.44");
		kursTb.setStudiengangsleiter("MNO");
		kursTb.setStudenten(studentenAnlegen(10, "bbb"));
		dhbw.addKurs(kursTb);
		
	}
	
	public List<Student> studentenAnlegen (int anzahl, String id ){
		List<Student> studenten = new ArrayList<Student>();
		for(int i=0; i<anzahl; i++){
			id = id + String.valueOf(i);
			studenten.add(new Student(matrikel, "V" + id, "N" + id, new Date(), "Gort" + id, "heimadr" + id, 2));
			matrikel++;
		}
				
		return studenten;
	}

	
	


}
