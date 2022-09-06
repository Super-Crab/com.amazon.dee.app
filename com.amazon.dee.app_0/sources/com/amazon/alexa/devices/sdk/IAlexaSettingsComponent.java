package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.sdk.ISettingsCallback;
/* loaded from: classes6.dex */
public interface IAlexaSettingsComponent extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IAlexaSettingsComponent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IAlexaSettingsComponent
        public void getSettingState(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
        }

        @Override // com.amazon.alexa.devices.sdk.IAlexaSettingsComponent
        public void setSettingsState(String str, Bundle bundle, ISettingsCallback iSettingsCallback) throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IAlexaSettingsComponent {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IAlexaSettingsComponent";
        static final int TRANSACTION_getSettingState = 1;
        static final int TRANSACTION_setSettingsState = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IAlexaSettingsComponent {
            public static IAlexaSettingsComponent sDefaultImpl;
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

            @Override // com.amazon.alexa.devices.sdk.IAlexaSettingsComponent
            public void getSettingState(String str, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getSettingState(str, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.devices.sdk.IAlexaSettingsComponent
            public void setSettingsState(String str, Bundle bundle, ISettingsCallback iSettingsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSettingsCallback != null ? iSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSettingsState(str, bundle, iSettingsCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAlexaSettingsComponent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAlexaSettingsComponent)) {
                return (IAlexaSettingsComponent) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAlexaSettingsComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAlexaSettingsComponent iAlexaSettingsComponent) {
            if (Proxy.sDefaultImpl == null) {
                if (iAlexaSettingsComponent == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAlexaSettingsComponent;
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
                getSettingState(parcel.readString(), ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setSettingsState(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, ISettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void getSettingState(String str, ISettingsCallback iSettingsCallback) throws RemoteException;

    void setSettingsState(String str, Bundle bundle, ISettingsCallback iSettingsCallback) throws RemoteException;
}
