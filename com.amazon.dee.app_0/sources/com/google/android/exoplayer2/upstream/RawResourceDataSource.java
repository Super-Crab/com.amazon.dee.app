package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.util.UriUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class RawResourceDataSource extends BaseDataSource {
    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    @Nullable
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    private final String packageName;
    private final Resources resources;
    @Nullable
    private Uri uri;

    /* loaded from: classes2.dex */
    public static class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(String str) {
            super(str);
        }

        public RawResourceDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.resources = context.getResources();
        this.packageName = context.getPackageName();
    }

    public static Uri buildRawResourceUri(int i) {
        return Uri.parse("rawresource:///" + i);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws RawResourceDataSourceException {
        this.uri = null;
        try {
            try {
                if (this.inputStream != null) {
                    this.inputStream.close();
                }
                this.inputStream = null;
                try {
                    try {
                        if (this.assetFileDescriptor != null) {
                            this.assetFileDescriptor.close();
                        }
                    } catch (IOException e) {
                        throw new RawResourceDataSourceException(e);
                    }
                } finally {
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        transferEnded();
                    }
                }
            } catch (IOException e2) {
                throw new RawResourceDataSourceException(e2);
            }
        } catch (Throwable th) {
            this.inputStream = null;
            try {
                try {
                    if (this.assetFileDescriptor != null) {
                        this.assetFileDescriptor.close();
                    }
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        transferEnded();
                    }
                    throw th;
                } catch (IOException e3) {
                    throw new RawResourceDataSourceException(e3);
                }
            } finally {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws RawResourceDataSourceException {
        int parseInt;
        Uri uri = dataSpec.uri;
        this.uri = uri;
        if (!TextUtils.equals(RAW_RESOURCE_SCHEME, uri.getScheme()) && (!TextUtils.equals(UriUtil.QUALIFIED_RESOURCE_SCHEME, uri.getScheme()) || uri.getPathSegments().size() != 1 || !((String) Assertions.checkNotNull(uri.getLastPathSegment())).matches("\\d+"))) {
            if (TextUtils.equals(UriUtil.QUALIFIED_RESOURCE_SCHEME, uri.getScheme())) {
                String str = (String) Assertions.checkNotNull(uri.getPath());
                if (str.startsWith("/")) {
                    str = str.substring(1);
                }
                String host = uri.getHost();
                parseInt = this.resources.getIdentifier(GeneratedOutlineSupport1.outline91(new StringBuilder(), TextUtils.isEmpty(host) ? "" : GeneratedOutlineSupport1.outline72(host, ":"), str), "raw", this.packageName);
                if (parseInt == 0) {
                    throw new RawResourceDataSourceException("Resource not found.");
                }
            } else {
                throw new RawResourceDataSourceException("URI must either use scheme rawresource or android.resource");
            }
        } else {
            try {
                parseInt = Integer.parseInt((String) Assertions.checkNotNull(uri.getLastPathSegment()));
            } catch (NumberFormatException unused) {
                throw new RawResourceDataSourceException("Resource identifier must be an integer.");
            }
        }
        transferInitializing(dataSpec);
        AssetFileDescriptor openRawResourceFd = this.resources.openRawResourceFd(parseInt);
        this.assetFileDescriptor = openRawResourceFd;
        if (openRawResourceFd != null) {
            FileInputStream fileInputStream = new FileInputStream(openRawResourceFd.getFileDescriptor());
            this.inputStream = fileInputStream;
            try {
                fileInputStream.skip(openRawResourceFd.getStartOffset());
                if (fileInputStream.skip(dataSpec.position) >= dataSpec.position) {
                    long j = dataSpec.length;
                    long j2 = -1;
                    if (j != -1) {
                        this.bytesRemaining = j;
                    } else {
                        long length = openRawResourceFd.getLength();
                        if (length != -1) {
                            j2 = length - dataSpec.position;
                        }
                        this.bytesRemaining = j2;
                    }
                    this.opened = true;
                    transferStarted(dataSpec);
                    return this.bytesRemaining;
                }
                throw new EOFException();
            } catch (IOException e) {
                throw new RawResourceDataSourceException(e);
            }
        }
        throw new RawResourceDataSourceException(GeneratedOutlineSupport1.outline58("Resource is compressed: ", uri));
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) throws RawResourceDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, i2);
            } catch (IOException e) {
                throw new RawResourceDataSourceException(e);
            }
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i, i2);
        if (read == -1) {
            if (this.bytesRemaining != -1) {
                throw new RawResourceDataSourceException(new EOFException());
            }
            return -1;
        }
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - read;
        }
        bytesTransferred(read);
        return read;
    }
}
