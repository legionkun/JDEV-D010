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
	    private Set<User> user;
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
		public Set<User> getuser() {
			return user;
		}
		public void setCustumer(Set<User> user) {
			this.user = user;
		}
		public Role(String role_name, int idrole, Set<User> user) {
			super();
			this.role_name = role_name;
			this.idrole = idrole;
			this.user = user;
		}
		
}
