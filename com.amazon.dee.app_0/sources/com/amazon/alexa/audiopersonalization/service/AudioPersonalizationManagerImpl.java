package com.amazon.alexa.audiopersonalization.service;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.accessoryclient.client.AlexaAccessoryClient;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.AudioPersonalizationManager;
import com.amazon.alexa.audiopersonalization.api.OnStart;
import com.amazon.alexa.audiopersonalization.components.AmaApiImpl;
import com.amazon.alexa.audiopersonalization.components.BudsSessionRetriever;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.audiopersonalization.factory.TonePlayerFactory;
import com.amazon.alexa.audiopersonalization.listeners.AccessoriesListener;
import com.amazon.alexa.audiopersonalization.listeners.AppStateListener;
import com.amazon.alexa.audiopersonalization.listeners.AssessmentEventListener;
import com.amazon.alexa.audiopersonalization.listeners.SettingsEventListener;
import com.amazon.alexa.audiopersonalization.listeners.ToneEventListener;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes6.dex */
public class AudioPersonalizationManagerImpl implements AudioPersonalizationManager {
    private static final String TAG = "AudioPersonalizationManagerImpl";
    private boolean hasStarted = false;

    private void startListeners(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory) {
        new SettingsEventListener(eventBus, amaApi, jSONObjectFactory).start();
        new AssessmentEventListener(eventBus, amaApi, jSONObjectFactory, new ToneEventListener(eventBus, jSONObjectFactory, new TonePlayerFactory())).start();
        new AccessoriesListener(eventBus, amaApi, jSONObjectFactory).start();
    }

    @Override // com.amazon.alexa.audiopersonalization.api.AudioPersonalizationManager
    public void init(Context context) {
        Log.i(TAG, "Initializing AudioPersonalizationManager");
        final EventBus eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).get();
        final JSONObjectFactory jSONObjectFactory = new JSONObjectFactory();
        BudsSessionRetriever budsSessionRetriever = new BudsSessionRetriever(new AlexaAccessoryClient(context));
        budsSessionRetriever.start();
        final AmaApiImpl amaApiImpl = new AmaApiImpl(budsSessionRetriever);
        new AppStateListener(eventBus, amaApiImpl, jSONObjectFactory).start(new OnStart() { // from class: com.amazon.alexa.audiopersonalization.service.-$$Lambda$AudioPersonalizationManagerImpl$3FOR29rif-SzZgmIWgkuJnHLSHc
            @Override // com.amazon.alexa.audiopersonalization.api.OnStart
            public final void onStart() {
                AudioPersonalizationManagerImpl.this.lambda$init$0$AudioPersonalizationManagerImpl(eventBus, amaApiImpl, jSONObjectFactory);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$AudioPersonalizationManagerImpl(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory) {
        if (!this.hasStarted) {
            this.hasStarted = true;
            startListeners(eventBus, amaApi, jSONObjectFactory);
        }
    }
}
