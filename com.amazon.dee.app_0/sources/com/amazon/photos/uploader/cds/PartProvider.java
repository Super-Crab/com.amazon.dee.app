package com.amazon.photos.uploader.cds;

import android.content.ContentResolver;
import android.net.Uri;
import com.amazon.photos.uploader.internal.OpenForTesting;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PartProvider.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/photos/uploader/cds/PartProvider;", "", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;)V", "getPart", "", "contentUri", "Landroid/net/Uri;", "offset", "", "size", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PartProvider {
    private final ContentResolver contentResolver;

    public PartProvider(@NotNull ContentResolver contentResolver) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "contentResolver");
        this.contentResolver = contentResolver;
    }

    @NotNull
    public final byte[] getPart(@NotNull Uri contentUri, long j, int i) {
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream openInputStream = this.contentResolver.openInputStream(contentUri);
        if (openInputStream != null) {
            byte[] bArr = new byte[i];
            openInputStream.skip(j);
            int read = openInputStream.read(bArr);
            openInputStream.close();
            byteArrayOutputStream.write(bArr, 0, read);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "outputStream.toByteArray()");
        return byteArray;
    }
}
