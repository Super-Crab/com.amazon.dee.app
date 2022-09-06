package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes6.dex */
public final class ZipFirmwareComponentSupplier extends Sha256FirmwareComponentSupplier {
    private static final int INVALID_SIZE = -1;
    private final FirmwareContract.Component component;
    private final File file;
    private int size;

    public ZipFirmwareComponentSupplier(File file, FirmwareContract.Component component) {
        Preconditions.notNull(file, "file");
        Preconditions.notNull(component, JsonFields.COMPONENT);
        this.component = component;
        this.file = file;
        this.size = -1;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public String getName() {
        return this.component.getName();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public synchronized int getSize() throws IOException {
        if (this.size != -1) {
            return this.size;
        }
        ZipFile zipFile = new ZipFile(this.file, 1);
        ZipEntry entry = zipFile.getEntry(this.component.getReference());
        if (entry != null) {
            this.size = (int) entry.getSize();
            zipFile.close();
            return this.size;
        }
        throw new IOException("Component with a name `" + this.component.getName() + "` does not exist!");
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public int getVersion() {
        return this.component.getVersion();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public Source open() throws IOException {
        return new ZipEntrySource(this.file, this.component.getReference());
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public Source open(int i, int i2) throws IOException {
        return new ZipEntrySource(this.file, this.component.getReference(), i, i2);
    }
}
