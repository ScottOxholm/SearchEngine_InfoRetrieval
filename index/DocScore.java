package index;

/** Class to represent search results, i.e., a search result is represented as an instance of this class.
 *  The class' instance variables are the document's score, the docuemnt's ID
 *  and the content of the document (stored as a String).
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public abstract class DocScore {

	protected double _score;
	protected int    _docID;
	protected String _content;
	
	public DocScore(double score, int doc_id, String content) {
		_score = score;
		_docID = doc_id;
		_content = content;
	}

	public DocScore(DocScore ds) {
		_score = ds._score;
		_content = ds._content;
	}

	public double getScore() {
		return _score;
	}

	public int getDocID() {
		return _docID;
	}
	
	public String getContent() {
		return _content;
	}
	
	@Override
	public String toString() {
		return "Score=" + _score + ": " + _content.substring(0, Math.min(100, _content.length()));
	}
	
}
