package com.amazon.device.nos;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes12.dex */
public interface INetworkOptimizationService extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements INetworkOptimizationService {
        private static final String DESCRIPTOR = "com.amazon.device.nos.INetworkOptimizationService";
        static final int TRANSACTION_deregister = 2;
        static final int TRANSACTION_deregisterAll = 3;
        static final int TRANSACTION_register = 1;

        /* loaded from: classes12.dex */
        private static class Proxy implements INetworkOptimizationService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.device.nos.INetworkOptimizationService
            public void deregister(ComponentName componentName, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.device.nos.INetworkOptimizationService
            public void deregisterAll(ComponentName componentName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.device.nos.INetworkOptimizationService
            public void register(TransferCriteria transferCriteria) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (transferCriteria != null) {
                        obtain.writeInt(1);
                        transferCriteria.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkOptimizationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INetworkOptimizationService)) {
                return (INetworkOptimizationService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            TransferCriteria transferCriteria = null;
            ComponentName componentName = null;
            ComponentName componentName2 = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    transferCriteria = TransferCriteria.CREATOR.createFromParcel(parcel);
                }
                register(transferCriteria);
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    componentName2 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                }
                deregister(componentName2, parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    componentName = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                }
                deregisterAll(componentName);
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void deregister(ComponentName componentName, int i) throws RemoteException;

    void deregisterAll(ComponentName componentName) throws RemoteException;

    void register(TransferCriteria transferCriteria) throws RemoteException;
}
