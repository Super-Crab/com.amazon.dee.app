package com.google.zxing.datamatrix.encoder;

import androidx.core.view.InputDeviceCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.net.telnet.TelnetCommand;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Base256Encoder implements Encoder {
    private static char randomize255State(char c, int i) {
        int i2 = ((i * 149) % 255) + 1 + c;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public void encode(EncoderContext encoderContext) {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104((char) 0);
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            outline104.append(encoderContext.getCurrentChar());
            encoderContext.pos++;
            if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                encoderContext.signalEncoderChange(0);
                break;
            }
        }
        int length = outline104.length() - 1;
        int codewordCount = encoderContext.getCodewordCount() + length + 1;
        encoderContext.updateSymbolInfo(codewordCount);
        boolean z = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount > 0;
        if (encoderContext.hasMoreCharacters() || z) {
            if (length <= 249) {
                outline104.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                outline104.setCharAt(0, (char) ((length / 250) + TelnetCommand.GA));
                outline104.insert(1, (char) (length % 250));
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Message length not in valid ranges: ", length));
            }
        }
        int length2 = outline104.length();
        for (int i = 0; i < length2; i++) {
            encoderContext.writeCodeword(randomize255State(outline104.charAt(i), encoderContext.getCodewordCount() + 1));
        }
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 5;
    }
}
