package com.amazon.alexa.accessory.davs;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.repositories.firmware.Sha256FirmwareComponentSupplier;
import java.io.File;
import java.io.IOException;
/* loaded from: classes.dex */
public final class DeviceArtifactSupplier extends Sha256FirmwareComponentSupplier {
    @VisibleForTesting
    static final int INVALID_VERSION = -1;
    private final String artifactName;
    private final File file;
    private final int version;

    public DeviceArtifactSupplier(File file, String str) {
        Preconditions.notNull(file, "file");
        Preconditions.notNull(str, "artifactName");
        this.file = file;
        this.artifactName = str;
        this.version = -1;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public String getName() {
        return this.artifactName;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public int getSize() throws IOException {
        return (int) this.file.length();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public int getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public Source open() throws IOException {
        return new DeviceArtifactSource(this.file);
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public Source open(int i, int i2) throws IOException {
        return new DeviceArtifactSource(this.file, i, i2);
    }
}
