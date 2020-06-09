package model;

import java.util.HashMap;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Medication implements CaseComponent {

	private String disease;
	private HashMap<String, Integer> anamnesis;
	private String medications;
	
	private String binAnamnesis;
	
	public Medication() {
		this.setAnamnesis();
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
	


	@Override
	public String toString() {
		return  disease + " " + medications;
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

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getBinAnamnesis() {
		return binAnamnesis;
	}

	public void setBinAnamnesis(String binAnamnesis) {
		this.binAnamnesis = binAnamnesis;
	}

	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id",this.getClass());
	}
	
	
}
