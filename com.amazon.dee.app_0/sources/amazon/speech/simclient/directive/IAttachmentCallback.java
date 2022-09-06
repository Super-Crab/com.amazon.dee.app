package amazon.speech.simclient.directive;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAttachmentCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IAttachmentCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.directive.IAttachmentCallback
        public void onAttachmentBytes(byte[] bArr) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.IAttachmentCallback
        public void onAttachmentPipe(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // amazon.speech.simclient.directive.IAttachmentCallback
        public void onError(String str) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAttachmentCallback {
        private static final String DESCRIPTOR = "amazon.speech.simclient.directive.IAttachmentCallback";
        static final int TRANSACTION_onAttachmentBytes = 2;
        static final int TRANSACTION_onAttachmentPipe = 1;
        static final int TRANSACTION_onError = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAttachmentCallback {
            public static IAttachmentCallback sDefaultImpl;
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

            @Override // amazon.speech.simclient.directive.IAttachmentCallback
            public void onAttachmentBytes(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAttachmentBytes(bArr);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.IAttachmentCallback
            public void onAttachmentPipe(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAttachmentPipe(parcelFileDescriptor);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // amazon.speech.simclient.directive.IAttachmentCallback
            public void onError(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAttachmentCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAttachmentCallback)) {
                return (IAttachmentCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAttachmentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAttachmentCallback iAttachmentCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iAttachmentCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAttachmentCallback;
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
                onAttachmentPipe(parcel.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onAttachmentBytes(parcel.createByteArray());
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void onAttachmentBytes(byte[] bArr) throws RemoteException;

    void onAttachmentPipe(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void onError(String str) throws RemoteException;
}
