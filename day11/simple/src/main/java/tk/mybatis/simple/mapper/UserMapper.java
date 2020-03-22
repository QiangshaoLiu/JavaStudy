package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.model.SysRole;
import java.util.List;

public interface UserMapper {
	//inquire user by ID
	SysUser selectById(Long id);
	//inquire all user
	List<SysUser> selectAll();
	//get role information by user id
	List<SysRole> selectRolesByUserId(Long userId);
	
	int insert (SysUser sysUser);
}
