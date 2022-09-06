package amazon.speech.simclient.focus;

import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.HandlerWrapper;
import amazon.speech.simclient.common.ServiceConnectionManager;
import amazon.speech.simclient.focus.IAlexaFocusContract;
import amazon.speech.simclient.focus.IFocusChangedCallback;
import amazon.speech.simclient.focus.IFocusModificationCallback;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
/* loaded from: classes.dex */
public class ExplicitFocusClient extends BaseClient<IAlexaFocusContract> {
    private static final boolean CLEAR_FOCUS = true;
    private static final boolean RELEASE_FOCUS = false;
    private static final String TAG = "ExplicitFocusClient";
    private final IBinder mBinder;
    private final HandlerWrapper mHandler;
    static final Intent BIND_INTENT = new Intent(AlexaFocusBinding.BIND_ACTION);
    public static final ExplicitFocusClientHandle HANDLE = new ExplicitFocusClientHandle();

    public ExplicitFocusClient(Context context) {
        this(context, new Handler(Looper.getMainLooper()), (UnbindBehavior) null);
    }

    private void attachClientInstance(UnbindBehavior unbindBehavior) {
        String name;
        if (unbindBehavior != null) {
            try {
                name = unbindBehavior.name();
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException registering client instance", e);
                return;
            }
        } else {
            name = null;
        }
        getService().attachClientInstance(this.mBinder, name);
    }

