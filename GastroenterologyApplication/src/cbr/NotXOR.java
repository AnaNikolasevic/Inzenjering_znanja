package cbr;

import java.io.IOException;


import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class NotXOR implements LocalSimilarityFunction {

	@Override
	public double compute(Object o1, Object o2) throws NoApplicableSimilarityFunctionException {
		// TODO Auto-generated method stub
		if ((o1 == null) || (o2 == null))
			return 0;
		if (!(o1 instanceof String))
			throw new NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
		if (!(o2 instanceof String))
			throw new NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());

		String s = (String) o1;
		String key = (String) o2;

		// xor operacija
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++)
		    sb.append((char)(s.charAt(i) ^ key.charAt(i % key.length())));
		String result = sb.toString();	
		
		//prebrojavanje simptoma koji se poklapaju
		int size = 0;
		for (char c : result.toCharArray()) {
			if(c==0) {
				size++;
			}
		}
		return ((double) size/ (double) s.length());

    }

	
	@Override
	public boolean isApplicable(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
