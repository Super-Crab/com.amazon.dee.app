package com.amazon.communication;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.communication.IConnection;
import com.amazon.communication.IConnectionListener;
import com.amazon.communication.IGatewayConnectivity;
import com.amazon.communication.IMessageHandler;
import com.amazon.communication.ir.IIdentityResolver;
import com.amazon.communication.rlm.IAckHandler;
/* loaded from: classes12.dex */
public interface ICommunicationService extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements ICommunicationService {
        private static final String DESCRIPTOR = "com.amazon.communication.ICommunicationService";
        static final int TRANSACTION_acquireConnection = 1;
        static final int TRANSACTION_acquireConnectionEx = 8;
        static final int TRANSACTION_deregisterMessageHandler = 3;
        static final int TRANSACTION_getGatewayConnectivity = 9;
        static final int TRANSACTION_getIdentityResolver = 6;
        static final int TRANSACTION_isInitialized = 7;
        static final int TRANSACTION_registerMessageHandler = 2;
        static final int TRANSACTION_removeAckHandler = 11;
        static final int TRANSACTION_routeMessage = 4;
        static final int TRANSACTION_routeMessageFragment = 5;
        static final int TRANSACTION_setAckHandler = 10;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class Proxy implements ICommunicationService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.amazon.communication.ICommunicationService
            public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelableConnectionPolicy parcelableConnectionPolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableEndpointIdentity != null) {
                        obtain.writeInt(1);
                        parcelableEndpointIdentity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelableConnectionPolicy != null) {
                        obtain.writeInt(1);
                        parcelableConnectionPolicy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iConnectionListener != null ? iConnectionListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    IConnection asInterface = IConnection.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        parcelableStatus.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public IConnection acquireConnectionEx(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelablePolicy parcelablePolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelableEndpointIdentity != null) {
                        obtain.writeInt(1);
                        parcelableEndpointIdentity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelablePolicy != null) {
                        obtain.writeInt(1);
                        parcelablePolicy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iConnectionListener != null ? iConnectionListener.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    IConnection asInterface = IConnection.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        parcelableStatus.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.communication.ICommunicationService
            public void deregisterMessageHandler(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public IGatewayConnectivity getGatewayConnectivity(IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iConnectionListener != null ? iConnectionListener.asBinder() : null);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    IGatewayConnectivity asInterface = IGatewayConnectivity.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        parcelableStatus.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public IIdentityResolver getIdentityResolver() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return IIdentityResolver.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.communication.ICommunicationService
            public boolean isInitialized() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
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

            @Override // com.amazon.communication.ICommunicationService
            public int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iMessageHandler != null ? iMessageHandler.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public void removeAckHandler() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public void routeMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
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
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i3 = 1;
                    if (parcelableEndpointIdentity != null) {
                        obtain.writeInt(1);
                        parcelableEndpointIdentity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (messageEnvelope != null) {
                        obtain.writeInt(1);
                        messageEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i3 = 0;
                    }
                    obtain.writeInt(i3);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.communication.ICommunicationService
            public int setAckHandler(IAckHandler iAckHandler) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAckHandler != null ? iAckHandler.asBinder() : null);
                    this.mRemote.transact(10, obtain, obtain2, 0);
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

        public static ICommunicationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICommunicationService)) {
                return (ICommunicationService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                IBinder iBinder = null;
                IBinder iBinder2 = null;
                IBinder iBinder3 = null;
                IBinder iBinder4 = null;
                MessageEnvelope messageEnvelope = null;
                MessageEnvelope messageEnvelope2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableEndpointIdentity createFromParcel = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                        ParcelableConnectionPolicy createFromParcel2 = parcel.readInt() != 0 ? ParcelableConnectionPolicy.CREATOR.createFromParcel(parcel) : null;
                        IConnectionListener asInterface = IConnectionListener.Stub.asInterface(parcel.readStrongBinder());
                        ParcelableStatus parcelableStatus = new ParcelableStatus();
                        IConnection acquireConnection = acquireConnection(createFromParcel, createFromParcel2, asInterface, parcelableStatus);
                        parcel2.writeNoException();
                        if (acquireConnection != null) {
                            iBinder = acquireConnection.asBinder();
                        }
                        parcel2.writeStrongBinder(iBinder);
                        parcel2.writeInt(1);
                        parcelableStatus.writeToParcel(parcel2, 1);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        int registerMessageHandler = registerMessageHandler(parcel.readInt(), IMessageHandler.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(registerMessageHandler);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        deregisterMessageHandler(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableEndpointIdentity createFromParcel3 = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            messageEnvelope2 = MessageEnvelope.CREATOR.createFromParcel(parcel);
                        }
                        routeMessage(createFromParcel3, messageEnvelope2, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableEndpointIdentity createFromParcel4 = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            messageEnvelope = MessageEnvelope.CREATOR.createFromParcel(parcel);
                        }
                        routeMessageFragment(createFromParcel4, readInt, messageEnvelope, parcel.readInt() != 0, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        IIdentityResolver identityResolver = getIdentityResolver();
                        parcel2.writeNoException();
                        if (identityResolver != null) {
                            iBinder4 = identityResolver.asBinder();
                        }
                        parcel2.writeStrongBinder(iBinder4);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isInitialized = isInitialized();
                        parcel2.writeNoException();
                        parcel2.writeInt(isInitialized ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        ParcelableEndpointIdentity createFromParcel5 = parcel.readInt() != 0 ? ParcelableEndpointIdentity.CREATOR.createFromParcel(parcel) : null;
                        ParcelablePolicy createFromParcel6 = parcel.readInt() != 0 ? ParcelablePolicy.CREATOR.createFromParcel(parcel) : null;
                        IConnectionListener asInterface2 = IConnectionListener.Stub.asInterface(parcel.readStrongBinder());
                        ParcelableStatus parcelableStatus2 = new ParcelableStatus();
                        IConnection acquireConnectionEx = acquireConnectionEx(createFromParcel5, createFromParcel6, asInterface2, parcelableStatus2);
                        parcel2.writeNoException();
                        if (acquireConnectionEx != null) {
                            iBinder3 = acquireConnectionEx.asBinder();
                        }
                        parcel2.writeStrongBinder(iBinder3);
                        parcel2.writeInt(1);
                        parcelableStatus2.writeToParcel(parcel2, 1);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        IConnectionListener asInterface3 = IConnectionListener.Stub.asInterface(parcel.readStrongBinder());
                        ParcelableStatus parcelableStatus3 = new ParcelableStatus();
                        IGatewayConnectivity gatewayConnectivity = getGatewayConnectivity(asInterface3, parcelableStatus3);
                        parcel2.writeNoException();
                        if (gatewayConnectivity != null) {
                            iBinder2 = gatewayConnectivity.asBinder();
                        }
                        parcel2.writeStrongBinder(iBinder2);
                        parcel2.writeInt(1);
                        parcelableStatus3.writeToParcel(parcel2, 1);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        int ackHandler = setAckHandler(IAckHandler.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(ackHandler);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeAckHandler();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelableConnectionPolicy parcelableConnectionPolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException;

    IConnection acquireConnectionEx(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelablePolicy parcelablePolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException;

    void deregisterMessageHandler(int i) throws RemoteException;

    IGatewayConnectivity getGatewayConnectivity(IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException;

    IIdentityResolver getIdentityResolver() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException;

    void removeAckHandler() throws RemoteException;

    void routeMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i) throws RemoteException;

    void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) throws RemoteException;

    int setAckHandler(IAckHandler iAckHandler) throws RemoteException;
}
