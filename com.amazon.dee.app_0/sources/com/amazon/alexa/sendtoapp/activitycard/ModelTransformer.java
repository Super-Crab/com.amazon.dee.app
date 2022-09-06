package com.amazon.alexa.sendtoapp.activitycard;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.evaluator.Target;
import com.amazon.alexa.applink.evaluator.TargetIdentifierType;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.IdentifierType;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig;
/* loaded from: classes10.dex */
public final class ModelTransformer {

    /* renamed from: com.amazon.alexa.sendtoapp.activitycard.ModelTransformer$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$sendtoapp$activitycard$model$v1$IdentifierType = new int[IdentifierType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$sendtoapp$activitycard$model$v1$IdentifierType[IdentifierType.URI_HTTP_SCHEME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sendtoapp$activitycard$model$v1$IdentifierType[IdentifierType.URI_CUSTOM_SCHEME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sendtoapp$activitycard$model$v1$IdentifierType[IdentifierType.URI_ANDROID_INTENT_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sendtoapp$activitycard$model$v1$IdentifierType[IdentifierType.URI_APP_IDENTIFIER_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private ModelTransformer() {
    }

    @VisibleForTesting
    static boolean mustLaunchTargetInGivenApp(LaunchConfig launchConfig) {
        if (launchConfig == null || launchConfig.mustLaunchTargetInGivenApp() == null) {
            return false;
        }
        return launchConfig.mustLaunchTargetInGivenApp().booleanValue();
    }

    @VisibleForTesting
    static TargetIdentifierType transformIdentifierType(IdentifierType identifierType) {
        int ordinal = identifierType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return TargetIdentifierType.URI_CUSTOM_SCHEME;
            }
            if (ordinal == 2) {
                return TargetIdentifierType.URI_APP_IDENTIFIER_SCHEME;
            }
            if (ordinal == 3) {
                return TargetIdentifierType.URI_ANDROID_INTENT_SCHEME;
            }
            throw new IllegalArgumentException(String.format("Invalid identifier type: %s", identifierType));
        }
        return TargetIdentifierType.URI_HTTP_SCHEME;
    }

    public static Target transformTarget(com.amazon.alexa.sendtoapp.activitycard.model.v1.Target target) {
        return Target.create(target.getCatalogId(), target.getIdentifier(), transformIdentifierType(target.getIdentifierType()), mustLaunchTargetInGivenApp(target.getLaunchConfig()));
    }
}
