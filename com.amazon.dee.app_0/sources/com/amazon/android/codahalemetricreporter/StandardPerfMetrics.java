package com.amazon.android.codahalemetricreporter;

import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.sun.mail.imap.IMAPStore;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class StandardPerfMetrics {
    public static final String BINDER_OBJECTS_DEATH = "android.binder.objects.death";
    public static final String BINDER_OBJECTS_LOCAL = "android.binder.objects.local";
    public static final String BINDER_OBJECTS_PROXY = "android.binder.objects.proxy";
    public static final String HEAP_ALLOCATED = "android.heap.allocated";
    public static final String HEAP_FREE = "android.heap.free";
    public static final String HEAP_SIZE = "android.heap.size";
    public static final String MEMORY_FREE = "java.runtime.memory.free";
    public static final String MEMORY_MAX = "java.runtime.memory.max";
    public static final String MEMORY_TOTAL = "java.runtime.memory.total";
    public static final String PROCESS_UPTIME = "java.runtime.process.uptime";
    public static final String REPORT_TIME = "report.time";
    public static final String THREAD_COUNT = "java.runtime.thread.count";

    /* loaded from: classes11.dex */
    private static final class BinderDeathObjectsMetric implements Gauge<Integer> {
        private BinderDeathObjectsMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Integer mo6796getValue() {
            return Integer.valueOf(Debug.getBinderDeathObjectCount());
        }
    }

    /* loaded from: classes11.dex */
    private static final class BinderLocalObjectsMetric implements Gauge<Integer> {
        private BinderLocalObjectsMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Integer mo6796getValue() {
            return Integer.valueOf(Debug.getBinderLocalObjectCount());
        }
    }

    /* loaded from: classes11.dex */
    private static final class BinderProxyObjectsMetric implements Gauge<Integer> {
        private BinderProxyObjectsMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Integer mo6796getValue() {
            return Integer.valueOf(Debug.getBinderProxyObjectCount());
        }
    }

    /* loaded from: classes11.dex */
    private static final class HeapAllocatedMetric implements Gauge<Long> {
        private HeapAllocatedMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Debug.getNativeHeapAllocatedSize());
        }
    }

    /* loaded from: classes11.dex */
    private static final class HeapFreeMetric implements Gauge<Long> {
        private HeapFreeMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Debug.getNativeHeapFreeSize());
        }
    }

    /* loaded from: classes11.dex */
    private static final class HeapSizeMetric implements Gauge<Long> {
        private HeapSizeMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Debug.getNativeHeapSize());
        }
    }

    /* loaded from: classes11.dex */
    private static final class MemoryFreeMetric implements Gauge<Long> {
        private MemoryFreeMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Runtime.getRuntime().freeMemory());
        }
    }

    /* loaded from: classes11.dex */
    private static final class MemoryMaxMetric implements Gauge<Long> {
        private MemoryMaxMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Runtime.getRuntime().maxMemory());
        }
    }

    /* loaded from: classes11.dex */
    private static final class MemoryTotalMetric implements Gauge<Long> {
        private MemoryTotalMetric() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Long mo6796getValue() {
            return Long.valueOf(Runtime.getRuntime().totalMemory());
        }
    }

    /* loaded from: classes11.dex */
    private static final class ReportTimeMetric implements Gauge<String> {
        private ReportTimeMetric() {
        }

        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue  reason: collision with other method in class */
        public String mo6796getValue() {
            return StandardPerfMetrics.getTimeStr();
        }
    }

    /* loaded from: classes11.dex */
    private static final class ThreadCountMetric implements Gauge<Integer> {
        private final ThreadGroup mThreadGroup;

        private ThreadCountMetric() {
            this.mThreadGroup = StandardPerfMetrics.getRootThreadGroup();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue */
        public Integer mo6796getValue() {
            return Integer.valueOf(this.mThreadGroup.activeCount());
        }
    }

    /* loaded from: classes11.dex */
    private static final class UptimeMetric implements Gauge<String> {
        private static final long T0 = StandardPerfMetrics.getStartTime(Process.myPid());

        private UptimeMetric() {
        }

        @Override // com.codahale.metrics.Gauge
        /* renamed from: getValue  reason: collision with other method in class */
        public String mo6796getValue() {
            return String.valueOf(SystemClock.elapsedRealtime() - T0);
        }
    }

    private StandardPerfMetrics() {
    }

    public static void add(MetricRegistry metricRegistry) {
        metricRegistry.register(REPORT_TIME, new ReportTimeMetric());
        metricRegistry.register(MEMORY_FREE, new MemoryFreeMetric());
        metricRegistry.register(MEMORY_TOTAL, new MemoryTotalMetric());
        metricRegistry.register(MEMORY_MAX, new MemoryMaxMetric());
        metricRegistry.register(HEAP_FREE, new HeapFreeMetric());
        metricRegistry.register(HEAP_ALLOCATED, new HeapAllocatedMetric());
        metricRegistry.register(HEAP_SIZE, new HeapSizeMetric());
        metricRegistry.register(THREAD_COUNT, new ThreadCountMetric());
        metricRegistry.register(PROCESS_UPTIME, new UptimeMetric());
        metricRegistry.register(BINDER_OBJECTS_DEATH, new BinderDeathObjectsMetric());
        metricRegistry.register(BINDER_OBJECTS_LOCAL, new BinderLocalObjectsMetric());
        metricRegistry.register(BINDER_OBJECTS_PROXY, new BinderProxyObjectsMetric());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ThreadGroup getRootThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getStartTime(int i) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(GeneratedOutlineSupport1.outline52("/proc/", i, "/stat")));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            try {
                try {
                    try {
                        try {
                            long parseLong = Long.parseLong(readLine.substring(readLine.lastIndexOf(") ")).split(" ")[20]);
                            int i2 = Class.forName("libcore.io.OsConstants").getField("_SC_CLK_TCK").getInt(null);
                            Object obj = Class.forName("libcore.io.Libcore").getField(IMAPStore.ID_OS).get(null);
                            return (parseLong * 1000) / ((Long) obj.getClass().getMethod("sysconf", Integer.TYPE).invoke(obj, Integer.valueOf(i2))).longValue();
                        } catch (NoSuchFieldException e) {
                            throw new IOException(e);
                        }
                    } catch (NoSuchMethodException e2) {
                        throw new IOException(e2);
                    } catch (NumberFormatException e3) {
                        throw new IOException(e3);
                    }
                } catch (ClassNotFoundException e4) {
                    throw new IOException(e4);
                } catch (IndexOutOfBoundsException e5) {
                    throw new IOException(e5);
                }
            } catch (IllegalAccessException e6) {
                throw new IOException(e6);
            } catch (InvocationTargetException e7) {
                throw new IOException(e7);
            }
        } catch (IOException unused) {
            return SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getTimeStr() {
        Calendar calendar = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%02d-%02d %02d:%02d:%02d.%03d", Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)), Integer.valueOf(calendar.get(14)));
    }
}
