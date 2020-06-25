import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
		setBorder(new LineBorder(null));
        this.setBackground(new Color(255, 250, 250));
		
    	table = new JTable();
    	table.setBorder(new LineBorder(null));
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Test", list1);
		tableModel.addColumn("Result");

		table.setModel(tableModel);

		table.setBounds(118, 80, 766, 286);
		this.add(table);	
	}

}
