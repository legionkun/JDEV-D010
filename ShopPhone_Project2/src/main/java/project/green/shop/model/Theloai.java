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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "theloai")
public class Theloai implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
		private String operating_system;
		
		@OneToMany(targetEntity=Product.class, mappedBy="Id",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		@JsonIgnore
	    private Set<Product> pro =new HashSet<>();
		
		public Theloai () {}
		
		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getOperating_system() {
			return operating_system;
		}

		public void setOperating_system(String operating_system) {
			this.operating_system = operating_system;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Set<Product> getPro() {
			return pro;
		}

		public void setPro(Set<Product> pro) {
			this.pro = pro;
		}
		
		
}
