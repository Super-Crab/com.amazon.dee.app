package amazon.speech.simclient.internal;

import amazon.speech.model.DirectiveEnvelope;
import amazon.speech.model.Event;
import amazon.speech.model.IEventCallback;
import amazon.speech.simclient.ISIMEventCallback;
import android.os.Handler;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public class EventCallbackDelegate {
    final ISIMEventCallback mCallback;
    final Event mEvent;
    final Handler mHandler;
    final OnErrorRunnable mOnErrorRunnable = new OnErrorRunnable();
    final OnResponseRunnable mOnResponseRunnable = new OnResponseRunnable();
    private final IEventCallback mBinderCallback = new IEventCallback.Stub() { // from class: amazon.speech.simclient.internal.EventCallbackDelegate.1
        @Override // amazon.speech.model.IEventCallback
        public boolean isLocal() {
            return false;
        }

        @Override // amazon.speech.model.IEventCallback
        public void onError(int i) throws RemoteException {
            EventCallbackDelegate eventCallbackDelegate = EventCallbackDelegate.this;
            OnErrorRunnable onErrorRunnable = eventCallbackDelegate.mOnErrorRunnable;
            onErrorRunnable.code = i;
            eventCallbackDelegate.mHandler.post(onErrorRunnable);
        }

        @Override // amazon.speech.model.IEventCallback
        public void onResponse(List<DirectiveEnvelope> list) throws RemoteException {
            EventCallbackDelegate eventCallbackDelegate = EventCallbackDelegate.this;
            OnResponseRunnable onResponseRunnable = eventCallbackDelegate.mOnResponseRunnable;
            onResponseRunnable.directives = list;
            eventCallbackDelegate.mHandler.post(onResponseRunnable);
        }
    };

    /* loaded from: classes.dex */
    class OnErrorRunnable implements Runnable {
        int code;

        OnErrorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            EventCallbackDelegate eventCallbackDelegate = EventCallbackDelegate.this;
            eventCallbackDelegate.mCallback.onError(eventCallbackDelegate.mEvent, this.code);
        }
    }

    /* loaded from: classes.dex */
    class OnResponseRunnable implements Runnable {
        List<DirectiveEnvelope> directives;

        OnResponseRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            EventCallbackDelegate eventCallbackDelegate = EventCallbackDelegate.this;
            eventCallbackDelegate.mCallback.onResponse(eventCallbackDelegate.mEvent, this.directives);
        }
    }

    public EventCallbackDelegate(Event event, ISIMEventCallback iSIMEventCallback, Handler handler) {
        if (handler != null) {
            if (iSIMEventCallback != null) {
                this.mEvent = event;
                this.mCallback = iSIMEventCallback;
                this.mHandler = handler;
                return;
            }
            throw new IllegalArgumentException("eventCallback must be non-null");
        }
        throw new IllegalArgumentException("Handler must be non-null.");
    }

    public IEventCallback getBinderCallback() {
        return this.mBinderCallback;
    }
}
