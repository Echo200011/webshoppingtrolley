create table spu
(
    id            int                                                            not null primary key auto_increment comment '商品id',
    `code`        varchar(255) unique                                            not null comment '商品编码',
    `name`        varchar(255)                                                   not null comment '商品名称',
    price         int                                                            not null comment '商品价格×1000后的价格',
    discount      int                                                            null comment '优惠后的标价×1000后的价格',
    category_id   int                                                            not null comment '商品种类',
    inventory     int                                  default 0                 not null comment '库存',
    `create_time` datetime                             default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime ON UPDATE CURRENT_TIMESTAMP                           null comment '更新时间',
    `status`      enum ('NEW','ON_SHELVES','SOLD_OUT') default 'NEW'             not null comment '商品状态',
    is_delete     tinyint                              default 0                 not null comment '是否逻辑删除',
    index `idx_category_id` (category_id),
    index `idx_name` (name),
    index `idx_code` (code),
    index `idx_discount` (discount),
    index `idx_create_time` (create_time),
    index `idx_status` (status)
);
insert into spu(name, code, price, discount, category_id)
values ('test', 'test2023', 10000, 8000, 1);
create table promotion
(
    id            int primary key auto_increment       not null comment '活动id',
    `name`        varchar(255)                         not null comment '活动名称',
    type          enum ('DISCOUNT','GIFT','BUNDLING')  not null comment '活动类型',
    `description` varchar(2047)                        null comment '活动描述',
    detail        json                                 not null comment '放入活动规则如:+{"price":10,"discount":2}; {"spuId":1,"song":2}',
    `create_time` datetime default CURRENT_TIMESTAMP   not null comment '创建时间',
    `update_time` datetime ON UPDATE CURRENT_TIMESTAMP null comment '更新时间',
    is_delete     tinyint  default 0                   not null comment '是否逻辑删除',
    index `idx_name` (name),
    index `idx_type` (type),
    index `idx_create_time` (create_time)
);
create table spu_categories
(
    id            int                                  not null primary key auto_increment comment '种类id',
    `name`        varchar(255)                         not null comment '种类名称',
    parent_id     int                                  null comment '父级id',
    `create_time` datetime default CURRENT_TIMESTAMP   not null comment '创建时间',
    `update_time` datetime ON UPDATE CURRENT_TIMESTAMP null comment '更新时间',
    is_delete     tinyint  default 0                   not null comment '否逻辑删除',
    index `idx_parent_id` (parent_id),
    index `idx_name` (name)
);
insert into spu_categories(id, name)
values (1, '水果');
create table spu_promotion_mapping
(
    id            int                                  not null primary key auto_increment comment '主键',
    spu_id        int                                  not null comment 'spu的id',
    promotion_id  int                                  not null comment 'promotion的id',
    `create_time` datetime default CURRENT_TIMESTAMP   not null comment '创建时间',
    `update_time` datetime ON UPDATE CURRENT_TIMESTAMP null comment '更新时间',
    index `idx_spu_id_promotion_id` (spu_id, promotion_id)
);
