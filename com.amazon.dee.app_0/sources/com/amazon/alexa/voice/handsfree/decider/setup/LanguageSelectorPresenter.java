package com.amazon.alexa.voice.handsfree.decider.setup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.handsfree.features.FeatureAvailabilityForUi;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthority;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthorityProvider;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerController;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
/* loaded from: classes11.dex */
public class LanguageSelectorPresenter {
    private static final String THREAD_TITLE = "hands-free-interactor";
    private final Component mComponent;
    private final FeatureAvailability mFeatureAvailabilityForUi;
    private final HandlerThread mHandlerThread;
    private final HandsFreeLocaleAuthority mHandsFreeLocaleAuthority;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private final NotificationMetricReporter mNotificationMetricReporter;
    private final Bundle mSavedInstanceState;
    private final LanguageSelectorActivity mView;

    public LanguageSelectorPresenter(@NonNull LanguageSelectorActivity languageSelectorActivity, @NonNull Bundle bundle, @NonNull Context context) {
        this(languageSelectorActivity, new HandlerThread(THREAD_TITLE), HandsFreeLocaleAuthorityProvider.getInstance(languageSelectorActivity.getApplicationContext()), bundle, new Component(), new FeatureAvailabilityForUi(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider(), new NotificationMetricReporter(languageSelectorActivity.getApplicationContext()));
    }

    @VisibleForTesting
    LanguageCombinationPrimerController createCombinationPrimerController(@NonNull LanguageCombinationPrimerParameters languageCombinationPrimerParameters) {
        return LanguageCombinationPrimerController.create(languageCombinationPrimerParameters);
    }

    @VisibleForTesting
    LanguagePrimerController createPrimerController(@NonNull LanguagePrimerParameters languagePrimerParameters) {
        return LanguagePrimerController.create(languagePrimerParameters);
    }

    @VisibleForTesting
    Handler getHandler() {
        return new Handler(this.mHandlerThread.getLooper());
    }

    @VisibleForTesting
    Router getRouter() {
        return new Router(this.mView, this.mComponent, this.mSavedInstanceState);
    }

    public void initializeHandsFreeLocaleAuthority() {
        getHandler().post(new Runnable() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LanguageSelectorPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                if (LanguageSelectorPresenter.this.mHandsFreeLocaleAuthority.ensureInitialized()) {
                    LanguageSelectorPresenter.this.mView.setupLanguageRouter();
                } else {
                    LanguageSelectorPresenter.this.mView.finishStep(ManagedActivity.StepResult.CONTINUE);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNotificationClicked(@NonNull Intent intent) {
        this.mNotificationMetricReporter.reportNotificationClickMetric(intent);
    }

    public void setupPermissionsRouter(@NonNull ViewGroup viewGroup, @NonNull Router.OnTransactionAdapter onTransactionAdapter) {
        this.mComponent.provide((Class<? extends Class>) AlexaLocaleAuthority.class, (Class) this.mHandsFreeLocaleAuthority).register();
        this.mComponent.provide((Class<? extends Class>) FeatureAvailability.class, (Class) this.mFeatureAvailabilityForUi).register();
        Router router = getRouter();
        router.attach(viewGroup);
        if (this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity() != null && this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING)) {
            router.replaceCurrentController(new ControllerTransaction(createCombinationPrimerController(this.mHandsFreeLocaleAuthority.getLanguageSelectorCombinationParameters()), new DismissContentTransition(), new PushContentTransition()));
        } else {
            router.replaceCurrentController(new ControllerTransaction(createPrimerController(this.mHandsFreeLocaleAuthority.getLanguageSelectorParameters()), new DismissContentTransition(), new PushContentTransition()));
        }
        router.addOnPopTransactionListener(onTransactionAdapter);
    }

    LanguageSelectorPresenter(@NonNull LanguageSelectorActivity languageSelectorActivity, @NonNull HandlerThread handlerThread, @NonNull HandsFreeLocaleAuthority handsFreeLocaleAuthority, @NonNull Bundle bundle, @NonNull Component component, @NonNull FeatureAvailability featureAvailability, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull NotificationMetricReporter notificationMetricReporter) {
        this.mView = languageSelectorActivity;
        this.mHandlerThread = handlerThread;
        this.mHandsFreeLocaleAuthority = handsFreeLocaleAuthority;
        this.mSavedInstanceState = bundle;
        this.mComponent = component;
        this.mFeatureAvailabilityForUi = featureAvailability;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mNotificationMetricReporter = notificationMetricReporter;
        this.mHandlerThread.start();
    }
}
