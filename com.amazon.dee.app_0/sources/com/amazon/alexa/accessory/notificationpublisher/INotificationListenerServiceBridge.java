package com.amazon.alexa.accessory.notificationpublisher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import java.util.List;
/* loaded from: classes.dex */
public interface INotificationListenerServiceBridge extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements INotificationListenerServiceBridge {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean doesNotificationExistInStatusBar(String str) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean doesNotificationSupportReply(String str) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public List<StatusBarNotification> getActiveNotificationsWithKeys(String[] strArr) throws RemoteException {
            return null;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public int getInterruptionFilter() throws RemoteException {
            return 0;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean isListenerConnected() throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean isReplyNotification(String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public void onDeviceConnectionChanged(boolean z) throws RemoteException {
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean sendReply(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public void setApplicationRunning(boolean z) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INotificationListenerServiceBridge {
        private static final String DESCRIPTOR = "com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge";
        static final int TRANSACTION_doesNotificationExistInStatusBar = 9;
        static final int TRANSACTION_doesNotificationSupportReply = 6;
        static final int TRANSACTION_getActiveNotificationsWithKeys = 5;
        static final int TRANSACTION_getInterruptionFilter = 2;
        static final int TRANSACTION_isListenerConnected = 1;
        static final int TRANSACTION_isReplyNotification = 8;
        static final int TRANSACTION_onDeviceConnectionChanged = 4;
        static final int TRANSACTION_sendReply = 7;
        static final int TRANSACTION_setApplicationRunning = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements INotificationListenerServiceBridge {
            public static INotificationListenerServiceBridge sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public boolean doesNotificationExistInStatusBar(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doesNotificationExistInStatusBar(str);
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

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public boolean doesNotificationSupportReply(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doesNotificationSupportReply(str);
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

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public List<StatusBarNotification> getActiveNotificationsWithKeys(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotificationsWithKeys(strArr);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public int getInterruptionFilter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterruptionFilter();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public boolean isListenerConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isListenerConnected();
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

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public boolean isReplyNotification(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    boolean z = false;
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isReplyNotification(str, str2, str3);
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

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public void onDeviceConnectionChanged(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceConnectionChanged(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public boolean sendReply(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendReply(str, str2);
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

            @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
            public void setApplicationRunning(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setApplicationRunning(z);
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

        public static INotificationListenerServiceBridge asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INotificationListenerServiceBridge)) {
                return (INotificationListenerServiceBridge) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static INotificationListenerServiceBridge getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(INotificationListenerServiceBridge iNotificationListenerServiceBridge) {
            if (Proxy.sDefaultImpl == null) {
                if (iNotificationListenerServiceBridge == null) {
                    return false;
                }
                Proxy.sDefaultImpl = iNotificationListenerServiceBridge;
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
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isListenerConnected = isListenerConnected();
                        parcel2.writeNoException();
                        parcel2.writeInt(isListenerConnected ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        int interruptionFilter = getInterruptionFilter();
                        parcel2.writeNoException();
                        parcel2.writeInt(interruptionFilter);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setApplicationRunning(z);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        onDeviceConnectionChanged(z);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<StatusBarNotification> activeNotificationsWithKeys = getActiveNotificationsWithKeys(parcel.createStringArray());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(activeNotificationsWithKeys);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean doesNotificationSupportReply = doesNotificationSupportReply(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(doesNotificationSupportReply ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean sendReply = sendReply(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(sendReply ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isReplyNotification = isReplyNotification(parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isReplyNotification ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean doesNotificationExistInStatusBar = doesNotificationExistInStatusBar(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(doesNotificationExistInStatusBar ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    boolean doesNotificationExistInStatusBar(String str) throws RemoteException;

    boolean doesNotificationSupportReply(String str) throws RemoteException;

    List<StatusBarNotification> getActiveNotificationsWithKeys(String[] strArr) throws RemoteException;

    int getInterruptionFilter() throws RemoteException;

    boolean isListenerConnected() throws RemoteException;

    boolean isReplyNotification(String str, String str2, String str3) throws RemoteException;

    void onDeviceConnectionChanged(boolean z) throws RemoteException;

    boolean sendReply(String str, String str2) throws RemoteException;

    void setApplicationRunning(boolean z) throws RemoteException;
}
