CREATE TABLE `tsp_command_record` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`request_id` VARCHAR(100) NOT NULL COMMENT '命令请求ｉｄ,全局唯一。',
	`vin` VARCHAR(255) NOT NULL COMMENT '发送命令的车辆vin',
  `source_sys_name` VARCHAR(100) NOT NULL COMMENT '命令发起系统',
	`sequence_id` VARCHAR(50)  NULL DEFAULT NULL COMMENT 'tsp生成的编码,全局唯一',
	`tsp_function` VARCHAR(255) NULL DEFAULT NULL COMMENT '发送命令类型\\r\\n  REMOTE_LOCK,\\r\\n  REMOTE_UNLOCK,\\r\\n  START_VEHICLE,//启动汽车命令\\r\\n  REMOTE_LOCATION,//远程定位（仅获取一次定位的数据）\\r\\n  REMOTE_DIAGNOSE,//远程诊断\\r\\n  REMOTE_CONDITION,//远程控制 车窗开关、空调、鸣笛、车门上锁\\r\\n  SEAT,//座位位置 COMPONENT_STATUS//获取车辆部件状态',
	`status` VARCHAR(255) NOT NULL DEFAULT 'PENDING' COMMENT '命令状态,PENDING:命令创建等待处理,SEND_TO_VG:命令下发到车机网关,ARRIVE_CAR_MACHINE:命令抵达车机,REJECT:因为业务原因，拒绝处理,FAILURE:执行结果显示失败,SUCCESS:执行结果显示成功,\\nTIMEOUT:规定时间内未接收到ack(应答)或者执行结果。',
	`content` VARCHAR(5000) NOT NULL COMMENT '原始命令内容,json字符串',
	`content_result` VARCHAR(5000) NULL DEFAULT NULL COMMENT '命令返回结果，json字符串',
	`remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '状态切换备注',
	`create_time` DATETIME(3) NOT NULL COMMENT '创建时间',
	`update_time` TIMESTAMP(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3),
	PRIMARY KEY (`id`),
	UNIQUE INDEX `ui_request_id` (`request_id`),
	INDEX `ui_sequence_id` (`sequence_id`)
)
COMMENT='Tsp命令记录表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
