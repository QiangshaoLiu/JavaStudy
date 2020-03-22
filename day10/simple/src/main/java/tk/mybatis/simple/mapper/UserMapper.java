package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysUser;
import java.util.List;

public interface UserMapper {
	//inquire user by ID
	SysUser selectById(Long id);
	//inquire all user
	List<SysUser> selectAll();
}
