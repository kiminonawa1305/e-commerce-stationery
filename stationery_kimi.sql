/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : stationery_kimi

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 24/06/2024 03:51:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill_details
-- ----------------------------
DROP TABLE IF EXISTS `bill_details`;
CREATE TABLE `bill_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `bill_id` bigint DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_option_id` bigint DEFAULT NULL,
  `discount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwm4sko9p82ndh6belyxx12bj` (`bill_id`),
  KEY `FKbevea26g0j2g9r87inag1omdp` (`product_option_id`),
  CONSTRAINT `FKbevea26g0j2g9r87inag1omdp` FOREIGN KEY (`product_option_id`) REFERENCES `product_options` (`id`),
  CONSTRAINT `FKfwm4sko9p82ndh6belyxx12bj` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of bill_details
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bill_statuses
-- ----------------------------
DROP TABLE IF EXISTS `bill_statuses`;
CREATE TABLE `bill_statuses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `bill_id` bigint DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcgo6x0vwvff04mcdrjpgxrttv` (`bill_id`),
  CONSTRAINT `FKcgo6x0vwvff04mcdrjpgxrttv` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of bill_statuses
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bills
-- ----------------------------
DROP TABLE IF EXISTS `bills`;
CREATE TABLE `bills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `payment_method` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `shipping_fee` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `shipping_address` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `shipping_note` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk8vs7ac9xknv5xp18pdiehpp1` (`user_id`),
  CONSTRAINT `FKk8vs7ac9xknv5xp18pdiehpp1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of bills
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `lock` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of categories
-- ----------------------------
BEGIN;
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (1, 'Bút viết', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (2, 'Văn phòng phẩm', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (3, 'Bút mực', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (4, 'Giấy', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (5, 'Bút viết cao cấp', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (6, 'Giấy In', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (7, 'Dụng cụ học tập', b'0');
INSERT INTO `categories` (`id`, `name`, `lock`) VALUES (8, 'Mỹ thuật', b'0');
COMMIT;

-- ----------------------------
-- Table structure for discounts
-- ----------------------------
DROP TABLE IF EXISTS `discounts`;
CREATE TABLE `discounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_percent` double DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of discounts
-- ----------------------------
BEGIN;
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (1, 0.1, '2023-05-31 23:59:59.000000', '2023-05-01 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (2, 0.15, '2024-01-15 23:59:59.000000', '2023-12-15 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (3, 0.08, '2023-02-14 23:59:59.000000', '2023-02-14 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (4, 0.2, '2024-05-01 23:59:59.000000', '2024-04-30 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (5, 0.3, '2023-09-02 23:59:59.000000', '2023-09-02 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (6, 0.15, '2023-11-11 23:59:59.000000', '2023-11-11 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (7, 0.18, '2023-06-19 23:59:59.000000', '2023-06-18 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (8, 0.09, '2024-03-08 23:59:59.000000', '2024-03-08 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (9, 0.13, '2023-10-20 23:59:59.000000', '2023-10-20 00:00:00.000000');
INSERT INTO `discounts` (`id`, `discount_percent`, `end_date`, `start_date`) VALUES (10, 0.35, '2023-09-25 23:59:59.000000', '2023-09-22 00:00:00.000000');
COMMIT;

-- ----------------------------
-- Table structure for product_images
-- ----------------------------
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`),
  CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of product_images
