package com.amazon.alexa.auto.navigation;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.auto.storage.StorageModule;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class PreferredNavigationAppSettingContentProvider extends ContentProvider {
    static final int CODE_PREFERRED_NAV_APP = 1;
    private static final String TAG = "PreferredNavigationAppSettingContentProvider";
    static final String URI_MATCH_PATTERN = "/preferredNavApp";
    @Inject
    PreferredNavigationAppSettingManager preferredNavAppManager;
    @Inject
    @Named("preferred_nav_app_content_uri_matcher")
    UriMatcher uriMatcher;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {StorageModule.class, PreferredNavigationAppContentProviderModule.class})
    @Singleton
    /* loaded from: classes6.dex */
    public interface Injector {
        void inject(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider);
    }

    private Cursor createCursor(String str, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{str}, 1);
        matrixCursor.newRow().add(str2);
        return matrixCursor;
    }

    private void injectDependencies() {
        DaggerPreferredNavigationAppSettingContentProvider_Injector.builder().preferredNavigationAppContentProviderModule(new PreferredNavigationAppContentProviderModule(getContext())).build().inject(this);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(Uri uri, ContentValues contentValues) {
        update(uri, contentValues, null, null);
        return uri;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        injectDependencies();
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (this.uriMatcher.match(uri) != 1) {
            return null;
        }
        return createCursor("preferred_nav_app", this.preferredNavAppManager.getPreferredNavigationApp());
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        String str2;
        String str3;
        if (this.uriMatcher.match(uri) != 1) {
            return 0;
        }
        if (contentValues == null) {
            str2 = TAG;
            str3 = "ContentValues is null";
        } else {
            String asString = contentValues.getAsString("preferred_nav_app");
            if (asString != null) {
                this.preferredNavAppManager.setPreferredNavigationApp(asString);
                return 1;
            }
            str2 = TAG;
            str3 = "PreferredNavApp is null";
        }
        Log.e(str2, str3);
        return 0;
    }
}
