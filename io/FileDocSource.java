/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author ingmatt1
 */
public class FileDocSource extends DocSource{
    ArrayList<File> docs;
    String[] docStrings;
    
    public FileDocSource (String directory){
         docs = FileFinder.GetAllFiles(directory);
    }
        
    @Override
    public int getNumDocs() {
       return docs.size();
    }

    @Override
    public String getDoc(int id) {
    
        StringBuilder sb = new StringBuilder();
            // initialize
            FileReader fr;
        try {
            
            
            String line = new String(); 

             fr = new FileReader(docs.get(id));
             BufferedReader br = new BufferedReader(fr);
            
            
                while ((line = br.readLine()) != null){
                    sb.append(line + " ");
                }
                sb.deleteCharAt(sb.length()-1);
         
        } catch (FileNotFoundException ex) {
            System.out.println("wrong");
        } catch (IOException ex) {
            System.out.println("wrong");
            
        }
           
          
       
        return sb.toString();
    }
    
}


