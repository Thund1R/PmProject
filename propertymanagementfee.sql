/*
 Navicat Premium Data Transfer

 Source Server         : Study_Django
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : propertymanagementfee

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 27/12/2019 15:31:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administors
-- ----------------------------
DROP TABLE IF EXISTS `administors`;
CREATE TABLE `administors`  (
  `AdminID` int(10) NOT NULL,
  `AdminName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `AdminSex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administors
-- ----------------------------
INSERT INTO `administors` VALUES (123, '王石', '1', '123', '1356', '1');

-- ----------------------------
-- Table structure for host
-- ----------------------------
DROP TABLE IF EXISTS `host`;
CREATE TABLE `host`  (
  `HostID` int(10) NOT NULL COMMENT '业主ID',
  `HostName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业主姓名',
  `IDCardID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `HostSex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业主性别',
  `TEL` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `WorkPlace` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作地点',
  `Email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮址',
  PRIMARY KEY (`HostID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of host
-- ----------------------------
INSERT INTO `host` VALUES (1, 'zlp', 'qwf', '男', 'qFWE', 'FQWE', 'fqw');
INSERT INTO `host` VALUES (2, '徐靖鑫', '511322199902029999', '男', '16666666666', '成都信息工程大学', 'xjx@qq.com');
INSERT INTO `host` VALUES (3, '王晟杰', '512312199903039999', '男', '10066668888', '成都信息工程大学科技实践部', 'wsj@qq.com');
INSERT INTO `host` VALUES (4, '王李红', '510682199603053213', '女', '18996963231', 'FPX运营有限公司', 'wlh@qq.com');
INSERT INTO `host` VALUES (5, '宋梓涵', '510982198006015665', '女', '13889234569', '丽缘美容有限公司', 'szh@qq.com');
INSERT INTO `host` VALUES (6, '刘青松', '134562199312119226', '男', '13778426913', '宇鑫物流公司', '13258@wangyi.com');
INSERT INTO `host` VALUES (7, '别倩', '135697197102048904', '女', '13887756775', '金桥饭店', '10098@qq.com');
INSERT INTO `host` VALUES (8, '李驰', '378752197606172458', '男', '19087893441', '光明市天然气有限公司', 'lc@qq.com');
INSERT INTO `host` VALUES (9, '金义芬', '477680196903248907', '女', '17890452309', '光明市市政府', 'kilo@wangyi.com');
INSERT INTO `host` VALUES (10, '邹振林', '511322199607304332', '男', '17489340385', '光明市市政府', 'jim@wangyi.com');
INSERT INTO `host` VALUES (11, '林峰', '511322198905243573', '男', '19793459736', '顺丰物流公司', 'linf@qq.com');
INSERT INTO `host` VALUES (12, '龙星金', '568930198801295384', '男', '16476864439', '外星人小吃店', 'lxj@wangyi.com');
INSERT INTO `host` VALUES (13, '测试', '1', '男', '1', '测试', '测试');
INSERT INTO `host` VALUES (14, '徐靖鑫2号', '5111', '男', '133', '1332', '133');
INSERT INTO `host` VALUES (15, '同一人', '12312', '男', '123123', '123123', '123123');
INSERT INTO `host` VALUES (16, '趣味', '123412', '女', '12432', '12134', '124323');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `HouseID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房屋的ID',
  `HostID` int(10) NOT NULL COMMENT '外键,业主ID',
  `HouseD` int(10) NOT NULL COMMENT '栋数',
  `HouseC` int(10) NOT NULL COMMENT '层数',
  `HouseB` int(10) NOT NULL COMMENT '门牌号',
  `HouseM` int(10) NOT NULL COMMENT '面积',
  `Huxing` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户型',
  `HouseType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋类型(商用,民用)',
  PRIMARY KEY (`HouseID`) USING BTREE,
  INDEX `HouseHolder`(`HostID`) USING BTREE,
  CONSTRAINT `HouseHolder` FOREIGN KEY (`HostID`) REFERENCES `host` (`HostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', 2, 2, 11, 1102, 132, 'A', '1');
INSERT INTO `house` VALUES ('10', 11, 5, 1, 101, 80, 'B', '1');
INSERT INTO `house` VALUES ('11', 7, 1, 10, 1001, 132, 'A', '0');
INSERT INTO `house` VALUES ('12', 9, 4, 4, 404, 121, 'C', '1');
INSERT INTO `house` VALUES ('13', 12, 6, 11, 1104, 132, 'A', '1');
INSERT INTO `house` VALUES ('14', 12, 4, 9, 903, 70, 'D', '0');
INSERT INTO `house` VALUES ('2', 3, 3, 6, 604, 80, 'B', '1');
INSERT INTO `house` VALUES ('3', 4, 6, 9, 901, 132, 'A', '1');
INSERT INTO `house` VALUES ('4', 5, 1, 2, 203, 121, 'C', '1');
INSERT INTO `house` VALUES ('5', 6, 3, 11, 1103, 70, 'D', '1');
INSERT INTO `house` VALUES ('6', 4, 5, 7, 701, 121, 'C', '1');
INSERT INTO `house` VALUES ('7', 10, 6, 3, 1303, 132, 'A', '1');
INSERT INTO `house` VALUES ('8', 1, 2, 8, 804, 80, 'B', '1');
INSERT INTO `house` VALUES ('9', 8, 4, 5, 502, 70, 'D', '1');

-- ----------------------------
-- Table structure for housearrear
-- ----------------------------
DROP TABLE IF EXISTS `housearrear`;
CREATE TABLE `housearrear`  (
  `HouseArrearID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '房屋的欠费订单编号',
  `HouseID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房屋ID',
  `WaterFee` float(10, 2) NOT NULL COMMENT '水费',
  `ElectricityFee` float(10, 2) NOT NULL COMMENT '电费',
  `GasFee` float(10, 2) NOT NULL COMMENT '燃气费',
  `ManageFee` float(10, 2) NOT NULL COMMENT '物业费',
  `GreenFee` float(10, 2) NOT NULL COMMENT '绿化费',
  `TotalFee` float(10, 2) NOT NULL,
  `isPay` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`HouseArrearID`) USING BTREE,
  INDEX `HouseArrear`(`HouseID`) USING BTREE,
  CONSTRAINT `HouseArrear` FOREIGN KEY (`HouseID`) REFERENCES `house` (`HouseID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housearrear
-- ----------------------------
INSERT INTO `housearrear` VALUES (1, '1', 2.16, 1.24, 6.99, 264.00, 1.00, 274.39, '0');
INSERT INTO `housearrear` VALUES (2, '2', 2.16, 1.24, 6.99, 160.00, 1.00, 170.39, '0');
INSERT INTO `housearrear` VALUES (3, '3', 8.64, 3.10, 13.98, 264.00, 1.00, 289.72, '0');
INSERT INTO `housearrear` VALUES (4, '4', 15.12, 4.34, 11.65, 242.00, 1.00, 273.11, '0');
INSERT INTO `housearrear` VALUES (5, '5', 4.32, 1.86, 6.99, 140.00, 1.00, 153.17, '0');
INSERT INTO `housearrear` VALUES (6, '6', 2.16, 1.24, 6.99, 242.00, 1.00, 252.39, '0');
INSERT INTO `housearrear` VALUES (7, '7', 4.32, 1.86, 9.32, 264.00, 1.00, 279.50, '0');
INSERT INTO `housearrear` VALUES (8, '8', 10.80, 0.62, 6.99, 160.00, 1.00, 178.41, '0');
INSERT INTO `housearrear` VALUES (9, '9', 15.12, 0.62, 6.99, 140.00, 1.00, 162.73, '0');
INSERT INTO `housearrear` VALUES (10, '10', 2.16, 1.24, 9.32, 160.00, 1.00, 172.72, '0');
INSERT INTO `housearrear` VALUES (11, '11', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '0');
INSERT INTO `housearrear` VALUES (12, '12', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '0');
INSERT INTO `housearrear` VALUES (13, '13', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '0');
INSERT INTO `housearrear` VALUES (14, '14', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '0');

-- ----------------------------
-- Table structure for other
-- ----------------------------
DROP TABLE IF EXISTS `other`;
CREATE TABLE `other`  (
  `OtherResidentID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `OtherResidentName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `OtherResidentSex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `OtherResidentTEL` int(20) NOT NULL COMMENT '电话',
  `HostID` int(10) NULL DEFAULT NULL COMMENT '隶属业主ID',
  `Relation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与业主关系',
  PRIMARY KEY (`OtherResidentID`) USING BTREE,
  INDEX `RelationHolder`(`HostID`) USING BTREE,
  CONSTRAINT `RelationHolder` FOREIGN KEY (`HostID`) REFERENCES `host` (`HostID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking`  (
  `ParkingID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位ID',
  `ParkingArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位分区区域',
  `ParkingB` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位区域编号',
  `ParkingM` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位面积',
  `HostID` int(10) NOT NULL COMMENT '业主ID',
  PRIMARY KEY (`ParkingID`) USING BTREE,
  INDEX `ParkingHolder`(`HostID`) USING BTREE,
  CONSTRAINT `ParkingHolder` FOREIGN KEY (`HostID`) REFERENCES `host` (`HostID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking
-- ----------------------------
INSERT INTO `parking` VALUES ('1', '1', '23', '35', 3);
INSERT INTO `parking` VALUES ('10', '4', '167', '35', 4);
INSERT INTO `parking` VALUES ('11', '1', '28', '35', 8);
INSERT INTO `parking` VALUES ('12', '3', '145', '40', 2);
INSERT INTO `parking` VALUES ('13', '1', '14', '35', 12);
INSERT INTO `parking` VALUES ('14', '2', '65', '35', 7);
INSERT INTO `parking` VALUES ('2', '2', '70', '35', 5);
INSERT INTO `parking` VALUES ('3', '1', '35', '35', 4);
INSERT INTO `parking` VALUES ('4', '1      ', '32', '35', 6);
INSERT INTO `parking` VALUES ('5', '3', '101', '40', 1);
INSERT INTO `parking` VALUES ('6', '4', '154', '35', 11);
INSERT INTO `parking` VALUES ('7', '2', '87', '35', 10);
INSERT INTO `parking` VALUES ('8', '3', '123', '40', 4);
INSERT INTO `parking` VALUES ('9', '2', '99', '35', 9);

-- ----------------------------
-- Table structure for parkingarrear
-- ----------------------------
DROP TABLE IF EXISTS `parkingarrear`;
CREATE TABLE `parkingarrear`  (
  `ParkingArrearID` bigint(20) NOT NULL COMMENT '车位欠费订单编号',
  `ParkingID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位编号',
  `ParkingFee` float(10, 2) NOT NULL COMMENT '车位欠费',
  `Time` date NOT NULL COMMENT '欠款时间',
  `Telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SinglePrice` float(20, 2) NOT NULL,
  `isPayed` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ParkingArrearID`) USING BTREE,
  INDEX `ParkingInfo`(`ParkingID`) USING BTREE,
  CONSTRAINT `ParkingInfo` FOREIGN KEY (`ParkingID`) REFERENCES `parking` (`ParkingID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parkingarrear
-- ----------------------------
INSERT INTO `parkingarrear` VALUES (1, '1', 0.00, '2019-12-17', '13055008585', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (2, '2', 0.00, '2019-12-17', '15881651354', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (3, '3', 0.00, '2019-12-17', '15154843211', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (4, '4', 0.00, '2019-12-17', '13003939996', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (5, '5', 2.00, '2019-12-17', '13003939995', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (6, '6', 0.00, '2019-12-17', '13003939146', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (7, '7', 0.00, '2019-12-17', '13003939019', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (8, '8', 0.00, '2019-12-17', '13003939073', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (9, '9', 0.00, '2019-12-17', '13003939064', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (10, '10', 0.00, '2019-12-17', '13003939028', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (11, '11', 0.00, '2019-12-17', '13003939037', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (12, '12', 0.00, '2019-12-17', '13003939055', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (13, '13', 0.00, '2019-12-17', '13003939046', 2.00, '0');
INSERT INTO `parkingarrear` VALUES (14, '14', 0.00, '2019-12-17', '13003939055', 2.00, '0');

-- ----------------------------
-- Table structure for propertyproject
-- ----------------------------
DROP TABLE IF EXISTS `propertyproject`;
CREATE TABLE `propertyproject`  (
  `ProjectID` bigint(20) NOT NULL COMMENT '物业项目ID',
  `ProjectName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物业名称',
  `Supplier` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '供应商名称',
  `Manager` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责人姓名',
  `Telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责人联系电话',
  `SinglePrice` float(20, 2) NOT NULL COMMENT '项目单价',
  PRIMARY KEY (`ProjectID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of propertyproject
-- ----------------------------
INSERT INTO `propertyproject` VALUES (1, 'Water', '成都第一水公司', '刘浩', '13569498827', 3.26);
INSERT INTO `propertyproject` VALUES (2, 'Electric', '成都第三电力公司', '蒋讯', '17787294167', 0.62);
INSERT INTO `propertyproject` VALUES (3, 'Gas', '成都天然气公司', '王猛', '18746592301', 2.33);
INSERT INTO `propertyproject` VALUES (4, 'Manage', '四川博鳌物业发展有限公司', '张三', '13543855461', 1.00);
INSERT INTO `propertyproject` VALUES (5, 'Green', '成都上成园林绿化有限公司', '李四', '13685462543', 1.00);

-- ----------------------------
-- Table structure for usaged
-- ----------------------------
DROP TABLE IF EXISTS `usaged`;
CREATE TABLE `usaged`  (
  `UsageID` bigint(20) NOT NULL,
  `HouseID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Water` float(20, 0) NOT NULL,
  `Electric` float(20, 0) NOT NULL,
  `Gas` float(20, 0) NOT NULL,
  `Time` date NOT NULL,
  `iscalled` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UsageID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usaged
-- ----------------------------
INSERT INTO `usaged` VALUES (1, '1', 1, 2, 3, '2019-12-26', '0');
INSERT INTO `usaged` VALUES (2, '2', 1, 2, 3, '2019-12-27', '0');
INSERT INTO `usaged` VALUES (3, '3', 4, 5, 6, '2019-12-27', '0');
INSERT INTO `usaged` VALUES (4, '4', 7, 7, 5, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (5, '5', 2, 3, 3, '2019-12-27', '0');
INSERT INTO `usaged` VALUES (6, '6', 1, 2, 3, '2019-12-27', '0');
INSERT INTO `usaged` VALUES (7, '7', 2, 3, 4, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (8, '8', 5, 1, 3, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (9, '9', 7, 1, 3, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (10, '10', 1, 2, 4, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (11, '11', 0, 0, 0, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (12, '12', 0, 0, 0, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (13, '13', 0, 0, 0, '2019-12-25', '0');
INSERT INTO `usaged` VALUES (14, '14', 0, 0, 0, '2019-12-25', '0');

-- ----------------------------
-- Procedure structure for DSelect
-- ----------------------------
DROP PROCEDURE IF EXISTS `DSelect`;
delimiter ;;
CREATE PROCEDURE `DSelect`(IN HouseD1 INT(10))
BEGIN	
		DECLARE HID varchar(20);/*声明接游标数据的值*/
		DECLARE HID1 varchar(20);
		DECLARE HID2 varchar(20);
		DECLARE HID3 varchar(20);
		DECLARE HID4 varchar(20);
		declare done int default false;
		DECLARE cur CURSOR for SELECT HouseID from house WHERE HouseD = HouseD1;
		declare continue HANDLER for not found set done = true;
		open cur;
		/*read_loop:LOOP*/
		fetch cur into HID;
		fetch cur into HID1;	
		fetch cur into HID2;
		fetch cur into HID3;
		fetch cur into HID4;
		/*if done then
				leave read_loop;
		end if;*/
		/*while(not done) DO
				
				FETCH cur into HID1;
		end while;*/
		SELECT * from housearrear where HouseID=HID UNION SELECT * from housearrear where HouseID=HID1 UNION SELECT * from housearrear where HouseID=HID2 UNION SELECT * from housearrear where HouseID=HID3 UNION SELECT * from housearrear where HouseID=HID4;
		/*end LOOP;*/
		CLOSE cur;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for feepay
