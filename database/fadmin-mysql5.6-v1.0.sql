/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.1.31-community : Database - fadmin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fadmin` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `fadmin`;

/*Table structure for table `dc_sql` */

DROP TABLE IF EXISTS `dc_sql`;

CREATE TABLE `dc_sql` (
  `dc_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `dc_sql` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `dc_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `dc_sql` */

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(200) COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values ('quartzJobFactory','定时JOB任务1_triggerName','DEFAULT','*/15 * * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values ('quartzJobFactory','定时JOB任务1','DEFAULT',NULL,'com.dtf.admin.manage.job.JobDemo','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('RenrenScheduler','TRIGGER_ACCESS'),('quartzJobFactory','STATE_ACCESS'),('quartzJobFactory','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('quartzJobFactory','fei-PC1504539743789',1504572238617,20000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values ('quartzJobFactory','定时JOB任务1_triggerName','DEFAULT','定时JOB任务1','DEFAULT',NULL,1504405380000,1504405365000,5,'PAUSED','CRON',1504106412000,0,NULL,0,'');

/*Table structure for table `sys_attr` */

DROP TABLE IF EXISTS `sys_attr`;

CREATE TABLE `sys_attr` (
  `attr_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `attr_code` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `struts` varchar(2) COLLATE utf8_bin DEFAULT '0' COMMENT '0：有效；1：失效',
  `attr_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_attr` */

insert  into `sys_attr`(`attr_id`,`attr_code`,`struts`,`attr_name`,`create_date`) values ('1','COMMON_STRUTS','0','公共状态',NULL),('331396c57bc444a785232464d9856f39','JOB_STATE','0','定时任务状态',NULL),('ef8b36dbde8e400bbda621e9b4610353','SEX','0','性别',NULL);

/*Table structure for table `sys_attr_value` */

DROP TABLE IF EXISTS `sys_attr_value`;

