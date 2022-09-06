package com.amazon.dee.app.ui.main;

import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.Printer;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.google.common.collect.ImmutableList;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class RNLogPrinter implements Printer {
    private static final int CRASH_METADATA_CAPACITY = 3;
    private static final int LOG_LEVEL_ALL = 0;
    private static final int LOG_LEVEL_NORMAL = 2;
    private static final int LOG_LEVEL_VERBOSE = 1;
    private SimpleDateFormat dateFormatter;
    private final Lazy<RoutingService> routingServiceLazy;
    private List<DebugOverlayTag> validTags;
    private static final String TAG = Log.tag(RNLogPrinter.class);
    private static final List<DebugOverlayTag> HIGH_FREQUENCY_TAGS = ImmutableList.of(ReactDebugOverlayTags.BRIDGE_CALLS, ReactDebugOverlayTags.UI_MANAGER);
    private boolean loggingEnabled = false;
    private final String[] metadata = new String[3];
    private final String[] routeIdMetadata = new String[3];
    private final int[] threadIdMetadata = new int[3];
    private final long[] timestampMetadata = new long[3];
    private int queueIndex = 0;

    public RNLogPrinter(Lazy<RoutingService> lazy) {
        this.routingServiceLazy = lazy;
        configurePrinter();
    }

    private void configurePrinter() {
        initialize(true, 0);
        PrinterHolder.setPrinter(this);
    }

    private String createCrashMetadataString() {
        String str = "metadata";
        int i = this.queueIndex;
        for (int i2 = 0; i2 < 3; i2++) {
            i = ((i - 1) + 3) % 3;
            str = String.format("%s | %s", str, createCrashMetadataString(i));
        }
        return str;
    }

    private String getDate(long j) {
        if (this.dateFormatter == null) {
            this.dateFormatter = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        }
        return this.dateFormatter.format(Long.valueOf(j));
    }

    private void initialize(boolean z, int i) {
        this.validTags = new ArrayList();
        this.validTags.add(ReactDebugOverlayTags.PERFORMANCE);
        this.validTags.add(ReactDebugOverlayTags.NAVIGATION);
        this.validTags.add(ReactDebugOverlayTags.RN_CORE);
        if (i < 2) {
            this.validTags.add(ReactDebugOverlayTags.NATIVE_MODULE);
            this.validTags.add(ReactDebugOverlayTags.RELAY);
        }
        if (i < 1) {
            this.validTags.add(ReactDebugOverlayTags.BRIDGE_CALLS);
            this.validTags.add(ReactDebugOverlayTags.UI_MANAGER);
        }
        enableMessageLogging(z);
    }

    private boolean isHighFrequencyMessage(DebugOverlayTag debugOverlayTag) {
        return HIGH_FREQUENCY_TAGS.contains(debugOverlayTag);
    }

    private void saveCrashMetadata(String str) {
        RouteContext currentRoute = this.routingServiceLazy.mo358get().getCurrentRoute();
        Route route = currentRoute == null ? null : currentRoute.getRoute();
        this.routeIdMetadata[this.queueIndex] = route == null ? "(no route)" : route.getName();
        String[] strArr = this.metadata;
        int i = this.queueIndex;
        strArr[i] = str;
        this.threadIdMetadata[i] = Process.getThreadPriority(Process.myTid());
        this.timestampMetadata[this.queueIndex] = System.currentTimeMillis();
        this.queueIndex = (this.queueIndex + 1) % 3;
    }

    @VisibleForTesting
    void doLog(String str) {
    }

    @VisibleForTesting
    void doLog(String str, Object[] objArr) {
    }

    public void enableMessageLogging(boolean z) {
        this.loggingEnabled = z;
    }

    public void handleNativeModuleCallException(Exception exc) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception thrown during native module call: ");
        outline107.append(createCrashMetadataString());
        throw new RuntimeException(outline107.toString(), exc);
    }

    @Override // com.facebook.debug.holder.Printer
    public void logMessage(DebugOverlayTag debugOverlayTag, String str, Object... objArr) {
        saveCrashMetadata(String.format(str, objArr));
        if (!isHighFrequencyMessage(debugOverlayTag)) {
            doLog(GeneratedOutlineSupport1.outline92(new StringBuilder(), debugOverlayTag.name, " = ", str), objArr);
        }
    }

    @Override // com.facebook.debug.holder.Printer
    public boolean shouldDisplayLogMessage(DebugOverlayTag debugOverlayTag) {
        if (!this.loggingEnabled) {
            return false;
        }
        List<DebugOverlayTag> list = this.validTags;
        return list == null || list.contains(debugOverlayTag);
    }

    private String createCrashMetadataString(int i) {
        return String.format("%s thread %d %s %s", getDate(this.timestampMetadata[i]), Integer.valueOf(this.threadIdMetadata[i]), this.routeIdMetadata[i], this.metadata[i]);
    }

    @Override // com.facebook.debug.holder.Printer
    public void logMessage(DebugOverlayTag debugOverlayTag, String str) {
        saveCrashMetadata(str);
        if (!isHighFrequencyMessage(debugOverlayTag)) {
            doLog(GeneratedOutlineSupport1.outline92(new StringBuilder(), debugOverlayTag.name, " = ", str));
        }
    }
}
