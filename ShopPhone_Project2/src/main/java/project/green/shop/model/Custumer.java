package project.green.shop.model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import project.green.shop.auth.EnumProvider;

@Entity
@Table(name= "custumer")
public class Custumer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(name = "email1", unique = true)
		private String email1;
		private String password1;
		private String hoten;
		private String diachi1;
		@Column(name="image")
		private String Image;
		private String sdt1;		
		@Column(updatable= false)
		private Date createtime;
		@Column(updatable= false)
		private Date lasttime;
		private boolean enabled;
		@Enumerated(EnumType.STRING)
		private project.green.shop.auth.EnumProvider  auth_provider;
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name = "user_role", 
			 joinColumns = @JoinColumn (name = "id"), 
			 inverseJoinColumns = @JoinColumn (name = "idrole"))
		private Set<Role> roles = new HashSet<>();
		@Column(name="verification_code", updatable=false)
		private String varificationCode;
		public Custumer() {	}
		
		public String getVarificationCode() {
			return varificationCode;
		}
		@Transient
		public String getImage() {
			if (id == 0 && this.Image == null) {
				return null;
			}
			
			return "/custumer-photo/" + this.id + "/" + this.Image;
		}

		public void setImage(String image) {
			Image = image;
		}

		public void setVarificationCode(String varificationCode) {
			this.varificationCode = varificationCode;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmail1() {
			return email1;
		}
		public void setEmail1(String email1) {
			this.email1 = email1;
		}
		public String getPassword1() {
			return password1;
		}
		public void setPassword1(String password1) {
			this.password1 = password1;
		}
		public String getHoten() {
			return hoten;
		}
		public void setHoten(String hoten) {
			this.hoten = hoten;
		}
		public String getDiachi1() {
			return diachi1;
		}
		public void setDiachi1(String diachi1) {
			this.diachi1 = diachi1;
		}
		public String getSdt1() {
			return sdt1;
		}
		public void setSdt1(String sdt1) {
			this.sdt1 = sdt1;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public project.green.shop.auth.EnumProvider getAuth_provider() {
			return auth_provider;
		}
		public void setAuth_provider(project.green.shop.auth.EnumProvider auth_provider) {
			this.auth_provider = auth_provider;
		}
		public Date getLasttime() {
			return lasttime;
		}
		public void setLasttime(Date lasttime) {
			this.lasttime = lasttime;
		}
		public Set<Role> getRoles() {
			return roles;
		}
		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}


		public Custumer(int id, String email1, String password1, String hoten, String diachi1, String image,
				String sdt1, Date createtime, Date lasttime, boolean enabled, EnumProvider auth_provider,
				Set<Role> roles, String varificationCode) {
			super();
			this.id = id;
			this.email1 = email1;
			this.password1 = password1;
			this.hoten = hoten;
			this.diachi1 = diachi1;
			this.Image = image;
			this.sdt1 = sdt1;
			this.createtime = createtime;
			this.lasttime = lasttime;
			this.enabled = enabled;
			this.auth_provider = auth_provider;
			this.roles = roles;
			this.varificationCode = varificationCode;
		}
		
		
		
		
}
