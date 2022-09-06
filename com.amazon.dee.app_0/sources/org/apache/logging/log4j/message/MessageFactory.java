package org.apache.logging.log4j.message;
/* loaded from: classes4.dex */
public interface MessageFactory {
    Message newMessage(Object obj);

    Message newMessage(String str);

    Message newMessage(String str, Object... objArr);
}
