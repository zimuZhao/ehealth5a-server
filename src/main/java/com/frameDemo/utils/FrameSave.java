package com.frameDemo.utils;

import com.frameDemo.frame.FrameData;
import com.frameDemo.frame.RecvSaveFrameData;
import com.frameDemo.model.*;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.UUID;

public class FrameSave {

    /**
     * 血氧数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setBloodOxygen(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 血氧数据开始解析...");

            DataBloodOxygen dataBloodOxygen = new DataBloodOxygen();
            //公共接收数据初始化 userID,deviceID,measureTime,SYNC,RES,cardType,upload
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //SPO2
            int SPO2 = (int) frameData.getData()[44];
            System.out.print("SPO2=" + SPO2 + "% ");

            //RES1
            byte[] bRES1 = {frameData.getData()[45], frameData.getData()[46]};
            String RES1 = String.valueOf(bRES1);
            System.out.print(" RES1=" + RES1);
            //RES2
            byte[] bRES2 = {frameData.getData()[48], frameData.getData()[53]};
            String RES2 = String.valueOf(bRES2);
            System.out.print(" RES2=" + RES2);

            //PR
            byte[] bPR = {frameData.getData()[46], frameData.getData()[47]};
            //2bytes 16进制转int 10进制 (低位在前，高位在后)
            int PR = ((bPR[0] & 0xFF) | ((bPR[1] & 0xFF) << 8));
            System.out.println(" PR=" + PR + "bpm ");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化血氧数据
            dataBloodOxygen.setBloodOxygenID(UUID.randomUUID().toString());
            dataBloodOxygen.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataBloodOxygen.setSPO2(SPO2);
            dataBloodOxygen.setPR(PR);
            dataBloodOxygen.setRES1(RES1);
            dataBloodOxygen.setRES2(RES2);
            dataBloodOxygen.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataBloodOxygen.setDeleteFlag(false);
            dataBloodOxygen.setCreatTime(new Date());
            dataBloodOxygen.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataBloodOxygenMapper.addBloodOxygen";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataBloodOxygen);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 血氧 数据上传成功");
            } else {
                System.out.println("* 血氧 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 无创血压 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setBloodPressure(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 无创血压数据开始解析...");

            DataBloodPressure dataBloodPressure = new DataBloodPressure();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //SYS
            byte[] bSYS = {frameData.getData()[44], frameData.getData()[45]};
            int SYS = ((bSYS[0] & 0xFF) | ((bSYS[1] & 0xFF) << 8));
            System.out.print(" SYS=" + SYS + "mmHg ");

            //DIA
            byte[] bDIA = {frameData.getData()[46], frameData.getData()[47]};
            int DIA = ((bDIA[0] & 0xFF) | ((bDIA[1] & 0xFF) << 8));
            System.out.print(" DIA=" + DIA + "mmHg ");

            //PR
            byte[] bPR = {frameData.getData()[48], frameData.getData()[49]};
            int PR = ((bPR[0] & 0xFF) | ((bPR[1] & 0xFF) << 8));
            System.out.print(" PR=" + PR + "bpm ");

            //ABI  需要拼接ABI_INT和ABI_DEC
            double ABI = (int) frameData.getData()[50] + ((int) frameData.getData()[51] * 0.01);
            System.out.print(" ABI=" + ABI);

            //ABI_Flag
            int abiFlag = (int) frameData.getData()[52];
            System.out.print(" ABI_FLAG=" + abiFlag);

            //RES
            byte bRES = frameData.getData()[53];
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES);

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化无创血压数据
            dataBloodPressure.setNibpID(UUID.randomUUID().toString());
            dataBloodPressure.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataBloodPressure.setSys(SYS);
            dataBloodPressure.setDia(DIA);
            dataBloodPressure.setABI(ABI);
            dataBloodPressure.setPR(PR);
            dataBloodPressure.setABIFlag(ifBoolean(abiFlag));
            dataBloodPressure.setRES(RES);
            dataBloodPressure.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataBloodPressure.setDeleteFlag(false);
            dataBloodPressure.setCreatTime(new Date());
            dataBloodPressure.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataBloodPressureMapper.addBloodPressure";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataBloodPressure);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 无创血压 数据上传成功");
            } else {
                System.out.println("* 无创血压 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 血糖 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setBloodGlucose(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 血糖 数据开始解析...");

            DataBloodGlucose dataBloodGlucose = new DataBloodGlucose();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //GLU
            byte[] bGLU = {frameData.getData()[44], frameData.getData()[45], frameData.getData()[46], frameData.getData()[47]};
            double GLU = getFloat(bGLU, 0);
            System.out.print(" GLU=" + GLU + "mmol/L ");

            //FLAG 占48,49  49是保留位 这里就不读取了
            int FLAG = (int) frameData.getData()[48];
            System.out.print(" FLAG=" + FLAG);

            //RES
            byte[] bRES = {frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES);

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化血糖数据
            dataBloodGlucose.setBloodGlucoseID(UUID.randomUUID().toString());
            dataBloodGlucose.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataBloodGlucose.setGLU(GLU);
            dataBloodGlucose.setFlag(FLAG);
            dataBloodGlucose.setRES(RES);
            dataBloodGlucose.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataBloodGlucose.setDeleteFlag(false);
            dataBloodGlucose.setCreatTime(new Date());
            dataBloodGlucose.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataBloodGlucoseMapper.addBloodGlucose";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataBloodGlucose);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 血糖 数据上传成功");
            } else {
                System.out.println("* 血糖 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * BMI 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setBodyMassIndex(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* BMI 数据开始解析...");

            DataBodyMassIndex dataBodyMassIndex = new DataBodyMassIndex();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //HEIGHT (byte范围是-128~127 超出范围会进行位拓展 所以加上&0xff)
            int HEIGHT = frameData.getData()[44] & 0xff;
            System.out.println(" HEIGHT=" + HEIGHT);

            //WEIGHT
            int WEIGHT = frameData.getData()[45] & 0xff;
            System.out.print(" WEIGHT=" + WEIGHT);

            //RES
            byte[] bRES = {frameData.getData()[46], frameData.getData()[47], frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES);

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化BMI数据
            dataBodyMassIndex.setBmiID(UUID.randomUUID().toString());
            dataBodyMassIndex.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataBodyMassIndex.setHeight(HEIGHT);
            dataBodyMassIndex.setWeight(WEIGHT);
            dataBodyMassIndex.setRES(RES);
            dataBodyMassIndex.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataBodyMassIndex.setCreatTime(new Date());
            dataBodyMassIndex.setDeleteFlag(false);
            dataBodyMassIndex.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataBodyMassIndexMapper.addBodyMassIndex";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataBodyMassIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* BMI 数据上传成功");
            } else {
                System.out.println("* BMI 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 体温 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setTemperature(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 体温数据开始解析...");

            DataTemperature dataTemperature = new DataTemperature();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //TP  需要拼接TP_INT和TP_DEC
            double TP = (int) frameData.getData()[44] + ((int) frameData.getData()[45] * 0.1);
            System.out.print("TP=" + TP + "℃ ");

            //RES
            byte[] bRES = {frameData.getData()[46], frameData.getData()[47], frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print("RES=" + RES);

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化体温数据
            dataTemperature.setTemperatureID(UUID.randomUUID().toString());
            dataTemperature.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataTemperature.setTemperature(TP);
            dataTemperature.setRES(RES);
            dataTemperature.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataTemperature.setDeleteFlag(false);
            dataTemperature.setCreatTime(new Date());
            dataTemperature.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataTemperatureMapper.addTemperature";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataTemperature);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 体温 数据上传成功");
            } else {
                System.out.println("* 体温 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 腰臀比 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setWaistHipRatio(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 腰臀比数据开始解析...");

            DataWaistHipRatio dataWaistHipRatio = new DataWaistHipRatio();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //WAIST
            byte[] bWAIST = {frameData.getData()[44], frameData.getData()[45]};
            int WAIST = ((bWAIST[0] & 0xFF) | ((bWAIST[1] & 0xFF) << 8));
            System.out.print(" WAIST=" + WAIST + "cm ");

            //HIP
            byte[] bHIP = {frameData.getData()[46], frameData.getData()[47]};
            int HIP = ((bHIP[0] & 0xFF) | ((bHIP[1] & 0xFF) << 8));
            System.out.print(" HIP=" + HIP + "kg ");

            //RES
            byte[] bRES = {frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES + " ");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化腰臀比数据
            dataWaistHipRatio.setWhrID(UUID.randomUUID().toString());
            dataWaistHipRatio.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataWaistHipRatio.setWaist(WAIST);
            dataWaistHipRatio.setHip(HIP);
            dataWaistHipRatio.setRES(RES);
            dataWaistHipRatio.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataWaistHipRatio.setDeleteFlag(false);
            dataWaistHipRatio.setCreatTime(new Date());
            dataWaistHipRatio.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataWaistHipRatioMapper.addWaistHipRatio";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataWaistHipRatio);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 腰臀比 数据上传成功");
            } else {
                System.out.println("* 腰臀比 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 尿酸 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setUricCid(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 尿酸数据开始解析...");

            DataUricCid dataUricCid = new DataUricCid();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //UA
            byte[] bUA = {frameData.getData()[44], frameData.getData()[45], frameData.getData()[46], frameData.getData()[47]};
            Float UA = getFloat(bUA, 0);
            System.out.print(" UA=" + UA + "mmol/L ");

            //RES
            byte[] bRES = {frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES + "");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化尿酸数据
            dataUricCid.setUaID(UUID.randomUUID().toString());
            dataUricCid.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataUricCid.setUA((double) UA);
            dataUricCid.setRES(RES);
            dataUricCid.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataUricCid.setDeleteFlag(false);
            dataUricCid.setCreatTime(new Date());
            dataUricCid.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataUricCidMapper.addUricCid";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataUricCid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 尿酸 数据上传成功");
            } else {
                System.out.println("* 尿酸 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 总胆固醇 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setChol(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 总胆固醇数据开始解析...");

            DataChol dataChol = new DataChol();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //CHOL
            byte[] bCHOL = {frameData.getData()[44], frameData.getData()[45], frameData.getData()[46], frameData.getData()[47]};
            Float CHOL = getFloat(bCHOL, 0);
            System.out.print(" CHOL=" + CHOL + "mmol/L ");

            //RES
            byte[] bRES = {frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES + " ");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化总胆固醇数据
            dataChol.setCholID(UUID.randomUUID().toString());
            dataChol.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataChol.setCHOL((double) CHOL);
            dataChol.setRES(RES);
            dataChol.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataChol.setDeleteFlag(false);
            dataChol.setCreatTime(new Date());
            dataChol.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataCholMapper.addChol";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataChol);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 总胆固醇 数据上传成功");
            } else {
                System.out.println("* 总胆固醇 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 血红蛋白 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回生理参数接收确认值
     */
    public static byte[] setHemoglobin(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 血红蛋白数据开始解析...");

            DataHemoglobin dataHemoglobin = new DataHemoglobin();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //HB
            byte[] bHB = {frameData.getData()[44], frameData.getData()[45], frameData.getData()[46], frameData.getData()[47]};
            Float HB = getFloat(bHB, 0);
            System.out.print(" HB=" + HB + "g/dL ");

            //RES
            byte[] bRES = {frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.print(" RES=" + RES + " ");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化血红蛋白数据
            dataHemoglobin.setHbID(UUID.randomUUID().toString());
            dataHemoglobin.setMeasureTime(recvSaveFrameData.getMeasureTime());
            dataHemoglobin.setHB((double) HB);
            dataHemoglobin.setRES(RES);
            dataHemoglobin.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            dataHemoglobin.setDeleteFlag(false);
            dataHemoglobin.setCreatTime(new Date());
            dataHemoglobin.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.DataHemoglobinMapper.addHemoglobin";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, dataHemoglobin);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 血红蛋白 数据上传成功");
            } else {
                System.out.println("* 血红蛋白 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 用户求助 数据解析
     *
     * @param frameData 帧数据
     * @return SYNC 原样返回用户求助同步计数
     */
    public static byte[] setForHelp(FrameData frameData) {
        if (frameData.getData().length == 68) {
            System.out.println("\n* 用户求助数据开始解析...");

            ForHelp forHelp = new ForHelp();
            RecvSaveFrameData recvSaveFrameData = RecvSaveFrameData.initRecvSaveFrameData(frameData);

            //RES
            byte[] bRES = {frameData.getData()[44], frameData.getData()[45], frameData.getData()[46], frameData.getData()[47], frameData.getData()[48], frameData.getData()[49], frameData.getData()[50], frameData.getData()[51], frameData.getData()[52], frameData.getData()[53]};
            String RES = String.valueOf(bRES);
            System.out.println(" RES=" + RES + " ");

            FrameSave.insertUser(recvSaveFrameData.getUserID(), recvSaveFrameData.getCardType());
            FrameSave.insertDevice(recvSaveFrameData.getDeviceID());
            FrameSave.insertAssociate(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());
            String associateID = FrameSave.selectAssociateID(recvSaveFrameData.getUserID(), recvSaveFrameData.getDeviceID());

            //初始化用户求助数据
            forHelp.setHelpID(UUID.randomUUID().toString());
            forHelp.setHelpTime(recvSaveFrameData.getMeasureTime());
            forHelp.setRES(RES);
            forHelp.setUpload(ifBoolean(recvSaveFrameData.getUpload()));
            forHelp.setDeleteFlag(false);
            forHelp.setAssociateID(associateID);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.ForHelpMapper.addForHelp";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, forHelp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 用户求助 数据上传成功");
            } else {
                System.out.println("* 用户求助 数据上传失败");
            }
            return recvSaveFrameData.getSYNC();
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            byte[] b = {0};
            return b;
        }
    }

