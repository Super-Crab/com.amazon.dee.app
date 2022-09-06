package com.typesafe.config;

import java.io.File;
/* loaded from: classes3.dex */
public interface ConfigIncluderFile {
    ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file);
}
