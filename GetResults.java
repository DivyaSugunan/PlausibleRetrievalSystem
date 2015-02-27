package irintegratedproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*; 
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class GetResults extends JFrame {
	static{
		//new File("./Results_QID").mkdir();
		new File("\"C:/Users/Divya/Documents/NetBeansProjects/IRIntegratedProject/Results_QID").mkdir();
	}
	private DefaultTableModel model;
	public void initResults(ArrayList<QueryOutputObject> queryOutputObjects, boolean currentDefaultFlag)
	{ 
		
		JPanel jpanel;
		JScrollPane jscrollpane;
		JTable jtable = null;
		model = new DefaultTableModel();
		String formattedString = "";
		//Object rowData[][]; 	
			
		setTitle("Search Results");
		setSize(600, 600);
		setBackground(Color.gray);
		
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		getContentPane().add(jpanel);
	    System.out.println("######----------#####   flag is : "+ currentDefaultFlag);
		// Create columns names
		//String columnNames[] = { "QUERYID", "RELEVANT RESULTS" };
                //String columnNames[] = { "QUERYID", "DOCID", "TITLE", "URL", "RANK", "SCORE" };
           // DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            //center.setHorizontalAlignment(JLabel.CENTER);
		model.addColumn("QUERYID");
		model.addColumn("DOCID");
		model.addColumn("TITLE");
		model.addColumn("URL");
		if(!currentDefaultFlag){
			model.addColumn("RANK");
			model.addColumn("SCORE");
		}else{
			model.addColumn("RELEVANCE");
			model.addColumn("COMMENT");
		}
		//GUI.defaultFlag
            //int  a = 1;
        jtable = new JTable(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jtable.setDefaultRenderer(String.class, centerRenderer);
        
		// Create some data
		int height = queryOutputObjects.size();
		int counter = 0;
		//String[][] QID_Results = new String[height][6];
               // jtable.getColumnModel().
                 //   getColumn(a).setCellRenderer(center);
		//"./Results_QID"
		//File file = new File("./Results_QID/"+Utility.current_QUERY_ID+".txt");
		File file = new File("C:/Users/Divya/Documents/NetBeansProjects/IRIntegratedProject/Results_QID/Results_Query"+Utility.current_QUERY_ID+".txt");
		try{
			BufferedWriter output = new BufferedWriter(new FileWriter(file));System.out.println("********** height"+ queryOutputObjects.size());
			for(QueryOutputObject qo : queryOutputObjects){
				formattedString = 
					 qo.getQueryID()  + " |	" + qo.getDocID()+ " | "+
					 qo.getTitle()    + " | " + qo.getUrl()  + " | "+ 
				     qo.getRank()     + " | " + qo.getScore() + " | "+ 
					 qo.getRelevance()+ " | " + qo.getComments()+"\n";
				if(counter < 100){
					//System.out.println("########## counter "+ counter);
//					QID_Results[counter][0] = ""+Utility.current_QUERY_ID;
//					QID_Results[counter][1] = ""+qo.getDocID();//formattedString;
//                                        QID_Results[counter][2] = qo.getTitle();
//                                        QID_Results[counter][3] = qo.getUrl();
//                                        QID_Results[counter][4] = ""+qo.getRank();
//                                        QID_Results[counter][5] = ""+qo.getScore();
					if(currentDefaultFlag){
						String[] row = {""+Utility.current_QUERY_ID, ""+qo.getDocID(), ""+qo.getTitle(), ""+qo.getUrl(), ""+qo.getRelevance(), ""+qo.getComments()};
						model.addRow(row);
					}else{
						String[] row = {""+Utility.current_QUERY_ID, ""+qo.getDocID(), ""+qo.getTitle(), ""+qo.getUrl(), ""+qo.getRank(), ""+qo.getScore()};
						model.addRow(row);
					}
					counter++;
					// write to the file too
					output.write(formattedString);
                                        //System.out.println("#### "+formattedString+" ###");
				}
			}
			output.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//jtable = new JTable(QID_Results, columnNames);
		// ScrollPane option to display scrollbars if required
		jtable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		
		jscrollpane = new JScrollPane(jtable);
		jpanel.add(jscrollpane, BorderLayout.CENTER);

		// Position the Frame in Center screen

		setVisible(true);
		System.out.println("Search Results updated and saved..");
	}
}
