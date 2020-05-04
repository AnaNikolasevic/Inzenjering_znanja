import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPTermParser;
import com.ugos.jiprolog.engine.JIPVariable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class App {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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
	@SuppressWarnings("unchecked")
	private void initialize() throws IOException {
		
		// New instance of prolog engine
        JIPEngine jip = new JIPEngine();

        JIPTerm queryTerm = null;

        // parse query
        try
        {
            // consult file
            jip.consultFile("rule_based/symptoms.pl");
            queryTerm = jip.getTermParser().parseTerm("symptoms(_, X)");
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
        
        // Loop while there is another solution
        while (jipQuery.hasMoreChoicePoints())
        {
            solution = jipQuery.nextSolution();
            System.out.println(solution);
            
            JIPVariable[] vars = solution.getVariables();
            for (JIPVariable var : vars) {
                if (!var.isAnonymous()) {
                    System.out.print(var.getName() + " = " + var.toString(jip) + " ");
                    System.out.println();
                    String[] symptoms = var.toString(jip).substring(1, var.toString(jip).length()-1).split(",");
                    for (String symptom : symptoms) {
                    	if(!listOfSymptoms.contains(symptom)) {
                    		listOfSymptoms.add(symptom);
                    	}
					}
                }
            }
        }
        
		
		frame = new JFrame();
		frame.setBounds(100, 100, 626, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNameAndSurname = new JLabel("Name and surname");
		lblNameAndSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameAndSurname.setBounds(10, 10, 146, 32);
		
		frame.getContentPane().add(lblNameAndSurname);
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(166, 19, 397, 19);
		frame.getContentPane().add(formattedTextField);
		
		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSymptoms.setBounds(10, 57, 146, 19);
		frame.getContentPane().add(lblSymptoms);
		
		// list of symptoms
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setModel(new AbstractListModel() {
			String [] values = (String[]) listOfSymptoms.toArray(new String[0]);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(166, 48, 397, 250);
		frame.getContentPane().add(list);

		
		JButton btnFindAdditionalTests = new JButton("Find additional tests");
		btnFindAdditionalTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							}
		});
		btnFindAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFindAdditionalTests.setBounds(386, 308, 177, 21);
		frame.getContentPane().add(btnFindAdditionalTests);
	}
	

    
}
