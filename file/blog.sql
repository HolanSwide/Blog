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
    `cover` varchar(1024),
    `title` varchar(1024),
    `summary` varchar(2048),
    `body` text
)ENGINE=INNODB DEFAULT CHARSET=utf8;

drop table if exists `t_follow`;
create table `t_follow`(
    `i` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `uid` varchar(10),
    `username` varchar(16),
    `followUid` varchar(10),
    `followUsername` varchar(16),
    `time` varchar(32)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `t_user`(uid,username,`password`,auth) VALUES('10000000','holanswide','$2a$10$R8ap85jkgRdv6jTb2cqL4OXkh2uzIgeDHJrb4fYLIJnlIc5cJ3XP2','a0');
insert into `t_user_info`(uid, email, phone, birth, address, sex, sign) VALUES ('10000000','holanswide@yeah.net','19180869602','2002-07-27','四川成都','男','fly me 2 the moon');
INSERT INTO `t_user`(uid,username,`password`,auth) VALUES('10000001','AyanamiRei','$2a$10$R8ap85jkgRdv6jTb2cqL4OXkh2uzIgeDHJrb4fYLIJnlIc5cJ3XP2','a0');
insert into `t_user_info`(uid, email, phone, birth, address, sex, sign) VALUES ('10000001','rei@eva.com','12100000000','2002-07-22','第三新东京市','女','（・∀・）');
INSERT INTO `t_user`(uid,username,`password`,auth) VALUES('10000002','asuka','$2a$10$R8ap85jkgRdv6jTb2cqL4OXkh2uzIgeDHJrb4fYLIJnlIc5cJ3XP2','a0');
insert into `t_user_info`(uid, email, phone, birth, address, sex, sign) VALUES ('10000002','asuka@eva.com','12122222222','2002-05-07','德国汉堡','女','baka shinji');

INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000000','holanswide','10000001','AyanamiRei','2022-12-24 22:04:11');
INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000000','holanswide','10000002','asuka','2022-12-24 22:05:13');
INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000001','AyanamiRei','10000002','asuka','2022-12-24 22:05:34');
INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000001','AyanamiRei','10000000','holanswide','2022-12-24 22:05:51');
INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000002','asuka','10000001','AyanamiRei','2022-12-24 22:06:10');
INSERT INTO `t_follow`(uid, username, followUid, followUsername, time) VALUES ('10000002','asuka','10000000','holanswide','2022-12-24 22:06:11');

insert into `t_field`(fid, fname) VALUES ('ACG','动漫/游戏/二次元');
insert into `t_field`(fid, fname) VALUES ('ART','音乐/绘画/艺术');
insert into `t_field`(fid, fname) VALUES ('NEW','新闻/动态/评论');
insert into `t_field`(fid, fname) VALUES ('ESA','散文/诗歌/创作');
insert into `t_field`(fid, fname) VALUES ('FIL','文件/分享/工具');
insert into `t_essay`(pid, fid, uid, username, time, cover, title, summary, body) VALUES
                    ('ACG-100001','ACG','10000000','holanswide','2022-10-1','https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/8326cffc1e178a82abaea50cf703738da877e888.jpg',
                     '我好像做凌波丽小姐的狗啊','我好想做凌波丽小姐的狗啊。可是凌波丽小姐说她喜欢的是猫','<p>我好想做凌波丽小姐的狗啊。</p><p>可是凌波丽小姐说她喜欢的是猫，</p><p>我哭了。</p><p>我知道既不是狗也不是猫的我为什么要哭的。</p><p>因为我其实是一只老鼠。</p>');
insert into `t_essay`(pid, fid, uid, username, time, cover, title, summary, body) VALUES
                    ('ART-100002','ART','10000000','holanswide','2022-10-05 09:31:12','https://cdnmusic.migu.cn/picture/mv/1v6/39/76760ced16c31b8cad75376aabbdca93.jpg',
                     '手写的从前','JayChou', '<p>这风铃跟心动很接近</p><p>这封信还在怀念旅行</p><p>路过的爱情都太年轻</p><p>你是我想要 再回去的风景</p><p>这别离被瓶装成秘密</p><p>这雏菊美得像诗句</p><p>而我在风中等你 的消息</p><p>等月光落雪地</p><p>等枫红染秋季等相遇</p><p>我重温午后的阳光</p><p>将吉他斜背在肩上</p><p>跟多年前一样 我们轻轻地唱</p><p>去任何地方</p><p>我看着你的脸 轻刷着和弦</p><p>情人节卡片 手写的永远</p><p>还记得广场公园 一起表演</p><p>校园旁糖果店 记忆里在微甜</p><p>我看着你的脸 轻刷着和弦</p><p>初恋是整遍 手写的从前</p><p>还记得那年秋天 说了再见</p><p>当恋情已走远</p><p>我将你深埋在 心里面</p><p>微风需要竹林 溪流需要蜻蜓</p><p>乡愁般的离开 需要片片浮萍</p><p>记得那年的雨季 回忆里特安静</p><p>哭过后的决定 是否还能进行</p><p>我傻傻等待 傻傻等春暖花开</p><p>等终等于等明等白 等爱情回来</p><p>青春属于表白 阳光属于窗台</p><p>而我想我属于</p><p>一个拥有你的未来</p><p>纸上的彩虹 用素描画的钟</p><p>我还在修改 回忆之中你的笑容</p><p>该怎么去形容 为思念酝酿的痛</p><p>夜空霓虹 都是我不要的繁荣</p><p>或许去趟沙滩 或许去看看夕阳</p><p>或许任何一个可以想心事的地方</p><p>情绪在咖啡馆 被调成一篇文章</p><p>彻底爱上你如诗一般透明的泪光</p>');