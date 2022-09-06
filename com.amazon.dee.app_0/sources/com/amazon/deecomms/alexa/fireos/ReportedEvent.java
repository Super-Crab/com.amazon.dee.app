package com.amazon.deecomms.alexa.fireos;

import amazon.speech.simclient.IEventStatusCallback;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.InCallEvent;
import com.amazon.deecomms.common.Constants;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class ReportedEvent implements IEventStatusCallback {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ReportedEvent.class);
    private final InCallEvent event;
    private final AlexaInterface mAlexaInterface;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReportedEvent(@NonNull InCallEvent inCallEvent, @NonNull AlexaInterface alexaInterface) {
        this.event = inCallEvent;
        this.mAlexaInterface = alexaInterface;
    }

    @Override // amazon.speech.simclient.IEventStatusCallback
    public synchronized void onError(final String str, final int i) {
        CommsLogger commsLogger = LOG;
        commsLogger.e("There was an error while sending the event : " + str + " error code : " + i);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.alexa.fireos.ReportedEvent.1
            @Override // java.lang.Runnable
            public void run() {
                if (ReportedEvent.this.mAlexaInterface instanceof SimClientAlexaInterface) {
                    for (SimEventListener simEventListener : ((SimClientAlexaInterface) ReportedEvent.this.mAlexaInterface).getSimEventListeners()) {
                        simEventListener.onError(ReportedEvent.this.event, i, str);
                    }
                }
            }
        });
    }

    @Override // amazon.speech.simclient.IEventStatusCallback
    public synchronized void onFinish() {
        LOG.i("Sending event finished");
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.alexa.fireos.ReportedEvent.2
            @Override // java.lang.Runnable
            public void run() {
                ReportedEvent.LOG.i(String.format("SipClient Message posted successfully: event:%s, data=%s", ReportedEvent.this.event, ReportedEvent.this.event.getData()));
                if (ReportedEvent.this.mAlexaInterface instanceof SimClientAlexaInterface) {
                    for (SimEventListener simEventListener : ((SimClientAlexaInterface) ReportedEvent.this.mAlexaInterface).getSimEventListeners()) {
                        simEventListener.onSuccess(ReportedEvent.this.event);
                    }
                }
            }
        });
    }
}
