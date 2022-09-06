package com.amazon.latencyinfra;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
class LatencyReporterSet {
    LatencyReporter logReporter;
    LatencyReporter metricsReporter;
    private int reporterCount;
    private String testID;
    private Map<String, LatencyReporter> domainReporterCache = new HashMap();
    private Map<String, List<LatencyOutputObject>> latencyCache = new HashMap();
    private boolean isReporterReady = false;
    private boolean hasTestID = false;

    /* renamed from: com.amazon.latencyinfra.LatencyReporterSet$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$latencyinfra$LatencyReporterType = new int[LatencyReporterType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$latencyinfra$LatencyReporterType[LatencyReporterType.LOG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$LatencyReporterType[LatencyReporterType.METRIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void addDefaultReporter(DefaultLatencyReporter defaultLatencyReporter) {
        int ordinal = defaultLatencyReporter.getType().ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                Log.w("[Latench Infra]:", "Default latency reporter can only be used to report log or metrics");
            } else if (this.metricsReporter == null) {
                this.metricsReporter = defaultLatencyReporter;
                this.reporterCount++;
            } else {
                Log.w("Latency Infra]:", "Metrics reporter has been registered already");
            }
        } else if (this.logReporter == null) {
            this.logReporter = defaultLatencyReporter;
            this.reporterCount++;
        } else {
            Log.w("Latency Infra]:", "Log reporter has been registered already");
        }
        if (this.reporterCount != 2 || !this.hasTestID || this.isReporterReady) {
            return;
        }
        this.isReporterReady = true;
        reportCachedEntries();
    }

    public void addDomainReporter(DomainLatencyReporter domainLatencyReporter) {
        String namespace = domainLatencyReporter.getNamespace();
        if (this.domainReporterCache.containsKey(namespace)) {
            String str = "reporter has already been set for " + namespace + ".";
        }
        this.domainReporterCache.put(namespace, domainLatencyReporter);
    }

    public void reportCachedEntries() {
        String str;
        Iterator<String> it2 = this.latencyCache.keySet().iterator();
        while (it2.hasNext()) {
            for (LatencyOutputObject latencyOutputObject : this.latencyCache.get(it2.next())) {
                LatencyReporterArgument argument = latencyOutputObject.getArgument();
                LatencyRecorderOption options = latencyOutputObject.getOptions();
                if (options.hasPerfTestOption() && (str = this.testID) != null) {
                    argument.setSourceMetaData(str);
                }
                reportWithOptions(argument, options);
            }
            it2.remove();
        }
    }

    public void reportWithOptions(LatencyReporterArgument latencyReporterArgument, LatencyRecorderOption latencyRecorderOption) {
        LatencyReporter latencyReporter;
        LatencyReporter latencyReporter2;
        String str;
        if (latencyReporterArgument != null && latencyRecorderOption != null) {
            String namespace = latencyReporterArgument.getNamespace();
            if (!this.isReporterReady) {
                if (!this.latencyCache.containsKey(namespace)) {
                    this.latencyCache.put(namespace, new ArrayList());
                }
                List<LatencyOutputObject> list = this.latencyCache.get(namespace);
                list.add(new LatencyOutputObject(latencyReporterArgument, latencyRecorderOption));
                this.latencyCache.put(namespace, list);
                return;
            }
            if (latencyRecorderOption.hasPerfTestOption() && (str = this.testID) != null) {
                latencyReporterArgument.setSourceMetaData(str);
            }
            if (latencyRecorderOption.hasNamespaceOption()) {
                LatencyReporter latencyReporter3 = this.domainReporterCache.get(namespace);
                if (latencyReporter3 != null) {
                    latencyReporter3.report(latencyReporterArgument);
                } else {
                    String str2 = "Domain reporter is set to report for " + namespace + " but reporter is null";
                }
            }
            if (latencyRecorderOption.hasMetricsReporterOption() && (latencyReporter2 = this.metricsReporter) != null) {
                latencyReporter2.report(latencyReporterArgument);
            }
            if (!latencyRecorderOption.hasLogReporterOption() || (latencyReporter = this.logReporter) == null) {
                return;
            }
            latencyReporter.report(latencyReporterArgument);
            return;
        }
        Log.w("[Latency Infra]: ", "Failed to report, argument or option is null.");
    }

    public void setTestID(String str) {
        this.hasTestID = true;
        this.testID = str;
        if (this.reporterCount != 2 || !this.hasTestID || this.isReporterReady) {
            return;
        }
        this.isReporterReady = true;
        reportCachedEntries();
    }
}
