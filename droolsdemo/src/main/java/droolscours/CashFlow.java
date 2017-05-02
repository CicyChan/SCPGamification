package droolscours;

import java.text.DateFormat;
import java.util.Date;

public class CashFlow {
	
	public static int CREDIT = 1;
	public static int DEBIT = 2;
	
	private Date mvDate;
	private double amount;
	private int type;
	private long accountNo;
	
	public Date getMyDate() {
		return mvDate;
	}
	public void setMyDate(Date myDate) {
		this.mvDate = myDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		StringBuffer buff= new StringBuffer();
		
		buff.append("------CashFlow-----)\n");
		buff.append("Account no is " + this.getAccountNo() + "\n");
		
		if(this.mvDate != null){
			buff.append("Movement Date= "
					+ DateFormat.getDateInstance().format(this.mvDate)
					+ "\n");
		}else{
			buff.append("No Movement Date was set"+ "\n");
		}
		
		buff.append("Movenet Amount= " + this.amount + "\n");
		buff.append("-------CashFlow End --)");
		
		return buff.toString();
	}
	

}
