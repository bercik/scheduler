package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Show_Panel extends JPanel {
	
static JList subjects;
private final Color color=new Color(192,224,255);

public Show_Panel(){
	super.setBackground(color);
	super.setPreferredSize(new Dimension(300,300));
	super.setLayout(new BorderLayout());
	JLabel info=new JLabel("Lista przedmiotów");
	info.setFont(new Font("Comic Sans",Font.BOLD,18));
	info.setAlignmentX(CENTER_ALIGNMENT);
	info.setAlignmentY(CENTER_ALIGNMENT);
	super.add(info,BorderLayout.NORTH);

	subjects=new JList();
	
	super.add(subjects,BorderLayout.CENTER);
	
}
}
