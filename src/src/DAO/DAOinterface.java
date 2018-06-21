package DAO;

import java.util.ArrayList;

public interface DAOinterface {
	
	public boolean insert(ArrayList<Object> args);
	
	public Object retrieve(ArrayList<Object> args);
	
}
