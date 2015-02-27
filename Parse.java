package irintegratedproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class parse {
public static ArrayList<docinfo> info = new ArrayList<docinfo>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//File file= new File("C:\\Users\\Sushen\\Desktop\\Python extractor\\icwsm09stories-2.1\\icwsm09stories.xml"); 
                    for(int i = 1; i < 700000 ; i++){
                        System.out.println(i);
                        //File newfile = new File("C:/DIVYA/CompleteFile/"+Integer.toString(id));
			File file = new File("C:/DIVYA/CompleteFile/"+Integer.toString(i)); 
			String line;
			Document doc = Jsoup.parse(file, "UTF-8");
                        Element eletitle = doc.select("title").first();
                        Element elelink = doc.select("a").first();
                        String title = eletitle.text();
                        String link = elelink.text();
                        info.add(new docinfo(title,link));
			Document doc1 =Jsoup.parse(doc.text());
                        //File file1 = new File("C:/DIVYA/IRindex/"+Integer.toString(i)+".txt");
                         File file1 = new File("C:/DIVYA/IRindex/"+Integer.toString(i));
                if (!file1.exists()) {
        file1.createNewFile();
                 System.out.println("File created");
                continue;
            }
            
               BufferedWriter writer = new BufferedWriter(new FileWriter("C:/DIVYA/IRindex/"+Integer.toString(i),true));
        
    writer.write (doc1.text());
    writer.write("\n");
    writer.flush();
    //writer.close();          
                        }
                    System.out.println("docinfo of doc 10 = "+info.get(10-1).title+ " : "+info.get(10-1).link);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
class docinfo
{
String title;
String link;

public docinfo(String title1,String link1)
{
    title = title1;
    link = link1;
}

}
