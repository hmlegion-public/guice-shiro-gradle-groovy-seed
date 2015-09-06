

package com.ljstudio.tests

import com.google.inject.Guice
import com.google.inject.Injector
import com.ljstudio.dao.RoleDao
import com.ljstudio.dao.UserDao
import com.ljstudio.domain.Role
import com.ljstudio.domain.User
import com.ljstudio.guice.AppPersistenceModule
import com.ljstudio.utils.PasswordUtil
import org.apache.onami.persist.PersistenceService
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test

@Ignore
class DataSeed {

	private static Injector i
	private static PersistenceService p

	@BeforeClass
	public static void setupClass() {

		i = Guice.createInjector(new TestModule(), new AppPersistenceModule())
		p = i.getInstance(PersistenceService)
		p.start()
	}

	@Test
	public void seedData() {

		Role adminRole = new Role(code: 'ADMIN', description: 'User can administer other users.')
		Role userRole = new Role(code: 'USER', description: 'Default role.')
		RoleDao roleDao = i.getInstance(RoleDao)
		roleDao.save(userRole)
		roleDao.save(adminRole)

		UserDao userDao = i.getInstance(UserDao)

		String password = 'P@55w0rd'
		PasswordUtil passwordUtil = new PasswordUtil();
		Object salt = passwordUtil.generateSalt()
		String encodedEncryptedPassword = passwordUtil.generatePassword(password, salt)

		User joeSchmoeUser = new User(email: 'joeschmoe@gmail.com',
			firstName: 'joe',
			lastName: 'schmoe',
			password: encodedEncryptedPassword,
			salt: salt.toString(),
			username: 'joeschmoe',
			roles: [adminRole, userRole] as ArrayList)

		userDao.save(joeSchmoeUser)
		
		salt = passwordUtil.generateSalt()
		encodedEncryptedPassword = passwordUtil.generatePassword(password, salt)
		User janeSchmoeUser = new User(email: 'janeschmoe@gmail.com',
			firstName: 'jane',
			lastName: 'schmoe',
			password: encodedEncryptedPassword,
			salt: salt.toString(),
			username: 'janeschmoe',
			roles: [userRole] as ArrayList)

		userDao.save(janeSchmoeUser)

		User user= userDao.fetchUserByUsername("janeschmoe");
		println user

	}

	@AfterClass
	public static void cleanupClass() {
		p?.stop()
	}
}
