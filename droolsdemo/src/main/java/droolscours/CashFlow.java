package droolscours;

import java.util.Date;

public class CashFlow {
	private Date myDate;
	private double amount;
	private int type;
	private long accountNo;
	
	public Date getMyDate() {
		return myDate;
	}
	public void setMyDate(Date myDate) {
		this.myDate = myDate;
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
	

}
