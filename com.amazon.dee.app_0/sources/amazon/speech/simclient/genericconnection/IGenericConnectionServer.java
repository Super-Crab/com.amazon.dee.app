package amazon.speech.simclient.genericconnection;

import amazon.speech.simclient.genericconnection.IDownstreamMessageCallback;
import amazon.speech.simclient.genericconnection.IGenericConnectionStatusCallback;
import amazon.speech.simclient.genericconnection.IServiceSupportedCallback;
import amazon.speech.simclient.genericconnection.IUpstreamMessageResultCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IGenericConnectionServer extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IGenericConnectionServer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void queryConnectionStatus(IGenericConnectionStatusCallback iGenericConnectionStatusCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void queryServiceSupported(IServiceSupportedCallback iServiceSupportedCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void registerConnectionStatusCallback(IGenericConnectionStatusCallback iGenericConnectionStatusCallback, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void registerDownstreamMessageCallback(IDownstreamMessageCallback iDownstreamMessageCallback, IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void sendMessage(byte[] bArr, String str, boolean z, IUpstreamMessageResultCallback iUpstreamMessageResultCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void unregisterConnectionStatusCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
        public void unregisterDownstreamMessageCallback(IBinder iBinder) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGenericConnectionServer {
        private static final String DESCRIPTOR = "amazon.speech.simclient.genericconnection.IGenericConnectionServer";
        static final int TRANSACTION_queryConnectionStatus = 3;
        static final int TRANSACTION_queryServiceSupported = 7;
        static final int TRANSACTION_registerConnectionStatusCallback = 1;
        static final int TRANSACTION_registerDownstreamMessageCallback = 5;
        static final int TRANSACTION_sendMessage = 4;
        static final int TRANSACTION_unregisterConnectionStatusCallback = 2;
        static final int TRANSACTION_unregisterDownstreamMessageCallback = 6;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IGenericConnectionServer {
            public static IGenericConnectionServer sDefaultImpl;
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

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void queryConnectionStatus(IGenericConnectionStatusCallback iGenericConnectionStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGenericConnectionStatusCallback != null ? iGenericConnectionStatusCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().queryConnectionStatus(iGenericConnectionStatusCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void queryServiceSupported(IServiceSupportedCallback iServiceSupportedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iServiceSupportedCallback != null ? iServiceSupportedCallback.asBinder() : null);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().queryServiceSupported(iServiceSupportedCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void registerConnectionStatusCallback(IGenericConnectionStatusCallback iGenericConnectionStatusCallback, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGenericConnectionStatusCallback != null ? iGenericConnectionStatusCallback.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerConnectionStatusCallback(iGenericConnectionStatusCallback, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void registerDownstreamMessageCallback(IDownstreamMessageCallback iDownstreamMessageCallback, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownstreamMessageCallback != null ? iDownstreamMessageCallback.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerDownstreamMessageCallback(iDownstreamMessageCallback, iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void sendMessage(byte[] bArr, String str, boolean z, IUpstreamMessageResultCallback iUpstreamMessageResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iUpstreamMessageResultCallback != null ? iUpstreamMessageResultCallback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendMessage(bArr, str, z, iUpstreamMessageResultCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void unregisterConnectionStatusCallback(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterConnectionStatusCallback(iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.genericconnection.IGenericConnectionServer
            public void unregisterDownstreamMessageCallback(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterDownstreamMessageCallback(iBinder);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGenericConnectionServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGenericConnectionServer)) {
                return (IGenericConnectionServer) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IGenericConnectionServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IGenericConnectionServer iGenericConnectionServer) {
            if (Proxy.sDefaultImpl == null) {
                if (iGenericConnectionServer == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iGenericConnectionServer;
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
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerConnectionStatusCallback(IGenericConnectionStatusCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterConnectionStatusCallback(parcel.readStrongBinder());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        queryConnectionStatus(IGenericConnectionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        sendMessage(parcel.createByteArray(), parcel.readString(), parcel.readInt() != 0, IUpstreamMessageResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerDownstreamMessageCallback(IDownstreamMessageCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterDownstreamMessageCallback(parcel.readStrongBinder());
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        queryServiceSupported(IServiceSupportedCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void queryConnectionStatus(IGenericConnectionStatusCallback iGenericConnectionStatusCallback) throws RemoteException;

    void queryServiceSupported(IServiceSupportedCallback iServiceSupportedCallback) throws RemoteException;

    void registerConnectionStatusCallback(IGenericConnectionStatusCallback iGenericConnectionStatusCallback, IBinder iBinder) throws RemoteException;

    void registerDownstreamMessageCallback(IDownstreamMessageCallback iDownstreamMessageCallback, IBinder iBinder) throws RemoteException;

    void sendMessage(byte[] bArr, String str, boolean z, IUpstreamMessageResultCallback iUpstreamMessageResultCallback) throws RemoteException;

    void unregisterConnectionStatusCallback(IBinder iBinder) throws RemoteException;

    void unregisterDownstreamMessageCallback(IBinder iBinder) throws RemoteException;
}
