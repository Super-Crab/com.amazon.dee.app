package com.amazon.alexa.api;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.annotations.UiThread;
import com.amazon.alexa.utils.security.SignatureVerifier;
import java.util.Set;
/* loaded from: classes6.dex */
public abstract class AlexaCapabilityAgentService extends Service {
    public static final String ALEXA_CAPABILITY_SERVICE_VERSION = "alexa_capability_service_version";
    private static final String CATEGORY = "com.amazon.alexa.CATEGORY_CAPABILITY_AGENT";
    private MessageReceiver<ab> capabilityAgentMessageReceiver;
    private MessageReceiversManager messageReceiversManager;
    private static final String TAG = AlexaCapabilityAgentService.class.getSimpleName();
    private static final String ACTION = "com.amazon.alexa.ACTION_PROCESS_DIRECTIVE";
    public static final Intent EXTERNAL_CAPABILITY_AGENT_INTENT = new Intent(ACTION);

    static {
        EXTERNAL_CAPABILITY_AGENT_INTENT.addCategory(CATEGORY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @UiThread
    public boolean cancel(AlexaDirective alexaDirective) {
        return true;
    }

    @UiThread
    public void doBind() {
    }

    @UiThread
    public void doUnbind() {
    }

    public abstract Set<AlexaCapability> getCapabilities();

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        doBind();
        this.capabilityAgentMessageReceiver = this.messageReceiversManager.createMessageReceiver(new AlexaCapabilityAgentV1(this));
        return this.capabilityAgentMessageReceiver.getMessenger().getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        this.messageReceiversManager = new MessageReceiversManager(new SignatureVerifier(this));
    }

    @Override // android.app.Service
    public void onDestroy() {
        MessageReceiver<ab> messageReceiver = this.capabilityAgentMessageReceiver;
        if (messageReceiver != null) {
            this.messageReceiversManager.removeMessageReceiver(messageReceiver);
        }
        this.messageReceiversManager.clear();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        doUnbind();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @UiThread
    public boolean preprocess(AlexaDirective alexaDirective) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @UiThread
    public abstract boolean process(AlexaDirective alexaDirective);
}