-- ----------------------------
DROP PROCEDURE IF EXISTS `feepay`;
delimiter ;;
CREATE PROCEDURE `feepay`(IN HouseID varchar(20),IN TotalFee1 FLOAT(10))
BEGIN
		/*SET @Water = (SELECT Water from usaged where usaged.HouseID=HouseID);
		SET @Electric = (SELECT Electric from usaged where usaged.HouseID=HouseID);
		SET @Gas = (SELECT Gas from usaged where usaged.HouseID=HouseID);
			*/
		SET @chatotal = (SELECT TotalFee from housearrear WHERE housearrear.HouseID=HouseID) - TotalFee1;
		/*SET @chaE = (SELECT ElectricityFee from housearrear WHERE housearrear.HouseID=HouseID) - ElectrictyFee1;
		SET @chaGas = (SELECT GasFee from housearrear WHERE housearrear.HouseID=HouseID) - GasFee1;
		SET @chaM = (SELECT ManageFee from housearrear WHERE housearrear.HouseID=HouseID) - ManageFee1;
		SET @chaGre = (SELECT GreenFee from housearrear WHERE housearrear.HouseID=HouseID) - GreenFee1;*/
		update housearrear set TotalFee = @chatotal WHERE housearrear.HouseID=HouseID;
			IF(@chatotal<=0) THEN
		UPDATE housearrear SET isPay=1 WHERE housearrear.HouseID=HouseID;
		UPDATE housearrear SET WaterFee=0,ElectricityFee=0,GasFee=0,ManageFee=0,GreenFee=0 WHERE housearrear.HouseID=HouseID;
		END IF;
	END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for feeupdate
