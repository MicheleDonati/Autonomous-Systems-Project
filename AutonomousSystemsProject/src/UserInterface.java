import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInterface {
	
	private JFrame frame;
	private JPanel panel;           
	private JButton start_button;
	private JLabel reccomendation_label;
	private JLabel housenumber_label;
	private JTextArea housenumber_area;
	private JLabel streetname_label;
	private JTextArea streetname_area;
	private JLabel city_label;
	private JTextArea city_area;
	private JTextArea informations_area;
	private JTextArea message_area;
	private MyListener listener;
	private GridLayout layout;
	
	public UserInterface(){
		frame = new JFrame();
		frame.setBounds(800, 800, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout = new GridLayout(0,1);
		
		reccomendation_label = new JLabel(Utils.RECCOMENDATION_LABEL);
		housenumber_label = new JLabel(Utils.HOUSE_NUMBER);
		housenumber_area = new JTextArea(10,10);
		housenumber_area.setEditable(true);
		streetname_label = new JLabel(Utils.STREET_NAME);
		streetname_area = new JTextArea(10,10);
		streetname_area.setEditable(true);
		city_label = new JLabel(Utils.CITY_NAME);
		city_area = new JTextArea(10,10);
		city_area.setEditable(true);
		start_button = new JButton(Utils.BUTTON_NAME);
		start_button.setPreferredSize(new Dimension(1, 1));
		informations_area = new JTextArea(30,20);
		message_area = new JTextArea(30,20);
		
		panel = new JPanel();
		panel.setLayout(layout);

		panel.add(reccomendation_label);
		panel.add(housenumber_label);
		panel.add(housenumber_area);
		panel.add(streetname_label);
		panel.add(streetname_area);
		panel.add(city_label);
		panel.add(city_area);
		panel.add(start_button);
		panel.add(new JLabel(Utils.GENERAL_TAB));
		panel.add(informations_area);
		panel.add(new JLabel(Utils.MESSAGE_TAB));
		panel.add(message_area);
		frame.add(panel);
		frame.setVisible(true);
		listener = new MyListener(this);
		start_button.addActionListener(listener);
	}
	
	public void shutdown() {
		frame.setVisible(false);
		System.exit(0);
	}
	
	public void writeGeneralInfo(String info, boolean clear){
		if(!clear){
			informations_area.setText(informations_area.getText() + "\n" + info);
		}
		else {
			informations_area.setText(info);
		}
	}
	
	public void writeMessageInfo(String message){
		message_area.setText(message);
	}


	public String getHouseNumber(){
		return housenumber_area.getText();
	}
	
	public String getStreetName(){
		return streetname_area.getText();
	}
	
	public String getCityName(){
		return city_area.getText();
	}
	
}
