package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.text.DecimalFormat;
/* loaded from: classes4.dex */
public class Timer implements Serializable, StringBuilderFormattable {
    private static final long serialVersionUID = 9175191792439630013L;
    private long elapsedTime;
    private final int iterations;
    private final String name;
    private ThreadLocal<Long> startTime;
    private Status status;
    private static long NANO_PER_SECOND = 1000000000;
    private static long NANO_PER_MINUTE = NANO_PER_SECOND * 60;
    private static long NANO_PER_HOUR = NANO_PER_MINUTE * 60;

    /* renamed from: org.apache.logging.log4j.util.Timer$2  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$logging$log4j$util$Timer$Status = new int[Status.values().length];

        static {
            try {
                $SwitchMap$org$apache$logging$log4j$util$Timer$Status[Status.Started.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$logging$log4j$util$Timer$Status[Status.Paused.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$logging$log4j$util$Timer$Status[Status.Stopped.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum Status {
        Started,
        Stopped,
        Paused
    }

    public Timer(String str) {
        this(str, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timer)) {
            return false;
        }
        Timer timer = (Timer) obj;
        if (this.elapsedTime != timer.elapsedTime || this.startTime != timer.startTime) {
            return false;
        }
        String str = this.name;
        if (str == null ? timer.name != null : !str.equals(timer.name)) {
            return false;
        }
        Status status = this.status;
        Status status2 = timer.status;
        return status == null ? status2 == null : status.equals(status2);
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        sb.append("Timer ");
        sb.append(this.name);
        int ordinal = this.status.ordinal();
        if (ordinal == 0) {
            sb.append(" started");
        } else if (ordinal != 1) {
            if (ordinal != 2) {
                sb.append(Chars.SPACE);
                sb.append(this.status);
                return;
            }
            sb.append(" paused");
        } else {
            long j = this.elapsedTime;
            long j2 = NANO_PER_HOUR;
            long j3 = j / j2;
            long j4 = j % j2;
            long j5 = NANO_PER_MINUTE;
            long j6 = j4 / j5;
            long j7 = j4 % j5;
            long j8 = NANO_PER_SECOND;
            long j9 = j7 / j8;
            long j10 = j7 % j8;
            int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
            String str = "";
            String outline57 = i > 0 ? GeneratedOutlineSupport1.outline57(str, j3, " hours ") : str;
            if (j6 > 0 || i > 0) {
                outline57 = GeneratedOutlineSupport1.outline57(outline57, j6, " minutes ");
            }
            DecimalFormat decimalFormat = new DecimalFormat("#0");
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(outline57);
            outline107.append(decimalFormat.format(j9));
            outline107.append('.');
            String sb2 = outline107.toString();
            DecimalFormat decimalFormat2 = new DecimalFormat("000000000");
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(sb2);
            outline1072.append(decimalFormat2.format(j10));
            outline1072.append(" seconds");
            String sb3 = outline1072.toString();
            sb.append(" stopped. Elapsed time: ");
            sb.append(sb3);
            int i2 = this.iterations;
            if (i2 <= 0) {
                return;
            }
            long j11 = this.elapsedTime / i2;
            long j12 = NANO_PER_HOUR;
            long j13 = j11 / j12;
            long j14 = j11 % j12;
            long j15 = NANO_PER_MINUTE;
            long j16 = j14 / j15;
            long j17 = j14 % j15;
            long j18 = NANO_PER_SECOND;
            long j19 = j17 / j18;
            long j20 = j17 % j18;
            long j21 = 0;
            int i3 = (j13 > 0L ? 1 : (j13 == 0L ? 0 : -1));
            if (i3 > 0) {
                str = GeneratedOutlineSupport1.outline57(str, j13, " hours ");
                j21 = 0;
            }
            if (j16 > j21 || i3 > 0) {
                str = GeneratedOutlineSupport1.outline57(str, j16, " minutes ");
            }
            DecimalFormat decimalFormat3 = new DecimalFormat("#0");
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(str);
            outline1073.append(decimalFormat3.format(j19));
            outline1073.append('.');
            String sb4 = outline1073.toString();
            DecimalFormat decimalFormat4 = new DecimalFormat("000000000");
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(sb4);
            outline1074.append(decimalFormat4.format(j20));
            outline1074.append(" seconds");
            String sb5 = outline1074.toString();
            sb.append(" Average per iteration: ");
            sb.append(sb5);
        }
    }

    public long getElapsedNanoTime() {
        return this.elapsedTime;
    }

    public long getElapsedTime() {
        return this.elapsedTime / 1000000;
    }

    public String getName() {
        return this.name;
    }

    public Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 29;
        Status status = this.status;
        if (status != null) {
            i = status.hashCode();
        }
        int i2 = hashCode + i;
        long longValue = this.startTime.get().longValue();
        long j = this.elapsedTime;
        return (((i2 * 29) + ((int) (longValue ^ (longValue >>> 32)))) * 29) + ((int) (j ^ (j >>> 32)));
    }

    public synchronized void pause() {
        this.elapsedTime = (System.nanoTime() - this.startTime.get().longValue()) + this.elapsedTime;
        this.startTime.set(0L);
        this.status = Status.Paused;
    }

    public synchronized void resume() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.status = Status.Started;
    }

    public synchronized void start() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.elapsedTime = 0L;
        this.status = Status.Started;
    }

    public synchronized void startOrResume() {
        if (this.status == Status.Stopped) {
            start();
        } else {
            resume();
        }
    }

    public synchronized String stop() {
        this.elapsedTime = (System.nanoTime() - this.startTime.get().longValue()) + this.elapsedTime;
        this.startTime.set(0L);
        this.status = Status.Stopped;
        return toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        formatTo(sb);
        return sb.toString();
    }

    public Timer(String str, int i) {
        this.startTime = new ThreadLocal<Long>() { // from class: org.apache.logging.log4j.util.Timer.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            /* renamed from: initialValue */
            public Long mo12844initialValue() {
                return 0L;
            }
        };
        this.name = str;
        this.status = Status.Stopped;
        this.iterations = i <= 0 ? 0 : i;
    }
}
