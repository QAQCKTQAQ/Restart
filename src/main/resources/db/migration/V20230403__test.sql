CREATE TABLE IF NOT EXISTS `auth_application` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(64) DEFAULT NULL COMMENT '应用名称',
`code` varchar(64) DEFAULT '' COMMENT '应用code',
`status` tinyint(3) unsigned DEFAULT NULL COMMENT '状态:1启用 2停用',
`comment` varchar(255) DEFAULT NULL COMMENT '描述',
`if_deleted` tinyint(4) NOT NULL DEFAULT '0',
`creator` varchar(50) DEFAULT NULL COMMENT '创建人',
`modifier` varchar(50) DEFAULT NULL COMMENT '更新人',
`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';
