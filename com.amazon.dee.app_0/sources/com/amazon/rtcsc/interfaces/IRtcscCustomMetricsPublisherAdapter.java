package com.amazon.rtcsc.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface IRtcscCustomMetricsPublisherAdapter extends IInterface {
    public static final int API_VERSION = 1;

    /* loaded from: classes13.dex */
    public static class Default implements IRtcscCustomMetricsPublisherAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
        public int getAPIVersion() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
        public void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException {
        }

        @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
        public void recordMetric(RtcscCustomMetric rtcscCustomMetric) throws RemoteException {
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements IRtcscCustomMetricsPublisherAdapter {
        private static final String DESCRIPTOR = "com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter";
        static final int TRANSACTION_getAPIVersion = 3;
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_recordMetric = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements IRtcscCustomMetricsPublisherAdapter {
            public static IRtcscCustomMetricsPublisherAdapter sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
            public int getAPIVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAPIVersion();
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

            @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
            public void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscErrorCode != null) {
                        obtain.writeInt(1);
                        rtcscErrorCode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(rtcscErrorCode);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
            public void recordMetric(RtcscCustomMetric rtcscCustomMetric) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rtcscCustomMetric != null) {
                        obtain.writeInt(1);
                        rtcscCustomMetric.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().recordMetric(rtcscCustomMetric);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRtcscCustomMetricsPublisherAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRtcscCustomMetricsPublisherAdapter)) {
                return (IRtcscCustomMetricsPublisherAdapter) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IRtcscCustomMetricsPublisherAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
            if (Proxy.sDefaultImpl == null) {
                if (iRtcscCustomMetricsPublisherAdapter == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iRtcscCustomMetricsPublisherAdapter;
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
            RtcscCustomMetric rtcscCustomMetric = null;
            RtcscErrorCode rtcscErrorCode = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    rtcscCustomMetric = RtcscCustomMetric.CREATOR.createFromParcel(parcel);
                }
                recordMetric(rtcscCustomMetric);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    rtcscErrorCode = RtcscErrorCode.CREATOR.createFromParcel(parcel);
                }
                onError(rtcscErrorCode);
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                int aPIVersion = getAPIVersion();
                parcel2.writeNoException();
                parcel2.writeInt(aPIVersion);
                return true;
            }
        }
    }

    int getAPIVersion() throws RemoteException;

    void onError(RtcscErrorCode rtcscErrorCode) throws RemoteException;

    void recordMetric(RtcscCustomMetric rtcscCustomMetric) throws RemoteException;
}
