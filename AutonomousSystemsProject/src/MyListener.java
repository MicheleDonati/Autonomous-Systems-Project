import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{

	private UserInterface ui;
	
	public MyListener(UserInterface ui){
		this.ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (Utils.BUTTON_NAME.equalsIgnoreCase(command)) {
			new Device(ui);
		}
		
	}

}
