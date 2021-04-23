CREATE TABLE `oms_owner_order_logistics`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `is_deleted`           tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识 1=删除 0=有效',
    `gmt_create`           timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `create_operator`      varchar(64) NOT NULL COMMENT '创建者',
    `create_operator_id`   bigint(20) NOT NULL COMMENT '创建者id',
    `gmt_modified`         timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    `modify_operator_id`   bigint(20) NOT NULL COMMENT '修改者id',
    `modify_operator`      varchar(64) NOT NULL DEFAULT '' COMMENT '修改者',
    `owner_order_code`     varchar(64) NOT NULL COMMENT '货主订单code',
    `logistics_order_code` varchar(64)          DEFAULT NULL COMMENT '运输订单code',
    `receipt_type`         varchar(64)          DEFAULT NULL COMMENT '回单类型',
    `receipt_num`          int(11) DEFAULT NULL COMMENT '回单数量',
    `transport_fee`        decimal(13, 2)       DEFAULT NULL COMMENT '运费',
    `payment_mode`         varchar(64)          DEFAULT NULL COMMENT '付款方式,中文字符串(PRESENTATION("提付", 1),\r\nCASH("现付", 2),\r\nARRIVED("到付", 3),\r\nRETURN("回单付", 4),\r\nIOAN("货款扣", 5),\r\nMONTH("月结", 6),\r\nBLEND("混合支付", 7),\r\nOILCARD("油卡付", 8),\r\nBANK("银行转账", Integer.valueOf(9)),\r\nBANKER_ACCEPTANCE("银行承兑汇票", Integer.valueOf(10)),\r\nCOMMERCIAL_ACCEPTANCE("商业承兑汇票", Integer.valueOf(11)),\r\nCREDIT_TRANSFER_CHEQUE("信用证转帐支票", Integer.valueOf(12)),\r\nCASH_CHECK("现金支票", Integer.valueOf(13)),\r\nCASH_2("现金", Integer.valueOf(14));)',
    `carrier_id`           bigint(20) DEFAULT NULL COMMENT '承运方ID',
    `carrier`              varchar(255)         DEFAULT NULL COMMENT '承运方名称',
    `shipper_id`           bigint(20) NOT NULL COMMENT '货主ID',
    `shipper`              varchar(255)         DEFAULT NULL COMMENT '货主名称',
    `customer_rela_code`   varchar(64)          DEFAULT NULL COMMENT '[冗余,非必要]货主code',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='货主运输子订单';




ALTER TABLE `oms_owner_order_warehouse`
    ADD COLUMN `client_code` varchar(64) NULL COMMENT '客户单号';


ALTER TABLE `oms_owner_order_logistics`
    ADD COLUMN `client_code` varchar(64) NULL COMMENT '客户单号' ;



