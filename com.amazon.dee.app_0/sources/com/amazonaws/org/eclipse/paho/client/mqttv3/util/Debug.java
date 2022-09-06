package com.amazonaws.org.eclipse.paho.client.mqttv3.util;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ClientComms;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class Debug {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0 = null;
    private static final String lineSep;
    private static final Logger log;
    private static final String separator = "==============";
    private String clientID;
    private ClientComms comms;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ClientComms");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        lineSep = System.getProperty("line.separator", "\n");
    }

    public Debug(String str, ClientComms clientComms) {
        this.clientID = str;
        this.comms = clientComms;
        log.setResourceName(str);
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> propertyNames = properties.propertyNames();
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(lineSep));
        GeneratedOutlineSupport1.outline174(stringBuffer2, separator, " ", str, " ");
        stringBuffer2.append(separator);
        stringBuffer2.append(lineSep);
        stringBuffer.append(stringBuffer2.toString());
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            StringBuffer stringBuffer3 = new StringBuffer(String.valueOf(left(str2, 28, Chars.SPACE)));
            stringBuffer3.append(":  ");
            stringBuffer3.append(properties.get(str2));
            stringBuffer3.append(lineSep);
            stringBuffer.append(stringBuffer3.toString());
        }
        StringBuffer stringBuffer4 = new StringBuffer("==========================================");
        stringBuffer4.append(lineSep);
        stringBuffer.append(stringBuffer4.toString());
        return stringBuffer.toString();
    }

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

    public void dumpBaseDebug() {
        dumpVersion();
        dumpSystemProperties();
        dumpMemoryTrace();
    }

    public void dumpClientComms() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            Properties debug = clientComms.getDebug();
            Logger logger = log;
            String str = CLASS_NAME;
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
            stringBuffer.append(" : ClientComms");
            logger.fine(str, "dumpClientComms", dumpProperties(debug, stringBuffer.toString()).toString());
        }
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpClientState() {
        ClientComms clientComms = this.comms;
        if (clientComms == null || clientComms.getClientState() == null) {
            return;
        }
        Properties debug = this.comms.getClientState().getDebug();
        Logger logger = log;
        String str = CLASS_NAME;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
        stringBuffer.append(" : ClientState");
        logger.fine(str, "dumpClientState", dumpProperties(debug, stringBuffer.toString()).toString());
    }

    public void dumpConOptions() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            Properties debug = clientComms.getConOptions().getDebug();
            Logger logger = log;
            String str = CLASS_NAME;
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
            stringBuffer.append(" : Connect Options");
            logger.fine(str, "dumpConOptions", dumpProperties(debug, stringBuffer.toString()).toString());
        }
    }

    protected void dumpMemoryTrace() {
        log.dumpTrace();
    }

    public void dumpSystemProperties() {
        log.fine(CLASS_NAME, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    protected void dumpVersion() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(lineSep));
        stringBuffer2.append(separator);
        stringBuffer2.append(" Version Info ");
        stringBuffer2.append(separator);
        stringBuffer2.append(lineSep);
        stringBuffer.append(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer(String.valueOf(left("Version", 20, Chars.SPACE)));
        stringBuffer3.append(":  ");
        stringBuffer3.append(ClientComms.VERSION);
        stringBuffer3.append(lineSep);
        stringBuffer.append(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer(String.valueOf(left("Build Level", 20, Chars.SPACE)));
        stringBuffer4.append(":  ");
        stringBuffer4.append(ClientComms.BUILD_LEVEL);
        stringBuffer4.append(lineSep);
        stringBuffer.append(stringBuffer4.toString());
        StringBuffer stringBuffer5 = new StringBuffer("==========================================");
        stringBuffer5.append(lineSep);
        stringBuffer.append(stringBuffer5.toString());
        log.fine(CLASS_NAME, "dumpVersion", stringBuffer.toString());
    }
}
