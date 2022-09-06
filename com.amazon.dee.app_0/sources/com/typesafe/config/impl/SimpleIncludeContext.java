package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SimpleIncludeContext implements ConfigIncludeContext {
    private final ConfigParseOptions options;
    private final Parseable parseable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleIncludeContext(Parseable parseable) {
        this.parseable = parseable;
        this.options = SimpleIncluder.clearForInclude(parseable.options());
    }

    @Override // com.typesafe.config.ConfigIncludeContext
    public ConfigParseOptions parseOptions() {
        return this.options;
    }

    @Override // com.typesafe.config.ConfigIncludeContext
    public ConfigParseable relativeTo(String str) {
        if (ConfigImpl.traceLoadsEnabled()) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Looking for '", str, "' relative to ");
            outline115.append(this.parseable);
            ConfigImpl.trace(outline115.toString());
        }
        Parseable parseable = this.parseable;
        if (parseable != null) {
            return parseable.relativeTo(str);
        }
        return null;
    }

    @Override // com.typesafe.config.ConfigIncludeContext
    public ConfigIncludeContext setParseOptions(ConfigParseOptions configParseOptions) {
        return new SimpleIncludeContext(this.parseable, configParseOptions.setSyntax(null).setOriginDescription(null));
    }

    SimpleIncludeContext withParseable(Parseable parseable) {
        return parseable == this.parseable ? this : new SimpleIncludeContext(parseable);
    }

    private SimpleIncludeContext(Parseable parseable, ConfigParseOptions configParseOptions) {
        this.parseable = parseable;
        this.options = configParseOptions;
    }
}
