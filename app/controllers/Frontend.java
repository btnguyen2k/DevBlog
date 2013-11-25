package controllers;

import play.api.templates.Html;
import play.data.Form;
import play.i18n.Messages;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;
import utils.AuthUtils;
import utils.IdUtils;
import bo.user.UserBo;
import bo.user.UserDao;
import devblog.Constants;
import forms.FormRegister;

public class Frontend extends BaseController {

    public static Promise<Result> index() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getFrontTemplateName();
                Html html = render(templateName, "front.index");
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles GET:/register
     */
    public static Promise<Result> register() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getFrontTemplateName();
                Html html = render(templateName, "front.register", (Form<?>) null);
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles POST:/register
     */
    public static Promise<Result> registerSubmit() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getFrontTemplateName();

                Form<FormRegister> form = Form.form(FormRegister.class).bindFromRequest();
                if (form.hasErrors()) {
                    Html html = render(templateName, "front.register", form);
                    return ok(html);
                }
                FormRegister formRegister = form.get();
                String id = IdUtils.nextId64Ascii();
                String email = formRegister.email.trim().toLowerCase();
                String password = formRegister.password.trim();
                String displayName = formRegister.displayName;
                UserBo user = new UserBo().setId(id).setEmail(email).setDisplayName(displayName)
                        .setGroupId(Constants.USER_GROUP_MEMBER);
                user = user.setPassword(AuthUtils.encryptPassword(user, password));
                user = UserDao.create(user);

                // TODO send welcome email

                flash("register_done", Messages.get("msg.signup.done"));
                return redirect(routes.Frontend.registerDone().url());
            }
        });
        return promise;
    }

    public static Promise<Result> registerDone() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getFrontTemplateName();
                String flashMsg = flash("register_done");
                Html html = render(templateName, "front.register_done", flashMsg);
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles GET:/fbregister
     */
    public static Promise<Result> fbregister() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getFrontTemplateName();
                Html html = render(templateName, "front.fbregister");
                return ok(html);
            }
        });
        return promise;
    }
}
