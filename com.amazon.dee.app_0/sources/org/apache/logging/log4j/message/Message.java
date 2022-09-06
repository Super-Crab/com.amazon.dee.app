package org.apache.logging.log4j.message;

import java.io.Serializable;
/* loaded from: classes4.dex */
public interface Message extends Serializable {
    String getFormat();

    String getFormattedMessage();

    Object[] getParameters();

    Throwable getThrowable();
}
