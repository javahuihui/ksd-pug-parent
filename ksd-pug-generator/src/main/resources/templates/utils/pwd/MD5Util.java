package ${rootPackage}.utils.pwd;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
public class MD5Util {
    public MD5Util() {
    }

    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            return bytesToHex(md5.digest());
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String md5slat(String str){
        return MD5Util.md5(MD5Util.md5("jiangfujia"+str+"223920230823!!!"));
    }

    public static String bytesToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);

        String hashtext;
        for(hashtext = bigInt.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {
        }

        return hashtext;
    }
}