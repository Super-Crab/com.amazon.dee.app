package org.apache.logging.log4j.status;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class StatusData implements Serializable {
    private static final long serialVersionUID = -4341916115118014017L;
    private final StackTraceElement caller;
    private final Level level;
    private final Message msg;
    private String threadName;
    private final Throwable throwable;
    private final long timestamp = System.currentTimeMillis();

    public StatusData(StackTraceElement stackTraceElement, Level level, Message message, Throwable th, String str) {
        this.caller = stackTraceElement;
        this.level = level;
        this.msg = message;
        this.throwable = th;
        this.threadName = str;
    }

    public String getFormattedStatus() {
        Throwable th;
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date(this.timestamp)));
        sb.append(Chars.SPACE);
        sb.append(getThreadName());
        sb.append(Chars.SPACE);
        sb.append(this.level.toString());
        sb.append(Chars.SPACE);
        sb.append(this.msg.getFormattedMessage());
        Object[] parameters = this.msg.getParameters();
        if (this.throwable == null && parameters != null && (parameters[parameters.length - 1] instanceof Throwable)) {
            th = (Throwable) parameters[parameters.length - 1];
        } else {
            th = this.throwable;
        }
        if (th != null) {
            sb.append(Chars.SPACE);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            th.printStackTrace(new PrintStream(byteArrayOutputStream));
            sb.append(byteArrayOutputStream.toString());
        }
        return sb.toString();
    }

    public Level getLevel() {
        return this.level;
    }

    public Message getMessage() {
        return this.msg;
    }

    public StackTraceElement getStackTraceElement() {
        return this.caller;
    }

    public String getThreadName() {
        if (this.threadName == null) {
            this.threadName = Thread.currentThread().getName();
        }
        return this.threadName;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
