package com.amazon.alexa.audiopersonalization.listeners;

import amazon.speech.simclient.metrics.upl.UPLConstants;
import android.annotation.SuppressLint;
import android.util.Log;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.AudioProfileGsonWrapper;
import com.amazon.alexa.audiopersonalization.api.CompletableDelegate;
import com.amazon.alexa.audiopersonalization.api.ErrorDelegate;
import com.amazon.alexa.audiopersonalization.api.SuccessDelegate;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
/* loaded from: classes6.dex */
public class AssessmentEventListener extends AbstractEventListener {
    private static final String TAG = "AssessmentEventListener";
    private final AmaApi mAmaApi;
    private ToneEventListener toneEventListener;

    public AssessmentEventListener(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory, ToneEventListener toneEventListener) {
        super(eventBus, jSONObjectFactory);
        this.mAmaApi = amaApi;
        this.toneEventListener = toneEventListener;
    }

    @SuppressFBWarnings
    private Hearing.Audiogram getAMAAudioGramFromWrapper(AudioProfileGsonWrapper.AudioChannel[] audioChannelArr) {
        if (audioChannelArr == null) {
            Log.e(TAG, "Audiogram entry in gson wrapper is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AudioProfileGsonWrapper.AudioChannel audioChannel : audioChannelArr) {
            if (audioChannel != null && audioChannel.bands != null) {
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                while (true) {
                    AudioProfileGsonWrapper.Band[] bandArr = audioChannel.bands;
                    if (i >= bandArr.length) {
                        break;
                    }
                    arrayList2.add(Hearing.AudioBand.newBuilder().setFc(bandArr[i].frequency.intValue()).setValue(audioChannel.bands[i].threshold.floatValue()).mo10084build());
                    i++;
                }
                Hearing.AudioChannelType audioChannelType = getAudioChannelType(audioChannel.type);
                if (audioChannelType == Hearing.AudioChannelType.UNRECOGNIZED) {
                    return null;
                }
                arrayList.add(Hearing.AudioChannel.newBuilder().setType(audioChannelType).addAllBands(arrayList2).mo10084build());
            } else {
                Log.e(TAG, "Channel or channel bands is null");
                return null;
            }
        }
        return Hearing.Audiogram.newBuilder().addAllAudiogram(arrayList).mo10084build();
    }

    @SuppressLint({"DefaultLocale"})
    private Hearing.AudioChannelType getAudioChannelType(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("left")) {
            return Hearing.AudioChannelType.LEFT;
        }
        if (lowerCase.equals("right")) {
            return Hearing.AudioChannelType.RIGHT;
        }
        if (!lowerCase.equals(UPLConstants.Channel.BOTH) && !lowerCase.equals("all")) {
            return Hearing.AudioChannelType.UNRECOGNIZED;
        }
        return Hearing.AudioChannelType.ALL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void listenForAbort(String str) {
        this.mAmaApi.subscribeToAudioAssessmentAbort(str, new CompletableDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.AssessmentEventListener.3
            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onComplete() {
                Log.i(AssessmentEventListener.TAG, "received an assessment abort msg, sending an error");
                AssessmentEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            }

            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onError() {
                Log.e(AssessmentEventListener.TAG, "Got an error emitted while listening for abort");
                AssessmentEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteAudioProfile(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Received request to delete audio profile with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
        } else {
            setAssessmentMode(0, null, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPauseAssessmentModeReceived(Message message) {
        Log.i(TAG, "Receive a pause assessment msg.");
        final String str = (String) parsePayload(message.getPayloadAsString(), "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            return;
        }
        stopListeningForAbort(str);
        setAssessmentMode(3, new Runnable() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$rdIKIIrR9WU7JZFNYVsPJhpSHgU
            @Override // java.lang.Runnable
            public final void run() {
                AssessmentEventListener.this.lambda$onPauseAssessmentModeReceived$0$AssessmentEventListener(str);
            }
        }, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResumeAssessmentModeReceived(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Receive resume assessment mode message with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
        } else {
            setAssessmentMode(1, null, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetAssessmentModeReceived(Message message) {
        String payloadAsString = message.getPayloadAsString();
        Integer num = (Integer) parsePayload(payloadAsString, "status", Integer.class);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str != null && num != null) {
            setAssessmentMode(num.intValue(), null, str);
        } else {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetAudioProfile(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Receive set audio profile message with payload: ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            return;
        }
        Hearing.Audiogram aMAAudioGramFromWrapper = getAMAAudioGramFromWrapper((AudioProfileGsonWrapper.AudioChannel[]) parsePayload(payloadAsString, EventBusConstants.JSON_KEY_AUDIO_PROFILE, AudioProfileGsonWrapper.AudioChannel[].class));
        if (aMAAudioGramFromWrapper == null) {
            Log.e(TAG, "Could not parse audio profile, returning without calling AMA.");
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            return;
        }
        this.mAmaApi.setAudioProfile(aMAAudioGramFromWrapper, str, new CompletableDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.AssessmentEventListener.2
            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onComplete() {
                AssessmentEventListener.this.sendEventBusMessage(EventBusConstants.EVENT_TYPE_SET_AUDIO_PROFILE_SUCCESS);
                Log.i(AssessmentEventListener.TAG, "Set audio profile complete");
            }

            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onError() {
                Log.e(AssessmentEventListener.TAG, "Received an error when attempting to set audio profile");
                AssessmentEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAssessmentMode(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Starting request for current assessment mode with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
        } else {
            this.mAmaApi.getAssessmentMode(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$gH65aIFoetKUsRUDjcISgPByBO8
                @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
                public final void onSuccess(Object obj) {
                    AssessmentEventListener.this.lambda$sendAssessmentMode$1$AssessmentEventListener((Integer) obj);
                }
            }, new ErrorDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$xLYnhZw1ZLVF4gjmetAsAukG328
                @Override // com.amazon.alexa.audiopersonalization.api.ErrorDelegate
                public final void onError() {
                    AssessmentEventListener.this.lambda$sendAssessmentMode$2$AssessmentEventListener();
                }
            });
        }
    }

    private void setAssessmentMode(final int i, @Nullable final Runnable runnable, final String str) {
        this.mAmaApi.setAssessmentMode(i, str, new CompletableDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.AssessmentEventListener.1
            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onComplete() {
                if (i == 1) {
                    Log.i(AssessmentEventListener.TAG, "inside started assessment, should set tone event to start");
                    AssessmentEventListener.this.toneEventListener.start();
                    AssessmentEventListener.this.listenForAbort(str);
                } else {
                    AssessmentEventListener.this.toneEventListener.stop();
                    AssessmentEventListener.this.stopListeningForAbort(str);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                String str2 = AssessmentEventListener.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully set assessment mode to ");
                outline107.append(i);
                Log.i(str2, outline107.toString());
            }

            @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
            public void onError() {
                Log.e(AssessmentEventListener.TAG, "Received an error in setAssessmentMode");
                AssessmentEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListeningForAbort(String str) {
        this.mAmaApi.unsubscribeToAudioAssessmentAbort(str);
    }

    public /* synthetic */ void lambda$onPauseAssessmentModeReceived$0$AssessmentEventListener(String str) {
        setAssessmentMode(0, null, str);
    }

    public /* synthetic */ void lambda$sendAssessmentMode$1$AssessmentEventListener(Integer num) {
        String str = TAG;
        Log.i(str, "Successfully retrieved assessment mode. Value: " + num);
        String createPayload = createPayload(EventBusConstants.JSON_KEY_ASSESSMENT_MODE, num);
        if (createPayload == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
            return;
        }
        if (num.intValue() == 1 && !this.toneEventListener.isActive()) {
            this.toneEventListener.start();
        } else if (num.intValue() != 1 && this.toneEventListener.isActive()) {
            this.toneEventListener.stop();
        }
        sendEventBusMessage(EventBusConstants.EVENT_TYPE_ASSESSMENT_MODE_RESULT, createPayload);
    }

    public /* synthetic */ void lambda$sendAssessmentMode$2$AssessmentEventListener() {
        sendErrorMsg(EventBusConstants.EVENT_TYPE_ASSESSMENT_ERROR);
    }

    public void start() {
        stop();
        subscribeToEvent(EventBusConstants.EVENT_TYPE_SET_ASSESSMENT_MODE, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$mQyTUkHQIfveUzvHHrAWrmmfjVA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.onSetAssessmentModeReceived(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_GET_ASSESSMENT_MODE, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$Rq6RR3yr2i9jLPhniloFAZR6z6A
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.sendAssessmentMode(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_SET_AUDIO_PROFILE, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$3vKEMNCd7vt7B4eyuewHhyjz_FE
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.onSetAudioProfile(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_DELETE_AUDIO_PROFILE, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$1IVl0zKUkWXBcgNyp9IlNQttEmo
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.onDeleteAudioProfile(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_PAUSE_ASSESSMENT, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$nGvr-VtYhvnlPrxZ7mc_pjTlu30
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.onPauseAssessmentModeReceived(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_RESUME_ASSESSMENT, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AssessmentEventListener$Bomu0Iie8KQFTDR1qlilRAEwPzw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AssessmentEventListener.this.onResumeAssessmentModeReceived(message);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.listeners.AbstractEventListener
    @OverridingMethodsMustInvokeSuper
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
