package amazon.speech.simclient.context;

import amazon.speech.simclient.context.IDeviceContextModificationCallback;
import amazon.speech.simclient.context.IDeviceContextQueryCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IDeviceContextContract extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IDeviceContextContract {
        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void addDeviceContext(String str, String str2, String str3, boolean z) throws RemoteException {
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void addDeviceContextWithCallback(String str, String str2, String str3, boolean z, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void attachClientInstance(IBinder iBinder) throws RemoteException {
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public List<String> getDeviceContext() throws RemoteException {
            return null;
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void queryDeviceContext(DeviceContextQueryType deviceContextQueryType, IDeviceContextQueryCallback iDeviceContextQueryCallback) throws RemoteException {
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void removeDeviceContext(String str, String str2) throws RemoteException {
        }

        @Override // amazon.speech.simclient.context.IDeviceContextContract
        public void removeDeviceContextWithCallback(String str, String str2, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeviceContextContract {
        private static final String DESCRIPTOR = "amazon.speech.simclient.context.IDeviceContextContract";
        static final int TRANSACTION_addDeviceContext = 1;
        static final int TRANSACTION_addDeviceContextWithCallback = 6;
        static final int TRANSACTION_attachClientInstance = 3;
        static final int TRANSACTION_getDeviceContext = 4;
        static final int TRANSACTION_queryDeviceContext = 5;
        static final int TRANSACTION_removeDeviceContext = 2;
        static final int TRANSACTION_removeDeviceContextWithCallback = 7;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDeviceContextContract {
            public static IDeviceContextContract sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void addDeviceContext(String str, String str2, String str3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addDeviceContext(str, str2, str3, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void addDeviceContextWithCallback(String str, String str2, String str3, boolean z, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iDeviceContextModificationCallback != null ? iDeviceContextModificationCallback.asBinder() : null);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addDeviceContextWithCallback(str, str2, str3, z, iDeviceContextModificationCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void attachClientInstance(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().attachClientInstance(iBinder);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public List<String> getDeviceContext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceContext();
                    }
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void queryDeviceContext(DeviceContextQueryType deviceContextQueryType, IDeviceContextQueryCallback iDeviceContextQueryCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (deviceContextQueryType != null) {
                        obtain.writeInt(1);
                        deviceContextQueryType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iDeviceContextQueryCallback != null ? iDeviceContextQueryCallback.asBinder() : null);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().queryDeviceContext(deviceContextQueryType, iDeviceContextQueryCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void removeDeviceContext(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeDeviceContext(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.context.IDeviceContextContract
            public void removeDeviceContextWithCallback(String str, String str2, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iDeviceContextModificationCallback != null ? iDeviceContextModificationCallback.asBinder() : null);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeDeviceContextWithCallback(str, str2, iDeviceContextModificationCallback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceContextContract asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeviceContextContract)) {
                return (IDeviceContextContract) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IDeviceContextContract getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDeviceContextContract iDeviceContextContract) {
            if (Proxy.sDefaultImpl == null) {
                if (iDeviceContextContract == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iDeviceContextContract;
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
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        String readString2 = parcel.readString();
                        String readString3 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        addDeviceContext(readString, readString2, readString3, z);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeDeviceContext(parcel.readString(), parcel.readString());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        attachClientInstance(parcel.readStrongBinder());
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> deviceContext = getDeviceContext();
                        parcel2.writeNoException();
                        parcel2.writeStringList(deviceContext);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        queryDeviceContext(parcel.readInt() != 0 ? DeviceContextQueryType.CREATOR.createFromParcel(parcel) : null, IDeviceContextQueryCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        addDeviceContextWithCallback(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, IDeviceContextModificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeDeviceContextWithCallback(parcel.readString(), parcel.readString(), IDeviceContextModificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void addDeviceContext(String str, String str2, String str3, boolean z) throws RemoteException;

    void addDeviceContextWithCallback(String str, String str2, String str3, boolean z, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException;

    void attachClientInstance(IBinder iBinder) throws RemoteException;

    List<String> getDeviceContext() throws RemoteException;

    void queryDeviceContext(DeviceContextQueryType deviceContextQueryType, IDeviceContextQueryCallback iDeviceContextQueryCallback) throws RemoteException;

    void removeDeviceContext(String str, String str2) throws RemoteException;

    void removeDeviceContextWithCallback(String str, String str2, IDeviceContextModificationCallback iDeviceContextModificationCallback) throws RemoteException;
}
