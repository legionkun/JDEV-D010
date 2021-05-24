package project.green.shop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "role_1")
public class Role implements Serializable{
		/**
	 * 
	 */
	
		private String role_name;
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idrole;
		@ManyToMany(mappedBy = "roles")
	    private Set<Custumer> custumer;
		public int getIdrole() {
			return idrole;
		}
		public void setIdrole(int idrole) {
			this.idrole = idrole;
		}
		public String getRole_name() {
			return role_name;
		}
		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public  Role() {}
		public Set<Custumer> getCustumer() {
			return custumer;
		}
		public void setCustumer(Set<Custumer> custumer) {
			this.custumer = custumer;
		}
		public Role(String role_name, int idrole, Set<Custumer> custumer) {
			super();
			this.role_name = role_name;
			this.idrole = idrole;
			this.custumer = custumer;
		}
		
}
