package irintegratedproject;



import java.io.BufferedReader;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.w3c.dom.NodeList;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class SimpleSearcher {
     public static QueryObject qo = new QueryObject();
      public static ArrayList<docinfo> info = new ArrayList<docinfo>();
      public static ArrayList<QueryOutputObject> outputList;// = new ArrayList<QueryOutputObject>();
      
	/*public static void main(String[] args) throws Exception {
        //File indexDir = new File("C:/DIVYA/index");
        
       // String query = qo.query;
        int hits = 100;
        SimpleSearcher searcher = new SimpleSearcher();
       
        //qo.query = "america";
        searcher.searchIndex(qo);       
    }*/
     
        public static ArrayList<QueryOutputObject> searchIndex(QueryObject qo) throws Exception{
         int maxHits = 100;
         outputList = new ArrayList<QueryOutputObject>();
          String  result = null;
       //System.out.println("########## " + qo.getQueryid() +"##################3");
        File indexDir = new File("C:/DIVYA/index");
        Directory directory = FSDirectory.open(indexDir);
        IndexSearcher searcher = new IndexSearcher(directory);
        QueryParser parser = new QueryParser(Version.LUCENE_30,
             "contents", new SimpleAnalyzer());
       String  queryStr = qo.getQuery();
       StringTokenizer token = new StringTokenizer(queryStr);
        while(token.hasMoreTokens()){
            String word = token.nextToken();
            System.out.println("Word"+word);
         result =   OntologyClass.ontology(word);
        }
        System.out.println("Here is the result" + result);
        Query query = parser.parse(queryStr);
        TopDocs topDocs = searcher.search(query, maxHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        System.out.println("HitsLength" + hits.length);
      
        for (int i = 0; i < hits.length; i++) {
           
        QueryOutputObject outputObj = new QueryOutputObject();
            int docId = hits[i].doc;
            Document d = searcher.doc(docId); 
            String titleInput1 = d.get("filename").replace(".txt", "");
            String titleInput = titleInput1.replace("C:\\DIVYA\\IRindex\\", "");
        File file = new File("C:/DIVYA/CompleteFile/"+titleInput); 
           org.jsoup.nodes.Document doc = Jsoup.parse(file, "UTF-8");
                        Element eletitle = doc.select("title").first();
                        Element elelink = doc.select("a").first();
                        String title = eletitle.text();
                        String link = elelink.text();
                        info.add(new docinfo(title,link)); 
         
                        
        /*   System.out.println("FileListing"+ titleInput);
    Path FROM = Paths.get("C:\\DIVYA\\CompleteFile\\"+titleInput);
   
     File file1 = new File("C:\\Users\\Divya\\Desktop\\Semester4\\FilesTo\\" +titleInput);
    if (!file1.exists()) {
        file1.createNewFile();
                 System.out.println("File created");
                continue;
            }
    Path TO = Paths.get("C:\\Users\\Divya\\Desktop\\Semester4\\FilesTo\\" +titleInput);
    CopyOption[] options = new CopyOption[]{
      StandardCopyOption.REPLACE_EXISTING,
      StandardCopyOption.COPY_ATTRIBUTES
    }; 
    Files.copy(FROM, TO, options);             
                        
                  */ 
       /*   System.out.println("FileListing"+ titleInput);  
                        File file3 = new File("C:\\Users\\Divya\\Desktop\\Semester4\\FilesTo\\"+titleInput);
    if (!file3.exists()) {
        file3.createNewFile();
                 System.out.println("File created");
               // continue;
            }
         System.out.println("FileListing"+ titleInput);
    Path FROM = Paths.get("C:\\DIVYA\\CompleteFile\\"+titleInput);
   
     
    Path TO = Paths.get("C:\\Users/Divya\\Desktop\\Semester4\\FilesTo\\" + titleInput);
    CopyOption[] options = new CopyOption[]{
      StandardCopyOption.REPLACE_EXISTING,
      StandardCopyOption.COPY_ATTRIBUTES
    }; 
    Files.copy(FROM, TO, options);            
                        
                    */   
                    
                        
                        
                        
           outputObj.queryID = qo.getQueryid();
           outputObj.title = title;
           outputObj.score = hits[i].score;
           outputObj.url = link;
           outputObj.docID = Integer.parseInt(d.get("filename").replace("C:\\DIVYA\\IRindex\\", ""));
           outputObj.rank = i+1;
           outputList.add(outputObj);
            
        }
 //for(int i=0;i<outputList.size();i++)
      //  {
           //System.out.println("Rank"+outputList.get(i).rank+" "+outputList.get(i).title+" | "+outputList.get(i).docID);
         // }
        return(outputList);
  }
}

	
	
