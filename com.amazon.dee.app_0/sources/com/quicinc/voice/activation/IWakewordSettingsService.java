package com.quicinc.voice.activation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.quicinc.voice.activation.IResultCallback;
/* loaded from: classes3.dex */
public interface IWakewordSettingsService extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IWakewordSettingsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.quicinc.voice.activation.IWakewordSettingsService
        public void getParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }

        @Override // com.quicinc.voice.activation.IWakewordSettingsService
        public boolean isWakewordEnabled() throws RemoteException {
            return false;
        }

        @Override // com.quicinc.voice.activation.IWakewordSettingsService
        public void setParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWakewordSettingsService {
        private static final String DESCRIPTOR = "com.quicinc.voice.activation.IWakewordSettingsService";
        static final int TRANSACTION_getParams = 2;
        static final int TRANSACTION_isWakewordEnabled = 3;
        static final int TRANSACTION_setParams = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWakewordSettingsService {
            public static IWakewordSettingsService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.quicinc.voice.activation.IWakewordSettingsService
            public void getParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getParams(bundle, iResultCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.quicinc.voice.activation.IWakewordSettingsService
            public boolean isWakewordEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWakewordEnabled();
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

            @Override // com.quicinc.voice.activation.IWakewordSettingsService
            public void setParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setParams(bundle, iResultCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWakewordSettingsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWakewordSettingsService)) {
                return (IWakewordSettingsService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IWakewordSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWakewordSettingsService iWakewordSettingsService) {
            if (Proxy.sDefaultImpl == null) {
                if (iWakewordSettingsService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iWakewordSettingsService;
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
            Bundle bundle = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                setParams(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                getParams(bundle, IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isWakewordEnabled = isWakewordEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(isWakewordEnabled ? 1 : 0);
                return true;
            }
        }
    }

    void getParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;

    boolean isWakewordEnabled() throws RemoteException;

    void setParams(Bundle bundle, IResultCallback iResultCallback) throws RemoteException;
}
