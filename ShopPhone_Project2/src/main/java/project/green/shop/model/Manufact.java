package project.green.shop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "manufact")
public class Manufact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
		private String manufacturer;
		
		@OneToMany(targetEntity=Product.class, mappedBy="Id",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	    private Set<Product> pro =new HashSet<>();
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getManufacturer() {
			return manufacturer;
		}
		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public Manufact() {}
		public Set<Product> getPro() {
			return pro;
		}
		public void setPro(Set<Product> pro) {
			this.pro = pro;
		}
		
		
}