CREATE TABLE `sys_attr_value` (
  `attr_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `attr_value` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `attr_value_name` varchar(200) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_attr_value` */

insert  into `sys_attr_value`(`attr_id`,`attr_value`,`attr_value_name`) values ('1','0','有效'),('1','1','无效'),('ef8b36dbde8e400bbda621e9b4610353','0','女'),('ef8b36dbde8e400bbda621e9b4610353','1','男'),('331396c57bc444a785232464d9856f39','PAUSED','暂停'),('331396c57bc444a785232464d9856f39','ACQUIRED','正常'),('331396c57bc444a785232464d9856f39','WAITING','等待');

/*Table structure for table `sys_button` */

DROP TABLE IF EXISTS `sys_button`;

CREATE TABLE `sys_button` (
  `btn_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '按钮ID',
  `btn_html` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '按钮HTML代码，可以写onclick事件等',
  `status` int(11) DEFAULT NULL COMMENT '0:有效；1：无效',
  `btn_code` varchar(100) COLLATE utf8_bin NOT NULL,
  `menu_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `btn_name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`btn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_button` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单显示名称',
  `menu_url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单链接',
  `menu_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单路径：父菜单ID.自己ID',
  `parent_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '父节点ID',
  `status` int(11) DEFAULT NULL COMMENT '0有效；1无效',
  `sort_order` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `class_style` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单样式，可以是icon图标',
  `menu_level` int(2) DEFAULT NULL COMMENT '菜单级别',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`menu_url`,`menu_path`,`parent_id`,`status`,`sort_order`,`class_style`,`menu_level`) values ('-1','系统菜单根目录','无，可添加manage的公共可访问页面','-1','',0,'','',0),('0f0ffa7ed9194235baf489ff5ed91657','个人信息','/manage/staff_user_info.jsp','-1.8ea1f7ba71b04e3e966ec366730eaf9c.0f0ffa7ed9194235baf489ff5ed91657','8ea1f7ba71b04e3e966ec366730eaf9c',0,'1','',2),('13bce71907804305b45cc505a2b5de7b','管理员管理','无','-1.13bce71907804305b45cc505a2b5de7b','-1',0,'100','&#xe62d;',1),('2c9ba381519b5fa901519ba8b9ed0011','用户管理','/manage/sysStaff_list.jsp','-1.13bce71907804305b45cc505a2b5de7b.2c9ba381519b5fa901519ba8b9ed0011','13bce71907804305b45cc505a2b5de7b',0,'0','',2),('2c9ba381519be34601519bea88310000','系统设置','/manage/system_set.jsp','-1.402880e54e59cb8f014e5d78706c0011.2c9ba381519be34601519bea88310000','402880e54e59cb8f014e5d78706c0011',0,'2','',2),('3fe83550861a47018ae9b72859c8eb98','修改密码','/manage/changePassword.jsp','-1.8ea1f7ba71b04e3e966ec366730eaf9c.3fe83550861a47018ae9b72859c8eb98','8ea1f7ba71b04e3e966ec366730eaf9c',0,'2','',2),('402880e54e59cb8f014e5d78706c0011','系统管理','无','-1.402880e54e59cb8f014e5d78706c0011','-1',0,'100','&#xe62e;',1),('402880e54e59cb8f014e5d79c5150012','菜单按钮管理','/manage/sysMenu_list.jsp','-1.402880e54e59cb8f014e5d78706c0011.402880e54e59cb8f014e5d79c5150012','402880e54e59cb8f014e5d78706c0011',0,'0','',2),('402880e54e7baf69014e7bb1d9700000','组织管理','/manage/sysOrg_list.jsp','-1.13bce71907804305b45cc505a2b5de7b.402880e54e7baf69014e7bb1d9700000','13bce71907804305b45cc505a2b5de7b',0,'3','',2),('402882834ea4d19a014ea4e0b4fb0000','角色管理','/manage/sysRole_list.jsp','-1.13bce71907804305b45cc505a2b5de7b.402882834ea4d19a014ea4e0b4fb0000','13bce71907804305b45cc505a2b5de7b',0,'-1','',2),('482065e88183455b856734968a476fda','数据字典','/manage/sysAttr_list.jsp','-1.402880e54e59cb8f014e5d78706c0011.482065e88183455b856734968a476fda','402880e54e59cb8f014e5d78706c0011',0,'99','',2),('8ea1f7ba71b04e3e966ec366730eaf9c','个人中心','无','-1.8ea1f7ba71b04e3e966ec366730eaf9c','-1',0,'99','&#xe705;',1),('d1820343c8294dd79cb159ade151a619','定时任务管理','/manage/quartzJob_list.jsp','-1.402880e54e59cb8f014e5d78706c0011.d1820343c8294dd79cb159ade151a619','402880e54e59cb8f014e5d78706c0011',0,'3','',2),('dfee3fec38044de8bb0e28b50afc5fa2','开发工具','无','-1.dfee3fec38044de8bb0e28b50afc5fa2','-1',0,'100','&#xe662;',1),('fc033fe40d17414f9658abdbd70b5820','代码生成工具','/manage/codeGeneration_list.jsp','-1.dfee3fec38044de8bb0e28b50afc5fa2.fc033fe40d17414f9658abdbd70b5820','dfee3fec38044de8bb0e28b50afc5fa2',0,'1','',2);

/*Table structure for table `sys_menu_children` */

DROP TABLE IF EXISTS `sys_menu_children`;

CREATE TABLE `sys_menu_children` (
  `menu_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '菜单ID',
  `menu_children_url` varchar(300) COLLATE utf8_bin NOT NULL COMMENT '子菜单URL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_menu_children` */

insert  into `sys_menu_children`(`menu_id`,`menu_children_url`) values ('2c9ba381519b5fa901519ba8b9ed0011','/manage/sysStaff_edit.jsp'),('2c9ba381519b5fa901519ba8b9ed0011','/manage/sysRole_select.jsp'),('2c9ba381519b5fa901519ba8b9ed0011','/manage/sysOrg_tree.jsp'),('402882834ea4d19a014ea4e0b4fb0000','/manage/sysRole_edit.jsp'),('402882834ea4d19a014ea4e0b4fb0000','/manage/sysRolePrivilege_edit.jsp'),('402880e54e7baf69014e7bb1d9700000','/manage/sysOrg_edit.jsp'),('fc033fe40d17414f9658abdbd70b5820','/manage/codeGeneration_add.jsp'),('d1820343c8294dd79cb159ade151a619','/manage/quartzJob_edit.jsp'),('d1820343c8294dd79cb159ade151a619','/manage/quartzJob_cron.jsp'),('2c9ba381519be34601519bea88310000',''),('13bce71907804305b45cc505a2b5de7b',''),('402880e54e59cb8f014e5d79c5150012','/manage/sysMenu_edit.jsp'),('402880e54e59cb8f014e5d79c5150012','/manage/sysBtn_edit.jsp'),('-1','/manage/welcome.jsp'),('-1','/manage/index.jsp'),('482065e88183455b856734968a476fda','/manage/sysAttr_edit.jsp');

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `org_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '组织ID',
  `org_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '组织名称',
  `parent_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '上级组织ID',
  `org_level` int(11) DEFAULT NULL COMMENT '组织级别',
  `status` int(11) DEFAULT NULL COMMENT '状态：0有效；1无效',
  `org_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '组织路径',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_org` */

insert  into `sys_org`(`org_id`,`org_name`,`parent_id`,`org_level`,`status`,`org_path`) values ('-1','系统根路径','',-1,0,'-1'),('1','人力资源部','-1',0,0,'-1.1'),('402881e54fe5f607014fe6092bbe0009','综合一部','1',1,0,'-1.1.402881e54fe5f607014fe6092bbe0009'),('402881e54fe5f607014fe6094f67000a','综合二部','1',1,0,'-1.1.402881e54fe5f607014fe6094f67000a'),('402882864e920871014e9227ca860000','销售部','-1',0,0,'-1.402882864e920871014e9227ca860000'),('705290062347454da1f644fe9e9a4b9a','销售三部','402882864e920871014e9227ca860000',1,0,'-1.402882864e920871014e9227ca860000.705290062347454da1f644fe9e9a4b9a'),('8e7303ff7d2144d7b9eb5941f964540e','销售一部','402882864e920871014e9227ca860000',1,0,'-1.402882864e920871014e9227ca860000.8e7303ff7d2144d7b9eb5941f964540e'),('a45221cb4db643359cbdee213b31002e','销售三部','402882864e920871014e9227ca860000',1,0,'-1.402882864e920871014e9227ca860000.a45221cb4db643359cbdee213b31002e');

/*Table structure for table `sys_params` */

DROP TABLE IF EXISTS `sys_params`;

CREATE TABLE `sys_params` (
  `param_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '系统参数名',
  `param_val` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '系统参数值',
  `param_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '系统参数说明',
  PRIMARY KEY (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_params` */

insert  into `sys_params`(`param_code`,`param_val`,`param_name`) values ('SYSTEM_EMAIL_SEND_FROM','中兴软创','系统发送邮件发送人名称'),('SYSTEM_EMAIL_USER_NAME','280789377@qq.com','用户名'),('SYSTEM_EMAIL_USER_PWD','ioauymrjedapcbee','密码'),('SYSTEM_NAME','卫浴管理系统','系统名称');

/*Table structure for table `sys_privilege_rel` */

DROP TABLE IF EXISTS `sys_privilege_rel`;

CREATE TABLE `sys_privilege_rel` (
  `privilege_rel_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限对应ID',
  `role_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色ID',
  `privilege_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '权限类型：1菜单权限；2按钮权限',
  `privilege_obj_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单ID、按钮ID',
  PRIMARY KEY (`privilege_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_privilege_rel` */

insert  into `sys_privilege_rel`(`privilege_rel_id`,`role_id`,`privilege_type`,`privilege_obj_id`) values ('1b9691882b1347c1ba63b7fea9363666','814e35076e3e4077aeed5857ea0260d1','menu','2c9ba381519b5fa901519ba8fe3a0012'),('20d9cea7f848457587f032f62d5c8759','1','menu','13bce71907804305b45cc505a2b5de7b'),('29917f6a9f0e4327aee27d5357a14e31','814e35076e3e4077aeed5857ea0260d1','menu','402880e54e59cb8f014e5d78706c0011'),('3ae132bc07f742f9b80e6cde261f3718','2c9ba381519149ca01519157eca00001','menu','3fe83550861a47018ae9b72859c8eb98'),('3c6f1f8179794793817de17243423d10','1','menu','fc033fe40d17414f9658abdbd70b5820'),('3f1265ce02aa4d23b39b376f89b2c955','1','menu','402880e54e59cb8f014e5d79c5150012'),('3f5dc8ba13d64de08018aee81ae0e7a0','814e35076e3e4077aeed5857ea0260d1','menu','402882834ea4d19a014ea4e0b4fb0000'),('43490fdc1a79404b8e28352d805c62d4','1','menu','dfee3fec38044de8bb0e28b50afc5fa2'),('4d10a429401d419992fcd9c0acac8c4d','1','menu','402880e54e7baf69014e7bb1d9700000'),('4fd28cb67cd4417e88e306f2afdd1595','814e35076e3e4077aeed5857ea0260d1','menu','2c9ba381519b5fa901519ba8b9ed0011'),('5263265b5ff74b719e7212216b8ef07a','2c9ba381519149ca01519157eca00001','menu','402880e54e59cb8f014e5d78706c0011'),('53a15c761d8a4abf840d81535acf67e7','2c9ba381519149ca01519157eca00001','menu','-1'),('6a79bb8521074fa092644705f58211c5','1','menu','3fe83550861a47018ae9b72859c8eb98'),('6c55f60304a04a6ca64a0f18f9d07c35','1','menu','402882834ea4d19a014ea4e0b4fb0000'),('7118e174685b495e96e6c4de453dd492','1','menu','2c9ba381519b5fa901519ba8b9ed0011'),('73d7b407d2fb482d872bf423edef9ded','1','menu','-1'),('7b84c63328314fcfb0bba6d4f155b1b3','2c9ba381519149ca01519157eca00001','menu','402882834ea4d19a014ea4e0b4fb0000'),('8f9e40b7d5a54227b4bea97c0fef1b8f','1','menu','482065e88183455b856734968a476fda'),('a444f89876ad489d8b7c75540942539e','2c9ba381519149ca01519157eca00001','menu','0f0ffa7ed9194235baf489ff5ed91657'),('a4687fd184fc4769b46e298fcc69662e','2c9ba381519149ca01519157eca00001','menu','402880e54e7baf69014e7bb1d9700000'),('aaabe075322442619e19453ebe87307a','2c9ba381519149ca01519157eca00001','menu','402880e54e59cb8f014e5d79c5150012'),('c05d28bb210548e3982db5eaf3b0f008','814e35076e3e4077aeed5857ea0260d1','menu','2c9ba381519be34601519bea88310000'),('c57a44a5869240ed95980d3129b6e983','2c9ba381519149ca01519157eca00001','menu','2c9ba381519b5fa901519ba8b9ed0011'),('d8a63bc24b6e4a51b1e2511eee83edaa','814e35076e3e4077aeed5857ea0260d1','menu','402880e54e59cb8f014e5d79c5150012'),('ddbaee7a67b244d69fd250f549108d69','1','menu','2c9ba381519be34601519bea88310000'),('e90b15ece10340d6a72466461f1bdddf','1','menu','0f0ffa7ed9194235baf489ff5ed91657'),('ebe53b575bcf4afbadef20796e555ea6','1','menu','8ea1f7ba71b04e3e966ec366730eaf9c'),('f1312050a19147b18b37818da69a8b34','1','menu','402880e54e59cb8f014e5d78706c0011'),('f200829d7172408684eb20a55699d522','2c9ba381519149ca01519157eca00001','menu','8ea1f7ba71b04e3e966ec366730eaf9c'),('f392011dfdcf4363a9ab4d14e33d1279','814e35076e3e4077aeed5857ea0260d1','menu','402880e54e7baf69014e7bb1d9700000'),('f396e1ad2bc04500850fbd21679d7e4c','1','menu','d1820343c8294dd79cb159ade151a619'),('f83f846671614353b21e2ee2e841acd4','2c9ba381519149ca01519157eca00001','menu','2c9ba381519be34601519bea88310000');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色ID',
  `role_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '角色说明',
  `status_staff_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '变更人员',
  `status_date` datetime DEFAULT NULL COMMENT '变更时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_desc`,`status_staff_id`,`status_date`) values ('1','超级管理员','超级管理员，最大权限','1','2017-08-23 00:01:28'),('2c9ba381519149ca01519157eca00001','普通员工','普通员工',NULL,'2015-12-13 21:58:56');

/*Table structure for table `sys_staff` */

DROP TABLE IF EXISTS `sys_staff`;

CREATE TABLE `sys_staff` (
  `staff_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户ID',
  `staff_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录账号',
  `staff_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别：0：女；1：男',
  `phone` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `family_phone` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '紧急联络人电话',
  `card` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `age` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '年龄',
  `education` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '学历',
  `school_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '毕业学校',
  `major` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '所学专业',
  `org_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '组织ID',
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '状态：0正常；1停用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_staff` */

insert  into `sys_staff`(`staff_id`,`staff_code`,`staff_name`,`password`,`sex`,`phone`,`family_phone`,`card`,`age`,`education`,`school_name`,`major`,`org_id`,`status`,`create_date`) values ('2c9ba381518191770151819d10740000','admin','系统超级管理员','E10ADC3949BA59ABBE56E057F20F883E',NULL,'135','111','5138221','11','1','1','1','402881e54fe5f607014fe6094f67000a','0',NULL),('2c9ba381518191770151819f31c80001','www','ww','E10ADC3949BA59ABBE56E057F20F883E',1,'ww','1','1','','','','','402881e54fe5f607014fe6094f67000a','1',NULL),('acd3c73b81824b20b4e55b1dd8674025','dtf','测试用户1','E10ADC3949BA59ABBE56E057F20F883E',0,'135','1','1','','','','','','0','2017-08-24 23:02:42');

/*Table structure for table `sys_staff_role_rel` */

DROP TABLE IF EXISTS `sys_staff_role_rel`;

CREATE TABLE `sys_staff_role_rel` (
  `rel_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '关系ID',
  `staff_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_staff_role_rel` */

insert  into `sys_staff_role_rel`(`rel_id`,`staff_id`,`role_id`) values ('2c9ba381519b5fa901519b9ef0db000b','1','1'),('2c9ba381519b5fa901519ba35b530010','2c9ba381519b5fa901519b8a4c400004','2c9ba381519149ca01519157eca00001'),('402881e551cee2780151cef91f2e0000','402881e45176e33f0151771b326a0002','2c9ba381519149ca01519157eca00001'),('56ac96cfd012471b93c9f6247f47d365','','2c9ba381519149ca01519157eca00001'),('57fbd30cad11460da3dbb483ef41ebae','acd3c73b81824b20b4e55b1dd8674025','2c9ba381519149ca01519157eca00001'),('d414cc5425bd4b9a8438e2cc206b45af','2c9ba381518191770151819d10740000','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
