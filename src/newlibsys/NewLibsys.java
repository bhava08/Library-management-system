/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newlibsys;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Balaji
 */
public class NewLibsys {
                JFrame mainFrame;
               static Model model;
               static boolean loggedin;
          static CardPanel cp;
     
    public NewLibsys() 
            {   
                 mainFrame=new JFrame("Libsys");
                 cp=new CardPanel();
                 
                 JPanel p= new JPanel();
                 p.add(cp);
                 p.setPreferredSize(new Dimension(489, 375));
                JScrollPane sp=new JScrollPane(p);
                    mainFrame.add(sp);
                     
                mainFrame.setVisible(true);
              
                mainFrame.pack();
                  mainFrame.setLocationRelativeTo(null);
                  mainFrame.setSize(520,500);
              if(checkMySQLService("mysql")==false && checkMySQLService("wampmysqld")==false){
            JOptionPane.showMessageDialog (null, "MySQL Service not running", "MySQL Error", JOptionPane.ERROR_MESSAGE);
System.exit(0);
}
                 model=new Model();
                
                mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                
                
            } 
                public boolean checkMySQLService(String service){
String STATE_PREFIX = "STATE              : ";
String line;
int lineno = 1;
    try 
       { 
           Process p=Runtime.getRuntime().exec("sc query \""+service+"\""); 
           p.waitFor(); 
           BufferedReader reader=new BufferedReader(
               new InputStreamReader(p.getInputStream())
           ); 
            
           while((line = reader.readLine()) != null) 
           { if(lineno==4){
               int ix = line.indexOf(STATE_PREFIX);
       	if (ix >= 0) {
       	 // compare status number to one of the states
       	 String stateStr = line.substring(ix+STATE_PREFIX.length(), ix+STATE_PREFIX.length() + 1);
       	 int state = Integer.parseInt(stateStr);
       	 switch(state) {
       	   case (1): return false;
       	   case (4): return true;
       	  }
       	}
           }
           lineno++;
         }}
            
                    catch (IOException ex) {
                        Logger.getLogger(NewLibsys.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InterruptedException ex) {
                        Logger.getLogger(NewLibsys.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return false;
       }
                
    public static void main(String[] args)  {
                    java.awt.EventQueue.invokeLater(new Runnable() {
    @Override
    public void run() {
       try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
    UIManager.put("ToolTip.background", new ColorUIResource(224, 224, 224)); 
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
       new NewLibsys();
    }
} );
                        // TODO code application logic here
                        
                       
                    
    }
    
}
