
/*
 * TCSS 372 – MIPS SIMULATOR 
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * {@code} Constructs graphical interface and functions for MIPS simulator.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/12/2019
 */
public class Gui extends JFrame {

	/**
	 * Serial version ID used during deserialization to verify serialized objects
	 * have loaded classes.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Stores the contentPane.
	 */
	private JPanel contentPane;

	/**
	 * Constructor that initializes GUI components.
	 * 
	 * @param d Driver object that stores hex instructions.
	 */
	public Gui(Driver d) {

		setTitle("Mips Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String startingDirectory = System.getProperty("user.home");
		final JFileChooser fc = new JFileChooser(startingDirectory + "/Desktop");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES OR ASM FILES", "txt", "text", "asm");
		fc.setFileFilter(filter);

		JButton runButton = new JButton("Run");
		runButton.setBounds(470, 422, 141, 33);
		contentPane.add(runButton);

		JButton loadButton = new JButton("Load");
		loadButton.setBounds(621, 422, 89, 33);
		contentPane.add(loadButton);

		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(720, 422, 89, 33);
		contentPane.add(resetButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 46, 450, 381);
		contentPane.add(textArea);

		// This needs to be looked at in greater detail.
		final JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 46, 450, 381);
		contentPane.add(scrollPane);

		JTextArea consoleOutputWindow = new JTextArea();
		consoleOutputWindow.setBounds(470, 46, 450, 381);
		contentPane.add(consoleOutputWindow);

		final JScrollPane scrollPane_1 = new JScrollPane(consoleOutputWindow);
		scrollPane_1.setBounds(470, 46, 450, 381);//470, 46, 339, 337
		contentPane.add(scrollPane_1);

		JLabel lblNewLabel = new JLabel("Input:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 128, 34);
		contentPane.add(lblNewLabel);

		JLabel lblConsole = new JLabel("Output:");
		lblConsole.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblConsole.setBounds(471, 11, 128, 34);
		contentPane.add(lblConsole, BorderLayout.WEST);

		runButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String output;

				try {

					String text = textArea.getText();
					output = d.run(text);

				} catch (IllegalArgumentException e1) {
					output = e1.getCause().getMessage();
				}

				consoleOutputWindow.setText(output);
			}
		});
		
		loadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// My clunky attempt at forcing user to use a .txt or .asm only.
				while (true) {

					int returnVal = fc.showOpenDialog(contentPane);

					if (returnVal == JFileChooser.APPROVE_OPTION) {

						File file = fc.getSelectedFile();
						if (file.getName().endsWith(".txt") || file.getName().endsWith(".asm")) {

							try {
								Scanner input = new Scanner(file);

								String accumulator = "";
								while (input.hasNext()) {

									accumulator += "\n" + input.nextLine();

								}
								textArea.setText(accumulator);
								input.close();
							} catch (FileNotFoundException e1) {

								e1.printStackTrace();
							}
							// Notes to myself for testing purposes
							System.out.println("valid" + " " + file.getName());

							return;

						}
						// testing purposes
						System.out.println("invalid");
					} else {
						// Testing purposes
						System.out.println("Cancel");
						return;
					}
				}
			}

		});
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				consoleOutputWindow.setText("");
			}
		});
	}

}
