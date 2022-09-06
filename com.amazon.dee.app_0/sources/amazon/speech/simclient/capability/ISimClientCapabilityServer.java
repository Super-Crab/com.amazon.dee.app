package amazon.speech.simclient.capability;

import amazon.speech.simclient.capability.ICapabilityPublishCallback;
import amazon.speech.simclient.capability.ICapabilityQueryCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISimClientCapabilityServer extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimClientCapabilityServer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
        public void attemptCapabilityPublish(String str, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
        public void getInterfaceVersion(String str, ICapabilityQueryCallback iCapabilityQueryCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
        public void publishDeviceCapabilities(String str, String str2, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimClientCapabilityServer {
        private static final String DESCRIPTOR = "amazon.speech.simclient.capability.ISimClientCapabilityServer";
        static final int TRANSACTION_attemptCapabilityPublish = 2;
        static final int TRANSACTION_getInterfaceVersion = 1;
        static final int TRANSACTION_publishDeviceCapabilities = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimClientCapabilityServer {
            public static ISimClientCapabilityServer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
            public void attemptCapabilityPublish(String str, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iCapabilityPublishCallback != null ? iCapabilityPublishCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().attemptCapabilityPublish(str, iCapabilityPublishCallback);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
            public void getInterfaceVersion(String str, ICapabilityQueryCallback iCapabilityQueryCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iCapabilityQueryCallback != null ? iCapabilityQueryCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getInterfaceVersion(str, iCapabilityQueryCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.capability.ISimClientCapabilityServer
            public void publishDeviceCapabilities(String str, String str2, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iCapabilityPublishCallback != null ? iCapabilityPublishCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().publishDeviceCapabilities(str, str2, iCapabilityPublishCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimClientCapabilityServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimClientCapabilityServer)) {
                return (ISimClientCapabilityServer) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimClientCapabilityServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimClientCapabilityServer iSimClientCapabilityServer) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimClientCapabilityServer == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimClientCapabilityServer;
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
                getInterfaceVersion(parcel.readString(), ICapabilityQueryCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                attemptCapabilityPublish(parcel.readString(), ICapabilityPublishCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                publishDeviceCapabilities(parcel.readString(), parcel.readString(), ICapabilityPublishCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void attemptCapabilityPublish(String str, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException;

    void getInterfaceVersion(String str, ICapabilityQueryCallback iCapabilityQueryCallback) throws RemoteException;

    void publishDeviceCapabilities(String str, String str2, ICapabilityPublishCallback iCapabilityPublishCallback) throws RemoteException;
}
