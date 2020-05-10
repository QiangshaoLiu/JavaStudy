package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tk.mybatis.simple.type.Enabled;

public class SysRole implements Serializable {
	private static final long serialVersionUID = 6320941908222932112L;
	//user ID
	private Long Id;
	//name of role
	private String roleName;
	//valid flag
	private Enabled enabled;
	//creator
	private Long createBy;
	//create time
	private Date createTime;
	
	private SysUser user;
	
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Enabled getEnabled() {
		return enabled;
	}
	public void setEnabled(Enabled enabled) {
		this.enabled = enabled;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/*
	 * 用户包含的权限列表
	 */
	List<SysPrivilege> privilegeList;

	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
}
