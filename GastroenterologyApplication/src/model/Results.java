package model;

import java.util.ArrayList;
import java.util.HashMap;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Results implements CaseComponent {

	HashMap<String, String> resultsOfTests = new HashMap<String, String>();
	private String disease;

	
	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Results(HashMap<String, String> resultsOfTests, String disease) {
		super();
		this.resultsOfTests = resultsOfTests;
		this.disease = disease;
	}


	public HashMap<String, String> getResultsOfTests() {
		return resultsOfTests;
	}


	@Override
	public String toString() {
		return  disease;
	}


	public void setResultsOfTests(HashMap<String, String> resultsOfTests) {
		this.resultsOfTests = resultsOfTests;
	}


	public String getDisease() {
		return disease;
	}


	public void setDisease(String disease) {
		this.disease = disease;
	}


	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
