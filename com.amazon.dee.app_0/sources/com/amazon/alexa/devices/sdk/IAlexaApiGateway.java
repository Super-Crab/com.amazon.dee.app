package com.amazon.alexa.devices.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.alexa.devices.Version;
/* loaded from: classes6.dex */
public interface IAlexaApiGateway extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements IAlexaApiGateway {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IAlexaApiGateway
        public IBinder getComponent(IBinder iBinder, Version version, Version version2, String str) throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.devices.sdk.IAlexaApiGateway
        public Version getVersion() throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements IAlexaApiGateway {
        private static final String DESCRIPTOR = "com.amazon.alexa.devices.sdk.IAlexaApiGateway";
        static final int TRANSACTION_getComponent = 2;
        static final int TRANSACTION_getVersion = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements IAlexaApiGateway {
            public static IAlexaApiGateway sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.devices.sdk.IAlexaApiGateway
            public IBinder getComponent(IBinder iBinder, Version version, Version version2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (version != null) {
                        obtain.writeInt(1);
                        version.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (version2 != null) {
                        obtain.writeInt(1);
                        version2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getComponent(iBinder, version, version2, str);
                    }
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.devices.sdk.IAlexaApiGateway
            public Version getVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Version.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAlexaApiGateway asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAlexaApiGateway)) {
                return (IAlexaApiGateway) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAlexaApiGateway getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAlexaApiGateway iAlexaApiGateway) {
            if (Proxy.sDefaultImpl == null) {
                if (iAlexaApiGateway == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAlexaApiGateway;
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
                Version version = getVersion();
                parcel2.writeNoException();
                if (version != null) {
                    parcel2.writeInt(1);
                    version.writeToParcel(parcel2, 1);
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
                IBinder readStrongBinder = parcel.readStrongBinder();
                Version version2 = null;
                Version createFromParcel = parcel.readInt() != 0 ? Version.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    version2 = Version.CREATOR.createFromParcel(parcel);
                }
                IBinder component = getComponent(readStrongBinder, createFromParcel, version2, parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(component);
                return true;
            }
        }
    }

    IBinder getComponent(IBinder iBinder, Version version, Version version2, String str) throws RemoteException;

    Version getVersion() throws RemoteException;
}
