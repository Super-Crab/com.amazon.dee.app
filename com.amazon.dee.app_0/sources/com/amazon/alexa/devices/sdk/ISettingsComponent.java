package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface ISettingsComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements ISettingsComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.ISettingsComponent
        public boolean isAccountRegistered() throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.devices.sdk.ISettingsComponent
        public void launchAccountRegistration() throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements ISettingsComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.ISettingsComponent";
        static final int TRANSACTION_isAccountRegistered = 1;
        static final int TRANSACTION_launchAccountRegistration = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements ISettingsComponent {
            public static ISettingsComponent sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.ISettingsComponent
            public boolean isAccountRegistered() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAccountRegistered();
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

            @Override // com.amazon.alexa.devices.sdk.ISettingsComponent
            public void launchAccountRegistration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAccountRegistration();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISettingsComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISettingsComponent)) {
                return (ISettingsComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISettingsComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISettingsComponent iSettingsComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iSettingsComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSettingsComponent;
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
                boolean isAccountRegistered = isAccountRegistered();
                parcel2.writeNoException();
                parcel2.writeInt(isAccountRegistered ? 1 : 0);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                launchAccountRegistration();
                parcel2.writeNoException();
                return true;
            }
        }
    }

    boolean isAccountRegistered() throws RemoteException;

    void launchAccountRegistration() throws RemoteException;
}
