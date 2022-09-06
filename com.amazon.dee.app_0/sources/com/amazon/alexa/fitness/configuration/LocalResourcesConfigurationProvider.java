package com.amazon.alexa.fitness.configuration;

import android.content.res.Resources;
import com.amazon.alexa.fitness.R;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocalResourcesConfigurationProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u001fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/configuration/LocalResourcesConfigurationProvider;", "Lcom/amazon/alexa/fitness/configuration/ConfigurationProvider;", "resources", "Landroid/content/res/Resources;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Landroid/content/res/Resources;Lcom/amazon/alexa/fitness/logs/ILog;)V", "alexaCapabilityAgentServiceConfiguration", "Lcom/amazon/alexa/fitness/configuration/AlexaFitnessCapabilityAgentServiceConfiguration;", "cacheProviderConfiguration", "Lcom/amazon/alexa/fitness/configuration/CacheProviderConfiguration;", "fitnessAccessorySessionMonitorConfiguration", "Lcom/amazon/alexa/fitness/configuration/FitnessAccessorySessionMonitorConfiguration;", "fitnessDataHandlerConfiguration", "Lcom/amazon/alexa/fitness/configuration/FitnessDataHandlerConfiguration;", "fitnessSessionCommandReceiverConfiguration", "Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfiguration;", "recoveryConfiguration", "Lcom/amazon/alexa/fitness/configuration/RecoveryConfiguration;", "speechletEventEmitterConfiguration", "Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfiguration;", "userProfileServiceConfiguration", "Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfiguration;", "provideAlexaCapabilityAgentServiceConfiguration", "provideCacheProviderConfiguration", "provideFitnessAccessorySessionMonitorConfiguration", "provideFitnessDataHandlerConfiguration", "provideFitnessSessionCommandReceiverConfiguration", "provideRecoveryConfiguration", "provideSpeechletEventEmitterConfiguration", "provideUserProfileServiceConfiguration", "AlexaCapabilityInterfaceConfigurations", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocalResourcesConfigurationProvider implements ConfigurationProvider {
    private final AlexaFitnessCapabilityAgentServiceConfiguration alexaCapabilityAgentServiceConfiguration;
    private final CacheProviderConfiguration cacheProviderConfiguration;
    private final FitnessAccessorySessionMonitorConfiguration fitnessAccessorySessionMonitorConfiguration;
    private final FitnessDataHandlerConfiguration fitnessDataHandlerConfiguration;
    private final FitnessSessionCommandReceiverConfiguration fitnessSessionCommandReceiverConfiguration;
    private final ILog log;
    private final RecoveryConfiguration recoveryConfiguration;
    private final Resources resources;
    private final SpeechletEventEmitterConfiguration speechletEventEmitterConfiguration;
    private final UserProfileServiceConfiguration userProfileServiceConfiguration;

    /* compiled from: LocalResourcesConfigurationProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ.\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/LocalResourcesConfigurationProvider$AlexaCapabilityInterfaceConfigurations;", "", "directives", "", "", "metrics", "([Ljava/lang/String;[Ljava/lang/String;)V", "getDirectives", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getMetrics", "component1", "component2", "copy", "([Ljava/lang/String;[Ljava/lang/String;)Lcom/amazon/alexa/fitness/configuration/LocalResourcesConfigurationProvider$AlexaCapabilityInterfaceConfigurations;", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    private static final class AlexaCapabilityInterfaceConfigurations {
        @NotNull
        private final String[] directives;
        @NotNull
        private final String[] metrics;

        public AlexaCapabilityInterfaceConfigurations(@NotNull String[] directives, @NotNull String[] metrics) {
            Intrinsics.checkParameterIsNotNull(directives, "directives");
            Intrinsics.checkParameterIsNotNull(metrics, "metrics");
            this.directives = directives;
            this.metrics = metrics;
        }

        public static /* synthetic */ AlexaCapabilityInterfaceConfigurations copy$default(AlexaCapabilityInterfaceConfigurations alexaCapabilityInterfaceConfigurations, String[] strArr, String[] strArr2, int i, Object obj) {
            if ((i & 1) != 0) {
                strArr = alexaCapabilityInterfaceConfigurations.directives;
            }
            if ((i & 2) != 0) {
                strArr2 = alexaCapabilityInterfaceConfigurations.metrics;
            }
            return alexaCapabilityInterfaceConfigurations.copy(strArr, strArr2);
        }

        @NotNull
        public final String[] component1() {
            return this.directives;
        }

        @NotNull
        public final String[] component2() {
            return this.metrics;
        }

        @NotNull
        public final AlexaCapabilityInterfaceConfigurations copy(@NotNull String[] directives, @NotNull String[] metrics) {
            Intrinsics.checkParameterIsNotNull(directives, "directives");
            Intrinsics.checkParameterIsNotNull(metrics, "metrics");
            return new AlexaCapabilityInterfaceConfigurations(directives, metrics);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!Intrinsics.areEqual(obj != null ? obj.getClass() : null, AlexaCapabilityInterfaceConfigurations.class)) {
                return false;
            }
            if (obj != null) {
                AlexaCapabilityInterfaceConfigurations alexaCapabilityInterfaceConfigurations = (AlexaCapabilityInterfaceConfigurations) obj;
                return Arrays.equals(this.directives, alexaCapabilityInterfaceConfigurations.directives) && Arrays.equals(this.metrics, alexaCapabilityInterfaceConfigurations.metrics);
            }
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.configuration.LocalResourcesConfigurationProvider.AlexaCapabilityInterfaceConfigurations");
        }

        @NotNull
        public final String[] getDirectives() {
            return this.directives;
        }

        @NotNull
        public final String[] getMetrics() {
            return this.metrics;
        }

        public int hashCode() {
            return Arrays.hashCode(this.directives) * Arrays.hashCode(this.metrics);
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaCapabilityInterfaceConfigurations(directives=");
            outline107.append(Arrays.toString(this.directives));
            outline107.append(", metrics=");
            return GeneratedOutlineSupport1.outline91(outline107, Arrays.toString(this.metrics), ")");
        }
    }

    @Inject
    public LocalResourcesConfigurationProvider(@NotNull Resources resources, @NotNull ILog log) {
        Double doubleOrNull;
        Double doubleOrNull2;
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.resources = resources;
        this.log = log;
        Resources resources2 = this.resources;
        String string = resources2.getString(R.string.alexa_capability_interface_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(R.string.al…apability_interface_name)");
        String string2 = resources2.getString(R.string.alexa_capability_interface_version);
        Intrinsics.checkExpressionValueIsNotNull(string2, "it.getString(R.string.al…bility_interface_version)");
        String[] stringArray = resources2.getStringArray(R.array.alexa_capability_interface_configuration_directives);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "it.getStringArray(R.arra…configuration_directives)");
        String[] stringArray2 = resources2.getStringArray(R.array.alexa_capability_interface_configuration_metrics);
        Intrinsics.checkExpressionValueIsNotNull(stringArray2, "it.getStringArray(R.arra…ce_configuration_metrics)");
        this.alexaCapabilityAgentServiceConfiguration = new AlexaFitnessCapabilityAgentServiceConfiguration(string, string2, GsonUtilsKt.toJson(new AlexaCapabilityInterfaceConfigurations(stringArray, stringArray2)));
        Resources resources3 = this.resources;
        this.cacheProviderConfiguration = new CacheProviderConfiguration(resources3.getInteger(R.integer.cache_provider_alexa_fitness_session_size_in_mb), resources3.getInteger(R.integer.cache_provider_fitness_session_event_size_in_mb), resources3.getInteger(R.integer.cache_provider_session_summary_size_in_mb), resources3.getInteger(R.integer.cache_provider_user_profile_size_in_mb));
        this.fitnessAccessorySessionMonitorConfiguration = new FitnessAccessorySessionMonitorConfiguration(this.resources.getInteger(R.integer.fitness_accessory_session_monitor_timeout_in_millis));
        Resources resources4 = this.resources;
        this.speechletEventEmitterConfiguration = new SpeechletEventEmitterConfiguration(resources4.getInteger(R.integer.speechlet_event_emitter_base_delay_in_millis), resources4.getInteger(R.integer.speechlet_event_emitter_maximum_attempts));
        Resources resources5 = this.resources;
        String string3 = resources5.getString(R.string.user_profile_minimum_height_in_cm);
        Intrinsics.checkExpressionValueIsNotNull(string3, "it.getString(R.string.us…ile_minimum_height_in_cm)");
        double parseDouble = Double.parseDouble(string3);
        String string4 = resources5.getString(R.string.user_profile_maximum_height_in_cm);
        Intrinsics.checkExpressionValueIsNotNull(string4, "it.getString(R.string.us…ile_maximum_height_in_cm)");
        double parseDouble2 = Double.parseDouble(string4);
        String string5 = resources5.getString(R.string.user_profile_default_gender);
        Intrinsics.checkExpressionValueIsNotNull(string5, "it.getString(R.string.user_profile_default_gender)");
        String string6 = resources5.getString(R.string.user_profile_default_birthday);
        Intrinsics.checkExpressionValueIsNotNull(string6, "it.getString(R.string.us…profile_default_birthday)");
        String string7 = resources5.getString(R.string.user_profile_default_height_in_cm);
        Intrinsics.checkExpressionValueIsNotNull(string7, "it.getString(R.string.us…ile_default_height_in_cm)");
        doubleOrNull = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(string7);
        String string8 = resources5.getString(R.string.user_profile_default_weight_in_kg);
        Intrinsics.checkExpressionValueIsNotNull(string8, "it.getString(R.string.us…ile_default_weight_in_kg)");
        doubleOrNull2 = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(string8);
        String string9 = resources5.getString(R.string.user_profile_event_type_update);
        Intrinsics.checkExpressionValueIsNotNull(string9, "it.getString(R.string.us…rofile_event_type_update)");
        String string10 = resources5.getString(R.string.user_profile_event_type_request);
        Intrinsics.checkExpressionValueIsNotNull(string10, "it.getString(R.string.us…ofile_event_type_request)");
        String string11 = resources5.getString(R.string.user_profile_event_type_response);
        Intrinsics.checkExpressionValueIsNotNull(string11, "it.getString(R.string.us…file_event_type_response)");
        this.userProfileServiceConfiguration = new UserProfileServiceConfiguration(parseDouble, parseDouble2, string5, string6, doubleOrNull, doubleOrNull2, string9, string10, string11);
        this.fitnessDataHandlerConfiguration = new FitnessDataHandlerConfiguration(this.resources.getInteger(R.integer.fitness_data_handler_no_fitness_data_timeout_in_millis));
        Resources resources6 = this.resources;
        String string12 = resources6.getString(R.string.workout_gui_toggle_event_type_update);
        Intrinsics.checkExpressionValueIsNotNull(string12, "it.getString(R.string.wo…toggle_event_type_update)");
        String string13 = resources6.getString(R.string.workout_gui_toggle_event_type_request);
        Intrinsics.checkExpressionValueIsNotNull(string13, "it.getString(R.string.wo…oggle_event_type_request)");
        String string14 = resources6.getString(R.string.workout_gui_toggle_event_type_notify);
        Intrinsics.checkExpressionValueIsNotNull(string14, "it.getString(R.string.wo…toggle_event_type_notify)");
        this.fitnessSessionCommandReceiverConfiguration = new FitnessSessionCommandReceiverConfiguration(string12, string13, string14);
        this.recoveryConfiguration = new RecoveryConfiguration(this.resources.getInteger(R.integer.fitness_session_recovery_window_in_msec));
    }

    @Override // com.amazon.alexa.fitness.configuration.AlexaFitnessCapabilityAgentServiceConfigurationProvider
    @NotNull
    public AlexaFitnessCapabilityAgentServiceConfiguration provideAlexaCapabilityAgentServiceConfiguration() {
        return this.alexaCapabilityAgentServiceConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.CacheProviderConfigurationProvider
    @NotNull
    public CacheProviderConfiguration provideCacheProviderConfiguration() {
        return this.cacheProviderConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.FitnessAccessorySessionMonitorConfigurationProvider
    @NotNull
    public FitnessAccessorySessionMonitorConfiguration provideFitnessAccessorySessionMonitorConfiguration() {
        return this.fitnessAccessorySessionMonitorConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.FitnessDataHandlerConfigurationProvider
    @NotNull
    public FitnessDataHandlerConfiguration provideFitnessDataHandlerConfiguration() {
        return this.fitnessDataHandlerConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.FitnessSessionCommandReceiverConfigurationProvider
    @NotNull
    public FitnessSessionCommandReceiverConfiguration provideFitnessSessionCommandReceiverConfiguration() {
        return this.fitnessSessionCommandReceiverConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.RecoveryConfigurationProvider
    @NotNull
    public RecoveryConfiguration provideRecoveryConfiguration() {
        return this.recoveryConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfigurationProvider
    @NotNull
    public SpeechletEventEmitterConfiguration provideSpeechletEventEmitterConfiguration() {
        return this.speechletEventEmitterConfiguration;
    }

    @Override // com.amazon.alexa.fitness.configuration.UserProfileServiceConfigurationProvider
    @NotNull
    public UserProfileServiceConfiguration provideUserProfileServiceConfiguration() {
        return this.userProfileServiceConfiguration;
    }
}
