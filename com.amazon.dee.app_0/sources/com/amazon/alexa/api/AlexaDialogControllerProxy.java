package com.amazon.alexa.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface AlexaDialogControllerProxy extends IInterface {

    /* loaded from: classes6.dex */
    public static class Default implements AlexaDialogControllerProxy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public String getDialogIdentifier() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public String getDialogTurnIdentifier() throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void onDialogFinished() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void onDialogStarted() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void onDialogTurnFinished() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void onDialogTurnStarted() throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void startRecordingNextDialogTurn(String str) throws RemoteException {
        }

        @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
        public void stopRecording() throws RemoteException {
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AlexaDialogControllerProxy {
        private static final String DESCRIPTOR = "com.amazon.alexa.api.AlexaDialogControllerProxy";
        static final int TRANSACTION_getDialogIdentifier = 7;
        static final int TRANSACTION_getDialogTurnIdentifier = 8;
        static final int TRANSACTION_onDialogFinished = 6;
        static final int TRANSACTION_onDialogStarted = 5;
        static final int TRANSACTION_onDialogTurnFinished = 4;
        static final int TRANSACTION_onDialogTurnStarted = 3;
        static final int TRANSACTION_startRecordingNextDialogTurn = 1;
        static final int TRANSACTION_stopRecording = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class Proxy implements AlexaDialogControllerProxy {
            public static AlexaDialogControllerProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public String getDialogIdentifier() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDialogIdentifier();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public String getDialogTurnIdentifier() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDialogTurnIdentifier();
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

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void onDialogFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDialogFinished();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void onDialogStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDialogStarted();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void onDialogTurnFinished() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDialogTurnFinished();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void onDialogTurnStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDialogTurnStarted();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void startRecordingNextDialogTurn(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startRecordingNextDialogTurn(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
            public void stopRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopRecording();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AlexaDialogControllerProxy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof AlexaDialogControllerProxy)) ? new Proxy(iBinder) : (AlexaDialogControllerProxy) queryLocalInterface;
        }

        public static AlexaDialogControllerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(AlexaDialogControllerProxy alexaDialogControllerProxy) {
            if (Proxy.sDefaultImpl == null) {
                if (alexaDialogControllerProxy == null) {
                    return false;
                }
                Proxy.sDefaultImpl = alexaDialogControllerProxy;
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
                    startRecordingNextDialogTurn(parcel.readString());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopRecording();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDialogTurnStarted();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDialogTurnFinished();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDialogStarted();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDialogFinished();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String dialogIdentifier = getDialogIdentifier();
                    parcel2.writeNoException();
                    parcel2.writeString(dialogIdentifier);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String dialogTurnIdentifier = getDialogTurnIdentifier();
                    parcel2.writeNoException();
                    parcel2.writeString(dialogTurnIdentifier);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String getDialogIdentifier() throws RemoteException;

    String getDialogTurnIdentifier() throws RemoteException;

    void onDialogFinished() throws RemoteException;

    void onDialogStarted() throws RemoteException;

    void onDialogTurnFinished() throws RemoteException;

    void onDialogTurnStarted() throws RemoteException;

    void startRecordingNextDialogTurn(String str) throws RemoteException;

    void stopRecording() throws RemoteException;
}
