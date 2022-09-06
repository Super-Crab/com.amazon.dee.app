package org.apache.logging.log4j.spi;

import java.net.URI;
/* loaded from: classes4.dex */
public interface LoggerContextFactory {
    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z);

    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI uri, String str2);

    default boolean hasContext(String str, ClassLoader classLoader, boolean z) {
        return false;
    }

    void removeContext(LoggerContext loggerContext);

    default void shutdown(String str, ClassLoader classLoader, boolean z, boolean z2) {
        if (hasContext(str, classLoader, z)) {
            LoggerContext context = getContext(str, classLoader, null, z);
            if (!(context instanceof Terminable)) {
                return;
            }
            ((Terminable) context).terminate();
        }
    }
}
