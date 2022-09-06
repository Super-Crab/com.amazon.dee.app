package com.amazon.alexa.voice.handsfree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.handsfree.features.FeatureAvailabilityForUi;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerController;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class HandsFreePermissionsActivityPresenter {
    @VisibleForTesting
    static final String EXTRA_ALL_PERMISSION = "voice_all_permission";
    @VisibleForTesting
    static final String EXTRA_FTUE_COMPLETED_RESULT_RECEIVER = "voice_ftue_complete";
    @VisibleForTesting
    static final String EXTRA_MINIMUM_PERMISSION = "voice_minimum_permission";
    private static final String TAG = "HandsFreePermissionsActivityPresenter";
    private final String[] allPermissions;
    private final FeatureAvailability mFeatureAvailabilityForUi = new FeatureAvailabilityForUi();
    private final String[] minimumPermissions;
    private final ResultReceiver resultReceiver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandsFreePermissionsActivityPresenter(@NonNull Intent intent) {
        this.allPermissions = intent.getStringArrayExtra(EXTRA_ALL_PERMISSION);
        this.minimumPermissions = intent.getStringArrayExtra(EXTRA_MINIMUM_PERMISSION);
        this.resultReceiver = (ResultReceiver) intent.getParcelableExtra(EXTRA_FTUE_COMPLETED_RESULT_RECEIVER);
    }

    public static boolean permissionsNeeded(Context context, String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return true;
            }
        }
        return false;
    }

    public static void showHandsfreeOOBEPermissions(Context context, String[] strArr, String[] strArr2, ResultReceiver resultReceiver) {
        Intent intent = new Intent(context, HandsFreePermissionsActivity.class);
        intent.putExtra(EXTRA_ALL_PERMISSION, strArr);
        intent.putExtra(EXTRA_MINIMUM_PERMISSION, strArr2);
        intent.putExtra(EXTRA_FTUE_COMPLETED_RESULT_RECEIVER, resultReceiver);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public void sendFTUECompleteMessage() {
        this.resultReceiver.send(3, null);
    }

    public void setupPermissionsRouter(@NonNull Context context, @NonNull final Locale locale, @Nullable Bundle bundle, @NonNull ViewGroup viewGroup, @NonNull Router.OnTransactionAdapter onTransactionAdapter) {
        Component component = new Component();
        component.provide((Class<? extends Class>) GetLocaleAuthority.class, (Class) new GetLocaleAuthority() { // from class: com.amazon.alexa.voice.handsfree.HandsFreePermissionsActivityPresenter.1
            @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
            public Locale getLocale() {
                return locale;
            }

            @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
            public List<Locale> getLocales() {
                return Arrays.asList(locale);
            }
        }).register();
        component.provide((Class<? extends Class>) FeatureAvailability.class, (Class) this.mFeatureAvailabilityForUi).register();
        Router router = new Router(context, component, bundle);
        router.attach(viewGroup);
        router.replaceCurrentController(new ControllerTransaction(PrimerController.create(this.allPermissions, this.minimumPermissions), new DismissContentTransition(), new PushContentTransition()));
        router.addOnPopTransactionListener(onTransactionAdapter);
    }
}
