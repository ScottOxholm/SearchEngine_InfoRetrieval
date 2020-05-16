/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;
import index.DocScore;
import index.Index;


/**
 *
 * @author oxholmsc
 */
public class SortedDocScore extends DocScore implements Comparable{

    public SortedDocScore(double score, int doc_id, String content) {
        super(score, doc_id, content);
    }
 
    @Override 
    public int compareTo(Object o) { //compare alpha 

        if(o instanceof DocScore) {
            DocScore doc = (DocScore) o; 

            if (this.getScore()==doc.getScore()) {
                if (this._content.compareTo(doc._content)==0)
                    return 0;
                else if (this._content.compareTo(doc._content)<0)
                    return -1;
           
        }
            else if (this.getScore()>doc.getScore())
                return -1;
        }
        else{
            System.out.println("Needs to be an instance of DocScore");
        }
        return 1;
    }
}
    
    


