package compositions;

import org.apache.commons.lang3.ArrayUtils;

import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;
import bo.user.UserBo;
import controllers.routes;
import devblog.Application;

public class AuthRequiredAction extends Action<AuthRequired> {

    private static Promise<SimpleResult> login(final Http.Context ctx) {
        return Promise.promise(new Function0<SimpleResult>() {
            public SimpleResult apply() {
                return redirect(routes.Frontend.login(ctx.request().uri()));
            }
        });
    }

    public Promise<SimpleResult> call(final Http.Context ctx) throws Throwable {
        UserBo user = Application.currentUser();
        if (user == null) {
            return login(ctx);
        }
        String[] userGroups = configuration.userGroups();
        if (userGroups != null && userGroups.length > 0) {
            if (ArrayUtils.indexOf(userGroups, user.getGroupId()) < 0) {
                return login(ctx);
            }
        }
        return delegate.call(ctx);
    }
}
