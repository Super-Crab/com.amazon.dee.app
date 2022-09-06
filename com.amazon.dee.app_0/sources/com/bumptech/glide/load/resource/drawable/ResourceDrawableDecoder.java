package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.facebook.common.util.UriUtil;
import java.util.List;
/* loaded from: classes6.dex */
public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    private static final int ID_PATH_SEGMENTS = 1;
    private static final int NAME_PATH_SEGMENT_INDEX = 1;
    private static final int NAME_URI_PATH_SEGMENTS = 2;
    private static final int RESOURCE_ID_SEGMENT_INDEX = 0;
    private static final int TYPE_PATH_SEGMENT_INDEX = 0;
    private final Context context;

    public ResourceDrawableDecoder(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    private Context getContextForPackage(Uri uri, String str) {
        try {
            return this.context.createPackageContext(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Failed to obtain context or unrecognized Uri format for: ", uri), e);
        }
    }

    @DrawableRes
    private int loadResourceIdFromUri(Uri uri) {
        Integer valueOf;
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            String authority = uri.getAuthority();
            String str = pathSegments.get(1);
            valueOf = Integer.valueOf(this.context.getResources().getIdentifier(str, pathSegments.get(0), authority));
        } else {
            if (pathSegments.size() == 1) {
                try {
                    valueOf = Integer.valueOf(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                }
            }
            valueOf = null;
        }
        if (valueOf != null) {
            if (valueOf.intValue() != 0) {
                return valueOf.intValue();
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Failed to obtain resource id for: ", uri));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Unrecognized Uri format: ", uri));
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    @Nullable
    public Resource<Drawable> decode(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        int loadResourceIdFromUri = loadResourceIdFromUri(uri);
        String authority = uri.getAuthority();
        return NonOwnedDrawableResource.newInstance(DrawableDecoderCompat.getDrawable(authority.equals(this.context.getPackageName()) ? this.context : getContextForPackage(uri, authority), loadResourceIdFromUri));
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull Uri uri, @NonNull Options options) {
        return uri.getScheme().equals(UriUtil.QUALIFIED_RESOURCE_SCHEME);
    }
}
