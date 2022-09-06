package com.amazon.alexa.voice.handsfree.decider.setup;

import android.app.Activity;
import android.content.Intent;
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
import androidx.core.app.ActivityCompat;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public class LocationPermissionStrategy implements PermissionStrategy {
    private static final MetricsConstants.PageType LOCATION_PERMISSION_PAGE_TYPE = MetricsConstants.PageType.LOCATION_PERMISSION;
    private DebounceOnClickListener mLearnMoreClickSpanListener;

    @VisibleForTesting
    void activityRequestPermissions(PermissionDelegate permissionDelegate) {
        ActivityCompat.requestPermissions((Activity) permissionDelegate, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 2590);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    public void execute(@NonNull final PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder, @NonNull Intent intent, @NonNull String str, @Nullable String str2) {
        permissionDelegate.setupContentView(R.layout.mosaic_alexa_app_location_permission_layout);
        permissionMetricRecorder.recordPageView(LOCATION_PERMISSION_PAGE_TYPE, MetricsConstants.SubPageType.NONE);
        permissionDelegate.setCurrentPage(LOCATION_PERMISSION_PAGE_TYPE);
        this.mLearnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LocationPermissionStrategy.1
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(@NonNull View view) {
                permissionDelegate.onClickLearnMore();
            }
        };
        View viewById = permissionDelegate.getViewById(R.id.alexa_app_location_permission_negative_button);
        View viewById2 = permissionDelegate.getViewById(R.id.alexa_app_location_permission_positive_button);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, viewById, viewById2);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, permissionDelegate.getViewById(R.id.alexa_app_location_permission_title_text));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Medium, permissionDelegate.getViewById(R.id.alexa_app_location_permission_detail));
        setupNegativeButtonOnClickListener(viewById, permissionDelegate, permissionMetricRecorder);
        setupPositiveButtonOnClickListener(viewById2, permissionDelegate, permissionMetricRecorder, str);
        setSpannableTextToView((TextView) permissionDelegate.getViewById(R.id.alexa_app_location_permission_learn_more), (Activity) permissionDelegate);
    }

    @VisibleForTesting
    ClickableSpan getClickableSpan(@NonNull Activity activity) {
        final int color = activity.getResources().getColor(R.color.action10);
        return new ClickableSpan() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LocationPermissionStrategy.4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                LocationPermissionStrategy.this.mLearnMoreClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(color);
                textPaint.setUnderlineText(false);
            }
        };
    }

    @VisibleForTesting
    ForegroundColorSpan getSpanColor(@NonNull Activity activity) {
        return new ForegroundColorSpan(activity.getResources().getColor(R.color.action10));
    }

    @VisibleForTesting
    void setSpannableTextToView(@NonNull TextView textView, @NonNull Activity activity) {
        ForegroundColorSpan spanColor = getSpanColor(activity);
        ClickableSpan clickableSpan = getClickableSpan(activity);
        String string = activity.getString(R.string.alexa_app_location_permission_learn_more);
        int length = string.length();
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(clickableSpan, 0, length, 33);
        spannableString.setSpan(spanColor, 0, length, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupNegativeButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LocationPermissionStrategy.3
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(LocationPermissionStrategy.LOCATION_PERMISSION_PAGE_TYPE, MetricsConstants.SubPageType.LATER_BUTTON);
                permissionDelegate.onClickLaterButton();
            }
        });
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionStrategy
    @VisibleForTesting
    public void setupPositiveButtonOnClickListener(@NonNull View view, @NonNull final PermissionDelegate permissionDelegate, @NonNull final PermissionMetricRecorder permissionMetricRecorder, @Nullable final String str) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LocationPermissionStrategy.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                permissionMetricRecorder.recordClick(LocationPermissionStrategy.LOCATION_PERMISSION_PAGE_TYPE, MetricsConstants.SubPageType.CONTINUE_BUTTON);
                if (permissionDelegate.hasPermission("android.permission.ACCESS_FINE_LOCATION", str)) {
                    permissionDelegate.finishStepForPermission(ManagedActivity.StepResult.CONTINUE);
                    return;
                }
                permissionMetricRecorder.recordPageView(LocationPermissionStrategy.LOCATION_PERMISSION_PAGE_TYPE, MetricsConstants.SubPageType.PERMISSION_DIALOG);
                LocationPermissionStrategy.this.activityRequestPermissions(permissionDelegate);
            }
        });
    }
}
