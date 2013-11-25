DROP TABLE IF EXISTS devblog_mailtemplate;
CREATE TABLE devblog_emailtemplate(
    template_id                 VARHCHAR(32),
    template_lang               VARHCHAR(16),
        PRIMARY KEY (template_id, template_lang)
    mail_subject                VARCHAR(255),
    mail_body                   TEXT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devblog_appconfig;
CREATE TABLE devblog_appconfig(
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devblog_usergroup;
CREATE TABLE devblog_usergroup (
    group_id                    VARCHAR(32),
        PRIMARY KEY (group_id),
    group_title                 VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO devblog_usergroup (group_id, group_title)
VALUES
    ("1", "Administrator")
   ,("2", "Moderator")
   ,("3", "Member")
   ,("4", "Guest")
   ;

DROP TABLE IF EXISTS devblog_master_user;
CREATE TABLE devblog_master_user (
    user_id                     VARCHAR(32),
    user_email                  VARCHAR(100),
        PRIMARY KEY (user_email),
    user_password               VARCHAR(64),
    group_id                    VARCHAR(32),
    timestamp_create            DATETIME,
    display_name                VARCHAR(40),
    user_gender                 TINYINT             NOT NULL DEFAULT 0,
    dob_month                   INT                 NOT NULL DEFAULT 0,
    dob_day                     INT                 NOT NULL DEFAULT 0,
        INDEX (dob_month, dob_day),
    dob_year                    INT                 NOT NULL DEFAULT 0,
        INDEX (dob_year)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
DROP TABLE IF EXISTS
    devblog_user_0, devblog_user_1, devblog_user_2, devblog_user_3,
    devblog_user_4, devblog_user_5, devblog_user_6, devblog_user_7,
    devblog_user_8, devblog_user_9, devblog_user_a, devblog_user_b,
    devblog_user_c, devblog_user_d, devblog_user_e, devblog_user_f;
CREATE TABLE devblog_user_0 LIKE devblog_master_user;
CREATE TABLE devblog_user_1 LIKE devblog_master_user;
CREATE TABLE devblog_user_2 LIKE devblog_master_user;
CREATE TABLE devblog_user_3 LIKE devblog_master_user;
CREATE TABLE devblog_user_4 LIKE devblog_master_user;
CREATE TABLE devblog_user_5 LIKE devblog_master_user;
CREATE TABLE devblog_user_6 LIKE devblog_master_user;
CREATE TABLE devblog_user_7 LIKE devblog_master_user;
CREATE TABLE devblog_user_8 LIKE devblog_master_user;
CREATE TABLE devblog_user_9 LIKE devblog_master_user;
CREATE TABLE devblog_user_a LIKE devblog_master_user;
CREATE TABLE devblog_user_b LIKE devblog_master_user;
CREATE TABLE devblog_user_c LIKE devblog_master_user;
CREATE TABLE devblog_user_d LIKE devblog_master_user;
CREATE TABLE devblog_user_e LIKE devblog_master_user;
CREATE TABLE devblog_user_f LIKE devblog_master_user;

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
DROP TABLE IF EXISTS
    devblog_userprofile_0, devblog_userprofile_1, devblog_userprofile_2, devblog_userprofile_3,
    devblog_userprofile_4, devblog_userprofile_5, devblog_userprofile_6, devblog_userprofile_7,
    devblog_userprofile_8, devblog_userprofile_9, devblog_userprofile_a, devblog_userprofile_b,
    devblog_userprofile_c, devblog_userprofile_d, devblog_userprofile_e, devblog_userprofile_f;
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
