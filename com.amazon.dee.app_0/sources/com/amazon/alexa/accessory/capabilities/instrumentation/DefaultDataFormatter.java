package com.amazon.alexa.accessory.capabilities.instrumentation;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Charsets;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public final class DefaultDataFormatter implements DataFormatter {
    private static final String FOOTER = "\n~~ End of PrintDebug ~~~~~~~~~";
    private static final String HEADER = "~~ Start of PrintDebug ~~~~~~~\n";
    private static final int HEXADECIMAL_TABLE_WIDTH = 16;

    /* renamed from: com.amazon.alexa.accessory.capabilities.instrumentation.DefaultDataFormatter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$instrumentation$OutputConfiguration = new int[OutputConfiguration.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$instrumentation$OutputConfiguration[OutputConfiguration.UTF_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$instrumentation$OutputConfiguration[OutputConfiguration.HEXADECIMAL_TABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private String addHeaderAndFooter(String str) {
        return GeneratedOutlineSupport1.outline75(HEADER, str, FOOTER);
    }

    private void addRowNumberPrefix(StringBuilder sb, int i) {
        sb.append(String.format(Locale.US, "%06x", Integer.valueOf(i * 16)));
    }

    private String asHexadecimalTable(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            if (i == 0) {
                addRowNumberPrefix(sb, i2);
            }
            sb.append(" ");
            sb.append(String.format("%02x", Byte.valueOf(b)));
            i++;
            if (i == 16) {
                sb.append("\n");
                i2++;
                i = 0;
            }
        }
        return sb.toString();
    }

    private String asUtf8(byte[] bArr) {
        return new String(bArr, Charsets.UTF_8);
    }

    private String formatData(byte[] bArr, OutputConfiguration outputConfiguration) {
        int ordinal = outputConfiguration.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                Logger.e("Unsupported output configuration %s", outputConfiguration);
                return null;
            }
            return asHexadecimalTable(bArr);
        }
        return asUtf8(bArr);
    }

    @Override // com.amazon.alexa.accessory.capabilities.instrumentation.DataFormatter
    public String format(byte[] bArr, List<OutputConfiguration> list) {
        Preconditions.notNull(bArr, "data");
        Preconditions.notNull(list, "formats");
        if (list.size() != 1) {
            Logger.e("DefaultDataFormatter only supports exactly 1 output configuration at a time (received %d)", Integer.valueOf(list.size()));
            return null;
        }
        return addHeaderAndFooter(formatData(bArr, list.get(0)));
    }
}
