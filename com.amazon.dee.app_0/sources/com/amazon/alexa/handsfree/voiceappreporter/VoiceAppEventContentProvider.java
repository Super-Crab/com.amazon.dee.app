package com.amazon.alexa.handsfree.voiceappreporter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.voiceappreporter.VoiceAppEventReporterContract;
import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper;
import com.amazon.alexa.handsfree.voiceappreporter.receivers.VoiceAppEventReceiver;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoiceAppEventContentProvider extends ContentProvider {
    private static final int CRASH_REPORTS_REQUEST_CODE = 100;
    private static final String TAG = VoiceAppEventContentProvider.class.getSimpleName();
    private static final UriMatcher URI_MATCHER = getUriMatcher();
    private SignatureVerifier mSignatureVerifier;
    @Inject
    VoiceAppEventReporterDatabaseHelper mVoiceAppEventReporterDatabaseHelper;

    @NonNull
    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(VoiceAppEventReporterContract.CONTENT_AUTHORITY, VoiceAppEventReporterContract.VoiceAppEventsTable.VOICE_APP_EVENT_DIRECTORY, 100);
        return uriMatcher;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        if (!this.mSignatureVerifier.verify(getCallingPackage())) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package ");
            outline107.append(getCallingPackage());
            outline107.append(" does not have permissions to write");
            Log.w(str, outline107.toString());
            return null;
        } else if (URI_MATCHER.match(uri) == 100) {
            return VoiceAppEventReporterContract.VoiceAppEventsTable.CONTENT_ITEM_TYPE;
        } else {
            Log.e(TAG, "Content provider getType() operation called with an invalid URI.");
            return null;
        }
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues contentValues) {
        if (!this.mSignatureVerifier.verify(getCallingPackage())) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package ");
            outline107.append(getCallingPackage());
            outline107.append(" does not have permissions to write");
            Log.w(str, outline107.toString());
            return null;
        } else if (URI_MATCHER.match(uri) != 100) {
            Log.e(TAG, "Content provider insert() operation called with an invalid URI.");
            return null;
        } else {
            long insertVoiceEvent = this.mVoiceAppEventReporterDatabaseHelper.insertVoiceEvent(contentValues);
            if (insertVoiceEvent > 0) {
                Uri withAppendedId = ContentUris.withAppendedId(VoiceAppEventReporterContract.VoiceAppEventsTable.CONTENT_URI, insertVoiceEvent);
                getContext().sendBroadcast(new Intent(getContext(), VoiceAppEventReceiver.class));
                return withAppendedId;
            }
            Log.e(TAG, "Failed to insert Event Report record.");
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        ((AlexaMobileMetricsComponent) AhfComponentsProvider.getComponent(getContext(), AlexaMobileMetricsComponent.class)).inject(this);
        return onCreate(new SignatureVerifier(getContext()));
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @VisibleForTesting
    boolean onCreate(@NonNull SignatureVerifier signatureVerifier) {
        this.mSignatureVerifier = signatureVerifier;
        return true;
    }
}
