package com.amazon.alexa.audiopersonalization.listeners;

import android.util.Log;
import com.amazon.alexa.audiopersonalization.components.Tone;
import com.amazon.alexa.audiopersonalization.components.TonePlayer;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.audiopersonalization.factory.TonePlayerFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
/* loaded from: classes6.dex */
public class ToneEventListener extends AbstractEventListener {
    private static final String TAG = "ToneEventListener";
    private boolean isActive;
    private final JSONObjectFactory mJsonFactory;
    private TonePlayer mTonePlayer;
    private final TonePlayerFactory mTonePlayerFactory;

    public ToneEventListener(EventBus eventBus, JSONObjectFactory jSONObjectFactory, TonePlayerFactory tonePlayerFactory) {
        super(eventBus, jSONObjectFactory);
        this.mJsonFactory = jSONObjectFactory;
        this.mTonePlayerFactory = tonePlayerFactory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartEventReceived(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline158("Received enhancedMedia::tone::play with payload ", payloadAsString);
        try {
            Tone fromJSONObject = Tone.fromJSONObject(this.mJsonFactory.createJSONObject(payloadAsString));
            TonePlayer tonePlayer = this.mTonePlayer;
            if (tonePlayer == null) {
                return;
            }
            tonePlayer.play(fromJSONObject);
        } catch (JSONException unused) {
            Log.e(TAG, "Could not parse tone start JSON");
            sendErrorMsg(EventBusConstants.EVENT_TYPE_TONE_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStopEventReceived(Message message) {
        GeneratedOutlineSupport1.outline158("Received enhancedMedia::tone::stop with payload ", message.getPayloadAsString());
        TonePlayer tonePlayer = this.mTonePlayer;
        if (tonePlayer != null) {
            tonePlayer.stop();
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void start() {
        stop();
        this.mTonePlayer = this.mTonePlayerFactory.createTonePlayer();
        subscribeToEvent(EventBusConstants.EVENT_TYPE_TONE_PLAY, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$ToneEventListener$Rbry0t_Fse6bEIzjlJrtidya5mQ
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ToneEventListener.this.onStartEventReceived(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_TONE_STOP, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$ToneEventListener$mj0wZQxXkHKkPXlN0bLD_iWh-xU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ToneEventListener.this.onStopEventReceived(message);
            }
        });
        this.isActive = true;
    }

    @Override // com.amazon.alexa.audiopersonalization.listeners.AbstractEventListener
    public void stop() {
        super.stop();
        this.isActive = false;
        TonePlayer tonePlayer = this.mTonePlayer;
        if (tonePlayer != null) {
            tonePlayer.shutdown();
            this.mTonePlayer = null;
        }
    }
}
