package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class AlexaLocale {
    private final String name;

    public AlexaLocale(String str) {
        Preconditions.notEmpty(str, "name");
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AlexaLocale.class == obj.getClass()) {
            return Objects.equals(this.name, ((AlexaLocale) obj).name);
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("AlexaLocale{name='"), this.name, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
