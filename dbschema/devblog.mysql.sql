DROP TABLE IF EXISTS devblog_mailtemplate;
CREATE TABLE devblog_mailtemplate(
    template_id                     VARCHAR(32),
    template_lang                   VARCHAR(16),
        PRIMARY KEY (template_id, template_lang),
    mail_subject                    VARCHAR(255),
    mail_body                       TEXT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devblog_appconfig;
CREATE TABLE devblog_appconfig(
    conf_key                        VARCHAR(32),
        PRIMARY KEY (conf_key)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devblog_usergroup;
CREATE TABLE devblog_usergroup (
    group_id                        VARCHAR(32),
        PRIMARY KEY (group_id),
    group_title                     VARCHAR(50)
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
    user_id                         VARCHAR(32),
    user_email                      VARCHAR(100),
        PRIMARY KEY (user_email),
    user_password                   VARCHAR(64),
    group_id                        VARCHAR(32),
    timestamp_create                DATETIME,
    display_name                    VARCHAR(40),
    user_gender                     TINYINT             NOT NULL DEFAULT 0,
    dob_month                       INT                 NOT NULL DEFAULT 0,
    dob_day                         INT                 NOT NULL DEFAULT 0,
        INDEX (dob_month, dob_day),
    dob_year                        INT                 NOT NULL DEFAULT 0,
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

DROP TABLE IF EXISTS devblog_master_lookup_userid_email;
CREATE TABLE devblog_master_lookup_userid_email (
    user_id                         VARCHAR(32),
        PRIMARY KEY (user_id),
    user_email                      VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
DROP TABLE IF EXISTS
    devblog_lookup_userid_email_0, devblog_lookup_userid_email_1, devblog_lookup_userid_email_2, devblog_lookup_userid_email_3,
    devblog_lookup_userid_email_4, devblog_lookup_userid_email_5, devblog_lookup_userid_email_6, devblog_lookup_userid_email_7,
    devblog_lookup_userid_email_8, devblog_lookup_userid_email_9, devblog_lookup_userid_email_a, devblog_lookup_userid_email_b,
    devblog_lookup_userid_email_c, devblog_lookup_userid_email_d, devblog_lookup_userid_email_e, devblog_lookup_userid_email_f;
CREATE TABLE devblog_lookup_userid_email_0 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_1 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_2 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_3 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_4 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_5 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_6 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_7 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_8 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_9 LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_a LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_b LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_c LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_d LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_e LIKE devblog_master_lookup_userid_email;
CREATE TABLE devblog_lookup_userid_email_f LIKE devblog_master_lookup_userid_email;

INSERT INTO devblog_user_2 (user_id, user_email, user_password, group_id, timestamp_create, display_name)
VALUES ("1", "admin@local", "a153ba7319d815ada7f473a54c209b4e7c9e3836", "1", UTC_TIMESTAMP(), "Administrator");

INSERT INTO devblog_lookup_userid_email_1 (user_id, user_email) VALUES ("1", "admin@local");

DROP TABLE IF EXISTS devblog_master_userprofile;
CREATE TABLE devblog_master_userprofile (
    user_id                         VARCHAR(32),
    profile_key                     VARCHAR(32),
        PRIMARY KEY (user_id, profile_key),
    profile_type                    INT             NOT NULL DEFAULT 0,
    profile_data_int                BIGINT,
    profile_data_real               DOUBLE,
    profile_data_money              DECIMAL(15,3),
    profile_data_datetime           DATETIME,
    profile_data_string             TEXT
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

DROP TABLE IF EXISTS devblog_blogcategory;
CREATE TABLE devblog_blogcategory(
    cat_id                          VARCHAR(32),
        PRIMARY KEY (cat_id),
    id_enabled                      TINYINT         NOT NULL DEFAULT 1,
    cat_title                       VARCHAR(150),
    cat_summary                     TEXT
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP TABLE IF EXISTS devblog_master_blog;
CREATE TABLE devblog_master_blog(
    blog_id                         VARCHAR(32),
        PRIMARY KEY (blog_id),
    blog_type                       INT             NOT NULL DEFAULT 0,
        INDEX (blog_type),
    timestamp_create                DATETIME,
        INDEX (timestamp_create),
    user_id                         VARCHAR(32),
        INDEX (user_id),
    cat_id                          VARCHAR(32),
        INDEX (cat_id),
    blog_title                      VARCHAR(100),
    blog_content                    TEXT,
    blog_tags                       VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
