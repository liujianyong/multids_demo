create database orders;
DROP TABLE IF EXISTS `order_exp`;
CREATE TABLE `order_exp` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '订单的主键',
  `order_no` varchar(50) NOT NULL COMMENT '订单的编号',
  `order_note` varchar(100) NOT NULL COMMENT '订单的说明',
  `insert_time` datetime NOT NULL COMMENT '插入订单的时间',
  `expire_duration` bigint(22) NOT NULL COMMENT '订单的过期时长，单位秒',
  `expire_time` datetime NOT NULL DEFAULT '1979-12-31 00:00:00' COMMENT '订单的过期时间',
  `order_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '订单的状态，0：未支付；1：已支付；-1：已过期，关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
insert into order_exp (order_no, order_note,insert_time, expire_duration,expire_time, order_status) values ('order_no_1','order_no_1',now(),25,DATE_ADD(now(),INTERVAL 25 SECOND),0);
