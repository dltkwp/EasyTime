create table t_b_user(
   id BIGINT(20) NOT NULL COMMENT '用户ID',
   username VARCHAR(16) NOT NULL COMMENT '用户名',
   password VARCHAR(32) NOT NULL COMMENT '密码',
   mobile VARCHAR(15) COMMENT '用户手机',
   PRIMARY KEY ( id )
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户表' AUTO_INCREMENT=1;

create table t_b_role(
   id BIGINT(20) NOT NULL COMMENT '权限ID',
   role_name VARCHAR(16) NOT NULL COMMENT '权限名称',
   PRIMARY KEY ( id )
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='权限表' AUTO_INCREMENT=1;

create table t_b_user_role(
   id BIGINT(20) NOT NULL COMMENT '用户权限ID',
   uid BIGINT(20) NOT NULL COMMENT '用户ID',
	 rid BIGINT(20) NOT NULL COMMENT '权限ID',
   PRIMARY KEY ( id ) ,
	 foreign key(uid) references t_b_user(id),
   foreign key(rid) references t_b_role(id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='权限表' AUTO_INCREMENT=1;

