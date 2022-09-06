package com.amazon.communication;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes12.dex */
public interface IInputStream extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IInputStream {
        private static final String DESCRIPTOR = "com.amazon.communication.IInputStream";
        static final int TRANSACTION_available = 4;
        static final int TRANSACTION_close = 5;
        static final int TRANSACTION_readByte = 3;
        static final int TRANSACTION_readBytes = 1;
        static final int TRANSACTION_readBytesIntoOffset = 2;

        /* loaded from: classes12.dex */
        private static class Proxy implements IInputStream {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.communication.IInputStream
            public int available() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IInputStream
            public boolean close() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
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

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.communication.IInputStream
            public int readByte() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IInputStream
            public int readBytes(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IInputStream
            public int readBytesIntoOffset(byte[] bArr, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputStream asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IInputStream)) {
                return (IInputStream) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                byte[] createByteArray = parcel.createByteArray();
                int readBytes = readBytes(createByteArray);
                parcel2.writeNoException();
                parcel2.writeInt(readBytes);
                parcel2.writeByteArray(createByteArray);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                byte[] createByteArray2 = parcel.createByteArray();
                int readBytesIntoOffset = readBytesIntoOffset(createByteArray2, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(readBytesIntoOffset);
                parcel2.writeByteArray(createByteArray2);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                int readByte = readByte();
                parcel2.writeNoException();
                parcel2.writeInt(readByte);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                int available = available();
                parcel2.writeNoException();
                parcel2.writeInt(available);
                return true;
            } else if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean close = close();
                parcel2.writeNoException();
                parcel2.writeInt(close ? 1 : 0);
                return true;
            }
        }
    }

    int available() throws RemoteException;

    boolean close() throws RemoteException;

    int readByte() throws RemoteException;

    int readBytes(byte[] bArr) throws RemoteException;

    int readBytesIntoOffset(byte[] bArr, int i, int i2) throws RemoteException;
}
