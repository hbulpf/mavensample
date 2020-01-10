/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm-sky-demo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`user_name`,`password`,`age`,`sex`,`email`,`account`,`memo`,`createTime`,`updateTime`) values (1,'披荆斩棘','123456',0,0,'yujunhao_8831@yahoo.com',75.3,'无 ','2020-01-10 10:57:54','2019-09-18 23:31:14');
insert  into `t_user`(`user_id`,`user_name`,`password`,`age`,`sex`,`email`,`account`,`memo`,`createTime`,`updateTime`) values (2,'adin2','`swin`',12,1,'sn',59.2,'isdnl','2020-01-10 10:56:12',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
