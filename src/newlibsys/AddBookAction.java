/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newlibsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static newlibsys.CardPanel.addProject;

 class PatternCheck {
    Pattern pNum,p;
    Matcher m;
    Pattern pLetter;
    public PatternCheck() {
   p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
   pNum=Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
   pLetter=Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
}
    public boolean checkNum(String args)
    {
         m=pNum.matcher(args);
           return m.find();
           
      
    }
      public boolean check(String args)
    {
   
            m=p.matcher(args);
           return m.find();
               
     }
         public boolean checkLetter(String args)
    {
   
            m=pLetter.matcher(args);
           return m.find();
               
     }
       
         
}


public class AddBookAction  extends MouseAdapter implements ActionListener{
 String bookID,bookName,author,edition,publication;  
 HashMap numMap,map;
 AddBooks addBook;
    @Override
    public void actionPerformed(ActionEvent e) {
        
           addBook=CardPanel.addBook;
            Model addBookAction=NewLibsys.model;
            addBook.addMouseListener(this);
            PatternCheck pchk=new PatternCheck();
            
             bookID=addBook.book_id.getText();
             bookName=addBook.book_name.getText();
             author=addBook.author.getText();
             edition=addBook.edition.getText();
             publication=addBook.publication.getText();
           
     Model.Bookinfo book =new Model.Bookinfo(bookID,bookName,author,edition,publication);
     if(pchk.check(bookID)){
         addBook.message.setText("Invalid Entry : BOOK ID");
     }
     else if(pchk.checkLetter(author) || pchk.checkLetter(bookName) || pchk.checkLetter(publication)){
         addBook.message.setText("Invalid Entry @ BookName or Author or Publication");
         
     }
     else if(pchk.checkNum(edition)){
         addBook.message.setText("Invalid Entry :Edition");
         
     }
     else if(addBookAction.addBook(book))
       {
           addBook.author.setText("");
           addBook.book_id.setText("");
           addBook.book_name.setText("");
           addBook.edition.setText("");
           addBook.publication.setText("");
           addBook.message.setText("Book sucessfully added to Database");
           addBook.book_id.requestFocusInWindow();
       }
       else
       {
           addBook.message.setText("Book not added !! check Entries");
       }
            
          
    }
    @Override
    public void mousePressed(MouseEvent e) {
      
          CardPanel.addBook.message.setText("");
    }
    
}
class LoginAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
       Model loginAction=NewLibsys.model;
       AdminLogin adminLogin=CardPanel.adminLogin;
       CardPanel cp=NewLibsys.cp;
       
     NewLibsys.loggedin=loginAction.adminLogin(adminLogin.uname.getText(),new String(adminLogin.password.getPassword()));
       System.out.print(NewLibsys.loggedin);
      if(NewLibsys.loggedin){
        cp.showPanel("adminPanel");     
           CardPanel.adminOpt.setEnabled(NewLibsys.loggedin);
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
    }
    
}

 class AddMemberAction  extends MouseAdapter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        AddMember addMem=CardPanel.addMember;
        Model addMemberAction=NewLibsys.model;
        String mmbrID,mmbrName;
        mmbrID=addMem.mmbr_id.getText();
        mmbrName=addMem.mmbr_name.getText();
        addMem.addMouseListener(this);
        PatternCheck pchk=new PatternCheck();
        if(pchk.check(mmbrID))
            addMem.message.setText("Invalid entry : MemberID");
       else if(pchk.checkLetter(mmbrName))
            addMem.message.setText("Invalid entry : Member Name");
        else if(addMemberAction.addMember(mmbrID,mmbrName)){
            
                addMem.mmbr_id.setText("");
                addMem.mmbr_name.setText("");
                addMem.mmbr_id.requestFocusInWindow();
                addMem.message.setText("Member details added successfully.");
           
        }
       else
       {
           addMem.message.setText("Member not added !! check Entries.");
       }
          
    }
    @Override
    public void mousePressed(MouseEvent e) {
      CardPanel.addMember.message.setText("");
    }
    
}
 class AddProjectAction extends MouseAdapter implements ActionListener{
@Override
    public void actionPerformed(ActionEvent e) {
            
           AddProjects addProj=CardPanel.addProject;
           addProj.message.setText("");
           addProj.addMouseListener(this);
           Model addProjAction=NewLibsys.model;
           String Pid= addProj.proj_id.getText();
           String title= addProj.title.getText();
           String author= addProj.author.getText();
           String guide=  addProj.guide.getText();
           String programme= addProj.programme.getText();
           String year= addProj.year.getText();
           PatternCheck pchk=new PatternCheck();
           Model.ProjectReportinfo proj;
           
    proj = new Model.ProjectReportinfo(Pid,title,author,guide,programme,year);
        if(pchk.check(Pid))
            addProj.message.setText("Invalid Entry : Project ID");
        else if(pchk.checkLetter(title) || pchk.checkLetter(author) || pchk.checkLetter(guide))            
            addProj.message.setText("Invalid Entry : Project Name or Guide or Author");
        else if(pchk.checkNum(year) || year.length()!=4)
             addProj.message.setText("Invalid Entry : Year");
        else if(addProjAction.addProjectReport(proj)){
            CardPanel.addProject.message.setText("Project details sucessfully added.");
             addProject.project.setSelected(true);
                 addProject.proj_id.setText("");
                  addProject.proj_id.requestFocusInWindow();
                 addProject.programme.setText("");
                 addProject.author.setText("");
                 addProject.title.setText("");
                 addProject.guide.setText("");
                 addProject.year.setText("");
                 addProject.title.setText("");
                 
       }
        else
           addProj.message.setText("Project not added !! check Entries.");
       
         
    }
@Override
     public void mousePressed(MouseEvent e) {
      
          CardPanel.addProject.message.setText("");
    }
    
}

