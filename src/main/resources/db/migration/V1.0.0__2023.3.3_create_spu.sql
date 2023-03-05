CREATE TABLE spu
(
    id           int                 NOT NULL primary key AUTO_INCREMENT comment '商品id',
    spu_code     varchar(255) unique not null comment '商品编码',
    spu_name     varchar(255)        not null comment '商品名称',
    spu_price    int                 not null comment '商品价格×1000后的价格',
    spu_discount int                 null comment '优惠后的标价×1000后的价格',
    spu_category int                 not null comment '商品种类'
);

create table promotion
(
    id                    int primary key auto_increment  not null comment '活动id',
    promotion_name        varchar(255)                    not null comment '活动名称',
    promotion_type        enum ('满减','买赠','捆绑折扣') not null comment '活动类型',
    promotion_description varchar(255) comment '活动描述',
    promotion_detail      json comment '{"price":10,"discount":2}; {"spuId":1,"song":2}'
);

create table category_list
(
    id                      int          not null primary key auto_increment comment '种类id',
    category_list_name      varchar(255) not null comment '种类名称',
    category_list_parent_id int          null comment '父级id'
);

create table spu_promotion_mapping
(
    id           int not null primary key auto_increment comment '主键',
    spu_id       int not null comment 'spu的id',
    promotion_id int not null comment 'promotion的id'
)