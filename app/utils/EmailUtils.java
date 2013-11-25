package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import play.Play;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;

public class EmailUtils {

    private final static ObjectPool<MailerAPI> poolMailer = new GenericObjectPool<MailerAPI>(
            new BasePoolableObjectFactory<MailerAPI>() {
                @Override
                public MailerAPI makeObject() throws Exception {
                    return Play.application().plugin(MailerPlugin.class).email();
                }
            });

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validateEmailAddr(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void sendEmail(String from, String to, String subject, String body) {
        try {
            MailerAPI mailer = poolMailer.borrowObject();
            try {
                mailer.setFrom(from);
                mailer.setRecipient(to);
                mailer.setSubject(subject);
                mailer.sendHtml(body);
            } finally {
                poolMailer.returnObject(mailer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
