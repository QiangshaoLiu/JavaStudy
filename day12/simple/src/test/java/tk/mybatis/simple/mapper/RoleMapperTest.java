package tk.mybatis.simple.mapper;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import junit.framework.Assert;
import tk.mybatis.simple.model.SysRole;

@SuppressWarnings("deprecation")
public class RoleMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById() {
	//获取sqlSession
	SqlSession sqlSession = getSqlSession();
	try {
		//获取RoleMapper接口
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		//调用selectById方法，查询id=1的角色
		SysRole role = roleMapper.selectById(1L);
		//role不为空
		Assert.assertNotNull(role);
		//roleName=管理员
		Assert.assertEquals("管理员", role.getRoleName());
	} finally {
		//不要忘记关闭sqlSession
		sqlSession.close();
	}
	}
	@Test
	public void testSelectById2() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
			try {
				//获取RoleMapper接口
				RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
				//调用selectById2方法，查询id=1的角色
				SysRole role = roleMapper.selectById2(1L);
				//role不为空
				Assert.assertNotNull(role);
				//roleName=管理员
				Assert.assertEquals("管理员", role.getRoleName());
			} finally {
				//不要忘记关闭sqlSession
				sqlSession.close();
			}
	}
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//create a role object
			SysRole role = new SysRole();
			role.setRoleName("test1");
			role.setEnabled(1);
			role.setCreateBy(1L);

			role.setCreateTime(new Date());
			//将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
			int result = roleMapper.insert(role);
			//只插入1条数据
			Assert.assertEquals(1,  result);
			//roleName=test1
			Assert.assertEquals("test1", role.getRoleName());
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
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//create a role object
			SysRole role = new SysRole();
			role.setRoleName("test2");
			role.setEnabled(1);
			role.setCreateBy(1L);

			role.setCreateTime(new Date());
			//将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
			int result = roleMapper.insert2(role);
			//只插入1条数据
			Assert.assertEquals(1,  result);
			//roleName=test2
			Assert.assertEquals("test2", role.getRoleName());
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
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//create a role object
			SysRole role = new SysRole();
			role.setRoleName("test3");
			role.setEnabled(1);
			role.setCreateBy(1L);

			role.setCreateTime(new Date());
			//将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
			int result = roleMapper.insert3(role);
			//只插入1条数据
			Assert.assertEquals(1,  result);
			//roleName=test3
			Assert.assertEquals("test3", role.getRoleName());
		} finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	@Test
	public void testUpdateById() {
		Integer enable = 1;
		Integer disable = 0;
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//从数据库查询1个role对象
			SysRole role = roleMapper.selectById(2L);
			//当前roleName为管理员
			Assert.assertEquals(enable, role.getEnabled());
			role.setEnabled(disable);
			//更新数据，特别注意，这里返回值result是执行的SQL影响的行数
			roleMapper.updateById(role);
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
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//从数据库查询1个user对象，根据id=1查询
			SysRole role1 = roleMapper.selectById(1L);
			//现在还能查询出role1对象
			Assert.assertNotNull(role1);
			//调用方法删除
			Assert.assertEquals(1, roleMapper.deleteById(1L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(roleMapper.selectById(1L));
			
			//使用SysRole参数进行一次测试，根据id=1001查询
			SysRole role2 = roleMapper.selectById(2L);
			//现在还能查询出role2对象
			Assert.assertNotNull(role2);
			//调用方法删除，注意这里使用参数为role2
			Assert.assertEquals(1, roleMapper.deleteById(2L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(roleMapper.selectById(2L));
			//使用SysRole参数再进行一次测试
		} finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的，
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
}
