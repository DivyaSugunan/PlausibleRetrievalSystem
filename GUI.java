package irintegratedproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;
import java.util.Properties;

public class GUI extends JFrame implements ActionListener, ItemListener {

    JLabel l1, l2;
    JTextField tf1;
    JButton btn1;
    JCheckBox chkBox1;
    JLabel error_lable;
    String textBoxField_value;
    public static boolean defaultFlag = false;
    //  protected static StanfordCoreNLP pipeline; //needed for lemmatisation

    GUI() throws ParserConfigurationException, SAXException, IOException {
        init();
    }

    public void init() throws ParserConfigurationException, SAXException, IOException {
        setTitle("Plausible retrieval system.. ");
        setVisible(true);
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("plausible retrieval system.. ");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Enter query:");
        tf1 = new JTextField();
        btn1 = new JButton("Submit");
        chkBox1 = new JCheckBox("Default");
        error_lable = new JLabel();
        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        chkBox1.setBounds(150, 100, 250, 50);
        btn1.setBounds(150, 160, 100, 30);
        error_lable.setBounds(80, 80, 400, 30);
        error_lable.setForeground(Color.RED);

        add(l1);
        add(l2);
        add(tf1);
        add(chkBox1);
        add(btn1);
        add(error_lable);

        btn1.addActionListener(this);
        chkBox1.addItemListener(this);
        error_lable.setVisible(false);

        /*
         *  preprocessing of XML parsing
         */
        Utility.parseQueries();

        HashMap<String, String[]> map = Utility.parseOutputXml();
        //for(Map.Entry<String, String[]> entry : map.entrySet()){
        //System.out.println("==== key:"+ entry.getKey()+ ", Relevance:"+ (String) entry.getValue()[0] +", Comment:"+ (String)entry.getValue()[1]);
        //}

//	        Utility.mergeRelevanceCommentsToQueryOutputObjects();

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (chkBox1.isSelected()) {//(e.getItem() == chkBox1) {
            defaultFlag = true;
        } else if (!chkBox1.isSelected()) {
            defaultFlag = false;
        }
    }

    public void actionPerformed(ActionEvent e) {
        OntologyClass.result = "";
        textBoxField_value = tf1.getText().trim();
        System.out.println("########## input : " + textBoxField_value);
        if (textBoxField_value == null && textBoxField_value.isEmpty()) {
            error_lable.setVisible(true);
            error_lable.setText("Query string can't be empty. Please enter the query.");
        } else {
            System.out.println("************************** defaultFlag:" + defaultFlag + " ***************************************");
            error_lable.setVisible(false);
            Utility.setCurrentQID(textBoxField_value, defaultFlag);
            if (defaultFlag) {
                System.out.println("only default querying...");

                // TODO
                // put this object in the function to provide it as i/p to the black box
                // add this method in BlackBox
                // this method also gives back list of 100 QueryOutputObject objects
                //findCurrentQueryObject(tf1.getText().trim());
//	    			List<QueryOutputObject> queryOutputObjects = Utility.getQueryOutputObjects( Utility.findCurrentQueryObject(tf1.getText().trim()) );

                // create QueryObject and call SimpleSearcher.searchIndex(<QueryObject>);
                // handle return object using GetResults
                //ArrayList<QueryOutputObject> queryOutputObjects = SimpleSearcher.searchIndex( Utility.findCurrentQueryObject(tf1.getText().trim()) );
                //System.out.println("****** vvvv  *********   "+ tf1.getText().trim());
                QueryObject inputObject = Utility.findCurrentQueryObject(tf1.getText().trim());
                ArrayList<QueryOutputObject> queryOutputObjects = null;
                try {
                    queryOutputObjects = SimpleSearcher.searchIndex(inputObject);
                    //  System.out.println();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }//Utility.getQueryOutputObjects(inputObject);
                error_lable.setVisible(false);
                if (queryOutputObjects != null && queryOutputObjects.size() > 0) {

                    // TODO
                    Utility.mergeRelevanceCommentsToQueryOutputObjects(queryOutputObjects);
                    GetResults getResults = new GetResults();
                    System.out.println("###########   flag is : "+ defaultFlag);
                    //getResults.initResults(queryOutputObjects, defaultFlag);
                    getResults.initResults(Utility.mergeRelevanceCommentsToQueryOutputObjects(queryOutputObjects), defaultFlag);
                } else {
                    error_lable.setVisible(true);
                    if (inputObject == null) {
                        error_lable.setText("Incorrect Default Query");
                    } else {
                        error_lable.setText("zero hits for given Query ");
                    }
                    // nothing returned with error
                }
            } else {
                System.out.println("reached arbitrary querying...");
                //String[] results = parseXML(tf1.getText());

                // create QueryObject and call SimpleSearcher.searchIndex(<QueryObject>);
                //ArrayList<QueryOutputObject> queryOutputObjects = SimpleSearcher.searchIndex( Utility.ParseArbitraryQuery(tf1.getText().trim()) );
                ArrayList<QueryOutputObject> queryOutputObjects = null;
                try {
                    queryOutputObjects = SimpleSearcher.searchIndex(Utility.ParseArbitraryQuery(tf1.getText().trim()));
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                };
                // handle return object using GetResults
                error_lable.setVisible(false);
                if (queryOutputObjects != null && queryOutputObjects.size() > 0) {
                    GetResults getResults = new GetResults();
                    System.out.println("#####*****######   flag is : "+ defaultFlag);
                    getResults.initResults(queryOutputObjects, defaultFlag);
                } else {
                    error_lable.setVisible(true);
                    error_lable.setText("zero hits for given Query ");
                    // nothing returned with error
                }


                // write code to stopWordRemove, parse, lemma,stemma,
                //send this list to task 3  		

            }
        }
    }

    public static String[] parseXML(String inputText) {
        String line;
        String[] results = {"dogs eat food"};
        String fileLoc = "C:/TPC-C_GAE_Datastore/EclipseWorkspace/IR_GUI/DefaultQueries.txt";

        Scanner fileScanner = new Scanner(fileLoc);
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            if (line.equalsIgnoreCase("<Query>")) {
                line = fileScanner.nextLine();

            }
        }
        fileScanner.close();
        return results;
    }

    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
        new GUI();
    }
}
