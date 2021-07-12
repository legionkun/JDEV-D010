package project.green.shop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name= "catelories")
public class Catelory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int Id;
	
		@Column(name="name_catelogry")
		private String name;
		
		private boolean enabled;
		
		@OneToOne
		@JoinColumn(name = "parent_id")
		private Catelory parent;
		
		@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
		private Set<Catelory> child = new HashSet<>();

		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

	

		public Catelory getParent() {
			return parent;
		}

		public void setParent(Catelory parent) {
			this.parent = parent;
		}

		public Set<Catelory> getChild() {
			return child;
		}

		public void setChild(Set<Catelory> child) {
			this.child = child;
		}

		public Catelory() {}
		
		@Transient
		public List<Catelory> getListSubCategory() {
			List<Catelory> list = new ArrayList<>();
			for (Catelory category :child ) {
				list.add(category);
			}
			
			return list;
		}
		
}
