package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaAudioInteractionProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaAudioInteractionProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public AlexaAudioChannel getAlexaAudioChannel() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public String getIdentifier() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public String getInteractionComponentName() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public void onBackground() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public void onForeground() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public void onPause() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public void onResume() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
        public void onStop() throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaAudioInteractionProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaAudioInteractionProxy";
        static final int TRANSACTION_getAlexaAudioChannel = 1;
        static final int TRANSACTION_getIdentifier = 3;
        static final int TRANSACTION_getInteractionComponentName = 2;
        static final int TRANSACTION_onBackground = 4;
        static final int TRANSACTION_onForeground = 5;
        static final int TRANSACTION_onPause = 6;
        static final int TRANSACTION_onResume = 7;
        static final int TRANSACTION_onStop = 8;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaAudioInteractionProxy {
            public static AlexaAudioInteractionProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public AlexaAudioChannel getAlexaAudioChannel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlexaAudioChannel();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AlexaAudioChannel.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public String getIdentifier() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIdentifier();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public String getInteractionComponentName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInteractionComponentName();
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

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public void onBackground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBackground();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public void onForeground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onForeground();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public void onPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPause();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public void onResume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResume();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
            public void onStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStop();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaAudioInteractionProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaAudioInteractionProxy)) ? new Proxy(iBinder) : (AlexaAudioInteractionProxy) queryLocalInterface;
        }

        public static AlexaAudioInteractionProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaAudioInteractionProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaAudioInteractionProxy;
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
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    AlexaAudioChannel alexaAudioChannel = getAlexaAudioChannel();
                    parcel2.writeNoException();
                    if (alexaAudioChannel != null) {
                        parcel2.writeInt(1);
                        alexaAudioChannel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String interactionComponentName = getInteractionComponentName();
                    parcel2.writeNoException();
                    parcel2.writeString(interactionComponentName);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String identifier = getIdentifier();
                    parcel2.writeNoException();
                    parcel2.writeString(identifier);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBackground();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onForeground();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPause();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onResume();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStop();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    AlexaAudioChannel getAlexaAudioChannel() throws RemoteException;

    String getIdentifier() throws RemoteException;

    String getInteractionComponentName() throws RemoteException;

    void onBackground() throws RemoteException;

    void onForeground() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onStop() throws RemoteException;
}
