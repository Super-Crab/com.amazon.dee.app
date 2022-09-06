package com.amazon.alexa.sharing.media.picker.util;

import android.content.Context;
import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.alexa.sharing.util.FileUtil;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ImageUtil_Factory implements Factory<ImageUtil> {
    private final Provider<DeviceInfoUtil> deviceInfoUtilProvider;
    private final Provider<ExifExtractor> exifExtractorProvider;
    private final Provider<FileUtil> fileUtilProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<UriUtil> uriUtilProvider;

    public ImageUtil_Factory(Provider<Context> provider, Provider<UriUtil> provider2, Provider<FileUtil> provider3, Provider<DeviceInfoUtil> provider4, Provider<ExifExtractor> provider5) {
        this.mContextProvider = provider;
        this.uriUtilProvider = provider2;
        this.fileUtilProvider = provider3;
        this.deviceInfoUtilProvider = provider4;
        this.exifExtractorProvider = provider5;
    }

    public static ImageUtil_Factory create(Provider<Context> provider, Provider<UriUtil> provider2, Provider<FileUtil> provider3, Provider<DeviceInfoUtil> provider4, Provider<ExifExtractor> provider5) {
        return new ImageUtil_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ImageUtil newImageUtil(Context context, UriUtil uriUtil, FileUtil fileUtil, DeviceInfoUtil deviceInfoUtil, ExifExtractor exifExtractor) {
        return new ImageUtil(context, uriUtil, fileUtil, deviceInfoUtil, exifExtractor);
    }

    public static ImageUtil provideInstance(Provider<Context> provider, Provider<UriUtil> provider2, Provider<FileUtil> provider3, Provider<DeviceInfoUtil> provider4, Provider<ExifExtractor> provider5) {
        return new ImageUtil(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ImageUtil mo10268get() {
        return provideInstance(this.mContextProvider, this.uriUtilProvider, this.fileUtilProvider, this.deviceInfoUtilProvider, this.exifExtractorProvider);
    }
}
