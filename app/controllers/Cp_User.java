package controllers;

import play.api.templates.Html;
import play.data.Form;
import play.i18n.Messages;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;
import utils.IdUtils;
import bo.user.UserDao;
import bo.user.UsergroupBo;

import compositions.AuthRequired;

import devblog.Constants;
import forms.FormCpCreateUsergroup;

@AuthRequired(userGroups = { Constants.USER_GROUP_ADMINISTRATOR })
public class Cp_User extends BaseController {

    private final static String TEMPLATE_NAME = getCpTemplateName();
    private final static String SECTION = "cp";
    private final static String VIEW_USERGROUPS = SECTION + ".usergroups";
    public final static String VIEW_CREATE_USERGROUP = SECTION + ".usergroup_create";

    /*
     * Handles GET:/cp/usergroups
     */
    public static Promise<Result> usergroups() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                UsergroupBo[] userGroups = UserDao.getAllUsergroups();
                Html html = render(TEMPLATE_NAME, VIEW_USERGROUPS, (Object) userGroups);
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles GET:/cp/createUsergroup
     */
    public static Promise<Result> createUsergroup() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                Html html = render(TEMPLATE_NAME, VIEW_CREATE_USERGROUP, (Form<?>) null);
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles POST:/cp/createUsergroup
     */
    public static Promise<Result> createUsergroupSubmit() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                Form<FormCpCreateUsergroup> form = Form.form(FormCpCreateUsergroup.class)
                        .bindFromRequest();
                if (form.hasErrors()) {
                    Html html = render(TEMPLATE_NAME, VIEW_CREATE_USERGROUP, (Form<?>) null);
                    return ok(html);
                }
                FormCpCreateUsergroup formCreateUsergroup = form.get();
                String id = IdUtils.nextIdTinyAscii();
                String title = formCreateUsergroup.title.trim();
                UsergroupBo usergroup = new UsergroupBo().setId(id).setTitle(title);
                usergroup = UserDao.create(usergroup);

                flash(VIEW_CREATE_USERGROUP, Messages.get("msg.cp.usergroup.create.done", title));
                return redirect(routes.Cp_User.usergroups());
            }
        });
        return promise;
    }
}
