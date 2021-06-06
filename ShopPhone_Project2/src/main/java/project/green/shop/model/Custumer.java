package project.green.shop.model;



import java.io.Serializable;
import java.util.Base64;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;

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
		
		@Column(name="image",columnDefinition = "LONGBLOB")
		private byte[] Image;
		
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
		public Custumer() {	}
		
		public String getVarificationCode() {
			return varificationCode;
		}
		@Transient
		public String getImagePath() {
			if (Id == 0 && this.Image == null) {
				return null;
			}
			return "/custumer-photo/" + this.Id + "/" + this.Image;
		}
		
		@Transient
		public String getBase64Image() {
		    return  Base64.getEncoder().encodeToString(this.Image);
		}

		public byte[] getImage() {
			return Image;
		}

		public void setImage(byte[] image) {
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

	
		

		
		
}
