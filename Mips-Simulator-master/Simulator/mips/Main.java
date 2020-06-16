
/*
 * TCSS 372 – MIPS SIMULATOR 
 */
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * {@code} Client code to initialize GUI and programs component values.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class Main {

	/**
	 * The main method, invokes the GUI and sets nimbus look and feel. Initializes
	 * program values at start.
	 * 
	 * @param theArgs Command line arguments.
	 */
	public static void main(String[] args) {

		Driver d = new Driver();

		// sets Nimbus look and feel.
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
		// initializes GUI.
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Gui frame = new Gui(d);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
