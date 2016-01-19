package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Add_dialog extends JDialog implements ActionListener{

JButton add;
JButton cancel;
JPanel panel;
final JLabel sub=new JLabel("Nazwa przedmiotu:");
JTextField subject;
final JLabel day=new JLabel("Dzień:");
JComboBox day_list;
final JLabel hour_start=new JLabel("Godzina rozpoczęcia:");
String hours[]=new String[24];
JComboBox hour_start_list;
String minutes[]=new String[60];
JComboBox minute_start_list;
final JLabel hour_stop=new JLabel("Godzina zakończenia:");
JComboBox hour_stop_list;
JComboBox minute_stop_list;
final JLabel lead=new JLabel("Prowadzący:");
JTextField leading;
final JLabel facul=new JLabel("Wydział:");
JTextField faculty;
final JLabel r=new JLabel("Sala:");
JTextField room;
private final String font="Comic Sans";


public Add_dialog(){
	super.setTitle("Dodaj przedmiot");
	super.setPreferredSize(new Dimension(300,300));
	panel=new JPanel(new GridBagLayout());
	GridBagConstraints cs=new GridBagConstraints();
	cs.fill=GridBagConstraints.HORIZONTAL;
	for(int i=0;i<24;i++){
		String temp;
		if(i<10)temp="0"+String.valueOf(i);
		else temp=String.valueOf(i);
		hours[i]=temp;
	}
	for(int i=0;i<60;i++){
		String temp;
		if(i<10)temp="0"+String.valueOf(i);
		else temp=String.valueOf(i);
		minutes[i]=temp;
	}
	//dodawanie
	cs.gridx=0;
	cs.gridy=0;
	sub.setFont(new Font(font,Font.ITALIC,15));
	panel.add(sub,cs);
	subject=new JTextField(20);
	cs.gridx=1;
	cs.gridy=0;
	panel.add(subject,cs);
	cs.gridx=0;
	cs.gridy=1;
	day.setFont(new Font(font,Font.ITALIC,15));
	panel.add(day,cs);
	day_list=new JComboBox(Day.values());
	cs.gridx=1;
	cs.gridy=1;
	panel.add(day_list,cs);
	cs.gridx=0;
	cs.gridy=2;
	hour_start.setFont(new Font(font,Font.ITALIC,15));
	panel.add(hour_start,cs);
	hour_start_list=new JComboBox(hours);
	cs.gridx=1;
	cs.gridy=2;
	panel.add(hour_start_list,cs);
	minute_start_list=new JComboBox(minutes);
	cs.gridx=2;
	cs.gridy=2;
	panel.add(minute_start_list,cs);
	cs.gridx=0;
	cs.gridy=3;
	hour_stop.setFont(new Font(font,Font.ITALIC,15));
	panel.add(hour_stop,cs);
	hour_stop_list=new JComboBox(hours);
	cs.gridx=1;
	cs.gridy=3;
	panel.add(hour_stop_list,cs);
	minute_stop_list=new JComboBox(minutes);
	cs.gridx=2;
	cs.gridy=3;
	panel.add(minute_stop_list,cs);
	cs.gridx=0;
	cs.gridy=4;
	lead.setFont(new Font(font,Font.ITALIC,15));
	panel.add(lead,cs);
	leading=new JTextField(20);
	cs.gridx=1;
	cs.gridy=4;
	panel.add(leading,cs);
	cs.gridx=0;
	cs.gridy=5;
	facul.setFont(new Font(font,Font.ITALIC,15));
	panel.add(facul,cs);
	cs.gridx=1;
	cs.gridy=5;
	faculty=new JTextField(20);
	panel.add(faculty,cs);
	cs.gridx=0;
	cs.gridy=6;
	r.setFont(new Font(font,Font.ITALIC,15));
	panel.add(r,cs);
	room=new JTextField(20);
	cs.gridx=1;
	cs.gridy=6;
	panel.add(room,cs);
	//przyciski
	add=new JButton("Dodaj przedmiot");
	add.addActionListener(this);
	cs.gridx=0;
	cs.gridy=7;
	panel.add(add, cs);
	cancel=new JButton("Anuluj");
	cancel.addActionListener(this);
	cs.gridx=1;
	cs.gridy=7;
	panel.add(cancel,cs);
	super.setContentPane(panel);
	super.pack();
}

public void actionPerformed(ActionEvent e){
	if(e.getSource()==add){
		Item item;
		try{
		int h=Integer.parseInt((String)hour_start_list.getSelectedItem());
		int m=Integer.parseInt((String)minute_start_list.getSelectedItem());
		Day d=(Day) day_list.getSelectedItem();
		Date start=new Date(h,m,d);
		h=Integer.parseInt((String)hour_stop_list.getSelectedItem());
		m=Integer.parseInt((String)minute_stop_list.getSelectedItem());
		d=(Day) day_list.getSelectedItem();
		Date stop=new Date(h,m,d);
		String name=subject.getText();
		String lead=leading.getText();
		String r=room.getText();
		String fac=faculty.getText();
		item=new Item(start,stop,name,lead,fac,r);
		Menu_Panel.data.addItem(item);
		super.setVisible(false);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this,"Nie można dodać przedmiotu!");
		}
		
		DefaultListModel lista=new DefaultListModel();
		for(Item i:Menu_Panel.data.getAllItems()){
			String sh=""+i.getStart().getHour();
			String sm=""+i.getStart().getMinute();
			String eh=""+i.getEnd().getHour();
			String em=""+i.getEnd().getMinute();
			if(i.getStart().getHour()<10)sh="0"+sh;
			if(i.getStart().getMinute()<10)sm="0"+sm;
			if(i.getEnd().getHour()<10)eh="0"+eh;
			if(i.getEnd().getMinute()<10)em="0"+em;
			lista.addElement(""+i.getName()+","+i.getStart().getDay()+","+sh+":"+sm+"-"+eh+":"+em);
		}
		Show_Panel.subjects.setModel(lista);
		if(Menu_Panel.filename.equals("")&&Menu_Panel.saving){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Zapisz jako"); 
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV(.csv)","csv"));
			fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
			int userSelection = fileChooser.showSaveDialog(this);
		 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				Menu_Panel.filename=fileToSave.getPath();
				if(!fileToSave.getPath().endsWith(".csv"))
					Menu_Panel.filename=fileToSave.getPath()+ "." + ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
			}
			if(userSelection==JFileChooser.CANCEL_OPTION){
				Menu_Panel.saving=false;
			}
		}
		try{
			Menu_Panel.data.save(Menu_Panel.filename);
		}catch(Exception ex){
			if(Menu_Panel.saving)
				JOptionPane.showMessageDialog(this,"Błąd zapisu pliku!");
		}
	}else if(e.getSource()==cancel){
		super.setVisible(false);
	}
	
}

}
