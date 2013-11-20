package bo.user;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bo.DbUtils;

import com.github.ddth.plommon.bo.BaseMysqlDao;
import com.github.ddth.plommon.utils.DPathUtils;

import devblog.Constants;

public class UserDao extends BaseMysqlDao {

    public final static String TABLE_USERGROUP = "devblog_usergroup";
    public final static String TABLE_USER = "devblog_user_{0}";

    // private final static UserBo[] EMPTY_ARR_USER_BO = new UserBo[0];
    private final static UsergroupBo[] EMPTY_ARR_USERGROUP_BO = new UsergroupBo[0];

    /*----------------------------------------------------------------------*/
    private static String cacheKeyUsergroup(String id) {
        return Constants.APP_ID + "_USERGROUP_" + id;
    }

    private static String cacheKey(UsergroupBo usergroup) {
        return cacheKeyUsergroup(usergroup.getId());
    }

    private static String cacheKeyAllUsergroups() {
        return Constants.APP_ID + "_ALL_USERGROUPS";
    }

    private static void invalidate(UsergroupBo usergroup) {
        removeFromCache(cacheKey(usergroup));
        removeFromCache(cacheKeyAllUsergroups());
    }

    private static String cacheKeyUser(String email) {
        return Constants.APP_ID + "_USER_" + email;
    }

    private static String cacheKey(UserBo user) {
        return cacheKeyUser(user.getEmail());
    }

    private static String cacheKeyAllUsers() {
        return Constants.APP_ID + "_ALL_USERS";
    }

    private static void invalidate(UserBo user) {
        removeFromCache(cacheKey(user));
        removeFromCache(cacheKeyAllUsers());
    }

    /*----------------------------------------------------------------------*/
    /**
     * Creates a new user group.
     * 
     * @param usergroup
     * @return
     */
    public static UsergroupBo create(UsergroupBo usergroup) {
        final String[] COLUMNS = new String[] { "group_id", "group_title" };
        final Object[] VALUES = new Object[] { usergroup.getId(), usergroup.getTitle() };
        insertIgnore(TABLE_USERGROUP, COLUMNS, VALUES);
        invalidate(usergroup);
        return (UsergroupBo) usergroup.markClean();
    }

    /**
     * Deletes an existing usergroup.
     * 
     * @param usergroup
     */
    public static void delete(UsergroupBo usergroup) {
        final String[] COLUMNS = new String[] { "group_id" };
        final Object[] VALUES = new Object[] { usergroup.getId() };
        delete(TABLE_USERGROUP, COLUMNS, VALUES);
        invalidate(usergroup);
    }

    /**
     * Gets a usergroup by id.
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UsergroupBo getUsergroup(String id) {
        final String CACHE_KEY = cacheKeyUsergroup(id);
        Map<String, Object> dbRow = getFromCache(CACHE_KEY, Map.class);
        if (dbRow == null) {
            final String SQL_TEMPLATE = "SELECT group_id AS {1}, group_title AS {2} FROM {0} WHERE group_id=?";
            final String SQL = MessageFormat.format(SQL_TEMPLATE, TABLE_USERGROUP,
                    UsergroupBo.COL_ID, UsergroupBo.COL_TITLE);
            final Object[] WHERE_VALUES = new Object[] { id };
            List<Map<String, Object>> dbResult = select(SQL, WHERE_VALUES);
            dbRow = dbResult != null && dbResult.size() > 0 ? dbResult.get(0) : null;
            putToCache(CACHE_KEY, dbRow);
        }
        return dbRow != null ? (UsergroupBo) new UsergroupBo().fromMap(dbRow) : null;
    }

    /**
     * Gets all usergroups as a list.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UsergroupBo[] getAllUsergroups() {
        final String CACHE_KEY = cacheKeyAllUsergroups();
        List<Map<String, Object>> dbRows = getFromCache(CACHE_KEY, List.class);
        if (dbRows == null) {
            final String SQL_TEMPLATE = "SELECT group_id AS {1} FROM {0} ORDER BY group_title";
            final String SQL = MessageFormat.format(SQL_TEMPLATE, TABLE_USERGROUP,
                    UsergroupBo.COL_ID);
            final Object[] PARAM_VALUES = new Object[] {};
            dbRows = select(SQL, PARAM_VALUES);
            putToCache(CACHE_KEY, dbRows);
        }
        List<UsergroupBo> result = new ArrayList<UsergroupBo>();
        if (dbRows != null) {
            for (Map<String, Object> dbRow : dbRows) {
                String id = DPathUtils.getValue(dbRow, UsergroupBo.COL_ID, String.class);
                UsergroupBo usergroup = getUsergroup(id);
                if (usergroup != null) {
                    result.add(usergroup);
                }
            }
        }
        return result.toArray(EMPTY_ARR_USERGROUP_BO);
    }

    /**
     * Updates an existing usergroup.
     * 
     * @param usergroup
     * @return
     */
    public static UsergroupBo update(UsergroupBo usergroup) {
        if (usergroup.isDirty()) {
            final String CACHE_KEY = cacheKey(usergroup);
            final String[] COLUMNS = new String[] { "group_title" };
            final Object[] VALUES = new Object[] { usergroup.getTitle() };
            final String[] WHERE_COLUMNS = new String[] { "group_id" };
            final Object[] WHERE_VALUES = new Object[] { usergroup.getId() };
            update(TABLE_USERGROUP, COLUMNS, VALUES, WHERE_COLUMNS, WHERE_VALUES);
            Map<String, Object> dbRow = usergroup.toMap();
            putToCache(CACHE_KEY, dbRow);
        }
        return (UsergroupBo) usergroup.markClean();
    }

