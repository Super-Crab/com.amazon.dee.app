package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.io.Source;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface FirmwareComponentSupplier {
    String getName();

    String getSignature() throws IOException;

    String getSignature(int i, int i2) throws IOException;

    int getSize() throws IOException;

    int getVersion();

    Source open() throws IOException;

    Source open(int i, int i2) throws IOException;
}
