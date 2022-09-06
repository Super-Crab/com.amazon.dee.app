package com.magiear.handsfree.util;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.magiear.handsfree.util.IAudioReaderCallback;
/* loaded from: classes3.dex */
public interface IAudioReader extends IInterface {

    /* loaded from: classes3.dex */
    public static class Default implements IAudioReader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.magiear.handsfree.util.IAudioReader
        public void registerWakeupCallback(IAudioReaderCallback iAudioReaderCallback) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IAudioReader
        public void startRecording(Bundle bundle) throws RemoteException {
        }

        @Override // com.magiear.handsfree.util.IAudioReader
        public void stopRecording(Bundle bundle) throws RemoteException {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IAudioReader {
        private static final String DESCRIPTOR = "com.magiear.handsfree.util.IAudioReader";
        static final int TRANSACTION_registerWakeupCallback = 1;
        static final int TRANSACTION_startRecording = 2;
        static final int TRANSACTION_stopRecording = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IAudioReader {
            public static IAudioReader sDefaultImpl;
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

            @Override // com.magiear.handsfree.util.IAudioReader
            public void registerWakeupCallback(IAudioReaderCallback iAudioReaderCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAudioReaderCallback != null ? iAudioReaderCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWakeupCallback(iAudioReaderCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IAudioReader
            public void startRecording(Bundle bundle) throws RemoteException {
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
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startRecording(bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.magiear.handsfree.util.IAudioReader
            public void stopRecording(Bundle bundle) throws RemoteException {
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
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopRecording(bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAudioReader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAudioReader)) {
                return (IAudioReader) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IAudioReader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAudioReader iAudioReader) {
            if (Proxy.sDefaultImpl == null) {
                if (iAudioReader == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iAudioReader;
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
                registerWakeupCallback(IAudioReaderCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            Bundle bundle = null;
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                startRecording(bundle);
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
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                stopRecording(bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void registerWakeupCallback(IAudioReaderCallback iAudioReaderCallback) throws RemoteException;

    void startRecording(Bundle bundle) throws RemoteException;

    void stopRecording(Bundle bundle) throws RemoteException;
}
