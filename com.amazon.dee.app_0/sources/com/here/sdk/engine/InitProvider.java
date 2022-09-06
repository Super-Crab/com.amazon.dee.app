package com.here.sdk.engine;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.here.sdk.core.engine.ApplicationUtilsInitializer;
import com.here.sdk.core.engine.PlatformUtilsInitializer;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.core.threading.ThreadingInitializer;
import com.here.sdk.core.utilities.Preconditions;
import com.here.sdk.engine.OptionsReader;
/* loaded from: classes3.dex */
public class InitProvider extends ContentProvider {
    private static final String METADATA_USE_TEST_LIBRARY = "com.here.sdk.use_testing_library";
    private static final String TAG = InitProvider.class.getSimpleName();

    static String getSDKLibraryName(@NonNull Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return (bundle == null || !bundle.getBoolean(METADATA_USE_TEST_LIBRARY)) ? "heresdk" : "heresdk_test";
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Failed to retrieve application info.", e);
        }
    }

    public static void initialize(@NonNull Context context) {
        Log.i(TAG, "HERESDK Version 4.6.0.0.3839-release");
        loadSDK(getSDKLibraryName(context));
        initializeSDK(context);
        startConsent();
    }

    static void initializeSDK(@NonNull Context context) {
        ThreadingInitializer.initialize();
        NetworkingInitializer.initialize(context);
        PlatformUtilsInitializer.initialize(context);
        ApplicationUtilsInitializer.initialize(context);
        try {
            Class.forName("com.here.sdk.consent.ConsentInitializer").getMethod("initialize", Context.class).invoke(null, context);
        } catch (Exception unused) {
        }
        try {
            Class.forName("com.here.sdk.location.LocationInitializer").getMethod("initialize", Context.class).invoke(null, context);
        } catch (Exception unused2) {
        }
    }

    static void loadSDK(String str) {
        System.loadLibrary(str);
    }

    public static SDKNativeEngine makeSDKNativeEngine(@NonNull Context context) {
        try {
            try {
                return new SDKNativeEngine(new OptionsReader().getOptionsFromManifestFile(context));
            } catch (InstantiationErrorException e) {
                throw new IllegalStateException("Unable to create SDK Native Engine.", e);
            }
        } catch (OptionsReader.MetaDataNotFoundException e2) {
            throw new IllegalStateException("Unable to find credentials in application manifest file.", e2);
        }
    }

    static void startConsent() {
        try {
            Class<?> cls = Class.forName("com.here.sdk.consent.ConsentInternal");
            cls.getMethod("start", new Class[0]).invoke(cls.getMethod("getSharedInstance", new Class[0]).invoke(null, new Object[0]), new Object[0]);
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = (Context) Preconditions.checkNotNull(getContext());
        initialize(context);
        SDKNativeEngine.setSharedInstance(makeSDKNativeEngine(context));
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
