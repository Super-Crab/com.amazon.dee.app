package com.amazon.alexa.hho.stickynotes;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import com.amazon.alexa.hho.R;
import com.facebook.react.views.text.ReactFontManager;
/* loaded from: classes8.dex */
public class KalamFontDownloader extends FontsContractCompat.FontRequestCallback {
    private static final String FONT_FAMILY_NAME = "kalam-regular";
    private static final String FONT_PROVIDER_AUTHORITY = "com.google.android.gms.fonts";
    private static final String FONT_PROVIDER_PACKAGE = "com.google.android.gms";
    private static final String FONT_QUERY = "Kalam";
    private static final String TAG = "KalamFontDownloader";
    private final Context context;
    private final Handler handler;

    public KalamFontDownloader(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public /* synthetic */ void lambda$startFontDownload$0$KalamFontDownloader() {
        FontsContractCompat.requestFont(this.context, new FontRequest(FONT_PROVIDER_AUTHORITY, "com.google.android.gms", FONT_QUERY, R.array.com_google_android_gms_fonts_certs), this, this.handler);
    }

    @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
    public void onTypefaceRequestFailed(int i) {
        String str = TAG;
        Log.e(str, "Unable download kalam font: " + i);
    }

    @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
    public void onTypefaceRetrieved(Typeface typeface) {
        ReactFontManager.getInstance().setTypeface(FONT_FAMILY_NAME, 0, typeface);
    }

    public void startFontDownload(long j) {
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$KalamFontDownloader$nVFnfby4vsp46AI2H5Ptzo8kOME
            @Override // java.lang.Runnable
            public final void run() {
                KalamFontDownloader.this.lambda$startFontDownload$0$KalamFontDownloader();
            }
        }, j);
    }
}
