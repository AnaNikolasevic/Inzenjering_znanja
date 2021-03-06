package connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import model.Examination2;
import model.Medication;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnectorMedication implements Connector {
	
	private static String disease;

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/medication.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.trim().split(";");

				CBRCase cbrCase = new CBRCase();
				
				if (!values[0].equals(disease))
					continue;

				Medication medication = new Medication();
				medication.setDisease(values[0]);
				
				String[] anam = values[1].split(",");
				medication.createBinAnam(anam);
				
				medication.setMedications(values[2].replaceAll("\\s+",""));
				
				cbrCase.setDescription(medication);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}
	
	public String createBinValue() {
		
		HashMap<String, Integer> anam = new HashMap<String, Integer>();		
		return null;
	}
	
	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {	
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
		
	}

	public static String getDisease() {
		return disease;
	}

	public static void setDisease(String disease) {
		CsvConnectorMedication.disease = disease;
	}
	

}
