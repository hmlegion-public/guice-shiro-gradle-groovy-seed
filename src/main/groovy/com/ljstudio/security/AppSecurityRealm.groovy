package com.ljstudio.security

import com.ljstudio.dao.UserDao
import com.ljstudio.domain.User
import org.apache.shiro.authc.*
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

import javax.inject.Inject
import javax.inject.Provider

class AppSecurityRealm extends AuthorizingRealm {
	private Provider<UserDao> userDao

	@Inject
	public AppSecurityRealm(Provider<UserDao> userDao) {
		this.userDao = userDao
		setName("dbRealm")

		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME)
		matcher.setStoredCredentialsHexEncoded(false)
		matcher.setHashIterations(1024)
		
		setCredentialsMatcher(matcher)
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username == null) {
			System.out.println("Username is null.");
			return null;
		}

		// read password hash and salt from db
		final User user = userDao.get().fetchUserByUsername(username)

		if (user == null) {
			System.out.println("No account found for user [" + username + "]");
			return null;
		}

		// return salted credentials
		SaltedAuthenticationInfo info = new AppSaltedAuthenticationInfo(username, user.password, user.salt);

		return info;

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		final String username = (String) principals.getPrimaryPrincipal();
		final List<String> roles = userDao.get().fetchUserByUsername(username).getUserRoles();
		return new SimpleAuthorizationInfo(new HashSet<String>(roles));
	}
}
