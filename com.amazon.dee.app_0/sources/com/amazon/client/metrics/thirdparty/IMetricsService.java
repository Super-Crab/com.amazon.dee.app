package com.amazon.client.metrics.thirdparty;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes11.dex */
public interface IMetricsService extends IInterface {

    /* loaded from: classes11.dex */
    public static class Default implements IMetricsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public boolean getRecordMetricsSetting() throws RemoteException {
            return false;
        }

        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException {
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class Stub extends Binder implements IMetricsService {
        private static final String DESCRIPTOR = "com.amazon.client.metrics.thirdparty.IMetricsService";
        static final int TRANSACTION_getRecordMetricsSetting = 2;
        static final int TRANSACTION_record = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class Proxy implements IMetricsService {
            public static IMetricsService sDefaultImpl;
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

            @Override // com.amazon.client.metrics.thirdparty.IMetricsService
            public boolean getRecordMetricsSetting() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecordMetricsSetting();
                    }
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

            @Override // com.amazon.client.metrics.thirdparty.IMetricsService
            public void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeTypedList(list);
                    try {
                        if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                            obtain.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().record(i, i2, str, str2, j, list);
                        obtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMetricsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMetricsService)) {
                return (IMetricsService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IMetricsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMetricsService iMetricsService) {
            if (Proxy.sDefaultImpl == null) {
                if (iMetricsService == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iMetricsService;
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
                record(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.createTypedArrayList(DataPointEnvelope.CREATOR));
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean recordMetricsSetting = getRecordMetricsSetting();
                parcel2.writeNoException();
                parcel2.writeInt(recordMetricsSetting ? 1 : 0);
                return true;
            }
        }
    }

    boolean getRecordMetricsSetting() throws RemoteException;

    void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException;
}
