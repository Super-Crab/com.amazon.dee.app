package com.amazon.alexa.sharing.media.picker.util;

import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.alexa.sharing.util.FileUtil;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ExifExtractor_Factory implements Factory<ExifExtractor> {
    private final Provider<DeviceInfoUtil> deviceInfoUtilProvider;
    private final Provider<FileUtil> fileUtilProvider;

    public ExifExtractor_Factory(Provider<FileUtil> provider, Provider<DeviceInfoUtil> provider2) {
        this.fileUtilProvider = provider;
        this.deviceInfoUtilProvider = provider2;
    }

    public static ExifExtractor_Factory create(Provider<FileUtil> provider, Provider<DeviceInfoUtil> provider2) {
        return new ExifExtractor_Factory(provider, provider2);
    }

    public static ExifExtractor newExifExtractor(FileUtil fileUtil, DeviceInfoUtil deviceInfoUtil) {
        return new ExifExtractor(fileUtil, deviceInfoUtil);
    }

    public static ExifExtractor provideInstance(Provider<FileUtil> provider, Provider<DeviceInfoUtil> provider2) {
        return new ExifExtractor(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ExifExtractor mo10268get() {
        return provideInstance(this.fileUtilProvider, this.deviceInfoUtilProvider);
    }
}
