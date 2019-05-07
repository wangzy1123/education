-- 每次启动执行

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `user_account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `pwd` varchar(40) DEFAULT NULL COMMENT '密码',
  `is_admin` int(2) DEFAULT NULL COMMENT '是否是管理员 0否1是',
  `state` int(2) DEFAULT NULL COMMENT '删除状态 0删除 1正常',
  `head_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;