-- ----------------------------
BEGIN;
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (1, NULL, '/images/product/1.but-bi.webp', 1);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (2, NULL, '/images/product/2.ong-muc.webp', 2);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (3, NULL, '/images/product/3.but-diem-10.jpg', 3);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (4, NULL, '/images/product/4.but-chi-go.webp', 4);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (5, NULL, '/images/product/5gel-diem-10.webp', 5);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (6, NULL, '/images/product/6gel-smooth.webp', 6);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (7, NULL, '/images/product/7but-long-kim.webp', 7);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (8, NULL, '/images/product/8but-sap-dau.webp', 8);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (9, NULL, '/images/product/9but-xoa.webp', 9);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (10, NULL, '/images/product/10gel-alona.webp', 10);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (11, NULL, '/images/product/11kep-giay.webp', 11);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (12, NULL, '/images/product/12go-kim.webp', 12);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (13, NULL, '/images/product/13bia-cong.webp', 13);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (14, NULL, '/images/product/14cap-sach.webp', 14);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (15, NULL, '/images/product/15keo.webp', 15);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (16, NULL, '/images/product/16ream-giay.webp', 16);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (17, NULL, '/images/product/17mau-nuoc.webp', 17);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (18, NULL, '/images/product/18sap-mau.webp', 18);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (19, NULL, '/images/product/19bia-lo.webp', 19);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (20, NULL, '/images/product/20dao-roc-giay.webp', 20);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (21, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Flexoffice FO-069VN/Bút bi cao cấp Flexoffice FO-069VN-1.png', 29);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (22, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Flexoffice FO-069VN/Bút bi cao cấp Flexoffice FO-069VN-2.png', 29);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (23, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Flexoffice FO-069VN/Bút bi cao cấp Flexoffice FO-069VN-3.png', 29);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (24, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Flexoffice FO-069VN/Bút bi cao cấp Flexoffice FO-069VN-4.png', 29);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (25, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Flexoffice FO-069VN/Bút bi cao cấp Flexoffice FO-069VN-5.png', 29);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (26, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)-1.png', 30);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (27, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)-2.png', 30);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (28, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)-3.png', 30);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (29, NULL, '/images/product/Bút cao cấp/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)/Bút bi cao cấp Thiên Long Bizner BIZ-1640YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)-4.png', 30);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (30, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589-1.png', 28);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (31, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589-2.png', 28);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (32, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589-3.png', 28);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (33, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589-4.png', 28);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (34, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589/Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589-5.png', 28);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (35, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585-1.png', 24);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (36, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585-2.png', 24);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (37, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585-3.png', 24);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (38, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585/Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585-4.png', 24);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (39, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583-1.png', 25);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (40, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583-2.png', 25);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (41, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583-3.png', 25);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (42, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583-4.png', 25);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (43, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583/Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583-5.png', 25);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (44, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586-1.png', 27);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (45, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586-2.png', 27);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (46, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586-3.png', 27);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (47, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586-4.png', 27);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (48, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586/Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586-5.png', 27);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (49, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578-1.png', 26);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (50, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578-2.png', 26);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (51, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578-3.png', 26);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (52, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578-4.png', 26);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (53, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578/Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578-5.png', 26);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (54, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K-1.jpg', 21);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (55, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K-2.jpg', 21);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (56, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K-3.jpg', 21);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (57, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K-4.jpg', 21);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (58, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K-5.jpg', 21);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (59, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K-1.png', 23);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (60, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K-2.png', 23);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (61, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K-3.png', 23);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (62, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K-4.png', 23);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (63, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K/Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K-5.png', 23);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (64, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873-1.png', 22);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (65, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873-2.png', 22);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (66, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873-3.png', 22);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (67, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873-4.png', 22);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (68, NULL, '/images/product/Bút cao cấp/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873/Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873-5.png', 22);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (69, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 40);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (70, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 40);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (71, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 40);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (72, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 40);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (73, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 39);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (74, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 39);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (75, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 39);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (76, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 39);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (77, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 37);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (78, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 37);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (79, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 37);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (80, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 37);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (81, NULL, '/images/product/Giấy In/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-5.png', 37);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (82, NULL, '/images/product/Giấy In/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 35);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (83, NULL, '/images/product/Giấy In/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 35);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (84, NULL, '/images/product/Giấy In/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 17);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (85, NULL, '/images/product/Giấy In/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 35);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (86, NULL, '/images/product/Giấy In/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-5.png', 35);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (87, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK siêu tiết kiệm/Combo giấy in văn phòng IK siêu tiết kiệm-1.png', 41);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (88, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK siêu tiết kiệm/Combo giấy in văn phòng IK siêu tiết kiệm-2.png', 41);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (89, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK siêu tiết kiệm/Combo giấy in văn phòng IK siêu tiết kiệm-3.png', 41);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (90, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK siêu tiết kiệm/Combo giấy in văn phòng IK siêu tiết kiệm-4.png', 41);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (91, NULL, '/images/product/Giấy In/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 34);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (92, NULL, '/images/product/Giấy In/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 34);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (93, NULL, '/images/product/Giấy In/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 34);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (94, NULL, '/images/product/Giấy In/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 34);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (95, NULL, '/images/product/Giấy In/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-5.png', 34);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (96, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 33);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (97, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 33);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (98, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 33);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (99, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 36);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (100, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 36);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (101, NULL, '/images/product/Giấy In/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 36);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (102, NULL, '/images/product/Giấy In/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-1.png', 32);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (103, NULL, '/images/product/Giấy In/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-2.png', 32);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (104, NULL, '/images/product/Giấy In/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-3.png', 32);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (105, NULL, '/images/product/Giấy In/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia/Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia-4.png', 32);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (106, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm-1.png', 42);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (107, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm-2.png', 42);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (108, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm-3.png', 42);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (109, NULL, '/images/product/Giấy In/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm/Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm-4.png', 42);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (110, NULL, '/images/product/but_chi_cham_1.webp', 43);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (111, NULL, '/images/product/but_chi_cham_2.webp', 43);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (112, NULL, '/images/product/bang_thong_minh_1.webp', 44);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (113, NULL, '/images/product/bang_thong_minh_2.webp', 44);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (114, NULL, '/images/product/bang_thong_minh_3.webp', 44);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (115, 'Hồng', '/images/product/may_chuot_chi_hong.webp', 45);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (116, 'Xanh', '/images/product/may_chuot_chi_xanh.webp', 45);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (117, 'Hồng', '/images/product/gom_dien_tu_1.webp', 46);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (118, 'Xanh', '/images/product/gom_dien_tu_2.webp', 46);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (119, 'Đen', '/images/product/luoi_chuot_1.webp', 47);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (120, 'Xanh', '/images/product/may_hut_bui_xanh.webp', 48);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (121, 'Hồng', '/images/product/may_hut_bui_hong.webp', 48);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (122, 'Đen', '/images/product/bop_viet_2_ngan.webp', 49);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (123, 'Xanh', '/images/product/keo_sua.webp', 50);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (124, 'Xanh', '/images/product/bo_bang_diem.webp', 51);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (125, 'Đỏ`', '/images/product/combo_10_bia.webp', 52);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (126, 'CPC-C029 12 màu`', '/images/product/CPC-C029 _12_ mau.webp', 53);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (127, 'CPC-C030 24 màu`', '/images/product/CPC-C030 _24_ mau.webp', 53);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (128, 'SCR-C001 12 màu`', '/images/product/SCR-C001_ 12_ mau.webp', 54);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (129, 'SCR-C002 24 màu`', '/images/product/SCR-C002_ 24_ mau.webp', 54);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (130, 'OP-C029  12 màu`', '/images/product/OP-C029 _ 12_ mau.webp', 55);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (131, 'OP-C030  18 màu`', '/images/product/OP-C030 _18_ mau.webp', 55);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (132, 'OP-C031  24 màu`', '/images/product/OP-C031 _ 24_ mau.webp', 55);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (133, 'CPC-C034  12 màu`', '/images/product/CPC-C034 _ 12_ mau.webp', 56);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (134, 'CPC-C035  24 màu`', '/images/product/CPC-C035_ 24_ mau.webp', 56);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (135, 'CPC-C034  12 màu`', '/images/product/CPC-C034_12_ mau.jpg', 56);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (136, 'CPC-C035  18 màu`', '/images/product/CPC-C035_18_ mau.webp', 56);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (137, NULL, '/images/product/bo_tap_to_1.webp', 57);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (138, 'SWP-C003/AK - 6 màu', '/images/product/SWP-C003_AK _6 mau.webp', 58);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (139, 'SWP-C004/AK - 12 màu', '/images/product/SWP-C004_AK -_12_ mau.webp', 58);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (140, 'SWM-C010/AK - 12 màu', '/images/product/SWM-C010_AK -_12 mau.webp', 59);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (141, 'SWM-C011/AK - 24 màu', '/images/product/SWM-C011_AK _24_mau.webp', 59);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (142, 'SWM-C010/AK - 12 màu', '/images/product/SWM-C010_AK -_12 mau.webp', 60);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (143, 'SWM-C011/AK - 24 màu', '/images/product/SWM-C011_AK _24_mau.webp', 60);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (144, 'SWM-C010/AK - 12 màu', '/images/product/SWM-C010_AK -_12 mau.webp', 61);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (145, 'SWM-C011/AK - 24 màu', '/images/product/SWM-C011_AK _24_mau.webp', 61);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (146, 'PCR-017/AK - Hạt đậu', '/images/product/PCR-017_AK _Hat_dau.webp', 62);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (147, 'PCR-018/AK - Phi thuyền', '/images/product/PCR-018_AK _phi_thuyen.webp', 62);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (148, 'SWP-C001 - 6 màu', '/images/product/SWP-C001 _6_ mau.jpg', 63);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (149, 'SWP-C002 - 12 màu', '/images/product/SWP-C002 _12_ mau.webp', 63);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (150, NULL, '/images/product/combo_nang_tien_ca_1.webp', 64);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (151, NULL, '/images/product/hop_12_mau_1.jpg', 65);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (152, NULL, '/images/product/hop_12_mau_2.webp', 65);
INSERT INTO `product_images` (`id`, `type`, `url`, `product_id`) VALUES (153, NULL, '/images/product/hop_12_mau_3.webp', 65);
COMMIT;

