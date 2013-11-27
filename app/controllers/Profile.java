package controllers;

import play.api.templates.Html;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;

import compositions.AuthRequired;

public class Profile extends BaseController {

    /*
     * Handles GET:/profile
     */
    @AuthRequired
    public static Promise<Result> index() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getProfileTemplateName();
                Html html = render(templateName, "profile.index");
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles GET:/profile/writeBlog
     */
    @AuthRequired
    public static Promise<Result> writeBlog() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getProfileTemplateName();
                Html html = render(templateName, "profile.write_blog");
                return ok(html);
            }
        });
        return promise;
    }

    /*
     * Handles GET:/profile/writeBlog
     */
    @AuthRequired
    public static Promise<Result> writeBlogSubmit() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                String templateName = getProfileTemplateName();
                Html html = render(templateName, "profile.write_blog");
                return ok(html);
            }
        });
        return promise;
    }
}
