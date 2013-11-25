package utils;

import bo.user.UserBo;

import com.github.ddth.commons.utils.HashUtils;

public class AuthUtils {
    public static String encryptPassword(UserBo user, String rawPassword) {
        return encryptPassword(user.getId(), rawPassword);
    }

    public static String encryptPassword(String salt, String rawPassword) {
        String combinedSaltAndPassword = salt + "." + rawPassword;
        return HashUtils.sha1(combinedSaltAndPassword);
    }
}
