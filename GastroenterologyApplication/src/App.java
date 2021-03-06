import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableModel;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import cbr.CbrApplicationMed;
import cbr.CbrApplicationResults;
import model.Medication;
import model.Results;
import sun.nio.cs.StandardCharsets;

import javax.swing.JRadioButton;

import java.awt.Color;

import javax.swing.border.LineBorder;


public class App {

	private JFrame frame;
	JPanel panel = new JPanel();
	JPanel panelAdditionalTests = new JPanel();
	JPanel panelResults = new JPanel();
	JPanel panelDiagnosis = new JPanel();
	JPanel panelWholeMR = new JPanel();
	public String person;
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
	public static HashMap<String, String> resultsOfTests=new HashMap<String, String>();
	public static String[] persSympt ;
	public static ArrayList<String> personalSymptoms;
	public static ArrayList<String> personalAnamnesis;
	public static ArrayList<String> listOfPatients;
	public static ButtonGroup G ;

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
		frame.getContentPane().setBackground(new Color(255, 250, 250));
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
		panel.setBorder(new LineBorder(new Color(255, 235, 205), 1, true));
		panel.setBackground(new Color(255, 250, 250));
		
		panel.setBounds(0, 0, 992, 529);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setBounds(709, 12, 77, 29);
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSymptoms);
		java.util.Collections.sort(listOfSymptoms, Collator.getInstance());
		java.util.Collections.sort(listOfAnamnesis, Collator.getInstance());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(63, 12, 614, 432);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(253, 245, 230));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSex = new JLabel("Gender: ");
		lblSex.setBounds(10, 220, 62, 20);
		panel_1.add(lblSex);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNameAndSurname = new JLabel("Name and surname: ");
		lblNameAndSurname.setBounds(10, 58, 182, 20);
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
		G = new ButtonGroup();
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
		scrollPane.setBounds(709, 55, 256, 389);
		panel.add(scrollPane);
		
		//setting list of symptoms
		list_0 = new JList();
		scrollPane.setViewportView(list_0);
		list_0.setBackground(new Color(255, 255, 255));
		list_0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_0.setListData((String[]) listOfSymptoms.toArray(new String[0]));
		persSympt = new String[list_0.getModel().getSize()];
		
		// citanje vec postojeceg pacijenta ////////////////////////////////////////////////////////////////////////
		JButton btnLoad = new JButton("Load");
		btnLoad.setBackground(new Color(250, 235, 215));
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
				                    patients[0]);
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
		btnLoad.setBounds(269, 373, 119, 23);
		panel_1.add(btnLoad);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(269, 89, 274, 260);
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
		
		JButton btnSeeWholeMR = new JButton("See whole MR");
		btnSeeWholeMR.setBackground(new Color(250, 235, 215));
		btnSeeWholeMR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				person = formattedTextField.getText().toLowerCase().replace(" ", "_");
				if (rdbtnF.isSelected()){
					gender = "F";
				} else if (rdbtnM.isSelected()){
					gender = "M";
				}
				if(formattedTextFieldAge.getText().equals("") ||  person.equals("") || gender.equals("")) {
					JOptionPane.showMessageDialog(frame, "Fields must be entered and patient must be examined in order to have medical record.");
					return;
				}
				try {
					String personalInfo= readPersonalInfo();
					String data[] = readCSV();
					String anam= readAnamnesis();
					WholeMRPanel(data, anam, personalInfo);
				}catch(IndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(frame, "Patient must be examined in order to have medical record.");
					return;
				}
				
			}
		});
		btnSeeWholeMR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeeWholeMR.setBounds(403, 373, 140, 23);
		panel_1.add(btnSeeWholeMR);

		// additional tests RBR ///////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btnFindAdditionalTests = new JButton("Find tests");
		btnFindAdditionalTests.setBounds(673, 468, 141, 29);
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
					
					String term = "additional_tests(" + person + ", D,S)";
					ArrayList<String> additional_tests = consultProlog(term);
					ArrayList<String> testForDisease = new ArrayList<String>();

					for(int i=0; i<additional_tests.size(); i++){
						String additionalTestsString1 = additional_tests.get(i).toString().replace("[", "").replace("]", "");
						String[] lista = additionalTestsString1.split(",");
						for (String string : lista) {
							string = string + "   [ " + additional_tests.get(i+1) + " ]";
							testForDisease.add(string);
						}
						i = i+1;
					}
 
		    		additionalTestsPanel(testForDisease);
					}
			}

		});
		btnFindAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnFindAdditionalTests);	
		
		// additional tests CBR ///////////////////////////////////////////////////////////////////////////////////////////////

		
		JButton btnFindAdditionalTestsCBR = new JButton("Find tests CBR");
		btnFindAdditionalTestsCBR.setBounds(824, 468, 141, 29);
		btnFindAdditionalTestsCBR.setBackground(new Color(250, 235, 215));
		btnFindAdditionalTestsCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = formattedTextField.getText().toLowerCase().replace(" ", "_");
		    	String age = formattedTextFieldAge.getText();
		    	if (rdbtnF.isSelected()){
					gender = "F";
				} else if (rdbtnM.isSelected()){
					gender = "M";
				}
				
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
					
					try {
						System.out.println("zibrisano");
						changeProlog(person, "rule_based/patients.pl");
						changeProlog(person, "rule_based/symptoms.pl");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
					ArrayList<String> anam = new ArrayList<String>(Arrays.asList(persAnam));
					String write = "personal_anamnesis(" + person + "," + anam + ").";
					ArrayList<String>  writeInFile = new ArrayList<String>();
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

	private void WholeMRPanel(String data[], String anam, String personalInfo) {
		
		// ------------------- panel for whole medical record--------------------
		panelWholeMR=new JPanel();
		panelWholeMR.setBackground(new Color(253, 245, 230));
		
		JLabel lblNewLabel_1 = new JLabel("Medical record:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(116, 80, 135, 36);
		panelWholeMR.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name: " + formattedTextField.getText());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(116, 150, 100, 30);
		panelWholeMR.add(lblNewLabel_2);
			
		JLabel lblNewLabel_3 = new JLabel("Age: "+  personalInfo.split(";")[0]);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(116, 190, 100, 30);
		panelWholeMR.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender: "+  personalInfo.split(";")[1]);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(116, 230, 100, 30);
		panelWholeMR.add(lblNewLabel_4);
		
		String diseases= new String();
		for(int i=0; i<data.length; i++ ) {	
			if(i!=0) {diseases+=", ";}
			diseases+=data[i].split(";")[3];		
		}
		
		JLabel lblNewLabel_5 = new JLabel("Previous diseases: " + diseases);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(116, 270, 300, 30);
		panelWholeMR.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Anamnesis: " + anam);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(116, 310, 300, 30);
		panelWholeMR.add(lblNewLabel_6);
		
		panel.setVisible(false);
		panelWholeMR.setVisible(true);
		panelWholeMR.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelWholeMR);
		panelWholeMR.setLayout(null);
		
		
		Icon icon = new ImageIcon("images/back.png");
		JButton btnBack = new JButton(icon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panelWholeMR.setVisible(false);
			}


		});
		btnBack.setBackground(new Color(250, 235, 215));
		btnBack.setBounds(20, 20, 30, 25);
		panelWholeMR.add(btnBack);
		
		/*JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}


		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(250, 235, 215));
		btnNewButton.setBounds(743, 468, 141, 29);
	
			*/
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------

	private void additionalTestsPanel(ArrayList<String> additional_tests) {
		// TODO Auto-generated method stub
		
		// ------------------- panel for additional tests--------------------
		panelAdditionalTests=new JPanel();
		panelAdditionalTests.setBackground(new Color(253, 245, 230));
		
		JLabel lblNewLabel_1 = new JLabel("Additional tests:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(116, 80, 135, 36);
		panelAdditionalTests.add(lblNewLabel_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(250, 80, 631, 342);
		

		
		String[] list= (String[]) additional_tests.toArray(new String[0]);
		
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
					String line = selected.toString();
					String[] values = line.trim().split(" ");
					if (!personalTests.contains(values[0]))
						personalTests.add(values[0]);
				}
				panelForTestsResults(personalTests);				
			}


		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(824, 468, 141, 29);
		btnNewButton.setBackground(new Color(250, 235, 215));

		panelAdditionalTests.add(btnNewButton);
		
		
		Icon icon = new ImageIcon("images/back.png");
		JButton btnBack = new JButton(icon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panelAdditionalTests.setVisible(false);
			}


		});
		btnBack.setBackground(new Color(250, 235, 215));
		btnBack.setBounds(20, 20, 30, 25);
		panelAdditionalTests.add(btnBack);
			
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	
	private void panelForTestsResults(ArrayList<String> personalTests) {
		// TODO Auto-generated method stub

		panelResults=new JPanel();
		
		panel.setVisible(false);
		panelAdditionalTests.setVisible(false);
		panelResults.setVisible(true);
		panelResults.setBounds(0, 0, 992, 531);
		panelResults.setBackground(new Color(253, 245, 230));
		frame.getContentPane().add(panelResults);
		panelResults.setLayout(null);
		

		list1 = personalTests.toArray();
		table = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Test", list1);
		tableModel.addColumn("Result");
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(tableModel);

		table.setBounds(118, 80, 766, 286);
		panelResults.add(table);
		
		
		
		JButton btnNewButton1 = new JButton("Disease RBR");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getValueAt(tableModel.getRowCount()-1, 1)==null){
					JOptionPane.showMessageDialog(panelResults, "Please enter every result and then click on white space in table."
							+ " After this steps you can get disease.");
				} else {

					for (int i = 0; i < tableModel.getRowCount(); i++) {
						String test = (String) tableModel.getValueAt(i, 0);
						String result = (String) tableModel.getValueAt(i, 1);					
						// removing disease in [] for that test
						test = test.split(" ")[0].trim();
						//dodato zbog upisa novih slucajeva
						String[] test1 = test.split("\\(");
						resultsOfTests.put(test1[0], result);
						String resultOfTest = test + "(" + person + "," + result + ").";
						ArrayList<String> writeInFile = new ArrayList<String>();
						writeInFile.add(resultOfTest);
						writeProlog(writeInFile, "rule_based/tests.pl");
					}
	
					String term = "diagnosis(" + person + ", D)";
		            ArrayList<String> diagnosis = consultProlog(term);
		            if (diagnosis.isEmpty()) {
		        		JOptionPane.showMessageDialog(frame, "There is no disease for this test results.");
						return;
				
		            }

					term = "medications(" + person + ", M)";
		            ArrayList<String> medications = consultProlog(term);
		            
		            panelDiagnosis(diagnosis, medications);
					}
			}
		});
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton1.setBounds(673, 468, 141, 29);
		btnNewButton1.setBackground(new Color(250, 235, 215));
		panelResults.add(btnNewButton1);
		
		JButton btnNewButtonCBR = new JButton("Disease CBR");
		btnNewButtonCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Results results = new Results();
				Medication medication = new Medication();
				if(tableModel.getValueAt(tableModel.getRowCount()-1, 1)==null){
					JOptionPane.showMessageDialog(panelResults, "Please enter every result and then click on white space in table."
							+ " After this steps you can get disease.");
				} else {				
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
		            if (diagnosis.isEmpty()) {
		        		JOptionPane.showMessageDialog(frame, "There is no disease for this test results.");
						return;
				
		            }

					//term = "medications(" + person + ", M)";
		            medication.setDisease(diagnosis.get(0));
					medication.createBinAnam(persAnam);
		            ArrayList<String> medications = CbrApplicationMed.main(medication);
		            
		            panelDiagnosis(diagnosis, medications);
				}

			}
		});
		btnNewButtonCBR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButtonCBR.setBounds(824, 468, 141, 29);
		btnNewButtonCBR.setBackground(new Color(250, 235, 215));
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
		Icon icon = new ImageIcon("images/back.png");
		JButton btnBack = new JButton(icon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelResults.setVisible(false);
				panelAdditionalTests.setVisible(true);
			}


		});
		btnBack.setBackground(new Color(250, 235, 215));
		btnBack.setBounds(20, 20, 30, 25);
		panelResults.add(btnBack);
		
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------

	protected void panelDiagnosis(ArrayList<String> diagnosis, ArrayList<String> medications) {
		// TODO Auto-generated method stub
		
		panelDiagnosis= new JPanel();
		
		panel.setVisible(false);
		panelAdditionalTests.setVisible(false);
		panelResults.setVisible(false);
		panelDiagnosis.setVisible(true);
		panelDiagnosis.setBounds(0, 0, 992, 531);
		panelDiagnosis.setBackground(new Color(253, 245, 230));
		frame.getContentPane().add(panelDiagnosis);
		panelDiagnosis.setLayout(null);
		
		JLabel lblDiagnosisForYou = new JLabel("Diagnosis for you patient is: ");
		lblDiagnosisForYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiagnosisForYou.setBounds(118, 80, 216, 69);
		panelDiagnosis.add(lblDiagnosisForYou);
		
		JLabel lblMedicam = new JLabel("Recommended medications:");
		lblMedicam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMedicam.setBounds(118, 150, 216, 69);
		panelDiagnosis.add(lblMedicam);
		
		JLabel diagnosisLabel = new JLabel(" ");
		diagnosisLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		diagnosisLabel.setBounds(350, 80, 400, 69);
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
		list.setBounds(350, 170, 216, 160);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		String additionalTestsString = medications.toString().replace("[", "").replace("]", "");
		String[] medicationsString= additionalTestsString.split(",");
		
    	list.setListData(medicationsString);
		panelDiagnosis.add(list);
		
		JButton btnBack = new JButton("Back to start");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				G.clearSelection();
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
		btnBack.setBackground(new Color(250, 235, 215));
		btnBack.setBounds(673, 468, 141, 29);
		panelDiagnosis.add(btnBack);
		
		JButton btnSaveCBR = new JButton("Save ");
		btnSaveCBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String anamnesis_csv= diagnosis.get(0)+";"+formattedTextFieldAge.getText()+";"+gender+";";
				String medication_csv= diagnosis.get(0)+";";
				String tests_csv= diagnosis.get(0)+";";
				 
				String mr_csv= formattedTextField.getText()+";"+formattedTextFieldAge.getText()+";"+gender+";"+diagnosis.get(0);
				
				//System.out.println("OVDEE JE ONO STO MI TRABAAAAAAAAAAAAAAAAAAA");
			///	System.out.println(Arrays.toString(persSympt));
				//System.out.println(personalSymptoms.toString());
				String symptoms;
				if (personalSymptoms.isEmpty()) {
					symptoms = Arrays.toString(persSympt);
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
				}else {
					//symptoms = personalSymptoms.toString();
					for (int i=0; i<personalSymptoms.size(); i++) {
						if(i!=0) { anamnesis_csv+=",";}
						anamnesis_csv+=personalSymptoms.get(i);
					}
					anamnesis_csv+=";";
					for (int i=0; i<personalAnamnesis.size(); i++) {
						if(i!=0) { anamnesis_csv+=","; medication_csv+=",";}
						anamnesis_csv+=personalAnamnesis.get(i);
						medication_csv+=personalAnamnesis.get(i);
					}
					anamnesis_csv+=";"+choosenTests;
					medication_csv+=";";
				}
				
			
				
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
				System.out.println(mr_csv);	
				
				ArrayList<String> writeInFile = new ArrayList<String>();
				writeInFile.add(anamnesis_csv);
				writeProlog(writeInFile, "data/anamnesis.csv");
				
				ArrayList<String> writeInFile1 = new ArrayList<String>();
				writeInFile1.add(medication_csv);
				writeProlog(writeInFile1, "data/medication.csv");
				
				ArrayList<String> writeInFile2 = new ArrayList<String>();
				writeInFile2.add(tests_csv);
				writeProlog(writeInFile2, "data/tests.csv");
				
				ArrayList<String> writeInFile3 = new ArrayList<String>();
				writeInFile3.add(mr_csv);
				writeProlog(writeInFile3, "data/mr.csv");
				
			}
		});
		btnSaveCBR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveCBR.setBackground(new Color(250, 235, 215));
		btnSaveCBR.setBounds(824, 468, 141, 29);
		panelDiagnosis.add(btnSaveCBR);
		
		Icon icon = new ImageIcon("images/back.png");
		JButton btnBack1 = new JButton(icon);
		btnBack1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDiagnosis.setVisible(false);
				panelResults.setVisible(true);
			}


		});
		btnBack1.setBackground(new Color(250, 235, 215));
		btnBack1.setBounds(20, 20, 30, 25);
		panelDiagnosis.add(btnBack1);
		
		
		

	}
	// --------------------------------------------------------------------------------------------------------------------------------

	
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
	
    
    public String[] readCSV() {
    	
    	String csvFile = "data/mr.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        String[] data = new String[20];
        int l=0; int j=0;
	    try {
	
	    	br = new BufferedReader(new FileReader(csvFile));
	        while ((line = br.readLine()) != null) {
	
	            // use comma as separator
	            String[] name = line.split(cvsSplitBy);
	            String[] examination1 = line.split(" ");

	            if(name[0].equals(formattedTextField.getText())) {
	            		 data[j]=examination1[0];
	            		 j++; l=j;	            	 
	            }
	
	        }
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
		
	    String data1[]= new String [l];
	    for(int i=0; i<l; i++) {
	    	data1[i]=data[i];
	    }
		
		return data1;
    }
    
    public String readAnamnesis() {
    	
    	String name= (String)formattedTextField.getText();
 		ArrayList<String> listOfPersonalAnamnesis = consultProlog("personal_anamnesis(" + name + ",X)");
 		Object[] list = ((String[]) listOfPersonalAnamnesis.toArray(new String[0]));
 		
 		String s= new String();
 		for (Object value : list) {
 			s+=value+"\n";
 	    }
 		
 		return s;
 		
    }
    
    public String readPersonalInfo() {
    	
    	String personalInfo= new String();
    	
    	String name= (String)formattedTextField.getText();
	    ArrayList<String> listOfAges = consultProlog("age(" + name + ",X)");
	    ArrayList<String> listOfSex = consultProlog("male(" + name + ")");
		personalInfo+=listOfAges.get(0)+";";
		if(listOfSex.size()!=0){
			personalInfo+="M"+";";
		} else{
			personalInfo+="F"+";";
		}			
		
		return personalInfo;
    }

}
