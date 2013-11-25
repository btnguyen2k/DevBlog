package forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.EmailUtils;
import bo.user.UserBo;
import bo.user.UserDao;

public class FormRegister {
    public String displayName, email, password, confirmedPassword;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        String displayName = !StringUtils.isBlank(this.displayName) ? this.displayName.trim()
                : null;
        if (StringUtils.isBlank(displayName)) {
            errors.add(new ValidationError("displayName", Messages.get("error.invalid.displayName")));
        }

        String email = !StringUtils.isBlank(this.email) ? this.email.trim().toLowerCase() : null;
        if (StringUtils.isBlank(email) || !EmailUtils.validateEmailAddr(email)) {
            errors.add(new ValidationError("email", Messages.get("error.invalid.email")));
        } else {
            UserBo user = UserDao.getUser(email);
            if (user != null) {
                errors.add(new ValidationError("email", Messages.get("error.exists.email", email)));
            }
        }

        String password = !StringUtils.isBlank(this.password) ? this.password.trim() : null;
        if (StringUtils.isBlank(password)) {
            errors.add(new ValidationError("password", Messages.get("error.invalid.password")));
        } else {
            String confirmedPassword = !StringUtils.isBlank(this.confirmedPassword) ? this.confirmedPassword
                    .trim() : null;
            if (!password.equals(confirmedPassword)) {
                errors.add(new ValidationError("confirmedPassword", Messages
                        .get("error.invalid.confirmedPassword")));
            }
        }

        return errors.isEmpty() ? null : errors;
    }
}
