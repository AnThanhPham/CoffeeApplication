package dao;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.CategoryModel;
import model.BillDetailsModel;
import model.BillModel;
import model.CustomerModel;
import model.RoleModel;
import model.UserModel;
import util.Encryption;
import util.ValidateUtils;

public class AbstractImpl {
	public static String buildSqlUpdateUser(UserModel t) {
		StringBuilder res = new StringBuilder("update user set ");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				String fieldName = field.getName();
				if(value != null) {
					if(!fieldName.equalsIgnoreCase("role") 
							&& !fieldName.equalsIgnoreCase("id")
							&& !fieldName.equalsIgnoreCase("createDate")
							&& !fieldName.equalsIgnoreCase("code")
							&& !fieldName.equalsIgnoreCase("status")) {
						if(field.getType().getName().equals("java.lang.String")) {
							res.append(fieldName+" = '"+value+"' ,");
						}else if(field.getType().getName().equals("java.lang.Integer")) {
							res.append(fieldName+" = "+value+" ,");
						}else if(field.getType().getName().equals("java.sql.Date")) {
							res.append(fieldName+" = '"+value+"' ,");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int indexComma = res.lastIndexOf(",");
		res.deleteCharAt(indexComma);
		res.append("where id = "+t.getID());
		return res.toString();
	}
	
	public static String buildSqlInsertUser(UserModel t) {
		StringBuilder res = new StringBuilder("insert into user(");
		StringBuilder sql2 = new StringBuilder("values (");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
					if(!field.getName().equalsIgnoreCase("role")) {
						res.append(field.getName()+",");
						sql2.append("'"+value+"',");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.deleteCharAt(res.lastIndexOf(","));
		sql2.deleteCharAt(sql2.lastIndexOf(","));		
		res.append(") ");
		sql2.append(") ");
		res.append(sql2);
		return res.toString();
	}
	
	public static String buildSqlUpdateCustomer(CustomerModel t) {
		StringBuilder res = new StringBuilder("update customer set ");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				String fieldName = field.getName();
				if(value != null) {
						if(!fieldName.equalsIgnoreCase("id")) {
							if(field.getType().getName().equals("java.lang.String")) {
								res.append(fieldName+" = '"+value+"' ,");
							}else if(field.getType().getName().equals("java.lang.Integer")) {
								res.append(fieldName+" = "+value+" ,");
							}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int indexComma = res.lastIndexOf(",");
		res.deleteCharAt(indexComma);
		res.append("where id = "+t.getID());
		return res.toString();
	}
	
	public static String buildSqlInsertCustomer(CustomerModel t) {
		StringBuilder res = new StringBuilder("insert into customer(");
		StringBuilder sql2 = new StringBuilder("values (");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
						res.append(field.getName()+",");
						sql2.append("'"+value+"',");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.deleteCharAt(res.lastIndexOf(","));
		sql2.deleteCharAt(sql2.lastIndexOf(","));		
		res.append(") ");
		sql2.append(") ");
		res.append(sql2);
		return res.toString();
	}
	
	public static String buildSqlUpdateCategory(CategoryModel t) {
		StringBuilder res = new StringBuilder("update category set ");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				String fieldName = field.getName();
				if(value != null) {
						if(!fieldName.equalsIgnoreCase("id")) {
							if(field.getType().getName().equals("java.lang.String")) {
								res.append(fieldName+" = '"+value+"' ,");
							}else if(field.getType().getName().equals("java.lang.Integer")) {
								res.append(fieldName+" = "+value+" ,");
							}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int indexComma = res.lastIndexOf(",");
		res.deleteCharAt(indexComma);
		res.append("where id = "+t.getID());
		return res.toString();
	}
	
	public static String buildSqlInsertCategory(CategoryModel t) {
		StringBuilder res = new StringBuilder("insert into category(");
		StringBuilder sql2 = new StringBuilder("values (");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
						res.append(field.getName()+",");
						sql2.append("'"+value+"',");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.deleteCharAt(res.lastIndexOf(","));
		sql2.deleteCharAt(sql2.lastIndexOf(","));		
		res.append(") ");
		sql2.append(") ");
		res.append(sql2);
		return res.toString();
	}
	
	public static String buildSqlUpdateBill(BillModel t) {
		StringBuilder res = new StringBuilder("update bill set ");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				String fieldName = field.getName();
				if(value != null) {
					if(!fieldName.equalsIgnoreCase("Customer")
							&& !fieldName.equalsIgnoreCase("User")
							&& !fieldName.equalsIgnoreCase("Table")) {
						if(field.getType().getName().equals("java.lang.String")) {
							res.append(fieldName+" = '"+value+"' ,");
						}else if(field.getType().getName().equals("java.lang.Integer")) {
							res.append(fieldName+" = "+value+" ,");
						}else if(field.getType().getName().equals("java.sql.Date")) {
							res.append(fieldName+" = '"+value+"' ,");
						}
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		int indexComma = res.lastIndexOf(",");
		res.deleteCharAt(indexComma);
		res.append("where id = "+t.getID());
		return res.toString();
	}
	

	public static String buildSqlInsertBill(BillModel t) {
		StringBuilder res = new StringBuilder("insert into bill(");
		StringBuilder sql2 = new StringBuilder("values (");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
					if(!field.getName().equalsIgnoreCase("Customer")
						&& !field.getName().equalsIgnoreCase("User")
						&& !field.getName().equalsIgnoreCase("Table")
						&& !field.getName().equalsIgnoreCase("Payment")
						) {
						res.append(field.getName()+",");
						sql2.append("'"+value+"',");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.deleteCharAt(res.lastIndexOf(","));
		sql2.deleteCharAt(sql2.lastIndexOf(","));		
		res.append(") ");
		sql2.append(") ");
		res.append(sql2);
		return res.toString();
	}
	
	public static String buildSqlUpdateBillDetails(BillDetailsModel t) {
		StringBuilder res = new StringBuilder("update billdetails set ");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				String fieldName = field.getName();
				if(value != null) {
					if(!fieldName.equalsIgnoreCase("Bill") 
							&& !fieldName.equalsIgnoreCase("Product")) {
						if(field.getType().getName().equals("java.lang.String")) {
							res.append(fieldName+" = '"+value+"' ,");
						}else if(field.getType().getName().equals("java.lang.Integer")) {
							res.append(fieldName+" = "+value+" ,");
						}else if(field.getType().getName().equals("java.sql.Date")) {
							res.append(fieldName+" = '"+value+"' ,");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int indexComma = res.lastIndexOf(",");
		res.deleteCharAt(indexComma);
		res.append("where id = "+t.getID());
		return res.toString();
	}
	
	public static String buildSqlInsertBillDetails(BillDetailsModel t) {
		StringBuilder res = new StringBuilder("insert into billdetails(");
		StringBuilder sql2 = new StringBuilder("values (");
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
					if(!field.getName().equalsIgnoreCase("Bill")
						&& !field.getName().equalsIgnoreCase("Product")
						) {
						res.append(field.getName()+",");
						sql2.append("'"+value+"',");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.deleteCharAt(res.lastIndexOf(","));
		sql2.deleteCharAt(sql2.lastIndexOf(","));		
		res.append(") ");
		sql2.append(") ");
		res.append(sql2);
		return res.toString();
	}
}

