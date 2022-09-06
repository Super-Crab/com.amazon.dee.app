package com.amazon.identity.auth.device.callback;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.IRemoteCallback;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class RemoteCallbackWrapper implements Parcelable, Callback {
    private static final int MAX_RETRIES = 1;
    private final IRemoteCallback mCallback;
    public static final Parcelable.Creator<RemoteCallbackWrapper> CREATOR = new Parcelable.Creator<RemoteCallbackWrapper>() { // from class: com.amazon.identity.auth.device.callback.RemoteCallbackWrapper.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: c */
        public RemoteCallbackWrapper createFromParcel(Parcel parcel) {
            return new RemoteCallbackWrapper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: h */
        public RemoteCallbackWrapper[] newArray(int i) {
            return new RemoteCallbackWrapper[i];
        }
    };
    private static final String TAG = RemoteCallbackWrapper.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a extends IRemoteCallback.Stub {
        private final Callback g;

        public a(Callback callback) {
            this.g = callback;
        }

        @Override // com.amazon.identity.auth.device.callback.IRemoteCallback
        public void onError(final Bundle bundle) throws RemoteException {
            if (this.g != null) {
                ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.callback.RemoteCallbackWrapper.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.g.onError(bundle);
                    }
                });
            }
        }

        @Override // com.amazon.identity.auth.device.callback.IRemoteCallback
        public void onSuccess(final Bundle bundle) throws RemoteException {
            if (this.g != null) {
                ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.callback.RemoteCallbackWrapper.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.g.onSuccess(bundle);
                    }
                });
            }
        }
    }

    public RemoteCallbackWrapper(Callback callback) {
        this(new a(callback));
    }

    public static IRemoteCallback toRemoteCallback(Callback callback) {
        return new a(callback);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        boolean z = false;
        int i = 0;
        while (!z && i <= 1) {
            i++;
            try {
                if (this.mCallback != null) {
                    this.mCallback.onError(bundle);
                } else {
                    io.e(TAG, "Not calling onError because mCallback is null");
                }
                z = true;
            } catch (RemoteException e) {
                io.e(TAG, "onError callback failed", e);
            } catch (NullPointerException e2) {
                io.e(TAG, "NullPointerException onError", e2);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        boolean z = false;
        int i = 0;
        while (!z && i <= 1) {
            i++;
            try {
                if (this.mCallback != null) {
                    this.mCallback.onSuccess(bundle);
                } else {
                    io.e(TAG, "Not calling onSuccess because mCallback is null");
                }
                z = true;
            } catch (RemoteException e) {
                io.e(TAG, "onSuccess callback failed", e);
            } catch (NullPointerException e2) {
                io.e(TAG, "NullPointerException onSuccess", e2);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        IRemoteCallback iRemoteCallback = this.mCallback;
        if (iRemoteCallback != null) {
            parcel.writeStrongBinder(iRemoteCallback.asBinder());
        }
    }

    public RemoteCallbackWrapper(Parcel parcel) {
        this(IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
    }

    public RemoteCallbackWrapper(IRemoteCallback iRemoteCallback) {
        this.mCallback = iRemoteCallback;
    }
}
