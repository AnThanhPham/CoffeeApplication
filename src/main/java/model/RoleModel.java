package model;

public class RoleModel {
	private Integer RoleID;
	private String creator;
	private String roleName;
	
	public RoleModel() {
		
	}
	
	public RoleModel(Integer roleID, String creator, String roleName) {
		RoleID = roleID;
		this.creator = creator;
		this.roleName = roleName;
	}

	public Integer getRoleID() {
		return RoleID;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
