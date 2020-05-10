package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;  //@select注解的注解方式
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.simple.model.SysRole;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {
	//@Select({"select id,role_name roleName, enabled, create_by createBy, create_time createTime", "from sys_role", "where id=#{id}"})
	/*
	 * @Select注释掉则是使用mapUnderscoreToCamelCase配置注解方式
	 */
	SysRole selectById(Long id);
	/*
	 * 使用resultMap注解方式
	 * 新增selectById2方法
	 */
	@Results(value = {
			@Result(property = "id", column = "id", id = true),
			@Result(property = "roleName", column = "role_name"),
			@Result(property = "enabled", column = "enabled"),
			@Result(property = "createBy", column = "create_by"),
			@Result(property = "createTime", column = "create_time")
		})
	@Select("select id,role_name, enabled, create_by, create_time from sys_role where id = #{id}")
	SysRole selectById2(Long id);
	/*
	 * 不需要返回主键的insert方法
	 */
	@Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
			"values(#{id}, #{roleName}, #{enabled}, #{createBy},","#{createTime, jdbcType=TIMESTAMP})"})
	int insert(SysRole sysRole);
	/*
	 * 新增insert2方法
	 */
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
		"values(#{roleName}, #{enabled}, #{createBy},","#{createTime, jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert2(SysRole sysRole);
	/*
	 * 新增insert3方法
	 */
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
		"values(#{roleName}, #{enabled}, #{createBy},","#{createTime, jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()",
			   keyProperty = "id",
			   resultType = Long.class,
			   before = false)
	int insert3(SysRole sysRole);
	/*
	 * 新增updateById方法
	 */
	@Update({"update sys_role",
			"set role_name = #{roleName},",
			"enabled = #{enabled},",
			"create_by = #{createBy},",
			"create_time = #{createTime, jdbcType=TIMESTAMP}",
			"where id = #{id}"})
	int updateById(SysRole sysRole);
	/*
	 * 新增deleteById方法
	 */
	@Delete("delete from sys_role where id = #{id}")
	int deleteById(Long id);
	/*
	 * 获取所有的角色以及对应的权限
	 */
	List<SysRole> selectAllRoleAndPrivileges();
	/*
	 * 根据用户ID获取用户的角色信息
	 */
	List<SysRole> selectRoleByUserIdChoose(Long userId);
}
