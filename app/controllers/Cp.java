package controllers;

import play.api.templates.Html;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Result;

import compositions.AuthRequired;

import devblog.Constants;

@AuthRequired(userGroups = { Constants.USER_GROUP_ADMINISTRATOR })
public class Cp extends BaseController {

    private final static String TEMPLATE_NAME = getCpTemplateName();
    private final static String SECTION = "cp";
    private final static String VIEW_INDEX = SECTION + ".index";

    /*
     * Handles GET:/profile
     */
    public static Promise<Result> index() {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                Html html = render(TEMPLATE_NAME, VIEW_INDEX);
                return ok(html);
            }
        });
        return promise;
    }

}
