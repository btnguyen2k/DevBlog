package forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import play.data.validation.ValidationError;
import play.i18n.Messages;

public class FormCpCreateUsergroup {
    public String id, title;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        String title = !StringUtils.isBlank(this.title) ? this.title.trim() : null;
        if (StringUtils.isBlank(title)) {
            errors.add(new ValidationError("title", Messages.get("error.usergroup.title.empty")));
        }

        return errors.isEmpty() ? null : errors;
    }
}
