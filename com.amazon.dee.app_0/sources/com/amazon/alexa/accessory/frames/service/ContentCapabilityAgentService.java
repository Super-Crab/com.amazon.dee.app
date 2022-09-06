package com.amazon.alexa.accessory.frames.service;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentCapability;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.SimpleAnnounceWithContentDirective;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class ContentCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String TAG = ContentCapabilityAgentService.class.getSimpleName();
    private Set<AlexaCapability> capabilities;
    private DirectiveConsumer directiveConsumer;

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        return this.capabilities;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.capabilities = new HashSet();
        this.capabilities.add(AnnounceWithContentCapability.create());
        this.directiveConsumer = new DirectiveConsumer();
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
        try {
            simpleAnnounceWithContentDirective = new SimpleAnnounceWithContentDirective(alexaDirective);
        } catch (Exception e) {
            Log.d(TAG, "process - Exception when processing directive. ", e);
            simpleAnnounceWithContentDirective = null;
        }
        try {
            this.directiveConsumer.consumeDirective(simpleAnnounceWithContentDirective);
        } catch (Exception e2) {
            Log.d(TAG, "process - Exception when processing directive. ", e2);
        }
        return true;
    }
}
