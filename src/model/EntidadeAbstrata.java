package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntidadeAbstrata {
	
	private final IntegerProperty id;
	
	public EntidadeAbstrata(){
		this.id = new SimpleIntegerProperty(0);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty(){
		return id;
	}


}
