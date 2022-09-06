package com.amazon.blueshift.bluefront.android.http.part;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public class OptionalSerializedJsonPart extends Part {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json");
    private final Optional<String> mOptionalPart;

    public OptionalSerializedJsonPart(Optional<String> optional, String str) {
        super(str, CONTENT_TYPE);
        Preconditions.checkNotNull(optional, "part cannot be empty");
        this.mOptionalPart = optional;
    }

    @Override // com.amazon.blueshift.bluefront.android.http.part.Part
    protected void writeBody(OutputStream outputStream) throws Exception {
        outputStream.write(this.mOptionalPart.get().getBytes(Charsets.UTF_8.displayName()));
    }

    @Override // com.amazon.blueshift.bluefront.android.http.part.Part
    public void writePart(OutputStream outputStream, String str) throws Exception {
        if (!this.mOptionalPart.isPresent()) {
            return;
        }
        super.writePart(outputStream, str);
    }
}
