package Mechanics;

public class Bank {
	private static int totalMoney;

	public Bank(int money) {
		setMoney(money);
	}
	public Bank() {
		setMoney(0);
	}

	public static int getMoney() {
		return totalMoney;
	}

	public static void addMoney(int money) {
		totalMoney += money;
	}

	public static void takeMoney(int money) {
		totalMoney -= money;
	}

	public static void setMoney(int money) {
		totalMoney = money;
	}

	@Override
	public String toString() {
		return getMoney() + "";

	}
}