-- ----------------------------
-- Table structure for product_options
-- ----------------------------
DROP TABLE IF EXISTS `product_options`;
CREATE TABLE `product_options` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8vv4f8fru80wxocwgxwsrow61` (`product_id`),
  CONSTRAINT `FK8vv4f8fru80wxocwgxwsrow61` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of product_options
-- ----------------------------
BEGIN;
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (1, 'Mặc định', 29, 1);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (2, 'Mặc định', 131, 2);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (3, 'Mặc định', 184, 3);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (4, 'Mặc định', 21, 4);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (5, 'Mặc định', 18, 5);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (6, 'Mặc định', 179, 6);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (7, 'Mặc định', 181, 7);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (8, 'Mặc định', 55, 8);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (9, 'Mặc định', 147, 9);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (10, 'Mặc định', 94, 10);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (11, 'Mặc định', 23, 11);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (12, 'Mặc định', 157, 12);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (13, 'Mặc định', 102, 13);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (14, 'Mặc định', 72, 14);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (15, 'Mặc định', 83, 15);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (16, 'Mặc định', 110, 16);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (17, 'Mặc định', 122, 17);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (18, 'Mặc định', 190, 18);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (19, 'Mặc định', 98, 19);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (20, 'Mặc định', 58, 20);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (21, 'Mặc định', 76, 21);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (22, 'Mặc định', 1, 22);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (23, 'Mặc định', 113, 23);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (24, 'Mặc định', 71, 24);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (25, 'Mặc định', 104, 25);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (26, 'Mặc định', 184, 26);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (27, 'Mặc định', 195, 27);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (28, 'Mặc định', 196, 28);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (29, 'Mặc định', 11, 29);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (30, 'Mặc định', 164, 30);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (31, 'Mặc định', 89, 31);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (32, 'Mặc định', 44, 32);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (33, 'Mặc định', 169, 33);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (34, 'Mặc định', 119, 34);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (35, 'Mặc định', 82, 35);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (36, 'Mặc định', 199, 36);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (37, 'Mặc định', 105, 37);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (38, 'Mặc định', 168, 38);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (39, 'Mặc định', 46, 39);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (40, 'Mặc định', 11, 40);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (41, 'Mặc định', 1, 41);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (42, 'Mặc định', 60, 42);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (43, 'Xanh', 100, 43);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (44, 'Vàng', 100, 43);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (45, 'Hồng', 100, 43);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (46, 'Xanh - Hồng', 100, 44);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (47, 'Xanh', 100, 45);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (48, 'Xanh', 100, 46);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (49, 'Hồng`', 100, 47);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (50, 'Xanh', 100, 48);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (51, 'Hồng`', 100, 48);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (52, 'Đen', 100, 49);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (53, 'Xanh', 100, 50);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (54, 'Hồng', 100, 50);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (55, 'Đen', 100, 51);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (56, 'Xanh', 100, 52);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (57, 'Xanh', 100, 53);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (58, 'Đỏ', 100, 53);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (59, 'CPC-C029 12 màu', 100, 54);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (60, 'CPC-C030 24 màu', 100, 54);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (61, 'SCR-C001 12 màu', 100, 55);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (62, 'SCR-C002 24 màu', 100, 55);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (63, 'OP-C029 12 màu', 100, 56);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (64, 'OP-C030  18 màu', 100, 56);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (65, 'OP-C031  24 màu', 100, 56);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (66, 'CPC-C034 12 màu', 100, 57);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (67, 'CPC-C035  24 màu', 100, 57);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (68, 'CPC-C035  24 màu', 100, 58);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (69, 'CR-C048 12 màu', 100, 59);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (70, 'CR-C049 18 màu', 100, 59);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (71, 'SWP-C003/AK - 6 màu', 100, 60);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (72, 'SWP-C004/AK - 12 màu', 100, 60);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (73, 'SWM-C010/AK - 12 màu', 100, 61);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (74, 'SWM-C011/AK - 24 màu', 100, 61);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (75, 'PCR-017/AK - Hạt đậu', 100, 62);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (76, 'PCR-017/AK - Hạt đậu', 100, 62);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (77, 'SWP-C001 - 6 màu', 100, 63);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (78, 'SWP-C002 - 12 màu', 100, 63);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (79, 'Combo', 100, 64);
INSERT INTO `product_options` (`id`, `name`, `quantity`, `product_id`) VALUES (80, 'POSCO-02', 100, 65);
COMMIT;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `lock` bit(1) DEFAULT b'0',
  `name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `price` int NOT NULL,
  `category_id` bigint NOT NULL,
  `provider_id` bigint NOT NULL,
  `discount_id` bigint DEFAULT NULL,
  `new` bit(1) DEFAULT NULL,
  `brand` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKtltawi3myjt9pi09219eiou1o` (`provider_id`),
  KEY `FK5cyj7v7fvm60i2ubciqfo2wxm` (`discount_id`),
  CONSTRAINT `FK5cyj7v7fvm60i2ubciqfo2wxm` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKtltawi3myjt9pi09219eiou1o` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of products
