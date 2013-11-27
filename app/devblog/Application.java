package devblog;

import play.Play;
import play.mvc.Controller;
import bo.user.UserBo;
import bo.user.UserDao;

import com.github.ddth.plommon.utils.SessionUtils;

public class Application {
    public static UserBo currentUser() {
        Object userEmail = SessionUtils.getSession(Constants.SESSION_USER_EMAIL, true);
        UserBo user = userEmail != null ? UserDao.getUser(userEmail.toString()) : null;
        return user;
    }

    public static void logout() {
        SessionUtils.removeSession(Constants.SESSION_USER_EMAIL);
    }

    public static void login(String email) {
        SessionUtils.setSession(Constants.SESSION_USER_EMAIL, email, 7 * 24 * 3600);
    }

    public static String fbAppId() {
        return Play.application().configuration().getString("fb.app_id");
    }

    public static String fbAppScope() {
        return Play.application().configuration().getString("fb.app_scope");
    }

    public static String queryString(String name) {
        return Controller.request().getQueryString(name);
    }
}
