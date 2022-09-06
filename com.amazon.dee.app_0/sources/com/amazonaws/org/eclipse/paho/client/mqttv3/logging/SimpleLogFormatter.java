package com.amazonaws.org.eclipse.paho.client.mqttv3.logging;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class SimpleLogFormatter extends Formatter {
    private static final String LS = System.getProperty("line.separator");

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length < 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(c);
        }
    }

    @Override // java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(logRecord.getLevel().getName());
        stringBuffer.append(DeviceDatabaseUtils.DELIMITER);
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(MessageFormat.format("{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} ", new Date(logRecord.getMillis()))));
        stringBuffer2.append(DeviceDatabaseUtils.DELIMITER);
        stringBuffer.append(stringBuffer2.toString());
        String sourceClassName = logRecord.getSourceClassName();
        if (sourceClassName != null) {
            int length = sourceClassName.length();
            if (length > 20) {
                str = logRecord.getSourceClassName().substring(length - 19);
            } else {
                char[] cArr = {Chars.SPACE};
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(sourceClassName);
                stringBuffer3.append(cArr, 0, 1);
                str = stringBuffer3.toString();
            }
        } else {
            str = "";
        }
        stringBuffer.append(str);
        stringBuffer.append(DeviceDatabaseUtils.DELIMITER);
        stringBuffer.append(" ");
        stringBuffer.append(left(logRecord.getSourceMethodName(), 23, Chars.SPACE));
        stringBuffer.append(DeviceDatabaseUtils.DELIMITER);
        stringBuffer.append(logRecord.getThreadID());
        stringBuffer.append(DeviceDatabaseUtils.DELIMITER);
        stringBuffer.append(formatMessage(logRecord));
        stringBuffer.append(LS);
        if (logRecord.getThrown() != null) {
            stringBuffer.append("Throwable occurred: ");
            Throwable thrown = logRecord.getThrown();
            PrintWriter printWriter = null;
            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter2 = new PrintWriter(stringWriter);
                try {
                    thrown.printStackTrace(printWriter2);
                    stringBuffer.append(stringWriter.toString());
                    try {
                        printWriter2.close();
                    } catch (Exception unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    printWriter = printWriter2;
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return stringBuffer.toString();
    }
}
