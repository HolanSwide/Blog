DROP DATABASE IF EXISTS `blog`;
CREATE DATABASE `blog`;
USE `blog`;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
  `i` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `uid` VARCHAR(10),
  `username` VARCHAR(16),
  `password` VARCHAR(256),
  `auth` VARCHAR(16)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

drop table if exists `t_user_info`;
create table `t_user_info`(
    `i` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `uid` VARCHAR(10),
    `email` varchar(32),
    `phone` varchar(16),
    `birth` varchar(64),
    `address` varchar(64),
    `sex` varchar(4),
    `sign` tinytext
) ENGINE=INNODB DEFAULT CHARSET=utf8;

drop table if exists `t_field`;
create table `t_field` (
    `i` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fid` varchar(12),
    `fname` varchar(16)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

drop table if exists `t_essay`;
create table `t_essay`(
    `i` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `pid` varchar(32),
    `fid` varchar(12),
    `uid` varchar(10),
    `username` varchar(16),
    `time` varchar(32),
    `cover` varchar(128),
    `title` varchar(128),
    `summary` varchar(256),
    `body` text
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `t_user`(uid,username,`password`,auth) VALUES('10000000','holanswide','$2a$10$R8ap85jkgRdv6jTb2cqL4OXkh2uzIgeDHJrb4fYLIJnlIc5cJ3XP2','a0');
insert into `t_user_info`(uid, email, phone, birth, address, sex, sign) VALUES ('10000000','holanswide@yeah.net','19180869602','2002-07-27','四川成都','男','fly me 2 the moon');
insert into `t_field`(fid, fname) VALUES ('ACG','动漫/游戏/二次元');
insert into `t_essay`(pid, fid, uid, username, time, cover, title, summary, body) VALUES
                    ('ACG-100001','ACG','10000000','holanswide','2022-10-1','https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/8326cffc1e178a82abaea50cf703738da877e888.jpg',
                     '我好像做凌波丽小姐的狗啊','我好想做凌波丽小姐的狗啊。可是凌波丽小姐说她喜欢的是猫','我好想做凌波丽小姐的狗啊。可是凌波丽小姐说她喜欢的是猫，我哭了。我知道既不是狗也不是猫的我为什么要哭的。因为我其实是一只老鼠。')