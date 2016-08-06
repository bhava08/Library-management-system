/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newlibsys;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static newlibsys.CardPanel.adminLogin;

/**
 *
 * @author Balaji
 */
 class CardL extends CardLayout
{
    public CardL() {
     
    
}
}
public class CardPanel extends javax.swing.JPanel{
    public CardLayout c1;
       static AddBooks addBook;
       static  AdminLogin adminLogin;
       static   AddMember addMember;
       static    AddProjects addProject;
       static     AdminPanel adminPanel;
       static     BookIssue issue;
       static     ProjectIssue prjIssue;
       static    BookReturn returnB ;
       static     ProjectReturn returnP;
       static     GenerateReport report;
       static     MainPanel mainPanel;
       static    SearchBooks sBooks;
       static    SearchProjects sProjects;
       boolean selectedCombo;
    /**
     * Creates new form CardPanel
     */
    public CardPanel() {
        initComponents();
        
                c1=new CardLayout();
                
                card_panel.setLayout(c1);
               
               addBook = new AddBooks();
                 adminLogin=new AdminLogin();
                 addMember=new AddMember();
                addProject= new AddProjects();
                adminPanel= new AdminPanel();
                issue = new BookIssue();
                prjIssue=new ProjectIssue();
               returnB = new BookReturn();
               returnP=new ProjectReturn();
               report= new GenerateReport();
              mainPanel=new MainPanel();
             sBooks = new SearchBooks();
             sProjects=new SearchProjects();
            
                
                card_panel.add(addBook,"addBook");
                card_panel.add(addMember,"addMember");
                card_panel.add(addProject,"addProject");
                card_panel.add(adminLogin,"adminLogin");
                card_panel.add(adminPanel,"adminPanel");
                card_panel.add(issue,"issue");
                card_panel.add(returnB,"return");
                card_panel.add(report,"report");
                card_panel.add(mainPanel,"mainPanel");
                card_panel.add(sBooks,"sBooks");
                card_panel.add(sProjects,"sProjects");
                   card_panel.add(returnP,"returnP");
                      card_panel.add(prjIssue,"pIssue");
               
                 c1.show(card_panel, "mainPanel");
                 
       
                adminLogin.login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
      // 
        Model loginAction=NewLibsys.model;
     NewLibsys.loggedin=loginAction.adminLogin(adminLogin.uname.getText(),new String(adminLogin.password.getPassword()));
       System.out.print(NewLibsys.loggedin);
      if(NewLibsys.loggedin){
        showPanel("adminPanel");     
        adminOpt.setEnabled(NewLibsys.loggedin);
      
        adminLogin.uname.setText("");
        adminLogin.password.setText("");
        adminLogin.message.setText("");
      }
      else
      {
         adminLogin.message.setText("Invalid Username and password");
         adminLogin.uname.setText("");
        adminLogin.password.setText("");
        adminLogin.uname.requestFocusInWindow();
        
      }
       
            
         }});
           
             adminPanel.issue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                adminOpt.setSelectedIndex(1);  
                 issue.book.setSelected(true);
                issue.message.setText("");
                  showPanel("issue");
                    issue.bookID.requestFocusInWindow();
            }
        });
             returnP.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                showPanel("adminPanel");
            }
        });
             report.book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              DefaultTableModel dm=(DefaultTableModel) report.reportTable.getModel();
                dm.setRowCount(0);
            }
        });
              report.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              DefaultTableModel dm=(DefaultTableModel) report.reportTable.getModel();
                dm.setRowCount(0);
            }
        });
          adminPanel.return_bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                adminOpt.setSelectedIndex(2);  
               returnB.book.setSelected(true);
               returnB.bookid.setText("");
               returnB.message.setText("");
               
          showPanel("return");
          returnB.bookid.requestFocusInWindow();
            }
        });
            adminPanel.update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                  adminOpt.setSelectedIndex(3);  
                addMember.member.setSelected(true);
                addMember.mmbr_id.setText("");
                addMember.mmbr_name.setText("");
               addMember.message.setText("");
             showPanel("addMember");
              addMember.mmbr_id.requestFocusInWindow();
            }
        });
              adminPanel.report.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                adminOpt.setSelectedIndex(4);
                DefaultTableModel dm=(DefaultTableModel) report.reportTable.getModel();
                dm.setRowCount(0);
            showPanel("report");
            }
        });
                  adminPanel.logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              adminOpt.setSelectedIndex(0);  
              options.setSelectedIndex(0);
            showPanel("mainPanel");
            NewLibsys.loggedin=false;
            adminOpt.setEnabled(false);
            }
        });
                    
                    
            addBook.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
            showPanel("adminPanel");
            }
        });
             addProject.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            showPanel("adminPanel");
            }
        });
              addMember.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            showPanel("adminPanel");
            }
        });
                issue.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            showPanel("adminPanel");
            }
        });
             
                 returnB.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            showPanel("adminPanel");
            }
        });
            report.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            showPanel("adminPanel");
            }
        });
     
          addBook.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addProject.project.setSelected(true);
                 addProject.project.setSelected(true);
                 addProject.proj_id.setText("");
               
                 addProject.programme.setText("");
                 addProject.author.setText("");
                 addProject.title.setText("");
                 addProject.guide.setText("");
                 addProject.title.setText("");
                 addProject.message.setText("");
                addProject.message.setText("");
                addProject.year.setText("");
               showPanel("addProject");
                 addProject.proj_id.requestFocusInWindow();
            }
        });
            addBook.member.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMember.member.setSelected(true);
                addMember.mmbr_id.setText("");
                
                addMember.mmbr_name.setText("");
                addMember.message.setText("");
               showPanel("addMember");
               addMember.mmbr_id.requestFocusInWindow();
            }
        });
            prjIssue.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("adminPanel");
            }
        });
            addMember.books.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBook.books.setSelected(true);
                addBook.book_id.setText("");
              
                addBook.book_name.setText("");
                addBook.author.setText("");
                addBook.edition.setText("");
                addBook.publication.setText("");
                addBook.message.setText("");
               showPanel("addBook");
                 addBook.book_id.requestFocusInWindow();
            }
        });
          addMember.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 addProject.project.setSelected(true);
                 addProject.proj_id.setText("");
                 
                 addProject.programme.setText("");
                 addProject.author.setText("");
                 addProject.title.setText("");
                 addProject.guide.setText("");
                 addProject.title.setText("");
                 addProject.message.setText("");
                 addProject.year.setText("");
               showPanel("addProject");
               addProject.proj_id.requestFocusInWindow();
            }
        });
          addProject.book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 addBook.books.setSelected(true);
                 addBook.book_id.setText("");
                 
                addBook.book_name.setText("");
                addBook.author.setText("");
                addBook.edition.setText("");
                addBook.publication.setText("");
                 addBook.message.setText("");
               showPanel("addBook");
               addBook.book_id.requestFocusInWindow();
            }
        });
          addProject.mmbr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMember.member.setSelected(true);
                addMember.mmbr_id.setText("");
                 
                addMember.mmbr_name.setText("");
                addMember.message.setText("");
               showPanel("addMember");
               addMember.mmbr_id.requestFocusInWindow();
            }
        });
          issue.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prjIssue.project.setSelected(true);
                prjIssue.prjID.setText("");
                
                prjIssue.mmbrID.setText("");
                prjIssue.message.setText("");
               showPanel("pIssue");
                prjIssue.prjID.requestFocusInWindow();
            }
        });
          prjIssue.book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                issue.book.setSelected(true);
                issue.bookID.setText("");
               
                issue.mmbrID.setText("");
                issue.message.setText("");
                showPanel("issue");
                issue.bookID.requestFocusInWindow();
               
            }
        });
          returnB.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                returnP.project.setSelected(true);
                returnP.projID.setText("");
               
                returnP.message.setText("");
               showPanel("returnP");
                returnP.projID.requestFocusInWindow();
            }
        });
          returnP.book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 returnB.book.setSelected(true);
                 returnB.bookid.setText("");
                
                 returnB.message.setText("");
               showPanel("return");
                returnB.bookid.requestFocusInWindow();
            }
        });
           sBooks.project.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dm=(DefaultTableModel) sProjects.searchPrj_tb.getModel();
                dm.setRowCount(0);
                sProjects.title.setText("");
               
                sProjects.author.setText("");
                sProjects.guide.setText("");
               sProjects.project.setSelected(true);
               showPanel("sProjects");
                sProjects.title.requestFocusInWindow();
            }
        });
           sBooks.clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) sBooks.searchBook_tb.getModel();
                sBooks.bookName.setText("");
                sBooks.bookName.requestFocusInWindow();
