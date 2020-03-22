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
}
