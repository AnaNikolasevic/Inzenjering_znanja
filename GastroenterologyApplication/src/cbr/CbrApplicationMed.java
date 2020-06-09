package cbr;

import java.util.ArrayList;
import java.util.Collection;

import connector.CsvConnector;
import connector.CsvConnectorMedication;
import model.Examination2;
import model.Medication;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

public class CbrApplicationMed  implements StandardCBRApplication  {
	
	Connector _connectorM;  /** Connector object */
	CBRCaseBase _caseBaseM;  /** CaseBase object */

	NNConfig simConfigM;  /** KNN configuration */
	
	private static ArrayList<String> output = new ArrayList<String>();
	private static ArrayList<String> medications = new ArrayList<>();
	
	
	public void configure() throws ExecutionException {
		
		 _connectorM =  new CsvConnectorMedication();
			
			_caseBaseM = new LinealCaseBase();
			
			simConfigM = new NNConfig();
			simConfigM.setDescriptionSimFunction(new Average());  
			
			simConfigM.addMapping(new Attribute("disease", Medication.class), new EqualsStringIgnoreCase());
			simConfigM.addMapping(new Attribute("binAnamnesis", Medication.class), new NotXOR());
		
	}
	
	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBaseM.getCases(), query, simConfigM);
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval) {
			output.add(nse.get_case().getDescription().toString());
			//System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
		}
	}

	public void postCycle() throws ExecutionException {
		
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		
		_caseBaseM.init(_connectorM);
		java.util.Collection<CBRCase> cases = _caseBaseM.getCases();
		for (CBRCase c: cases)
			System.out.println(c.getDescription());
		
		return _caseBaseM;
	}
	
	public static ArrayList<String> main(Medication medication) {
		StandardCBRApplication recommenderMedication = new CbrApplicationMed();
		
		try {
			
			recommenderMedication.configure();
			recommenderMedication.preCycle();

			CBRQuery query1 = new CBRQuery();
		//	Medication medication = new Medication();
		//	medication.setDisease("hiatal_hernia");
		//	String [] a= { "overweight"};
			
			//medication.createBinAnam(a);
	
			query1.setDescription(medication);

			recommenderMedication.cycle(query1);
			
			
			
		/*	for (String s : output) {
				String[] values = s.strip().split(",");
				if (values[0].equals(medication.getDisease())){
					medications.add(values[1]);
				}
			}
*/
			recommenderMedication.postCycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}	

}
