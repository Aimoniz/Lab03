package it.polito.tdp.spellchecker.model;

import java.util.*;
import java.io.*;

public class Dictionary {
	private List<String> dizionario=new ArrayList<String>();
	
	private List<RichWord> errate=new ArrayList<RichWord>();
	
	public void LoadDictionary(String language) {
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			dizionario.add(word.toUpperCase());
			}
			br.close();
			 } catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		for(String temp:inputTextList) {
			if(dizionario.contains(temp)) {
				RichWord re=new RichWord(temp,true);
				errate.add(re);
			}
			else {
				RichWord r=new RichWord(temp,false);
				errate.add(r);
			}
		}
		return errate;
	}
	

}
