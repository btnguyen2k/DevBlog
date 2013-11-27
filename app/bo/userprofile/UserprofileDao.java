package bo.userprofile;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import bo.DbUtils;
import bo.user.UsergroupBo;

import com.github.ddth.plommon.bo.BaseMysqlDao;

import devblog.Constants;

public class UserprofileDao extends BaseMysqlDao {

    public final static String TABLE_USERPROFILE = "devblog_userprofile_{0}";

    // private final static UserprofileBo[] EMPTY_ARR_USERPROFILE_BO = new
    // UserprofileBo[0];

    /*----------------------------------------------------------------------*/
    private static String cacheKeyUserprofile(String userId) {
        return Constants.APP_ID + "_USERPROFILE_" + userId;
    }

    private static String cacheKeyUserprofile(String userId, String profileKey) {
        return Constants.APP_ID + "_USERPROFILE_" + userId + "_" + profileKey;
    }

    private static String cacheKey(UserprofileBo userprofile) {
        return cacheKeyUserprofile(userprofile.getUserId(), userprofile.getProfileKey());
    }

    private static void invalidate(UserprofileBo userprofileBo) {
        removeFromCache(cacheKey(userprofileBo));
        removeFromCache(cacheKeyUserprofile(userprofileBo.getUserId()));
    }

    /*----------------------------------------------------------------------*/
    public final static int NUM_TABLES_USERPROFILE = 16;

    /**
     * Creates a new user profile.
     * 
     * @param userprofile
     * @return
     */
    public static UserprofileBo create(UserprofileBo userprofile) {
        final String table = DbUtils.calcTableNameLinearHex(userprofile.getUserId(),
                TABLE_USERPROFILE, NUM_TABLES_USERPROFILE);
        final String[] COLUMNS = new String[] { "user_id", "profile_key", "profile_type",
                "profile_data_int", "profile_data_real", "profile_data_money",
                "profile_data_datetime", "profile_data_string" };
        final Object[] VALUES = new Object[] { userprofile.getUserId(),
                userprofile.getProfileKey(), userprofile.getProfileType(),
                userprofile.getProfileDataInt(), userprofile.getProfileDataReal(),
                userprofile.getProfileDataMoney(), userprofile.getProfileDataDatetime(),
                userprofile.getProfileDataString() };
        insertIgnore(table, COLUMNS, VALUES);
        invalidate(userprofile);
        return (UserprofileBo) userprofile.markClean();
    }

    /**
     * Deletes an existing user profile.
     * 
     * @param userprofile
     */
    public static void delete(UserprofileBo userprofile) {
        final String table = DbUtils.calcTableNameLinearHex(userprofile.getUserId(),
                TABLE_USERPROFILE, NUM_TABLES_USERPROFILE);
        final String[] COLUMNS = new String[] { "user_id", "profile_key" };
        final Object[] VALUES = new Object[] { userprofile.getUserId(), userprofile.getProfileKey() };
        delete(table, COLUMNS, VALUES);
        invalidate(userprofile);
    }

    /**
     * Gets a user profile.
     * 
     * @param userId
     * @param profileKey
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserprofileBo getUserprofile(String userId, String profileKey) {
        final String CACHE_KEY = cacheKeyUserprofile(userId, profileKey);
        Map<String, Object> dbRow = getFromCache(CACHE_KEY, Map.class);
        if (dbRow == null) {
            final String table = DbUtils.calcTableNameLinearHex(userId, TABLE_USERPROFILE,
                    NUM_TABLES_USERPROFILE);
            final String SQL_TEMPLATE = "SELECT user_id AS {1}, profile_key AS {2}, profile_type AS {3}, "
                    + "profile_data_int AS {4}, profile_data_real AS {5}, profile_data_money AS {6}, profile_data_datetime AS {7}, profile_data_string AS {8} "
                    + "FROM {0} WHERE user_id=? AND profile_key=?";
            final String SQL = MessageFormat.format(SQL_TEMPLATE, table, UserprofileBo.COL_USER_ID,
                    UserprofileBo.COL_PROFILE_KEY, UserprofileBo.COL_PROFILE_TYPE,
                    UserprofileBo.COL_PROFILE_DATA_INT, UserprofileBo.COL_PROFILE_DATA_REAL,
                    UserprofileBo.COL_PROFILE_DATA_MONEY, UserprofileBo.COL_PROFILE_DATA_DATETIME,
                    UserprofileBo.COL_PROFILE_DATA_STRING);
            final Object[] WHERE_VALUES = new Object[] { userId, profileKey };
            List<Map<String, Object>> dbResult = select(SQL, WHERE_VALUES);
            dbRow = dbResult != null && dbResult.size() > 0 ? dbResult.get(0) : null;
            putToCache(CACHE_KEY, dbRow);
        }
        return dbRow != null ? (UserprofileBo) new UsergroupBo().fromMap(dbRow) : null;
    }

    /**
     * Updates an existing user profile.
     * 
     * @param user
     *            profile
     * @return
     */
    public static UserprofileBo update(UserprofileBo userprofile) {
        if (userprofile.isDirty()) {
            final String table = DbUtils.calcTableNameLinearHex(userprofile.getUserId(),
                    TABLE_USERPROFILE, NUM_TABLES_USERPROFILE);
            final String CACHE_KEY = cacheKey(userprofile);
            final String[] COLUMNS = new String[] { "profile_type", "profile_data_int",
                    "profile_data_real", "profile_data_money", "profile_data_datetime",
                    "profile_data_string" };
            final Object[] VALUES = new Object[] { userprofile.getProfileType(),
                    userprofile.getProfileDataInt(), userprofile.getProfileDataReal(),
                    userprofile.getProfileDataMoney(), userprofile.getProfileDataDatetime(),
                    userprofile.getProfileDataString() };
            final String[] WHERE_COLUMNS = new String[] { "user_id", "profile_key" };
            final Object[] WHERE_VALUES = new Object[] { userprofile.getUserId(),
                    userprofile.getProfileKey() };
            update(table, COLUMNS, VALUES, WHERE_COLUMNS, WHERE_VALUES);
            Map<String, Object> dbRow = userprofile.toMap();
            putToCache(CACHE_KEY, dbRow);
        }
        return (UserprofileBo) userprofile.markClean();
    }
}