-- ----------------------------
DROP PROCEDURE IF EXISTS `feeupdate`;
delimiter ;;
CREATE PROCEDURE `feeupdate`(IN HouseID varchar(20))
BEGIN
		SET @Water = (SELECT Water from usaged where usaged.HouseID=HouseID);
		SET @Electric = (SELECT Electric from usaged where usaged.HouseID=HouseID);
		SET @Gas = (SELECT Gas from usaged where usaged.HouseID=HouseID);
		SET @iscalled = (SELECT iscalled from usaged where usaged.HouseID=HouseID);
		SET @WaterFee = (SELECT WaterFee from housearrear where housearrear.HouseID=HouseID);
		SET @ElectricityFee = (SELECT ElectricityFee from housearrear where housearrear.HouseID=HouseID);
		SET @GasFee = (SELECT GasFee from housearrear where housearrear.HouseID=HouseID);
		SET @ManageFee = (SELECT ManageFee from housearrear where housearrear.HouseID=HouseID);
		SET @GreenFee = (SELECT GreenFee from housearrear where housearrear.HouseID=HouseID);
		SET @isPay = (SELECT isPay from housearrear where housearrear.HouseID=HouseID);
		SET @houseM = (SELECT houseM from house WHERE house.HouseID=HouseID);
		SET @totalFee = (SELECT TotalFee from housearrear where housearrear.HouseID=HouseID);

		IF(@isPay=1&&@iscalled=1) THEN
		update housearrear set WaterFee = @Water*2.16,ElectricityFee = @Electric*0.62,GasFee = @Gas*2.33,ManageFee = 2*@houseM,GreenFee = 1,TotalFee=@totalFee+WaterFee+ElectricityFee+GasFee+ManageFee+GreenFee,isPay=0 WHERE housearrear.HouseID=HouseID;
		UPDATE usaged SET iscalled=0 WHERE usaged.HouseID=HouseID;
		ELSEIF(@isPay=0&&@iscalled=1) THEN
		update housearrear set WaterFee = @Water*2.16+@WaterFee,ElectricityFee = @Electric*0.62+@ElectricityFee,GasFee = @Gas*2.33+@GasFee,ManageFee = 2*@houseM+@ManageFee,GreenFee = 1+@GreenFee,TotalFee=@totalFee+WaterFee+ElectricityFee+GasFee+ManageFee,isPay=0 WHERE housearrear.HouseID=HouseID;
		UPDATE usaged SET iscalled=0 WHERE usaged.HouseID=HouseID;
		END IF;
		END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for HSelect
