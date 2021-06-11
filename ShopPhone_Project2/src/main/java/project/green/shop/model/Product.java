package project.green.shop.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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
@Entity
@Table(name= "products")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
		@Column(name="name_")
		private String name_product;
		
		@Column(name="description_")
		private String Description;
		
		@Column(name="image")
		private String Image;
		
		private Date date_create;
		private Date date_update;
		
		private boolean best_sell;
		private boolean new_product;
		
		private double price;
		private double price_sell;
		private int quantity;

		@OneToMany(targetEntity=Descriptions.class, mappedBy="Id",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	    private Set<Descriptions> des =new HashSet<>();
		@OneToMany(targetEntity=ImagesProduct.class, mappedBy="Id",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	    private Set<ImagesProduct> ima= new HashSet<>();
		
		@ManyToOne 
	    @JoinColumn(name = "id_manu", insertable = false, updatable = false)
		private Theloai theloai;
		
		@ManyToOne 
	    @JoinColumn(name = "id_theloai", insertable = false, updatable = false)
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
		
		public Set<Descriptions> getDes() {
			return des;
		}

		public void setDes(Set<Descriptions> des) {
			this.des = des;
		}

		public Set<ImagesProduct> getIma() {
			return ima;
		}

		public void setIma(Set<ImagesProduct> ima) {
			this.ima = ima;
		}

		public Product() {}

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

		@Transient
		public String getImageProduct() {
			if (Id == 0 && this.Image == null) {
				return null;
			}
			return "/image-products/" + this.Id + "/" + this.Image;
		}
		
		
}
