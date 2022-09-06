package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.views.ActivityIndicator;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentActivity;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class GettingReadyFragment extends Fragment {
    private static final String TAG = GettingReadyFragment.class.getSimpleName();
    private static final int TIMEOUT_IN_MILLISECONDS = 20000;
    private GettingReadyFragmentCallback callback;
    private Handler handler;
    private ActivityIndicator mActivityIndicator;
    @NonNull
    private AlertDialog.Builder mAlertDialogBuilder;
    private EnrollmentType mEnrollmentType;
    private Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private Runnable runnable;

    /* loaded from: classes8.dex */
    public interface GettingReadyFragmentCallback {
        void onClickRetryButton(VoiceTrainingMetricMetadata.PageType pageType);

        void onClickSkipButton(VoiceTrainingMetricMetadata.PageType pageType);
    }

    public GettingReadyFragment() {
        this.handler = new Handler(Looper.getMainLooper());
        this.runnable = new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.GettingReadyFragment.1
            @Override // java.lang.Runnable
            public void run() {
                GettingReadyFragment.this.showErrorDialog(R.string.uvr_primer_getting_ready_generic_error_dialog_title, R.string.uvr_primer_getting_ready_generic_error_dialog_description, VoiceTrainingMetricMetadata.PageType.TRAINING_GETTING_READY_GENERIC_TIMEOUT_ALERT);
            }
        };
    }

    @VisibleForTesting
    void emitMetric(@NonNull VoiceTrainingMetricMetadata.PageType pageType, @NonNull VoiceTrainingMetricMetadata.SubPageType subPageType, @NonNull VoiceTrainingMetricMetadata.EventType eventType, @NonNull EnrollmentType enrollmentType) {
        if (getActivity() != null && (getActivity() instanceof EnrollmentActivity)) {
            ((EnrollmentActivity) getActivity()).emitMetric(pageType, subPageType, eventType, enrollmentType);
        } else {
            Log.e(TAG, "GettingReadyFragment was used inside another activity other than EnrollmentActivity.");
        }
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        this.mAlertDialogBuilder = new AlertDialog.Builder(getContext(), 16974394);
        ThemeUtil.setTheme(getActivity().getApplicationContext());
        return LayoutInflater.from(new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_fragment_getting_ready, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.handler.removeCallbacks(this.runnable);
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.handler.postDelayed(this.runnable, SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US);
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getActivity().getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
        updateUI(view);
    }

    public void setCallback(@Nullable GettingReadyFragmentCallback gettingReadyFragmentCallback) {
        this.callback = gettingReadyFragmentCallback;
    }

    @VisibleForTesting
    void showErrorDialog(@NonNull int i, @NonNull int i2, @NonNull final VoiceTrainingMetricMetadata.PageType pageType) {
        this.mAlertDialogBuilder.setTitle(i).setMessage(i2).setPositiveButton(R.string.uvr_primer_getting_ready_error_dialog_positive_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.GettingReadyFragment.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                if (GettingReadyFragment.this.callback != null) {
                    GettingReadyFragment.this.callback.onClickRetryButton(pageType);
                }
                GettingReadyFragment gettingReadyFragment = GettingReadyFragment.this;
                gettingReadyFragment.emitMetric(pageType, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_POSITIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, gettingReadyFragment.getEnrollmentType());
            }
        }).setNegativeButton(R.string.uvr_primer_getting_ready_error_dialog_negative_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.GettingReadyFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                if (GettingReadyFragment.this.callback != null) {
                    GettingReadyFragment.this.callback.onClickSkipButton(pageType);
                }
                GettingReadyFragment gettingReadyFragment = GettingReadyFragment.this;
                gettingReadyFragment.emitMetric(pageType, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_NEGATIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, gettingReadyFragment.getEnrollmentType());
            }
        }).show();
        emitMetric(pageType, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType());
    }

    public void showInternetErrorDialog() {
        showErrorDialog(R.string.uvr_primer_getting_ready_internet_error_dialog_title, R.string.uvr_primer_getting_ready_internet_error_dialog_description, VoiceTrainingMetricMetadata.PageType.TRAINING_GETTING_READY_INTERNET_ALERT);
    }

    @VisibleForTesting
    void updateUI(@NonNull View view) {
        this.mActivityIndicator = (ActivityIndicator) view.findViewById(R.id.getting_ready_activity_indicator);
        this.mActivityIndicator.startAnimation();
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, view.findViewById(R.id.getting_ready_title));
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    GettingReadyFragment(@NonNull AlertDialog.Builder builder, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.handler = new Handler(Looper.getMainLooper());
        this.runnable = new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.GettingReadyFragment.1
            @Override // java.lang.Runnable
            public void run() {
                GettingReadyFragment.this.showErrorDialog(R.string.uvr_primer_getting_ready_generic_error_dialog_title, R.string.uvr_primer_getting_ready_generic_error_dialog_description, VoiceTrainingMetricMetadata.PageType.TRAINING_GETTING_READY_GENERIC_TIMEOUT_ALERT);
            }
        };
        this.mAlertDialogBuilder = builder;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mEnrollmentType = null;
    }
}
