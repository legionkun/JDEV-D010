package project.green.shop.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	@Column(name = "name_")
	private String name_product;

	@Column(name = "description")
	private String Description;

	@Column(name = "image")
	private String Image;

	private Date date_create;
	private Date date_update;

	private boolean best_sell;
	private boolean new_product;

	private double price;
	private double price_sell;
	private int quantity;
	private String keyword;
	private int product_code;
	private String more_details;

	public String getMore_details() {
		return more_details;
	}

	public void setMore_details(String more_details) {
		this.more_details = more_details;
	}

	public int getProduct_code() {
		return product_code;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@OneToMany(targetEntity = ImagesProduct.class, mappedBy = "Id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<ImagesProduct> ima = new HashSet<>();

	@OneToMany(targetEntity = CartItem.class, mappedBy = "Id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<CartItem> cartitem = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "id_theloai", insertable = false, updatable = false)
	@JsonIgnore
	private Theloai theloai;

	@ManyToOne
	@JoinColumn(name = "id_manu", insertable = false, updatable = false)
	@JsonIgnore
	private Manufact manu;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName_product() {
		return name_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public Date getDate_update() {
		return date_update;
	}

	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}

	public boolean isBest_sell() {
		return best_sell;
	}

	public void setBest_sell(boolean best_sell) {
		this.best_sell = best_sell;
	}

	public boolean isNew_product() {
		return new_product;
	}

	public void setNew_product(boolean new_product) {
		this.new_product = new_product;
	}

	public String getPrice() {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(price);
		return str1;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice1() {
		return price;
	}

	public double getPrice_sell() {
		return price_sell;
	}

	public void setPrice_sell(double price_sell) {
		this.price_sell = price_sell;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<ImagesProduct> getIma() {
		return ima;
	}

	public void setIma(Set<ImagesProduct> ima) {
		this.ima = ima;
	}

	public Product() {
	}

	public Theloai getTheloai() {
		return theloai;
	}

	public void setTheloai(Theloai theloai) {
		this.theloai = theloai;
	}

	public Manufact getManu() {
		return manu;
	}

	public void setManu(Manufact manu) {
		this.manu = manu;
	}

	public Set<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(Set<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	@Transient
	public String getImageProduct() {
		if (Id == 0 && this.Image == null) {
			return null;
		}
		return "http://localhost:9090/images" + "/" + this.Image;
	}

	@Transient
	public String getImagesProduct() {
		if (Id == 0 && this.Image == null) {
			System.out.println(" Null ");
			return null;
		}
		System.out.println("model  " + Id);
		System.out.println("model  " + Image);
		return "http://localhost:9090/image-products/" + this.product_code + "/" + this.Image;
	}

	@Transient
	public String getPriceSell() {
		double pricesell = getPrice1();
		double percent = getPrice_sell();
		double totalpricesell = (pricesell / 100) * (100 - percent);
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(totalpricesell);
		return str1;
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", name_product=" + name_product + ", Description=" + Description + ", Image="
				+ Image + ", date_create=" + date_create + ", date_update=" + date_update + ", best_sell=" + best_sell
				+ ", new_product=" + new_product + ", price=" + price + ", price_sell=" + price_sell + ", quantity="
				+ quantity + ", keyword=" + keyword + ", product_code=" + product_code + ", more_details="
				+ more_details + ", ima=" + ima + ", cartitem=" + cartitem + ", theloai=" + theloai + ", manu=" + manu
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Description, Id, Image, best_sell, cartitem, date_create, date_update, ima, keyword, manu,
				more_details, name_product, new_product, price, price_sell, product_code, quantity, theloai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(Description, other.Description) && Id == other.Id && Objects.equals(Image, other.Image)
				&& best_sell == other.best_sell && Objects.equals(cartitem, other.cartitem)
				&& Objects.equals(date_create, other.date_create) && Objects.equals(date_update, other.date_update)
				&& Objects.equals(ima, other.ima) && Objects.equals(keyword, other.keyword)
				&& Objects.equals(manu, other.manu) && Objects.equals(more_details, other.more_details)
				&& Objects.equals(name_product, other.name_product) && new_product == other.new_product
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Double.doubleToLongBits(price_sell) == Double.doubleToLongBits(other.price_sell)
				&& product_code == other.product_code && quantity == other.quantity
				&& Objects.equals(theloai, other.theloai);
	}

}