-- ----------------------------
DROP PROCEDURE IF EXISTS `HSelect`;
delimiter ;;
CREATE PROCEDURE `HSelect`(IN HostID1 INT(10))
BEGIN	
		DECLARE HID varchar(20);/*声明接游标数据的值*/
		DECLARE HID1 varchar(20);
		DECLARE HID2 varchar(20);
		DECLARE HID3 varchar(20);
		DECLARE HID4 varchar(20);
		declare done int default false;
		DECLARE cur CURSOR for SELECT HouseID from house WHERE HostID = HostID1;
		declare continue HANDLER for not found set done = true;
		open cur;
		/*read_loop:LOOP*/
		fetch cur into HID;
		fetch cur into HID1;	
		fetch cur into HID2;
		fetch cur into HID3;
		fetch cur into HID4;
		/*if done then
				leave read_loop;
		end if;*/
		/*while(not done) DO
				
				FETCH cur into HID1;
		end while;*/
		SELECT * from housearrear where HouseID=HID UNION SELECT * from housearrear where HouseID=HID1 UNION SELECT * from housearrear where HouseID=HID2 UNION SELECT * from housearrear where HouseID=HID3 UNION SELECT * from housearrear where HouseID=HID4;
		/*end LOOP;*/
		CLOSE cur;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for parkfeeupdate
