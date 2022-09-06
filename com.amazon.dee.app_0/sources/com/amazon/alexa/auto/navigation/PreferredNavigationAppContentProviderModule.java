package com.amazon.alexa.auto.navigation;

import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import com.amazon.alexa.auto.R;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class PreferredNavigationAppContentProviderModule {
    static final String PREFERRED_NAV_APP_CONTENT_URI_MATCHER = "preferred_nav_app_content_uri_matcher";
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreferredNavigationAppContentProviderModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return this.context;
    }

    @Provides
    @Singleton
    public PackageManager providePackageManager(Context context) {
        return context.getPackageManager();
    }

    @Provides
    @Singleton
    @Named(PREFERRED_NAV_APP_CONTENT_URI_MATCHER)
    public UriMatcher providePreferredNavAppContentUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(this.context.getString(R.string.preferredNavAppContentProviderAuthoritiesId), "/preferredNavApp", 1);
        return uriMatcher;
    }
}
