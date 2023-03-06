CREATE TABLE spu
(
    id       int                 not null primary key auto_increment comment '商品id',
    `code`   varchar(255) unique not null comment '商品编码',
    `name`   varchar(255)        not null comment '商品名称',
    price    int                 not null comment '商品价格×1000后的价格',
    discount int                 null comment '优惠后的标价×1000后的价格',
    category int                 not null comment '商品种类'
);
create table promotion
(
    id            int primary key auto_increment                       not null comment '活动id',
    `name`        varchar(255)                                         not null comment '活动名称',
    type          enum ('DISCOUNT','GIFT','BUNDLING') not null comment '活动类型',
    `description` varchar(2048) comment '活动描述',
    detail        json                                                 not null comment '{"price":10,"discount":2}; {"spuId":1,"song":2}'
);
create table merchandise_categories
(
    id        int          not null primary key auto_increment comment '种类id',
    `name`    varchar(255) not null comment '种类名称',
    parent_id int          null comment '父级id'
);
create table spu_promotion_mapping
(
    id           int        not null primary key auto_increment comment '主键',
    spu_id       int        not null comment 'spu的id',
    promotion_id int unique not null comment 'promotion的id',
    index `spu_promotion` (spu_id, promotion_id)
);