-- ----------------------------
BEGIN;
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (1, 'Bút bi FO-03 là sản phẩm do Tập đoàn văn phòng phẩm Thiên Long sản xuất, mang nhãn hiệu FlexOffice. Bút FO-03 thiết kế trẻ trung , nhỏ gọn , dễ sử dụng . Sản phẩm được sản xuất theo công nghệ hiện đại, kiểu dáng phù hợp cho đối tượng học sinh, nhân viên văn phòng, tiểu thương, lao động phổ thông. Dạng bút bi cửa sổ bấm. Nút bấm và lò xo rất nhạy và bền, không bung, không kẹt, không tự rơi ra ngoài của sổ bấm. Tay cầm có hoa văn , giúp cầm bút không trơn , trượt khi viết. Mực không độc hại tiêu chuẩn quốc tế.', b'0', 'Bút bi Thiên Long FO-03', 6000, 1, 1, 1, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (2, 'Ống mực bút máy FPIC-02 được làm từ nhựa trong, mềm, dễ sử dụng, phù hợp cho các loại bút máy chuyên sử dụng ống mực như FTC-03 và một số loại bút chuyên sử dụng ống mực khác có trên thị trường.', b'1', 'Ống mực Điểm 10 FPIC-02', 16000, 1, 2, 2, b'1', 'Sang Hà');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (3, 'Bút có thiết kế tối giản, nhưng tinh tế và ấn tượng. Toàn bộ thân bút làm từ nhựa màu trong, phối hợp hoàn hảo với màu ruột bút bên trong.', b'0', 'Bút bi Điểm 10 TP-06', 5000, 1, 3, 3, b'0', 'An Lộc Việt');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (4, 'Bút chì gỗ GP-018 thích hợp cho các hoạt động như ghi chép, vẽ nháp, học tập. Đặc biệt thích hợp cho các em học sinh tiểu học. ', b'0', 'Bút chì gỗ GP-018', 8000, 1, 4, 4, b'1', 'Văn Phòng phẩm Ba Nhất');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (5, 'Bút gel Điểm 10 TP-GELE01 có thiết kế đơn giản và hiện đại. Thân và nắp bút bằng nhựa trong, sáng bóng sang trọng. Đặc biệt thân bút có những họa tiết nhân vật hoạt hình ngộ nghĩnh và đáng yêu rất thích hợp cho học sinh tiểu học.', b'0', 'Bút Bút Gel Điểm 10 xóa được TP-GELE01', 9000, 1, 5, 5, b'0', 'BIC Việt Nam');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (6, 'Bút gel Smooth Flowing Gel Ink Điểm 10 TP-GEL039 là sản phẩm mới sản xuất, mang nhãn hiệu Điểm 10. Thiết kế tinh tế từng chi tiết, kiểu dáng thanh lịch, trẻ trung nhỏ gọn, dễ cầm nắm, không gây mỏi tay khi sử dụng. Bao bì ấn tượng và sinh động, tăng thêm tính cạnh tranh khi trưng bày. ', b'0', 'Bút gel Smooth Flowing Gel Ink', 5000, 1, 6, 6, b'1', 'Stabilo');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (7, 'Bút lông kim Beebee FL-04 thiết kế bút an toàn đạt theo tiêu chuẩn ISO 11540 (tiêu chuẩn về các yêu cầu an toàn về nắp của các sản phẩm dùng để viết hoặc đánh dấu dành cho trẻ em từ 14 tuổi trở xuống).', b'0', 'Bút lông kim Beebee FL-04', 6000, 1, 7, 7, b'1', 'Deli');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (8, 'Bút sáp dầu - Soft Oil Pastels Colokit dễ dàng di màu, đắp nổi, chồng màu và phối trộn màu.', b'0', 'Bút sáp dầu - Soft Oil Pastels Coloki', 10000, 1, 8, 8, b'0', 'Artline');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (9, 'Bút xóa - FlexOffice FO-CP01 Plus có kiểu dáng thân dẹp, vừa tầm tay, thuận tiện khi sử dụng. Cán bằng nhựa màu xanh dương thể hiện sự trẻ trung, năng động. Đầu bút bằng kim loại có lò xo đàn hồi tốt. ', b'0', 'Bút xóa - FlexOffice FO-CP01 Plus', 20000, 1, 9, 9, b'1', 'Mitsubishi Pencil');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (10, 'BÚT GEL ALONA FO-GEL018/VN được chăm chút trong thiết kế, tinh tế từng chi tiết. Kiểu dáng vô cùng thanh lịch, trẻ trung nhỏ gọn, dễ cầm nắm, không gây mỏi tay khi sử dụng. Bao bì ấn tượng và sinh động, tăng thêm tính cạnh tranh khi trưng bày. ', b'0', 'BÚT GEL ALONA FO-GEL018/VN', 16000, 1, 10, 10, b'0', 'Pilot');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (11, 'Kẹp giấy FO-PAC01 là vật dụng không thể thiếu trong mọi văn phòng để hỗ trợ công việc sắp xếp giấy tờ, tài liệu rời. Kẹp thiết kế dễ sử dụng, bền bỉ và không gỉ sét mang đến hiệu quả lâu dài và an tâm cho người dùng.', b'1', 'Kẹp giấy Flexoffice FO-PAC01', 7000, 2, 1, 1, b'0', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (12, 'Gỡ kim Flexoffice làm bằng chất liệu thép bền bỉ, có độ cứng cao, bọc lớp nhựa bên ngoài giúp thao tác êm tay và chống trơn trợt hiệu quả.', b'1', 'Gỡ kim Flexoffice', 20000, 2, 2, 2, b'0', 'Sang Hà');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (13, 'Bìa còng Flexoffice 50F4 FO-BC03 có khổ F4. Sản phẩm được sản xuất theo công nghệ hiện đại, đạt tiêu chuẩn quốc tế, thuận tiện khi sử dụng.', b'0', 'BútBìa còng Flexoffice 50F4 FO-BC03', 50000, 2, 3, 3, b'1', 'An Lộc Việt');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (14, 'Sản phẩm cặp sách tiểu học cao cấp của nhãn hàng Bebé được sản xuất bởi Tiger family. Với cấu trúc siêu nhẹ có hỗ trợ chống gù cho cột sống sử dụng hình ảnh bản quyền từ Walt Disney/ Marvel.', b'0', 'BútCặp chống gù Nature Quest TP-BP048/FR', 299000, 2, 4, 4, b'1', 'Văn phòng phẩm Ba Nhất');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (15, 'Sản phẩm được làm từ màng BOPP có độ bền dai cao cộng keo tráng được lựa chọn để làm băng keo luôn đảm bảo độ dính cao, khả năng đàn hồi tốt.', b'0', 'Băng keo đục OPP Flexoffice FO-BKD06', 11000, 2, 5, 5, b'0', 'BIC Việt Nam');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (16, 'IK Copy là sự lựa chọn thích hợp cho mọi nhu cầu sử dụng. Thiết kế vượt trội chạy trên máy photocopy tốc độ cao, số lượng nhiều. Công nghệ Fast Copying ứng dụng trong IK Copy đã được kiểm chứng và tin dùng bởi chất lượng vận hành đồng bộ, không kẹt giấy.', b'0', 'Ream giấy A4 70 gsm IK Copy (500 tờ)', 49000, 2, 6, 6, b'1', 'Stabilo');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (17, 'Màu nước rửa được - Washable Poster Color Colokit, hoàn hảo để sử dụng vui chơi tại nhà hoặc đồng hành cùng bé trong giờ mỹ thuật tại lớp. Với công thức không độc hại đạt tiêu chuẩn châu Âu EN 71/3, an toàn cho các bé.', b'1', 'Màu nước rửa được - Washable Poster Color', 39000, 2, 7, 7, b'0', 'Deli');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (18, 'Sáp màu đa năng WSO-C001 an toàn, không độc hại, đáp ứng tiêu chuẩn châu Âu và Mỹ về độ an toàn cho trẻ em.', b'0', 'Bút sáp đa năng 12 màu rửa được Colo Art Thiên Long Colokit - WSO-C001', 29000, 2, 8, 8, b'1', 'Artline');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (19, 'Kích thước A4 giúp việc lưu trữ tài liệu , hồ sơ , chứng từ của giới văn phòng trở nên thuận tiện , dễ dàng hơn', b'0', 'Bìa lỗ không viền A4 Flexoffice FO-CS02', 9000, 2, 9, 9, b'0', 'Mitsubishi Pencil');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (20, 'Sản phẩm được thiết kế thon gọn, có ngấn và răng trên cán dao, tạo cảm giác vừa thoải mái vừa chắc chắn và thuận tiện khi sử dụng.', b'1', 'Dao rọc giấy Flexoffice FO-KN02B', 23000, 2, 10, 10, b'1', 'Pilot');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (21, 'Thông số kỉ thuật', b'0', 'Bút gel Thiên Long GEL-012/AK - Nét viết 0.5mm êm mượt, đầu bút cao cấp, mực đều liên tục, nhân vật Akooland Tahi', 10000, 3, 1, 1, b'0', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (22, 'Thông số kỉ thuật', b'0', 'Bút gel Thiên Long GEL-012/AK - Nét viết 0.5mm êm mượt, đầu bút cao cấp, mực đều liên tục, nhân vật Akooland Tahi', 10000, 3, 1, 1, b'1', 'Thượng Đình');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (23, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787  Mạ Vàng 18K', 5168000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (24, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873', 3808000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (25, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K', 5168000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (26, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585', 2499000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (27, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583', 2499000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (28, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker IM X-BS Metal GT TB4-1975578', 1377000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (29, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker IM PRM X-GREE CT TB4-1975586', 2499000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (30, 'Thông số kỹ thuật', b'0', 'Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589', 2499000, 5, 1, 1, b'0', 'Parker');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (31, 'Thông số kỹ thuật', b'0', 'Bút bi cao cấp Flexoffice FO-069/VN', 328000, 5, 1, 1, b'0', 'Flexoffice');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (32, 'Thông số kỹ thuật', b'0', 'Bút bi cao cấp Thiên Long Bizner BIZ-16/40YEARS - Mạ vàng 22K (Tặng kèm 02 ruột mạ vàng)', 328000, 5, 1, 1, b'0', 'Bizner');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (33, 'Mô tả sản phẩm', b'0', 'Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 44000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (34, 'Mô tả sản phẩm', b'0', 'Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia', 85000, 6, 1, 1, b'0', 'IK Plus');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (35, 'Mô tả sản phẩm', b'0', 'Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 87000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (36, 'Mô tả sản phẩm', b'0', 'Combo 10 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 440000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (37, 'Mô tả sản phẩm', b'0', 'Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia', 89000, 6, 1, 1, b'0', 'IK Plus');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (38, 'Mô tả sản phẩm', b'0', 'Combo 5 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 505000, 6, 1, 1, b'0', 'IK Plus');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (39, 'Mô tả sản phẩm', b'0', 'Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 435000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (40, 'Mô tả sản phẩm', b'0', 'Combo 5 Ream giấy A3 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', 835000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (41, 'Mô tả sản phẩm', b'0', 'Combo giấy in văn phòng IK siêu tiết kiệm', 908000, 6, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (42, 'Mô tả sản phẩm', b'0', 'Combo giấy in văn phòng IK Copy A4 80 gsm siêu tiết kiệm', 1018000, 5, 1, 1, b'0', 'IK Copy');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (43, '<br />Thương hiệu	Thiên Long<br />Loại min chì	2 mm<br />Tiêu chuẩn	TCCS 19:2008/TL-BCB<br />Phù hợp min chì	3H, 2H, H, HB, 2B, 3B...<br />Đóng gói	01 Cây/ Kiện.<br />Xuất xứ	Việt Nam<br />Sản xuất	Trung Quốc<br />Khuyến cáo	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút chì bấm Thiên Long PC-032 - Tích hợp chuốt chì trên thân bút - Màu thân ngẫu nhiên', 11000, 7, 1, 3, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (44, '<br />Thương hiệu	Thiên Long;<br />Kích thước túi: 235 x 90 mm;<br />Độ cứng bút chì: 	2B;<br />Phù hợp min chì: 3H, 2H, H, HB, 2B, 3B...;<br />Xuất xứ: Việt Nam;<br />Sản xuất: Việt Nam;<br />Khuyến cáo: Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bộ 2 bút chì và gôm Thiên Long BTS-022/AK - Nhân vật Akooland thế giới học cụ thần kỳ - Mẫu ngẫu nhiên', 22000, 7, 1, 3, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (45, '<br />Thương hiệu	Thiên Long;<br />Kích thước túi: 260 x 165 x 5.5 mm;<br />Tiêu chuẩn: 	TCCS 0166:2022/TL-BTM.NK;<br />Trọng lượng: 156 gram;<br />Xuất xứ: Việt Nam;<br />Sản xuất: Trung Quốc;<br />Khuyến cáo: Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bảng thông minh đa sắc Thiên Long EWB-001', 290000, 7, 1, 3, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (46, '<br /><br />Thương hiệu:	Flexio;<br />Kích thước:	65 x 63 x 72 mm;<br />Tiêu chuẩn:	TCCS 034:2010/TL-CBC;<br />Trọng lượng	93 gram;<br />Điện áp:	3 V (02 Pin AA);<br />Chất liệu:	ABS Plastic;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Bảo hành:	30 ngày (1 đổi 1);<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Máy chuốt chì tự động Thiên Long Flexio SE-001 - Không bao gồm pin', 132000, 7, 1, 2, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (47, '<br />Thương hiệu:	Flexio;<br />Kích thước:	125 x 29 x 23.5 mm;<br />Tiêu chuẩn:	TCCS 0169:2023/TL-GD;<br />Trọng lượng:	64.5 gram;<br />Điện áp:	3 V (02 Pin AAA);<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Gôm điện tự động Thiên Long Flexio EE-001 - Không bao gồm pin', 69000, 7, 1, 2, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (48, '<br />Thương hiệu	Flexio;<br />Kích thước:	Dài 40 mm; đường kính 19 mm;<br />Tiêu chuẩn:	TCCS 034:2010/TL-CBC;<br />Trọng lượng:	Đang cập nhật;<br />Đóng gói:	02 Cái/ Kiện.;<br />Chất liệu:	Lưỡi dao từ hợp kim thép;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bộ 02 lưỡi chuốt Thiên Long Flexio SER-001 - Dùng cho máy chuốt tự động SE-001', 21000, 7, 1, 2, b'1', 'Flexio');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (49, '<br />Thương hiệu:	Flexio;<br />Kích thước:	83 x 83 x 77 mm;<br />Tiêu chuẩn:	TCCS 0170:2023/TL-MHB;<br />Trọng lượng:	114.5 gram;<br />Điện áp:	3 V (02 Pin AA);<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Bảo hành:	30 ngày (1 đổi 1);<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Máy hút bụi mini Thiên Long Flexio MVE-001 - Không bao gồm pin', 167000, 7, 1, 2, b'1', 'Flexio');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (50, '<br />Danh mục:	Hộp đựng bút;<br />Thương hiệu:	Thiên Long;<br />Kích thước:	200 x 60 x 50mm;<br />Tiêu chuẩn:	TCCS 064:2011/TL-BV;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.	<br />', b'0', 'Bóp viết 2 ngăn Thiên Long PCA-019 - Vải trượt nước - Mẫu ngẫu nhiên', 45000, 7, 1, 2, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (51, '<br />Thương hiệu:	Thiên Long;<br />Dung tích:	40 ml;<br />Trọng lượng:	40 gram;<br />Kích thước:	107.7 x 47.7 mm;<br />Tiêu chuẩn:	TCCS 0176:2023/TL-KDĐN; châu Âu EN 71/3;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.	<br />', b'0', 'Keo sữa - White Glue Thiên Long G-022 - Độ dính cao', 10000, 7, 1, 2, b'1', 'Thiên Long');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (52, '<br /><br />Thương hiệu:	Điểm 10<br />Quy cách:	1 bảng/túi;<br />- Là loại bảng bộ, 1 mặt viết phấn, 1 mặt viết lông bảng, trên bảng có kẹp thêm 01 bút lông bảng có sẵn đồ bôi chuyên dành cho học sinh.;<br />- Bảng sử dụng hình ảnh Doraemon rất dễ thương và thu hút.;<br />- Mặt viết phấn mịn và bám phấn giúp viết rõ nét, chữ viết đẹp.;<br />- Mặt viết lông bảng màu trắng, mặt trơn dễ viết, dễ xóa.;<br />- Kích thước A4, màu sắc ngẫu nhiên.<br />', b'0', 'Bảng bộ Điểm 10 B-015/DO', 61000, 7, 1, 2, b'0', 'Điểm 10');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (53, '<br />Thương hiệu:	Điểm 10;<br />Trọng lượng:	80 gram;<br />Kích thước:	365 x 209 mm;<br />Chất liệu:	Nhựa OPP;<br />Loại:	Không in;<br />Quy cách:	1 cái;<br />Bảo quản:	Nơi khô ráo, tránh lửa;<br />', b'0', 'Combo 10 Bìa bao tập Điểm 10 TP-NBC01', 16000, 7, 1, 2, b'0', 'Điểm 10');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (54, '<br />Thương hiệu	Colokit;<br />Tiêu chuẩn:	TCCS 060:2011/TL-BCM; châu Âu EN 71/3;<br />Kích thước:	120.33 x 206mm (CPC-C029); 215 x 203mm (CPC-C030);<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút chì 12/24 màu Black Wood Colored Pencils - Thiên Long Colokit - Thân gỗ Linden cao cấp Màu sắc tươi sáng', 91000, 8, 1, 2, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (55, '<br />Thương hiệu	Colokit;<br />Trọng lượng:	200gram (SCR-C001), 400gram (SCR-C002);<br />Kích thước:	150 x 67mm (SCR-C001), 150 x 90mm (SCR-C002);<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Trung Quốc;<br />Đóng gói:	01 Hộp/ Kiện.;<br />Bảo quản:	Tránh xa nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút sáp lụa vặn - Silky Crayons Thiên Long Colokit - Mềm khô nhanh không lem', 107000, 8, 1, 2, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (56, '<br /><br />Thương hiệu	Colokit;<br />Trọng lượng:	Đang cập nhật;<br />Chất liệu:	Sáp dầu;<br />Kích thước: hộp	87 x 168 x 18 mm (12 màu); 127 x 168 x 18 (18 màu); 167 x 172 x 18 mm (24 màu);<br />Kích thước: sáp	74 mm;<br />Đường kính: sáp	10 mm;<br />Tiêu chuẩn:	Tiêu chuẩn châu Âu EN 71/3;<br />Bảo quản:	Tránh xa nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút sáp dầu 12/18/24 màu - Hexagonal Oil Pastels Thiên Long Colokit - Tiêu chuẩn châu Âu', 32000, 8, 1, 2, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (57, '<br />Thương hiệu:	Colokit;<br />Tiêu chuẩn:	TCCS 060:2011/TL-BCM, châu Âu EN 71/3;<br />Kích thước: bút	163 x 7.3mm;<br />Kích thước:	182 x 47 x 10mm (CPC-C034); 182 x 94 x 10mm (CPC-C035);<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Bảo quản:	Tránh xa nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút chì 12/24 màu hai đầu - Double Ended Colored Pencils - Thiên Long Colokit', 54000, 8, 1, 2, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (58, '<br />Thương hiệu:	Colokit;<br />Đường kính sáp:	8.25 mm;<br />Chiều dài sáp:	73 mm;<br />Tiêu chuẩn:	TCCS 21:2018/TL-BSD; tiêu chuẩn châu Âu EN 71/3;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Bảo quản:	Tránh xa nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Bút sáp 12/18 màu Thiên Long Colokit - Màu sắc tươi sáng Phủ đều màu Bền màu', 20000, 8, 1, 2, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (59, '<br />Thương hiệu:	Colokit;<br />Định lượng: giấy tô	100 gsm;<br />Độ trắng:	90%;<br />Số trang:	30 trang không kể bìa;<br />Trọng lượng:	200 gram;<br />Kích thước:	200 x 280 mm;<br />Đóng gói:	01 Cuốn/ kiện;<br />Khuyến cáo:	Nhiệt độ: 10 ~ 55º C, Độ ẩm: 55 ~ 95% RH, Tránh xa nguồn nhiệt, dầu mỡ.;<br />', b'0', 'Bộ tập tô màu tương tác 3D Thiên Long Colokit COB-C034/AK - Nhân vật Akooland thế giới học cụ thần kỳ', 52000, 8, 1, 3, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (60, '<br />Thương hiệu	Colokit;<br />Tiêu chuẩn:	TCCS 012:2011/TL-MN; châu Âu EN 71/3;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Kích thước:	97 x 65 x 48.5mm (SWP-C003/AK); 198 x 68 x 54mm (SWP-C004/AK);<br />Trọng lượng:	100gram (SWP-C003/AK); 200gram (SWP-C004/AK); Dung tích 15ml/ lọ;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Hộp 6/12 màu nước rửa được - Washable Poster Color Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ', 53000, 8, 1, 3, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (61, '<br />Thương hiệu:	Colokit;<br />Tiêu chuẩn:	TCCS 005:2011/TL-BLM;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Bảo quản:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.;<br />', b'0', 'Hộp bút lông 12/ 24 màu rửa được - Washable Brush Fiber Pen Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ<br />', 87000, 8, 1, 3, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (62, '<br />Thương hiệu:	Colokit;<br />Tiêu chuẩn:	TCCS 053:2011/TL-BNM, EN 71/3 - tiêu chuẩn an toàn đồ chơi châu Âu;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Bảo quản:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.;<br />', b'0', 'Sáp nhựa 12 màu xóa được - Erasable Crayons Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ<br />', 75000, 8, 1, 3, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (63, '<br />Thương hiệu:	Colokit;<br />Tiêu chuẩn:	TCCS 012:2011/TL-MN; châu Âu EN 71/3;<br />Xuất xứ:	Việt Nam;<br />Sản xuất:	Việt Nam;<br />Kích thước:	97 x 65 x 48.5mm (SWP-C001); 198 x 68 x 54mm (SWP-C002);<br />Trọng lượng:	100gram (SWP-C001); 200gram (SWP-C002), Dung tích 15ml/ lọ;<br />Khuyến cáo:	Tránh nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Màu nước rửa được - Washable Poster Color Thiên Long Colokit - Rửa sạch bằng nước dễ dàng<br />', 126000, 8, 1, 3, b'1', 'Colokit');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (64, '<br />Combo mỹ thuật nhân vật hoạt hình nàng tiên cá Ariel đáng yêu, gồm: Gôm, Tập tô màu, Thước, Bộ sáp 18 màu, Bút chì gỗ.<br />', b'0', 'Combo mỹ thuật nàng tiên cá Ariel (5 món tiện dụng, nhân vật hoạt hình đáng yêu)<br />', 129000, 8, 1, 4, b'1', 'Thiên Long ');
INSERT INTO `products` (`id`, `description`, `lock`, `name`, `price`, `category_id`, `provider_id`, `discount_id`, `new`, `brand`) VALUES (65, '<br />Thương hiệu:	Colokit;<br />Trọng lượng:	300 gram;<br />Chất liệu:	Màu hệ nước.;<br />Kích thước hộp:	202 x 67 x 51 mm;<br />Dung tích lọ màu:	15 ml;<br />Tiêu chuẩn:	Tiêu chuẩn châu Âu;<br />Bảo quản:	Tránh xa nguồn nhiệt, hóa chất. Không thích hợp cho trẻ dưới 3 tuổi.<br />', b'0', 'Hộp 12 màu nước Poster Color Thiên Long Colokit POSCO-02 - Tiêu chuẩn châu Âu<br />', 64000, 8, 1, 2, b'1', 'Thiên Long ');
COMMIT;

-- ----------------------------
-- Table structure for providers
-- ----------------------------
DROP TABLE IF EXISTS `providers`;
CREATE TABLE `providers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `lock` bit(1) DEFAULT b'0',
  `name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of providers
-- ----------------------------
BEGIN;
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (1, '525/46 Tô Hiến Thành, P. 14, Q. 10, HCM', 'info@sangha.vn', b'0', 'Sang Hà', '028 3862 5555');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (2, 'B1-09 Đường Số 04, KDC Tân An Huy, P. Tân Phong, Q. 7, Tp. HCM', 'thienlong@gmail.com', b'0', 'Tập đoàn Thiên Long', '093 7191 311');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (3, '479/45 Phan Văn Trị, Phường 5, Quận Gò Vấp, TPHCM', 'info@anlocviet.vn', b'0', 'An Lộc Việt', '028 7300 160');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (4, '216 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', 'banhat@gmail.com', b'0', 'Văn phòng phẩm Ba Nhất', '028 3930 555');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (5, '267 Điện Biên Phủ, Phường 7, Quận 3, TP.HCM', 'info@bicworld.com', b'0', 'BIC Việt Nam', '028 3930 3333');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (6, '181 Nguyễn Thị Minh Khai, Quận 1, TP.HCM', 'info@stabilo.com', b'0', 'Stabilo', '028 3822 2020');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (7, '260/13 Nguyễn Thái Bình, Phường 12, Quận Tân Bình, TP.HCM', 'info@delivietnam.com', b'0', 'Deli', '028 3811 955');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (8, '311 Trường Chinh, Phường 13, Quận Tân Bình, TP.HCM', 'info@artline.com.vn', b'0', 'Artline', '028 3812 2222');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (9, '144 Võ Thị Sáu, Phường 8, Quận 3, TP.HCM', 'info@mitsubishivietnampencil.com', b'0', 'Mitsubishi Pencil', '028 3930 808');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (10, '391B Hai Bà Trưng, Phường 8, Quận 3, TP.HCM', 'info@pilotpen.vn', b'0', 'Pilot', '028 3930 222');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (11, 'B1-09 Đường Số 04, KDC Tân An Huy, P. Tân Phong, Q. 7, Tp. HCM', 'thienlong@gmail.com', b'0', 'Tập đoàn Thiên Long', '093 7191 311');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (12, '525/46 Tô Hiến Thành, P. 14, Q. 10, HCM', 'info@sangha.vn', b'0', 'Sang Hà', '028 3862 5555');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (13, '479/45 Phan Văn Trị, Phường 5, Quận Gò Vấp, TPHCM', 'info@anlocviet.vn', b'0', 'An Lộc Việt', '028 7300 160');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (14, '216 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', 'banhat@gmail.com', b'0', 'Văn phòng phẩm Ba Nhất', '028 3930 555');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (15, '267 Điện Biên Phủ, Phường 7, Quận 3, TP.HCM', 'info@bicworld.com', b'0', 'BIC Việt Nam', '028 3930 3333');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (16, '181 Nguyễn Thị Minh Khai, Quận 1, TP.HCM', 'info@stabilo.com', b'0', 'Stabilo', '028 3822 2020');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (17, '260/13 Nguyễn Thái Bình, Phường 12, Quận Tân Bình, TP.HCM', 'info@delivietnam.com', b'0', 'Deli', '028 3811 955');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (18, '311 Trường Chinh, Phường 13, Quận Tân Bình, TP.HCM', 'info@artline.com.vn', b'0', 'Artline', '028 3812 2222');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (19, '144 Võ Thị Sáu, Phường 8, Quận 3, TP.HCM', 'info@mitsubishivietnampencil.com', b'0', 'Mitsubishi Pencil', '028 3930 808');
INSERT INTO `providers` (`id`, `address`, `email`, `lock`, `name`, `phone`) VALUES (20, '391B Hai Bà Trưng, Phường 8, Quận 3, TP.HCM', 'info@pilotpen.vn', b'0', 'Pilot', '028 3930 222');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT 'user',
  `verify_id` bigint DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `lock` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_71209g1e1uk6mge68bc73wckc` (`verify_id`),
  CONSTRAINT `FKrsvkk7ogc5xybgvms7lcxqbkq` FOREIGN KEY (`verify_id`) REFERENCES `verify_email_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`id`, `avatar`, `email`, `password`, `role`, `verify_id`, `first_name`, `last_name`, `phone`, `lock`) VALUES (1, NULL, 'kiminonawa1305@gmail.com', '$2a$10$H/41ZokHBPYP7YiEBfUNKea5ytBtXgVmFCY989flOBLi9HQ8j29V.', 'USER', 11, 'Lam', 'Nguyễn', '0855354919', b'0');
INSERT INTO `users` (`id`, `avatar`, `email`, `password`, `role`, `verify_id`, `first_name`, `last_name`, `phone`, `lock`) VALUES (12, NULL, '21130416@st.hcmuaf.edu.vn', '$2a$10$nnmom.oM0EXvj1FLVawkqeCcZfTItSiPTIfdjm/ijwgIpg5teiRWi', 'ADMIN', 13, 'Lam', 'Nguyễn', '0855354919', b'0');
COMMIT;

-- ----------------------------
-- Table structure for verify_email_status
-- ----------------------------
DROP TABLE IF EXISTS `verify_email_status`;
CREATE TABLE `verify_email_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  `expired_at` datetime(6) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bg_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bg_0900_ai_ci;

-- ----------------------------
-- Records of verify_email_status
-- ----------------------------
BEGIN;
INSERT INTO `verify_email_status` (`id`, `code`, `expired_at`, `type`) VALUES (11, NULL, NULL, 'VERIFY_SUCCESS');
INSERT INTO `verify_email_status` (`id`, `code`, `expired_at`, `type`) VALUES (13, NULL, NULL, 'VERIFY_SUCCESS');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
