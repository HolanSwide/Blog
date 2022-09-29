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

INSERT INTO `t_user`(uid,username,`password`,auth) VALUES('10000000','holanswide','$2a$10$R8ap85jkgRdv6jTb2cqL4OXkh2uzIgeDHJrb4fYLIJnlIc5cJ3XP2','a0');
insert into `t_user_info`(uid, email, phone, birth, address, sex, sign) VALUES ('10000000','holanswide@yeah.net','19180869602','2002-07-27','四川成都','男','fly me 2 the moon');
