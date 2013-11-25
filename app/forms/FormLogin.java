package forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.AuthUtils;
import bo.user.UserBo;
import bo.user.UserDao;

public class FormLogin {
    public String email, password, captcha;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        String email = !StringUtils.isBlank(this.email) ? this.email.trim().toLowerCase() : null;
        String password = !StringUtils.isBlank(this.password) ? this.password.trim() : null;
        UserBo user = UserDao.getUser(email);
        if (!AuthUtils.authenticate(user, password)) {
            errors.add(new ValidationError("email", Messages.get("error.signin.failed", email)));
        }

        return errors.isEmpty() ? null : errors;
    }
}
