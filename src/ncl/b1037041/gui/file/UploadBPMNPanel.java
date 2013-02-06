package ncl.b1037041.gui.file;

import javax.swing.*;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.exception.AlertException;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.parts.ChoreographyItemPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("serial")
public class UploadBPMNPanel extends JPanel {

	private static final String FILE_PATH = "./bpmnFile/";
	private static final String IMAGE_PATH = "./bpmnImage/";

	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	private JFileChooser chooser_file = new JFileChooser();
	private JFileChooser chooser_image = new JFileChooser();
	private JTextField textField_file;
	private JTextField textField_image;

	private JPanel panel_choreographies;

	private File file;
	private File image;

	/**
	 * Create the panel.
	 */
	public UploadBPMNPanel() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		this.setLayout(null);

		JLabel label_file = new JLabel("Upload file");
		label_file.setFont(new Font("Arial", Font.PLAIN, 16));
		label_file.setBounds(10, 10, 74, 28);
		add(label_file);

		JLabel label_image = new JLabel("Upload image");
		label_image.setFont(new Font("Arial", Font.PLAIN, 16));
		label_image.setBounds(10, 48, 98, 28);
		add(label_image);

		JButton button_file = new JButton("Select");
		button_file.setBounds(119, 14, 80, 23);
		button_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int result = BPMN2PROMELAWindow
						.centerFileChooserDialog(chooser_file);
				if (result == JFileChooser.APPROVE_OPTION) {
					file = chooser_file.getSelectedFile();
					textField_file.setText(file.getAbsolutePath());
				}
			}
		});
		add(button_file);

		JButton button_image = new JButton("Select");
		button_image.setBounds(119, 52, 80, 23);
		button_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int result = BPMN2PROMELAWindow
						.centerFileChooserDialog(chooser_image);
				if (result == JFileChooser.APPROVE_OPTION) {
					image = chooser_image.getSelectedFile();
					textField_image.setText(image.getAbsolutePath());
				}
			}
		});
		add(button_image);

		textField_file = new JTextField();
		textField_file.setBackground(new Color(230, 230, 250));
		textField_file.setEditable(false);
		textField_file.setBounds(299, 15, 410, 21);
		add(textField_file);
		textField_file.setColumns(10);

		textField_image = new JTextField();
		textField_image.setBackground(new Color(230, 230, 250));
		textField_image.setEditable(false);
		textField_image.setColumns(10);
		textField_image.setBounds(299, 53, 410, 21);
		add(textField_image);

		JButton button_file_clear = new JButton("Clear");
		button_file_clear.setBounds(209, 14, 80, 23);
		button_file_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				file = null;
				textField_file.setText("");
			}
		});
		add(button_file_clear);

		JButton button_image_clear = new JButton("Clear");
		button_image_clear.setBounds(209, 52, 80, 23);
		button_image_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				image = null;
				textField_image.setText("");
			}
		});
		add(button_image_clear);

		JButton button_add_ltl = new JButton("Add");
		button_add_ltl.setBackground(Color.GREEN);
		button_add_ltl.setBounds(719, 14, 61, 62);
		button_add_ltl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (file == null || image == null) {
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"Please select choreography file and image.", "");
				} else {
					String fileName = file.getName().substring(0,
							file.getName().indexOf("."));
					String filePath = FILE_PATH + UUID.randomUUID().toString() + ".bpmn";
					String imagePath = IMAGE_PATH + UUID.randomUUID().toString() 
							+ image.getName().substring(image.getName().indexOf("."), image.getName().length());
					BPMNChoreography chor = new BPMNChoreography();
					chor.setName(fileName);
					chor.setFilePath(filePath);
					chor.setImagePath(imagePath);

					FileInputStream is;
					FileOutputStream fos;
					try {
						// Add to database
						ltlManager.addBPMN(chor);
						// Add to folder
						is = new FileInputStream(file);
						File outFile = new File(filePath);
						fos = new FileOutputStream(outFile);
						byte[] buffer = new byte[1024];
						int len;
						while ((len = is.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.flush();
						is.close();
						fos.close();
						
						is = new FileInputStream(image);
						File outImage = new File(imagePath);
						fos = new FileOutputStream(outImage);
						while ((len = is.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.flush();
						is.close();
						fos.close();

						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								"Choreography has been added.", "Success");
						BPMN2PROMELAWindow.click_file_upload();
					} catch (AlertException e) {
						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								e.getMessage(), "Failure");
					} catch (FileNotFoundException e) {
						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								"File does not exist.", "Failure");
					} catch (IOException e) {
						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								"IO error.", "Failure");
					}
				}
			}
		});
		add(button_add_ltl);

		panel_choreographies = new JPanel();
		//panel_choreographies.setPreferredSize(new Dimension(770, 120));

		JScrollPane scrollPane = new JScrollPane(panel_choreographies);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 86, 770, 454);
		scrollPane.setViewportView(panel_choreographies);
		this.add(scrollPane);
		
		List<BPMNChoreography> chors = ltlManager.getAllBPMN();
		panel_choreographies.setLayout(new GridLayout(chors.size(), 1));
		Iterator<BPMNChoreography> it = chors.iterator();
		BPMNChoreography chor = null;
		while(it.hasNext()) {
			chor = it.next();
			panel_choreographies.add(new ChoreographyItemPanel(chor));
		}
		panel_choreographies.validate();

		this.validate();
	}
}