package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Examination implements CaseComponent {
	
	
	

	private String sharp_abdominal_pain, sharp_chest_pain, nausea, vomiting, difficulty_in_swallowing,
	heartburn, vomiting_blood, upper_abdominal_pain, back_pain, burning_abdominal_pain, cough, diarrhea;
	
	
	/*fatigue, ache_all_overfatigue, ache_all_over, weakness, shortness_of_breath, loss_of_appetite,
	blood_in_stool, jaundice , fever, headache, regurgitation, stomach_bloating, arm_swelling, elbow_swelling,
	rectal_bleeding, lower_abdominal_pain, melena, swollen_lymph_nodes, discharge_in_stools, pain_of_the_anus, 
	joint_aches, itching,  dark_colored_urine, light_colored_stools, abdominal_discomfort, abdominal_swelling;
	*/
	private String disease;
	
	
	
	
	@Override
	public String toString() {
		return "Examination [sharp_abdominal_pain=" + sharp_abdominal_pain + ", sharp_chest_pain=" + sharp_chest_pain
				+ ", nausea=" + nausea + ", vomiting=" + vomiting + ", difficulty_in_swallowing="
				+ difficulty_in_swallowing + ", heartburn=" + heartburn + ", vomiting_blood=" + vomiting_blood
				+ ", upper_abdominal_pain=" + upper_abdominal_pain + ", back_pain=" + back_pain
				+ ", burning_abdominal_pain=" + burning_abdominal_pain + ", cough=" + cough + ", diarrhea=" + diarrhea
				+ ", disease=" + disease + "]";
	}
	public Examination() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id",this.getClass());
	}
	
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getSharp_abdominal_pain() {
		return sharp_abdominal_pain;
	}
	public void setSharp_abdominal_pain(String sharp_abdominal_pain) {
		this.sharp_abdominal_pain = sharp_abdominal_pain;
	}
	public String getSharp_chest_pain() {
		return sharp_chest_pain;
	}
	public void setSharp_chest_pain(String sharp_chest_pain) {
		this.sharp_chest_pain = sharp_chest_pain;
	}
	public String getNausea() {
		return nausea;
	}
	public void setNausea(String nausea) {
		this.nausea = nausea;
	}
	public String getVomiting() {
		return vomiting;
	}
	public void setVomiting(String vomiting) {
		this.vomiting = vomiting;
	}
	public String getDifficulty_in_swallowing() {
		return difficulty_in_swallowing;
	}
	public void setDifficulty_in_swallowing(String difficulty_in_swallowing) {
		this.difficulty_in_swallowing = difficulty_in_swallowing;
	}
	public String getHeartburn() {
		return heartburn;
	}
	public void setHeartburn(String heartburn) {
		this.heartburn = heartburn;
	}
	public String getVomiting_blood() {
		return vomiting_blood;
	}
	public void setVomiting_blood(String vomiting_blood) {
		this.vomiting_blood = vomiting_blood;
	}
	public String getUpper_abdominal_pain() {
		return upper_abdominal_pain;
	}
	public void setUpper_abdominal_pain(String upper_abdominal_pain) {
		this.upper_abdominal_pain = upper_abdominal_pain;
	}
	public String getBack_pain() {
		return back_pain;
	}
	public void setBack_pain(String back_pain) {
		this.back_pain = back_pain;
	}
	public String getBurning_abdominal_pain() {
		return burning_abdominal_pain;
	}
	public void setBurning_abdominal_pain(String burning_abdominal_pain) {
		this.burning_abdominal_pain = burning_abdominal_pain;
	}
	public String getCough() {
		return cough;
	}
	public void setCough(String cough) {
		this.cough = cough;
	}
	public String getDiarrhea() {
		return diarrhea;
	}
	public void setDiarrhea(String diarrhea) {
		this.diarrhea = diarrhea;
	}

	
}


