package com.facebook.react.modules.blob;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactApplication;
import java.io.FileNotFoundException;
import java.io.IOException;
/* loaded from: classes2.dex */
public final class BlobProvider extends ContentProvider {
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
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        if (str.equals("r")) {
            Context applicationContext = getContext().getApplicationContext();
            BlobModule blobModule = applicationContext instanceof ReactApplication ? (BlobModule) ((ReactApplication) applicationContext).getReactNativeHost().getReactInstanceManager().getCurrentReactContext().getNativeModule(BlobModule.class) : null;
            if (blobModule != null) {
                byte[] resolve = blobModule.resolve(uri);
                if (resolve != null) {
                    try {
                        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                        ParcelFileDescriptor parcelFileDescriptor = createPipe[0];
                        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
                        autoCloseOutputStream.write(resolve);
                        autoCloseOutputStream.close();
                        return parcelFileDescriptor;
                    } catch (IOException unused) {
                        return null;
                    }
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot open ");
                outline107.append(uri.toString());
                outline107.append(", blob not found.");
                throw new FileNotFoundException(outline107.toString());
            }
            throw new RuntimeException("No blob module associated with BlobProvider");
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Cannot open ");
        outline1072.append(uri.toString());
        outline1072.append(" in mode '");
        outline1072.append(str);
        outline1072.append("'");
        throw new FileNotFoundException(outline1072.toString());
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
