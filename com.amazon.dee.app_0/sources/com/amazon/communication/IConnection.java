package com.amazon.communication;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.communication.IResponseHandler;
/* loaded from: classes12.dex */
public interface IConnection extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IConnection {
        private static final String DESCRIPTOR = "com.amazon.communication.IConnection";
        static final int TRANSACTION_isValidConnection = 4;
        static final int TRANSACTION_release = 3;
        static final int TRANSACTION_sendMessage = 1;
        static final int TRANSACTION_sendReliableMessage = 5;
        static final int TRANSACTION_sendRequest = 2;

        /* loaded from: classes12.dex */
        private static class Proxy implements IConnection {
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

            @Override // com.amazon.communication.IConnection
            public boolean isValidConnection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
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

            @Override // com.amazon.communication.IConnection
            public void release() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IConnection
            public int sendMessage(MessageEnvelope messageEnvelope, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IConnection
            public int sendReliableMessage(MessageEnvelope messageEnvelope, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IConnection
            public int sendRequest(MessageEnvelope messageEnvelope, IResponseHandler iResponseHandler) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iResponseHandler != null ? iResponseHandler.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IConnection)) {
                return (IConnection) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            MessageEnvelope messageEnvelope = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                }
                int sendMessage = sendMessage(messageEnvelope, parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(sendMessage);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                }
                int sendRequest = sendRequest(messageEnvelope, IResponseHandler.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(sendRequest);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                release();
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isValidConnection = isValidConnection();
                parcel2.writeNoException();
                parcel2.writeInt(isValidConnection ? 1 : 0);
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                }
                int sendReliableMessage = sendReliableMessage(messageEnvelope, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(sendReliableMessage);
                return true;
            }
        }
    }

    boolean isValidConnection() throws RemoteException;

    void release() throws RemoteException;

    int sendMessage(MessageEnvelope messageEnvelope, int i) throws RemoteException;

    int sendReliableMessage(MessageEnvelope messageEnvelope, int i, int i2) throws RemoteException;

    int sendRequest(MessageEnvelope messageEnvelope, IResponseHandler iResponseHandler) throws RemoteException;
}
