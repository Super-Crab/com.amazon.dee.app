package com.amazon.alexa.handsfree.ui.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.ui.R;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public class HandsFreeLogoBarProvider {
    private static final String TAG = "HandsFreeLogoBarProvider";
    private final View mLogoBarView;

    /* JADX WARN: Init of enum METRO can be incorrect */
    /* loaded from: classes8.dex */
    public enum LogoProvider {
        METRO(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME, r5, r5),
        MOTOROLA("com.motorola.motoalexa", R.drawable.ic_moto_logo, R.drawable.mosaic_ic_moto_logo),
        QUALCOMM("com.quicinc.voice.activation", R.drawable.ic_provider_logo, R.drawable.mosaic_ic_qualcomm_logo);
        
        private final int mMosaicLogoID;
        private final String mPackageName;

        static {
            int i = R.drawable.ic_metro_logo;
        }

        LogoProvider(@NonNull String str, @DrawableRes int i, @DrawableRes int i2) {
            this.mPackageName = str;
            this.mMosaicLogoID = i2;
        }

        @NonNull
        @DrawableRes
        public int getMosaicLogoID() {
            return this.mMosaicLogoID;
        }

        @NonNull
        public String getPackageName() {
            return this.mPackageName;
        }
    }

    public HandsFreeLogoBarProvider(@NonNull Context context) {
        this(View.inflate(context, R.layout.alexa_handsfree_logo_bar, null));
    }

    private void setupUI(@DrawableRes int i, @NonNull ImageView imageView) {
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Light, this.mLogoBarView.findViewById(R.id.logo_title_text));
        if (i != -1) {
            imageView.setImageResource(i);
            return;
        }
        imageView.setImageDrawable(null);
        this.mLogoBarView.findViewById(R.id.logo_line).setVisibility(4);
    }

    @NonNull
    public View getHandsFreeLogoBarMosaic(@Nullable String str) {
        GeneratedOutlineSupport1.outline167("getHandsFreeLogoBarMosaic: partner package name is ", str, TAG);
        this.mLogoBarView.findViewById(R.id.logo_line).setBackgroundColor(R.attr.mosaicNeutral10);
        setupUI(getMosaicPartnerLogoID(str), (ImageView) this.mLogoBarView.findViewById(R.id.provider_logo));
        return this.mLogoBarView;
    }

    @DrawableRes
    public int getMosaicPartnerLogoID(@Nullable String str) {
        LogoProvider[] values;
        for (LogoProvider logoProvider : LogoProvider.values()) {
            if (logoProvider.getPackageName().equals(str)) {
                return logoProvider.getMosaicLogoID();
            }
        }
        return -1;
    }

    @VisibleForTesting
    HandsFreeLogoBarProvider(@NonNull View view) {
        this.mLogoBarView = view;
    }
}
