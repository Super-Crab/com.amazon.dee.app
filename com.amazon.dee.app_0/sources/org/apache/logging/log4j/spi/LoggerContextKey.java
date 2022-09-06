package org.apache.logging.log4j.spi;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.message.MessageFactory;
@Deprecated
/* loaded from: classes4.dex */
public class LoggerContextKey {
    public static String create(String str) {
        return create(str, AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS);
    }

    public static String create(String str, MessageFactory messageFactory) {
        return create(str, (Class<? extends MessageFactory>) (messageFactory != null ? messageFactory.getClass() : AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS));
    }

    public static String create(String str, Class<? extends MessageFactory> cls) {
        if (cls == null) {
            cls = AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS;
        }
        return GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline113(str, "."));
    }
}
