package com.amazon.alexa.client.alexaservice.componentstate;

import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.lNG;
import com.google.auto.value.AutoValue;
import java.util.Date;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ExternalComponentStateEntity {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class zZm {
        public abstract zZm zZm(int i);

        public abstract zZm zZm(Name name);

        public abstract zZm zZm(Namespace namespace);

        public abstract zZm zZm(PackageName packageName);

        public abstract zZm zZm(String str);

        public abstract ExternalComponentStateEntity zZm();
    }

    public static zZm zZm() {
        return new lNG.zZm();
    }

    @Nullable
    public abstract Date BIo();

    public abstract String JTe();

    public abstract int Qle();

    public abstract PackageName jiA();

    public abstract Name zQM();

    public abstract Namespace zyO();
}
