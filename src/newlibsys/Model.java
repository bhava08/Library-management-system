/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Balaji
 */
package newlibsys;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



 public class Model{
	
	private Connection con;
	private PreparedStatement statement;
	
	public Model(){
		
		try {
                        
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");	
			createDatabase();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createDatabase() {
		String database = "CREATE DATABASE IF NOT EXISTS cselibrary";
		String bookList = "CREATE TABLE IF NOT EXISTS `booklist` ( `BookID` varchar(30) NOT NULL, `Title` text NOT NULL, `Author` text NOT NULL, `Edition` int(2) NOT NULL, `Publication` text NOT NULL, `Availability` tinyint(1) NOT NULL, PRIMARY KEY (`BookID`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		String projectReportList = "CREATE TABLE IF NOT EXISTS `projectreportlist` ( `ProjectID` varchar(30) NOT NULL, `Title` text NOT NULL, `Author` text NOT NULL, `Guide` text NOT NULL, `Programme` text NOT NULL, `Year` year(4) NOT NULL, `Availability` tinyint(1) NOT NULL, PRIMARY KEY (`ProjectID`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		String members = "CREATE TABLE IF NOT EXISTS `members` ( `MemberID` varchar(30) NOT NULL, `Name` text NOT NULL, PRIMARY KEY (`MemberID`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		String admin = "CREATE TABLE IF NOT EXISTS `admin` ( `username` varchar(20) NOT NULL, `password` varchar(20) NOT NULL, PRIMARY KEY (`username`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		String booklog = "CREATE TABLE IF NOT EXISTS `booklog` ( `IssueID` int(5) NOT NULL AUTO_INCREMENT, `BookID` varchar(30) NOT NULL, `MemberID` varchar(30) NOT NULL, `IssueDate` date NOT NULL, `ReturnDate` date DEFAULT NULL, `Availability` tinyint(1) NOT NULL, PRIMARY KEY (`IssueID`), FOREIGN KEY (`BookID`) references booklist(`BookID`), FOREIGN KEY (`MemberID`) references members(`MemberID`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1";
    String projectlog = "CREATE TABLE IF NOT EXISTS `projectreportlog` ( `IssueID` int(30) NOT NULL AUTO_INCREMENT, `MemberID` varchar(30) NOT NULL, `ProjectID` varchar(30) NOT NULL, `IssueDate` date NOT NULL, `ReturnDate` date DEFAULT NULL, `Availability` tinyint(1) NOT NULL, PRIMARY KEY (`IssueID`), FOREIGN KEY (`ProjectID`) references projectreportlist(`ProjectID`), FOREIGN KEY (`MemberID`) references members(`MemberID`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";
	String adminLogin="INSERT INTO admin VALUES(?,?)";
       
		try {
			statement = con.prepareStatement(database);
			statement.executeUpdate();
			System.out.println("Created Database");
			
			//Logging into Database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cselibrary?zeroDateTimeBehavior=convertToNull","root","");
			System.out.println("Logged into db");
			//Creating tables inside database
			statement = con.prepareStatement(bookList);
			statement.executeUpdate();
			System.out.println("Created BookList");
			statement = con.prepareStatement(projectReportList);
			statement.executeUpdate();
			System.out.println("Created Project Report List");
			statement = con.prepareStatement(members);
			statement.executeUpdate();
			System.out.println("Created Members");
			statement = con.prepareStatement(admin);
			statement.executeUpdate();
			System.out.println("Created Admin");
			statement = con.prepareStatement(booklog);
			statement.executeUpdate();
			System.out.println("Created Book log");
			statement = con.prepareStatement(projectlog);
			statement.executeUpdate();
			System.out.println("Created project report log");
                        statement = con.prepareStatement("select * from admin");
                   
			ResultSet rs=statement.executeQuery();
                        if(!rs.next())
                        {
                             statement = con.prepareStatement(adminLogin);
                             statement.setString(1, "admin");
                             statement.setString(2, "admin");
			statement.executeUpdate();
                        }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	ResultSet searchBooks (String term){
		ResultSet rs = null ;
		String temp = "%" + term + "%";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("select * from booklist where title like ? or author like ? ");
			statement.setString(1,temp);
			statement.setString(2,temp);
			rs = statement.executeQuery();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	ResultSet searchProjectReports (String title,String author, String guide){
		//pass ""(nothing) to search for empty 
		ResultSet rs = null ;
		title = "%" + title + "%";
		author = "%" + author + "%";
		guide = "%" + guide + "%";
		          System.out.println(title+author+guide);
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("select * from projectreportlist where title like ? and author like ? and guide like ?");
			statement.setString(1,title);
			statement.setString(2,author);
			statement.setString(3,guide);
			
			rs = statement.executeQuery();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	boolean adminLogin(String user, String pass){
		ResultSet rs = null ;
		System.out.println(user + pass);
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("select * from admin where username =  ? and password = ?");
                        statement.setString(1,user);
                        statement.setString(2,pass);
                        rs = statement.executeQuery();

                
                        return (rs.next());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	boolean addMember(String id, String name){
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("insert into members values( ? , ?)");
			statement.setString(1, id);
			statement.setString(2, name);
			if(statement.executeUpdate()>0) return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.addMember.message.setText("Invalid Data entered");
			
		}
		return false;
	}

	boolean addBook(Bookinfo book){
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("insert into booklist values( ? , ? , ? , ? , ? , ? )");
			statement.setString(1, book.bookid);
			statement.setString(2, book.title);
			statement.setString(3, book.author);
                        System.out.println(book.edition);
			statement.setInt(4, Integer.parseInt(book.edition));
                        
			statement.setString(5, book.publication);
			statement.setString(6, "1");
			if(statement.executeUpdate()>0) return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.addBook.message.setText("Invalid Data entered");
			
		}
		
		return false;
	}
	
	boolean addProjectReport(ProjectReportinfo projectReport){
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("insert into projectreportlist values( ? , ? , ? , ? , ? , ? , ? )");
			statement.setString(1, projectReport.ProjectReportID);
			statement.setString(2, projectReport.title);
			statement.setString(3, projectReport.author);
			statement.setString(4, projectReport.guide);
			statement.setString(5, projectReport.programme);
			statement.setString(6, projectReport.year);
			statement.setString(7, "1");
			if(statement.executeUpdate()>0) return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.addProject.message.setText("Invalid Data entered");
			
		}
		
		return false;
	}
	
	ResultSet generateReportBook(String memberID){
		ResultSet rs = null ;
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("select BookID,MemberID,IssueDate,ReturnDate,Availability from booklog where memberid like ? and Availability=0");
			if(memberID.trim().length()>0) statement.setString(1,memberID);
			else statement.setString(1, "%");
			rs = statement.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	ResultSet generateReportProject(String memberID){
		ResultSet rs = null ;
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("select MemberID,ProjectID,IssueDate,ReturnDate,Availability from projectreportlog where memberid like ? and Availability=0");
			if(memberID.trim().length()>0) statement.setString(1,memberID);
			else statement.setString(1, "%");
			rs = statement.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	boolean issueBook(String bookID, String memberID){
		PreparedStatement statement,check1,check2;
		try {   check1=con.prepareStatement("select * from booklist where BookID=? AND Availability=?");
                        check1.setString(1, bookID);
                        check1.setString(2, "1");
                         check2=con.prepareStatement("select * from members where MemberID=?");
                        check2.setString(1, memberID);
			statement = con.prepareStatement("insert into booklog(bookid,memberid,issueDate,returnDate,Availability) values( ? , ? , ? , ? ,?)");
			statement.setString(1, bookID);
			statement.setString(2, memberID);
			
			//generating date
			GregorianCalendar date = new GregorianCalendar();
			 int day, month, year;
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      month=month+1;
		      year = date.get(Calendar.YEAR);
		      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		        Date parsed = format.parse(day+"-"+month+"-"+year);
		        java.sql.Date sqld = new java.sql.Date(parsed.getTime());
			System.out.println(sqld+""+day+month+year+parsed);
			statement.setString(3, sqld.toString());
			
                        statement.setNull(4,Types.NULL);
                        statement.setString(5,"0");
			
			//change status of book in book inventory
			PreparedStatement statuschange = con.prepareStatement("update booklist set availability = ? where bookid like ?");
			statuschange.setString(1, "0");
			statuschange.setString(2, bookID);
			
                      boolean chk1,chk2,chk3;
                      chk1=check1.executeQuery().next();
                      chk2=check2.executeQuery().next();
                      chk3=statement.executeUpdate()>0;
                        
			                 System.out.println(chk1+"   "+ chk2  +" "+chk3);
			if(chk1 && chk2 && chk3) {
                            statuschange.executeUpdate();
                            return true;
                        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.issue.message.setText("Invalid Data entered");
		}
		return false;
	}
	
	boolean returnBook(String bookID){
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("update booklog set returnDate = ?,Availability = ? where bookID = ? and Availability = ?");
			
			//generating date
			GregorianCalendar date = new GregorianCalendar();
			 int day, month, year;
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      month=month+1;
		      year = date.get(Calendar.YEAR);
		      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		        Date parsed = format.parse(day+"-"+month+"-"+year);
		        java.sql.Date sqld = new java.sql.Date(parsed.getTime());
			
			statement.setString(1, sqld.toString());
			statement.setString(2, "1");
			statement.setString(3, bookID);
			statement.setString(4, "0");
			//change status of book in book inventory
			PreparedStatement statuschange = con.prepareStatement("update booklist set availability = ? where bookid like ?");
			statuschange.setString(1, "1");
			statuschange.setString(2, bookID);
			
			int chk1=statement.executeUpdate();
                        int chk2=statuschange.executeUpdate();
                        System.out.println("Book log"+chk1+"booklist"+chk2);
			if (chk1>0&& chk2>0) {
                            
                            return true;
                        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.returnB.message.setText("Invalid Data entered");
		}
		return false;
	}
	
	boolean issueProjectReport(String projID, String memberID){
		PreparedStatement statement,check1,check2;
		try {
                        check1=con.prepareStatement("select * from projectreportlist where ProjectID=? AND Availability=?");
                        check1.setString(1,projID);
                        check1.setString(2,"1");
                         check2=con.prepareStatement("select * from members where MemberID=?");
                        check2.setString(1, memberID);
			statement = con.prepareStatement("insert into projectreportlog(projectid,memberid,issuedate,returnDate,Availability) values( ? , ? , ? , ? ,?)");
			statement.setString(1, projID);
			statement.setString(2, memberID);
			
			//generating date
			GregorianCalendar date = new GregorianCalendar();
			 int day, month, year;
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      month=month+1;
		      year = date.get(Calendar.YEAR);
		      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		        Date parsed = format.parse(day+"-"+month+"-"+year);
		        java.sql.Date sqld = new java.sql.Date(parsed.getTime());
			
			statement.setString(3, sqld.toString());
                        statement.setNull(4,Types.NULL);
			statement.setString(5, "0");
			
			//change status of book in book inventory
			PreparedStatement statuschange = con.prepareStatement("update projectreportlist set availability = ? where projectid = ?");
			statuschange.setString(1, "0");
			statuschange.setString(2, projID);
			boolean chk1,chk2,chk3;
			chk1=check1.executeQuery().next();
                        chk2=check2.executeQuery().next();
                        chk3=statement.executeUpdate()>0;
                        System.out.println(chk1 +" "+ chk2 +" "+ chk3);
			if( chk1 && chk2 && chk3 ) {
                            statuschange.executeUpdate();
                            return true;
                        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.prjIssue.message.setText("Invalid Data entered");
		}
		return false;
	}
	
	
        
        boolean returnProjectReport(String projID){
		PreparedStatement statement;
		try {                                      
			statement = con.prepareStatement("update projectreportlog set returndate=?,Availability=? where projectid = ? and Availability = ?");
			
			//generating date
			GregorianCalendar date = new GregorianCalendar();
			 int day, month, year;
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      month=month+1;
		      year = date.get(Calendar.YEAR);
		      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		        Date parsed = format.parse(day+"-"+month+"-"+year);
		        java.sql.Date sqld = new java.sql.Date(parsed.getTime());
			
			statement.setString(1, sqld.toString());
			statement.setString(2, "1");
			statement.setString(3, projID);
                        statement.setString(4, "0");
			
			//change Availability of book in book inventory
			PreparedStatement statuschange = con.prepareStatement("update projectreportlist set availability = ? where projectid = ?");
			statuschange.setString(1, "1");
			statuschange.setString(2, projID);
			
			int chk1=statement.executeUpdate(),chk2=statuschange.executeUpdate();
                        System.out.println("Proj LOg"+chk1+"\n proj list"+chk2);
			if(chk1>0 && chk2>0)
                            return true;
                        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CardPanel.returnP.message.setText("Invalid Data entered");
		}
		return false;
	}

     
	
	public static class Bookinfo{
		String bookid,title,author,edition,publication;
		public Bookinfo(){}
		public Bookinfo(String bookid,String title,String author,String edition,String publication){
			this.bookid=bookid;
			this.title=title;
			this.author=author;
			this.edition=edition;
			this.publication=publication;
		}
	}
	
	public static class ProjectReportinfo{
		String ProjectReportID,title,author,guide,programme,year;
		public ProjectReportinfo(){}
		public ProjectReportinfo(String projectReportID, String title, String author, String guide, String programme, String year){
			this.ProjectReportID=projectReportID;
			this.title=title;
			this.author=author;
			this.guide=guide;
			this.programme=programme;
			this.year=year;
		}
	}
        
	
}
