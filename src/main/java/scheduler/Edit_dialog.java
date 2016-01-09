package scheduler;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Edit_dialog extends Add_dialog {
	Item old;

	public Edit_dialog(Item item){
		super();
		old=item;
		super.subject.setText(item.getName());
		super.leading.setText(item.getLeading());
		super.room.setText(item.getRoom());
		super.faculty.setText(item.getFaculty());
		Date start=item.getStart();
		Date stop=item.getEnd();
		super.day_list.setSelectedIndex(start.getDay().ordinal());
		super.hour_start_list.setSelectedIndex(start.getHour());
		super.hour_stop_list.setSelectedIndex(stop.getHour());
		super.minute_start_list.setSelectedIndex(start.getHour());
		super.minute_stop_list.setSelectedIndex(stop.getMinute());
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
			item=new Item(start,stop,name,lead,"FiIS",r);
			Menu_Panel.data.editItem(old, item);
			super.setVisible(false);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this,
					    "Nie można edytować przedmiotu!");
			}
			
			DefaultListModel lista=new DefaultListModel();
			System.out.println(Menu_Panel.data.getAllItems().size());
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
			if(Menu_Panel.filename.equals("")){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Zapisz jako"); 
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV(.csv)","csv"));
				fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
				int userSelection = fileChooser.showSaveDialog(this);
			 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					Menu_Panel.filename=fileToSave.getPath()+ "." + ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
				}
			}
			try{
				Menu_Panel.data.save(Menu_Panel.filename);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this,"Błąd zapisu pliku!");
			}
		}else if(e.getSource()==cancel){
			super.setVisible(false);
		}
		
	}
}
