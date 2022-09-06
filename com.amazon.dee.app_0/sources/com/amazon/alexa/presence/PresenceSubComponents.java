package com.amazon.alexa.presence;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.amazon.alexa.presence.receiver.ScanCheckAlarmReceiver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceSubComponents {
    private static final String TAG = Presence.tag();
    private final Lazy<AlexaPresenceBluetoothReceiver> alexaPresenceBluetoothReceiver;
    private final Lazy<ApplicationLifecycleService> applicationLifecycleService;
    private final Context context;
    private final Lazy<PresenceAlarmManager> presenceAlarmManager;
    private final Lazy<PresenceApplicationLifecycleObserver> presenceApplicationLifecycleObserver;
    private final Lazy<ScanCheckAlarmReceiver> scanCheckAlarmReceiver;

    @Inject
    public PresenceSubComponents(Lazy<ApplicationLifecycleService> lazy, Lazy<PresenceApplicationLifecycleObserver> lazy2, Lazy<AlexaPresenceBluetoothReceiver> lazy3, Lazy<PresenceAlarmManager> lazy4, Lazy<ScanCheckAlarmReceiver> lazy5, Context context) {
        this.applicationLifecycleService = lazy;
        this.presenceApplicationLifecycleObserver = lazy2;
        this.alexaPresenceBluetoothReceiver = lazy3;
        this.presenceAlarmManager = lazy4;
        this.scanCheckAlarmReceiver = lazy5;
        this.context = context;
    }

    private void cancelPeriodicScanAlarm() {
        Log.i(TAG, "Canceling scheduled alarm used to restart scan");
        this.presenceAlarmManager.mo358get().cancel();
        Log.i(TAG, "Unregistering from scan check alarm triggers");
        try {
            this.context.unregisterReceiver(this.scanCheckAlarmReceiver.mo358get());
        } catch (Exception e) {
            Log.e(TAG, "Alarm Broadcast receiver already unregistered", e);
        }
    }

    private void observeApplicationLifecycleChanges() {
        Log.i(TAG, "Observing application lifecycle changes");
        this.applicationLifecycleService.mo358get().addObserver(this.presenceApplicationLifecycleObserver.mo358get());
    }

    private void observeBluetoothStateChange() {
        Log.i(TAG, "Listening to bluetooth state change events");
        try {
            this.context.registerReceiver(this.alexaPresenceBluetoothReceiver.mo358get(), new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        } catch (Exception e) {
            Log.e(TAG, "Bluetooth Broadcast receiver already registered", e);
        }
    }

    private void removeApplicationLifecycleObserver() {
        Log.i(TAG, "Removing application lifecycle observer");
        this.applicationLifecycleService.mo358get().removeObserver(this.presenceApplicationLifecycleObserver.mo358get());
    }

    private void setPeriodicScanAlarm() {
        Log.i(TAG, "Listening to scan check alarm triggers");
        try {
            this.context.registerReceiver(this.scanCheckAlarmReceiver.mo358get(), new IntentFilter(PresenceAlarmManager.ALARM_INTENT_ACTION));
        } catch (Exception e) {
            Log.e(TAG, "Alarm Broadcast receiver already registered", e);
        }
        Log.i(TAG, "Scheduling a repeated alarm to restart scan");
        this.presenceAlarmManager.mo358get().set();
    }

    private void unregisterBluetoothStateChange() {
        Log.i(TAG, "Unregistering from bluetooth state change events");
        try {
            this.context.unregisterReceiver(this.alexaPresenceBluetoothReceiver.mo358get());
        } catch (Exception e) {
            Log.e(TAG, "Bluetooth Broadcast receiver already unregistered", e);
        }
    }

    public void destroyPresenceComponentsOnNoDomainRequest() {
        removeApplicationLifecycleObserver();
        unregisterBluetoothStateChange();
        cancelPeriodicScanAlarm();
    }

    public PersonIdProvider getPersonIdProvider() {
        return (PersonIdProvider) GeneratedOutlineSupport1.outline21(PersonIdProvider.class);
    }

    public void initializePresenceComponentsAfterDomainRequest() {
        observeApplicationLifecycleChanges();
        observeBluetoothStateChange();
        setPeriodicScanAlarm();
    }
}
