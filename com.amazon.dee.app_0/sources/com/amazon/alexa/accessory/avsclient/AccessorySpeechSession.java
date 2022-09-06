package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Sink;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class AccessorySpeechSession implements SpeechSession {
    private final List<SpeechSession.SpeechSessionCallback> callbackList;
    private boolean endpointed;
    private final Sink sink;
    private boolean terminated;

    public AccessorySpeechSession(Sink sink) {
        Preconditions.notNull(sink, "sink");
        this.sink = sink;
        this.callbackList = new ArrayList();
    }

    private void notifyEndpointed() {
        for (int size = this.callbackList.size() - 1; size >= 0; size--) {
            this.callbackList.get(size).onEndpointSpeech(this);
        }
    }

    private void notifyError(String str) {
        for (int size = this.callbackList.size() - 1; size >= 0; size--) {
            this.callbackList.get(size).onError(this, new IOException(str));
        }
    }

    private void notifyReleased() {
        for (int size = this.callbackList.size() - 1; size >= 0; size--) {
            this.callbackList.get(size).onRelease(this);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void abort() {
        Preconditions.mainThread();
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        notifyError("Speech session is aborted");
        Logger.d("Speech session is aborted");
        IOUtils.closeQuietly(this.sink);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void addCallback(SpeechSession.SpeechSessionCallback speechSessionCallback) {
        Preconditions.notNull(speechSessionCallback, "speechSessionCallback");
        Preconditions.mainThread();
        this.callbackList.add(speechSessionCallback);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void endpointSpeech() {
        Preconditions.mainThread();
        Logger.d("AccessorySpeechSession: endpointSpeech: terminated=%b, endpointed=%b", Boolean.valueOf(this.terminated), Boolean.valueOf(this.endpointed));
        if (this.terminated || this.endpointed) {
            return;
        }
        this.endpointed = true;
        Logger.d("Speech session is endpointed");
        notifyEndpointed();
        try {
            this.sink.flush();
        } catch (IOException e) {
            Logger.d("Failed to flush voice pipe", e);
        }
        IOUtils.closeQuietly(this.sink);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public Sink getSink() {
        return this.sink;
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void release() {
        Preconditions.mainThread();
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        if (!this.endpointed) {
            notifyError("Speech session was released without endpoint");
        } else {
            notifyReleased();
        }
        Logger.d("Speech session is released");
        IOUtils.closeQuietly(this.sink);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void removeCallback(SpeechSession.SpeechSessionCallback speechSessionCallback) {
        Preconditions.notNull(speechSessionCallback, "speechSessionCallback");
        Preconditions.mainThread();
        this.callbackList.remove(speechSessionCallback);
    }
}
