package com.amazon.deecomms.core;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.telecom.Connection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.TelecomConnectionService;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.inject.Singleton;
@TargetApi(26)
@Module
/* loaded from: classes12.dex */
public class TelecomModule {
    @Provides
    @Singleton
    public Map<String, Connection> providesCallIdToTelecomConnection() {
        return GeneratedOutlineSupport1.outline136();
    }

    @Provides
    @Singleton
    public PhoneAccount providesPhoneAccount(@NonNull PhoneAccountHandle phoneAccountHandle) {
        return PhoneAccount.builder(phoneAccountHandle, TelecomConstants.PHONE_HANDLE_ID).setCapabilities(2048).build();
    }

    @Provides
    @Singleton
    public PhoneAccountHandle providesPhoneAccountHandle(@NonNull Context context) {
        return new PhoneAccountHandle(new ComponentName(context, TelecomConnectionService.class), TelecomConstants.PHONE_HANDLE_ID);
    }

    @Provides
    @Singleton
    public TelecomBridge providesTelecomBridge(@NonNull TelecomManager telecomManager, @NonNull PhoneAccountHandle phoneAccountHandle, @NonNull Map<String, Connection> map, @NonNull Queue<String> queue, @NonNull Object obj) {
        return new TelecomBridge(telecomManager, phoneAccountHandle, map, queue, obj);
    }

    @Provides
    @Singleton
    public TelecomCallAudioRouteObservable providesTelecomCallAudioRouteObservable() {
        return new TelecomCallAudioRouteObservable();
    }

    @Provides
    @Singleton
    public Queue<String> providesTelecomCallIds() {
        return new LinkedBlockingQueue();
    }

    @Provides
    @Singleton
    public Object providesTelecomLock() {
        return new Object();
    }
}
