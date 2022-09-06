package com.amazon.alexa.voice.handsfree.decider.setup;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.utils.HandsFreeLogoBarProvider;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public class DoubleMicrophonePermissionStrategy implements PermissionStrategy {
    private static final int ANDROID_11_VERSION_CODE = 30;
    private static final MetricsConstants.PageType DOUBLE_MICROPHONE_PERMISSIONS_PAGE_TYPE = MetricsConstants.PageType.DOUBLE_MICROPHONE_PERMISSIONS;

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    public void execute(@NonNull PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder, @NonNull Intent intent, @NonNull String str, @Nullable String str2) {
        permissionDelegate.setupContentView(R.layout.mosaic_alexa_handsfree_permission_layout);
        if (isAndroid11OrHigher()) {
            ((TextView) permissionDelegate.getViewById(R.id.title_text)).setText(R.string.alexa_handsfree_permission_title_android11_or_higher);
        }
        permissionMetricRecorder.recordPageView(DOUBLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.NONE);
        permissionDelegate.setCurrentPage(DOUBLE_MICROPHONE_PERMISSIONS_PAGE_TYPE);
        View viewById = permissionDelegate.getViewById(R.id.continue_button);
        View viewById2 = permissionDelegate.getViewById(R.id.later_button);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, viewById, viewById2);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, permissionDelegate.getViewById(R.id.title_text));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Light, permissionDelegate.getViewById(R.id.logo_title_text));
        TextView textView = (TextView) permissionDelegate.getViewById(R.id.handsfree_text);
        textView.setText(((Context) permissionDelegate).getString(R.string.alexa_handsfree_permission_allow_handsfree_text, str2));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Regular, textView, permissionDelegate.getViewById(R.id.handsfree_text), permissionDelegate.getViewById(R.id.alexa_text));
        setupLogos(permissionDelegate, intent);
        setupNegativeButtonOnClickListener(viewById2, permissionDelegate, permissionMetricRecorder);
        setupPositiveButtonOnClickListener(viewById, permissionDelegate, permissionMetricRecorder, null);
    }

    @VisibleForTesting
    boolean isAndroid11OrHigher() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @VisibleForTesting
    public void setupLogos(@NonNull PermissionDelegate permissionDelegate, @NonNull Intent intent) {
        HandsFreeLogoBarProvider handsFreeLogoBarProvider = new HandsFreeLogoBarProvider((Context) permissionDelegate);
        String str = intent != null ? intent.getPackage() : null;
        ImageView imageView = (ImageView) permissionDelegate.getViewById(R.id.permissions_partner_logo);
        int mosaicPartnerLogoID = handsFreeLogoBarProvider.getMosaicPartnerLogoID(str);
        if (mosaicPartnerLogoID != -1) {
            imageView.setImageResource(mosaicPartnerLogoID);
        }
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupNegativeButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.DoubleMicrophonePermissionStrategy.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(DoubleMicrophonePermissionStrategy.DOUBLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.LATER_BUTTON);
                permissionDelegate.onClickLaterButtonWithConfirmationDialog();
            }
        });
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupPositiveButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder, @Nullable String str) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.DoubleMicrophonePermissionStrategy.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(DoubleMicrophonePermissionStrategy.DOUBLE_MICROPHONE_PERMISSIONS_PAGE_TYPE, MetricsConstants.SubPageType.CONTINUE_BUTTON);
                permissionDelegate.onClickContinueButton();
            }
        });
    }
}
