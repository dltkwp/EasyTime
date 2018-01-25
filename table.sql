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

CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  foreign key(`uid`) references t_user(`id`),
  foreign key(`rid`) references t_role(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';


CREATE TABLE `t_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `categories_name` varchar(20) NOT NULL COMMENT '类型名称',
  `user_id` bigint(20) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

insert into t_role(role_name) values('dealer');
insert into t_role(role_name) values('agent');

insert into t_user_role(uid, rid) values(1, 1);
insert into t_user_role(uid, rid) values(1, 2);
insert into t_user_role(uid, rid) values(2, 1);
insert into t_user_role(uid, rid) values(2, 2);
insert into t_user_role(uid, rid) values(3, 1);
insert into t_user_role(uid, rid) values(3, 2);

CREATE TABLE `t_distributor_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level_name` varchar(20) NOT NULL COMMENT '分销等级',
  `discount` bigint(3) NOT NULL COMMENT '默认折扣',
  `initial_fee` DECIMAL(10,2) COMMENT '加盟费',
  `user_id` bigint(20) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销等级';

CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_no` bigint(20) COMMENT '商品编号',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `categories_id` bigint(20) COMMENT '分类ID',
  `cost` DECIMAL(5, 2) COMMENT '成本',
  `recommended_retail_price` DECIMAL(10,2) COMMENT '建议售价',
  `min_retail_price` DECIMAL(10,2) COMMENT '最低售价',
  `stock` bigint(10) COMMENT '库存',
  `status` bit(1) DEFAULT b'1' COMMENT '订单状态 1-在售, 0-停售',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  `update_date` datetime COMMENT '更新时间',
  `update_user` bigint(20) COMMENT '更新用户',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  foreign key(`create_user`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE `t_product_description` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `description` text COMMENT '商品描述',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  `update_date` datetime COMMENT '更新时间',
  `update_user` bigint(20) COMMENT '更新用户',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  foreign key(`product_id`) references t_product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品描述表';

CREATE TABLE `t_product_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `distributor_level_id` bigint(20) COMMENT '分销等级',
  `price` DECIMAL(10,2) COMMENT '分销价格',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  `update_date` datetime COMMENT '更新时间',
  `update_user` bigint(20) COMMENT '更新用户',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  foreign key(`product_id`) references t_product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品价格表';

CREATE TABLE `t_product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `image_code` VARCHAR(20) COMMENT '图片地址',
  `order` int(10) COMMENT '顺序',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  PRIMARY KEY (`id`),
  foreign key(`product_id`) references t_product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

