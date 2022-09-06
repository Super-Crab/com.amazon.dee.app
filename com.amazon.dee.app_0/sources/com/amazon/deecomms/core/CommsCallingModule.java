package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.impl.CallingAPI;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.impl.CallingAPIPopulator;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.notifications.PushNotificationManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class CommsCallingModule {
    @Provides
    @Singleton
    public ICallingAPI providesCallingAPI(@NonNull InitiationLogicFactory initiationLogicFactory, @NonNull Context context, @NonNull CallingAPIMonitor callingAPIMonitor, @NonNull CallingAPIPopulator callingAPIPopulator, @NonNull CommsDirectiveHandler commsDirectiveHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull PushNotificationManager pushNotificationManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull DriveModeCallPermissionHandler driveModeCallPermissionHandler, @NonNull EventBus eventBus) {
        return new CallingAPI(initiationLogicFactory, new CallInitiator(MetricKeys.CALL_INITIATED_FROM_API), new ContactsDataStoreUtil(context), callingAPIMonitor, callingAPIPopulator, context, commsDirectiveHandler, commsIdentityManager, pushNotificationManager, commsAccessorySessionListener, makeNativeCallHandler, driveModeCallPermissionHandler, eventBus);
    }
}
