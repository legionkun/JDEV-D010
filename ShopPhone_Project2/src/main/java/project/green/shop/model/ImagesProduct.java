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
	    @JoinColumn(name = "id", insertable = false, updatable = false)
		private Product product;

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getImage() {
			return image;
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
		
		

}
