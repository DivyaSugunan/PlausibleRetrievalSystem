/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irintegratedproject;

import java.io.FileInputStream;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

/*
Example code from Wicked Cool Java (No Starch Press)
Copyright (C) 2005 Brian D. Eubanks

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

Note: The LGPL licence can be found online at http://www.gnu.org

*/
/**
 * This class looks up word senses in JWordnet, and finds holonyms.
 */
/*public class HelloWordUp {

	public static void main(String[] args) 
	throws JWNLException {
		configureJWordNet();
		Dictionary dictionary = Dictionary.getInstance();
		String term="dog";
                //System.out.println("test");
		IndexWord word = dictionary.lookupIndexWord(POS.NOUN,term); 
		if(word==null)
		word = dictionary.lookupIndexWord(POS.VERB,term); 
		/*if(word==null)
			word = dictionary.lookupIndexWord(POS.ADJECTIVE,term); 
		if(word==null)
			word = dictionary.lookupIndexWord(POS.ADVERB,term);*/ 
		/*if(word==null)
			return;
		System.out.println("Synonyms of the word "+term+" : ");
		//Synset[] senses = word.getSenses();
		Synset[] senses = word.getSenses();
		for (int i=0; i<senses.length; i++) {
		  Synset sense = senses[i];
		  //System.out.println((i+1) + ". " + sense.getGloss());
		  Pointer[] hyper = sense.getPointers(PointerType.HYPERNYM);
		  Pointer[] hypo = sense.getPointers(PointerType.HYPONYM);
		  //System.out.println("Hyper");
		  for (int j=0; j<hyper.length; j++) {
		    Synset synset = (Synset) (hyper[j].getTarget());
		    Word synsetWord = synset.getWord(0);
		    System.out.println("hyper :"+synsetWord.getLemma().replace("_"," "));
		  }
		  //System.out.println("Hypo");
		  for (int j=0; j<hypo.length; j++) {
			    Synset synset = (Synset) (hypo[j].getTarget());
			    Word synsetWord = synset.getWord(0);
			    System.out.println("hypo :"+synsetWord.getLemma().replace("_"," "));
			  }
		}
	}
	
	public static void configureJWordNet() {
		// WARNING: This still does not work in Java 5!!!
		try {
			// initialize JWNL (this must be done before JWNL can be used)
			// See the JWordnet documentation for details on the properties file
			JWNL.initialize(new FileInputStream("C:\\Users\\Divya\\Desktop\\jwnl14-rc2\\jwnl14-rc2\\config\\file_properties.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
}*/





public class OntologyClass {
public static String result = "";
	public static String ontology(String term)throws JWNLException {
            
		configureJWordNet();
		Dictionary dictionary = Dictionary.getInstance();
		//String term= "beautiful";
                //System.out.println("test");
                result = result+ " "+term;
		IndexWord word = dictionary.lookupIndexWord(POS.NOUN,term); 
                
		if(word==null)
		word = dictionary.lookupIndexWord(POS.VERB,term); 
		/*if(word==null)
			word = dictionary.lookupIndexWord(POS.ADJECTIVE,term); 
		if(word==null)
			word = dictionary.lookupIndexWord(POS.ADVERB,term);*/ 
		if(word!=null){
			//return;
		System.out.println("Synonyms of the word "+term+" : ");
		//Synset[] senses = word.getSenses();
		Synset[] senses = word.getSenses();
		for (int i=0; i<senses.length; i++) {
		  Synset sense = senses[i];
		  //System.out.println((i+1) + ". " + sense.getGloss());
		  Pointer[] hyper = sense.getPointers(PointerType.HYPERNYM);
		  Pointer[] hypo = sense.getPointers(PointerType.HYPONYM);
		  //System.out.println("Hyper");
		  for (int j=0; j<hyper.length; j++) {
		    Synset synset = (Synset) (hyper[j].getTarget());
		    Word synsetWord = synset.getWord(0);
		    System.out.println("hyper :"+synsetWord.getLemma().replace("_"," "));
                    result = result + " "+synsetWord.getLemma().replace("_"," ");
		  }
		  //System.out.println("Hypo");
		  for (int j=0; j<hypo.length; j++) {
			    Synset synset = (Synset) (hypo[j].getTarget());
			    Word synsetWord = synset.getWord(0);
			    System.out.println("hypo :"+synsetWord.getLemma().replace("_"," "));
                            result =  result + " "+ synsetWord.getLemma().replace("_"," ");
			  }
		}
                }
                return result;
	}
       
	
	public static void configureJWordNet() {
		// WARNING: This still does not work in Java 5!!!
		try {
			// initialize JWNL (this must be done before JWNL can be used)
			// See the JWordnet documentation for details on the properties file
			JWNL.initialize(new FileInputStream("C:\\Users\\Divya\\Desktop\\jwnl14-rc2\\jwnl14-rc2\\config\\file_properties.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
}
