package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LoggingException;
/* loaded from: classes4.dex */
public final class Base64Util {
    private static Method encodeMethod;
    private static Object encoder;

    static {
        try {
            try {
                Class<?> loadClass = LoaderUtil.loadClass("java.util.Base64");
                Class<?> loadClass2 = LoaderUtil.loadClass("java.util.Base64$Encoder");
                encoder = loadClass.getMethod("getEncoder", new Class[0]).invoke(null, new Object[0]);
                encodeMethod = loadClass2.getMethod("encodeToString", byte[].class);
            } catch (Exception e) {
                LowLevelLogUtil.logException("Unable to create a Base64 Encoder", e);
            }
        } catch (Exception unused) {
            encodeMethod = LoaderUtil.loadClass("javax.xml.bind.DataTypeConverter").getMethod("printBase64Binary", new Class[0]);
        }
    }

    private Base64Util() {
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        Method method = encodeMethod;
        if (method != null) {
            try {
                return (String) method.invoke(encoder, bytes);
            } catch (Exception e) {
                throw new LoggingException("Unable to encode String", e);
            }
        }
        throw new LoggingException("No Encoder, unable to encode string");
    }
}
