package com.amazon.alexa.biloba.view.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.constants.Events;
import dagger.Lazy;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class RemoteManagementInactivityHandler {
    private static final String PLACEHOLDER_PERMISSION_DENIED_EVENT = "PLACEHOLDER_PERMISSION_DENIED_EVENT";
    private static final long REMOTE_MANAGEMENT_INACTIVITY_TIMEOUT = 600000;
    private static final String RM_STATUS_INACTIVE = "INACTIVE";
    private static final String RM_STATUS_KEY = "status";
    private static final String TAG = "RemoteManagementInactivityHandler";
    private static final int TERMINATION_RUNNABLE_ID = 421;
    private final Lazy<CareActorsStore> careActorsStore;
    private final Lazy<EventBus> eventBus;
    private final Handler handler;
    private final Lazy<IdentityService> identityService;
    @VisibleForTesting
    MainActivityLifecycleObserver mainActivityLifecycleObserver;
    private final Lazy<MainActivityLifecycleObserverRegistrar> mainActivityLifecycleObserverRegistrar;
    private MultiFilterSubscriber multiFilterSubscriber;
    @VisibleForTesting
    AlertDialog permissionDeniedAlertDialog;
    private final Lazy<RoutingService> routingService;
    @VisibleForTesting
    AlertDialog terminationAlertDialog;
    @VisibleForTesting
    Runnable terminationRunnable;

    public RemoteManagementInactivityHandler(Lazy<EventBus> lazy, Lazy<IdentityService> lazy2, Lazy<MainActivityLifecycleObserverRegistrar> lazy3, Lazy<RoutingService> lazy4, Lazy<CareActorsStore> lazy5, Handler handler) {
        this.eventBus = lazy;
        this.identityService = lazy2;
        this.mainActivityLifecycleObserverRegistrar = lazy3;
        this.routingService = lazy4;
        this.careActorsStore = lazy5;
        this.handler = handler;
    }

    private String getCareContactFirstName(Context context) {
        String displayName = CareActorUtil.getDisplayName(this.careActorsStore.mo358get().getCareContact().getValue());
        return displayName.isEmpty() ? context.getString(R.string.your_loved_one_lowercase) : displayName;
    }

    private MultiFilterSubscriber getMultiFilterSubscriber(Context context) {
        MultiFilterSubscriber newSubscriber = this.eventBus.mo358get().getNewSubscriber();
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(Events.ROUTE_CHANGED_EVENT), getRouteChangeSubscriberHandler());
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_PROFILE_DELEGATION_CHANGED), getTerminationSubscriberHandler());
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS), getSignOutSubscriberHandler());
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(PLACEHOLDER_PERMISSION_DENIED_EVENT), getPermissionDeniedSubscriberHandler(context));
        return newSubscriber;
    }

    private MessageHandler getPermissionDeniedSubscriberHandler(final Context context) {
        return new MessageHandler() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$5obt3Don05Bu-YbfBcYLYnm0sug
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RemoteManagementInactivityHandler.this.lambda$getPermissionDeniedSubscriberHandler$4$RemoteManagementInactivityHandler(context, message);
            }
        };
    }

    private MessageHandler getRouteChangeSubscriberHandler() {
        return new MessageHandler() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$Z2zijiZ8RY10o3RHI_gTOXHJPUc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RemoteManagementInactivityHandler.this.lambda$getRouteChangeSubscriberHandler$1$RemoteManagementInactivityHandler(message);
            }
        };
    }

    private MessageHandler getSignOutSubscriberHandler() {
        return new MessageHandler() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$60U0NSpyhJXp1Mm-piKtrUMvTMM
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RemoteManagementInactivityHandler.this.lambda$getSignOutSubscriberHandler$3$RemoteManagementInactivityHandler(message);
            }
        };
    }

    private MessageHandler getTerminationSubscriberHandler() {
        return new MessageHandler() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$NuJZdGu1iVLHwD3z5RRFvViXRBk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RemoteManagementInactivityHandler.this.lambda$getTerminationSubscriberHandler$2$RemoteManagementInactivityHandler(message);
            }
        };
    }

    private void registerMainActivityLifecycleObserver() {
        if (this.mainActivityLifecycleObserver == null) {
            this.mainActivityLifecycleObserver = new MainActivityLifecycleObserver() { // from class: com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler.1
                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityCreated() {
                }

                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityDestroy() {
                    LogUtils.d(RemoteManagementInactivityHandler.TAG, "MainActivity onDestroy, stopping timer and unsubscribing from all events.");
                    RemoteManagementInactivityHandler.this.unsubscribeInactivityTimerEvents();
                }

                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityPause() {
                }

                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityResume() {
                }

                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityStart() {
                }

                @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
                public void onActivityStop() {
                }
            };
        }
        this.mainActivityLifecycleObserverRegistrar.mo358get().addObserver(this.mainActivityLifecycleObserver);
    }

    private void startHandler() {
        LogUtils.d(TAG, "Starting RM inactivity timer");
        android.os.Message obtain = android.os.Message.obtain(this.handler, this.terminationRunnable);
        obtain.what = 421;
        this.handler.sendMessageDelayed(obtain, 600000L);
    }

    private void stopHandler() {
        Handler handler = this.handler;
        if (handler == null || !handler.hasMessages(421)) {
            return;
        }
        LogUtils.d(TAG, "Stopping RM inactivity timer");
        this.handler.removeMessages(421);
    }

    private void terminateRemoteManagement() {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null && user.getDelegatedProfile() != null) {
            try {
                LogUtils.d(TAG, "Terminating remote management session");
                this.identityService.mo358get().terminateDelegation(TAG);
                return;
            } catch (UnsupportedOperationException unused) {
                LogUtils.e(TAG, "Terminate Remote management session not supported");
                return;
            }
        }
        LogUtils.d(TAG, "Not terminating RM; not in RM.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unsubscribeInactivityTimerEvents() {
        LogUtils.d(TAG, "Unsubscribing from events.");
        if (this.multiFilterSubscriber != null) {
            this.eventBus.mo358get().unsubscribe(this.multiFilterSubscriber);
            this.multiFilterSubscriber = null;
        }
        stopHandler();
        if (this.mainActivityLifecycleObserver != null) {
            this.mainActivityLifecycleObserverRegistrar.mo358get().removeObserver(this.mainActivityLifecycleObserver);
            this.mainActivityLifecycleObserver = null;
        }
        this.permissionDeniedAlertDialog = null;
        this.terminationAlertDialog = null;
    }

    @VisibleForTesting
    void displayTerminationPopUp(Context context) {
        if (context != null) {
            LogUtils.d(TAG, "Displaying termination popup");
            if (this.terminationAlertDialog == null) {
                this.terminationAlertDialog = getRemoteManagementEndDialog(context, context.getString(R.string.remote_management_termination_popup_message), context.getString(R.string.remote_management_termination_popup_title), context.getString(R.string.remote_management_termination_popup_button));
            }
            this.terminationAlertDialog.show();
            return;
        }
        LogUtils.d(TAG, "Context was null, unable to display termination popup.");
    }

    @VisibleForTesting
    AlertDialog getRemoteManagementEndDialog(Context context, String str, String str2, String str3) {
        return new AlertDialog.Builder(context).setMessage(str).setTitle(str2).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$rrBnbImYmAYv2haZgNvJcwx4ggM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RemoteManagementInactivityHandler.this.lambda$getRemoteManagementEndDialog$5$RemoteManagementInactivityHandler(dialogInterface, i);
            }
        }).setCancelable(false).create();
    }

    public /* synthetic */ void lambda$getPermissionDeniedSubscriberHandler$4$RemoteManagementInactivityHandler(Context context, Message message) {
        terminateRemoteManagement();
        if (this.permissionDeniedAlertDialog == null) {
            if (context != null) {
                this.permissionDeniedAlertDialog = getRemoteManagementEndDialog(context, String.format(context.getString(R.string.remote_management_permission_denied_popup_message), getCareContactFirstName(context)), context.getString(R.string.remote_management_permission_denied_popup_title), context.getString(R.string.remote_management_permission_denied_popup_button));
            } else {
                LogUtils.d(TAG, "Context was null, unable to create and display permission denied popup.");
                return;
            }
        }
        LogUtils.d(TAG, "Displaying permission denied popup");
        this.permissionDeniedAlertDialog.show();
    }

    public /* synthetic */ void lambda$getRemoteManagementEndDialog$5$RemoteManagementInactivityHandler(DialogInterface dialogInterface, int i) {
        LogUtils.d(TAG, "Navigating from RM back to biloba.");
        this.routingService.mo358get().navigate("biloba");
        unsubscribeInactivityTimerEvents();
    }

    public /* synthetic */ void lambda$getRouteChangeSubscriberHandler$1$RemoteManagementInactivityHandler(Message message) {
        if (this.handler.hasMessages(421)) {
            LogUtils.d(TAG, "Route changed, restarting RM inactivity timer.");
            stopHandler();
            startHandler();
        }
    }

    public /* synthetic */ void lambda$getSignOutSubscriberHandler$3$RemoteManagementInactivityHandler(Message message) {
        if (this.handler.hasMessages(421)) {
            LogUtils.d(TAG, "Signed out, stopping timer and unsubscribing from all events.");
            unsubscribeInactivityTimerEvents();
        }
    }

    public /* synthetic */ void lambda$getTerminationSubscriberHandler$2$RemoteManagementInactivityHandler(Message message) {
        try {
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            String str = TAG;
            LogUtils.d(str, "Received RM termination message: " + jSONObject.get("status"));
            if (!jSONObject.get("status").equals("INACTIVE")) {
                return;
            }
            unsubscribeInactivityTimerEvents();
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtils.e(TAG, "Received bad payload from RM termination message.");
        }
    }

    public /* synthetic */ void lambda$startRMInactivityTimer$0$RemoteManagementInactivityHandler(Context context) {
        LogUtils.d(TAG, "RM inactivity timer finished; terminating session.");
        terminateRemoteManagement();
        displayTerminationPopUp(context);
        unsubscribeInactivityTimerEvents();
    }

    public void startRMInactivityTimer(@NonNull final Context context) {
        if (this.multiFilterSubscriber == null) {
            this.multiFilterSubscriber = getMultiFilterSubscriber(context);
        }
        this.eventBus.mo358get().subscribe(this.multiFilterSubscriber);
        if (this.terminationRunnable == null) {
            this.terminationRunnable = new Runnable() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$RemoteManagementInactivityHandler$WhWJ-Q0zUKUP5w-3s48AXFSUuTU
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteManagementInactivityHandler.this.lambda$startRMInactivityTimer$0$RemoteManagementInactivityHandler(context);
                }
            };
        }
        registerMainActivityLifecycleObserver();
        startHandler();
    }
}
