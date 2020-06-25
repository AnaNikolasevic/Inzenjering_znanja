import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.Collator;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
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

import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

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
	public static Object[] list1;
	public static DefaultTableModel tableModel;
	private static String[] persAnam;
	public static JFormattedTextField formattedTextField;
	public static JFormattedTextField formattedTextFieldAge;
	public static HashMap<String, String> resultsOfTests =  new HashMap<String, String> ();
	public static String[] persSympt ;
	public static ArrayList<String> personalSymptoms;
	public static ArrayList<String> personalAnamnesis;
	public static ArrayList<String> listOfPatients;

	public static String choosenTests;
	public String gender = "";
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
		panel.setBackground(new Color(255, 250, 240));
		
		panel.setBounds(-25, 0, 992, 529);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setBounds(709, 23, 77, 29);
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSymptoms);
		java.util.Collections.sort(listOfSymptoms, Collator.getInstance());
		java.util.Collections.sort(listOfAnamnesis, Collator.getInstance());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(63, 11, 614, 432);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(253, 245, 230));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSex = new JLabel("Gender: ");
		lblSex.setBounds(10, 220, 62, 20);
		panel_1.add(lblSex);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNameAndSurname = new JLabel("Name and surname: ");
		lblNameAndSurname.setBounds(10, 58, 149, 20);
		panel_1.add(lblNameAndSurname);
		lblNameAndSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBackground(new Color(255, 255, 255));
		formattedTextField.setBounds(10, 89, 182, 19);
		panel_1.add(formattedTextField);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnF.setBackground(new Color(253, 245, 230));
		rdbtnF.setBounds(10, 247, 38, 23);
		panel_1.add(rdbtnF);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnM.setBackground(new Color(253, 245, 230));
		rdbtnM.setBounds(57, 247, 52, 23);
		panel_1.add(rdbtnM);
		ButtonGroup G = new ButtonGroup();
		G.add(rdbtnF);
		G.add(rdbtnM);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(10, 133, 52, 20);
		panel_1.add(lblAge);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		formattedTextFieldAge = new JFormattedTextField();
		formattedTextFieldAge.setBackground(new Color(255, 255, 255));
		formattedTextFieldAge.setBounds(10, 164, 62, 19);
		panel_1.add(formattedTextFieldAge);
		
		JLabel lblNewLabel_2 = new JLabel("Medical record");
		lblNewLabel_2.setFont(new Font("Tahoma", lblNewLabel_2.getFont().getStyle() | Font.BOLD, 16));
		lblNewLabel_2.setBounds(224, 11, 193, 23);
		panel_1.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(709, 65, 256, 378);
		panel.add(scrollPane);
		
		//setting list of symptoms
		list_0 = new JList();
		scrollPane.setViewportView(list_0);
		list_0.setBackground(new Color(255, 255, 255));
		list_0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_0.setListData((String[]) listOfSymptoms.toArray(new String[0]));
		persSympt = new String[list_0.getModel().getSize()];
		
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listOfPatients = consultProlog("age(X, _)");
				Object[] patients = listOfPatients.toArray(new Object[listOfPatients.size()]);
				String s = (String)JOptionPane.showInputDialog(
				                    frame,
				                    "Choose patient:\n",
				                    "Medical record",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    patients,
				                    patients[2]);
				if (!s.equals(null)) {
					formattedTextField.setText(s);
					ArrayList<String> listOfAges = consultProlog("age(" + s + ",X)");
					formattedTextFieldAge.setText(listOfAges.get(0));
					ArrayList<String> listOfSex = consultProlog("male(" + s + ")");
					System.out.println(listOfSex.size());
					if(listOfSex.size()!=0){
						rdbtnF.setSelected(false);
						rdbtnM.setSelected(true);

					} else{
						rdbtnF.setSelected(true);
						rdbtnM.setSelected(false);

					}
					ArrayList<String> listOfPersonalAnamnesis = consultProlog("personal_anamnesis(" + s + ",X)");
					Object[] list = ((String[]) listOfPersonalAnamnesis.toArray(new String[0]));

					list_1.clearSelection();
				    for (Object value : list) {
				        int index = getIndex(list_1.getModel(), value);
				        if (index >=0) {
				        	list_1.addSelectionInterval(index, index);
				        }
				    }
				    list_1.ensureIndexIsVisible(list_1.getSelectedIndex());
					
				} else {
				    System.exit(0);
				}
			}
			public int getIndex(ListModel model, Object value) {
			    if (value == null) return -1;
			    if (model instanceof DefaultListModel) {
			        return ((DefaultListModel) model).indexOf(value);
			    }
			    for (int i = 0; i < model.getSize(); i++) {
			        if (value.equals(model.getElementAt(i))) return i;
			    }
			    return -1;
			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLoad.setBounds(398, 373, 127, 23);
		panel_1.add(btnLoad);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(269, 89, 256, 260);
		panel_1.add(scrollPane_1);
		
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setBackground(new Color(255, 255, 255));
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setListData((String[]) listOfAnamnesis.toArray(new String[0]));
		persAnam= new String[list_1.getModel().getSize()];
		
		JLabel lblNewLabel = new JLabel("Anamnesis");
		lblNewLabel.setBounds(269, 58, 77, 20);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnCompleteMr = new JButton("Complete MR");
		btnCompleteMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCompleteMr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompleteMr.setBounds(261, 373, 127, 23);
		panel_1.add(btnCompleteMr);

		
		JButton btnFindAdditionalTests = new JButton("Find tests");
		btnFindAdditionalTests.setBounds(593, 468, 141, 29);
		btnFindAdditionalTests.setBackground(new Color(250, 235, 215));
		btnFindAdditionalTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = formattedTextField.getText().toLowerCase().replace(" ", "_");
				if (rdbtnF.isSelected()){
					gender = "F";
				} else if (rdbtnM.isSelected()){
					gender = "M";
				}
				// check if each field is filled
				if ( formattedTextFieldAge.getText().equals("") ||  person.equals("") || gender.equals("")) {
					JOptionPane.showMessageDialog(frame, "You must enter patient information.");
				} else if (list_0.getSelectedValuesList().isEmpty() ||  list_1.getSelectedValuesList().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's symptoms and anamnesis.");
				} else {
		        
					try {
							double d = Double.parseDouble(formattedTextFieldAge.getText());
					} catch (NumberFormatException nfe) {
					    	JOptionPane.showMessageDialog(frame, "Enter the number in the age field.");
					    	return;
					}

					try {
						System.out.println("zibrisano");
						changeProlog(person, "rule_based/patients.pl");
						changeProlog(person, "rule_based/symptoms.pl");
						changeProlog(person, "rule_based/tests.pl");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					for (Object selected : list_0.getSelectedValuesList()) {
						personalSymptoms.add(selected.toString());
						System.out.println(personalSymptoms);
					}
					for (Object selected : list_1.getSelectedValuesList()) {
						personalAnamnesis.add(selected.toString());
					}
					
					String write = "personal_symptoms(" + person + "," + personalSymptoms + ").";
					ArrayList<String> writeInFile = new ArrayList<String>();
					writeInFile.add(write);
					writeProlog(writeInFile, "rule_based/symptoms.pl");
					
					write = "personal_anamnesis(" + person + "," + personalAnamnesis + ").";
					writeInFile.clear();
					writeInFile.add(write);
					writeProlog(writeInFile, "rule_based/patients.pl");
		
					write = "age(" + person + "," + formattedTextFieldAge.getText() + ").";
					writeInFile.clear();
					writeInFile.add(write);
					writeProlog(writeInFile, "rule_based/patients.pl");
					
					if(rdbtnF.isSelected()){
						write = "female(" + person + ").";
					} else {
						write = "male(" + person + ").";
					}
					writeInFile.clear();
					writeInFile.add(write);
					writeProlog(writeInFile, "rule_based/patients.pl");
					
					String term = "additional_tests(" + person + ", S)";
					ArrayList<String> additional_tests = consultProlog(term);
		            
		    		additionalTestsPanel(additional_tests);
					}
			}

		});
		btnFindAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnFindAdditionalTests);	
		
		JButton btnFindAdditionalTestsCBR = new JButton("Find tests CBR");
		btnFindAdditionalTestsCBR.setBounds(743, 468, 141, 29);
		btnFindAdditionalTestsCBR.setBackground(new Color(250, 235, 215));
		btnFindAdditionalTestsCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = formattedTextField.getText().toLowerCase().replace(" ", "_");
		    	String age = formattedTextFieldAge.getText();
				
				if ( age.equals("") ||  person.equals("") || gender.equals("")) {
					JOptionPane.showMessageDialog(frame, "You must enter patient information.");
				} else if (list_0.getSelectedValuesList().isEmpty() || list_1.getSelectedValuesList().isEmpty() ) {
					JOptionPane.showMessageDialog(frame, "You must select the patient's symptoms and anamnesis.");
					
				}else {
					try {
						double d = Double.parseDouble(age);
					} catch (NumberFormatException nfe) {
					    	JOptionPane.showMessageDialog(frame, "Enter the number in the age field.");
					    	return;
					}

					int i=0;
					for (Object selected : list_0.getSelectedValuesList()) {
						persSympt[i] = selected.toString();
						i++;		
					}
					int j=0;
					for (Object selected : list_1.getSelectedValuesList()) {
						persAnam[j]=selected.toString();
						j++;
					}
				
					ArrayList<String> additional_tests = new ArrayList<String>();
					additional_tests.clear();
					additional_tests.addAll(cbr.CbrApplication.main(personalAnamnesis, personalSymptoms, formattedTextFieldAge.getText(), gender));
					additionalTestsPanel(additional_tests);
				}
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
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setBounds(593, 468, 141, 29);
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
		btnNewButton1.setBackground(SystemColor.controlHighlight);
		btnNewButton1.setBounds(593, 468, 141, 29);
		panelResults.add(btnNewButton1);
		
		JButton btnNewButtonCBR = new JButton("Disease with CBR");
		btnNewButtonCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Results results = new Results();
				Medication medication = new Medication();
				
			//	resultsOfTests = new HashMap<String, String>();
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
		btnNewButtonCBR.setBackground(SystemColor.controlHighlight);
		btnNewButtonCBR.setBounds(743, 468, 141, 29);
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
		diagnosisLabel.setBounds(288, 43, 400, 69);
		ArrayList<String> outputDiagnosis = new ArrayList<>();
		for (String s : diagnosis) {
			if (!outputDiagnosis.contains(s)) {
				outputDiagnosis.add(s);
			}
		}
		
		diagnosisLabel.setText(outputDiagnosis.toString());
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
				
				String anamnesis_csv= diagnosis.get(0)+";"+formattedTextFieldAge.getText()+";"+formattedTextFieldAge.getText()+";";
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
				formattedTextFieldAge.setValue("");
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
            
            if(solution.toString(jip).contains("male")) {
            	returnList.add(solution.toString());
            } 
            
            JIPVariable[] vars = solution.getVariables();
            for (JIPVariable var : vars) {
                if (!var.isAnonymous()) {
                    System.out.println(var.getName() + " = " + var.toString(jip) + " ");
                    if(solution.toString(jip).contains("personal_anamnesis")) {
	                    String[] personal_anamnesis = var.toString(jip).substring(1, var.toString(jip).length()-1).split(",");
	                    for (String a : personal_anamnesis) {
	                    	if(!listOfAnamnesis.contains(a)) {
	                    		listOfAnamnesis.add(a);
	                    		returnList = listOfAnamnesis;
	                    	}
						}    
                    } else if(solution.toString(jip).contains("symptoms")) {
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
	                } else if(solution.toString(jip).contains("age")) {
	                	System.out.println("Age: " + var.toString(jip));
	                	returnList.add(var.toString(jip).replace("_", " "));
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
	
	public static void changeProlog(String line, String fileName) throws IOException {
		File inputFile = new File(fileName);
		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = line;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();

		    if(trimmedLine.contains(lineToRemove)){
		    	System.out.println("jeej");
		    }
		    if(trimmedLine.contains(lineToRemove)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		inputFile.delete();
		boolean successful = tempFile.renameTo(inputFile);
	}
}
