package controllers;

import play.api.templates.Html;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;

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
                Html html = render(templateName, "front.register");
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
