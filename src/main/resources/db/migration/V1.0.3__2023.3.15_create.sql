create table shopping_cart_line
(
    id       int           not null primary key auto_increment comment '主键',
    `code`   int default 1 not null comment '购物车code',
    `spu_id` int           not null comment '商品id',
    `count`  int default 0 not null comment '购买数量',
    index `idx_count` (count),
    index `idx_spu_id` (spu_id),
    index `idx_cod` (code)
);
insert into shopping_cart_line(id, code, spu_id)
values (1, 1, 1);
insert into shopping_cart_line(id, code, spu_id)
values (2, 1, 2);
insert into shopping_cart_line(id, code, spu_id)
values (3, 1, 3);
insert into shopping_cart_line(id, code, spu_id)
values (4, 1, 4);