-- ----------------------------
DROP PROCEDURE IF EXISTS `parkfeeupdate`;
delimiter ;;
CREATE PROCEDURE `parkfeeupdate`(IN HostID INT(10))
BEGIN
		SET @ParkingID = (SELECT ParkingID from parking where parking.HostID=HostID);
		update parkingarrear set ParkingFee = 2 WHERE parkingarrear.ParkingID=@ParkingID;
	END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usagedupdate
-- ----------------------------
DROP PROCEDURE IF EXISTS `usagedupdate`;
delimiter ;;
CREATE PROCEDURE `usagedupdate`(IN HouseID varchar(20),IN Water float(20),IN Electric float(20),IN Gas float(20))
BEGIN
		/*SET @ParkingID = (SELECT ParkingID from usaged where usaged.HouseID=HouseID);
		SET @ParkingID = (SELECT ParkingID from parking where parking.HostID=HostID);
		SET @ParkingID = (SELECT ParkingID from parking where parking.HostID=HostID);
		SET @ParkingID = (SELECT ParkingID from parking where parking.HostID=HostID);*/
		update usaged set Water = Water,Electric = Electric,Gas = Gas,iscalled=1,Time=NOW() WHERE usaged.HouseID=HouseID;
	END
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee1
-- ----------------------------
DROP EVENT IF EXISTS `updatefee1`;
delimiter ;;
CREATE EVENT `updatefee1`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(1)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee10
-- ----------------------------
DROP EVENT IF EXISTS `updatefee10`;
delimiter ;;
CREATE EVENT `updatefee10`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(10)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee11
-- ----------------------------
DROP EVENT IF EXISTS `updatefee11`;
delimiter ;;
CREATE EVENT `updatefee11`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(11)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee12
-- ----------------------------
DROP EVENT IF EXISTS `updatefee12`;
delimiter ;;
CREATE EVENT `updatefee12`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(12)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee13
-- ----------------------------
DROP EVENT IF EXISTS `updatefee13`;
delimiter ;;
CREATE EVENT `updatefee13`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(13)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee14
-- ----------------------------
DROP EVENT IF EXISTS `updatefee14`;
delimiter ;;
CREATE EVENT `updatefee14`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(14)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee2
-- ----------------------------
DROP EVENT IF EXISTS `updatefee2`;
delimiter ;;
CREATE EVENT `updatefee2`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(2)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee3
-- ----------------------------
DROP EVENT IF EXISTS `updatefee3`;
delimiter ;;
CREATE EVENT `updatefee3`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(3)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee4
-- ----------------------------
DROP EVENT IF EXISTS `updatefee4`;
delimiter ;;
CREATE EVENT `updatefee4`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(4)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee5
-- ----------------------------
DROP EVENT IF EXISTS `updatefee5`;
delimiter ;;
CREATE EVENT `updatefee5`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(5)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee6
-- ----------------------------
DROP EVENT IF EXISTS `updatefee6`;
delimiter ;;
CREATE EVENT `updatefee6`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(6)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee7
-- ----------------------------
DROP EVENT IF EXISTS `updatefee7`;
delimiter ;;
CREATE EVENT `updatefee7`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(7)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee8
-- ----------------------------
DROP EVENT IF EXISTS `updatefee8`;
delimiter ;;
CREATE EVENT `updatefee8`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(8)
;;
delimiter ;

-- ----------------------------
-- Event structure for updatefee9
-- ----------------------------
DROP EVENT IF EXISTS `updatefee9`;
delimiter ;;
CREATE EVENT `updatefee9`
ON SCHEDULE
EVERY '10' SECOND STARTS '2019-12-22 13:12:00'
ON COMPLETION PRESERVE
DISABLE
DO call feeupdate(9)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
