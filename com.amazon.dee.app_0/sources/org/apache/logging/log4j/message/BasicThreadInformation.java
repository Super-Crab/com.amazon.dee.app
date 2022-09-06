package org.apache.logging.log4j.message;

import java.lang.Thread;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilders;
/* loaded from: classes4.dex */
class BasicThreadInformation implements ThreadInformation {
    private static final int HASH_MULTIPLIER = 31;
    private static final int HASH_SHIFT = 32;
    private final long id;
    private final boolean isAlive;
    private final boolean isDaemon;
    private final String longName;
    private final String name;
    private final int priority;
    private final Thread.State state;
    private final String threadGroupName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicThreadInformation(Thread thread) {
        this.id = thread.getId();
        this.name = thread.getName();
        this.longName = thread.toString();
        this.state = thread.getState();
        this.priority = thread.getPriority();
        this.isAlive = thread.isAlive();
        this.isDaemon = thread.isDaemon();
        ThreadGroup threadGroup = thread.getThreadGroup();
        this.threadGroupName = threadGroup == null ? null : threadGroup.getName();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BasicThreadInformation.class != obj.getClass()) {
            return false;
        }
        BasicThreadInformation basicThreadInformation = (BasicThreadInformation) obj;
        if (this.id != basicThreadInformation.id) {
            return false;
        }
        String str = this.name;
        String str2 = basicThreadInformation.name;
        return str == null ? str2 == null : str.equals(str2);
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.name;
        return i + (str != null ? str.hashCode() : 0);
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr) {
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printThreadInfo(StringBuilder sb) {
        StringBuilders.appendDqValue(sb, this.name).append(Chars.SPACE);
        if (this.isDaemon) {
            sb.append("daemon ");
        }
        sb.append("prio=");
        sb.append(this.priority);
        sb.append(" tid=");
        sb.append(this.id);
        sb.append(Chars.SPACE);
        String str = this.threadGroupName;
        if (str != null) {
            StringBuilders.appendKeyDqValue(sb, "group", str);
        }
        sb.append('\n');
        sb.append("\tThread state: ");
        sb.append(this.state.name());
        sb.append('\n');
    }
}
