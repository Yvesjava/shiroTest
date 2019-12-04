package com.imcode.shiro.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {

	@Test
	public void test1(){
		// 1.初始化shiro的安全管理器
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		// 2.设置用户的权限信息到安全管理器
		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
		securityManager.setRealm(iniRealm);

		// 3.使用SecurityUtils将securityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 创建一个Subject示例
		Subject subject = SecurityUtils.getSubject();

		// 5.创建用于认证的认证器,记录用户认证的身份和凭证即账号和密码
		AuthenticationToken token = new UsernamePasswordToken("lisi","123456");

		// 6.主体要进行登录,登录的时候进行认证检查
		subject.login(token);
		// IncorrectCredentialsException 密码认证失败
		// UnknownAccountException  未知的用户

		// 用户的认证状态
		System.out.println("用户的认证状态:"+subject.isAuthenticated());

		// 7.检查授权资源
		System.out.println("是否拥有admin角色:"+subject.hasRole("admin"));
		System.out.println("是否拥有public角色:"+subject.hasRole("public"));
		System.out.println("是否拥有创建产品的权限:"+subject.isPermitted("product:view"));

		System.out.println(subject.getPrincipal());
		System.out.println(((UsernamePasswordToken) token).getPassword());

		subject.logout();

		System.out.println(subject.isAuthenticated());

	}
}
