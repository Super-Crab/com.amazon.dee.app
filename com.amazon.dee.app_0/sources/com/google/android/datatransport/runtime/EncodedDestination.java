package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Set;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public interface EncodedDestination extends Destination {
    Set<Encoding> getSupportedEncodings();
}
