package com.amazon.leaderselection;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
/* loaded from: classes12.dex */
public interface l extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements l {

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.amazon.leaderselection.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0055a implements l {
            public static l b;
            private IBinder a;

            C0055a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.amazon.leaderselection.l
            public Message a(Message message) {
                Message message2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.amazon.leaderselection.LeaderSelectionServiceInterface");
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        message2 = obtain2.readInt() != 0 ? (Message) Message.CREATOR.createFromParcel(obtain2) : null;
                    } else {
                        message2 = a.a().a(message);
                    }
                    return message2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.leaderselection.l
            public Leader a(String str) {
                Leader createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.amazon.leaderselection.LeaderSelectionServiceInterface");
                    obtain.writeString(str);
                    if (this.a.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? Leader.CREATOR.createFromParcel(obtain2) : null;
                    } else {
                        createFromParcel = a.a().a(str);
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }
        }

        public a() {
            attachInterface(this, "com.amazon.leaderselection.LeaderSelectionServiceInterface");
        }

        public static l a() {
            return C0055a.b;
        }

        public static l a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.amazon.leaderselection.LeaderSelectionServiceInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof l)) ? new C0055a(iBinder) : (l) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.amazon.leaderselection.LeaderSelectionServiceInterface");
                Leader a = a(parcel.readString());
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(1);
                    a.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.amazon.leaderselection.LeaderSelectionServiceInterface");
                return true;
            } else {
                parcel.enforceInterface("com.amazon.leaderselection.LeaderSelectionServiceInterface");
                Message a2 = a(parcel.readInt() != 0 ? (Message) Message.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a2 != null) {
                    parcel2.writeInt(1);
                    a2.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }

    Message a(Message message);

    Leader a(String str);
}
