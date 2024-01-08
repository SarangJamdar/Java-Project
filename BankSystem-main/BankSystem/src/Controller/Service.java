package Controller;

import java.util.Scanner;

public class Service {
	
	Scanner ip = new Scanner(System.in);
	BankDb db = new BankDb();
	int acno;
	{
		db.createCon();
	}
	
	public void displayMenu(){
		System.out.println("`````` Choose Option ``````");
		System.out.println("\t 1.New User \n\t 2.Existing User \n\t 0.Exit");
		System.out.print("Enter option : ");
		int op = ip.nextInt();
		System.out.println();

		switch(op){
			case 1:{
				createAcc();
			}
			break;
			case 2:{
				displayOption();
			}
			break;
			case 0:{
				System.exit(0);
			}
			default:{
				System.out.println("Enter Correct Option! Try Again.");
			}
			break;
		}
	}
	
	
	public Account createAcc() {
		System.out.print("Enter Your name    : ");
		String name = ip.nextLine();
		ip.next();
		System.out.print("Enter Your Address : ");
		String add = ip.nextLine();
		ip.next();
		System.out.print("Enter Your Contact : ");
		long contact = ip.nextLong();
		ip.next();
		System.out.print("Enter Your Pan no. : ");
		String pan = ip.next();
		ip.reset();
		System.out.print("Enter Your Uid no. : ");
		long uid = ip.nextLong();
		System.out.print("Enter Your PIN     : ");
		int pin = ip.nextInt();
		System.out.print("Enter Your Balance : ");
		double bal = ip.nextDouble();
		Account a = new Account(name,add,contact,pan,uid,pin,bal);
		return a;
	}

	public void displayOption(){
		int op;
		System.out.print("Enter Your Account Number : ");
		acno = ip.nextInt();
		do{ 
			System.out.println("``````````````````````````````````````````````");
			System.out.println("`````` HELLO CUSTOMER ``````");
			System.out.println("Choose Option");
			System.out.println("\t 1.WITHDRAW \n\t 2.DEPOSIT \n\t 3.CHECK BALANCE \n\t 4.CHANGE PIN \n\t 5.DISPLAY DETAILS \n\t 6.UPDATE DETAILS \n\t 0.Back");
			System.out.print("Enter option : ");
			op = ip.nextInt();
			System.out.println();

			switch(op){
				case 1:{
					//withdraw	
					withdraw();
					System.out.println("\n\n\n");

				}
				break;
				case 2:{
					//deposit	
					deposit();
					System.out.println("\n\n\n");
				}
				break;
				case 3:{
					//check bal	
					displayBal();
					System.out.println("\n\n\n");
				}
				break;
				case 4:{
					//change pin
					changePin();	
					System.out.println("\n\n\n");
				}
				break;
				case 5:{
					//display details
					accDetails();	
				}
				break;
				case 6:{
					//update details
					updateDetails();	
					System.out.println("\n\n\n");
				}
				break;
				case 0:{
					System.out.println("\n\n\n");
				}
				break;
				default:{
					System.out.println("Enter Correct Option! Try Again.");
					System.out.println("``````````````````````````````````````````````");
					System.out.println("\n\n\n");

				}
				break;
			}
		}
		while(op!=0);
	}
	
	
	public boolean checkPin(int acno){
		int count = 0;
		boolean ans = false;
		do{
			if(count>2){break;}
			
			System.out.print("Enter Your pin : ");
			int vpin = ip.nextInt();
			System.out.println();
			
			if(db.getPin(acno) == vpin){
				ans = true;
			}
			else{
				System.out.println("\t Incorrect Pin! Try Again.");
			}
			count++;
		}
		while(ans == false);
		return ans;
	}
	
	
	public void withdraw() {
		System.out.println("`````````````````````````WINTHDRAW`````````````````````````");
		System.out.println("Enter the Amount : ");
		double amt = ip.nextDouble();
		if(checkPin(acno)) {
			double acbal = db.getBal(acno);
			if(amt<acbal) {
				acbal -= amt;
				db.updateBal(acbal,acno);
			}
		}
	}
	

	public void deposit() {
		System.out.println("`````````````````````````DEPOSIT CASH`````````````````````````");
		System.out.println("Enter the Amount : ");
		double amt = ip.nextDouble();
		if(checkPin(acno)) {
			double acbal = db.getBal(acno);
			acbal += amt;
			db.updateBal(acbal, acno);
		}
	}
	
	
	public void displayBal() {
		System.out.println("`````````````````````````CHECKING BALANCE`````````````````````````");
		if(checkPin(acno)) {
			double acbal = db.getBal(acno);
			System.out.println("Account Balance : "+acbal);
		}
	}
	
	
	public void accDetails() {
		if(checkPin(acno)) {
			db.dispalyAcc(acno);
		}
	}
	
	
	public void changePin() {
		if(checkPin(acno)) {
			System.out.print("Enter new Pin     : ");
			int pin = ip.nextInt();
			db.updatePin(pin,acno);
		}	
	}
	
	
	public void updateDetails(){
		int op;
		do{	
			System.out.println("\t Choose Option To Update");
			System.out.println("\t 1.Name \n\t 2.Adress \n\t 3.Contact \n\t 0.Back");

			System.out.print("enter you option : ");
			op = ip.nextInt();
			
			switch(op){
				case 1:{
					System.out.print("Enter Your Name : ");
					String name = ip.next();
					System.out.println();
					if(checkPin(acno)){
						db.updateName(name,acno);
						System.out.println("\t`````` NAME UPDATED ``````");
						System.out.println("``````````````````````````````````````````````");
						System.out.println("\n\n\n");
					}
				}
				break;
				case 2:{
					System.out.print("Enter Your Address : ");
					String add = ip.next();
					System.out.println();
					if(checkPin(acno)){
						db.updateAdd(add,acno);
						System.out.println("\t`````` ADDRESS UPDATED ``````");
						System.out.println("``````````````````````````````````````````````");
						System.out.println("\n\n\n");
					}
				}
				break;
				case 3:{
					System.out.print("Enter Your Contact : ");
					long contact = ip.nextLong();
					// ip.nextLong();
					if(checkPin(acno)){
						db.updateContact(contact, acno);
						System.out.println("\t`````` CONTACT UPDATED ``````");
						System.out.println("``````````````````````````````````````````````");
						System.out.println("\n\n\n");
					}
				}
				break;
				case 0:{

				}
				break;
				default:{
					System.out.println("Enter Correct Option! Try Again.");
				}
			}
		}
		while(op!=0);	
	}

}
