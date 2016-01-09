package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Menu_Panel extends JPanel implements ActionListener{
	JButton add;
	JButton edit;
	JButton delete;
	JButton show;
	JButton save;
	JButton load;
	JButton exit;
	JDialog dialog;
	JOptionPane info;
	Add_dialog add_dialog;
	Show_Dialog show_dialog;
	static ScheduleLocalDatabase data;
	static String filename="";

	public Menu_Panel(){
		super.setPreferredSize(new Dimension(280,300));
		super.setBackground(Color.DARK_GRAY);
		add=new JButton("Dodaj przedmiot");
		add.addActionListener(this);
		edit=new JButton("Edytuj przedmiot");
		edit.addActionListener(this);
		delete=new JButton("Usuń przedmiot");
		delete.addActionListener(this);
		show=new JButton("Pokaż plan zajęć");
		show.addActionListener(this);
		save=new JButton("Zapisz do pliku");
		save.addActionListener(this);
		load=new JButton("Otwórz z pliku");
		load.addActionListener(this);
		exit=new JButton("Wyjście");
		exit.addActionListener(this);
		super.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(add);
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(edit);
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(delete);
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(show);
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(save);
		super.add(Box.createRigidArea(new Dimension(100, 30)));
		super.add(load);
		super.add(Box.createRigidArea(new Dimension(100, 180)));
		super.add(exit);
		data=new ScheduleLocalDatabase();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==add){
			add_dialog=new Add_dialog();
			add_dialog.setModal(true);
			add_dialog.setVisible(true);
		}else if(e.getSource()==edit){
			int index=Show_Panel.subjects.getSelectedIndex();
			if(!Show_Panel.subjects.isSelectionEmpty()){
				Item item=data.getAllItems().get(index);
				add_dialog=new Edit_dialog(item);
				add_dialog.setModal(true);
				add_dialog.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(this, "Nie wybrano przedmiotu!");
			}
		}else if(e.getSource()==delete){
			if(!Show_Panel.subjects.isSelectionEmpty()){
				info=new JOptionPane("Czy jesteś pewien, ze chcesz usunąć ten przedmiot?",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
				dialog=new JDialog();
				dialog.setModal(true);
				dialog.setContentPane(info);
				dialog.setMinimumSize(new Dimension(400,150));
				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				info.addPropertyChangeListener(new PropertyChangeListener() {
				        public void propertyChange(PropertyChangeEvent e) {
				            String prop = e.getPropertyName();
				            if (dialog.isVisible() && (e.getSource() == info) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				            	int value = ((Integer)info.getValue()).intValue();
				            	if (value == JOptionPane.YES_OPTION) {
				            		int index=Show_Panel.subjects.getSelectedIndex();
				        			Item item=data.getAllItems().get(index);
				        			data.removeItem(item);
				        			DefaultListModel lista=new DefaultListModel();
				        			for(Item i:data.getAllItems()){
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
				        			if(filename.equals("")){
				        				JFileChooser fileChooser = new JFileChooser();
				        				fileChooser.setDialogTitle("Zapisz jako"); 
				        				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV(.csv)","csv"));
				        				fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
				        				int userSelection = fileChooser.showSaveDialog(Menu_Panel.this);
				        			 
				        				if (userSelection == JFileChooser.APPROVE_OPTION) {
				        					File fileToSave = fileChooser.getSelectedFile();
				        					filename=fileToSave.getPath()+ "." + ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
				        				}
				        			}
				        			try{
				        				data.save(filename);
				        			}catch(Exception ex){
				        				
				        			}
				            	} else if (value == JOptionPane.NO_OPTION) {
				            		
				            	}
				            	 dialog.setVisible(false);
				            }
				        }});
				dialog.setVisible(true);
				//dialog.pack();
			}else{
				JOptionPane.showMessageDialog(this,"Nie wybrano przedmiotu!");
			}
		}else if(e.getSource()==show){
			show_dialog=new Show_Dialog();
			show_dialog.setModal(true);
			show_dialog.setVisible(true);
		}else if(e.getSource()==save){
			if(filename.equals("")){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Zapisz jako"); 
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV(.csv)","csv"));
				fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
				int userSelection = fileChooser.showSaveDialog(this);
			 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					filename=fileToSave.getPath()+ "." + ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
				}
			}
			try{
				data.save(filename);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this,"Nie można zapisać do pliku!");
			}
		}else if(e.getSource()==load){
			String file="";
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Otwórz"); 
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV(.csv)","csv"));
			fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
			int userSelection = fileChooser.showOpenDialog(this);
		 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				file=fileToSave.getPath()+ "." + ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
			}
			try{
				data.load(file);
				DefaultListModel lista=new DefaultListModel();
    			for(Item i:data.getAllItems()){
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
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this,"Nie można załadować pliku!");
			}
		}else if(e.getSource()==exit){
			System.exit(0);
		}
	}
}
