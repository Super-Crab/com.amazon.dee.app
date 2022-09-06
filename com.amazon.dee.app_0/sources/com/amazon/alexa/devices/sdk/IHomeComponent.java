package com.amazon.alexa.devices.sdk;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface IHomeComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IHomeComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IHomeComponent
        public Intent getHomeActivityIntent() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IHomeComponent
        public Intent getSettingsActivityIntent() throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IHomeComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IHomeComponent";
        static final int TRANSACTION_getHomeActivityIntent = 1;
        static final int TRANSACTION_getSettingsActivityIntent = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IHomeComponent {
            public static IHomeComponent sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.devices.sdk.IHomeComponent
            public Intent getHomeActivityIntent() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHomeActivityIntent();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.devices.sdk.IHomeComponent
            public Intent getSettingsActivityIntent() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsActivityIntent();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHomeComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHomeComponent)) {
                return (IHomeComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IHomeComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IHomeComponent iHomeComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iHomeComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iHomeComponent;
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
                Intent homeActivityIntent = getHomeActivityIntent();
                parcel2.writeNoException();
                if (homeActivityIntent != null) {
                    parcel2.writeInt(1);
                    homeActivityIntent.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                Intent settingsActivityIntent = getSettingsActivityIntent();
                parcel2.writeNoException();
                if (settingsActivityIntent != null) {
                    parcel2.writeInt(1);
                    settingsActivityIntent.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }

    Intent getHomeActivityIntent() throws RemoteException;

    Intent getSettingsActivityIntent() throws RemoteException;
}
