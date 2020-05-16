package score;
import java.lang.*;
import index.Index;
import io.DocSource;


public class TFIDFScoringFun implements TermScoringFun{
    public double N;
    public Index i;
    
    @Override
	public void init(Index s) {
            
            DocSource ds = s.getDocSource();
            this.N = (double) ds.getNumDocs();
            this.i = s;
		// Do nothing here for TF... no need to compute IDF so no need to keep track of the Index.
		// Note that for TFIDF, the Index gives you access to the document frequency of any term!
	}

    @Override
	public double scoreToken(String term, int freq) {
            if (freq<=0)
                freq=1;

            double TFIDF = ((double)(1+Math.log10((double)freq)))*Math.log10(((double)N/((double)i.getDocumentFreq(term))));
		return TFIDF; // Notes: (1) This is a non-log TF; (2) Ignore term since IDF irrelevant
	}
    
}


