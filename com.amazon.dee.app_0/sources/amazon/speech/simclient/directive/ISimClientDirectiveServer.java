package amazon.speech.simclient.directive;

import amazon.speech.simclient.directive.IAttachmentCallback;
import amazon.speech.simclient.directive.IProxyCallback;
import amazon.speech.simclient.event.IEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ISimClientDirectiveServer extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ISimClientDirectiveServer {
        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void acknowledgeDirective(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void attachClientInstance(IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void cancelCurrentVoiceInteraction() throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void cancelDirectiveSequence(String str) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void fetchAttachment(String str, IAttachmentCallback iAttachmentCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public int getClientServerInterfaceVersion() throws RemoteException {
            return 0;
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void routeConcurrentProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void routeProxy(Directive directive) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void routeProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
        public void sendExceptionEvent(Directive directive, String str, String str2, IEventCallback iEventCallback) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISimClientDirectiveServer {
        private static final String DESCRIPTOR = "amazon.speech.simclient.directive.ISimClientDirectiveServer";
        static final int TRANSACTION_acknowledgeDirective = 1;
        static final int TRANSACTION_attachClientInstance = 7;
        static final int TRANSACTION_cancelCurrentVoiceInteraction = 3;
        static final int TRANSACTION_cancelDirectiveSequence = 2;
        static final int TRANSACTION_fetchAttachment = 4;
        static final int TRANSACTION_getClientServerInterfaceVersion = 10;
        static final int TRANSACTION_routeConcurrentProxySequence = 9;
        static final int TRANSACTION_routeProxy = 5;
        static final int TRANSACTION_routeProxySequence = 8;
        static final int TRANSACTION_sendExceptionEvent = 6;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISimClientDirectiveServer {
            public static ISimClientDirectiveServer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void acknowledgeDirective(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().acknowledgeDirective(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void attachClientInstance(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().attachClientInstance(iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void cancelCurrentVoiceInteraction() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelCurrentVoiceInteraction();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void cancelDirectiveSequence(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelDirectiveSequence(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void fetchAttachment(String str, IAttachmentCallback iAttachmentCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iAttachmentCallback != null ? iAttachmentCallback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().fetchAttachment(str, iAttachmentCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public int getClientServerInterfaceVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getClientServerInterfaceVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void routeConcurrentProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(iProxyCallback != null ? iProxyCallback.asBinder() : null);
                    if (this.mRemote.transact(9, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().routeConcurrentProxySequence(list, iProxyCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void routeProxy(Directive directive) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (directive != null) {
                        obtain.writeInt(1);
                        directive.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().routeProxy(directive);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void routeProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(iProxyCallback != null ? iProxyCallback.asBinder() : null);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().routeProxySequence(list, iProxyCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.ISimClientDirectiveServer
            public void sendExceptionEvent(Directive directive, String str, String str2, IEventCallback iEventCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (directive != null) {
                        obtain.writeInt(1);
                        directive.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iEventCallback != null ? iEventCallback.asBinder() : null);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendExceptionEvent(directive, str, str2, iEventCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISimClientDirectiveServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISimClientDirectiveServer)) {
                return (ISimClientDirectiveServer) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ISimClientDirectiveServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISimClientDirectiveServer iSimClientDirectiveServer) {
            if (Proxy.sDefaultImpl == null) {
                if (iSimClientDirectiveServer == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iSimClientDirectiveServer;
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
                Directive directive = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        acknowledgeDirective(parcel.readString());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelDirectiveSequence(parcel.readString());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelCurrentVoiceInteraction();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        fetchAttachment(parcel.readString(), IAttachmentCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            directive = Directive.CREATOR.createFromParcel(parcel);
                        }
                        routeProxy(directive);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            directive = Directive.CREATOR.createFromParcel(parcel);
                        }
                        sendExceptionEvent(directive, parcel.readString(), parcel.readString(), IEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        attachClientInstance(parcel.readStrongBinder());
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        routeProxySequence(parcel.createTypedArrayList(Directive.CREATOR), IProxyCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        routeConcurrentProxySequence(parcel.createTypedArrayList(Directive.CREATOR), IProxyCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        int clientServerInterfaceVersion = getClientServerInterfaceVersion();
                        parcel2.writeNoException();
                        parcel2.writeInt(clientServerInterfaceVersion);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void acknowledgeDirective(String str) throws RemoteException;

    void attachClientInstance(IBinder iBinder) throws RemoteException;

    void cancelCurrentVoiceInteraction() throws RemoteException;

    void cancelDirectiveSequence(String str) throws RemoteException;

    void fetchAttachment(String str, IAttachmentCallback iAttachmentCallback) throws RemoteException;

    int getClientServerInterfaceVersion() throws RemoteException;

    void routeConcurrentProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException;

    void routeProxy(Directive directive) throws RemoteException;

    void routeProxySequence(List<Directive> list, IProxyCallback iProxyCallback) throws RemoteException;

    void sendExceptionEvent(Directive directive, String str, String str2, IEventCallback iEventCallback) throws RemoteException;
}
