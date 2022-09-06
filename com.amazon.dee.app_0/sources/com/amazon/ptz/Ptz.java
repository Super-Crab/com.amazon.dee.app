package com.amazon.ptz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.ptz.gestures.listeners.PtzKeyEventListener;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.metrics.MetricRecorderImpl;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class Ptz {
    private static final String TAG = LogTag.forClass(Ptz.class);
    @Nonnull
    private final PtzKeyEventListener ptzKeyEventListener;
    @Nonnull
    private final PtzListener ptzListener;
    @Nonnull
    private final PhysicalPtzResponseHandler responseHandler;

    public Ptz(Context context, ViewGroup viewGroup, View view, PhysicalPtzDirectiveSender physicalPtzDirectiveSender, List<CameraPtzInstance> list, MetricRecorder metricRecorder) {
        PtzComponent build = DaggerPtzComponent.builder().mo4455context(context).mo4459viewGroup(viewGroup).mo4458view(view).mo4457physicalPtzDirectiveSender(physicalPtzDirectiveSender).mo4454cameraPtzInstances(list).mo4456metricRecorder(metricRecorder == null ? new MetricRecorderImpl() : metricRecorder).build();
        this.ptzListener = build.getPtzListener();
        this.ptzKeyEventListener = build.getPtzKeyEventListener();
        this.responseHandler = build.getResponseHandler();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Nonnull
    @Generated
    public PtzKeyEventListener getPtzKeyEventListener() {
        return this.ptzKeyEventListener;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Nonnull
    @Generated
    public PtzListener getPtzListener() {
        return this.ptzListener;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Nonnull
    @Generated
    public PhysicalPtzResponseHandler getResponseHandler() {
        return this.responseHandler;
    }
}
