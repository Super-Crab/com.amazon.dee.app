package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.InternalCommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.conversation.CommsConversationService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.conversation.DefaultCommsDynamicFeatureService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceImpl;
import com.amazon.deecomms.core.decoupling.MainActivityLoader;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.features.CommsFeatureFilter;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.google.common.annotations.VisibleForTesting;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class ApplicationModule {
    private final Lazy<CommsDelegateBase> commsDelegateLazy;
    private final Lazy<Context> contextLazy;
    private final Lazy<DeviceInformation> deviceInformationLazy;
    private final String deviceNameTemplate;
    private final Lazy<IdentityService> identityServiceLazy;

    public ApplicationModule(@NonNull Lazy<Context> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<CommsDelegateBase> lazy3, @NonNull String str, @NonNull Lazy<DeviceInformation> lazy4) {
        this.contextLazy = lazy;
        this.identityServiceLazy = lazy2;
        this.commsDelegateLazy = lazy3;
        this.deviceNameTemplate = str;
        this.deviceInformationLazy = lazy4;
    }

    static /* synthetic */ Context lambda$new$0(Context context) {
        return context;
    }

    static /* synthetic */ IdentityService lambda$new$1() {
        return null;
    }

    static /* synthetic */ CommsDelegateBase lambda$new$2(CommsDelegateBase commsDelegateBase) {
        return commsDelegateBase;
    }

    static /* synthetic */ DeviceInformation lambda$new$3() {
        return null;
    }

    static /* synthetic */ Context lambda$new$4(Context context) {
        return context;
    }

    static /* synthetic */ IdentityService lambda$new$5(IdentityService identityService) {
        return identityService;
    }

    static /* synthetic */ CommsDelegateBase lambda$new$6(CommsDelegateBase commsDelegateBase) {
        return commsDelegateBase;
    }

    static /* synthetic */ DeviceInformation lambda$new$7() {
        return null;
    }

    @Provides
    @Singleton
    public AlexaCommsService providesAlexaCommsService(@NonNull Lazy<IdentityService> lazy, @NonNull Lazy<EventBus> lazy2, @NonNull Lazy<OobeService> lazy3, @NonNull Lazy<MessagingReceiver> lazy4, @NonNull Lazy<ContentSharingService> lazy5, @NonNull Lazy<ConversationService> lazy6, @NonNull Lazy<CommsDeviceSupport> lazy7, @NonNull Lazy<CommsDynamicFeatureService> lazy8, @NonNull Lazy<CommsManager> lazy9, @NonNull Lazy<FeatureFilter> lazy10, @NonNull Lazy<AccessoriesHardwareIntentHandler> lazy11, @NonNull Lazy<AudioContentManager> lazy12, @NonNull Lazy<DriveModeService> lazy13, @NonNull Lazy<DriveModeEventHandler> lazy14, @NonNull Lazy<CommsDriveModeCardProvider> lazy15, @NonNull Lazy<PhoneCallControllerManager> lazy16, @NonNull Lazy<MainActivityLoader> lazy17, @NonNull Lazy<ProfileSelectionService> lazy18) {
        return new AlexaCommsServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18);
    }

    @Provides
    @Singleton
    @Named("AppVersion")
    public String providesAppVersion(@NonNull Context context) {
        return Utils.getAppVersion(context);
    }

    @Provides
    @Singleton
    public ApplicationManager providesApplicationManager() {
        return new ApplicationManager(this.contextLazy.mo358get(), this.commsDelegateLazy.mo358get());
    }

    @Provides
    @Singleton
    public CommsDeviceSupport providesCommsDeviceSupport(@NonNull EncryptionUtils encryptionUtils) {
        return new CommsDeviceSupport(encryptionUtils);
    }

    @Provides
    @Singleton
    public CommsDynamicFeatureService providesCommsDynamicFeatureService(@NonNull CommsManager commsManager) {
        return new DefaultCommsDynamicFeatureService(commsManager);
    }

    @Provides
    @Singleton
    public CommsManager providesCommsManager(@NonNull Context context) {
        return new InternalCommsManager(context);
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return this.contextLazy.mo358get();
    }

    @Provides
    @Singleton
    public ConversationService providesConversationService(@NonNull Lazy<IdentityService> lazy, @NonNull String str) {
        final CommsComponent component = CommsDaggerWrapper.getComponent();
        component.getClass();
        Lazy lazy2 = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$riPK_h2nEj7iMJIkJfKiA4pBAJ4
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsComponent.this.getCommsManager();
            }
        };
        component.getClass();
        Lazy lazy3 = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$3_-HfOuIfMBAqzMjJd1WeB-x3_M
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsComponent.this.getCommsDeviceSupport();
            }
        };
        component.getClass();
        Lazy lazy4 = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$eYfEdVuIQhsbZAgRmIiOAsTgNRM
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsComponent.this.getCommsDynamicFeatureService();
            }
        };
        component.getClass();
        return new CommsConversationService(lazy2, lazy3, lazy, lazy4, str, new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$Y8MiDqEEEVvOUlJqxn6E0tbT_kk
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsComponent.this.getEventBus();
            }
        });
    }

    @Provides
    @Singleton
    public DeviceInformation providesDeviceInformation() {
        return this.deviceInformationLazy.mo358get();
    }

    @Provides
    @Singleton
    public String providesDeviceNameTemplate() {
        return this.deviceNameTemplate;
    }

    @Provides
    @Singleton
    public EncryptionUtils providesEncryptionUtils(@NonNull Context context) {
        return new EncryptionUtils(context);
    }

    @Provides
    @Singleton
    public FeatureFilter providesFeatureFilter(@NonNull CommsDeviceSupport commsDeviceSupport, @NonNull DeviceInformation deviceInformation) {
        return new CommsFeatureFilter(commsDeviceSupport, deviceInformation);
    }

    @Provides
    @Singleton
    public IdentityService providesIdentityService() {
        return this.identityServiceLazy.mo358get();
    }

    @VisibleForTesting
    public ApplicationModule(@NonNull final Context context, @NonNull final CommsDelegateBase commsDelegateBase) {
        this.contextLazy = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$ApplicationModule$7UWTFBHt-1xJFz8MUGfls1zeZpI
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return context;
            }
        };
        this.identityServiceLazy = $$Lambda$ApplicationModule$mL34i_d1KACL_JIyUXWWpF0CM.INSTANCE;
        this.deviceNameTemplate = null;
        this.commsDelegateLazy = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$ApplicationModule$Tq4QUqqJ2LhuF5TG9V5ZUBWjEu0
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsDelegateBase.this;
            }
        };
        this.deviceInformationLazy = $$Lambda$ApplicationModule$WFoiEzaFOcvzbIBeeKRlPhFpCXk.INSTANCE;
    }

    @VisibleForTesting
    public ApplicationModule(@NonNull final Context context, @NonNull final CommsDelegateBase commsDelegateBase, @NonNull final IdentityService identityService) {
        this.contextLazy = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$ApplicationModule$OGOX97n-1rHKosedN9-Qbwa7_3U
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return context;
            }
        };
        this.identityServiceLazy = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$ApplicationModule$6sm_i-n6O1Szbd18VTOHjr_Vf-g
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return IdentityService.this;
            }
        };
        this.deviceNameTemplate = null;
        this.commsDelegateLazy = new Lazy() { // from class: com.amazon.deecomms.core.-$$Lambda$ApplicationModule$PUv7aqYNjR6dKERdLooeJ-6_EgM
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return CommsDelegateBase.this;
            }
        };
        this.deviceInformationLazy = $$Lambda$ApplicationModule$WFOnlCeme1qfA_jEKSum8o1I5gw.INSTANCE;
    }
}
