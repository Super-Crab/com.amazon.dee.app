package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
/* loaded from: classes4.dex */
public class PropertyFilePropertySource extends PropertiesPropertySource {
    public PropertyFilePropertySource(String str) {
        super(loadPropertiesFile(str));
    }

    private static Properties loadPropertiesFile(String str) {
        Properties properties = new Properties();
        for (URL url : LoaderUtil.findResources(str)) {
            try {
                InputStream openStream = url.openStream();
                properties.load(openStream);
                if (openStream != null) {
                    openStream.close();
                }
            } catch (IOException e) {
                LowLevelLogUtil.logException("Unable to read " + url, e);
            }
        }
        return properties;
    }

    @Override // org.apache.logging.log4j.util.PropertiesPropertySource, org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return 0;
    }
}
