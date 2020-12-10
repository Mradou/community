## 阿兜社区

## 资料
[spring文档](https://spring.io/guides) 

[spring web文档](https://spring.io/guides/gs/serving-web-content/)

[es社区](https://elasticsearch.cn/explore)

[Bootstrap](https://v3.bootcss.com/)

[Github OAuth文档](https://docs.github.com/cn/free-pro-team@latest/developers/apps/building-oauth-apps)

[okhttp(用于GitHub登录)](https://square.github.io/okhttp/#post-to-a-server)

## 工具
[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com)

## 脚本
[question表的sql]
```sql 
CREATE TABLE `community`.`Untitled`  (
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

