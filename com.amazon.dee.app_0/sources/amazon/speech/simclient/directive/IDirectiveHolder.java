package amazon.speech.simclient.directive;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDirectiveHolder extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IDirectiveHolder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // amazon.speech.simclient.directive.IDirectiveHolder
        public Directive getDirective() throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDirectiveHolder {
        private static final String DESCRIPTOR = "amazon.speech.simclient.directive.IDirectiveHolder";
        static final int TRANSACTION_getDirective = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDirectiveHolder {
            public static IDirectiveHolder sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // amazon.speech.simclient.directive.IDirectiveHolder
            public Directive getDirective() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDirective();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Directive.CREATOR.createFromParcel(obtain2) : null;
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

        public static IDirectiveHolder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDirectiveHolder)) {
                return (IDirectiveHolder) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IDirectiveHolder getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDirectiveHolder iDirectiveHolder) {
            if (Proxy.sDefaultImpl == null) {
                if (iDirectiveHolder == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iDirectiveHolder;
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
            Directive directive = getDirective();
            parcel2.writeNoException();
            if (directive != null) {
                parcel2.writeInt(1);
                directive.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    Directive getDirective() throws RemoteException;
}
