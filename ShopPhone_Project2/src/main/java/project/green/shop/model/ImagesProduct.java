package project.green.shop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name= "images_product")
public class ImagesProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
		
		private String image;
		
		public ImagesProduct() {}
		
		@ManyToOne 
	    @JoinColumn(name = "product_id", insertable = false, updatable = false)
		private Product product;
		
		private int product_code;
		
		
		public int getProduct_code() {
			return product_code;
		}

		public void setProduct_code(int product_code) {
			this.product_code = product_code;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getImage() {
			return "http://localhost:9090/images" + "/" + this.image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
		
		
		@Override
		public String toString() {
			return "ImagesProduct [Id=" + product.getId() + ", image=" + image + ", product=" + product + ", product_code="
					+ product_code + "]";
		}

		public ImagesProduct(int id, String image, Product product, int product_code) {
			super();
			Id = id;
			this.image = image;
			this.product = product;
			this.product_code = product_code;
		}

		@Transient
		public String getImagessProduct() {
			if (product_code==0 && this.image == null) {
				System.out.println(" Null ");
				return null;
			}
			System.out.println("  "+product_code );
			System.out.println("  "+image );
			return "http://localhost:9090/images" + "/" + this.image;
		}
}
