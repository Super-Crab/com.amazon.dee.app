package com.sun.mail.handlers;

import com.google.android.exoplayer2.util.MimeTypes;
import java.awt.Image;
import javax.activation.ActivationDataFlavor;
/* loaded from: classes3.dex */
public class image_jpeg extends image_gif {
    private static ActivationDataFlavor myDF = new ActivationDataFlavor(Image.class, MimeTypes.IMAGE_JPEG, "JPEG Image");

    @Override // com.sun.mail.handlers.image_gif
    protected ActivationDataFlavor getDF() {
        return myDF;
    }
}
