package tk.mybatis.simple.mapper;

import java.util.List;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.model.SysRole;

public class UserMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById() {
		//get sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//get UserMapper interface
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//use selectById methods,inquire id = 1 user
			SysUser user = userMapper.selectById(1l);
			//user is not NULL
			Assert.assertNotNull(user);
			//userName = admin
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			//Don't forget to close sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//use selectAll method to inquire all users
			List<SysUser> userList = userMapper.selectAll();
			//results is not NULL
			Assert.assertNotNull(userList);
			//number of users more than 0
			Assert.assertTrue(userList.size() > 0);
		} finally {
			//Don't forget to close sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserId() {
		//get sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//get UserMapper interface
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//use selectRolesByUserId methods,inquire roleId = id & userId = id
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			//role is not NULL
			Assert.assertNotNull(roleList);
			//Number of RoleName is more than 0
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			//Don't forget to close sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//create a user object
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//in normal condition, a figure should be writing to byte array
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			//将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
			int result = userMapper.insert(user);
			//只插入1条数据
			Assert.assertEquals(1,  result);
			//id is NULL, 没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()不是自动提交的
			//由于不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//Don't forget to close sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test2");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			//只插入1条数据
			Assert.assertEquals(1,result);
			//因为id回写，所以id不为null
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test3");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert3(user);
			//只插入1条数据
			Assert.assertEquals(1,result);
			//因为id回写，所以id不为null
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询1个user对象
			SysUser user = userMapper.selectById(1L);
			//当前userName为admin
			Assert.assertEquals("admin",user.getUserName());
			//修改用户名
			user.setUserName("admin_test");
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新数据，特别注意，这里返回值result是执行的SQL影响的行数
			int result = userMapper.updateById(user);
			//只更新1条数据
			Assert.assertEquals(1, result);
			//根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			//修改后的名字是admin_test
			Assert.assertEquals("admin_test",user.getUserName());
		} finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()不是自动提交的，
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询1个user对象，根据id=1查询
			SysUser user1 = userMapper.selectById(1L);
			//现在还能查询出user对象
			Assert.assertNotNull(user1);
			//调用方法删除
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1L));
			
			//使用SysUser参数进行一次测试，根据id=1001查询
			SysUser user2 = userMapper.selectById(1001L);
			//现在还能查询出user对象
			Assert.assertNotNull(user2);
			//调用方法删除，注意这里使用参数为user2
			Assert.assertEquals(1, userMapper.deleteById(1001L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1001L));
			//使用SysUser参数再进行一次测试
		} finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的，
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectRolesByUserIdAndRoleEnabled方法查询用户的角色
			List<SysRole> userList = 
					userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			//结果不为空
			Assert.assertNotNull(userList);
			//角色数量大于0个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//只查询用户名时
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//只查询用户邮箱时
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//当同时查询用户名和邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			//由于没有同时符合这两个条件的用户，因此查询结果数为0
			Assert.assertTrue(userList.size() == 0);
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个新的user对象
			SysUser user = new SysUser();
			//更新id=1的用户
			user.setId(1L);
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新邮箱，特别注意，这里的返回值result执行的是SQL影响的行数
			int result = userMapper.updateByIdSelective(user);
			//只更新1条数据
			Assert.assertEquals(1,result);
			//根据当前id查询修改后的数据
			user = userMapper.selectById(1l);
			//修改后的名字保持不变，但是邮箱变成了新的
			Assert.assertEquals("admin",user.getUserName());
			Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
		} finally {
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2Selective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个新的user对象
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			//插入数据库
			userMapper.insert2(user);
			//获取插入的这条数据
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
		} finally {
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
}
