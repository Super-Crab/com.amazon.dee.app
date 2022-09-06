package com.amazon.alexa.accessory.kota;

import android.content.Context;
import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.davs.DeviceArtifactSupplier;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareMetadata;
import com.amazon.alexa.accessory.repositories.firmware.ZipFirmwareComponentSupplier;
import com.amazon.alexa.accessory.repositories.firmware.ZipFirmwareMetadata;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class FileSystemFirmwareSupplier implements FirmwareSupplier {
    private final File directory;
    private final PublishSubject<FirmwareContract.Package> packageSubject;

    /* loaded from: classes.dex */
    private final class FileSystemPackage implements FirmwareSupplier.StreamablePackage {
        private final FirmwareContract.Package aPackage;
        private boolean finished;
        private Sink sink;

        @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier.StreamablePackage
        public synchronized void cancel() {
            if (this.finished) {
                return;
            }
            this.finished = true;
            this.aPackage.getFile().delete();
            IOUtils.closeQuietly(this.sink);
        }

        @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier.StreamablePackage
        public synchronized FirmwareContract.Package complete() throws IOException {
            if (this.sink != null) {
                if (!this.finished) {
                    this.finished = true;
                    this.sink.flush();
                    this.sink.close();
                    FileSystemFirmwareSupplier.this.packageSubject.onNext(this.aPackage);
                    Logger.d("Exposing built package to packageSubject (" + this.aPackage.getFile().getName() + ")");
                } else {
                    throw new IOException("Cannot complete, was already complete via cancel or complete.");
                }
            } else {
                throw new IllegalStateException("Cannot complete package, sink() was not called!");
            }
            return this.aPackage;
        }

        @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier.StreamablePackage
        public synchronized Sink sink() throws IOException {
            if (this.sink == null) {
                if (!FileSystemFirmwareSupplier.this.directory.exists() && !FileSystemFirmwareSupplier.this.directory.mkdirs()) {
                    throw new FileNotFoundException("Failed to create firmware directory, firmware will not work!");
                }
                this.sink = new OutputStreamSink(new BufferedOutputStream(new FileOutputStream(this.aPackage.getFile())));
            }
            return this.sink;
        }

        private FileSystemPackage(PackageIdentifier packageIdentifier) {
            Preconditions.notNull(packageIdentifier, "packageIdentifier");
            this.aPackage = FirmwareContract.Package.create(FileSystemFirmwareSupplier.this.directory, packageIdentifier);
        }
    }

    public FileSystemFirmwareSupplier(Context context) {
        Preconditions.notNull(context, "context");
        this.packageSubject = PublishSubject.create();
        this.directory = new File(context.getCacheDir(), "firmware");
    }

    private Maybe<FirmwareContract.Package> getExistingPackageForCriteria(final PackageCandidateIdentifier packageCandidateIdentifier) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$FileSystemFirmwareSupplier$A23owp2yzwrhPgLfaM-UFc8iT-g
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileSystemFirmwareSupplier.this.lambda$getExistingPackageForCriteria$4$FileSystemFirmwareSupplier(packageCandidateIdentifier);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Single<FirmwareMetadata> getMetadata(final FirmwareContract.Package r2) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$FileSystemFirmwareSupplier$dxlLyXb3hL1T9dHHRYz6rs2wAq0
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                SingleSource just;
                just = Single.just(new ZipFirmwareMetadata(new File(r0.getReference()), FirmwareContract.Package.this.getMetadata().getReference()));
                return just;
            }
        });
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Maybe<FirmwareContract.Package> getPackage(final PackageIdentifier packageIdentifier) {
        Preconditions.notNull(packageIdentifier.updateVersion, "updateVersion");
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$FileSystemFirmwareSupplier$oHFwjqyI_i218oVSDe52TA1mvPw
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileSystemFirmwareSupplier.this.lambda$getPackage$2$FileSystemFirmwareSupplier(packageIdentifier);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Single<FirmwareSupplier.StreamablePackage> getStreamablePackage(PackageIdentifier packageIdentifier) {
        Preconditions.notNull(packageIdentifier, "packageIdentifier");
        return Single.just(new FileSystemPackage(packageIdentifier));
    }

    public /* synthetic */ MaybeSource lambda$getExistingPackageForCriteria$4$FileSystemFirmwareSupplier(final PackageCandidateIdentifier packageCandidateIdentifier) throws Throwable {
        if (!this.directory.exists()) {
            return Maybe.empty();
        }
        return Observable.fromIterable(Arrays.asList(this.directory.listFiles())).map($$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0.INSTANCE).onErrorResumeWith(Observable.empty()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$FileSystemFirmwareSupplier$FY-zJ2Sg63JO1u2AALEgYJo1T0U
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((FirmwareContract.Package) obj).isCandidate(PackageCandidateIdentifier.this);
            }
        }).reduce($$Lambda$9cgVujc98YnqEawZLXLVl4DZJc.INSTANCE);
    }

    public /* synthetic */ MaybeSource lambda$getPackage$2$FileSystemFirmwareSupplier(PackageIdentifier packageIdentifier) throws Throwable {
        FirmwareContract.Package create = FirmwareContract.Package.create(this.directory, packageIdentifier);
        if (create.getFile().exists() && create.getFile().isFile()) {
            return Maybe.just(create);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package file is not on disk: ");
        outline107.append(create.getFile().getName());
        Logger.d(outline107.toString());
        return Maybe.empty();
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Single<FirmwareComponentSupplier> queryComponent(FirmwareContract.Component component) {
        return Single.just(new ZipFirmwareComponentSupplier(new File(this.directory, component.getPackageName()), component));
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Single<FirmwareComponentSupplier> queryDAVSArtifact(DeviceArtifactContract.ArtifactPackage artifactPackage) {
        return Single.just(new DeviceArtifactSupplier(artifactPackage.getFile(), artifactPackage.getArtifactName()));
    }

    @Override // com.amazon.alexa.accessory.kota.FirmwareSupplier
    public Observable<FirmwareContract.Package> queryPackage(final PackageCandidateIdentifier packageCandidateIdentifier) {
        Preconditions.notNull(packageCandidateIdentifier, "packageIdentifier");
        return Observable.concat(getExistingPackageForCriteria(packageCandidateIdentifier).toObservable(), this.packageSubject.filter(new Predicate() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$FileSystemFirmwareSupplier$mA6umxFNcEDzpvsC77EubLT_y4w
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((FirmwareContract.Package) obj).isCandidate(PackageCandidateIdentifier.this);
            }
        }));
    }
}
