DROP TABLE IF EXISTS devglob_usergroup;
CREATE TABLE IF EXISTS devblog_usergroup (
    group_id                    VARCHAR(32),
        PRIMARY KEY (gid),
    group_title                 VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devglob_master_user;
CREATE TABLE IF EXISTS devglob_master_user (
    user_id                     VARCHAR(32),
    user_email                  VARCHAR(100),
        PRIMARY KEY (uemail),
    user_password               VARCHAR(64),
    group_id                    VARCHAR(32),
    timestamp_create            DATETIME,
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE TABLE devblog_user_0 LIKE devglob_master_user;
CREATE TABLE devblog_user_1 LIKE devglob_master_user;
CREATE TABLE devblog_user_2 LIKE devglob_master_user;
CREATE TABLE devblog_user_3 LIKE devglob_master_user;
CREATE TABLE devblog_user_4 LIKE devglob_master_user;
CREATE TABLE devblog_user_5 LIKE devglob_master_user;
CREATE TABLE devblog_user_6 LIKE devglob_master_user;
CREATE TABLE devblog_user_7 LIKE devglob_master_user;
CREATE TABLE devblog_user_8 LIKE devglob_master_user;
CREATE TABLE devblog_user_9 LIKE devglob_master_user;
CREATE TABLE devblog_user_a LIKE devglob_master_user;
CREATE TABLE devblog_user_b LIKE devglob_master_user;
CREATE TABLE devblog_user_c LIKE devglob_master_user;
CREATE TABLE devblog_user_d LIKE devglob_master_user;
CREATE TABLE devblog_user_e LIKE devglob_master_user;
CREATE TABLE devblog_user_f LIKE devglob_master_user;

DROP TABLE IF EXISTS devblog_master_userprofile;
CREATE TABLE devblog_master_userprofile (
    user_id                     VARCHAR(32),
    profile_key                 VARCHAR(32),
        PRIMARY KEY (user_id, profile_key),
    profile_type                INT             NOT NULL DEFAULT 0,
    profile_data_int            BIGINT,
    profile_data_real           DOUBLE,
    profile_data_money          DECIMAL(15,3),
    profile_data_datetime       DATETIME,
    profile_data_string         TEXT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE TABLE devblog_userprofile_0 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_1 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_2 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_3 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_4 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_5 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_6 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_7 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_8 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_9 LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_a LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_b LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_c LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_d LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_e LIKE devblog_master_userprofile;
CREATE TABLE devblog_userprofile_f LIKE devblog_master_userprofile;
