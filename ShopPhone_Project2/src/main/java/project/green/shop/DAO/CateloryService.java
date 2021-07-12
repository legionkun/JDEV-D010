package project.green.shop.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.green.shop.model.Catelory;

@Service
public class CateloryService {
@Autowired
private CateloryRepo caterepo;

public List<Catelory> getRootCategory() {
	
	return caterepo.getRootCategory();
}
public Catelory getCateloryByName(String name) {
	return caterepo.getByName(name);
}
public List<Catelory> getParents(Catelory catelory) {
	
	List<Catelory> listParents = new ArrayList<Catelory>();
	Catelory parent = catelory.getParent();
	
	while (parent != null) {
		listParents.add(0, parent);
		parent = parent.getParent();
	}
	
	listParents.add(catelory);

	return listParents;
}
}
