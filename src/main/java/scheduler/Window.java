package scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//okienko głowne
public class Window extends JFrame implements ActionListener{

private final int sizeX=600;
private final int sizeY=600;

private JButton add;
private JButton load;
private JLabel info;
private JPanel panel;
Menu_Panel menu;
Show_Panel show;
	
public Window(){
	super("Plan zajęć");
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(sizeX,sizeY);
	super.setVisible(true);
	super.setLayout(new BorderLayout());
	super.setResizable(false);
	
	menu=new Menu_Panel();
	show=new Show_Panel();
	super.add(show,BorderLayout.WEST);
	super.add(menu,BorderLayout.EAST);
}

public void actionPerformed(ActionEvent e){
	
	}

}
