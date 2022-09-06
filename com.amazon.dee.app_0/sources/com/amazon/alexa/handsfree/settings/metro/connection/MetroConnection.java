package com.amazon.alexa.handsfree.settings.metro.connection;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.metro.connection.exception.RemoteServiceNotSetUpException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.IWakeWordSettings;
import com.magiear.handsfree.util.ParamDefinition;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class MetroConnection implements ServiceConnection {
    private static final String ERROR_CONNECTION = "Error on the connection.";
    private static final String TAG = MetroConnection.class.getSimpleName();
    private Context mContext;
    private Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private IWakeWordSettings mIWakeWordSettings;
    private boolean mIsSettingsServiceBound;

    public MetroConnection(@NonNull Context context) {
        this.mContext = context;
    }

    private void cleanupConnection(@Nullable IResultCallback iResultCallback, @Nullable Exception exc) {
        if (iResultCallback != null) {
            Bundle outline11 = GeneratedOutlineSupport1.outline11(ParamDefinition.KEY_CALLBACK_RESULT, "fail");
            String message = exc != null ? exc.getMessage() : null;
            if (message == null) {
                message = ERROR_CONNECTION;
            }
            outline11.putString(ParamDefinition.KEY_CALLBACK_ERROR_MSG, message);
            try {
                iResultCallback.onResult(outline11);
            } catch (RemoteException e) {
                Log.wtf(TAG, "RemoteException was thrown for a local object.", e);
            }
        }
        this.mIWakeWordSettings = null;
        this.mIsSettingsServiceBound = false;
    }

    private void verifyServiceConnected() throws RemoteServiceNotSetUpException {
        if (isServiceConnected()) {
            return;
        }
        Log.e(TAG, "Remote service not setup");
        throw new RemoteServiceNotSetUpException();
    }

    @Nullable
    public String getLocale(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            return this.mIWakeWordSettings.getLocale();
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(null, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "getLocale", e);
            return null;
        }
    }

    @Nullable
    public Bundle getWakeWordParam(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            return this.mIWakeWordSettings.getWakeWordParam();
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(null, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "getWakeWordParam", e);
            return null;
        }
    }

    public boolean isServiceConnected() {
        return this.mIsSettingsServiceBound;
    }

    public boolean isWakeWordRecognitionEnabled(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            return this.mIWakeWordSettings.isWakeWordRecognitionEnabled();
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(null, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "isWakeWordRecognitionEnabled", e);
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        this.mIWakeWordSettings = IWakeWordSettings.Stub.asInterface(iBinder);
        this.mIsSettingsServiceBound = true;
        this.mCrashReportRecorderLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoProtocolComponent.class)).crashReportRecorderLazy();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        cleanupConnection(null, null);
    }

    public void setLocale(@NonNull Context context, @NonNull String str, @NonNull IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            this.mIWakeWordSettings.setLocale(str, iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setLocale", e);
        }
    }

    public void setWakeWordParam(@NonNull Context context, @NonNull Bundle bundle, @NonNull IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            this.mIWakeWordSettings.setWakeWordParam(bundle, iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setWakeWordParam", e);
        }
    }

    public void setWakeWordRecognitionEnabled(@NonNull Context context, boolean z, @Nullable IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            this.mIWakeWordSettings.setWakeWordRecognitionEnabled(z, iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, ERROR_CONNECTION, e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setWakeWordRecognitionEnabled", e);
        }
    }

    @VisibleForTesting
    MetroConnection(@NonNull Context context, boolean z, @Nullable IWakeWordSettings iWakeWordSettings, @NonNull Lazy<CrashReportRecorder> lazy) {
        this.mContext = context;
        this.mIsSettingsServiceBound = z;
        this.mIWakeWordSettings = iWakeWordSettings;
        this.mCrashReportRecorderLazy = lazy;
    }
}
