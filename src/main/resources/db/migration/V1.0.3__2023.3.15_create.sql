create table shopping_cart
(
    id int not null primary key auto_increment comment '主键',
    spu_id int not null  comment '商品id',
    price int not null  comment '商品原价',
    discount int default 0 not null comment '优惠价',
    `count` int default 0 not null  comment '购买数量'
)