package cbr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import connector.CsvConnectorResults;
import model.Examination2;
import model.Results;
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

public class CbrApplicationResults implements StandardCBRApplication {
	
	Connector _connectorR;  /** Connector object */
	CBRCaseBase _caseBaseR;  /** CaseBase object */

	NNConfig simConfigR;  /** KNN configuration */
	
	private static ArrayList<String> output = new ArrayList<String>();
	
	public void configure() throws ExecutionException {
		_connectorR =  new CsvConnectorResults();
		
		_caseBaseR = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
		
		simConfigR = new NNConfig(); // KNN configuration
		simConfigR.setDescriptionSimFunction(new Average());  // global similarity function = average
		
		// results of tests
		simConfigR.addMapping(new Attribute("resultsOfTests", Results.class), new SimilarityHashMap());


		// simConfig.addMapping(new Attribute("attribute", CaseDescription.class), new Interval(5));
		// TODO

		// Equal - returns 1 if both individuals are equal, otherwise returns 0
		// Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
		// Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
		// EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
		// MaxString - returns a similarity value depending of the biggest substring that belong to both strings
		// EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
		// EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
		// Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
		// TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity) 
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBaseR.getCases(), query, simConfigR);
		eval = SelectCases.selectTopKRR(eval, 5);
		output.clear();
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval) {
			System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
			if (nse.getEval() > 0.5) {
			output.add(nse.get_case().getDescription().toString());
			}
		}
	}

	public void postCycle() throws ExecutionException {
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBaseR.init(_connectorR);
		java.util.Collection<CBRCase> cases = _caseBaseR.getCases();
		for (CBRCase c: cases)
			System.out.println(c.getDescription());
		return _caseBaseR;
	}

	public static ArrayList<String> main(Results results) {
		StandardCBRApplication recommender = new CbrApplicationResults();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();
			//Results results = new Results();
			
		//	HashMap<String, String> resultsOfTests = new HashMap<String, String>();
		//	resultsOfTests.put("cbc", "anemia");
	//		resultsOfTests.put("ast", "high");
		//	resultsOfTests.put("alp", "high");
		//	resultsOfTests.put("alt", "high");

		//	resultsOfTests.put("bilirubin", "high");
		//	resultsOfTests.put("albumin", "low");
//			resultsOfTests.put("cbc", "platelets_low");

		//	resultsOfTests.put("platelets", "low");

			//resultsOfTests.put("ana", "positive");

			//results.setResultsOfTests(resultsOfTests);
			
			query.setDescription( results );
			
			recommender.cycle(query);

			recommender.postCycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

}
