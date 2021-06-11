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
@Table(name= "descriptions")
public class Descriptions  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
		private String tv_led;
		private String camera_frontof;
		private String camera_behind;
		
		@Column(name="cpu_")
		private String cpu;
		
		private String memmory;
		private String pin;
		private String text_details;
		
		public Descriptions() {}
		
		@ManyToOne 
	    @JoinColumn(name = "id", insertable = false, updatable = false)
		private Product product;
		
		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getTv_led() {
			return tv_led;
		}

		public void setTv_led(String tv_led) {
			this.tv_led = tv_led;
		}

		public String getCamera_frontof() {
			return camera_frontof;
		}

		public void setCamera_frontof(String camera_frontof) {
			this.camera_frontof = camera_frontof;
		}

		public String getCamera_behind() {
			return camera_behind;
		}

		public void setCamera_behind(String camera_behind) {
			this.camera_behind = camera_behind;
		}

		public String getCpu() {
			return cpu;
		}

		public void setCpu(String cpu) {
			this.cpu = cpu;
		}
		

		public String getMemmory() {
			return memmory;
		}

		public void setMemmory(String memmory) {
			this.memmory = memmory;
		}

		public String getPin() {
			return pin;
		}

		public void setPin(String pin) {
			this.pin = pin;
		}

		public String getText_details() {
			return text_details;
		}

		public void setText_details(String text_details) {
			this.text_details = text_details;
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
