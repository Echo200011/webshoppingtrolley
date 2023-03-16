create table spu
(
    id            int                                                            not null primary key auto_increment comment '商品id',
    `code`        varchar(255) unique                                            not null comment '商品编码',
    `name`        varchar(255)                                                   not null comment '商品名称',
    price         int                                                            not null comment '商品价格×1000后的价格',
    bid_price     int                                                            null comment '优惠后的标价×1000后的价格',
    category_id   int                                                            not null comment '商品种类',
    stock         int                                  default 0                 not null comment '库存',
    `create_time` datetime                             default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime ON UPDATE CURRENT_TIMESTAMP                           null comment '更新时间',
    `status`      enum ('NEW','ON_SHELVES','SOLD_OUT') default 'NEW'             not null comment '商品状态',
    is_delete     tinyint                              default 0                 not null comment '是否逻辑删除',
    index `idx_category_id` (category_id),
    index `idx_name` (name),
    index `idx_code` (code),
    index `idx_discount` (bid_price),
    index `idx_create_time` (create_time),
    index `idx_status` (status)
);
insert into spu(name, code, price, bid_price, category_id)
values ('test', 'test2023', 200000, 190000, 1);
insert into spu(name, code, price, bid_price, category_id)
values ('test', 'test2024', 100000, 80000, 1);
insert into spu(name, code, price, bid_price, category_id)
values ('test', 'test2025', 100000, 80000, 1);
insert into spu(name, code, price, bid_price, category_id)
values ('test', 'test2026', 100000, 80000, 1);
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
insert into promotion(id, name, type, description, detail)
values (1,'test1', 'BUNDLING', 'test1', '{"type": "BUNDLING", "level": 1, "discount": 800}');
insert into promotion(id, name, type, description, detail)
values (2,'test2', 'DISCOUNT', 'test2', '{"full": 200, "type": "DISCOUNT", "level": 2, "discount": 30}');
insert into promotion(id, name, type, description, detail)
values (3,'test3', 'GIFT', 'test3', '{"type": "GIFT", "level": 3, "giftSpuIdList": [1, 2, 3]}');
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
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (1,1,1);
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (2,1,2);
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (3,1,3);
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (4,2,1);
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (5,3,2);
insert into spu_promotion_mapping(id, spu_id, promotion_id)
values (6,3,3);
