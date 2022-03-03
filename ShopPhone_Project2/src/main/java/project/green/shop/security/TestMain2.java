package project.green.shop.security;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestMain2 {
	String name;
	int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public TestMain2(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public TestMain2() {
	}

	public class TestMain3 {
		int id;
		int price1;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getPrice1() {
			return price1;
		}

		public void setPrice1(int price1) {
			this.price1 = price1;
		}

		public TestMain3(int id, int price1) {
			super();
			this.id = id;
			this.price1 = price1;
		}

		public TestMain3() {
		}
	}

	public class TestMain4 {
		String name;
		int price;
		LocalDate born;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getBorn() {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String date = dateFormat.format(born);
			return date;
		}

		public void setBorn(LocalDate born) {
			this.born = born;
		}

		public TestMain4(String name, int price, LocalDate born) {
			super();
			this.name = name;
			this.price = price;
			this.born = born;
		}

		public TestMain4() {
		}
	}
}
