package amazon.speech.simclient.focus;

import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.ServiceConnectionManager;
import amazon.speech.simclient.focus.IAlexaFocusContract;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
/* loaded from: classes.dex */
public class ImplicitFocusClient extends BaseClient<IAlexaFocusContract> {
    static final Intent BIND_INTENT = new Intent(AlexaFocusBinding.BIND_ACTION);
    public static final ImplicitFocusClientHandle HANDLE = new ImplicitFocusClientHandle();
    private static final String TAG = "ImplicitFocusClient";
    private final IBinder mBinder;

    public ImplicitFocusClient(Context context) {
        this(context, null);
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

    public static boolean isFocusServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA");
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<IAlexaFocusContract>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<IAlexaFocusContract>.BaseClientServiceConnection() { // from class: amazon.speech.simclient.focus.ImplicitFocusClient.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService */
            public IAlexaFocusContract mo49convertIBinderToService(IBinder iBinder) {
                return IAlexaFocusContract.Stub.asInterface(iBinder);
            }
        };
    }

    public boolean setActiveNamespace(String str) {
        try {
            getService().setActiveNamespace(str);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException on acquireFocus attempt", e);
            return false;
        }
    }

    public ImplicitFocusClient(Context context, UnbindBehavior unbindBehavior) {
        this(context, BIND_INTENT, "amazon.speech.permission.SEND_DATA_TO_ALEXA", new AsyncServiceConnectionManager(), unbindBehavior);
    }

    ImplicitFocusClient(Context context, Intent intent, String str) {
        this(context, intent, str, new AsyncServiceConnectionManager(), null);
    }

    ImplicitFocusClient(Context context, Intent intent, String str, ServiceConnectionManager serviceConnectionManager, UnbindBehavior unbindBehavior) {
        super(IAlexaFocusContract.class, context, intent, str, serviceConnectionManager);
        this.mBinder = new Binder();
        attachClientInstance(unbindBehavior);
    }
}
