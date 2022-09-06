package com.amazon.alexa.voice.handsfree.decider.setup;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public class SingleMicrophonePermissionStrategy implements PermissionStrategy {
    private static final int ANDROID_11_VERSION_CODE = 30;
    private static final MetricsConstants.PageType SINGLE_MICROPHONE_PERMISSIONS_PAGE_TYPE = MetricsConstants.PageType.SINGLE_MICROPHONE_PERMISSION;
    private static final String TAG = "SingleMicrophonePermissionStrategy";
    private DebounceOnClickListener learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SingleMicrophonePermissionStrategy.4
        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            SingleMicrophonePermissionStrategy.this.mDelegate.onClickLearnMore();
        }
    };
    private PermissionDelegate mDelegate;

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    public void execute(@NonNull PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder, @NonNull Intent intent, @NonNull String str, @Nullable String str2) {
        permissionDelegate.setupContentView(R.layout.mosaic_alexa_app_audio_permission_layout);
        if (isAndroid11OrHigher()) {
            ((TextView) permissionDelegate.getViewById(R.id.alexa_app_audio_permission_title_text)).setText(R.string.alexa_app_audio_permission_title_android11_or_higher);
        }
        permissionMetricRecorder.recordPageView(SINGLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.NONE);
        permissionDelegate.setCurrentPage(SINGLE_MICROPHONE_PERMISSIONS_PAGE_TYPE);
        View viewById = permissionDelegate.getViewById(R.id.alexa_app_audio_permission_skip_button);
        View viewById2 = permissionDelegate.getViewById(R.id.alexa_app_audio_permission_next_button);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, viewById, viewById2);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, permissionDelegate.getViewById(R.id.alexa_app_audio_permission_title_text));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Medium, permissionDelegate.getViewById(R.id.alexa_app_audio_permission_detail));
        setupPositiveButtonOnClickListener(viewById2, permissionDelegate, permissionMetricRecorder, null);
        setupNegativeButtonOnClickListener(viewById, permissionDelegate, permissionMetricRecorder);
        setSpannableTextToView((TextView) permissionDelegate.getViewById(R.id.alexa_app_audio_permission_learn_more), permissionDelegate);
        this.mDelegate = permissionDelegate;
    }

    @VisibleForTesting
    boolean isAndroid11OrHigher() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @VisibleForTesting
    void setSpannableTextToView(@NonNull TextView textView, @NonNull PermissionDelegate permissionDelegate) {
        Activity activity = (Activity) permissionDelegate;
        final int color = activity.getResources().getColor(R.color.action10);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SingleMicrophonePermissionStrategy.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SingleMicrophonePermissionStrategy.this.learnMoreClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(color);
                textPaint.setUnderlineText(false);
            }
        };
        String string = activity.getString(R.string.alexa_app_audio_permission_learn_more);
        int length = string.length();
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(clickableSpan, 0, length, 33);
        spannableString.setSpan(foregroundColorSpan, 0, length, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupNegativeButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SingleMicrophonePermissionStrategy.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(SingleMicrophonePermissionStrategy.SINGLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.LATER_BUTTON);
                permissionDelegate.onClickLaterButtonWithConfirmationDialog();
            }
        });
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupPositiveButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder, @Nullable String str) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SingleMicrophonePermissionStrategy.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(SingleMicrophonePermissionStrategy.SINGLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.CONTINUE_BUTTON);
                permissionDelegate.onClickContinueButton();
            }
        });
    }
}
