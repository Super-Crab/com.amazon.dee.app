package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaLocalesListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocaleSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0017J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u0014\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0017J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0016\u0010\u001a\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001cH\u0016J \u0010\u001a\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/api/compat/LocaleSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/LocaleApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterListener", "", "compatAlexaSupportedLocalesListener", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListener;", "compatAlexaSupportedLocalesListenerv2", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "deregisterLocalesListener", "alexaLocalesListener", "Lcom/amazon/alexa/api/AlexaLocalesListener;", "deregisterSupportedLocalesListener", "alexaSupportedLocalesListener", "Lcom/amazon/alexa/api/AlexaSupportedLocalesListener;", "registerListener", "registerLocalesListener", "registerSupportedLocalesListener", "setLocale", "locale", "Ljava/util/Locale;", "alexaApiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "setLocales", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LocaleSubcomponent extends Subcomponent implements LocaleApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaSupportedLocalesListener, AlexaSupportedLocalesListenerAdapter> compatListeners = new LinkedHashMap();
    @NotNull
    private static final Map<AlexaSupportedLocalesListenerv2, AlexaSupportedLocalesListenerv2Adapter> compatv2Listeners = new LinkedHashMap();

    /* compiled from: LocaleSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/LocaleSubcomponent$Companion;", "", "()V", "compatListeners", "", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListener;", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerAdapter;", "getCompatListeners", "()Ljava/util/Map;", "compatv2Listeners", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2Adapter;", "getCompatv2Listeners", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaSupportedLocalesListener, AlexaSupportedLocalesListenerAdapter> getCompatListeners() {
            return LocaleSubcomponent.compatListeners;
        }

        @NotNull
        public final Map<AlexaSupportedLocalesListenerv2, AlexaSupportedLocalesListenerv2Adapter> getCompatv2Listeners() {
            return LocaleSubcomponent.compatv2Listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocaleSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void deregisterListener(@NotNull AlexaSupportedLocalesListener compatAlexaSupportedLocalesListener) {
        Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListener, "compatAlexaSupportedLocalesListener");
        AlexaSupportedLocalesListenerAdapter remove = compatListeners.remove(compatAlexaSupportedLocalesListener);
        if (remove != null) {
            getFrameworkApis().getLocale().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void deregisterLocalesListener(@NotNull AlexaLocalesListener alexaLocalesListener) {
        Intrinsics.checkParameterIsNotNull(alexaLocalesListener, "alexaLocalesListener");
        getFrameworkApis().getLocale().deregisterListener(alexaLocalesListener);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    @Deprecated(message = "use compat listener")
    public void deregisterSupportedLocalesListener(@Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        getFrameworkApis().getLocale().deregisterListener(alexaSupportedLocalesListener);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void registerListener(@NotNull AlexaSupportedLocalesListener compatAlexaSupportedLocalesListener) {
        Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListener, "compatAlexaSupportedLocalesListener");
        AlexaSupportedLocalesListenerAdapter alexaSupportedLocalesListenerAdapter = new AlexaSupportedLocalesListenerAdapter(compatAlexaSupportedLocalesListener);
        compatListeners.put(compatAlexaSupportedLocalesListener, alexaSupportedLocalesListenerAdapter);
        getFrameworkApis().getLocale().registerListener(alexaSupportedLocalesListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void registerLocalesListener(@NotNull AlexaLocalesListener alexaLocalesListener) {
        Intrinsics.checkParameterIsNotNull(alexaLocalesListener, "alexaLocalesListener");
        getFrameworkApis().getLocale().registerListener(alexaLocalesListener);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    @Deprecated(message = "use compat listener")
    public void registerSupportedLocalesListener(@Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        getFrameworkApis().getLocale().registerListener(alexaSupportedLocalesListener);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void setLocale(@NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        getFrameworkApis().getLocale().setLocale(locale);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void setLocales(@NotNull List<Locale> locales) {
        Intrinsics.checkParameterIsNotNull(locales, "locales");
        getFrameworkApis().getLocale().setLocales(locales);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void setLocale(@NotNull Locale locale, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        getFrameworkApis().getLocale().setLocale(locale, alexaApiCallbacks);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void setLocales(@NotNull List<Locale> locales, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        Intrinsics.checkParameterIsNotNull(locales, "locales");
        getFrameworkApis().getLocale().setLocales(locales, alexaApiCallbacks);
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void deregisterListener(@NotNull AlexaSupportedLocalesListenerv2 compatAlexaSupportedLocalesListenerv2) {
        Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListenerv2, "compatAlexaSupportedLocalesListenerv2");
        AlexaSupportedLocalesListenerv2Adapter remove = compatv2Listeners.remove(compatAlexaSupportedLocalesListenerv2);
        if (remove != null) {
            getFrameworkApis().getLocale().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.LocaleApi
    public void registerListener(@NotNull AlexaSupportedLocalesListenerv2 compatAlexaSupportedLocalesListenerv2) {
        Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListenerv2, "compatAlexaSupportedLocalesListenerv2");
        AlexaSupportedLocalesListenerv2Adapter alexaSupportedLocalesListenerv2Adapter = new AlexaSupportedLocalesListenerv2Adapter(compatAlexaSupportedLocalesListenerv2);
        compatv2Listeners.put(compatAlexaSupportedLocalesListenerv2, alexaSupportedLocalesListenerv2Adapter);
        getFrameworkApis().getLocale().registerListener(alexaSupportedLocalesListenerv2Adapter);
    }
}
