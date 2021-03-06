package cbr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sun.net.httpserver.Authenticator.Result;

import connector.CsvConnector;
import connector.CsvConnectorResults;
import model.Examination2;
import model.Results;
import similarity.TableSimilarity;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.connector.PlainTextConnector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.extensions.recommendation.casesDisplay.UserChoice;
import ucm.gaia.jcolibri.extensions.recommendation.conditionals.BuyOrQuit;
import ucm.gaia.jcolibri.extensions.recommendation.conditionals.ContinueOrFinish;
import ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.CriticalUserChoice;
import ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.CritiqueOption;
import ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.DisplayCasesTableWithCritiquesMethod;
import ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.queryElicitation.MoreLikeThis;
import ucm.gaia.jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.FilterBasedRetrievalMethod;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.FilterConfig;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.predicates.NotEqual;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryLess;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryMore;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Table;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaMoreIsBetter;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;
import ucm.gaia.jcolibri.util.FileIO;

public class CbrApplication implements StandardCBRApplication {
	
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */
	private static ArrayList<String> list = new ArrayList<String>();
	
	public void configure() throws ExecutionException {
		_connector =  new CsvConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
	
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		
		simConfig.addMapping(new Attribute("age", Examination2.class), new Interval(15));
		simConfig.addMapping(new Attribute("sex", Examination2.class), new EqualsStringIgnoreCase());
		
		simConfig.addMapping(new Attribute("binSymptoms", Examination2.class), new NotXOR());

		simConfig.addMapping(new Attribute("binAnamnesis", Examination2.class), new NotXOR());

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
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");
		list.clear();
		for (RetrievalResult nse : eval) {
			System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
			String description = nse.get_case().getDescription().toString();
			//String[] tests= description.split("tests")[1].substring(1).split(",");
			System.out.println("OVDE CE BITI ISPISANI TESTOVI SA PROVENTOM");
			String[] tests= description.split("=")[1].split(",");
			for (String test : tests) {
				if (nse.getEval() > 0.5) {
					DecimalFormat df = new DecimalFormat("##.##");
					System.out.println();
					list.add(test + "  ( For "  +description.split("=")[0]+ " disease. Similarity with this disease is " + df.format(nse.getEval()*100) + "% )");
				//	System.out.println(test + "  ( For "  +description.split("=")[0]+ " disease with " + df.format(nse.getEval()*100) + "% )");
				} 
			}
		}
	}

	public void postCycle() throws ExecutionException {
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for (CBRCase c: cases) {
			System.out.println(c.getDescription());
		}
		return _caseBase;
	}

	public static ArrayList<String> main(ArrayList<String> anamnesis, ArrayList<String> symptoms, String age, String sex) {
		StandardCBRApplication recommender = new CbrApplication();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();
			

			Examination2 examination = new Examination2();
			examination.setAge(Integer.parseInt(age));
			examination.setSex(sex);
			String [] symp = symptoms.toArray(new String[0]);
			String [] anam= anamnesis.toArray(new String[0]);
			examination.createBinSymp(symp);
			examination.createBinAnam(anam);
	

			
			query.setDescription( examination );

			recommender.cycle(query);

			recommender.postCycle();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}