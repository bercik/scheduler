package scheduler;

import java.awt.EventQueue;

//klasa wywołujaca okienko
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				new Window();
			}
			}
		);
	}

}
