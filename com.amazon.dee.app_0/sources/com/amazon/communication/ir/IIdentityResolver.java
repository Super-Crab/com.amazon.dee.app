package com.amazon.communication.ir;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes12.dex */
public interface IIdentityResolver extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IIdentityResolver {
        private static final String DESCRIPTOR = "com.amazon.communication.ir.IIdentityResolver";
        static final int TRANSACTION_getEndpointForServiceName = 1;
        static final int TRANSACTION_resolveServiceEndpoint = 2;

        /* loaded from: classes12.dex */
        private static class Proxy implements IIdentityResolver {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.communication.ir.IIdentityResolver
            public ParcelableIRServiceEndpoint getEndpointForServiceName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ParcelableIRServiceEndpoint.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.communication.ir.IIdentityResolver
            public ParcelableIRServiceEndpoint resolveServiceEndpoint(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ParcelableIRServiceEndpoint.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIdentityResolver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IIdentityResolver)) {
                return (IIdentityResolver) queryLocalInterface;
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
                ParcelableIRServiceEndpoint endpointForServiceName = getEndpointForServiceName(parcel.readString());
                parcel2.writeNoException();
                if (endpointForServiceName != null) {
                    parcel2.writeInt(1);
                    endpointForServiceName.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                ParcelableIRServiceEndpoint resolveServiceEndpoint = resolveServiceEndpoint(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (resolveServiceEndpoint != null) {
                    parcel2.writeInt(1);
                    resolveServiceEndpoint.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }

    ParcelableIRServiceEndpoint getEndpointForServiceName(String str) throws RemoteException;

    ParcelableIRServiceEndpoint resolveServiceEndpoint(String str, String str2, String str3) throws RemoteException;
}
