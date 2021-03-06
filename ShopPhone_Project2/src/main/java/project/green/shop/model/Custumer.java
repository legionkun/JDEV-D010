package project.green.shop.model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "custumer")
public class Custumer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
		@Column(name = "email1", unique = true)
		private String Email;
		
		@Column(name="password1")
		private String Password;
		
		@Column(name="hoten")
		private String Hoten;
		
		@Column(name="diachi1")
		private String Diachi;
		
		@Column(name="image")
		private String Image;
		
		@Column(name="sdt1")
		private String SDT;
		
		@Column(updatable= false)
		private Date createtime;
		@Column(updatable= false)
		private Date lasttime;
		
		private boolean enabled;
		
		@Column(name="TokenPW")
		private String TokenPassword;
		
		@Enumerated(EnumType.STRING)
		@Column(updatable=false)
		private project.green.shop.auth.EnumProvider  auth_provider;
		//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		//@JoinTable(name = "user_role", 
		//	 joinColumns = @JoinColumn (name = "id"), 
		//	 inverseJoinColumns = @JoinColumn (name = "idrole"))
		//private Set<Role> roles = new HashSet<>();
		
		@Column(name="verification_code")
		private String varificationCode;
		
		@JsonIgnore
		@OneToMany(targetEntity=CartItem.class, mappedBy="Id",cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	    private Set<CartItem> cartitem= new HashSet<>();
		
		public Custumer() {	}
		
		public String getVarificationCode() {
			return varificationCode;
		}
		@Transient
		public String getImagePath() {
			if (Id == 0 && this.Image == null) {
				return null;
			}
			return "http://localhost:9090/custumer-photo/" + this.Id + "/" + this.Image;
		}

		public String getImage() {
			return Image;
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
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getHoten() {
			return Hoten;
		}

		public void setHoten(String hoten) {
			Hoten = hoten;
		}

		public String getDiachi() {
			return Diachi;
		}

		public void setDiachi(String diachi) {
			Diachi = diachi;
		}

		public String getSDT() {
			return SDT;
		}

		public void setSDT(String sDT) {
			SDT = sDT;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Date getLasttime() {
			return lasttime;
		}

		public void setLasttime(Date lasttime) {
			this.lasttime = lasttime;
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

		public String getTokenPassword() {
			return TokenPassword;
		}

		public void setTokenPassword(String tokenPassword) {
			TokenPassword = tokenPassword;
		}

		public Set<CartItem> getCartitem() {
			return cartitem;
		}

		public void setCartitem(Set<CartItem> cartitem) {
			this.cartitem = cartitem;
		}

		@Override
		public String toString() {
			return "Custumer [Id=" + Id + ", Email=" + Email + ", Password=" + Password + ", Hoten=" + Hoten
					+ ", Diachi=" + Diachi + ", Image=" + Image + ", SDT=" + SDT + ", createtime=" + createtime
					+ ", lasttime=" + lasttime + ", enabled=" + enabled + ", TokenPassword=" + TokenPassword
					+ ", auth_provider=" + auth_provider + ", varificationCode=" + varificationCode + ", cartitem="
					+ cartitem + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(Diachi, Email, Hoten, Id, Image, Password, SDT, TokenPassword, auth_provider, cartitem,
					createtime, enabled, lasttime, varificationCode);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Custumer other = (Custumer) obj;
			return Objects.equals(Diachi, other.Diachi) && Objects.equals(Email, other.Email)
					&& Objects.equals(Hoten, other.Hoten) && Id == other.Id && Objects.equals(Image, other.Image)
					&& Objects.equals(Password, other.Password) && Objects.equals(SDT, other.SDT)
					&& Objects.equals(TokenPassword, other.TokenPassword) && auth_provider == other.auth_provider
					&& Objects.equals(cartitem, other.cartitem) && Objects.equals(createtime, other.createtime)
					&& enabled == other.enabled && Objects.equals(lasttime, other.lasttime)
					&& Objects.equals(varificationCode, other.varificationCode);
		}	
		
}