    /**
     * 用户授权列表时间戳 数据解析
     *
     * @param frameData 帧数据
     * @return
     */
    public static String setUserAuthorise(FrameData frameData) {
        if (frameData.getData().length == 28) {
            System.out.println("\n* 用户授权列表时间戳 数据开始解析...");

            UserAuthorise userAuthorise = new UserAuthorise();

            /**
             * char[]接收byte[] 并转换为ascii编码
             */
            char[] chData = new char[frameData.getData().length];
            for (int i = 0; i < frameData.getData().length; i++) {
                chData[i] = (char) frameData.getData()[i];
            }
            String sData = String.valueOf(chData);
            //deviceID
            String deviceID = (sData.substring(0, 8)).replace("\u0000", "");
            System.out.print("deviceID=" + deviceID + " ");

            //日期authoriseTime (低位在前，高位在后)
            byte[] year = {frameData.getData()[8], frameData.getData()[9]};
            int Y = ((year[0] & 0xFF) | ((year[1] & 0xFF) << 8));
            int M = frameData.getData()[10];
            int D = frameData.getData()[11];
            int hrs = frameData.getData()[12];
            int min = frameData.getData()[13];
            int sec = frameData.getData()[14];
            Date authoriseTime = new Date((Y - 1900), (M - 1), D, hrs, min, sec);
            System.out.println(" measureTime=" + authoriseTime + " ");

            FrameSave.insertDevice(deviceID);

            //初始化用户授权时间戳数据
            userAuthorise.setAuthoriseID(UUID.randomUUID().toString());
            userAuthorise.setDeviceID(deviceID);
            userAuthorise.setAuthoriseTime(authoriseTime);

            SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
            String statement = "mapper.UserAuthoriseMapper.addUserAuthorise";//映射sql的标识字符串
            int retResult = 0;
            try {
                retResult = sqlSession.insert(statement, userAuthorise);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sqlSession.close();

            if (retResult == 1) {
                System.out.println("* 用户授权时间戳 数据上传成功");
            } else {
                System.out.println("* 用户授权时间戳 数据上传失败");
            }
            return "Success";
        } else {
            System.out.println("ERROR! 解析失败 -->1帧数据长度错误");
            return "ERROR";
        }
    }

    /**
     * 转换cardType
     *
     * @param b
     * @return
     */
    public static Integer getCardType(byte b) {
        //cardID
        switch (b) {
            case (byte) 0x30://社保卡
                return 1;
            case (byte) 0x31://医保卡
                return 2;
            case (byte) 0x32://自费卡
                return 3;
            case (byte) 0x33://其他卡
                return 4;
            case (byte) 0x3A://自制IC卡
                return 5;
            case (byte) 0x3B://身份证卡
                return 6;
            case (byte) 0x3C://NFC卡
                return 7;
            default:
                return 0;
        }
    }

    /**
     * 通过byte数组取得float
     *
     * @param b
     * @param index 从第几位开始取.
     * @return
     */
    public static float getFloat(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public static Boolean ifBoolean(int i) {
        if (i == 0) {
            return false;
        } else if (i == 1) {
            return true;
        }
        return false;
    }

    /**
     * 新设备插入设备表（若数据已存在，则不插入）
     *
     * @param deviceID
     * @return
     */
    public static boolean insertDevice(String deviceID) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        String statement = "mapper.DeviceMapper.insertDevice";

        Device device = new Device();
        device.setDeviceID(deviceID);
        device.setCreatTime(new Date());
        device.setDeleteFlag(false);
        device.setDeviceName("Eleath5A");
        device.setDeviceType("eleath5A");
        device.setProtocolVersion("1");
        int retResult = 0;
        try {
            retResult = sqlSession.insert(statement, device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();

        if (retResult == 1) {
//            System.out.println("* 设备记录 更新成功");
            return true;
        } else {
//            System.out.println("* 设备记录更新失败 - 表中已存在该设备信息");
            return false;
        }
    }

    /**
     * 插入新用户
     *
     * @param userID
     * @param cardType
     * @return
     */
    public static boolean insertUser(String userID, int cardType) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        String statement = "mapper.UserMapper.insertUser";

        User user = new User();
        user.setUserID(userID);
        user.setCardType(cardType);
        user.setCreatTime(new Date());
        user.setDeleteFlag(false);
        int retResult = 0;
        try {
            retResult = sqlSession.insert(statement, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();

        if (retResult == 1) {
//            System.out.println("* 用户记录 更新成功");
            return true;
        } else {
//            System.out.println("* 用户记录更新失败 - 表中已存在该用户信息");
            return false;
        }
    }

    /**
     * 插入用户，设备关联关系
     *
     * @param userID
     * @param deviceID
     * @return
     */
    public static boolean insertAssociate(String userID, String deviceID) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        String statement = "mapper.UserAssociateDevice.insertUserAssociateDevice";

        UserAssociateDevice userAssociateDevice = new UserAssociateDevice();
        userAssociateDevice.setAssociateID(UUID.randomUUID().toString());
        userAssociateDevice.setUserID(userID);
        userAssociateDevice.setDeviceID(deviceID);
        int retResult = 0;
        try {
            retResult = sqlSession.insert(statement, userAssociateDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();

        if (retResult == 1) {
//            System.out.println("* 用户-设备关联关系 更新成功");
            return true;
        } else {
//            System.out.println("* 用户-设备关联关系 更新失败 - 关联关系已存在");
            return false;
        }
    }

    /**
     * 查找关联关系ID
     *
     * @param userID
     * @param deviceID
     * @return
     */
    private static String selectAssociateID(String userID, String deviceID) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        String statement = "mapper.UserAssociateDevice.getAssociateID";

        UserAssociateDevice userAssociateDevice = new UserAssociateDevice();
        userAssociateDevice.setUserID(userID);
        userAssociateDevice.setDeviceID(deviceID);
        String retResult = "";
        try {
            retResult = sqlSession.selectOne(statement, userAssociateDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();

        if (retResult == "") {
            System.out.println("* 关联关系ID 查询失败");
            return "";
        } else {
//            System.out.println("* 关联关系ID 查询成功");
            return retResult;
        }
    }

}
