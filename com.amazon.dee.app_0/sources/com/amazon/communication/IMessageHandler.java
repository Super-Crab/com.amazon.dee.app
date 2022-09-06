package com.amazon.communication;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes12.dex */
public interface IMessageHandler extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IMessageHandler {
        private static final String DESCRIPTOR = "com.amazon.communication.IMessageHandler";
        static final int TRANSACTION_onMessage = 1;
        static final int TRANSACTION_onMessageFragment = 2;

        /* loaded from: classes12.dex */
        private static class Proxy implements IMessageHandler {
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

            @Override // com.amazon.communication.IMessageHandler
            public void onMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableEndpointIdentity != null) {
                        obtain.writeInt(1);
                        parcelableEndpointIdentity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.IMessageHandler
            public void onMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 0;
                    if (parcelableEndpointIdentity != null) {
                        obtain.writeInt(1);
                        parcelableEndpointIdentity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMessageHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMessageHandler)) {
                return (IMessageHandler) queryLocalInterface;
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
                ParcelableEndpointIdentity createFromParcel = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                }
                onMessage(createFromParcel, messageEnvelope);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                ParcelableEndpointIdentity createFromParcel2 = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                }
                onMessageFragment(createFromParcel2, messageEnvelope, parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
        }
    }

    void onMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope) throws RemoteException;

    void onMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i, boolean z) throws RemoteException;
}
