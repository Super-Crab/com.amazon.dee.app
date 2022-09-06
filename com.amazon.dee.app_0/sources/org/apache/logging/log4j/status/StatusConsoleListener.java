package org.apache.logging.log4j.status;

import java.io.IOException;
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
/* loaded from: classes4.dex */
public class StatusConsoleListener implements StatusListener {
    private String[] filters;
    private Level level;
    private final PrintStream stream;

    public StatusConsoleListener(Level level) {
        this(level, System.out);
    }

    private boolean filtered(StatusData statusData) {
        if (this.filters == null) {
            return false;
        }
        String className = statusData.getStackTraceElement().getClassName();
        for (String str : this.filters) {
            if (className.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        PrintStream printStream = this.stream;
        if (printStream == System.out || printStream == System.err) {
            return;
        }
        printStream.close();
    }

    @Override // org.apache.logging.log4j.status.StatusListener
    public Level getStatusLevel() {
        return this.level;
    }

    @Override // org.apache.logging.log4j.status.StatusListener
    public void log(StatusData statusData) {
        if (!filtered(statusData)) {
            this.stream.println(statusData.getFormattedStatus());
        }
    }

    public void setFilters(String... strArr) {
        this.filters = strArr;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public StatusConsoleListener(Level level, PrintStream printStream) {
        this.level = Level.FATAL;
        if (printStream != null) {
            this.level = level;
            this.stream = printStream;
            return;
        }
        throw new IllegalArgumentException("You must provide a stream to use for this listener.");
    }
}
