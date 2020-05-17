package model;

import java.util.HashMap;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Examination2 implements CaseComponent {
	
	private int age;
	private String sex;
	private HashMap<String, Integer> symptoms;
	//ovde cemmo vrednosti iz HashMap-e cuvati kao binarnu vrednost
	private String binSymptoms;
	private String disease;
	
	public Examination2() {
		//prilikom kreiranja pregleda podesavamo sve simptome na 0
		this.symptoms = new HashMap<String, Integer>();
		this.symptoms.put("nausea", 0);
		this.symptoms.put(" harp_chest_pain", 0);
		this.symptoms.put("difficulty_in_swallowing", 0);
		this.symptoms.put("back_pain", 0);
		this.symptoms.put("burning_abdominal_pain", 0);
		this.symptoms.put("sharp_chest_pain", 0);
		this.symptoms.put("sharp_abdominal_pain", 0);
		this.symptoms.put("vomiting", 0);
		this.symptoms.put("heartburn", 0);
		this.symptoms.put("loss_of_appetite", 0);
		this.symptoms.put("fatigue", 0);
		this.symptoms.put("weakness", 0);
	}

	
	public HashMap<String, Integer> addSymptoms(String [] symp){
		int i;
		for (i = 0; i < symp.length; i++) { 
			 this.symptoms.replace(symp[i], 1);
        } 
		System.out.println(this.symptoms.toString());
		return this.symptoms;
	}
	// vrednosti simptoma koji se nalaze u prosledjenom nizu postavljamo na 1
	//vrednosti mape pretvaramo u string
	public void createBinSymp(String [] symp) {
		int i;
		for (i = 0; i < symp.length; i++) { 
			 this.symptoms.replace(symp[i], 1);
        } 
		
		this.binSymptoms = "";

		for (Integer value : this.symptoms.values()) {
			  this.binSymptoms += String.valueOf(value);
			}
		System.out.println(this.symptoms.toString());
		System.out.println(this.binSymptoms);
		
		
	}
	
	
	public String getBinSymptoms() {
		return binSymptoms;
	}

	public void setBinSymptoms(String binSymptoms) {
		this.binSymptoms = binSymptoms;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	public HashMap<String, Integer> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(HashMap<String, Integer> symptoms) {
		this.symptoms = symptoms;
	}


	public String getDisease() {
		return disease;
	}


	public void setDisease(String disease) {
		this.disease = disease;
	}


	@Override
	public String toString() {
		return "Examination2 [age=" + age + ", sex=" + sex + ", symptoms=" + symptoms + ", disease=" + disease + "]";
	}


	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return new Attribute("id",this.getClass());
	}
	
	
}
