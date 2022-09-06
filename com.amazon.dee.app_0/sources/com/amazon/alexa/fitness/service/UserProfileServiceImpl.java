package com.amazon.alexa.fitness.service;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.configuration.UserProfileServiceConfiguration;
import com.amazon.alexa.fitness.configuration.UserProfileServiceConfigurationProvider;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.model.event.UserProfileResponseDTO;
import com.amazon.alexa.fitness.model.event.UserProfileUpdateDTO;
import com.amazon.alexa.fitness.time.ISO8601;
import com.amazon.alexa.fitness.util.GsonUtils;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.fitness.util.GuavaUtilsKt;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.gson.JsonParseException;
import java.util.Locale;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserProfileServiceImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\u000e\u0010\u0018\u001a\u00020\u0019*\u0004\u0018\u00010\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u0019*\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u0004\u0018\u00010\u001aH\u0002J\u000e\u0010\u001e\u001a\u00020\u001f*\u0004\u0018\u00010\u001aH\u0002J\f\u0010 \u001a\u00020!*\u0004\u0018\u00010\u001aJ\f\u0010\"\u001a\u00020\u001a*\u00020!H\u0002J\f\u0010#\u001a\u00020\u0010*\u00020\u0012H\u0002J\u000e\u0010$\u001a\u0004\u0018\u00010%*\u00020\u001dH\u0002J\u000e\u0010&\u001a\u00020\u0012*\u0004\u0018\u00010%H\u0002J\u0013\u0010'\u001a\u00020(*\u0004\u0018\u00010(H\u0002¢\u0006\u0002\u0010)R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/alexa/fitness/service/UserProfileServiceImpl;", "Lcom/amazon/alexa/fitness/service/UserProfileService;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfigurationProvider;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "userProfileRepository", "Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfigurationProvider;Lcom/amazon/alexa/fitness/identity/IdentityManager;Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;Lcom/amazon/alexa/fitness/logs/ILog;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfiguration;", "createUserProfileResponseMessage", "Lcom/amazon/alexa/eventbus/api/Message;", "defaultUserProfileResponseDTO", "Lcom/amazon/alexa/fitness/model/event/UserProfileResponseDTO;", "handleUserProfileRequestedEvent", "handleUserProfileUpdatedEvent", "", "message", "start", "isUserProfileRequestEvent", "", "", "isUserProfileUpdateEvent", "parseAsUserProfileUpdateDTO", "Lcom/amazon/alexa/fitness/model/event/UserProfileUpdateDTO;", "toDateTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "toGender", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;", "toGenderString", "toMessage", "toUserProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "toUserProfileResponseDTO", "validateHeight", "", "(Ljava/lang/Double;)D", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileServiceConfigurationProvider configurationProvider;
    private final IdentityManager identityManager;
    private final ILog log;
    private final UserProfileRepository userProfileRepository;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UserProfile.Gender.values().length];

        static {
            $EnumSwitchMapping$0[UserProfile.Gender.MALE.ordinal()] = 1;
            $EnumSwitchMapping$0[UserProfile.Gender.FEMALE.ordinal()] = 2;
        }
    }

    @Inject
    public UserProfileServiceImpl(@NotNull UserProfileServiceConfigurationProvider configurationProvider, @NotNull IdentityManager identityManager, @NotNull UserProfileRepository userProfileRepository, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        Intrinsics.checkParameterIsNotNull(userProfileRepository, "userProfileRepository");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.configurationProvider = configurationProvider;
        this.identityManager = identityManager;
        this.userProfileRepository = userProfileRepository;
        this.log = log;
    }

    private final Message createUserProfileResponseMessage() {
        return toMessage(toUserProfileResponseDTO(this.userProfileRepository.getUserProfileForCurrentUser()));
    }

    private final UserProfileResponseDTO defaultUserProfileResponseDTO() {
        UserProfileServiceConfiguration configuration = getConfiguration();
        return new UserProfileResponseDTO(configuration.getDefaultHeightInCm(), configuration.getDefaultWeightInKg(), configuration.getDefaultGender(), configuration.getDefaultBirthday());
    }

    private final UserProfileServiceConfiguration getConfiguration() {
        return this.configurationProvider.provideUserProfileServiceConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Message handleUserProfileRequestedEvent() {
        Message createUserProfileResponseMessage = createUserProfileResponseMessage();
        ((EventBus) GeneratedOutlineSupport1.outline20(EventBus.class)).publish(createUserProfileResponseMessage);
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "UserProfileService", "Sent user profile response event: " + createUserProfileResponseMessage, null, 4, null);
        return createUserProfileResponseMessage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleUserProfileUpdatedEvent(Message message) {
        UserProfile userProfile;
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("User profile update event payload: ");
        outline107.append(message.getPayloadAsString());
        ILog.DefaultImpls.debug$default(iLog, "UserProfileService", outline107.toString(), null, 4, null);
        UserProfileUpdateDTO parseAsUserProfileUpdateDTO = parseAsUserProfileUpdateDTO(message.getPayloadAsString());
        if (parseAsUserProfileUpdateDTO == null || (userProfile = toUserProfile(parseAsUserProfileUpdateDTO)) == null) {
            return;
        }
        this.userProfileRepository.saveUserProfile(userProfile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isUserProfileRequestEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, getConfiguration().getEventTypeUserProfileRequest());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isUserProfileUpdateEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, getConfiguration().getEventTypeUserProfileUpdate());
    }

    private final UserProfileUpdateDTO parseAsUserProfileUpdateDTO(@Nullable String str) {
        UserProfileUpdateDTO userProfileUpdateDTO;
        boolean isBlank;
        try {
        } catch (Exception e) {
            ILog iLog = this.log;
            iLog.error("UserProfileService", "Failed to parse user profile update event. Payload: " + str, e);
            userProfileUpdateDTO = null;
        }
        if (str != null) {
            try {
                isBlank = StringsKt__StringsJVMKt.isBlank(str);
                if (!isBlank) {
                    Object fromJson = GsonUtils.Companion.getGson().fromJson(str, (Class<Object>) UserProfileUpdateDTO.class);
                    Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(this, T::class.java)");
                    userProfileUpdateDTO = (UserProfileUpdateDTO) fromJson;
                    if (userProfileUpdateDTO != null) {
                        return userProfileUpdateDTO;
                    }
                } else {
                    throw new JsonParseException("Cannot parse blank JSON String.");
                }
            } catch (JsonParseException e2) {
                throw new IllegalArgumentException("Failed to deserialize JSON String: '" + str + Chars.QUOTE, e2);
            }
        }
        ILog iLog2 = this.log;
        ILog.DefaultImpls.debug$default(iLog2, "UserProfileService", getConfiguration().getEventTypeUserProfileUpdate() + " event payload was null.", null, 4, null);
        return null;
    }

    private final DateTime toDateTime(@Nullable String str) {
        DateTime resultOrThrow;
        if (str == null || (resultOrThrow = ISO8601.Companion.parseDateTime(str).resultOrThrow()) == null) {
            throw new NullPointerException("Birthday is mandatory but was null.");
        }
        return resultOrThrow;
    }

    private final String toGenderString(@NotNull UserProfile.Gender gender) {
        int i = WhenMappings.$EnumSwitchMapping$0[gender.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return "female";
        }
        return "male";
    }

    private final Message toMessage(@NotNull UserProfileResponseDTO userProfileResponseDTO) {
        Message.Builder builder = new Message.Builder();
        builder.setEventType(getConfiguration().getEventTypeUserProfileResponse());
        builder.setPayload(GsonUtilsKt.toJsonWithNulls(userProfileResponseDTO));
        Message build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Message.Builder().also {…ulls())\n        }.build()");
        return build;
    }

    private final UserProfile toUserProfile(@NotNull UserProfileUpdateDTO userProfileUpdateDTO) {
        try {
            String userIdentityDirectedId = this.identityManager.getUserIdentityDirectedId();
            if (userIdentityDirectedId == null) {
                Intrinsics.throwNpe();
            }
            return new UserProfile(userIdentityDirectedId, toGender(userProfileUpdateDTO.getGender()), toDateTime(userProfileUpdateDTO.getBirthday()), validateHeight(userProfileUpdateDTO.getHeightInCentimeters()), userProfileUpdateDTO.getWeightInKilograms());
        } catch (Exception e) {
            this.log.error("UserProfileService", "An error occurred creating the UserProfile.", e);
            return null;
        }
    }

    private final UserProfileResponseDTO toUserProfileResponseDTO(@Nullable UserProfile userProfile) {
        if (userProfile != null) {
            return new UserProfileResponseDTO(Double.valueOf(userProfile.getHeightInCentimetres()), userProfile.getWeightInKilograms(), toGenderString(userProfile.getGender()), ISO8601.Companion.serializeDate(userProfile.getDateOfBirth()));
        }
        return defaultUserProfileResponseDTO();
    }

    private final double validateHeight(@Nullable Double d) {
        if (d != null) {
            d.doubleValue();
            if (d.doubleValue() >= getConfiguration().getMinimumHeightInCm() && d.doubleValue() <= getConfiguration().getMaximumHeightInCm()) {
                return d.doubleValue();
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Height must between ");
            outline107.append(getConfiguration().getMinimumHeightInCm());
            outline107.append("cm and ");
            outline107.append(getConfiguration().getMaximumHeightInCm());
            outline107.append("cm inclusive, was: '");
            outline107.append(d);
            outline107.append(Chars.QUOTE);
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new NullPointerException("Height is mandatory but was null.");
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        ILog.DefaultImpls.debug$default(this.log, "UserProfileService", "start() invoked...", null, 4, null);
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subscribing to ");
        outline107.append(getConfiguration().getEventTypeUserProfileUpdate());
        outline107.append(" event type");
        ILog.DefaultImpls.debug$default(iLog, "UserProfileService", outline107.toString(), null, 4, null);
        Optional optional = ComponentRegistry.getInstance().get(EventBus.class);
        Intrinsics.checkExpressionValueIsNotNull(optional, "ComponentRegistry.getIns…get(EventBus::class.java)");
        UUID uuid = (UUID) GuavaUtilsKt.flatMap(optional, UserProfileServiceImpl$start$subscriberIdUpdateEvent$1.INSTANCE).transform(new Function<T, V>() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdUpdateEvent$2
            @Override // com.google.common.base.Function
            /* renamed from: apply */
            public final UUID mo8172apply(@Nullable MultiFilterSubscriber multiFilterSubscriber) {
                if (multiFilterSubscriber == null) {
                    Intrinsics.throwNpe();
                }
                return multiFilterSubscriber.subscribe(new MessageFilter() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdUpdateEvent$2.1
                    @Override // com.amazon.alexa.eventbus.api.MessageFilter
                    public final boolean isMatch(@NotNull Message message) {
                        boolean isUserProfileUpdateEvent;
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        isUserProfileUpdateEvent = UserProfileServiceImpl.this.isUserProfileUpdateEvent(message.getEventType());
                        return isUserProfileUpdateEvent;
                    }
                }, new MessageHandler() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdUpdateEvent$2.2
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(@NotNull Message message) {
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        UserProfileServiceImpl.this.handleUserProfileUpdatedEvent(message);
                    }
                });
            }
        }).orNull();
        ILog iLog2 = this.log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Subscribing to ");
        outline1072.append(getConfiguration().getEventTypeUserProfileRequest());
        outline1072.append(" event type");
        ILog.DefaultImpls.debug$default(iLog2, "UserProfileService", outline1072.toString(), null, 4, null);
        Optional optional2 = ComponentRegistry.getInstance().get(EventBus.class);
        Intrinsics.checkExpressionValueIsNotNull(optional2, "ComponentRegistry.getIns…get(EventBus::class.java)");
        UUID uuid2 = (UUID) GuavaUtilsKt.flatMap(optional2, UserProfileServiceImpl$start$subscriberIdRequestEvent$1.INSTANCE).transform(new Function<T, V>() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdRequestEvent$2
            @Override // com.google.common.base.Function
            /* renamed from: apply */
            public final UUID mo8172apply(@Nullable MultiFilterSubscriber multiFilterSubscriber) {
                if (multiFilterSubscriber == null) {
                    Intrinsics.throwNpe();
                }
                return multiFilterSubscriber.subscribe(new MessageFilter() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdRequestEvent$2.1
                    @Override // com.amazon.alexa.eventbus.api.MessageFilter
                    public final boolean isMatch(@NotNull Message message) {
                        boolean isUserProfileRequestEvent;
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        isUserProfileRequestEvent = UserProfileServiceImpl.this.isUserProfileRequestEvent(message.getEventType());
                        return isUserProfileRequestEvent;
                    }
                }, new MessageHandler() { // from class: com.amazon.alexa.fitness.service.UserProfileServiceImpl$start$subscriberIdRequestEvent$2.2
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(@NotNull Message it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        UserProfileServiceImpl.this.handleUserProfileRequestedEvent();
                    }
                });
            }
        }).orNull();
        if (uuid == null) {
            ILog iLog3 = this.log;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Failed to subscribe to ");
            outline1073.append(getConfiguration().getEventTypeUserProfileUpdate());
            outline1073.append(" event.");
            ILog.DefaultImpls.error$default(iLog3, "UserProfileService", outline1073.toString(), null, 4, null);
            Unit unit = Unit.INSTANCE;
        }
        if (uuid2 != null) {
            return;
        }
        ILog iLog4 = this.log;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Failed to subscribe to ");
        outline1074.append(getConfiguration().getEventTypeUserProfileRequest());
        outline1074.append(" event.");
        ILog.DefaultImpls.error$default(iLog4, "UserProfileService", outline1074.toString(), null, 4, null);
        Unit unit2 = Unit.INSTANCE;
    }

    @NotNull
    public final UserProfile.Gender toGender(@Nullable String str) {
        String str2;
        if (str != null) {
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            str2 = str.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.String).toLowerCase(locale)");
        } else {
            str2 = null;
        }
        if (str2 != null) {
            int hashCode = str2.hashCode();
            if (hashCode != -1278174388) {
                if (hashCode == 3343885 && str2.equals("male")) {
                    return UserProfile.Gender.MALE;
                }
            } else if (str2.equals("female")) {
                return UserProfile.Gender.FEMALE;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline73("Invalid Gender string, must be 'male' or 'female', was: '", str, Chars.QUOTE));
    }
}
