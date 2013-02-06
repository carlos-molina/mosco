package ncl.b1037041.gui.parts;

import java.awt.BorderLayout;

import javax.swing.*;

import ncl.b1037041.LTL.entites.BPMNChoreography;

@SuppressWarnings("serial")
public class BPMNImageFrame extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private Icon icon;

	public BPMNImageFrame(BPMNChoreography bpmn) {
		setTitle(bpmn.getName());	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane(contentPane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getViewport().add(contentPane);
		
		this.add(scroll);
		label = new JLabel();
		contentPane.add(label);

		icon = new ImageIcon(bpmn.getImagePath());
		label.setIcon(icon);
	}
}
