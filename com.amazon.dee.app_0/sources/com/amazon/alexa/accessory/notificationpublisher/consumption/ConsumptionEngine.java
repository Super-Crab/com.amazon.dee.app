package com.amazon.alexa.accessory.notificationpublisher.consumption;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.timers.TimerManager;
import com.amazon.alexa.accessory.notificationpublisher.transcriber.SpeechRecognizerComponent;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class ConsumptionEngine {
    private static final String TAG = "ConsumptionEngine";
    public static final int THREAD_TYPE_INCOMING = 1;
    public static final int THREAD_TYPE_OUTGOING = 2;
    private static ConsumptionEngine instance;
    private GestureManager gestureManagerInstance;
    private IncomingMessageThread incomingMessageThread;
    private OutgoingMessageThread outgoingMessageThread;
    private NotificationQueue queueInstance;
    private RenderManager renderManagerInstance;
    private ReplyReadBackComponent replyReadBackComponent;
    private SpeechRecognizerComponent speechRecognizerInstance;
    private StateManager stateManagerInstance;
    private TimerManager timerManagerInstance;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IncomingMessageThread extends HandlerThread {
        Handler handler;

        IncomingMessageThread(String str, int i) {
            super(str, i);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            this.handler = new Handler(getLooper()) { // from class: com.amazon.alexa.accessory.notificationpublisher.consumption.ConsumptionEngine.IncomingMessageThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    try {
                        String str = ConsumptionEngine.TAG;
                        Log.d(str, "handleIncomingMessage " + message.toString());
                        int componentId = MessageUtils.getComponentId(message);
                        int eventId = MessageUtils.getEventId(message);
                        Object payload = MessageUtils.getPayload(message);
                        switch (componentId) {
                            case 1:
                                ConsumptionEngine.this.stateManagerInstance.handleNotificationEvent(eventId, payload);
                                break;
                            case 2:
                                ConsumptionEngine.this.stateManagerInstance.handleGestureEvent(eventId, payload);
                                break;
                            case 3:
                                ConsumptionEngine.this.stateManagerInstance.handleRendererEvent(eventId, payload);
                                break;
                            case 4:
                                ConsumptionEngine.this.stateManagerInstance.handleTimerEvent(eventId, payload);
                                break;
                            case 5:
                            default:
                                String str2 = ConsumptionEngine.TAG;
                                Log.w(str2, "IncomingMessageThread - No event handler for this component: " + componentId);
                                break;
                            case 6:
                                ConsumptionEngine.this.stateManagerInstance.handleStatusEvent(eventId, payload);
                                break;
                            case 7:
                                ConsumptionEngine.this.stateManagerInstance.handleAudioFocusEvent(eventId, payload);
                                break;
                            case 8:
                                ConsumptionEngine.this.stateManagerInstance.handleSpeechRecognizerEvent(eventId, payload);
                                break;
                            case 9:
                                ConsumptionEngine.this.stateManagerInstance.handleReplyReadBackEvent(eventId, payload);
                                break;
                        }
                    } catch (Exception unused) {
                        Log.e(ConsumptionEngine.TAG, "IncomingMessageThread - Exception caught in message handling");
                        StateManager.getInstance().transitState(StateManager.STATE_IDLE);
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR_INCOMING_THREAD);
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class OutgoingMessageThread extends HandlerThread {
        Handler handler;

        OutgoingMessageThread(String str, int i) {
            super(str, i);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            this.handler = new Handler(getLooper()) { // from class: com.amazon.alexa.accessory.notificationpublisher.consumption.ConsumptionEngine.OutgoingMessageThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    try {
                        String str = ConsumptionEngine.TAG;
                        Log.d(str, "handleOutgoingMessage " + message.toString());
                        int componentId = MessageUtils.getComponentId(message);
                        int eventId = MessageUtils.getEventId(message);
                        Object payload = MessageUtils.getPayload(message);
                        if (componentId == 2) {
                            ConsumptionEngine.this.gestureManagerInstance.handleEventMessage(eventId, payload);
                        } else if (componentId == 3) {
                            ConsumptionEngine.this.renderManagerInstance.handleEventMessage(eventId, payload);
                        } else if (componentId == 4) {
                            ConsumptionEngine.this.timerManagerInstance.handleEventMessage(eventId, payload);
                        } else if (componentId == 8) {
                            ConsumptionEngine.this.speechRecognizerInstance.handleEventMessage(eventId, payload);
                        } else if (componentId != 9) {
                            String str2 = ConsumptionEngine.TAG;
                            Log.w(str2, "OutgoingMessageThread - No event handler for this component: " + componentId);
                        } else {
                            ConsumptionEngine.this.replyReadBackComponent.handleEventMessage(eventId, payload);
                        }
                    } catch (Exception unused) {
                        Log.e(ConsumptionEngine.TAG, "OutgoingMessageThread - Exception caught in message handling");
                        StateManager.getInstance().transitState(StateManager.STATE_IDLE);
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR_OUTGOING_THREAD);
                    }
                }
            };
        }
    }

    private ConsumptionEngine() {
        Log.d(TAG, "ConsumptionEngine constructor");
        this.incomingMessageThread = new IncomingMessageThread("IncomingMessageThread", 10);
        this.incomingMessageThread.start();
        this.outgoingMessageThread = new OutgoingMessageThread("OutgoingMessageThread", 10);
        this.outgoingMessageThread.start();
        this.queueInstance = NotificationQueue.getInstance();
        this.stateManagerInstance = StateManager.getInstance();
        this.timerManagerInstance = TimerManager.getInstance();
        this.renderManagerInstance = RenderManager.getInstance();
        this.gestureManagerInstance = GestureManager.getInstance();
        this.speechRecognizerInstance = SpeechRecognizerComponent.getInstance();
        this.replyReadBackComponent = ReplyReadBackComponent.getInstance();
    }

    public static synchronized ConsumptionEngine getInstance() {
        ConsumptionEngine consumptionEngine;
        synchronized (ConsumptionEngine.class) {
            if (instance == null) {
                instance = new ConsumptionEngine();
            }
            consumptionEngine = instance;
        }
        return consumptionEngine;
    }

    public static synchronized void releaseInstance() {
        synchronized (ConsumptionEngine.class) {
            if (instance != null) {
                instance.incomingMessageThread.quit();
                instance.outgoingMessageThread.quit();
                instance.queueInstance = null;
                instance.stateManagerInstance = null;
                instance.incomingMessageThread = null;
                instance.outgoingMessageThread = null;
                instance.timerManagerInstance = null;
                instance.renderManagerInstance = null;
                instance.gestureManagerInstance = null;
                instance.speechRecognizerInstance = null;
                instance.replyReadBackComponent = null;
            }
            instance = null;
        }
    }

    public boolean postEventMessage(Message message, int i) {
        if (i == 2) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("status: ");
            outline107.append(this.outgoingMessageThread.getState().name());
            Log.d(str, outline107.toString());
            return this.outgoingMessageThread.handler.sendMessage(message);
        } else if (i == 1) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("status: ");
            outline1072.append(this.incomingMessageThread.getState().name());
            Log.d(str2, outline1072.toString());
            return this.incomingMessageThread.handler.sendMessage(message);
        } else {
            Log.w(TAG, "Invalid Thread Type");
            return false;
        }
    }

    public boolean postEventMessageDelayed(Message message, int i, long j) {
        String str = TAG;
        Log.i(str, "postEventMessageDelayed: " + j);
        if (i == 2) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("status: ");
            outline107.append(this.outgoingMessageThread.getState().name());
            Log.d(str2, outline107.toString());
            return this.outgoingMessageThread.handler.sendMessageDelayed(message, j);
        } else if (i == 1) {
            String str3 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("status: ");
            outline1072.append(this.incomingMessageThread.getState().name());
            Log.d(str3, outline1072.toString());
            return this.incomingMessageThread.handler.sendMessageDelayed(message, j);
        } else {
            Log.w(TAG, "Invalid Thread Type");
            return false;
        }
    }

    public void removeMessages(int i, int i2) {
        if (i2 == 2) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("status: ");
            outline107.append(this.outgoingMessageThread.getState().name());
            Log.d(str, outline107.toString());
            this.outgoingMessageThread.handler.removeMessages(i);
        } else if (i2 == 1) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("status: ");
            outline1072.append(this.incomingMessageThread.getState().name());
            Log.d(str2, outline1072.toString());
            this.incomingMessageThread.handler.removeMessages(i);
        } else {
            Log.w(TAG, "Invalid Thread Type");
        }
    }

    /* renamed from: clone */
    public ConsumptionEngine m335clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }

    public boolean postEventMessage(int i, int i2, int i3, @Nullable Object obj) {
        try {
            return postEventMessage(MessageUtils.createMessage(i2, i3, obj), i);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid messageType.", e);
            return false;
        }
    }

    public boolean postEventMessageDelayed(int i, int i2, int i3, @Nullable Object obj, long j) {
        try {
            return postEventMessageDelayed(MessageUtils.createMessage(i2, i3, obj), i, j);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid messageType.", e);
            return false;
        }
    }
}
