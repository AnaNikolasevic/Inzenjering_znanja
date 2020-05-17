package connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import model.Examination;
import model.Examination2;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnector implements Connector {
	
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/anamnesis.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

				Examination2 examination = new Examination2();
				
				examination.setDisease(values[0]);
				examination.setAge(Integer.parseInt(values[1]));
				examination.setSex(values[2]);
				
				
				String[] symp = values[3].split(",");
				examination.createBinSymp(symp);
				
				
				/*examination.setDisease(values[0]);
				examination.setSharp_abdominal_pain(values[1]);
				examination.setSharp_chest_pain(values[2]);
				examination.setNausea(values[3]);
				examination.setVomiting(values[4]);
				examination.setDifficulty_in_swallowing(values[5]);
				examination.setHeartburn(values[6]);
				examination.setVomiting_blood(values[7]);
				examination.setUpper_abdominal_pain(values[8]);
				examination.setBack_pain(values[9]);
				examination.setBurning_abdominal_pain(values[10]);
				examination.setCough(values[11]);
				examination.setDiarrhea(values[12]);*/
				// TODO

				
				cbrCase.setDescription(examination);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}
	//u ovoj metodi postavljamo sve vrednosti simtoma na nula
	public String createBinValue() {
	
		HashMap<String, Integer> symp = new HashMap<String, Integer>();
		
		
		return null;
	}
	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
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

}