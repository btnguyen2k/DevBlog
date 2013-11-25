package bo.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.ddth.plommon.bo.BaseBo;

public class UserBo extends BaseBo {
    /* virtual db columns */
    public final static String COL_ID = "user_id";
    public final static String COL_EMAIL = "user_email";
    public final static String COL_PASSWORD = "user_password";
    public final static String COL_GROUP_ID = "group_id";
    public final static String COL_TIMESTAMP_CREATE = "timestamp_create";
    public final static String COL_DISPLAY_NAME = "display_name";
    public final static String COL_GENDER = "user_gender";
    public final static String COL_DOB_DAY = "dob_day";
    public final static String COL_DOB_MONTH = "dob_month";
    public final static String COL_DOB_YEAR = "dob_year";

    public final static int GENDER_NONE = 0;
    public final static int GENDER_FEMALE = 1;
    public final static int GENDER_MALE = 2;

    public static int parseGender(String gender) {
        if ("female".equalsIgnoreCase(gender)) {
            return GENDER_FEMALE;
        } else if ("male".equalsIgnoreCase(gender)) {
            return GENDER_MALE;
        } else {
            return GENDER_NONE;
        }
    }

    public static Date parseDob(String dob, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dob);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Hashes a password using MD5.
     * 
     * @param rawPassword
     * @return
     */
    public static String hashPassword(String rawPassword) {
        return DigestUtils.md5Hex(rawPassword).toLowerCase();
    }

    public UsergroupBo getGroup() {
        return UserDao.getUsergroup(getGroupId());
    }

    /**
     * Authenticates a user's password.
     * 
     * @param rawPassword
     * @return
     */
    public boolean authenticate(String rawPassword) {
        String hashedPwd = hashPassword(rawPassword);
        return StringUtils.equalsIgnoreCase(hashedPwd, getPassword());
    }

    public String getId() {
        return getAttribute(COL_ID, String.class);
    }

    public UserBo setId(String id) {
        return (UserBo) setAttribute(COL_ID, id);
    }

    public String getPassword() {
        return getAttribute(COL_PASSWORD, String.class);
    }

    public UserBo setPassword(String password) {
        return (UserBo) setAttribute(COL_PASSWORD, password);
    }

    public String getEmail() {
        return getAttribute(COL_EMAIL, String.class);
    }

    public UserBo setEmail(String email) {
        return (UserBo) setAttribute(COL_EMAIL, email);
    }

    public String getGroupId() {
        return getAttribute(COL_GROUP_ID, String.class);
    }

    public UserBo setGroupId(String groupId) {
        return (UserBo) setAttribute(COL_GROUP_ID, groupId);
    }

    public Date getTimestampCreate() {
        return getAttribute(COL_TIMESTAMP_CREATE, Date.class);
    }

    public UserBo setTimestampCreate(Date timestamp) {
        return (UserBo) setAttribute(COL_TIMESTAMP_CREATE, timestamp);
    }

    public String getDisplayName() {
        return getAttribute(COL_DISPLAY_NAME, String.class);
    }

    public UserBo setDisplayName(String displayName) {
        return (UserBo) setAttribute(COL_DISPLAY_NAME, displayName);
    }

    public int getGender() {
        Integer gender = getAttribute(COL_GENDER, Integer.class);
        return gender != null ? gender.intValue() : GENDER_NONE;
    }

    public UserBo setGender(int gender) {
        if (gender != GENDER_FEMALE && gender != GENDER_MALE) {
            gender = GENDER_NONE;
        }
        return (UserBo) setAttribute(COL_GENDER, gender);
    }

    public int getDobDay() {
        Integer day = getAttribute(COL_DOB_DAY, Integer.class);
        return day != null ? day.intValue() : 0;
    }

    public UserBo setDob(Date dob) {
        Calendar c = Calendar.getInstance();
        c.setTime(dob);
        setDobDay(c.get(Calendar.DAY_OF_MONTH));
        setDobMonth(c.get(Calendar.MONTH) + 1); // month starts from 0
        setDobYear(c.get(Calendar.YEAR));
        return this;
    }

    public UserBo setDobDay(int value) {
        return (UserBo) setAttribute(COL_DOB_DAY, value);
    }

    public int getDobMonth() {
        Integer month = getAttribute(COL_DOB_MONTH, Integer.class);
        return month != null ? month.intValue() : 0;
    }

    public UserBo setDobMonth(int value) {
        return (UserBo) setAttribute(COL_DOB_MONTH, value);
    }

    public int getDobYear() {
        Integer year = getAttribute(COL_DOB_YEAR, Integer.class);
        return year != null ? year.intValue() : 0;
    }

    public UserBo setDobYear(int value) {
        return (UserBo) setAttribute(COL_DOB_YEAR, value);
    }
}
