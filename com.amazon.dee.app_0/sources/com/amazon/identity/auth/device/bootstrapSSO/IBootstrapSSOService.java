package com.amazon.identity.auth.device.bootstrapSSO;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface IBootstrapSSOService extends IInterface {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IBootstrapSSOService {
        private static final String DESCRIPTOR = "com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService";
        static final int TRANSACTION_bootstrapForPackage = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public static class Proxy implements IBootstrapSSOService {
            public static IBootstrapSSOService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService
            public Bundle bootstrapForPackage(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bootstrapForPackage(bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBootstrapSSOService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBootstrapSSOService)) {
                return (IBootstrapSSOService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IBootstrapSSOService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IBootstrapSSOService iBootstrapSSOService) {
            if (Proxy.sDefaultImpl == null) {
                if (iBootstrapSSOService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iBootstrapSSOService;
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
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            Bundle bootstrapForPackage = bootstrapForPackage(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            if (bootstrapForPackage != null) {
                parcel2.writeInt(1);
                bootstrapForPackage.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    Bundle bootstrapForPackage(Bundle bundle) throws RemoteException;
}
