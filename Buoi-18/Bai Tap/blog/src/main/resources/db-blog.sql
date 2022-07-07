-- cau 1(done)
SELECT b.id, b.title, b.slug, b.description, b.thumbnail,
       DATE_FORMAT(b.published_at, '%d/%m/%Y') as published_at,
       json_object('id', u.id, 'name', u.name) as author,
       COUNT(c.id) as count_comment
from blog b
         left join `user` u on b.user_id = u.id
         LEFT JOIN comment c on b.id = c.blog_id
where b.status = 1
GROUP by b.id
ORDER BY b.published_at DESC;

-- cau 1.1(done)
SELECT b.id, b.title, b.slug, b.description, b.thumbnail,
       DATE_FORMAT(b.pulished_at, '%d/%m/%Y') as pulished_at,
       json_object("id", u.id, "name", u.name) as author,
       COUNT(c.id) as count_comment
from blog b
         left join `user`u on b.user_id = u.id
         LEFT JOIN comment c on b.id = c.blog_id
where b.status = 1
GROUP by b.id
ORDER BY b.pulished_at DESC
    limit 5
OFFSET 0;

-- cau 2 (done)
SELECT b.id , b.title , b.slug ,
       DATE_FORMAT(b.published_at, '%d/%m/%Y') as published_at,
       COUNT(c.id) as count_comment
FROM blog b
         left join comment c on c.blog_id = b.id
GROUP BY b.id
ORDER BY count_comment DESC
    LIMIT 3


-- cau 3 (done)
SELECT c.name,
       COUNT(b.id) as count_blog
FROM blog_categories bc
         left join category c on c.id = bc.categories_id
         LEFT JOIN blog b on b.id = bc.blog_id
GROUP BY c.id
ORDER BY count_blog DESC
    limit 5;

-- cau4 (done)
SELECT b.id, b.title, b.slug, b.description, b.thumbnail,
       DATE_FORMAT(b.published_at, '%d/%m/%Y') as published_at,
       json_object('id', u.id, 'name', u.name) as author,
       COUNT(c.id) as count_comment
from blog b
         left join `user` u on b.user_id = u.id
         LEFT JOIN comment c on b.id = c.blog_id
         LEFT join blog_categories bc on b.id = bc.blog_id
where b.status = 1 and bc.categories_id = 1
GROUP by b.id
ORDER BY b.published_at DESc



-- cau5 (done)
SELECT b.title,
       json_object('id', u.id, 'name', u.name, 'avatar', u.avatar) as author
FROM blog b
         left join `user` u on u.id = b.user_id ;

-- cau 6(done)
SELECT u.id, u.name,
       json_arrayagg(json_object('title', b.title, 'description', b.description, 'thumbnail', b.thumbnail)) as blog
FROM `user` u
         left join blog b on b.user_id = u.id
GROUP BY u.id ;

-- cau 7(done)
select b.id, json_arrayagg(json_object('id', u.id,
                                       'name', u.name,
                                       'avatar', u.avatar,
                                       'created_at', DATE_FORMAT(c.created_at , '%d/%m/%Y'),
                                       'content', c.content)
    ) as comment
FROM blog b
         LEFT JOIN `user` u  on u.id  = b.user_id
         left join comment c on c.user_id = u.id
WHERE b.id = '1bY'

-- cau 8 (done)
SELECT b.id ,
       json_object('id', b.id ,
                   'title', b.title ,
                   'slug', b.slug ,
                   'description', b.description ,
                   'content', b.content ,
                   'thumbnail', b.thumbnail,
                   'published_at',DATE_FORMAT(b.published_at, '%d/%m/%Y'),
                   'author', json_object('id', u.id, 'name', u.name)
           )  as blog
FROM blog b
         left join `user` u on u.id = b.user_id
WHERE b.id = '1bY'


-- cau 9
SELECT json_object('id', b.id ,
                         'title', b.title ,
                         'slug', b.slug ,
                         'description', b.description ,
                         'content', b.content ,
                         'thumbnail', b.thumbnail,
                         'published_at',DATE_FORMAT(b.published_at, '%d/%m/%Y'),
                         'author', json_object('id', u.id, 'name', u.name),
                         'comment', json_arrayagg(json_object('id', u.id,
                                                              'name', u.name,
                                                              'avatar', u.avatar,
                                                              'created_at', DATE_FORMAT(c.created_at , '%d/%m/%Y'),
                                                              'content', c.content)
                                                  )
    ) as blog
FROM blog b
         LEFT JOIN `user` u  on u.id  = b.user_id
         left join comment c on c.user_id = u.id
WHERE b.id = '1bY'

-- 123


