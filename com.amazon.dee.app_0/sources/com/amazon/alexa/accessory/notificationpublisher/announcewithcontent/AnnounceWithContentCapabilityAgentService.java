package com.amazon.alexa.accessory.notificationpublisher.announcewithcontent;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.amazon.alexa.accessory.notificationpublisher.directiveconsumer.ZionAnnounceWithContentDirectiveConsumer;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class AnnounceWithContentCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String TAG = AnnounceWithContentCapabilityAgentService.class.getSimpleName();
    private Set<AlexaCapability> capabilities;
    private ZionAnnounceWithContentDirectiveConsumer zionAnnounceWithContentDirectiveConsumer;

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    @UiThread
    public void doUnbind() {
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        return this.capabilities;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.capabilities = new HashSet();
        this.capabilities.add(AnnounceWithContentCapability.create());
        this.zionAnnounceWithContentDirectiveConsumer = new ZionAnnounceWithContentDirectiveConsumer();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(@NonNull AlexaDirective alexaDirective) {
        SimpleAnnounceWithContentDirective simpleAnnounceWithContentDirective;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("process - ");
        outline107.append(alexaDirective.getNamespace());
        outline107.append(".");
        outline107.append(alexaDirective.getName());
        Log.i(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.DIRECTIVE_RECEIVED);
        try {
            simpleAnnounceWithContentDirective = new SimpleAnnounceWithContentDirective(alexaDirective);
        } catch (Exception e) {
            Log.w(TAG, "process - Exception when processing directive. ", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.PROCESS_DIRECTIVE_ERROR_INSTANTIATE, MetricsRecorder.customAttributesForException(e));
            simpleAnnounceWithContentDirective = null;
        }
        try {
            this.zionAnnounceWithContentDirectiveConsumer.consumeDirective(simpleAnnounceWithContentDirective);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.PROCESS_DIRECTIVE_SUCCESS);
            return true;
        } catch (Exception e2) {
            Log.w(TAG, "process - Exception when processing directive. ", e2);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.PROCESS_DIRECTIVE_ERROR_CONSUME, MetricsRecorder.customAttributesForException(e2));
            return true;
        }
    }
}
