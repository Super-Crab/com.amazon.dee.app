package com.amazon.alexa.handsfree.devices.locales;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.R;
import com.amazon.alexa.handsfree.devices.utils.ResourceFilesLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes8.dex */
public class CertifiedLocaleVoiceAppProvider {
    @StringRes
    static final int NO_DEFAULT_LOCALE_PROVIDED_RES_ID = R.string.voice_app_no_default_provided;
    private String[] mCertifiedLocalesArray;
    private String mDefaultCertifiedLocale;
    private final int mResIdVoiceAppUpdateCertifiedLocaleDefault;
    private final int mResIdVoiceAppUpdateCertifiedLocaleList;
    private final ResourceFilesLoader mResourceFilesLoader;

    public CertifiedLocaleVoiceAppProvider(int i, int i2) {
        this(new ResourceFilesLoader(), i, i2);
    }

    private String[] getCertifiedLocalesArray(@NonNull Context context) {
        if (this.mCertifiedLocalesArray == null) {
            this.mCertifiedLocalesArray = this.mResourceFilesLoader.getStringArrayResource(context, this.mResIdVoiceAppUpdateCertifiedLocaleList);
        }
        return this.mCertifiedLocalesArray;
    }

    @NonNull
    public List<Locale> getCertifiedLocales(@NonNull Context context) {
        String[] certifiedLocalesArray = getCertifiedLocalesArray(context);
        ArrayList arrayList = new ArrayList();
        for (String str : certifiedLocalesArray) {
            arrayList.add(Locale.forLanguageTag(str));
        }
        return arrayList;
    }

    @Nullable
    public Locale getDefaultLocale(@NonNull Context context) {
        int i = this.mResIdVoiceAppUpdateCertifiedLocaleDefault;
        if (i == NO_DEFAULT_LOCALE_PROVIDED_RES_ID) {
            return null;
        }
        if (this.mDefaultCertifiedLocale == null) {
            this.mDefaultCertifiedLocale = this.mResourceFilesLoader.getStringResource(context, i);
        }
        return new Locale.Builder().setLanguageTag(this.mDefaultCertifiedLocale).build();
    }

    public boolean isLocaleCertified(@NonNull Context context, @NonNull String str) {
        for (String str2 : getCertifiedLocalesArray(context)) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    CertifiedLocaleVoiceAppProvider(@NonNull ResourceFilesLoader resourceFilesLoader, int i, int i2) {
        this.mResourceFilesLoader = resourceFilesLoader;
        this.mResIdVoiceAppUpdateCertifiedLocaleList = i;
        this.mResIdVoiceAppUpdateCertifiedLocaleDefault = i2;
    }
}
