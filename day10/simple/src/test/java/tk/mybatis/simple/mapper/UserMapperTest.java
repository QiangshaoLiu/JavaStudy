package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysUser;

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
			//Don't forget close sqlSession
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
			//Don't forget close sqlSession
			sqlSession.close();
		}
	}
}
