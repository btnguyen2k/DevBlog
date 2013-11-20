package bo.userprofile;

import java.math.BigDecimal;
import java.sql.Date;

import com.github.ddth.plommon.bo.BaseBo;

public class UserprofileBo extends BaseBo {
    /* virtual db columns */
    public final static String COL_USER_ID = "user_id";
    public final static String COL_PROFILE_KEY = "profile_key";
    public final static String COL_PROFILE_TYPE = "profile_type";
    public final static String COL_PROFILE_DATA_INT = "profile_data_int";
    public final static String COL_PROFILE_DATA_REAL = "profile_data_real";
    public final static String COL_PROFILE_DATA_MONEY = "profile_data_money";
    public final static String COL_PROFILE_DATA_DATETIME = "profile_data_datetime";
    public final static String COL_PROFILE_DATA_STRING = "profile_data_string";

    public String getUserId() {
        return getAttribute(COL_USER_ID, String.class);
    }

    public UserprofileBo setUserId(String userId) {
        return (UserprofileBo) setAttribute(COL_USER_ID, userId);
    }

    public String getProfileKey() {
        return getAttribute(COL_PROFILE_KEY, String.class);
    }

    public UserprofileBo setProfileKey(String profileKey) {
        return (UserprofileBo) setAttribute(COL_PROFILE_KEY, profileKey);
    }

    public int getProfileType() {
        return getAttribute(COL_PROFILE_TYPE, Integer.class);
    }

    public UserprofileBo setProfileType(int profileType) {
        return (UserprofileBo) setAttribute(COL_PROFILE_TYPE, profileType);
    }

    public Long getProfileDataInt() {
        return getAttribute(COL_PROFILE_DATA_INT, Long.class);
    }

    public UserprofileBo setProfileDataInt(Long value) {
        return (UserprofileBo) setAttribute(COL_PROFILE_DATA_INT, value);
    }

    public Double getProfileDataReal() {
        return getAttribute(COL_PROFILE_DATA_REAL, Double.class);
    }

    public UserprofileBo setProfileDataReal(Double value) {
        return (UserprofileBo) setAttribute(COL_PROFILE_DATA_REAL, value);
    }

    public BigDecimal getProfileDataMoney() {
        return getAttribute(COL_PROFILE_DATA_MONEY, BigDecimal.class);
    }

    public UserprofileBo setProfileDataReal(BigDecimal value) {
        return (UserprofileBo) setAttribute(COL_PROFILE_DATA_MONEY, value);
    }

    public Date getProfileDataDatetime() {
        return getAttribute(COL_PROFILE_DATA_DATETIME, Date.class);
    }

    public UserprofileBo setProfileDataDatetime(Date value) {
        return (UserprofileBo) setAttribute(COL_PROFILE_DATA_DATETIME, value);
    }

    public String getProfileDataString() {
        return getAttribute(COL_PROFILE_DATA_STRING, String.class);
    }

    public UserprofileBo setProfileDataString(String value) {
        return (UserprofileBo) setAttribute(COL_PROFILE_DATA_STRING, value);
    }
}
