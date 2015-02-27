package irintegratedproject;

/* Sushen Kumar Manchukanti
 * Sample code to parse data 
 * and put it into separate files.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
                        //BufferedWriter writer = new BufferedWriter(new FileWriter("C:/DIVYA/IRindex/title_url.txt",true));
			File file= new File("C:/DIVYA/icwsm09stories.xml"); 
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			String line;
			String line1;
                        int i=0;
			ArrayList<String> list = new ArrayList<String>();
			int id=1;
                        //int checker = 0;
			//worked for first 1000 entries.
			while ((line = reader.readLine()) != null && id <=700000)   // <<<<----change number or take off the number condition !!!  
			{
                            //checker++;
				System.out.println(id);
			if(line.startsWith("<title>"))	//title added to list
				{
					list.add(line);
				}
			if(line.startsWith("<link>"))	//link added to list
				{
				line=line.replace("<link>","<a>");
                                line=line.replace("</link>","</a>");
                                list.add(line);
				}
			if(line.startsWith("<description>"))	//description added to list
			{
				list.add(line);
				while ((line1 = reader.readLine()) != null && !line1.startsWith("</description>")) // description may be more than one line.
					list.add(line1);
				list.add("</description>");
				File newfile = new File("C:/DIVYA/CompleteFile/"+Integer.toString(id));  //create a file with the name as id
				write(newfile,list);	//write all contents of array list into file
				++id;					//increment the id
				list.clear();			//clear the list.
			}
			}
			reader.close();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("End");			//end of operation.
	}
	public static void write(File file, ArrayList<String> list)
	{
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < list.size(); i++) {
				out.write(list.get(i));	
		    	out.newLine();
			}
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