    /*----------------------------------------------------------------------*/
    public final static int NUM_TABLES_USER = 16;

    /**
     * Creates a new user account.
     * 
     * @param user
     * @return
     */
    public static UserBo create(UserBo user) {
        final String table = DbUtils.calcTableNameLinear(user.getEmail(), TABLE_USER,
                NUM_TABLES_USER);
        final String[] COLUMNS = new String[] { "user_id", "user_email", "user_password",
                "group_id", "timestamp_create" };
        final Object[] VALUES = new Object[] { user.getId(), user.getEmail(), user.getPassword(),
                user.getGroupId(), user.getTimestampCreate() };
        insertIgnore(table, COLUMNS, VALUES);
        invalidate(user);
        return (UserBo) user.markClean();
    }

    /**
     * Deletes an existing user account.
     * 
     * @param user
     */
    public static void delete(UserBo user) {
        final String table = DbUtils.calcTableNameLinear(user.getEmail(), TABLE_USER,
                NUM_TABLES_USER);
        final String[] COLUMNS = new String[] { "user_email" };
        final Object[] VALUES = new Object[] { user.getEmail() };
        delete(table, COLUMNS, VALUES);
        invalidate(user);
    }

    /**
     * Gets a user account by .
     * 
     * @param email
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserBo getUser(String email) {
        final String CACHE_KEY = cacheKeyUser(email);
        Map<String, Object> dbRow = getFromCache(CACHE_KEY, Map.class);
        if (dbRow == null) {
            final String table = DbUtils.calcTableNameLinear(email, TABLE_USER, NUM_TABLES_USER);
            final String SQL_TEMPLATE = "SELECT user_id AS {1}, user_email AS {2}, user_password AS {3}, group_id AS {4}, timestamp_create AS {5} FROM {0} WHERE user_email=?";
            final String SQL = MessageFormat.format(SQL_TEMPLATE, table, UserBo.COL_ID,
                    UserBo.COL_EMAIL, UserBo.COL_PASSWORD, UserBo.COL_GROUP_ID,
                    UserBo.COL_TIMESTAMP_CREATE);
            final Object[] WHERE_VALUES = new Object[] { email };
            List<Map<String, Object>> dbResult = select(SQL, WHERE_VALUES);
            dbRow = dbResult != null && dbResult.size() > 0 ? dbResult.get(0) : null;
            putToCache(CACHE_KEY, dbRow);
        }
        return dbRow != null ? (UserBo) new UserBo().fromMap(dbRow) : null;
    }

    /**
     * Updates an existing user account.
     * 
     * @param user
     * @return
     */
    public static UserBo update(UserBo user) {
        if (user.isDirty()) {
            final String table = DbUtils.calcTableNameLinear(user.getEmail(), TABLE_USER,
                    NUM_TABLES_USER);
            final String CACHE_KEY = cacheKey(user);
            final String[] COLUMNS = new String[] { "user_password", "group_id" };
            final Object[] VALUES = new Object[] { user.getPassword(), user.getGroupId() };
            final String[] WHERE_COLUMNS = new String[] { "user_email" };
            final Object[] WHERE_VALUES = new Object[] { user.getEmail() };
            update(table, COLUMNS, VALUES, WHERE_COLUMNS, WHERE_VALUES);
            Map<String, Object> dbRow = user.toMap();
            putToCache(CACHE_KEY, dbRow);
        }
        return (UserBo) user.markClean();
    }

    /*----------------------------------------------------------------------*/
}