package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.Calendar;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
/* loaded from: classes4.dex */
public abstract class ConfigurableFTPFileEntryParserImpl extends RegexFTPFileEntryParserImpl implements Configurable {
    private FTPTimestampParser timestampParser;

    public ConfigurableFTPFileEntryParserImpl(String str) {
        super(str);
        this.timestampParser = new FTPTimestampParserImpl();
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig fTPClientConfig) {
        if (this.timestampParser instanceof Configurable) {
            FTPClientConfig defaultConfiguration = getDefaultConfiguration();
            if (fTPClientConfig != null) {
                if (fTPClientConfig.getDefaultDateFormatStr() == null) {
                    fTPClientConfig.setDefaultDateFormatStr(defaultConfiguration.getDefaultDateFormatStr());
                }
                if (fTPClientConfig.getRecentDateFormatStr() == null) {
                    fTPClientConfig.setRecentDateFormatStr(defaultConfiguration.getRecentDateFormatStr());
                }
                ((Configurable) this.timestampParser).configure(fTPClientConfig);
                return;
            }
            ((Configurable) this.timestampParser).configure(defaultConfiguration);
        }
    }

    protected abstract FTPClientConfig getDefaultConfiguration();

    public Calendar parseTimestamp(String str) throws ParseException {
        return this.timestampParser.parseTimestamp(str);
    }
}
