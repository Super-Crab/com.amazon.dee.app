package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.quicinc.voice.activation.IResultCallback;
/* loaded from: classes3.dex */
public interface IVersionInfo extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IVersionInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IVersionInfo
        public int getApiVersion(IResultCallback iResultCallback) throws RemoteException {
            return 0;
        }

        @Override // com.quicinc.voice.activation.IVersionInfo
        public boolean isUvrSupported(int i, String str) throws RemoteException {
            return false;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IVersionInfo {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IVersionInfo";
        static final int TRANSACTION_getApiVersion = 1;
        static final int TRANSACTION_isUvrSupported = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IVersionInfo {
            public static IVersionInfo sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.quicinc.voice.activation.IVersionInfo
            public int getApiVersion(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApiVersion(iResultCallback);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.quicinc.voice.activation.IVersionInfo
            public boolean isUvrSupported(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUvrSupported(i, str);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVersionInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IVersionInfo)) {
                return (IVersionInfo) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IVersionInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IVersionInfo iVersionInfo) {
            if (Proxy.sDefaultImpl == null) {
                if (iVersionInfo == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iVersionInfo;
                return true;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int apiVersion = getApiVersion(IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(apiVersion);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isUvrSupported = isUvrSupported(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(isUvrSupported ? 1 : 0);
                return true;
            }
        }
    }

    int getApiVersion(IResultCallback iResultCallback) throws RemoteException;

    boolean isUvrSupported(int i, String str) throws RemoteException;
}
