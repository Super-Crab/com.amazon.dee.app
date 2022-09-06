package com.amazon.clouddrive.cdasdk.dagger.module;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cdp.CDPUtil;
import com.amazon.clouddrive.cdasdk.cdp.CDPUtilImpl;
import com.amazon.clouddrive.cdasdk.util.AndroidLogger;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import com.amazon.clouddrive.cdasdk.util.SystemUtilImpl;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class ApplicationModule {
    @NonNull
    private static final String LOG_PREFIX = "CDASDK";
    private final Context appContext;

    public ApplicationModule(Context context) {
        this.appContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CDPUtil provideCDPUtil() {
        return new CDPUtilImpl(this.appContext.getContentResolver());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public Logger provideLogger() {
        return new AndroidLogger(LOG_PREFIX);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public SystemUtil provideSystemUtil() {
        return new SystemUtilImpl();
    }
}
