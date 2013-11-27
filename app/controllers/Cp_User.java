package controllers;

import play.api.templates.Html;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;
import bo.user.UserDao;
import bo.user.UsergroupBo;

import compositions.AuthRequired;

import devblog.Constants;

@AuthRequired(userGroups = { Constants.USER_GROUP_ADMINISTRATOR })
public class Cp_User extends BaseController {

    private final static String TEMPLATE_NAME = getCpTemplateName();
    private final static String SECTION = "cp";
    private final static String VIEW_USERGROUPS = SECTION + ".user_groups";

    /*
     * Handles GET:/cp/userGroups
     */
    public static Promise<Result> userGroups() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                UsergroupBo[] userGroups = UserDao.getAllUsergroups();
                Html html = render(TEMPLATE_NAME, VIEW_USERGROUPS, (Object) userGroups);
                return ok(html);
            }
        });
        return promise;
    }

}
