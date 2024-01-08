package Controller;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDb {
	
		String url = "jdbc:mysql://localhost:3306/bankdb";
		String user = "root";
		String pass = "root";
		Connection con;
		Statement st;
		PreparedStatement pst;
		String sql;
		Scanner ip = new Scanner(System.in);
		
		
	
	public void createCon() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			st = con.createStatement();
			System.out.println("Succefully connected");
		//	con.close();
		}
		catch(Exception e) {
			
			System.out.println(e);
		}
		
	}
	
	public void dispalyAcc(int acno) {
		try {
			ResultSet rs = st.executeQuery("select * from account where acno = "+ acno +"");
			System.out.println("`````````` Account Holder Details `````````\n");
			while(rs.next()){
				System.out.println(  "\t Acc No.     : "+rs.getInt(1)+"\n\t Acc Name    : "+rs.getString(2)+"\n\t Acc Add.    : "+rs.getString(3)+"\n\t Acc Contact : "+rs.getString(4)+"\n\t Pan No.     : "+rs.getString(5)+"\n\t UID No.     : "+rs.getString(6)+"\n\t Acc Pin     : "+rs.getInt(7)+"\n\t Acc Bal     : "+rs.getDouble(8)  );
				System.out.println("\n ````````````````````````````````````````````````` \n");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveAcc() {
		try {
			Service sc = new Service();
			Account ac = sc.createAcc();
			String name = ac.name;
			String add = ac.getAdd();
			long contact = ac.getContact();
			String pan = ac.getPan();
			long uid = ac.getUid();
			int pin = ac.getPin();
			double bal = ac.getBal();
			
			sql = "Insert into account(acname,acadd,acphone,acuid,acpan,acpin,acbal) values('"+name+"','"+add+"','"+contact+"','"+uid+"','"+pan+"','"+pin+"','"+bal+"')";
			
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Account data saved");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPin(int acno) {
		int pin;
		try {
			ResultSet rs = st.executeQuery("Select `acpin` from `account` where `acno` = "+ acno);
			while(rs.next()) {
				pin = rs.getInt(1);
				return pin;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getBal(int acno) {
		double bal;
		try {
			ResultSet rs = st.executeQuery("Select `acbal` from `account` where `acno` = "+ acno);
			while(rs.next()) {
				bal = rs.getInt(1);
				return bal;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void updateName(String name, int acno) {
		sql = "update account set `acname` ='"+name+"' where `acno` = "+acno;	
		try {
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Name Updated Successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateAdd(String add, int acno) {
		sql = "update account set `acadd` ='"+add+"' where `acno` = "+acno;	
		try {
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Address Updated Successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void updateContact(long phone, int acno) {
		sql = "update account set `acphone` ="+phone+" where `acno` = "+acno;		
		try {
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Contact Updated Successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void updatePin(int pin, int acno) {
		sql = "update account set `acpin` ="+pin+" where `acno` = "+acno;		
		try {
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Pin Updated Successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void updateBal(double acbal,int acno) {
		sql = "update account set `acbal` = "+acbal+" where `acno` ="+ acno;
		try {
			pst = con.prepareStatement(sql);
			pst.execute();
			System.out.println("Transaction Successfull !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void dispalyAcc() {
		try {
			ResultSet rs = st.executeQuery("select * from account");
			System.out.println("`````````` Account Holder Details `````````\n");
			while(rs.next()){
				System.out.println(  "\t Acc No.     : "+rs.getInt(1)+"\n\t Acc Name    : "+rs.getString(2)+"\n\t Acc Add.    : "+rs.getString(3)+"\n\t Acc Contact : "+rs.getString(4)+"\n\t Pan No.     : "+rs.getString(5)+"\n\t UID No.     : "+rs.getString(6)+"\n\t Acc Pin     : "+rs.getInt(7)+"\n\t Acc Bal     : "+rs.getDouble(8)  );
				System.out.println("\n ````````````````````````````````````````````````` \n");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}