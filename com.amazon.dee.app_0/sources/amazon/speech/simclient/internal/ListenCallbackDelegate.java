package amazon.speech.simclient.internal;

import amazon.speech.simclient.IListenCallback;
import amazon.speech.simclient.ISimListenCallback;
import android.os.Handler;
import android.os.RemoteException;
/* loaded from: classes.dex */
public class ListenCallbackDelegate {
    final ISimListenCallback mCallback;
    final Handler mHandler;
    private final OnErrorRunnable mOnErrorRunnable = new OnErrorRunnable();
    private final OnFinishedRunnable mOnFinishedRunnable = new OnFinishedRunnable();
    private final OnStartedRunnable mOnStartedRunnable = new OnStartedRunnable();
    private final IListenCallback mBinderCallback = new IListenCallback.Stub() { // from class: amazon.speech.simclient.internal.ListenCallbackDelegate.1
        @Override // amazon.speech.simclient.IListenCallback
        public void onListenError(int i) throws RemoteException {
            ListenCallbackDelegate.this.mOnErrorRunnable.code = i;
            ListenCallbackDelegate listenCallbackDelegate = ListenCallbackDelegate.this;
            listenCallbackDelegate.mHandler.post(listenCallbackDelegate.mOnErrorRunnable);
        }

        @Override // amazon.speech.simclient.IListenCallback
        public void onListenFinished() throws RemoteException {
            ListenCallbackDelegate listenCallbackDelegate = ListenCallbackDelegate.this;
            listenCallbackDelegate.mHandler.post(listenCallbackDelegate.mOnFinishedRunnable);
        }

        @Override // amazon.speech.simclient.IListenCallback
        public void onListenStarted() throws RemoteException {
            ListenCallbackDelegate listenCallbackDelegate = ListenCallbackDelegate.this;
            listenCallbackDelegate.mHandler.post(listenCallbackDelegate.mOnStartedRunnable);
        }
    };

    /* loaded from: classes.dex */
    class OnErrorRunnable implements Runnable {
        int code;

        OnErrorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenCallbackDelegate.this.mCallback.onListenError(this.code);
        }
    }

    /* loaded from: classes.dex */
    class OnFinishedRunnable implements Runnable {
        OnFinishedRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenCallbackDelegate.this.mCallback.onListenFinished();
        }
    }

    /* loaded from: classes.dex */
    class OnStartedRunnable implements Runnable {
        OnStartedRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenCallbackDelegate.this.mCallback.onListenStarted();
        }
    }

    public ListenCallbackDelegate(ISimListenCallback iSimListenCallback, Handler handler) {
        if (handler != null) {
            if (iSimListenCallback != null) {
                this.mCallback = iSimListenCallback;
                this.mHandler = handler;
                return;
            }
            throw new IllegalArgumentException("listenCallback must be non-null");
        }
        throw new IllegalArgumentException("Handler must be non-null.");
    }

    public IListenCallback getBinderCallback() {
        return this.mBinderCallback;
    }
}
