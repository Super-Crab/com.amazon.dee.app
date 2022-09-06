package com.amazon.alexa.voice.handsfree.decider.setup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.views.TopDockedToast;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.dependencies.AhfComponent;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class HandsFreeIntroActivity extends ManagedActivity {
    public static final String EXTRA_IS_AIS_FLOW = "IS_AIS_FLOW";
    private static final String TAG = HandsFreeIntroActivity.class.getSimpleName();
    private AlertDialog.Builder mAlertDialogBuilder;
    private EnrollmentType mEnrollmentType;
    @Inject
    Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final Initializer mInitializer;
    @Inject
    MetricsBuilderProvider mMetricsBuilderProvider;

    @VisibleForTesting
    HandsFreeIntroActivity(@NonNull Initializer initializer, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull AlertDialog.Builder builder) {
        this.mInitializer = initializer;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mAlertDialogBuilder = builder;
    }

    private EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    private boolean isInAisFlow() {
        return getIntent().getBooleanExtra(EXTRA_IS_AIS_FLOW, false);
    }

    @VisibleForTesting
    void emitButtonClickMetric(MetricsConstants.SubPageType subPageType) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withClickMetric(TAG, MetricsConstants.Component.HANDS_FREE, MetricsConstants.PageType.HANDS_FREE_INTRO, subPageType, getEnrollmentType());
        newBuilder.emit(this);
    }

    @VisibleForTesting
    void emitPageViewMetrics() {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        newBuilder.withPageViewMetric(TAG, MetricsConstants.Component.HANDS_FREE, MetricsConstants.PageType.HANDS_FREE_INTRO, MetricsConstants.SubPageType.NONE, getEnrollmentType());
        newBuilder.emit(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        ((AhfComponent) AhfComponentsProvider.getComponent(this, AhfComponent.class)).inject(this);
        Log.e("HansFreeIntro", "HandsFreeIntro started");
        super.onCreate(bundle);
        ThemeUtil.setTheme(this);
        setContentView(R.layout.mosaic_alexa_handsfree_intro_layout);
        this.mInitializer.initialize(getApplicationContext());
        this.mAlertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialogBackground);
        emitPageViewMetrics();
        Button button = (Button) findViewById(R.id.get_started_button);
        View findViewById = findViewById(R.id.skip_button);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, button, findViewById);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, findViewById(R.id.handsfree_intro_title_text));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Medium, findViewById(R.id.handsfree_intro_sub_title_text));
        if (isInAisFlow()) {
            setupGetStartedButtonLabel(button);
            showRecognizeVoiceToastMessage();
        }
        setupGetStartedButtonOnClickListener(button);
        setupSkipButtonOnClickListener(findViewById);
        saveHandsFreeIntroShown();
    }

    @VisibleForTesting
    void saveHandsFreeIntroShown() {
        getSharedPreferences(getString(R.string.preference_file_name_intro), 0).edit().putBoolean(getString(R.string.preference_key_intro_shown), true).apply();
    }

    @VisibleForTesting
    void setupGetStartedButtonLabel(@NonNull Button button) {
        button.setText(R.string.alexa_handsfree_intro_enable);
    }

    @VisibleForTesting
    void setupGetStartedButtonOnClickListener(@NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                HandsFreeIntroActivity.this.emitButtonClickMetric(MetricsConstants.SubPageType.GET_STARTED);
                HandsFreeIntroActivity.this.finishStep(ManagedActivity.StepResult.CONTINUE);
            }
        });
    }

    @VisibleForTesting
    void setupSkipButtonOnClickListener(@NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                HandsFreeIntroActivity.this.showSkipAHFSetupConfirmationDialog();
                HandsFreeIntroActivity.this.emitButtonClickMetric(MetricsConstants.SubPageType.LATER_BUTTON);
            }
        });
    }

    void showRecognizeVoiceToastMessage() {
        TopDockedToast.newInstance(this).setText(getResources().getString(R.string.alexa_handsfree_intro_enable_toast_message)).setDuration(1).show();
    }

    @VisibleForTesting
    void showSkipAHFSetupConfirmationDialog() {
        this.mAlertDialogBuilder.setTitle(R.string.alexa_handsfree_intro_skip_dialog_title);
        this.mAlertDialogBuilder.setMessage(R.string.alexa_handsfree_intro_skip_dialog_message);
        this.mAlertDialogBuilder.setPositiveButton(R.string.alexa_handsfree_intro_skip_dialog_skip_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                HandsFreeIntroActivity.this.emitButtonClickMetric(MetricsConstants.SubPageType.DIALOG_CONFIRM_BUTTON);
                HandsFreeIntroActivity.this.finishStep(ManagedActivity.StepResult.EXIT);
            }
        });
        this.mAlertDialogBuilder.setNegativeButton(R.string.alexa_handsfree_intro_skip_dialog_go_back_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                HandsFreeIntroActivity.this.emitButtonClickMetric(MetricsConstants.SubPageType.DIALOG_GO_BACK_BUTTON);
            }
        });
        this.mAlertDialogBuilder.create().show();
    }

    public HandsFreeIntroActivity() {
        this.mInitializer = InitializerProvider.getInitializer();
    }
}
