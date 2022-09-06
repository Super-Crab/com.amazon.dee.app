package com.amazon.ptz.physical.communication.responses.handlers;

import android.util.Log;
import com.amazon.alexa.property.Property;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.PropertyName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.payload.RcChangeReportPayload;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.NtpClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import javax.inject.Singleton;
import lombok.Generated;
import org.apache.commons.net.ntp.TimeStamp;
@Singleton
/* loaded from: classes13.dex */
public class ChangeReportHandler implements ResponseHandler, NtpClient.NtpListener {
    private static final String TAG = LogTag.forClass(ChangeReportHandler.class);
    @Nonnull
    private final MetricRecorder metricRecorder;
    private Long ntpOffsetMilliseconds;
    @Nonnull
    private final RcSerializer rcSerializer;
    private TimeStamp remoteTimeStamp;
    private long systemTimeUponReceipt;

    /* renamed from: com.amazon.ptz.physical.communication.responses.handlers.ChangeReportHandler$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$rangecontroller$lib$model$PropertyName = new int[PropertyName.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$rangecontroller$lib$model$PropertyName[PropertyName.TIMESTAMP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReportHandler(@Nonnull RcSerializer rcSerializer, @Nonnull MetricRecorder metricRecorder) {
        if (rcSerializer != null) {
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            this.rcSerializer = rcSerializer;
            this.metricRecorder = metricRecorder;
            return;
        }
        throw new IllegalArgumentException("rcSerializer is null");
    }

    private void handleProperty(Property<NamespaceName, PropertyName, EmptyInstance, JsonElement> property) {
        PropertyName name = property.getName();
        if (name.ordinal() != 0) {
            String str = TAG;
            Log.e(str, "Unhandled property encountered " + name);
            return;
        }
        this.remoteTimeStamp = (TimeStamp) this.rcSerializer.fromJson(property.getValue(), TimeStamp.class);
        this.systemTimeUponReceipt = System.currentTimeMillis();
        if (this.ntpOffsetMilliseconds == null) {
            NtpClient.getNtpOffset(this);
        } else {
            reportDataChannelLatency();
        }
    }

    private void reportDataChannelLatency() {
        TimeStamp ntpTime = TimeStamp.getNtpTime(this.ntpOffsetMilliseconds.longValue() + this.systemTimeUponReceipt);
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Local clock time: ");
        outline107.append(currentTime.toDateString());
        outline107.toString();
        String str = "Synchronized time: " + ntpTime.toDateString();
        long time = ntpTime.getTime() - this.remoteTimeStamp.getTime();
        GeneratedOutlineSupport1.outline153("Data channel latency: ", time);
        this.metricRecorder.recordRtcDataChannelLatency(Long.valueOf(time));
    }

    @Override // com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler
    public boolean canHandle(HeaderName headerName, NamespaceName namespaceName) {
        return HeaderName.CHANGE_REPORT.equals(headerName);
    }

    @Override // com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler
    public void handle(JsonElement jsonElement) {
        RcChangeReportPayload rcChangeReportPayload = (RcChangeReportPayload) this.rcSerializer.fromJson(jsonElement, RcChangeReportPayload.class);
        if (rcChangeReportPayload == null) {
            String str = TAG;
            Log.e(str, "Failed to deserialize payload: " + jsonElement);
            return;
        }
        for (Property<NamespaceName, PropertyName, EmptyInstance, JsonElement> property : rcChangeReportPayload.getChange().getProperties()) {
            handleProperty(property);
        }
    }

    @Override // com.amazon.ptz.util.NtpClient.NtpListener
    public void onNtpOffsetReceived(long j) {
        this.ntpOffsetMilliseconds = Long.valueOf(j);
        reportDataChannelLatency();
    }
}
