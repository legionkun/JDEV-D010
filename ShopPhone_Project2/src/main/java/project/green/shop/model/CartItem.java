package project.green.shop.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "cart_items")
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade={ CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable=false)
	@JsonIgnore
	private Product product;

	
	@ManyToOne (cascade={ CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "custumer_id",nullable=false)
	@JsonIgnore
	private Custumer custumer;

	private boolean confirm;

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Custumer getCustumer() {
		return custumer;
	}

	public void setCustumer(Custumer custumer) {
		this.custumer = custumer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Transient
	public String getTotal()
	{	
		double quanlity = getQuantity();
		double price = product.getPrice1();
		double total = quanlity * price;
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(total);
		return str1;
	}
	
	@Transient
	public double getTotal1()
	{	
		double quanlity = getQuantity();
		double price = product.getPrice1();
		double total = quanlity * price;
		return total;
	}
}
