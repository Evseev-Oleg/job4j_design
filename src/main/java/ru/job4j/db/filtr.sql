select product.name from product, type where type.name = 'сыр' and type.id = product.type_id;
select * from product where name like '%мороженное%';
select * from product where expired_date < current_date;
select name from product
     where price = (select max(price) from product);
select t.name,count(p.name) from type as t,product as p where t.id = p.type_id group by t.name;
select * from product, type where (type.name = 'сыр' or type.name = 'молоко') and type.id = product.type_id;

select t.name from type as t,product as p
     where t.id = p.type_id
	 group by t.name having count(t.name) < 10;