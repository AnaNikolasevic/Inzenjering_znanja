import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sun.nio.cs.StandardCharsets;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class probA extends JPanel {

	/**
	 * Create the panel.
	 */
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
	public static HashMap<String, String> resultsOfTests =  new HashMap<String, String> ();
	public static String[] persSympt ;
	public static ArrayList<String> personalSymptoms;
	public static ArrayList<String> personalAnamnesis ;
	public static String choosenTests;
	public String gender = "";
	public probA() {
	      this.setBackground(new Color(255, 250, 250));
     // ------------------- panel for additional tests--------------------
     		
     		JLabel lblNewLabel_1 = new JLabel("Additional tests:");
     		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
     		lblNewLabel_1.setBounds(116, 80, 135, 36);
     		this.add(lblNewLabel_1);
     		
     		scrollPane_2 = new JScrollPane();
     		scrollPane_2.setBounds(250, 80, 631, 342);
     		
     		//String additionalTestsString1 = additional_tests.toString().replace("[", "").replace("]", "");
    // 		String[] list= additionalTestsString1.split(",");
     		
     		list_2 = new JList();
     		list_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
     		
   //      	list_2.setListData(list);
         	scrollPane_2.setViewportView(list_2);
     		
     		this.add(scrollPane_2);
     		
     		//panel.setVisible(false);
     		this.setVisible(true);
     		this.setBounds(0, 0, 992, 531);
     		//frame.getContentPane().add(panelAdditionalTests);
     		this.setLayout(null);
     		
     		ArrayList<String> personalTests = new ArrayList<String>();

     		
     		JButton btnNewButton = new JButton("Results");
     		btnNewButton.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				for (Object selected : list_2.getSelectedValuesList()) {
     					String line = selected.toString();
     					String[] values = line.trim().split(" ");
     					personalTests.add(values[0]);
     					//personalTests.add(selected.toString());
     				}
     		//		panelForTestsResults(personalTests);				
     			}


     		});
     		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
     		btnNewButton.setBackground(new Color(250, 235, 215));
     		btnNewButton.setBounds(743, 468, 141, 29);
     		this.add(btnNewButton);
     		
     		Icon icon = new ImageIcon("images/back.png");
    		JButton btnNewButton_1 = new JButton(icon);
    	
    		this.add(btnNewButton_1);
     		
	}
}