class IssueBookAction extends MouseAdapter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
           BookIssue issue=CardPanel.issue;
           issue.message.setText("");
        Model issueAction=NewLibsys.model;
        PatternCheck pchk=new PatternCheck();
      String bookID = issue.bookID.getText();
      String mmbrID =issue.mmbrID.getText();
      if(pchk.check(bookID) || pchk.check(mmbrID))
           issue.message.setText("Invalid Entries");
    else if(issueAction.issueBook(bookID,mmbrID))
     {
         issue.bookID.setText("");
         issue.bookID.requestFocusInWindow();
         issue.mmbrID.setText("");
         issue.message.setText("Book Issued.");
         
     }
     else
     {  
         issue.message.setText("Book not Available");
     }
    }
    @Override
     public void mousePressed(MouseEvent e) {
      
          CardPanel.issue.message.setText("");
    }
         
    }
    

class ReturnBookAction extends MouseAdapter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        BookReturn returnb=CardPanel.returnB;
        returnb.message.setText("");
        Model ReturnBookAction=NewLibsys.model;
        PatternCheck pchk =new PatternCheck();
        if(pchk.check(returnb.bookid.getText()))
            returnb.message.setText("Invalid Entries");
        if(ReturnBookAction.returnBook(returnb.bookid.getText()))
        {   
         returnb.bookid.setText("");
          returnb.bookid.requestFocusInWindow();
         returnb.message.setText("Book returnned");
        }
        else
            returnb.message.setText("Book not borrowed yet!!");
            
    }
    @Override
     public void mousePressed(MouseEvent e) {
      
          CardPanel.returnB.message.setText("");
    }
    
}

class SearchReportAction implements ActionListener{
    static ResultSet rs;
    @Override
    public void actionPerformed(ActionEvent e) {
            
           GenerateReport report=CardPanel.report;
           Model generateAction=NewLibsys.model;
     
        
         
          if(report.book.isSelected()){
              
           rs=generateAction.generateReportBook(report.mmbrID.getText());
            new DBtoTable().DBtoTable(rs,report.reportTable);
          }
            else if(report.project.isSelected()){
            rs=generateAction.generateReportProject(report.mmbrID.getText());
            new DBtoTable().DBtoTable(rs,report.reportTable);
            }
         
    }
    
 }
class IssueProjectAction extends MouseAdapter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        ProjectIssue issue=CardPanel.prjIssue;
        Model issueAction=NewLibsys.model;
        issue.message.setText("");
        PatternCheck pchk =new PatternCheck();
        
        if(pchk.check(issue.prjID.getText())||pchk.check(issue.mmbrID.getText()))
            issue.message.setText("Invalid Entries ");  
      else if(issueAction.issueProjectReport(issue.prjID.getText(),issue.mmbrID.getText()))
        {
         issue.prjID.setText("");
         issue.mmbrID.setText("");
         issue.message.setText("Project Issued.");  
        }
     else
     {
         issue.message.setText("Project not issued !! Check Entries.");
     }
       
         
    }
    @Override
     public void mousePressed(MouseEvent e) {
      
          CardPanel.prjIssue.message.setText("");
    }
}

