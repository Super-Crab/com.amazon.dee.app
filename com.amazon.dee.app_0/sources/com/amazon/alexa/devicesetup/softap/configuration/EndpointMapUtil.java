package com.amazon.alexa.devicesetup.softap.configuration;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.deecomms.common.Constants;
import com.amazon.tarazed.core.logging.TarazedLogFormatter;
import com.google.common.base.Ascii;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes7.dex */
public final class EndpointMapUtil {
    private EndpointMapUtil() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static CountryCode countryCodeToUse(String str) {
        char c;
        switch (str.hashCode()) {
            case -2064598270:
                if (str.equals("https://projectdee-ui-au.aka.amazon.com")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -2032197224:
                if (str.equals("https://projectdee-ui-ca.aka.amazon.com")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -2031614928:
                if (str.equals("https://eu-api-alexa-gamma.amazon.co.jp")) {
                    c = 'b';
                    break;
                }
                c = 65535;
                break;
            case -2031614592:
                if (str.equals("https://eu-api-alexa-gamma.amazon.co.uk")) {
                    c = 136;
                    break;
                }
                c = 65535;
                break;
            case -2000520962:
                if (str.equals("https://projectdee-ui-gamma.amazon.com.au")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -2000520934:
                if (str.equals("https://projectdee-ui-gamma.amazon.com.br")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -2000520587:
                if (str.equals("https://projectdee-ui-gamma.amazon.com.mx")) {
                    c = 'f';
                    break;
                }
                c = 65535;
                break;
            case -1881670040:
                if (str.equals("https://fe-api-alexa-gamma.amazon.com.au")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1881670012:
                if (str.equals("https://fe-api-alexa-gamma.amazon.com.br")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1881669665:
                if (str.equals("https://fe-api-alexa-gamma.amazon.com.mx")) {
                    c = 'n';
                    break;
                }
                c = 65535;
                break;
            case -1865367791:
                if (str.equals("https://alexa.amazon.com.au")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1865367763:
                if (str.equals("https://alexa.amazon.com.br")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1865367416:
                if (str.equals("https://alexa.amazon.com.mx")) {
                    c = 'd';
                    break;
                }
                c = 65535;
                break;
            case -1861873023:
                if (str.equals("https://fe-api-alexa-gamma.amazon.co.jp")) {
                    c = 'c';
                    break;
                }
                c = 65535;
                break;
            case -1861872687:
                if (str.equals("https://fe-api-alexa-gamma.amazon.co.uk")) {
                    c = 137;
                    break;
                }
                c = 65535;
                break;
            case -1861347144:
                if (str.equals("https://alexa.amazon.co.jp")) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case -1861346808:
                if (str.equals("https://alexa.amazon.co.uk")) {
                    c = '~';
                    break;
                }
                c = 65535;
                break;
            case -1849191068:
                if (str.equals(WebConstants.Endpoints.GAMMA_ENDPOINT)) {
                    c = 140;
                    break;
                }
                c = 65535;
                break;
            case -1750443798:
                if (str.equals("https://na-api-alexa-preprod.aka.amazon.com")) {
                    c = 146;
                    break;
                }
                c = 65535;
                break;
            case -1555782719:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.ca")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1555782684:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.de")) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -1555782639:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.es")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -1555782609:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.fr")) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case -1555782520:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.in")) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case -1555782514:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.it")) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case -1555782367:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.nl")) {
                    c = 'p';
                    break;
                }
                c = 65535;
                break;
            case -1555782234:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.ru")) {
                    c = JsonReaderKt.BEGIN_OBJ;
                    break;
                }
                c = 65535;
                break;
            case -1433775075:
                if (str.equals("https://na-api-alexa-gamma.aka.amazon.com")) {
                    c = 147;
                    break;
                }
                c = 65535;
                break;
            case -1424459909:
                if (str.equals("https://projectdee-ui-gb.aka.amazon.com")) {
                    c = 129;
                    break;
                }
                c = 65535;
                break;
            case -1413793147:
                if (str.equals("https://fe-api-alexa-gamma.amazon.ca")) {
                    c = Chars.SPACE;
                    break;
                }
                c = 65535;
                break;
            case -1413793112:
                if (str.equals("https://fe-api-alexa-gamma.amazon.de")) {
                    c = JsonReaderKt.COMMA;
                    break;
                }
                c = 65535;
                break;
            case -1413793067:
                if (str.equals("https://fe-api-alexa-gamma.amazon.es")) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case -1413793037:
                if (str.equals("https://fe-api-alexa-gamma.amazon.fr")) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case -1413792948:
                if (str.equals("https://fe-api-alexa-gamma.amazon.in")) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case -1413792942:
                if (str.equals("https://fe-api-alexa-gamma.amazon.it")) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case -1413792795:
                if (str.equals("https://fe-api-alexa-gamma.amazon.nl")) {
                    c = 'y';
                    break;
                }
                c = 65535;
                break;
            case -1351601096:
                if (str.equals("https://projectdee-ui-nl.aka.amazon.com")) {
                    c = 'r';
                    break;
                }
                c = 65535;
                break;
            case -1330425915:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.co.jp")) {
                    c = Matrix.MATRIX_TYPE_ZERO;
                    break;
                }
                c = 65535;
                break;
            case -1330425579:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.co.uk")) {
                    c = Ascii.MAX;
                    break;
                }
                c = 65535;
                break;
            case -1235494500:
                if (str.equals("https://eu-api-alexa.amazon.ca")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1235494465:
                if (str.equals("https://eu-api-alexa.amazon.de")) {
                    c = Chars.QUOTE;
                    break;
                }
                c = 65535;
                break;
            case -1235494420:
                if (str.equals("https://eu-api-alexa.amazon.es")) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case -1235494390:
                if (str.equals("https://eu-api-alexa.amazon.fr")) {
                    c = Chars.EQ;
                    break;
                }
                c = 65535;
                break;
            case -1235494301:
                if (str.equals("https://eu-api-alexa.amazon.in")) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case -1235494295:
                if (str.equals("https://eu-api-alexa.amazon.it")) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case -1235494148:
                if (str.equals("https://eu-api-alexa.amazon.nl")) {
                    c = 't';
                    break;
                }
                c = 65535;
                break;
            case -984623490:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.com")) {
                    c = 139;
                    break;
                }
                c = 65535;
                break;
            case -919395838:
                if (str.equals("https://na-api-alexa.amazon.com.au")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -919395810:
                if (str.equals("https://na-api-alexa.amazon.com.br")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -919395463:
                if (str.equals("https://na-api-alexa.amazon.com.mx")) {
                    c = 'h';
                    break;
                }
                c = 65535;
                break;
            case -886457074:
                if (str.equals("https://fe-api-alexa.amazon.com.au")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -886457046:
                if (str.equals("https://fe-api-alexa.amazon.com.br")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -886456699:
                if (str.equals("https://fe-api-alexa.amazon.com.mx")) {
                    c = 'j';
                    break;
                }
                c = 65535;
                break;
            case -794920422:
                if (str.equals("https://na-api-alexa-preprod.amazon.co.jp")) {
                    c = '`';
                    break;
                }
                c = 65535;
                break;
            case -794920086:
                if (str.equals("https://na-api-alexa-preprod.amazon.co.uk")) {
                    c = 134;
                    break;
                }
                c = 65535;
                break;
            case -594709615:
                if (str.equals("https://fe-api-alexa-gamma.aka.amazon.com")) {
                    c = 149;
                    break;
                }
                c = 65535;
                break;
            case -549283343:
                if (str.equals("https://projectdee-ui-in.aka.amazon.com")) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case -533169901:
                if (str.equals("https://projectdee-ui-ru.aka.amazon.com")) {
                    c = JsonReaderKt.END_OBJ;
                    break;
                }
                c = 65535;
                break;
            case -507923072:
                if (str.equals("https://eu-api-alexa-gamma.aka.amazon.com")) {
                    c = 148;
                    break;
                }
                c = 65535;
                break;
            case -277301330:
                if (str.equals("https://alexa.amazon.ca")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -277301295:
                if (str.equals("https://alexa.amazon.de")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -277301250:
                if (str.equals("https://alexa.amazon.es")) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case -277301220:
                if (str.equals("https://alexa.amazon.fr")) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case -277301131:
                if (str.equals("https://alexa.amazon.in")) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case -277301125:
                if (str.equals("https://alexa.amazon.it")) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case -277300978:
                if (str.equals("https://alexa.amazon.nl")) {
                    c = 'o';
                    break;
                }
                c = 65535;
                break;
            case -277300845:
                if (str.equals("https://alexa.amazon.ru")) {
                    c = 'z';
                    break;
                }
                c = 65535;
                break;
            case -241386325:
                if (str.equals("https://projectdee-ui-mx.aka.amazon.com")) {
                    c = 'g';
                    break;
                }
                c = 65535;
                break;
            case -217042000:
                if (str.equals("https://projectdee-ui-jp.aka.amazon.com")) {
                    c = '\\';
                    break;
                }
                c = 65535;
                break;
            case -151696556:
                if (str.equals("https://fe-api-alexa.amazon.com")) {
                    c = 145;
                    break;
                }
                c = 65535;
                break;
            case -6406095:
                if (str.equals("https://alexa.amazon.com")) {
                    c = 138;
                    break;
                }
                c = 65535;
                break;
            case 74741576:
                if (str.equals("https://projectdee-ui-es.aka.amazon.com")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case 165192076:
                if (str.equals("https://na-api-alexa-preprod.amazon.ca")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 165192111:
                if (str.equals("https://na-api-alexa-preprod.amazon.de")) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 165192156:
                if (str.equals("https://na-api-alexa-preprod.amazon.es")) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 165192186:
                if (str.equals("https://na-api-alexa-preprod.amazon.fr")) {
                    c = Constants.DEFAULT_IMAGE_CHAR;
                    break;
                }
                c = 65535;
                break;
            case 165192275:
                if (str.equals("https://na-api-alexa-preprod.amazon.in")) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case 165192281:
                if (str.equals("https://na-api-alexa-preprod.amazon.it")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_UT;
                    break;
                }
                c = 65535;
                break;
            case 165192428:
                if (str.equals("https://na-api-alexa-preprod.amazon.nl")) {
                    c = 'v';
                    break;
                }
                c = 65535;
                break;
            case 206911999:
                if (str.equals("https://eu-api-alexa.amazon.com.au")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 206912027:
                if (str.equals("https://eu-api-alexa.amazon.com.br")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 206912374:
                if (str.equals("https://eu-api-alexa.amazon.com.mx")) {
                    c = 'i';
                    break;
                }
                c = 65535;
                break;
            case 248440603:
                if (str.equals("https://fe-api-alexa.amazon.co.jp")) {
                    c = '_';
                    break;
                }
                c = 65535;
                break;
            case 248440939:
                if (str.equals("https://fe-api-alexa.amazon.co.uk")) {
                    c = 133;
                    break;
                }
                c = 65535;
                break;
            case 354376707:
                if (str.equals("https://eu-api-alexa.amazon.com")) {
                    c = 144;
                    break;
                }
                c = 65535;
                break;
            case 682478891:
                if (str.equals("https://projectdee-ui-it.aka.amazon.com")) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 778775264:
                if (str.equals("https://na-api-alexa.amazon.com")) {
                    c = 143;
                    break;
                }
                c = 65535;
                break;
            case 820313958:
                if (str.equals("https://projectdee-ui-br.aka.amazon.com")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 834685269:
                if (str.equals("https://projectdee-ui.aka.amazon.com")) {
                    c = 141;
                    break;
                }
                c = 65535;
                break;
            case 899261965:
                if (str.equals("https://na-api-alexa-gamma.amazon.co.jp")) {
                    c = 'a';
                    break;
                }
                c = 65535;
                break;
            case 899262301:
                if (str.equals("https://na-api-alexa-gamma.amazon.co.uk")) {
                    c = 135;
                    break;
                }
                c = 65535;
                break;
            case 1043787051:
                if (str.equals("https://projectdee-ui-gamma.amazon.co.jp")) {
                    c = JsonReaderKt.BEGIN_LIST;
                    break;
                }
                c = 65535;
                break;
            case 1043787387:
                if (str.equals("https://projectdee-ui-gamma.amazon.co.uk")) {
                    c = 128;
                    break;
                }
                c = 65535;
                break;
            case 1078662055:
                if (str.equals("https://na-api-alexa.amazon.co.jp")) {
                    c = JsonReaderKt.END_LIST;
                    break;
                }
                c = 65535;
                break;
            case 1078662391:
                if (str.equals("https://na-api-alexa.amazon.co.uk")) {
                    c = 131;
                    break;
                }
                c = 65535;
                break;
            case 1129089519:
                if (str.equals("https://na-api-alexa-preprod.amazon.com.au")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1129089547:
                if (str.equals("https://na-api-alexa-preprod.amazon.com.br")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1129089894:
                if (str.equals("https://na-api-alexa-preprod.amazon.com.mx")) {
                    c = 'k';
                    break;
                }
                c = 65535;
                break;
            case 1253541898:
                if (str.equals("https://eu-api-alexa.amazon.co.jp")) {
                    c = '^';
                    break;
                }
                c = 65535;
                break;
            case 1253542234:
                if (str.equals("https://eu-api-alexa.amazon.co.uk")) {
                    c = 132;
                    break;
                }
                c = 65535;
                break;
            case 1291793017:
                if (str.equals("https://na-api-alexa-gamma.amazon.ca")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case 1291793052:
                if (str.equals("https://na-api-alexa-gamma.amazon.de")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 1291793097:
                if (str.equals("https://na-api-alexa-gamma.amazon.es")) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 1291793127:
                if (str.equals("https://na-api-alexa-gamma.amazon.fr")) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 1291793216:
                if (str.equals("https://na-api-alexa-gamma.amazon.in")) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 1291793222:
                if (str.equals("https://na-api-alexa-gamma.amazon.it")) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case 1291793369:
                if (str.equals("https://na-api-alexa-gamma.amazon.nl")) {
                    c = 'w';
                    break;
                }
                c = 65535;
                break;
            case 1325821979:
                if (str.equals("https://projectdee-ui-gamma.amazon.ca")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 1325822014:
                if (str.equals("https://projectdee-ui-gamma.amazon.de")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 1325822059:
                if (str.equals("https://projectdee-ui-gamma.amazon.es")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 1325822089:
                if (str.equals("https://projectdee-ui-gamma.amazon.fr")) {
                    c = JsonReaderKt.COLON;
                    break;
                }
                c = 65535;
                break;
            case 1325822178:
                if (str.equals("https://projectdee-ui-gamma.amazon.in")) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case 1325822184:
                if (str.equals("https://projectdee-ui-gamma.amazon.it")) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case 1325822331:
                if (str.equals("https://projectdee-ui-gamma.amazon.nl")) {
                    c = 'q';
                    break;
                }
                c = 65535;
                break;
            case 1325822464:
                if (str.equals("https://projectdee-ui-gamma.amazon.ru")) {
                    c = '|';
                    break;
                }
                c = 65535;
                break;
            case 1341865782:
                if (str.equals("https://eu-api-alexa-gamma.amazon.ca")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 1341865817:
                if (str.equals("https://eu-api-alexa-gamma.amazon.de")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 1341865862:
                if (str.equals("https://eu-api-alexa-gamma.amazon.es")) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case 1341865892:
                if (str.equals("https://eu-api-alexa-gamma.amazon.fr")) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 1341865981:
                if (str.equals("https://eu-api-alexa-gamma.amazon.in")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_LT;
                    break;
                }
                c = 65535;
                break;
            case 1341865987:
                if (str.equals("https://eu-api-alexa-gamma.amazon.it")) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case 1341866134:
                if (str.equals("https://eu-api-alexa-gamma.amazon.nl")) {
                    c = 'x';
                    break;
                }
                c = 65535;
                break;
            case 1446265497:
                if (str.equals("https://eu-api-alexa-gamma.amazon.com.au")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1446265525:
                if (str.equals("https://eu-api-alexa-gamma.amazon.com.br")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1446265872:
                if (str.equals("https://eu-api-alexa-gamma.amazon.com.mx")) {
                    c = 'm';
                    break;
                }
                c = 65535;
                break;
            case 1573943061:
                if (str.equals("https://projectdee-ui-de.aka.amazon.com")) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case 1708288420:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.com.au")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1708288448:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.com.br")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1708288795:
                if (str.equals("https://projectdee-ui-pre-prod.amazon.com.mx")) {
                    c = 'e';
                    break;
                }
                c = 65535;
                break;
            case 1845546931:
                if (str.equals("https://projectdee-ui-dev.integ.amazon.com")) {
                    c = 142;
                    break;
                }
                c = 65535;
                break;
            case 1934769195:
                if (str.equals("https://fe-api-alexa.amazon.ca")) {
                    c = TarazedLogFormatter.FILE_SEPARATOR;
                    break;
                }
                c = 65535;
                break;
            case 1934769230:
                if (str.equals("https://fe-api-alexa.amazon.de")) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case 1934769275:
                if (str.equals("https://fe-api-alexa.amazon.es")) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 1934769305:
                if (str.equals("https://fe-api-alexa.amazon.fr")) {
                    c = Typography.greater;
                    break;
                }
                c = 65535;
                break;
            case 1934769394:
                if (str.equals("https://fe-api-alexa.amazon.in")) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case 1934769400:
                if (str.equals("https://fe-api-alexa.amazon.it")) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case 1934769547:
                if (str.equals("https://fe-api-alexa.amazon.nl")) {
                    c = 'u';
                    break;
                }
                c = 65535;
                break;
            case 1938585450:
                if (str.equals("https://projectdee-ui-fr.aka.amazon.com")) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case 1964784415:
                if (str.equals("https://na-api-alexa.amazon.ca")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 1964784450:
                if (str.equals("https://na-api-alexa.amazon.de")) {
                    c = Typography.amp;
                    break;
                }
                c = 65535;
                break;
            case 1964784495:
                if (str.equals("https://na-api-alexa.amazon.es")) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 1964784525:
                if (str.equals("https://na-api-alexa.amazon.fr")) {
                    c = Typography.less;
                    break;
                }
                c = 65535;
                break;
            case 1964784614:
                if (str.equals("https://na-api-alexa.amazon.in")) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case 1964784620:
                if (str.equals("https://na-api-alexa.amazon.it")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_REGULAR;
                    break;
                }
                c = 65535;
                break;
            case 1964784767:
                if (str.equals("https://na-api-alexa.amazon.nl")) {
                    c = 's';
                    break;
                }
                c = 65535;
                break;
            case 1976237062:
                if (str.equals("https://projectdee-ui-brodie-beta.aka.amazon.com")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 2083263914:
                if (str.equals("https://projectdee-ui-pico-beta.aka.amazon.com")) {
                    c = 130;
                    break;
                }
                c = 65535;
                break;
            case 2109135964:
                if (str.equals("https://na-api-alexa-gamma.amazon.com.au")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2109135992:
                if (str.equals("https://na-api-alexa-gamma.amazon.com.br")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2109136339:
                if (str.equals("https://na-api-alexa-gamma.amazon.com.mx")) {
                    c = 'l';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
                return CountryCode.AU;
            case 11:
            case '\f':
            case '\r':
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return CountryCode.BR;
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return CountryCode.CA;
            case '!':
            case '\"':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
                return CountryCode.DE;
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
                return CountryCode.ES;
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'B':
                return CountryCode.FR;
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
                return CountryCode.IN;
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
                return CountryCode.IT;
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'c':
                return CountryCode.JP;
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
                return CountryCode.MX;
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
                return CountryCode.NL;
            case 'z':
            case '{':
            case '|':
            case '}':
            case '~':
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
                return CountryCode.GB;
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
                return CountryCode.US;
            default:
                return CountryCode.US;
        }
    }
}
