package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import junit.framework.Assert;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.plugin.PageRowBounds;
import tk.mybatis.simple.type.Enabled;

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
			role.setEnabled(Enabled.enabled);
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
			role.setEnabled(Enabled.enabled);
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
			role.setEnabled(Enabled.enabled);
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
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//从数据库查询1个role对象
			SysRole role = roleMapper.selectById(2L);
			//当前roleName为管理员
			Assert.assertEquals(Enabled.enabled, role.getEnabled());
			role.setEnabled(Enabled.disabled);
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
	
	@Test
	public void testSelectRoleByUserIdChoose(){
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取 RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//由于数据库数据enable都是1，所以给其中一个角色的enable赋值为0
			SysRole role = roleMapper.selectById(2L);
			role.setEnabled(Enabled.disabled);
			roleMapper.updateById(role);
			//获取用户1的角色
			List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
			for(SysRole r: roleList){
				System.out.println("角色名：" + r.getRoleName());
				if(r.getId().equals(1L)){
					//第一个角色存在权限信息
					Assert.assertNotNull(r.getPrivilegeList());
				} else if(r.getId().equals(2L)){
					//第二个角色的权限1位null
					Assert.assertNull(r.getPrivilegeList());
					continue;
				}
				for(SysPrivilege privilege : r.getPrivilegeList()){
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectAll方法查询所有角色
			List<SysRole> roleList = roleMapper.selectAll();
			//结果不为空
			Assert.assertNotNull(roleList);
			//角色数量大于0个
			Assert.assertTrue(roleList.size() > 0);
			//验证下画线字段是否映射成功
			Assert.assertNotNull(roleList.get(0).getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAllByRowBounds(){
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
	        //查询第一个，使用RowBounds类型时不会查询总数
			RowBounds rowBounds = new RowBounds(0, 1);
			List<SysRole> list = roleMapper.selectAll(rowBounds);
			for(SysRole role : list){
				System.out.println("角色名：" + role.getRoleName());
			}
	        //使用PageRowBounds时会查询总数
			PageRowBounds pageRowBounds = new PageRowBounds(0, 1);
			list = roleMapper.selectAll(pageRowBounds);
			//获取总数
			System.out.println("查询总数：" + pageRowBounds.getTotal());
			for(SysRole role : list){
				System.out.println("角色名：" + role.getRoleName());
			}
			//再次查询获取第二个角色
			pageRowBounds = new PageRowBounds(1, 1);
			list = roleMapper.selectAll(pageRowBounds);
			//获取总数
			System.out.println("查询总数：" + pageRowBounds.getTotal());
			for(SysRole role : list){
				System.out.println("角色名：" + role.getRoleName());
			}
		} finally {
			sqlSession.close();
		}
	}
}
