import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import cbr.CbrApplication;
import cbr.CbrApplicationMed;
import cbr.CbrApplicationResults;
import model.Examination2;
import model.Medication;
import model.Results;
import sun.nio.cs.StandardCharsets;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;

import java.io.FileWriter;
import com.opencsv.CSVWriter;

public class App {

	private JFrame frame;
	JPanel panel = new JPanel();
	JPanel panelAdditionalTests = new JPanel();
	JPanel panelResults = new JPanel();
	JPanel panelDiagnosis = new JPanel();
	String person;
	public static final StandardCharsets UTF_8 = new StandardCharsets();
	public static JList list_2; 
	public static JList list_1;
	public static JList list;
	public static JList list_0;
	public static JTable table;
	public static JScrollPane scrollPane_2;
	public static JScrollPane scrollPane_1;
	public static JScrollPane scrollPane;
	public static Object[] list1;
	public static DefaultTableModel tableModel;
	private static String[] persAnam;
	public static JFormattedTextField formattedTextField;
	public static JFormattedTextField formattedTextFieldAge;
	public static JFormattedTextField formattedTextFieldSex;
	public static HashMap<String, String> resultsOfTests ;
	public static String[] persSympt ;
	public static ArrayList<String> personalSymptoms;
	public static ArrayList<String> personalAnamnesis ;
	public static String choosenTests;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public App() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		// initializing frame	
		frame = new JFrame();
		frame.setBounds(100, 100, 1006, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//list of personal symptoms/anamnesis/tests (selected from list)
		 personalSymptoms = new ArrayList<String>();
		 personalAnamnesis = new ArrayList<String>();

		//lists from prolog
	    ArrayList<String> listOfSymptoms = consultProlog("symptoms(_, X)");
	    ArrayList<String> listOfAnamnesis = consultProlog("anamnesis(_, X)");

	    // ------------------------------first panel--------------------------
	    
		panelAdditionalTests.setVisible(false);
		
		panel.setBounds(0, 0, 992, 521);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNameAndSurname = new JLabel("Name and surname: ");
		lblNameAndSurname.setBounds(53, 26, 150, 20);
		lblNameAndSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNameAndSurname);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(53, 46, 139, 20);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblAge);
		
		JLabel lblSex = new JLabel("Gender: ");
		lblSex.setBounds(53, 66, 139, 20);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSex);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(201, 26, 258, 19);
		panel.add(formattedTextField);
		
	    formattedTextFieldAge = new JFormattedTextField();
		formattedTextFieldAge.setBounds(201, 46, 200, 19);
		panel.add(formattedTextFieldAge);
		
		formattedTextFieldSex = new JFormattedTextField();
		formattedTextFieldSex.setBounds(201, 66, 200, 19);
		panel.add(formattedTextFieldSex);

		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setBounds(118, 97, 74, 20);
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSymptoms);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 97, 258, 278);
		panel.add(scrollPane);
		
		//setting list of symptoms
		list_0 = new JList();
		scrollPane.setViewportView(list_0);
		list_0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_0.setListData((String[]) listOfSymptoms.toArray(new String[0]));

		JLabel lblNewLabel = new JLabel("Anamnesis");
		lblNewLabel.setBounds(539, 45, 77, 125);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(626, 97, 258, 278);
		panel.add(scrollPane_1);
		
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setListData((String[]) listOfAnamnesis.toArray(new String[0]));
		
		JButton btnFindAdditionalTests = new JButton("Find tests");
		btnFindAdditionalTests.setBounds(441, 435, 220, 29);
		btnFindAdditionalTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = formattedTextField.getText();
				// check if each field is filled
				if ( formattedTextFieldAge.getText().equals("") || formattedTextFieldSex.getText().equals("") || person.equals("")) {
					JOptionPane.showMessageDialog(frame, "You must enter patient information.");
					return;
				}

				for (Object selected : list_0.getSelectedValuesList()) {
					personalSymptoms.add(selected.toString());
					System.out.println(personalSymptoms);
				}
				for (Object selected : list_1.getSelectedValuesList()) {
					personalAnamnesis.add(selected.toString());
				}
				
				if (personalSymptoms.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's symptoms.");
					return;
					
				}
				if (personalAnamnesis.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's anamnesis.");
					return;
					
				}
				
				String write = "personal_symptoms(" + person + "," + personalSymptoms + ").";
				ArrayList<String> writeInFile = new ArrayList<String>();
				writeInFile.add(write);
				writeProlog(writeInFile, "rule_based/symptoms.pl");
				
				write = "personal_anamnesis(" + person + "," + personalAnamnesis + ").";
				writeInFile.clear();
				writeInFile.add(write);
				writeProlog(writeInFile, "rule_based/patients.pl");
				
				String term = "additional_tests(" + person + ", S)";
				ArrayList<String> additional_tests = consultProlog(term);
	            
	    		additionalTestsPanel(additional_tests);
			}

		});
		btnFindAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnFindAdditionalTests);	
		

		persSympt = new String[list_0.getModel().getSize()];
		persAnam= new String[list_1.getModel().getSize()];
		
		JButton btnFindAdditionalTestsCBR = new JButton("Find tests CBR");
		btnFindAdditionalTestsCBR.setBounds(441, 475, 250, 29);
		btnFindAdditionalTestsCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = formattedTextField.getText();
				String age = formattedTextFieldAge.getText();
				String gender = formattedTextFieldSex.getText();
				
				
				
				if ( age.equals("") || gender.equals("") || person.equals("")) {
					JOptionPane.showMessageDialog(frame, "You must enter patient information.");
				}

				if (list_0.getSelectedValuesList().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's symptoms.");
					return;
					
				}
				
				int i=0;
				for (Object selected : list_0.getSelectedValuesList()) {
					persSympt[i] = selected.toString();
					i++;		
				}
				if (list_1.getSelectedValuesList().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's anamnesis.");
					return;
				}
				int j=0;
				for (Object selected : list_1.getSelectedValuesList()) {
					persAnam[j]=selected.toString();
					j++;
				}
				
				ArrayList<String> additional_tests = new ArrayList<String>();
	            additional_tests.clear();
	            additional_tests.addAll(cbr.CbrApplication.main(personalAnamnesis, personalSymptoms, formattedTextFieldAge.getText(), formattedTextFieldSex.getText()));
	            additionalTestsPanel(additional_tests);
			}

		});
		btnFindAdditionalTestsCBR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnFindAdditionalTestsCBR);	

	}

	private void additionalTestsPanel(ArrayList<String> additional_tests) {
		// TODO Auto-generated method stub
		
		// ------------------- panel for additional tests--------------------
		panelAdditionalTests=new JPanel();
		
		JLabel lblNewLabel_1 = new JLabel("Additional tests:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 77, 154, 36);
		panelAdditionalTests.add(lblNewLabel_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(222, 77, 594, 342);
		
		String additionalTestsString1 = additional_tests.toString().replace("[", "").replace("]", "");
		String[] list= additionalTestsString1.split(",");
		
		list_2 = new JList();
		list_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
    	list_2.setListData(list);
    	scrollPane_2.setViewportView(list_2);
		
		panelAdditionalTests.add(scrollPane_2);
		
		panel.setVisible(false);
		panelAdditionalTests.setVisible(true);
		panelAdditionalTests.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelAdditionalTests);
		panelAdditionalTests.setLayout(null);
		
		ArrayList<String> personalTests = new ArrayList<String>();

		
		JButton btnNewButton = new JButton("Results");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Object selected : list_2.getSelectedValuesList()) {
					//String line = selected.toString();
					//String[] values = line.strip().split("\\(");
					//personalTests.add(values[0]);
					personalTests.add(selected.toString());
				}
				panelForTestsResults(personalTests);				
			}


		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(846, 447, 100, 36);
		panelAdditionalTests.add(btnNewButton);
			
	}
	
	private void panelForTestsResults(ArrayList<String> personalTests) {
		// TODO Auto-generated method stub

		panelResults=new JPanel();
		
		panel.setVisible(false);
		panelAdditionalTests.setVisible(false);
		panelResults.setVisible(true);
		panelResults.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelResults);
		panelResults.setLayout(null);
		

		list1 = personalTests.toArray();
		table = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Test", list1);
		tableModel.addColumn("Result");

		table.setModel(tableModel);

		table.setBounds(109, 22, 648, 286);
		panelResults.add(table);
		
		JButton btnNewButton1 = new JButton("Disease wiht RBR");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					String test = (String) tableModel.getValueAt(i, 0);
					String result = (String) tableModel.getValueAt(i, 1);
					String resultOfTest = test + "(" + person + "," + result + ").";
					ArrayList<String> writeInFile = new ArrayList<String>();
					writeInFile.add(resultOfTest);
					writeProlog(writeInFile, "rule_based/tests.pl");
				}
				String term = "diagnosis(" + person + ", D)";
	            ArrayList<String> diagnosis = consultProlog(term);
	            
				term = "medications(" + person + ", M)";
	            ArrayList<String> medications = consultProlog(term);
	            
	            panelDiagnosis(diagnosis, medications);
			}
		});
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton1.setBounds(441, 435, 220, 29);
		panelResults.add(btnNewButton1);
		
		JButton btnNewButtonCBR = new JButton("Disease with CBR");
		btnNewButtonCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Results results = new Results();
				Medication medication = new Medication();
				
				resultsOfTests = new HashMap<String, String>();
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					String test = (String) tableModel.getValueAt(i, 0);
					String[] test1 = test.split("\\(");
					String result = (String) tableModel.getValueAt(i, 1);
					resultsOfTests.put(test1[0], result);
				
				}
				results.setResultsOfTests(resultsOfTests);
				System.out.println(" Rezultati testova ");
				System.out.println(resultsOfTests);
				System.out.println(" Rezultati testova - ovako se salju: ");
				System.out.println(results);
				//String term = "diagnosis(" + person + ", D)";
	            ArrayList<String> diagnosis = CbrApplicationResults.main(results);
	            
				//term = "medications(" + person + ", M)";
	            medication.setDisease(diagnosis.get(0));
				medication.createBinAnam(persAnam);
	            ArrayList<String> medications = CbrApplicationMed.main(medication);
	            
	           // ArrayList<String> list = new ArrayList<>();
	           // list.add(diagnosis.get(0));
	            System.out.println("ovo vracaaa!!!!!!!!!!!!!!!!!" + diagnosis.toString());
	            
	            panelDiagnosis(diagnosis, medications);
			}
		});
		btnNewButtonCBR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButtonCBR.setBounds(441, 475, 250, 29);
		panelResults.add(btnNewButtonCBR);
		
		//testovi za pisanje u fajl
 		choosenTests= new String();
 		String choosen = personalTests.toString().replace("[", "").replace("]", "");
 		String [] c= choosen.split(","); 
 		for(int i=0; i<c.length; i++) {
 			if(i!=0) { choosenTests+=",";}
 			String c1[]= c[i].split("\\(");
 			choosenTests+=c1[0];
 		}
		
	}
	
	protected void panelDiagnosis(ArrayList<String> diagnosis, ArrayList<String> medications) {
		// TODO Auto-generated method stub
		
		panelDiagnosis= new JPanel();
		
		panel.setVisible(false);
		panelAdditionalTests.setVisible(false);
		panelResults.setVisible(false);
		panelDiagnosis.setVisible(true);
		panelDiagnosis.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelDiagnosis);
		panelDiagnosis.setLayout(null);
		
		JLabel lblDiagnosisForYou = new JLabel("Diagnosis for you patient is: ");
		lblDiagnosisForYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiagnosisForYou.setBounds(62, 43, 216, 69);
		panelDiagnosis.add(lblDiagnosisForYou);
		
		JLabel lblMedicam = new JLabel("Recommended medications:");
		lblMedicam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMedicam.setBounds(62, 122, 216, 69);
		panelDiagnosis.add(lblMedicam);
		
		JLabel diagnosisLabel = new JLabel(" ");
		diagnosisLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		diagnosisLabel.setBounds(288, 43, 216, 69);
		//diagnosisLabel.setText("");
		diagnosisLabel.setText(diagnosis.get(0));
		panelDiagnosis.add(diagnosisLabel);
		
		list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 16));
		list.setBounds(288, 151, 216, 157);
		String additionalTestsString = medications.toString().replace("[", "").replace("]", "");
		String[] medicationsString= additionalTestsString.split(",");
		
    	list.setListData(medicationsString);
		panelDiagnosis.add(list);
		
		JButton btnBack = new JButton("Back to start");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String anamnesis_csv= diagnosis.get(0)+";"+formattedTextFieldAge.getText()+";"+formattedTextFieldSex.getText()+";";
				String medication_csv= diagnosis.get(0)+";";
				String tests_csv= diagnosis.get(0)+";";
				
				System.out.println(Arrays.toString(persSympt));
				String symptoms = Arrays.toString(persSympt);
				String symptoms1 = symptoms.toString().replace("[", "").replace("]", "");
				String[] symptomsWithNull= symptoms1.split("null");
				String[] sympt = symptomsWithNull[0].split(",");
				for(int i=0; i<sympt.length-1; i++) {
					System.out.println(sympt[i]);
					if(i!=0) { anamnesis_csv+=",";}
					anamnesis_csv+=sympt[i];
				}
				anamnesis_csv+=";";
	            
				System.out.println(Arrays.toString(persAnam));
				String anamnesis = Arrays.toString(persAnam);
				String anamnesis1 = anamnesis.toString().replace("[", "").replace("]", "");
				String[] anamnWithNull= anamnesis1.split("null");
				String[] anamn = anamnWithNull[0].split(",");
				for(int i=0; i<anamn.length-1; i++) {
					if(i!=0) { anamnesis_csv+=","; medication_csv+=",";}
					anamnesis_csv+=anamn[i];
					medication_csv+=anamn[i];
				}
				anamnesis_csv+=";"+choosenTests;
				medication_csv+=";";
				
				long size3= list.getModel().getSize();
				for(int i=0; i<size3; i++) {
					if(i!=0) {medication_csv+=",";}
					medication_csv+=list.getModel().getElementAt(i).toString().split("\\(")[0];			
				}	
				
				int a=0;
				for(String key : resultsOfTests.keySet()) {
					tests_csv+=key+":"+resultsOfTests.get(key);
					a++;
					if(a<resultsOfTests.size()) {tests_csv+=",";}			
				}
				
				System.out.println(anamnesis_csv);
				System.out.println(medication_csv);
				System.out.println(tests_csv);				
				
				ArrayList<String> writeInFile = new ArrayList<String>();
				writeInFile.add(anamnesis_csv);
				writeProlog(writeInFile, "data/anamnesis.csv");
				
				ArrayList<String> writeInFile1 = new ArrayList<String>();
				writeInFile1.add(medication_csv);
				writeProlog(writeInFile, "data/medication.csv");
				
				ArrayList<String> writeInFile2 = new ArrayList<String>();
				writeInFile2.add(medication_csv);
				writeProlog(writeInFile, "data/tests.csv");
				
			      				
				list_0.clearSelection();
				list_1.clearSelection();
				list_2.clearSelection();
				list.clearSelection();
				resultsOfTests.clear();
				formattedTextField.setValue("");
				formattedTextFieldAge.setValue("");
				formattedTextFieldSex.setValue("");
				personalSymptoms.clear();
				personalAnamnesis.clear();
				
				panel.setVisible(true);
				panelAdditionalTests.setVisible(false);
				panelResults.setVisible(false);
				panelDiagnosis.setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(141, 475, 250, 29);
		panelDiagnosis.add(btnBack);
	}

	private ArrayList<String> consultProlog(String term){
		// New instance of prolog engine
        JIPEngine jip = new JIPEngine();

        JIPTerm queryTerm = null;

        // parse query
        try
        {
            // consult file
            jip.consultFile("rule_based/symptoms.pl");
            jip.consultFile("rule_based/patients.pl");
            jip.consultFile("rule_based/tests.pl");
            jip.consultFile("rule_based/diagnosis.pl");
            jip.consultFile("rule_based/medications.pl");


            queryTerm = jip.getTermParser().parseTerm(term);
        }
        catch(JIPSyntaxErrorException ex)
        {
            ex.printStackTrace();
            System.exit(0); // needed to close threads by AWT if shareware
        }

        // open Query
        JIPQuery jipQuery = jip.openSynchronousQuery(queryTerm);
        JIPTerm solution;
        ArrayList<String> listOfSymptoms = new ArrayList<String>();
        ArrayList<String> listOfAnamnesis = new ArrayList<String>();
        ArrayList<String> returnList = new ArrayList<String>();
 
        // Loop while there is another solution
        while (jipQuery.hasMoreChoicePoints())
        {
        	
            solution = jipQuery.nextSolution();
            if(solution!=null) {
            System.out.println("solution: " + solution);
            
            JIPVariable[] vars = solution.getVariables();
            for (JIPVariable var : vars) {
                if (!var.isAnonymous()) {
                    System.out.println(var.getName() + " = " + var.toString(jip) + " ");
                    if(solution.toString(jip).contains("symptoms")) {
	                    String[] symptoms = var.toString(jip).substring(1, var.toString(jip).length()-1).split(",");
	                    for (String symptom : symptoms) {
	                    	if(!listOfSymptoms.contains(symptom)) {
	                    		listOfSymptoms.add(symptom);
	                    		returnList = listOfSymptoms;
	                    	}
						}
                    } else if(solution.toString(jip).contains("anamnesis")) {
	                    String[] anamnesis = var.toString(jip).substring(1, var.toString(jip).length()-1).split(",");
	                    for (String a : anamnesis) {
	                    	if(!listOfAnamnesis.contains(a)) {
	                    		listOfAnamnesis.add(a);
	                    		returnList = listOfAnamnesis;
	                    	}
						}    
                    } else if(solution.toString(jip).contains("possible_diseases")) {
                    	System.out.println("Possible disease: " + var.toString(jip));
                    	returnList.add(var.toString(jip));
                    } else if(solution.toString(jip).contains("additional_tests")) {
                    	System.out.println("Additional tests: " + var.toString(jip));
                    	returnList.add(var.toString(jip));
	                } else if(solution.toString(jip).contains("diagnosis")) {
	                	System.out.println("Diagnosis: " + var.toString(jip));
	                	returnList.add(var.toString(jip));
	                } else if(solution.toString(jip).contains("medications")) {
	                	System.out.println("Medications: " + var.toString(jip));
	                	returnList.add(var.toString(jip));
	                }
                }
            }
        	}
        }
		System.out.println("-------------------------------------------");
        return returnList;
     }
	
	public static void writeProlog(ArrayList<String> terms, String fileName) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(fileName, true);
			pw = new PrintWriter(file);

			for(String t: terms) {
				pw.println(t);
			}
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (null != file)
					file.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
