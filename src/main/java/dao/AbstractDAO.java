package dao;

import views.menu.PanelShop;

public interface AbstractDAO<T> {
	public void insert(T t);
	public void delete(T t);
	public void update(T t);
	
	  
	
}