    private IFocusModificationCallback.Stub createBinderCallback(final FocusModificationCallback focusModificationCallback) {
        if (focusModificationCallback == null) {
            return null;
        }
        return new IFocusModificationCallback.Stub() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.2
            @Override // amazon.speech.simclient.focus.IFocusModificationCallback
            public void onFinished(String str) throws RemoteException {
                final FocusModificationResult focusModificationResult = FocusModificationResult.FAIL_INTERNAL_ERROR;
                try {
                    focusModificationResult = FocusModificationResult.valueOf(str);
                } catch (Exception e) {
                    Log.e(ExplicitFocusClient.TAG, String.format("Exception converting result to enum: %s", str), e);
                }
                ExplicitFocusClient.this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        focusModificationCallback.onFinished(focusModificationResult);
                    }
                });
            }
        };
    }

    public static boolean isFocusServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA");
    }

    private void postModificationCallback(final FocusModificationCallback focusModificationCallback, final FocusModificationResult focusModificationResult) {
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.5
            @Override // java.lang.Runnable
            public void run() {
                focusModificationCallback.onFinished(focusModificationResult);
            }
        });
    }

    public boolean acquireFocus(String str, int i) {
        try {
            getService().acquireFocus(str, i, this.mBinder);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on acquireFocus attempt", e);
            return false;
        }
    }

    public void acquireSystemVisualFocus(String str, int i, SystemVisualFocusResultCallback systemVisualFocusResultCallback) {
        try {
            getService().acquireSystemVisualFocus(str, i, this.mBinder, createBinderCallback(systemVisualFocusResultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on acquireSystemVisualFocus", e);
            if (systemVisualFocusResultCallback == null) {
                return;
            }
            postModificationCallback(systemVisualFocusResultCallback, SystemVisualFocusResult.FAIL_SERVICE_UNAVAILABLE);
        }
    }

    public boolean clearFocus(String str, int i) {
        try {
            getService().releaseFocus(str, i, this.mBinder, true);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on releaseFocus attempt", e);
            return false;
        }
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<IAlexaFocusContract>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<IAlexaFocusContract>.BaseClientServiceConnection() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public IAlexaFocusContract mo49convertIBinderToService(IBinder iBinder) {
                return IAlexaFocusContract.Stub.asInterface(iBinder);
            }
        };
    }

    public boolean releaseFocus(String str, int i) {
        try {
            getService().releaseFocus(str, i, this.mBinder, false);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on releaseFocus attempt", e);
            return false;
        }
    }

    public void releaseSystemVisualFocus(String str, int i, SystemVisualFocusResultCallback systemVisualFocusResultCallback) {
        try {
            getService().releaseSystemVisualFocus(str, i, this.mBinder, createBinderCallback(systemVisualFocusResultCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on releaseSystemVisualFocus", e);
            if (systemVisualFocusResultCallback == null) {
                return;
            }
            postModificationCallback(systemVisualFocusResultCallback, SystemVisualFocusResult.FAIL_SERVICE_UNAVAILABLE);
        }
    }

    public ExplicitFocusClient(Context context, UnbindBehavior unbindBehavior) {
        this(context, new Handler(Looper.getMainLooper()), unbindBehavior);
    }

    private IFocusChangedCallback.Stub createBinderCallback(final FocusChangedCallback focusChangedCallback) {
        if (focusChangedCallback == null) {
            return null;
        }
        return new IFocusChangedCallback.Stub() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.3
            @Override // amazon.speech.simclient.focus.IFocusChangedCallback
            public void onFocusChanged(final String str, String str2) throws RemoteException {
                final FocusChangeReason focusChangeReason = FocusChangeReason.FOCUS_CLEARED;
                try {
                    focusChangeReason = FocusChangeReason.valueOf(str2);
                } catch (Exception e) {
                    Log.e(ExplicitFocusClient.TAG, String.format("Exception converting reason to enum: %s", str2), e);
                }
                ExplicitFocusClient.this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        focusChangedCallback.onFocusChanged(str, focusChangeReason);
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postModificationCallback(final SystemVisualFocusResultCallback systemVisualFocusResultCallback, final SystemVisualFocusResult systemVisualFocusResult) {
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.6
            @Override // java.lang.Runnable
            public void run() {
                systemVisualFocusResultCallback.onFinished(systemVisualFocusResult);
            }
        });
    }

    public ExplicitFocusClient(Context context, Handler handler) {
        this(context, handler, (UnbindBehavior) null);
    }

    private IFocusModificationCallback createBinderCallback(final SystemVisualFocusResultCallback systemVisualFocusResultCallback) {
        if (systemVisualFocusResultCallback == null) {
            return null;
        }
        return new IFocusModificationCallback.Stub() { // from class: amazon.speech.simclient.focus.ExplicitFocusClient.4
            @Override // amazon.speech.simclient.focus.IFocusModificationCallback
            public void onFinished(String str) {
                SystemVisualFocusResult systemVisualFocusResult;
                try {
                    systemVisualFocusResult = SystemVisualFocusResult.valueOf(str);
                } catch (Exception e) {
                    Log.e(ExplicitFocusClient.TAG, String.format("Exception converting result to enum: %s", str), e);
                    systemVisualFocusResult = SystemVisualFocusResult.FAIL_UNEXPECTED_RESULT;
                }
                ExplicitFocusClient.this.postModificationCallback(systemVisualFocusResultCallback, systemVisualFocusResult);
            }
        };
    }

    public boolean acquireFocus(String str, int i, FocusModificationCallback focusModificationCallback) {
        try {
            getService().acquireFocusWithCallback(str, i, this.mBinder, createBinderCallback(focusModificationCallback), null);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on acquireFocus with callback", e);
            if (focusModificationCallback == null) {
                return false;
            }
            postModificationCallback(focusModificationCallback, FocusModificationResult.FAIL_INTERNAL_ERROR);
            return false;
        }
    }

    public boolean clearFocus(String str, int i, FocusModificationCallback focusModificationCallback) {
        try {
            getService().releaseFocusWithCallback(str, i, this.mBinder, createBinderCallback(focusModificationCallback), true);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on releaseFocus with callback", e);
            if (focusModificationCallback == null) {
                return false;
            }
            postModificationCallback(focusModificationCallback, FocusModificationResult.FAIL_INTERNAL_ERROR);
            return false;
        }
    }

    public boolean releaseFocus(String str, int i, FocusModificationCallback focusModificationCallback) {
        try {
            getService().releaseFocusWithCallback(str, i, this.mBinder, createBinderCallback(focusModificationCallback), false);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on releaseFocus with callback", e);
            if (focusModificationCallback == null) {
                return false;
            }
            postModificationCallback(focusModificationCallback, FocusModificationResult.FAIL_INTERNAL_ERROR);
            return false;
        }
    }

    public ExplicitFocusClient(Context context, Handler handler, UnbindBehavior unbindBehavior) {
        this(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA", new AsyncServiceConnectionManager(), new HandlerWrapper(handler), unbindBehavior);
    }

    ExplicitFocusClient(Context context, Intent intent, String str) {
        this(context, intent, str, new AsyncServiceConnectionManager(), new HandlerWrapper(new Handler(Looper.getMainLooper())), null);
    }

    ExplicitFocusClient(Context context, Intent intent, String str, ServiceConnectionManager serviceConnectionManager, HandlerWrapper handlerWrapper, UnbindBehavior unbindBehavior) {
        super(IAlexaFocusContract.class, context, intent, str, serviceConnectionManager);
        this.mBinder = new Binder();
        this.mHandler = handlerWrapper;
        attachClientInstance(unbindBehavior);
    }

    public boolean acquireFocus(String str, int i, FocusModificationCallback focusModificationCallback, FocusChangedCallback focusChangedCallback) {
        try {
            getService().acquireFocusWithCallback(str, i, this.mBinder, createBinderCallback(focusModificationCallback), createBinderCallback(focusChangedCallback));
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on acquireFocus with callback", e);
            if (focusModificationCallback == null) {
                return false;
            }
            postModificationCallback(focusModificationCallback, FocusModificationResult.FAIL_INTERNAL_ERROR);
            return false;
        }
    }
}
