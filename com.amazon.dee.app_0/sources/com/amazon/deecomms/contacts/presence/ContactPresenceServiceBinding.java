package com.amazon.deecomms.contacts.presence;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class ContactPresenceServiceBinding {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactPresenceServiceBinding.class);
    private ServiceBindingCallback callback;
    private Messenger service = null;
    private boolean bound = false;
    private ServiceConnection connection = new ServiceConnection() { // from class: com.amazon.deecomms.contacts.presence.ContactPresenceServiceBinding.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ContactPresenceServiceBinding.this.service = new Messenger(iBinder);
            ContactPresenceServiceBinding.this.bound = true;
            if (ContactPresenceServiceBinding.this.callback != null) {
                ContactPresenceServiceBinding.this.callback.serviceBound();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ContactPresenceServiceBinding.this.bound = false;
            ContactPresenceServiceBinding.this.service = null;
            if (ContactPresenceServiceBinding.this.callback != null) {
                ContactPresenceServiceBinding.this.callback.serviceUnbound();
            }
        }
    };

    /* loaded from: classes12.dex */
    public interface ServiceBindingCallback {
        void serviceBound();

        void serviceUnbound();
    }

    public boolean getActiveContacts(@NonNull Messenger messenger) {
        if (this.bound) {
            Message obtain = Message.obtain((Handler) null, 3);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_GET_ALL_ACTIVE_CONTACTS but it failed");
                return false;
            }
        }
        LOG.e("Attempted to send ContactPresenceService.MSG_GET_ALL_ACTIVE_CONTACTS when not bound to the service.");
        return false;
    }

    public boolean getActiveContactsForBanner(@NonNull Messenger messenger) {
        if (this.bound) {
            Message obtain = Message.obtain((Handler) null, 2);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_GET_ACTIVE_CONTACTS_FOR_BANNER but it failed");
                return false;
            }
        }
        LOG.e("Attempted to send ContactPresenceService.MSG_GET_ACTIVE_CONTACTS_FOR_BANNER when not bound to the service.");
        return false;
    }

    public boolean getActiveCount(@NonNull Messenger messenger) {
        if (this.bound) {
            Message obtain = Message.obtain((Handler) null, 1);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_GET_ACTIVE_COUNT but it failed");
                return false;
            }
        }
        LOG.e("Attempted to send ContactPresenceService.MSG_GET_ACTIVE_COUNT when not bound to the service.");
        return false;
    }

    public boolean getAllContacts(@NonNull Messenger messenger, boolean z) {
        if (this.bound) {
            Message obtain = Message.obtain(null, 4, z ? 1 : 0, 0);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_GET_ALL_CONTACTS_PRESENCE but it failed");
            }
        } else {
            LOG.e("Attempted to send ContactPresenceService.MSG_GET_ALL_CONTACTS_PRESENCE when not bound to the service.");
        }
        return false;
    }

    public boolean getContactPresence(@NonNull String str, @NonNull Messenger messenger) {
        if (this.bound) {
            Message obtain = Message.obtain(null, 6, str);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_GET_CONTACT_PRESENCE but it failed");
                return false;
            }
        }
        LOG.e("Attempted to send ContactPresenceService.MSG_GET_CONTACT_PRESENCE when not bound to the service.");
        return false;
    }

    public boolean isContactActive(@NonNull String str, @NonNull Messenger messenger) {
        if (this.bound) {
            Message obtain = Message.obtain(null, 5, str);
            obtain.replyTo = messenger;
            try {
                this.service.send(obtain);
                return true;
            } catch (RemoteException unused) {
                LOG.w("Attempted to send ContactPresenceService.MSG_IS_CONTACT_ACTIVE but it failed");
                return false;
            }
        }
        LOG.e("Attempted to send ContactPresenceService.MSG_IS_CONTACT_ACTIVE when not bound to the service.");
        return false;
    }

    public void startConnection(@NonNull Activity activity, @NonNull ServiceBindingCallback serviceBindingCallback) {
        this.callback = serviceBindingCallback;
        if (!this.bound) {
            activity.bindService(new Intent(activity, ContactPresenceService.class), this.connection, 1);
        } else {
            serviceBindingCallback.serviceBound();
        }
    }

    public void stopConnection(@NonNull Activity activity, boolean z) {
        if (this.bound || z) {
            activity.unbindService(this.connection);
            this.bound = false;
            this.service = null;
        }
        ServiceBindingCallback serviceBindingCallback = this.callback;
        if (serviceBindingCallback != null) {
            serviceBindingCallback.serviceUnbound();
        }
    }
}
