package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaBaseURLs;
import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaMediaPlaybackMetadata;
import com.amazon.alexa.api.AlexaMediaPlaybackState;
import com.amazon.alexa.api.AlexaReadyState;
import com.amazon.alexa.api.AlexaState;
import com.amazon.alexa.api.AlexaSupportedInitiationType;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.ArtifactDownloadListenerFailure;
import com.amazon.alexa.api.CaptionResponse;
import com.amazon.alexa.api.DialogData;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.DialogTurnData;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.api.TextResponse;
import com.amazon.alexa.api.TextResponseMetadata;
import com.amazon.alexa.api.UserPerceivedLatencyData;
import com.amazon.alexa.api.WakeWordState;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class BundleTransformer {
    static final String KEY_PREFIX = "ELEMENT_";
    static final String KEY_SIZE = "SIZE";
    private final Map<Class, u> bundleAdapters = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class a {
        static BundleTransformer a = new BundleTransformer().registerBundleAdapter(AlexaMediaPlaybackState.class, new AlexaMediaPlaybackState.Adapter()).registerBundleAdapter(TextResponseMetadata.class, new TextResponseMetadata.Adapter()).registerBundleAdapter(ArtifactDownloadListenerFailure.class, new ArtifactDownloadListenerFailure.a()).registerBundleAdapter(DriveModeState.class, new DriveModeState.a()).registerBundleAdapter(AlexaCapabilityAgentRegistration.class, new AlexaCapabilityAgentRegistration.Adapter()).registerBundleAdapter(CaptionResponse.class, new CaptionResponse.Adapter()).registerBundleAdapter(ArtifactDownloadListenerFailure.class, new ArtifactDownloadListenerFailure.a()).registerBundleAdapter(String.class, new bi()).registerBundleAdapter(LaunchType.class, new LaunchType.a()).registerBundleAdapter(AlexaDialogExtras.class, new AlexaDialogExtras.DialogExtrasBundleAdapter()).registerBundleAdapter(AlexaState.class, new AlexaState.AlexaStateBundleAdapter()).registerBundleAdapter(DialogTurnData.class, new DialogTurnData.DialogTurnDataAdapter()).registerBundleAdapter(AlexaState.class, new AlexaState.AlexaStateBundleAdapter()).registerBundleAdapter(AlexaContext.class, new AlexaContext.AlexaContextBundleAdapter()).registerBundleAdapter(CaptionResponse.class, new CaptionResponse.Adapter()).registerBundleAdapter(DialogData.class, new DialogData.DialogTurnDataAdapter()).registerBundleAdapter(AlexaBaseURLs.class, new AlexaBaseURLs.URLsBundleAdapter()).registerBundleAdapter(DriveModeState.class, new DriveModeState.a()).registerBundleAdapter(AlexaMediaPlaybackMetadata.class, new AlexaMediaPlaybackMetadata.Adapter()).registerBundleAdapter(TextResponse.class, new TextResponse.Adapter()).registerBundleAdapter(DialogRequestDeniedReason.class, new DialogRequestDeniedReason.DialogRequestDeniedReasonAdapter()).registerBundleAdapter(AlexaReadyState.class, new AlexaReadyState.AlexaReadyStateAdapter()).registerBundleAdapter(AlexaMediaPlaybackMetadata.class, new AlexaMediaPlaybackMetadata.Adapter()).registerBundleAdapter(UserPerceivedLatencyData.class, new UserPerceivedLatencyData.a()).registerBundleAdapter(AlexaSupportedInitiationType.class, new AlexaSupportedInitiationType.SupportedInitiationTypeAdapter()).registerBundleAdapter(AlexaUserSpeechProviderMetadata.class, new AlexaUserSpeechProviderMetadata.Adapter()).registerBundleAdapter(UserPerceivedLatencyData.class, new UserPerceivedLatencyData.a()).registerBundleAdapter(WakeWordState.class, new WakeWordState.Adapter()).registerBundleAdapter(AlexaMediaPlaybackState.class, new AlexaMediaPlaybackState.Adapter());
    }

    public static BundleTransformer getDefaultInstance() {
        return a.a;
    }

    @Nullable
    public <T> u<T> getBundleAdapter(Class<T> cls) {
        Preconditions.notNull(cls, "type cannot be null");
        if (this.bundleAdapters.containsKey(cls)) {
            return this.bundleAdapters.get(cls);
        }
        return null;
    }

    public <T> Collection<T> getCollectionFromBundle(Bundle bundle, Class<T> cls) {
        Object fromBundle;
        Preconditions.notNull(cls, "type cannot be null");
        if (bundle != null && bundle.containsKey(KEY_SIZE)) {
            int i = bundle.getInt(KEY_SIZE);
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                Bundle bundle2 = bundle.getBundle(KEY_PREFIX + i2);
                if (bundle2 != null && (fromBundle = getFromBundle(bundle2, cls)) != null) {
                    arrayList.add(fromBundle);
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    @Nullable
    public <T> T getFromBundle(Bundle bundle, Class<T> cls) {
        if (bundle == null) {
            return null;
        }
        Preconditions.notNull(cls, "type cannot be null");
        u<T> bundleAdapter = getBundleAdapter(cls);
        if (bundleAdapter != null) {
            return bundleAdapter.mo844createFromBundle(bundle);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BundleTransformer cannot handle type: ");
        outline107.append(cls.getClass());
        throw new IllegalArgumentException(outline107.toString());
    }

    <T> BundleTransformer registerBundleAdapter(Class<T> cls, u<T> uVar) {
        Preconditions.notNull(uVar, "bundleAdapter cannot be null");
        Preconditions.notNull(cls, "type cannot be null");
        this.bundleAdapters.put(cls, uVar);
        return this;
    }

    public Bundle toBundle(Object obj) {
        Preconditions.notNull(obj, "object cannot be null");
        u bundleAdapter = getBundleAdapter(obj.getClass());
        if (bundleAdapter != null) {
            return bundleAdapter.toBundle(obj);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("BundleTransformer cannot handle type: ")));
    }

    public <T> Bundle toBundle(Collection<T> collection) {
        Preconditions.notNull(collection, "collection cannot be null");
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SIZE, collection.size());
        int i = 0;
        for (T t : collection) {
            bundle.putBundle(GeneratedOutlineSupport1.outline49(KEY_PREFIX, i), toBundle(t));
            i++;
        }
        return bundle;
    }
}
