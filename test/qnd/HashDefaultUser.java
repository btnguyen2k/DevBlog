package qnd;

import org.junit.Test;

import utils.AuthUtils;
import bo.DbUtils;
import bo.user.UserDao;

public class HashDefaultUser {
    @Test
    public void test1() {
        String id = "1";
        String password = AuthUtils.encryptPassword(id, "password");
        String email = "admin@local";
        // UserBo user = new UserBo().setId(id).setPassword(password)
        // .setGroupId(Constants.USER_GROUP_ADMINISTRATOR).setEmail(email);
        String tableUserAccount = DbUtils.calcTableNameLinearHex(email, UserDao.TABLE_USER,
                UserDao.NUM_TABLES_USER);
        String tableMapIdEmail = DbUtils.calcTableNameLinearHex(id,
                UserDao.TABLE_LOOKUP_USERID_EMAIL, UserDao.NUM_TABLES_USER);
        System.out.println("Table [user account]       : " + tableUserAccount);
        System.out.println("Table [lookup userid-email]: " + tableMapIdEmail);
        System.out.println("Encrypted password         : " + password);
    }
}
