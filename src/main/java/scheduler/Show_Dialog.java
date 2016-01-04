package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

public class Show_Dialog extends JDialog implements ActionListener{
	
	JPanel panel;
	JPanel left;
	JPanel right;
	JPanel center;
	JButton left_button;
	JButton right_button;
	JLabel daytext;
	ArrayList<JLabel> lista;
	Day d;
	private final String font="Comic Sans";
	private final Color color1=new Color(64,64,80);
	private final Color color2=new Color(192,224,240);
	
	public Show_Dialog(){
		super.setMinimumSize(new Dimension(500,500));
		panel=new JPanel(new BorderLayout());
		left=new JPanel();
		left.setBackground(color1);
		right=new JPanel();
		right.setBackground(color1);
		center=new JPanel();
		center.setLayout(new BoxLayout(center,BoxLayout.PAGE_AXIS));
		center.setBackground(color2);
		left_button=new JButton("<");
		left_button.addActionListener(this);
		right_button=new JButton(">");
		right_button.addActionListener(this);
		left.add(left_button);
		right.add(right_button);
		d=Day.Monday;
		daytext=new JLabel("Monday");
		daytext.setFont(new Font("Comic Sans",Font.BOLD,16));
		daytext.setAlignmentX(CENTER_ALIGNMENT);
		center.add(daytext);
		lista=new ArrayList<JLabel>();
		for(Item i:Menu_Panel.data.getAllItems()){
			if(i.getStart().getDay().name().equals("Monday")){
				String sh=""+i.getStart().getHour();
				String sm=""+i.getStart().getMinute();
				String eh=""+i.getEnd().getHour();
				String em=""+i.getEnd().getMinute();
				if(i.getStart().getHour()<10)sh="0"+sh;
				if(i.getStart().getMinute()<10)sm="0"+sm;
				if(i.getEnd().getHour()<10)eh="0"+eh;
				if(i.getEnd().getMinute()<10)em="0"+em;
				String fac=i.getFaculty();
				String r=i.getRoom();
				lista.add(new JLabel(""+i.getName()+", "+sh+":"+sm+"-"+eh+":"+em+", "+fac+", s."+r));
			}
		}
		for(JLabel label:lista){
			label.setFont(new Font(font,Font.ITALIC,14));
			center.add(label);
		}
		panel.add(left,BorderLayout.WEST);
		panel.add(right,BorderLayout.EAST);
		panel.add(center,BorderLayout.CENTER);
		super.setContentPane(panel);
		//super.pack();
	}
	
	public void actionPerformed(ActionEvent e){
		//poprzedni dzień
		if(e.getSource()==left_button){
			if(d==Day.Monday)
				d=Day.Sunday;
			else
				d=d.previous();
			daytext.setText(d.toString());
			print(d);
		}else if(e.getSource()==right_button){//następny dzien
			d=d.next();
			daytext.setText(d.toString());
			print(d);
		}
	
	}
	
	public void print(Day day){
		center.removeAll();
		center.add(daytext);
		lista.clear();
		for(Item i:Menu_Panel.data.getAllItems()){
			if(i.getStart().getDay().name().equals(day.toString())){
					String sh=""+i.getStart().getHour();
					String sm=""+i.getStart().getMinute();
					String eh=""+i.getEnd().getHour();
					String em=""+i.getEnd().getMinute();
					if(i.getStart().getHour()<10)sh="0"+sh;
					if(i.getStart().getMinute()<10)sm="0"+sm;
					if(i.getEnd().getHour()<10)eh="0"+eh;
					if(i.getEnd().getMinute()<10)em="0"+em;
					String fac=i.getFaculty();
					String r=i.getRoom();
					lista.add(new JLabel(""+i.getName()+", "+sh+":"+sm+"-"+eh+":"+em+", "+fac+", s."+r));
		}
		for(JLabel label:lista){
			label.setFont(new Font(font,Font.ITALIC,14));
			center.add(label);
		}
		center.repaint();
	}
}
}
