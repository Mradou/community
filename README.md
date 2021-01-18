## 阿兜社区

## 资料
[spring文档](https://spring.io/guides) 

[spring web文档](https://spring.io/guides/gs/serving-web-content/)

[es社区](https://elasticsearch.cn/explore)

[Bootstrap](https://v3.bootcss.com/)

[Github OAuth文档](https://docs.github.com/cn/free-pro-team@latest/developers/apps/building-oauth-apps)

[okhttp(用于GitHub登录)](https://square.github.io/okhttp/#post-to-a-server)

[github用户携带的信息](https://api.github.com/users/Mradou)

[thymeleaf文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

## 工具
[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com)


## 脚本
[question表的sql]
```sql 
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NULL,
  `description` text NULL,
  `gmt_create` bigint NULL,
  `gmt_modified` bigint NULL,
  `creator` int NOT NULL,
  `comment_count` int NULL DEFAULT 0,
  `view_count` int NULL DEFAULT 0,
  `like_count` int NULL DEFAULT 0,
  `tag` varchar(256) NULL,
  PRIMARY KEY (`id`)
);
```

[user表的sql]
```sql
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `account_id` varchar(100) NULL,
  `name` varchar(50) NULL,
  `token` varchar(100) NULL,
  `gmt_create` bigint(20) NULL,
  `gmt_modified` bigint(20) NULL,
  `picture_url` varchar(100) NULL,
  PRIMARY KEY (`id`)
);
```

[comment表的sql]
```sql
CREATE TABLE `comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL COMMENT '父类id',
  `type` int NOT NULL COMMENT '父类类型',
  `commentor` int NOT NULL COMMENT '评论人id',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '更新时间',
  `like_count` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`)
);
```
```bash
这将运行MBG，并指示其覆盖可能找到的所有现有Java文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```


