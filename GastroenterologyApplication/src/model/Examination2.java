package model;

import java.util.HashMap;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Examination2 implements CaseComponent {
	
	private int age;
	private String sex;
	private HashMap<String, Integer> symptoms;
	private HashMap<String, Integer> anamnesis;
	//ovde cemmo vrednosti iz HashMap-e cuvati kao binarnu vrednost
	private String binSymptoms;
	private String binAnamnesis;
	private String disease;
	private String tests;
	
	public Examination2() {
		//prilikom kreiranja pregleda podesavamo sve simptome na 0
		this.setSymptoms();
		this.setAnamnesis();
	}
	private void setSymptoms () {
		this.symptoms = new HashMap<String, Integer>();
		this.symptoms.put("abdominal_discomfort", 0);
		this.symptoms.put(" abdominal_swelling", 0);
		this.symptoms.put("ache_all_over", 0);
		this.symptoms.put("arm_swelling", 0);
		this.symptoms.put("back_pain", 0);
		this.symptoms.put("blood_in_stool", 0);
		this.symptoms.put("burning_abdominal_pain", 0);
		this.symptoms.put("cough", 0);
		this.symptoms.put("dark_colored_urine", 0);
		this.symptoms.put("diarrhea", 0);
		this.symptoms.put("difficulty_in_swallowing", 0);
		this.symptoms.put("discharge_in_stools", 0);
		this.symptoms.put("elbow_swelling", 0);
		this.symptoms.put("fatigue", 0);
		this.symptoms.put("fever", 0);
		this.symptoms.put("headache", 0);
		this.symptoms.put("heartburn", 0);
		this.symptoms.put("heartburn", 0);
		this.symptoms.put("itching", 0);
		this.symptoms.put("jaundice", 0);
		this.symptoms.put("joint_aches", 0);
		this.symptoms.put("light_colored_stools", 0);
		this.symptoms.put("loss_of_appetite", 0);
		this.symptoms.put("loss_of_weight", 0);
		this.symptoms.put("lower_abdominal_pain", 0);
		this.symptoms.put("melena", 0);
		this.symptoms.put("nausea", 0);
		this.symptoms.put("pain_of_the_anus", 0);
		this.symptoms.put("rectal_bleeding", 0);
		this.symptoms.put("regurgitation", 0);
		this.symptoms.put("sharp_chest_pain", 0);
		this.symptoms.put("sharp_abdominal_pain", 0);
		this.symptoms.put("shortness_of_breath", 0);
		this.symptoms.put("stomach_bloating", 0);
		this.symptoms.put("swollen_lymph_nodes", 0);
		this.symptoms.put("upper_abdominal_pain", 0);
		this.symptoms.put("vomiting", 0);
		this.symptoms.put("vomiting_blood", 0);
		this.symptoms.put("weakness", 0);		
	}
	public void setAnamnesis() {
		this.anamnesis = new HashMap<String, Integer>();
		this.anamnesis.put("active_smoking", 0);
		this.anamnesis.put("abusing_alchohol", 0);
		this.anamnesis.put("antibiotics_abuse", 0);
		this.anamnesis.put("autoimmune_hepatitis", 0);
		this.anamnesis.put("active_smoking", 0);
		this.anamnesis.put("barretts_esophagus", 0);
		this.anamnesis.put("BMI_over_35", 0);
		this.anamnesis.put("born_with_larger_hiatus", 0);
		this.anamnesis.put("chronic_gastritis", 0);
		this.anamnesis.put("crohns_disease", 0);
		this.anamnesis.put("delayed_stomach_emptying", 0);
		this.anamnesis.put("diabetes", 0);
		this.anamnesis.put("diet_high_in_salty_foods", 0);
		this.anamnesis.put("diet_high_in_smoked_foods", 0);
		this.anamnesis.put("diet_low_in_fruits", 0);
		this.anamnesis.put("diet_low_in_vegetables", 0);
		this.anamnesis.put("drug_abuse", 0);
		this.anamnesis.put("family_history_of_crohn_disease", 0);
		this.anamnesis.put("family_history_of_gallstones", 0);
		this.anamnesis.put("family_history_of_liver_disease", 0);
		this.anamnesis.put("family_history_of_pancreatic_cancer", 0);
		this.anamnesis.put("family_history_of_stomach_cancer", 0);
		this.anamnesis.put("genetic_predisposition_gastritis", 0);
		this.anamnesis.put("gerb", 0);
		this.anamnesis.put("h_pylori_infection", 0);
		this.anamnesis.put("heavy_lifting", 0);
		this.anamnesis.put("hepatitis_A", 0);
		this.anamnesis.put("hepatitis_B", 0);
		this.anamnesis.put("hepatitis_C", 0);
		this.anamnesis.put("hepatitis_C_risk_group", 0);
		this.anamnesis.put("herbal_supplements_abuse", 0);
		this.anamnesis.put("hiatal_hernia", 0);
		this.anamnesis.put("high_blood_cholesterol", 0);
		this.anamnesis.put("hiv_positive", 0);
		this.anamnesis.put("ibs", 0);
		this.anamnesis.put("inherited_hepatitis", 0);
		this.anamnesis.put("long_term_stomach_inflammation", 0);
		this.anamnesis.put("nsaid_abuse", 0);
		this.anamnesis.put("nt0ba", 0);
		this.anamnesis.put("overweight", 0);
		this.anamnesis.put("pernicious_anaemia", 0);
		this.anamnesis.put("persistent_vomiting", 0);
		this.anamnesis.put("persistent_coughing", 0);
		this.anamnesis.put("physical_strain", 0);
		this.anamnesis.put("pregnancy", 0);
		this.anamnesis.put("previous_stomach_surgery", 0);
		this.anamnesis.put("steroids_abuse", 0);
		this.anamnesis.put("stomach_polyps", 0);
		this.anamnesis.put("straining_during_bowel_movements", 0);
		this.anamnesis.put("stress", 0);
		this.anamnesis.put("unprotected_sex", 0);
		this.anamnesis.put("vitamin_supplements_abuse", 0);

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
		//System.out.println(this.symptoms.toString());
		//System.out.println(this.binSymptoms);
		
		
	}
	public void createBinAnam(String [] anam) {
		int i;
		for (i = 0; i < anam.length; i++) { 
			 this.anamnesis.replace(anam[i], 1);
        } 
		
		this.binAnamnesis = "";

		for (Integer value : this.anamnesis.values()) {
			  this.binAnamnesis += String.valueOf(value);
			}
		//System.out.println(this.anamnesis.toString());
		//System.out.println(this.binAnamnesis);
		
		
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


	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
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
	


	public HashMap<String, Integer> getAnamnesis() {
		return anamnesis;
	}
	public void setAnamnesis(HashMap<String, Integer> anamnesis) {
		this.anamnesis = anamnesis;
	}
	public String getBinAnamnesis() {
		return binAnamnesis;
	}
	public void setBinAnamnesis(String binAnamnesis) {
		this.binAnamnesis = binAnamnesis;
	}
	


	
	@Override
	public String toString() {
		return disease + "=" + tests;
	}
	
	
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return new Attribute("id",this.getClass());
	}
	
	
}
