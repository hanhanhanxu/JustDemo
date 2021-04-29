CREATE TABLE test_dynamic_tim_task(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`name` VARCHAR(64) COMMENT '业务名称',
	`code` VARCHAR(64) COMMENT '业务code，唯一',
    `cron` VARCHAR(30) COMMENT '业务执行周期',
	`inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '逻辑删除，1 正常，0 删除',
    PRIMARY KEY (id)
);


INSERT INTO test_dynamic_tim_task (name, code, cron) VALUES
        ('接口错误统计任务', 'fun', '0 0/1 * * * ?'),
        ('合同过期统计任务', 'expired', '0 0/2 * * * ?');