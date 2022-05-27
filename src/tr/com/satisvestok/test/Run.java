package tr.com.satisvestok.test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import tr.com.satisvestok.fe.AnaPencereFE;
import tr.com.satisvestok.fe.LoginFE;

public class Run {

	public static void main(String[] args) {
		
		try {			
			for( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {				
				if ("Nimbus".equals(info.getName())) {					
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}			
		} catch (Exception e) {			
		}
		
		SwingUtilities.invokeLater(new Runnable () {
			
			@Override
			public void run() {				
				new LoginFE();
	
			}
		});
	
	}
	
	

}
