package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaContextProviderProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaContextProviderProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaContextProviderProxy
        public AlexaContext getAlexaContext() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaContextProviderProxy
        public String getIdentifier() throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaContextProviderProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaContextProviderProxy";
        static final int TRANSACTION_getAlexaContext = 1;
        static final int TRANSACTION_getIdentifier = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaContextProviderProxy {
            public static AlexaContextProviderProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.api.AlexaContextProviderProxy
            public AlexaContext getAlexaContext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlexaContext();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AlexaContext.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaContextProviderProxy
            public String getIdentifier() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIdentifier();
                    }
                    obtain2.readException();
                    return obtain2.readString();
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

        public static AlexaContextProviderProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaContextProviderProxy)) ? new Proxy(iBinder) : (AlexaContextProviderProxy) queryLocalInterface;
        }

        public static AlexaContextProviderProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaContextProviderProxy alexaContextProviderProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaContextProviderProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaContextProviderProxy;
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
                AlexaContext alexaContext = getAlexaContext();
                parcel2.writeNoException();
                if (alexaContext != null) {
                    parcel2.writeInt(1);
                    alexaContext.writeToParcel(parcel2, 1);
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
                String identifier = getIdentifier();
                parcel2.writeNoException();
                parcel2.writeString(identifier);
                return true;
            }
        }
    }

    AlexaContext getAlexaContext() throws RemoteException;

    String getIdentifier() throws RemoteException;
}
