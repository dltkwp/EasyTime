CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `realname` varchar(40) DEFAULT NULL COMMENT '姓名',
  `wechart` varchar(40) DEFAULT NULL COMMENT '微信',
  `alipay` varchar(40) DEFAULT NULL COMMENT '支付宝',
  `comment` VARCHAR(200) COMMENT '备注',
  `open_id` VARCHAR(200) COMMENT '小程序OpenId',
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

CREATE TABLE `t_distributor_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level_name` varchar(20) NOT NULL COMMENT '分销等级',
  `discount` bigint(3) NOT NULL COMMENT '默认折扣',
  `initial_fee` DECIMAL(10,2) COMMENT '加盟费',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销等级';

CREATE TABLE `t_relationship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) NOT NULL COMMENT '分销商ID',
  `pid` bigint(20) NOT NULL COMMENT '供应商ID',
  `distributor_level_id` bigint(20) NOT NULL COMMENT '分销等级ID',
  PRIMARY KEY (`id`),
  foreign key(`uid`) references t_user(`id`),
  foreign key(`pid`) references t_user(`id`),
  foreign key(`distributor_level_id`) references t_distributor_level(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应分销关系表';

CREATE TABLE `t_agent_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `pid` bigint(20) NOT NULL COMMENT '分销商ID',
  PRIMARY KEY (`id`),
  foreign key(`uid`) references t_user(`id`),
  foreign key(`pid`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销顾客关系表';

CREATE TABLE `t_user_recipients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '收件人ID',
  `recipients_phone` VARCHAR(20) COMMENT '收货电话',
  `recipients_address` VARCHAR(200) COMMENT '收件地址',
  `is_default` bit(1) NOT NULL DEFAULT b'0' COMMENT '1:默认 0:不默认',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顾客关系表';

CREATE TABLE `t_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `categories_name` varchar(20) NOT NULL COMMENT '类型名称',
  `user_id` bigint(20) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

insert into t_role(role_name) values('dealer');
insert into t_role(role_name) values('agent');
insert into t_role(role_name) values('normal');

CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_no` bigint(20) COMMENT '商品编号',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `categories_id` bigint(20) COMMENT '分类ID',
  `cost` DECIMAL(10, 2) COMMENT '成本',
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
  `allow` bit(1) DEFAULT b'0' COMMENT '允许分销',
  `discount` bigint(3) COMMENT '默认折扣',
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
  `image_code` VARCHAR(70) COMMENT '图片地址',
  `order_number` int(10) COMMENT '顺序',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  PRIMARY KEY (`id`),
  foreign key(`product_id`) references t_product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` VARCHAR(20) COMMENT '订单编号',
  `content` VARCHAR(200) COMMENT '订单内容',
  `recipients_id` bigint(20) COMMENT '收件人ID',
  `recipients` VARCHAR(20) COMMENT '收件人',
  `recipients_phone` VARCHAR(20) COMMENT '收货电话',
  `recipients_address` VARCHAR(200) COMMENT '收件地址',
  `payment` DECIMAL(10,2) COMMENT '支付金额',
  `pay_type` VARCHAR(20) COMMENT '支付方式',
  `pay_channel` VARCHAR(20) COMMENT '订单支付渠道',
  `out_trade_no` VARCHAR(50) COMMENT '订单支付单号',
  `status` VARCHAR(20) COMMENT '状态',
  `agent_id` bigint(20) NOT NULL COMMENT '分销商ID',
  `user_type` VARCHAR(20) NOT NULL COMMENT '创建订单用户分类',
  `source` VARCHAR(20) COMMENT  '来源',
  `review_date` datetime COMMENT '审核通过时间',
  `pay_date` datetime COMMENT '支付时间',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  `update_date` datetime COMMENT '更新时间',
  `update_user` bigint(20) COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

CREATE TABLE `t_order_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  `update_date` datetime COMMENT '更新时间',
  `update_user` bigint(20) COMMENT '更新用户',
  PRIMARY KEY (`id`),
  foreign key(`order_id`) references t_order(`id`),
  foreign key(`user_id`) references t_user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

CREATE TABLE `t_order_express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `company` VARCHAR(100) NOT NULL COMMENT '快递公司',
  `express_order` VARCHAR(100) NOT NULL COMMENT '快递单号',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  PRIMARY KEY (`id`),
  foreign key(`order_id`) references t_order(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单快递信息表';

CREATE TABLE `t_order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `product_number` bigint(10) NOT NULL COMMENT '购买数量',
  `product_price` DECIMAL(10,2) NOT NULL COMMENT '原始金额',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  PRIMARY KEY (`id`),
  foreign key(`order_id`) references t_order(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品信息表';

CREATE TABLE `t_order_payment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `payment` DECIMAL(10,2) COMMENT '修改金额',
  `comment` VARCHAR(200) COMMENT '备注',
  `create_date` datetime COMMENT '创建时间',
  `create_user` bigint(20) COMMENT '创建用户',
  PRIMARY KEY (`id`),
  foreign key(`order_id`) references t_order(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单改价表';

CREATE TABLE `t_shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_number` bigint(20) NOT NULL COMMENT '商品ID',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references t_user(`id`),
  foreign key(`product_id`) references t_product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `product_statistics` (
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `visit_count` bigint(20) NOT NULL  DEFAULT 0 COMMENT '浏览次数',
  `sale_quantity` BIGINT(20) NOT NULL DEFAULT 0 comment '销售总量',
  `sale_amount` BIGINT(20) NOT NULL  DEFAULT 0 COMMENT '销售总额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品销量表';