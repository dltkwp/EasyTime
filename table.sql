CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `realname` varchar(40) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `wechart` varchar(40) DEFAULT NULL COMMENT '微信',
  `alipay` varchar(40) DEFAULT NULL COMMENT '支付宝',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'1' COMMENT '1:删除 0:可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(40) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `t_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `categories_name` varchar(20) NOT NULL COMMENT '类型名称',
  `user_id` bigint(20) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
)

