package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import junit.framework.Assert;
import tk.mybatis.simple.model.SysPrivilege;

@SuppressWarnings("deprecation")
public class PrivilegeMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取PrivilegeMapper接口
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			//调用selectById方法，查询id=1的权限
			SysPrivilege privilege = privilegeMapper.selectById(1L);
			//privilege不为空
			Assert.assertNotNull(privilege);
			//privilegeName=用户管理
			Assert.assertEquals("用户管理", privilege.getPrivilegeName());
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectPrivilegeByRoleId() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取PrivilegeMapper接口
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			//调用selectById方法，查询id=1的权限
			List<SysPrivilege> privilegeList = privilegeMapper.selectPrivilegeByRoleId(1L);
			//privilegeList不为空
			Assert.assertNotNull(privilegeList);
			//privilegeName个数大于0
			Assert.assertTrue(privilegeList.size() > 0);
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
}
