package project.green.shop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name= "user_")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
		private String email;
		
		@Column(name="password_")
		private String password;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
				@JoinTable(name = "user_role", 
				 joinColumns = @JoinColumn (name = "id"), 
				 inverseJoinColumns = @JoinColumn (name = "idrole"))
				 private Set<Role> roles = new HashSet<>();

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
}
