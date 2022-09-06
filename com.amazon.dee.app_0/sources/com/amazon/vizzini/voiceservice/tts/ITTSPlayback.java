package com.amazon.vizzini.voiceservice.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.vizzini.voiceservice.tts.ITTSCallback;
/* loaded from: classes13.dex */
public interface ITTSPlayback extends IInterface {

    /* loaded from: classes13.dex */
    public static class Default implements ITTSPlayback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSPlayback
        public int endTTS(long j) throws RemoteException {
            return 0;
        }

        @Override // com.amazon.vizzini.voiceservice.tts.ITTSPlayback
        public long playTTS(ITTSCallback iTTSCallback, boolean z) throws RemoteException {
            return 0L;
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Stub extends Binder implements ITTSPlayback {
        private static final String DESCRIPTOR = "com.amazon.vizzini.voiceservice.tts.ITTSPlayback";
        static final int TRANSACTION_endTTS = 2;
        static final int TRANSACTION_playTTS = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes13.dex */
        public static class Proxy implements ITTSPlayback {
            public static ITTSPlayback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.vizzini.voiceservice.tts.ITTSPlayback
            public int endTTS(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endTTS(j);
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

            @Override // com.amazon.vizzini.voiceservice.tts.ITTSPlayback
            public long playTTS(ITTSCallback iTTSCallback, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iTTSCallback != null ? iTTSCallback.asBinder() : null);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playTTS(iTTSCallback, z);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITTSPlayback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITTSPlayback)) {
                return (ITTSPlayback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static ITTSPlayback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ITTSPlayback iTTSPlayback) {
            if (Proxy.sDefaultImpl == null) {
                if (iTTSPlayback == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iTTSPlayback;
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
                long playTTS = playTTS(ITTSCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeLong(playTTS);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                int endTTS = endTTS(parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeInt(endTTS);
                return true;
            }
        }
    }

    int endTTS(long j) throws RemoteException;

    long playTTS(ITTSCallback iTTSCallback, boolean z) throws RemoteException;
}
