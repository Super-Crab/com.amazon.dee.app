package com.amazon.deecomms.sharing;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.util.DeviceInfo;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ShareSheetActivity extends AppCompatActivity {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ShareSheetActivity.class);
    @Inject
    ThemingUpdateUtil themingUpdateUtil;

    private void createTaskToHandleShareAction(final Intent intent) {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.sharing.ShareSheetActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(ShareSheetActivity.this.handleShareAction(intent) != null);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(@NonNull Boolean bool) {
                CommsLogger commsLogger = ShareSheetActivity.LOG;
                commsLogger.i("[Content-Sharing-Service] Share intent generated: " + bool);
                ShareSheetActivity.this.finish();
            }
        }.execute(new Void[0]);
    }

    private void prepareUI() {
        if (DeviceInfo.isPhone(getApplicationContext())) {
            setRequestedOrientation(1);
        }
        if (this.themingUpdateUtil.isMosaicThemingEnabled()) {
            ThemeUtil.setTheme(getApplicationContext());
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().getDecorView().setSystemUiVisibility(this.themingUpdateUtil.getSystemUiVisibility(getApplicationContext()));
            getWindow().setStatusBarColor(this.themingUpdateUtil.getColorFromAttribute(getApplicationContext(), R.attr.background));
        }
        setContentView(R.layout.share_sheet_interstitial);
        this.themingUpdateUtil.applyBackgroundColorToView(findViewById(R.id.share_sheet_interstitial), getApplicationContext(), R.attr.background);
        showProgressDots();
    }

    private void showProgressDots() {
        ImageView imageView = (ImageView) findViewById(R.id.progress_dots);
        imageView.setBackground(getResources().getDrawable(R.drawable.progress_dots_white));
        imageView.setVisibility(0);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        this.themingUpdateUtil.applyTintColorToDrawable(animationDrawable, getApplicationContext(), R.attr.mosaicNeutral10);
        animationDrawable.start();
    }

    @VisibleForTesting
    Intent handleShareAction(Intent intent) {
        if (intent != null && "android.intent.action.SEND".equals(intent.getAction())) {
            ContentSharingService contentSharingService = CommsDaggerWrapper.getComponent().getContentSharingService();
            if (contentSharingService == null) {
                LOG.w("[Content-Sharing-Service] CommsDaggerWrapper did not find the ContentSharingService");
                return null;
            }
            return contentSharingService.handleAndroidShareIntent(intent);
        }
        LOG.w("[Content-Sharing-Service] This activity should always be initiated by receiving a 'Share' intent");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        CommsDaggerWrapper.getComponent().inject(this);
        LOG.d("onCreate");
        prepareUI();
        createTaskToHandleShareAction(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        createTaskToHandleShareAction(intent);
    }
}
