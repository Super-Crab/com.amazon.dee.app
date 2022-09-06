package org.apache.logging.log4j.message;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringBuilderFormattable;
@AsynchronouslyFormattable
/* loaded from: classes4.dex */
public class ThreadDumpMessage implements Message, StringBuilderFormattable {
    private static ThreadInfoFactory FACTORY = null;
    private static final long serialVersionUID = -1103400781608841088L;
    private String formattedMessage;
    private volatile Map<ThreadInformation, StackTraceElement[]> threads;
    private final String title;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class BasicThreadInfoFactory implements ThreadInfoFactory {
        private BasicThreadInfoFactory() {
        }

        @Override // org.apache.logging.log4j.message.ThreadDumpMessage.ThreadInfoFactory
        public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            HashMap hashMap = new HashMap(allStackTraces.size());
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                hashMap.put(new BasicThreadInformation(entry.getKey()), entry.getValue());
            }
            return hashMap;
        }
    }

    /* loaded from: classes4.dex */
    private static class ThreadDumpMessageProxy implements Serializable {
        private static final long serialVersionUID = -3476620450287648269L;
        private final String formattedMsg;
        private final String title;

        ThreadDumpMessageProxy(ThreadDumpMessage threadDumpMessage) {
            this.formattedMsg = threadDumpMessage.getFormattedMessage();
            this.title = threadDumpMessage.title;
        }

        protected Object readResolve() {
            return new ThreadDumpMessage(this.formattedMsg, this.title);
        }
    }

    /* loaded from: classes4.dex */
    public interface ThreadInfoFactory {
        Map<ThreadInformation, StackTraceElement[]> createThreadInfo();
    }

    private static ThreadInfoFactory getFactory() {
        if (FACTORY == null) {
            FACTORY = initFactory(ThreadDumpMessage.class.getClassLoader());
        }
        return FACTORY;
    }

    private static ThreadInfoFactory initFactory(ClassLoader classLoader) {
        ThreadInfoFactory threadInfoFactory;
        try {
            Iterator it2 = ServiceLoader.load(ThreadInfoFactory.class, classLoader).iterator();
            threadInfoFactory = null;
            while (threadInfoFactory == null) {
                if (!it2.hasNext()) {
                    break;
                }
                threadInfoFactory = (ThreadInfoFactory) it2.next();
            }
        } catch (Exception | LinkageError | ServiceConfigurationError e) {
            StatusLogger.getLogger().info("ThreadDumpMessage uses BasicThreadInfoFactory: could not load extended ThreadInfoFactory: {}", e.toString());
            threadInfoFactory = null;
        }
        return threadInfoFactory == null ? new BasicThreadInfoFactory() : threadInfoFactory;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        sb.append(this.title);
        if (this.title.length() > 0) {
            sb.append('\n');
        }
        for (Map.Entry<ThreadInformation, StackTraceElement[]> entry : this.threads.entrySet()) {
            ThreadInformation key = entry.getKey();
            key.printThreadInfo(sb);
            key.printStack(sb, entry.getValue());
            sb.append('\n');
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        String str = this.title;
        return str == null ? "" : str;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        String str = this.formattedMessage;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(255);
        formatTo(sb);
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return null;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return null;
    }

    public String toString() {
        return getFormattedMessage();
    }

    protected Object writeReplace() {
        return new ThreadDumpMessageProxy(this);
    }

    public ThreadDumpMessage(String str) {
        this.title = str == null ? "" : str;
        this.threads = getFactory().createThreadInfo();
    }

    private ThreadDumpMessage(String str, String str2) {
        this.formattedMessage = str;
        this.title = str2 == null ? "" : str2;
    }
}
