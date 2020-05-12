package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest {
	@Test
	public void testL1Cache(){
		//get sqlSession
		SqlSession sqlSession = getSqlSession();
		SysUser user1 = null;
		try {
			//get UserMapper interface
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用 selectById方法，查询 id = 1 的用户
			user1 = userMapper.selectById(1l);
			//对当前获取的对象重新赋值
			user1.setUserName("New Name");
			//再次查询获取id相同的用户
			SysUser user2 = userMapper.selectById(1l);
			//虽然没有更新数据库，但是这个用户名和user1重新赋值的名字相同
			Assert.assertEquals("New Name", user2.getUserName());
			//无论如何，user2和user1完全就是同一个实例
			Assert.assertEquals(user1, user2);
		} finally {
			//关闭当前的sqlSession
			sqlSession.close();
		}
		System.out.println("开启新的 sqlSession");
		//开始另一个新的session
		sqlSession = getSqlSession();
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectById，查询id = 1的用户
			SysUser user2 = userMapper.selectById(1l);
			//第二个session获取的用户名依然是admin
			Assert.assertNotEquals("New Name", user2.getUserName());
			//这里的user2和前一个session查询的结果是两个不同的实例
			Assert.assertNotEquals(user1, user2);
			//执行删除操作
			userMapper.deleteById(2L);
			//获取user3
			SysUser user3 = userMapper.selectById(1l);
			//这里的user2 和 user3是两个不同的实例
			Assert.assertNotEquals(user2, user3);
		} finally {
			//关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testL2Cache(){
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		SysRole role1 = null;
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的用户
			role1 = roleMapper.selectById(1l);
			//对当前获取的对象重新赋值
			role1.setRoleName("New Name");
			//再次查询获取id相同的用户
			SysRole role2 = roleMapper.selectById(1l);
			//虽然没有更新数据库，但是这个用户名和role1重新赋值的名字相同
			Assert.assertEquals("New Name", role2.getRoleName());
			//无论如何，role2和role1完全就是同一个实例
			Assert.assertEquals(role1, role2);
		} finally {
			//关闭当前的sqlSession
			sqlSession.close();
		}
		System.out.println("开启新的sqlSession");
		//开始另一新的session
		sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的用户
			SysRole role2 = roleMapper.selectById(1l);
			//第二个session获取的用户名是New Name
			Assert.assertEquals("New Name", role2.getRoleName());
			//这里的role2和前一个session查询的结果是两个不同的实例
			Assert.assertNotEquals(role1, role2);
			//获取role3
			SysRole role3 = roleMapper.selectById(1l);
			//这里的role2和role3是两个不同的实例
			Assert.assertNotEquals(role2, role3);
		} finally {
			//关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testDirtyData(){
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			Assert.assertEquals("普通用户", user.getRole().getRoleName());
			System.out.println("角色名：" + user.getRole().getRoleName());
		} finally {
			sqlSession.close();
		}
		//开始另一个新的session
		sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			role.setRoleName("脏数据");
			roleMapper.updateById(role);
			//提交修改
			sqlSession.commit();
		} finally {
			//关闭当前的sqlSession
			sqlSession.close();
		}
		System.out.println("开启新的sqlSession");
		//开始另一个新的session
		sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			SysRole role = roleMapper.selectById(2L);
			Assert.assertEquals("脏数据", user.getRole().getRoleName());
			Assert.assertEquals("脏数据", role.getRoleName());
			System.out.println("角色名：" + user.getRole().getRoleName());
			//还原数据
			role.setRoleName("普通用户");
			roleMapper.updateById(role);
			//提交修改
			sqlSession.commit();
		} finally {
			//关闭sqlSession
			sqlSession.close();
		}
	}
}
