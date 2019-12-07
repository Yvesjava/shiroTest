package com.imcode.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

	/**
	 * 初始化Shiro的运行环境
	 */
	static {
		// 1.初始化shiro的安全管理器
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		// 2.设置用户的权限信息到安全管理器
		Realm iniRealm = new IniRealm("classpath:shiro.ini");
		securityManager.setRealm(iniRealm);

		// 3.使用SecurityUtils将securityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
	}

	public static Subject login(String username, String password){

		// 创建一个Subject示例
		Subject subject = SecurityUtils.getSubject();

		// 5.创建用于认证的认证器,记录用户认证的身份和凭证即账号和密码
		AuthenticationToken token = new UsernamePasswordToken(username,password);

		// 6.主体要进行登录,登录的时候进行认证检查
		subject.login(token);
		return subject;
	}
}
