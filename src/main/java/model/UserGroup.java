package model;

import java.util.ArrayList;

public class UserGroup {

	private String groupName;
	private UserGroup[] subGroups;
	
	public UserGroup(String groupName) {
		this.groupName = groupName;
		subGroups = new UserGroup[0];
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public UserGroup[] getSubGroups() {
		return subGroups;
	}
	public void setSubGroups(UserGroup[] subGroups) {
		this.subGroups = subGroups;
	}
	
	public void addSubGroup(UserGroup subGroup) {
		ArrayList<UserGroup> groups = new ArrayList<>();
		for (int i = 0; i < subGroups.length; i++) {
			groups.add(subGroups[i]);
		}
		groups.add(subGroup);
		subGroups = groups.toArray(subGroups);
	}
	
}
