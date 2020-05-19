import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;


public class App {

	private JFrame frame;
	JPanel panel = new JPanel();
	JPanel panelAdditionalTests = new JPanel();
	JPanel panelResults = new JPanel();
	JPanel panelDiagnosis = new JPanel();

	String person;
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
		ArrayList<String> personalSymptoms = new ArrayList<String>();
		ArrayList<String> personalAnamnesis = new ArrayList<String>();

		//lists from prolog
	    ArrayList<String> listOfSymptoms = consultProlog("symptoms(_, X)");
	    ArrayList<String> listOfAnamnesis = consultProlog("anamnesis(_, X)");

	    // ------------------------------first panel--------------------------
	    
		panelAdditionalTests.setVisible(false);
		
		panel.setBounds(0, 0, 992, 521);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNameAndSurname = new JLabel("Name and surname");
		lblNameAndSurname.setBounds(53, 36, 139, 20);
		lblNameAndSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNameAndSurname);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(201, 39, 258, 19);
		panel.add(formattedTextField);

		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setBounds(118, 97, 74, 20);
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSymptoms);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 97, 258, 278);
		panel.add(scrollPane);
		
		//setting list of symptoms
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setListData((String[]) listOfSymptoms.toArray(new String[0]));

		JLabel lblNewLabel = new JLabel("Anamnesis");
		lblNewLabel.setBounds(539, 45, 77, 125);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(626, 97, 258, 278);
		panel.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setListData((String[]) listOfAnamnesis.toArray(new String[0]));
		
		JButton btnFindAdditionalTests = new JButton("Find possible diseases");
		btnFindAdditionalTests.setBounds(441, 455, 191, 29);
		btnFindAdditionalTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (Object selected : list.getSelectedValuesList()) {
					personalSymptoms.add(selected.toString());
				}
				for (Object selected : list_1.getSelectedValuesList()) {
					personalAnamnesis.add(selected.toString());
				}
				person = formattedTextField.getText();
				
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

	}

	private void additionalTestsPanel(ArrayList<String> additional_tests) {
		// TODO Auto-generated method stub
		
		// ------------------- panel for additional tests--------------------
		panel.setVisible(false);
		panelAdditionalTests.setVisible(true);
		panelAdditionalTests.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelAdditionalTests);
		panelAdditionalTests.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Additional tests:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 77, 154, 36);
		panelAdditionalTests.add(lblNewLabel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(222, 77, 594, 342);
		panelAdditionalTests.add(scrollPane_2);
		
		JList list_2 = new JList();
		list_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_2.setViewportView(list_2);
		
		String additionalTestsString = additional_tests.toString().replace("[", "").replace("]", "");
		String[] list= additionalTestsString.split(",");
		
    	list_2.setListData(list);
		System.out.println(additional_tests.size());

		ArrayList<String> personalTests = new ArrayList<String>();

		
		JButton btnNewButton = new JButton("Results");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Object selected : list_2.getSelectedValuesList()) {
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
		panel.setVisible(false);
		panelAdditionalTests.setVisible(false);
		panelResults.setVisible(true);
		panelResults.setBounds(0, 0, 992, 531);
		frame.getContentPane().add(panelResults);
		panelResults.setLayout(null);
		
		Object[] list1 = personalTests.toArray();
		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Test", list1);
		tableModel.addColumn("Result");

		table.setModel(tableModel);

		table.setBounds(109, 22, 648, 286);
		panelResults.add(table);
		
		JButton btnNewButton = new JButton("Disease");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(846, 447, 100, 36);
		panelResults.add(btnNewButton);
	}
	
	protected void panelDiagnosis(ArrayList<String> diagnosis, ArrayList<String> medications) {
		// TODO Auto-generated method stub
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
		diagnosisLabel.setText(diagnosis.get(0));
		panelDiagnosis.add(diagnosisLabel);
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 16));
		list.setBounds(288, 151, 216, 157);
		String additionalTestsString = medications.toString().replace("[", "").replace("]", "");
		String[] medicationsString= additionalTestsString.split(",");
		
    	list.setListData(medicationsString);
		panelDiagnosis.add(list);
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
