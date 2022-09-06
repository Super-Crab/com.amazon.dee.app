package org.apache.logging.log4j.message;
/* loaded from: classes4.dex */
public interface ThreadInformation {
    void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr);

    void printThreadInfo(StringBuilder sb);
}
