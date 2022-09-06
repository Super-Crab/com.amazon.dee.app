package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import java.io.File;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class ZipFirmwareMetadata implements FirmwareMetadata {
    private final File file;
    private final String reference;

    public ZipFirmwareMetadata(File file, String str) {
        Preconditions.notNull(file, "file");
        Preconditions.notNull(str, "reference");
        this.file = file;
        this.reference = str;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareMetadata
    public Source open() throws IOException {
        return new ZipEntrySource(this.file, this.reference);
    }
}
