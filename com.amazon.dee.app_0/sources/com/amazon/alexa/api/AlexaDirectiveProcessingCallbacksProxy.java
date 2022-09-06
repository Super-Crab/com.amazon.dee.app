package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaDirectiveProcessingCallbacksProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaDirectiveProcessingCallbacksProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onCancelFinished() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onCancelStarted() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onError() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onProcessFinished() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
        public void onProcessStarted() throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaDirectiveProcessingCallbacksProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy";
        static final int TRANSACTION_onCancelFinished = 4;
        static final int TRANSACTION_onCancelStarted = 3;
        static final int TRANSACTION_onError = 5;
        static final int TRANSACTION_onProcessFinished = 2;
        static final int TRANSACTION_onProcessStarted = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaDirectiveProcessingCallbacksProxy {
            public static AlexaDirectiveProcessingCallbacksProxy sDefaultImpl;
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

            @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
            public void onCancelFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCancelFinished();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
            public void onCancelStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCancelStarted();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
            public void onError() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
            public void onProcessFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProcessFinished();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy
            public void onProcessStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProcessStarted();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaDirectiveProcessingCallbacksProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaDirectiveProcessingCallbacksProxy)) ? new Proxy(iBinder) : (AlexaDirectiveProcessingCallbacksProxy) queryLocalInterface;
        }

        public static AlexaDirectiveProcessingCallbacksProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaDirectiveProcessingCallbacksProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaDirectiveProcessingCallbacksProxy;
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
                onProcessStarted();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onProcessFinished();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onCancelStarted();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onCancelFinished();
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onError();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onCancelFinished() throws RemoteException;

    void onCancelStarted() throws RemoteException;

    void onError() throws RemoteException;

    void onProcessFinished() throws RemoteException;

    void onProcessStarted() throws RemoteException;
}
