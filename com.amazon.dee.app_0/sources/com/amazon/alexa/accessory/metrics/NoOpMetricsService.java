package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes.dex */
public class NoOpMetricsService implements AccessoryMetricsService {
    private Logger.Level logLevel;

    /* renamed from: com.amazon.alexa.accessory.metrics.NoOpMetricsService$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$internal$util$Logger$Level = new int[Logger.Level.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$util$Logger$Level[Logger.Level.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$util$Logger$Level[Logger.Level.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public NoOpMetricsService() {
        this(Logger.Level.INFO);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void createTimer(String str, String str2, String str3, String str4) {
        Preconditions.notNull(str, "metricIdentifier");
        Preconditions.notNull(str2, "timerName");
        Preconditions.notNull(str3, JsonFields.COMPONENT);
        int ordinal = this.logLevel.ordinal();
        if (ordinal == 2) {
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print createTimer. metricIdentifier:", str, " timerName:", str2, " component:");
            outline116.append(str3);
            outline116.append(" subComponent:");
            outline116.append(str4);
            Logger.d(outline116.toString());
        } else if (ordinal != 3) {
            StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print createTimer. metricIdentifier:", str, " timerName:", str2, " component:");
            outline1162.append(str3);
            outline1162.append(" subComponent:");
            outline1162.append(str4);
            Logger.w(outline1162.toString());
        } else {
            StringBuilder outline1163 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print createTimer. metricIdentifier:", str, " timerName:", str2, " component:");
            outline1163.append(str3);
            outline1163.append(" subComponent:");
            outline1163.append(str4);
            Logger.v(outline1163.toString());
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCounter(String str, String str2, double d, Map<String, Object> map) {
        int ordinal = this.logLevel.ordinal();
        if (ordinal == 2) {
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " counter:");
            outline116.append(d);
            Logger.d(outline116.toString());
        } else if (ordinal != 3) {
            StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " counter:");
            outline1162.append(d);
            Logger.w(outline1162.toString());
        } else {
            StringBuilder outline1163 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " counter:");
            outline1163.append(d);
            Logger.v(outline1163.toString());
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCriticalEvent(String str, String str2, String str3, Throwable th) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " subComponent:", str3, " error:");
        outline116.append(th);
        Logger.e(outline116.toString());
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        int ordinal = this.logLevel.ordinal();
        if (ordinal == 2) {
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " eventOccurred:");
            outline116.append(z);
            Logger.d(outline116.toString());
        } else if (ordinal != 3) {
            StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " eventOccurred:");
            outline1162.append(z);
            Logger.w(outline1162.toString());
        } else {
            StringBuilder outline1163 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " eventOccurred:");
            outline1163.append(z);
            Logger.v(outline1163.toString());
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTime(String str, String str2, long j, Map<String, Object> map) {
        int ordinal = this.logLevel.ordinal();
        if (ordinal == 2) {
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " timeInterval:");
            outline116.append(j);
            Logger.d(outline116.toString());
        } else if (ordinal != 3) {
            StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " timeInterval:");
            outline1162.append(j);
            Logger.w(outline1162.toString());
        } else {
            StringBuilder outline1163 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " componentName:", str2, " timeInterval:");
            outline1163.append(j);
            Logger.v(outline1163.toString());
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTimer(String str) {
        Preconditions.notNull(str, "metricIdentifier");
        int ordinal = this.logLevel.ordinal();
        if (ordinal == 2) {
            Logger.d("AccessoryMetricsService was not set, NO-OP impl is used to print recordTimer. metricIdentifier:" + str);
        } else if (ordinal != 3) {
            Logger.w("AccessoryMetricsService was not set, NO-OP impl is used to print recordTimer. metricIdentifier:" + str);
        } else {
            Logger.v("AccessoryMetricsService was not set, NO-OP impl is used to print recordTimer. metricIdentifier:" + str);
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordWarningEvent(String str, String str2, String str3, Throwable th) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AccessoryMetricsService was not set, NO-OP impl is used to print this metrics. eventName:", str, " subComponent:", str3, " error:");
        outline116.append(th);
        Logger.w(outline116.toString());
    }

    public NoOpMetricsService(Logger.Level level) {
        this.logLevel = level;
    }
}
