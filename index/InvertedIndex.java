/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;
import index.*;
import tokenizer.*;
import score.*;
import io.*;
import java.util.*;


/**
 *
 * @author oxholmsc
 */
public class InvertedIndex extends Index{
        private HashMap<String, HashMap<Integer, Integer>> termMap;
        private HashMap<String, Integer> totDocFreq;
        int numDocs = this._docSource.getNumDocs();

        
    public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
        super(doc_source, tokenizer, scoring);
        termMap = new HashMap<String, HashMap<Integer, Integer>>();
        totDocFreq = new HashMap<String, Integer>();
    }

    @Override
    public void buildIndex() {
        
        for(int i=0; i<numDocs; i++){
            String document = this._docSource.getDoc(i);
            ArrayList<String> docArray;
            docArray = _tokenizer.tokenize(document);

            for (String s : docArray){      
              if (termMap.keySet().contains(s)) {
                  if (termMap.get(s).get(i)==null) {
                      termMap.get(s).put(i,1);
                       
                  }
                      
                  else {
                      termMap.get(s).put(i, termMap.get(s).get(i)+1);
                      
                  }
              }
              else {
                      HashMap<Integer, Integer> h= new HashMap<>();
                        termMap.put(s, h);
                        termMap.get(s).put(i,1); 
              }
                     
            }    
      
        }
        for (String k : termMap.keySet()){
            HashMap<Integer, Integer> h= new HashMap<>();
            h = termMap.get(k);
            int sum = 0;
            for(int id : h.keySet()){
                if(h.get(id)>0)
                    sum++;
            }
            totDocFreq.put(k,sum);
        }
    }
        
//for each doc get tokenized string
//loop through and add string to hash map
//
    
    
    @Override
    public Integer getDocumentFreq(String term) {
        int i =totDocFreq.get(term); 
        return i;
    }

    @Override
    public ArrayList<DocScore> getSortedSearchResults(String query) { //merger
        double scoreCount=0.0;
        TreeSet<DocScore> docSet = new TreeSet<DocScore>();
        ArrayList<String> q= _tokenizer.tokenize(query);
        //we use counter to keep track of whether or not we have found a score for this or not and want to add it in
        int counter;
        
        for (int i =0; i<numDocs; i++){ 
            counter=0;
            scoreCount=0.0;
            
            for (String s: q) {
                HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
                if(termMap.containsKey(s)){
                    h = termMap.get(s);                
                    if (h.containsKey(i)) {
                        counter++;
                        scoreCount+=_scoring.scoreToken(s, termMap.get(s).get(i));
                    }
                }
            }
            if (counter!=0)
                docSet.add(new SortedDocScore(scoreCount, i, this._docSource.getDoc(i)));
            
        }
        
        return new ArrayList<>(docSet);

    }
    

}
