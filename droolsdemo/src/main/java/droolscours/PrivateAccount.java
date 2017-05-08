package droolscours;

public class PrivateAccount extends Account {
	private Customer owner;

	public PrivateAccount() {
		super();
	}

	public PrivateAccount(Customer owner) {
		super();
		this.owner = owner;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append("-----Private Account-)");
		buff.append(super.toString());
		if (this.owner != null) {
			buff.append(this.owner.toString());
		}
		buff.append("-----Private Account end-)");

		return buff.toString();
	}

}
