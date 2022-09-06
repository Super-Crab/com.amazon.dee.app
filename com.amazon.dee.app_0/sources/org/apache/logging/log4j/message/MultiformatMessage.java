package org.apache.logging.log4j.message;
/* loaded from: classes4.dex */
public interface MultiformatMessage extends Message {
    String[] getFormats();

    String getFormattedMessage(String[] strArr);
}