class ReturnProjectAction extends MouseAdapter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        ProjectReturn returnp=CardPanel.returnP;
        PatternCheck pchk =new PatternCheck();
        Model returnProjectAction=NewLibsys.model;
         if(pchk.check(returnp.projID.getText()))
            ProjectReturn.message.setText("Invalid Entries ");  
      
      else  if(returnProjectAction.returnProjectReport(returnp.projID.getText()))
             {   
         returnp.projID.setText("");
         ProjectReturn.message.setText("Project returnned.");
        }
        else
            ProjectReturn.message.setText("Project not borrowed Yet!!");
         
    }
    @Override
     public void mousePressed(MouseEvent e) {
      
          CardPanel.returnP.message.setText("");
    }
    
}
class SearchBookAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        SearchBooks sBooks=CardPanel.sBooks;
        Model SearchBookAction=NewLibsys.model;
       ResultSet rs= SearchBookAction.searchBooks(sBooks.bookName.getText());
       new DBtoTable().DBtoTable(rs,sBooks.searchBook_tb);
         
    }
    
}
class SearchProjectAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
        SearchProjects sproject=CardPanel.sProjects;
        Model SearchProjectAction=NewLibsys.model;
       ResultSet rs= SearchProjectAction.searchProjectReports(sproject.title.getText(),sproject.author.getText(),sproject.guide.getText());
       new DBtoTable().DBtoTable(rs,sproject.searchPrj_tb);
         
    }
    
}
class GenerateAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            
       GenerateReport report=CardPanel.report;
        Model GenerateAction=NewLibsys.model;
        TableModel tm;
         TableToExcel te;
        String[] header=new String[25];
        JFileChooser chooser=new JFileChooser();
       int userSelection=  chooser.showSaveDialog(report);
         File log;
          String fileName;
         if(JFileChooser.APPROVE_OPTION ==userSelection){
             if(!chooser.getSelectedFile().toString().endsWith(".xls"))
              fileName=chooser.getSelectedFile().toString()+".xls";
             else 
                fileName=chooser.getSelectedFile().toString(); 
        log = new File(fileName);
      
        
        if(report.book.isSelected())
        {
           te=new TableToExcel(report.reportTable, null,"BookLog");
           try {
                ResultSetMetaData rsmd = SearchReportAction.rs.getMetaData();
               tm=(DefaultTableModel)report.reportTable.getModel();
              int j=tm.getColumnCount();
               for(int k=0;k<j;k++)
                    header[k]=(rsmd.getColumnName(k+1));
                    
               te.setCustomTitles(Arrays.asList(header));
               te.generate(log);
           } catch (Exception ex) {
               Logger.getLogger(GenerateAction.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        else if(report.project.isSelected())
        {
             te=new TableToExcel(report.reportTable, null,"ProjectLog");
           try {
                ResultSetMetaData rsmd = SearchReportAction.rs.getMetaData();
               tm=(DefaultTableModel)report.reportTable.getModel();
              int j=tm.getColumnCount();
               for(int k=0;k<j;k++)
                    header[k]=(rsmd.getColumnName(k+1));
                    
               te.setCustomTitles(Arrays.asList(header));
               te.generate(log);
           } catch (Exception ex) {
               Logger.getLogger(GenerateAction.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       
       
    }
    
}
}
class DBtoTable 
{
     public void DBtoTable(ResultSet rs,JTable t)
    {
        try{
         ResultSetMetaData rsmd = rs.getMetaData();
           
          String data=null;
                //if the result set exists
                int i = 0;
                while ( rs.next() ) //step through the result set
                    i++;//count rows
                int j=rsmd.getColumnCount();
                System.out.println(j);
//Define TableModel
                TableModel tmodel = new DefaultTableModel(i+1, j);//i+1 rows , 1 column
                
                t.setModel(tmodel);
                    for(int k=0;k<j;k++)
                       t.getColumnModel().getColumn(k).setHeaderValue(rsmd.getColumnName(k+1));
            
                int l=0;
                 rs.beforeFirst();
                while (rs.next()) {
                  
                    for(int k=0;k<j;k++){
                         if(k==j-1 && rs.getString(k+1).equals("0"))
                             data="NO";
                         else if(k==j-1 && rs.getString(k+1).equals("1"))
                              data="YES";   
                         else
                             data = rs.getString(k+1); 
                   
                    t.setValueAt(data,l,k);
                  }
                 l++;
               } 
           }
               catch (SQLException ex) {
                   Logger.getLogger(SearchReportAction.class.getName()).log(Level.SEVERE, null, ex);
               }
}}
 

   