model.setRowCount(0);
            }
        });
           sProjects.clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) sProjects.searchPrj_tb.getModel();
                model.setRowCount(0);
                sProjects.author.setText("");
                sProjects.title.setText("");
                sProjects.guide.setText("");
                sProjects.title.requestFocusInWindow();
                
            }
        });
          sProjects.book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sBooks.book.setSelected(true);
                sBooks.bookName.setText("");
                
                DefaultTableModel dm=(DefaultTableModel) sBooks.searchBook_tb.getModel();
                dm.setRowCount(0);
               showPanel("sBooks");
               sBooks.bookName.requestFocusInWindow();
            }
        });
               adminPanel.search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                adminOpt.setSelectedIndex(0);  
                sBooks.bookName.setText("");
                 sBooks.book.setSelected(true);
                 
                DefaultTableModel dm=(DefaultTableModel) sBooks.searchBook_tb.getModel();
                dm.setRowCount(0);
               showPanel("sBooks");
               sBooks.bookName.requestFocusInWindow();
            }
        });  
          options.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               JComboBox options= (JComboBox) e.getSource();
               selectedCombo=true;
               int i=options.getSelectedIndex();
                if(i==0)
                    c1.show(card_panel, "mainPanel");
               if(i==1){
                    sBooks.book.setSelected(true);
                    sBooks.bookName.setText("");
                    
                    
                DefaultTableModel dm=(DefaultTableModel) sBooks.searchBook_tb.getModel();
                dm.setRowCount(0);
                    c1.show(card_panel, "sBooks");
                    sBooks.bookName.requestFocusInWindow();
               }
                 if(i==2){
                     if(NewLibsys.loggedin){
                    c1.show(card_panel, "adminPanel");
                    adminPanel.search.requestFocusInWindow();
                     }
                     else{
                    c1.show(card_panel, "adminLogin");
                     adminLogin.uname.requestFocusInWindow();
                     }
              
                    
                 }}});
          
          
          adminOpt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
               selectedCombo=false;
               int i=adminOpt.getSelectedIndex();
                System.out.println(i);
               switch(i){
                case 0: 
                     sBooks.book.setSelected(true);
                     sBooks.bookName.setText("");
                     sBooks.bookName.requestFocusInWindow();
                DefaultTableModel dm=(DefaultTableModel) sBooks.searchBook_tb.getModel();
                dm.setRowCount(0);
                   c1.show(card_panel, "sBooks");
                    break;
                case 1:
                    c1.show(card_panel, "issue");
                    issue.book.setSelected(true); 
                    issue.bookID.setText("");
                    issue.mmbrID.setText("");
                    issue.message.setText("");
                    issue.bookID.requestFocusInWindow();
                                        break;
                case 2:  
                    returnB.book.setSelected(true);
                    returnB.bookid.setText("");
                    c1.show(card_panel, "return");
                    returnB.message.setText("");
                    returnB.bookid.requestFocusInWindow();
                                        break;
                 case 3:
      
                    addMember.member.setSelected(true);
                    addMember.mmbr_id.setText("");
                    addMember.mmbr_name.setText("");
                    addMember.message.setText("");
                    c1.show(card_panel, "addMember");
                     addMember.mmbr_id.requestFocusInWindow();
                                         break;
                    
                  case 4:
                      report.mmbrID.setText("");
              DefaultTableModel dm1=(DefaultTableModel) report.reportTable.getModel();
                dm1.setRowCount(0);
                        c1.show(card_panel, "report");
                       report.mmbrID.requestFocusInWindow();
                                          break;
                  case 5:
                    NewLibsys.loggedin=false;
                        options.setSelectedIndex(0);
                        adminOpt.setSelectedIndex(0);
                        adminOpt.setEnabled(false);
                        System.out.println("done");
                    c1.show(card_panel, "mainPanel");
                                          break;
                   
                  
               }  
                     
                 
                  
                      
            }});
    }
    
    void showPanel(String panelName)
    {
        c1.show(card_panel, panelName);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     *d by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card_panel = new javax.swing.JPanel();
        options = new javax.swing.JComboBox();
        adminOpt = new javax.swing.JComboBox();

        javax.swing.GroupLayout card_panelLayout = new javax.swing.GroupLayout(card_panel);
        card_panel.setLayout(card_panelLayout);
        card_panelLayout.setHorizontalGroup(
            card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        card_panelLayout.setVerticalGroup(
            card_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        options.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Main Menu","Search","Login" }));

        adminOpt.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Search","Issue","Return","Update DB","Generate Report","Logout"}));
        adminOpt.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(card_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(adminOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(adminOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    Action book_bt,Proj_bt,mmbr_bt;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    static javax.swing.JComboBox adminOpt;
    public javax.swing.JPanel card_panel;
    javax.swing.JComboBox options;
    // End of variables declaration//GEN-END:variables
}

