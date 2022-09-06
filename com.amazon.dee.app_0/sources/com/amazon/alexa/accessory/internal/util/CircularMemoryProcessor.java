package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.internal.util.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.Locale;
/* loaded from: classes.dex */
public final class CircularMemoryProcessor implements Logger.Processor {
    public static final int DEFAULT_LOG_ENTRIES_RETENTION_COUNT = 50000;
    private final Object lock;
    private final LinkedList<String> logs;
    private final int maxLines;
    private final PrintWriter printWriter;
    private final StringWriter stringWriter;

    public CircularMemoryProcessor() {
        this(50000);
    }

    private String extractStackTrace(Throwable th) {
        Preconditions.notNull(th, "throwable");
        th.printStackTrace(this.printWriter);
        String stringWriter = this.stringWriter.toString();
        this.stringWriter.getBuffer().setLength(0);
        return stringWriter;
    }

    private static String formatLog(String str) {
        return String.format(Locale.US, "%dms: %s", Long.valueOf(System.currentTimeMillis()), str);
    }

    private void logThrowable(Throwable th) {
        if (th == null) {
            return;
        }
        this.logs.addLast(formatLog(extractStackTrace(th)));
    }

    private void trimLogs(int i) {
        int size = (this.logs.size() + i) - this.maxLines;
        for (int i2 = 0; i2 < size; i2++) {
            this.logs.removeFirst();
        }
    }

    @Override // com.amazon.alexa.accessory.internal.util.Logger.Processor
    public void process(String str) {
        process(str, null);
    }

    public String toString() {
        String sb;
        synchronized (this.lock) {
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < this.logs.size(); i++) {
                sb2.append(this.logs.get(i));
                sb2.append("\n");
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public CircularMemoryProcessor(int i) {
        Preconditions.precondition(i > 0, "maxLines > 0");
        this.maxLines = i;
        this.lock = new Object();
        this.logs = new LinkedList<>();
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(this.stringWriter);
    }

    @Override // com.amazon.alexa.accessory.internal.util.Logger.Processor
    public void process(String str, Throwable th) {
        synchronized (this.lock) {
            trimLogs(1 + (th == null ? 0 : 1));
            this.logs.addLast(formatLog(str));
            logThrowable(th);
        }
    }
}
