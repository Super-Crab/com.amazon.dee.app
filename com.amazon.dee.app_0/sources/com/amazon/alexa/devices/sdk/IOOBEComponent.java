package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.ISettingsCallback;
/* loaded from: classes6.dex */
public interface IOOBEComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IOOBEComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
        public void getOOBEFlowState(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
        public void getOOBEFlowsState(ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
        public void getOOBEState(ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
        public void launchAlexaOOBE(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
        public void resetOOBE(ISettingsCallback iSettingsCallback) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IOOBEComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IOOBEComponent";
        static final int TRANSACTION_getOOBEFlowState = 2;
        static final int TRANSACTION_getOOBEFlowsState = 3;
        static final int TRANSACTION_getOOBEState = 1;
        static final int TRANSACTION_launchAlexaOOBE = 4;
        static final int TRANSACTION_resetOOBE = 5;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IOOBEComponent {
            public static IOOBEComponent sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
            public void getOOBEFlowState(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getOOBEFlowState(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
            public void getOOBEFlowsState(ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getOOBEFlowsState(iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
            public void getOOBEState(ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getOOBEState(iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
            public void launchAlexaOOBE(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().launchAlexaOOBE(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IOOBEComponent
            public void resetOOBE(ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().resetOOBE(iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOOBEComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOOBEComponent)) {
                return (IOOBEComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IOOBEComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOOBEComponent iOOBEComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iOOBEComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iOOBEComponent;
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
                getOOBEState(ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                getOOBEFlowState(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                getOOBEFlowsState(ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                launchAlexaOOBE(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                resetOOBE(ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void getOOBEFlowState(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void getOOBEFlowsState(ISettingsCallback iSettingsCallback) throws RemoteException;

    void getOOBEState(ISettingsCallback iSettingsCallback) throws RemoteException;

    void launchAlexaOOBE(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void resetOOBE(ISettingsCallback iSettingsCallback) throws RemoteException;
}
