package cbr;

import java.util.HashMap;

import com.sun.jdi.Value;

import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class SimilarityHashMap implements LocalSimilarityFunction {

	@Override
	public double compute(Object o1, Object o2) throws NoApplicableSimilarityFunctionException {
		// TODO Auto-generated method stub
		if ((o1 == null) || (o2 == null))
			return 0;

		HashMap<String, String> resultsOfTests1 = new HashMap<String, String>();
		resultsOfTests1 = (HashMap<String, String>) o1;
		HashMap<String, String> resultsOfTests2 = new HashMap<String, String>();
		resultsOfTests2 = (HashMap<String, String>) o2;	
	
		int number=0;
		for (String test1 : resultsOfTests1.keySet()) {
			for (String test2 : resultsOfTests2.keySet()) {
				if(test1.strip().equalsIgnoreCase(test2.strip())) {
					if(resultsOfTests1.get(test1).equals(resultsOfTests2.get(test2))) {
						number++;
					}
				}
			}
		}
		return (double)number/ (double) resultsOfTests1.size();

    }

	
	@Override
	public boolean isApplicable(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}

