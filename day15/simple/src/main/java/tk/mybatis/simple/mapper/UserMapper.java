package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.model.SysRole;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	//inquire user by ID
	SysUser selectById(Long id);
	//inquire all user
	List<SysUser> selectAll();
	//get role information by user id
	List<SysRole> selectRolesByUserId(Long userId);
	
	int insert (SysUser sysUser);
	/*
	 * 新增用户-使用useGeneratedKeys方式
	 * 
	 */
	int insert2(SysUser sysUser);
	/*
	 * 新增用户-使用selectKey方式
	 */
	int insert3(SysUser sysUser);
	/*
	 * 根据主键更新
	 */
	int updateById(SysUser sysUser);
	/*
	 * 通过主键删除
	 */
	int deleteById(Long id);
	/*
	 * 根据用户id和角色的enabled状态获取用户的角色
	 */
	List<SysRole> selectRolesByUserIdAndRoleEnabled(
			@Param("userId")Long userId,
			@Param("enabled")Integer enabled);
	/*
	 * 根据动态条件查询用户信息
	 */
	List<SysUser> selectByUser(SysUser sysUser);
	/*
	 * 根据主键更新
	 */
	int updateByIdSelective(SysUser sysUser);
	/*
	 * 在insert动态插入列中使用if
	 */
	void insert2Selective();
	/*
	 * 根据用户id或用户名查询
	 */
	SysUser selectByIdOrUserName(SysUser sysUser);
	/*
	 * 根据用户id集合查询
	 */
	List<SysUser> selectByIdList(List<Long> idList);
	/*
	 * 批量插入用户信息
	 */
	int insertList(List<SysUser> userList);
	/*
	 * 通过Map更新列
	 */
	int updateByMap(Map<String, Object> Map);
	/*
	 * 根据用户id获取用户信息和用户的角色信息
	 */
	SysUser selectUserAndRoleById(Long id);
	/*
	 *  根据用户id获取用户信息和用户的角色信息
	 */
	SysUser selectUserAndRoleById2(Long id);
	/*
	 * 根据用户id获取用户信息和用户的角色信息，嵌套查询方式
	 */
	SysUser selectUserAndRoleByIdSelect(Long id);
	/*
	 * 获取所有的用户以及对应的所有角色
	 */
	List<SysUser> selectAllUserAndRoles();
	/*
	 * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
	 */
	SysUser selectAllUserAndRolesSelect(Long id);
	/*
	 * 使用存储过程查询用户信息
	 */
	void selectUserById(SysUser user);
	/*
	 * 使用存储过程分页查询
	 */
	List<SysUser> selectUserPage(Map<String, Object> params);
	/*
	 * 保存用户信息和角色关联信息
	 */
	int insertUserAndRoles(@Param("user")SysUser user, @Param("roleIds")String roleIds);
	/*
	 * 根据用户id删除用户和用户的角色信息
	 */
	int deleteUserById(Long id);
}
