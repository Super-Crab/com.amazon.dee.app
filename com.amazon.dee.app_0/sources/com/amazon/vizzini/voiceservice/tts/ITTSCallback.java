package com.amazon.vizzini.voiceservice.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes13.dex */
public interface ITTSCallback extends IInterface {

    /* loaded from: classes13.dex */
    public static class Default implements ITTSCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public void notifyTTSPlaybackInterrupted(long j) throws RemoteException {
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public void notifyTTSPlaybackStarted(long j) throws RemoteException {
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
        public boolean ttsHeartbeat(long j) throws RemoteException {
            return false;
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements ITTSCallback {
        private static final String DESCRIPTOR = "com.amazon.vizzini.voiceservice.tts.ITTSCallback";
        static final int TRANSACTION_notifyTTSPlaybackInterrupted = 2;
        static final int TRANSACTION_notifyTTSPlaybackStarted = 1;
        static final int TRANSACTION_ttsHeartbeat = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements ITTSCallback {
            public static ITTSCallback sDefaultImpl;
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

            @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
            public void notifyTTSPlaybackInterrupted(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyTTSPlaybackInterrupted(j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
            public void notifyTTSPlaybackStarted(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyTTSPlaybackStarted(j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
            public boolean ttsHeartbeat(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ttsHeartbeat(j);
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
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITTSCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITTSCallback)) {
                return (ITTSCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ITTSCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ITTSCallback iTTSCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iTTSCallback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iTTSCallback;
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
                notifyTTSPlaybackStarted(parcel.readLong());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyTTSPlaybackInterrupted(parcel.readLong());
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
                boolean ttsHeartbeat = ttsHeartbeat(parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeInt(ttsHeartbeat ? 1 : 0);
                return true;
            }
        }
    }

    void notifyTTSPlaybackInterrupted(long j) throws RemoteException;

    void notifyTTSPlaybackStarted(long j) throws RemoteException;

    boolean ttsHeartbeat(long j) throws RemoteException;
}
