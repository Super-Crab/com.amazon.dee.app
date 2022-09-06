package com.amazon.identity.auth.device.callback;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.identity.auth.device.callback.IRemoteCallback;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface IGenericIPC extends IInterface {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IGenericIPC {
        private static final String DESCRIPTOR = "com.amazon.identity.auth.device.callback.IGenericIPC";
        static final int TRANSACTION_call = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public static class Proxy implements IGenericIPC {
            public static IGenericIPC sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.identity.auth.device.callback.IGenericIPC
            public void call(String str, Bundle bundle, IRemoteCallback iRemoteCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().call(str, bundle, iRemoteCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGenericIPC asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGenericIPC)) {
                return (IGenericIPC) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IGenericIPC getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IGenericIPC iGenericIPC) {
            if (Proxy.sDefaultImpl == null) {
                if (iGenericIPC == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iGenericIPC;
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
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            call(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void call(String str, Bundle bundle, IRemoteCallback iRemoteCallback) throws RemoteException;
}
