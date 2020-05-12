package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.simple.model.PrivilegeProvider;
import tk.mybatis.simple.model.SysPrivilege;

public interface PrivilegeMapper {
	@SelectProvider(type = PrivilegeProvider.class, method = "selectById")
	SysPrivilege selectById(Long id);
	/*
	 * 通过Role Id选择权限
	 */
	List<SysPrivilege> selectPrivilegeByRoleId(Long id);
}
