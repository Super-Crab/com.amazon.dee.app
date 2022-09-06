package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
/* loaded from: classes4.dex */
public class TigerDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH = 24;
    private static final long[] t1 = {192161084409973854L, -6034178070669973268L, 8272369121297300691L, 7854730284916899642L, -3631738584360316525L, 8463286011307239906L, -5664346993730092093L, 5082381371487377520L, -1536603760329757466L, -4232985935611735204L, 5541490850629862524L, 766444128913191948L, 1204553577021685498L, -4121719295987045526L, 1401289229890216703L, 1893918052108309022L, 5461170853188208586L, 2807403890869420487L, -8822417684582283338L, 5699452412975025298L, -2914262034798377397L, -8199292901130911363L, 7624427211800470465L, -5330070367527189138L, 9043806901924967914L, 7231827479902542914L, -4667804575905660192L, 6875646691050945796L, -954047427515838778L, 7786398710221814956L, 8167597339425066981L, 1830707105885056415L, -192929137551915557L, -4000909680243679221L, -8790383730744944306L, -6559119868654993229L, -8046943608939121133L, -2635222011098072079L, 1783120314242633559L, 248005612187258982L, 7688500634458409525L, -799055769434250085L, 8591138587399736033L, -2813706756098348539L, -4803442773389201549L, 5042603696143252264L, 2053990370701680515L, -8434990628116389527L, 3741955435321465241L, 4334407786093429776L, -5399798173115342087L, 1449859124008718907L, -259597992345095852L, -2299784421946890745L, -8624947886301142065L, -7850603641235491331L, 3847074041673952000L, 4649400157396704725L, -4273499526689310132L, -3840742565288711634L, 2909491499011162061L, 4458122598401901638L, 7071481730398905774L, 6725294491764459774L, -6201551736110472662L, -4372530048007926361L, 1226483701329067140L, -2522035007050864557L, -3676115808446124170L, -4975751036383735295L, -1831728144282101387L, -7732658914112356844L, 479582384021555544L, 8040612334407127321L, -2798227069691230528L, -1334228551670664750L, 8751740296797632830L, 6603430683508552489L, 8942924799792477540L, 3573742753214737511L, -2419519573825602302L, 6349030933445924429L, -2501945979441900175L, -6177453506703404958L, -7885857697280165792L, 5194369709296555225L, 7174555471952375656L, 7982812746821821468L, -8707669106532426453L, 3232013613859041307L, -5747376245209101971L, -2231459388012946321L, 3112410413624570453L, -2336602742119691332L, 6658792778814911418L, 6126246269502162262L, -6070952467612144753L, 4721051187472420532L, -5533619424469951182L, -4853025588084287359L, 2663576151211431276L, 928112258657309258L, 5664920977038299994L, 2704699625848084345L, 2312925355491498803L, -528812816973409076L, 2964761606854114992L, 4148718494125202372L, 4082542483235864459L, 5171535286737311423L, 2166137813939512309L, 8844224567096109974L, -6373247044080797239L, -8133614489572350707L, 7053919794999990929L, 5576291611870337032L, -1374825740467639573L, -734453569254161202L, -705972172313107935L, -6688726126811769884L, -7468621655906046812L, -3527580439205474383L, -6956282119872554589L, -6281089153129775081L, 853355433004222246L, -1924221946255713479L, 2124075034376372323L, 5881355904936746717L, 1033318428544969251L, 1692585388818821524L, -1245985052454466526L, 1107424405919510210L, -9211670503851965599L, -5975256720516651978L, 963191604767572015L, 4506934758573727688L, -6511972687387035778L, -6714534832456272315L, 7421261837586505858L, 3318186242040429129L, -4402061108394378299L, 1910808081503L, 4771413979138012118L, -3357965141731676491L, -6811660122601107496L, 3247421105326436348L, -1009844908434318049L, 8353265116968520410L, -5881406294935394735L, -7574869783018555510L, 6528592316425799439L, -3049672598698961616L, -3303981966096002009L, 7320455443630736945L, -7351974990356818097L, 2539802313181221187L, -7307523792611951465L, 6084456898448652712L, 1615327116689102472L, 8126548348642832045L, -1094214848903295726L, 6320848846662414801L, -1163799684465161365L, 3439926484095136410L, -7218302546559918104L, 4583261464596863494L, 5278432013075676693L, 672210957064462075L, -5420889727701263133L, -3948047341652367807L, 3753742208681096767L, -5185515461782971584L, -460252340867529358L, 111470777923844445L, 1951374535466601971L, -8875343681432095955L, -4493729248843343338L, 4830799035278983864L, -5224728565293047538L, 6842302225500364445L, -7111193868311747516L, -2729919277420993032L, -5582278241003401657L, -126421769187551098L, -4035721366655415313L, -1986169280154305277L, 3977519900599801820L, 9148781857317432677L, 6468933130968205401L, 8516219711084257782L, 1539015908620793624L, 7527026033758878374L, -1647949680688450337L, 3088835283432281588L, 3651919061693825289L, -8985256062000155568L, -423165018983337331L, -7032056788937726985L, 308165109378616703L, 8884692927086426203L, 2438838841395254149L, -3550173447755953499L, 2823241734971430590L, 3896218688877146334L, 393786506094771122L, -3117973570538943511L, -7973569017697024389L, -8368763565314219996L, 6934559736714979565L, -589348163057397487L, -7554853961030558080L, -6878676038788161577L, -3798065817641571893L, -9101961441510934879L, -4559443103670756675L, -7665374195348870830L, -8336074436196531783L, 4236391428300945648L, 555138268555536248L, 5351590591369890935L, 4306521946498657944L, -7151482210676895604L, 4901816398460471456L, -9033789479800328823L, 7485939926152528684L, -5105994143555176462L, 6245128712556390173L, -4718679834244078161L, -325273111308121687L, 7772052866533484500L, 639373189613950878L, 2515940555210603828L, -2058685867725021174L, 9187445612742136046L, -5771987833248487369L, -2125811817212952004L, -3204735567712096048L, -3393897870002714342L, 1313621308117380133L, 3526835097255131285L, -4953033604042954265L, 8704164972314360376L, -920137909863202916L, 5969067443919232116L, 5791404459833380522L, -1682712826007985785L, 6001456072058810555L, -8273861206301250160L, 2241175407069758350L, -2962551490920225208L, 8359644330926224055L, -8523485772611717717L, -5183265553382750L, -1789270636298447811L, -6471656072873752544L, -1458735953920612486L};
    private static final long[] t2 = {-1826563305001377480L, -5358963986493047656L, 6213947727727520144L, 5496303794639560149L, -2795981259149962188L, 642450021946863605L, -2925749420550550287L, -4252676236223476327L, -2372897249057438062L, -2455723000952046826L, 8011611286970690052L, 5372247966639775667L, -6490268738015937967L, -265982677241022690L, -1711898199407229911L, -2553549223344005918L, -3655427155680827379L, 1788379855404599063L, 3792259505844355329L, 857793142685420274L, 2176386753693503798L, -2281187609587102471L, -12877901320348396L, 6070247714570225101L, 7358743242340641331L, -8703516059324417162L, 1522910625901990663L, -2134847759353728262L, 5235630359010597374L, -5774648161970196758L, 277273466943670671L, 3580831169916433691L, -1032406685548103719L, 4657750985732713388L, 1177149711660596421L, 8685721698255572101L, -3227632359902186326L, -6349410231276355429L, -4809500581665772080L, -7923309769729008016L, -6726740716384263588L, -4587792071496920925L, -658271017113840853L, 3834592178494549117L, -3853851402329989932L, -8865288174312808228L, 8774750272303345432L, -8428026360225307604L, -3404183201405868250L, 6519077675840655372L, 1009372798613472243L, -4504928615151511518L, 7670504156571609794L, -9068448121725124008L, 7481699948221361317L, 2131352009749933493L, 7854556580946198495L, 5848046147829288198L, 6811751916476253359L, -635956774299390418L, -4737535235939835750L, -1614809042241653147L, 8245611441321613668L, 8087057586628171618L, 5058061449640751271L, -5151918184365513026L, 7212395796113148780L, 8872633840395976086L, 8602726521519041395L, -5885490816789515276L, 6042660761688602872L, 1642367900117110883L, 25924001596622557L, 7531865058110106323L, 4223621278438660202L, 3926684511422013614L, -2064363959953346089L, 5939130201053773422L, 8312208923375399755L, 5278156969609628584L, -5712322089306707131L, 3610014133393185213L, -8850224129823554669L, -7989215126425784091L, 7953444341930717599L, -5072589324995998940L, -3677986556148923193L, 5127306049615917691L, 9121210965518562125L, 8462056263389103903L, -743704981880018871L, 5658738406708581754L, 3084862250275496789L, -2839477530259368618L, -3966384508771725354L, -3487534071112132806L, -123994483119243460L, -1345606558677941971L, -8999779576894164844L, -4191785782441631580L, 1116769798908306816L, 1871732813531574911L, -5639228995346094013L, 2050857069623328786L, 942713319182180155L, -8555767913901511542L, -1938713800388260250L, 7028952989422544417L, 9018945159409650955L, -9098571702620193189L, 512456053301416255L, -4053543709501018729L, -4330900206871259305L, -1512795427272957464L, -3102984968199159270L, -7389706432295929941L, -6638196300801425917L, -7112719166685012944L, 4569666897377300404L, -7151449437793514816L, 4462677101358564049L, 3679240545963649394L, -4129112553160565951L, 776201060342576796L, -1202834617519492059L, -842133208882402856L, -8445297248460022090L, 3458390008116962295L, -8107400727032609416L, 6618311662604863029L, 4790267690900900096L, 1716087693007726108L, 4148457837926911568L, -5418957485852076861L, 8968309666649857421L, -2611360075161572255L, 6968029403465067289L, -3584187592496365262L, 500987773930853904L, -8168172799095912208L, 2355660670689429871L, 3178293543037890097L, -5583593033549110520L, -6297125087914569009L, 894835714693979080L, -5305826774090122525L, -348051181029808153L, 352461093517089771L, 5441805419015688358L, -3049381223523647492L, 3501129463520285556L, -4980126173351398283L, -8303518980934164731L, -7446347735086057113L, 2615208954064994172L, -522603252265687058L, 2237558221535645089L, -3911919600557704777L, -5210711461681408094L, 7102368496127332321L, -7719366717024918019L, 399232473491847935L, 7140013836546489399L, -8234741283244511424L, -2231392863125672626L, -7060197492102713059L, 5038446221635409553L, 6294769326316815049L, -387802090031244907L, -3350046130045840024L, -2666808022981539793L, -6161723600240465717L, 2783168786742146440L, 1986639352536355296L, -1988727118208302602L, 8799325730492140254L, 7305467695957075406L, 2551364576700533681L, -6081001307066006598L, -4889804522683628146L, -7324859595388608820L, -6885748294050442179L, 5760535140236403614L, 1501217875009212803L, -1291632093432900094L, -7706153952057205239L, 6454505253869455699L, 4319683495060363885L, -6244922308576078969L, -6818767823778904188L, 2960027307368769952L, 8570410701452901115L, 160427886842421800L, -4969938860820756853L, -4627442630994782527L, -3285648034072744413L, -7606118162332863056L, 6176075057452006273L, 7582622308322968760L, 6649763778434249567L, -183456705028906550L, 2699628156079216836L, -1767231947251866451L, 2945653313023238585L, 2813841150172635667L, 8163160757531991904L, -7212422464109809801L, -5924618728816493121L, 649720531103423106L, 6394120152722619742L, -934965811117111118L, 4753049982369101610L, 2408845162401379802L, 1253140645631747605L, -7799048643966905049L, -1584266091164108743L, -456002869645138839L, 8367255505928917714L, 91400768704631494L, -4464375255980341934L, 1938401838693046941L, -7520293791609324052L, -8636597607271566304L, 3990523136699180870L, 7731749711829208666L, 4875740361372990282L, 9173201802070489451L, 7834799413446679311L, -6433392137177717442L, 3325271250982575439L, -8730608807451740020L, -2389358865336045484L, -9209652622095187875L, 4359958813756723849L, 4539467735137059035L, -5508531677782308793L, 1312945880979454078L, -947428475416758718L, 4958176066159770025L, 1374196081931091686L, -6918434684938959032L, -1095184559281703237L, -1411469442470588444L, 3145683508650593868L, -6039522865352658195L, -3804467173852034031L, -6563710254104815428L, 6868326517302426863L, 6758043032196830276L, 5827167051130463242L, 4074828688890126937L, 3293442170241026694L, -8065760984084440343L, 5618223731912049521L, -3014545685365689991L, 2520538699101199374L};
    private static final long[] t3 = {-819712100864953445L, 5224129141031473793L, -1683494792012715969L, 3214246200928423523L, -2720183745931134014L, 3432136347919366758L, -6844377996819786796L, -4697838837464539535L, -3480123136110369641L, -5257202687841710057L, -3160671586143389472L, -8143604544638974599L, -7582212342885995579L, 7399204607179264370L, 2410740665327626235L, -5531319028708868287L, -1132011872800708955L, -8244108713684067595L, -8100030830173699490L, -865042824158552761L, -1406263208487841571L, -743744098937138031L, -7255025749313877870L, 5293216666010209768L, -6686350151342941087L, 505172698323928814L, -8504163865352868456L, -6039198373597746942L, 2102395425312436973L, -1480681786698906867L, 6364975572501938982L, -7035658141633266754L, -8022507636838873565L, -4480433668109774745L, 2328871106231838244L, 1378680973804076623L, -3586772320324138908L, -2755027987269747529L, 7519553577929664460L, 460638964809724379L, -99820877092259348L, 6562793443469826132L, 1580997072160885165L, 859005579845670993L, -3058956174016989192L, -3379814835910611228L, -3936971176641920257L, -8723858077265400670L, 3784640730692549981L, -2514946515147142870L, -718211188705137671L, 5877026246039211124L, -8623573777109189598L, -6383628662057423219L, 4036482174343220762L, -6451625591996463702L, -5974472282720051687L, -4119613249555124729L, -4204805774663870152L, 1637614953354483776L, 1768420517056302872L, -6063481615036972513L, 4469119677486524438L, 6862084742702193339L, 2666591392741323510L, 1958911907595193257L, 2078226524874004819L, 9182514826368667184L, -5667455777910095811L, -6961112304229951815L, 7984583406477441100L, 5152724216922222472L, -2011927023009527807L, -212234053999724107L, 4838452819165657451L, -8437636414480207278L, -4364095106444861094L, -8843563141488759799L, -952547977505311611L, 7192165871822020282L, -8957588412064574366L, 4293149567017494192L, 6266031685674981260L, 3297360663327026118L, -7424220229153493459L, 1848411117523063487L, 4803542876947788811L, -6514007507455064743L, 3918859449562378630L, 7730455268829558643L, 2300310138214025757L, 5073098731442674389L, -1867327214174801803L, -5119713925479725192L, 2481833961960165662L, 3483465760582650171L, -3799159280037322961L, -2614176868807805682L, 3683901813415452623L, -6586240258798896426L, -6280196637815307286L, -6878770741467980580L, -8649528727307138543L, 1263269478536931145L, -7419991789716909164L, -5769815365846261236L, 7280608515770959015L, 7790930297845911262L, -5059374975740702796L, -6705059931318638429L, 8900403996915095151L, 8816891275549542045L, -476483339080012016L, -1232282160203339243L, 3119849171172694992L, 7662494604586420558L, 149203013753700084L, 5530308158539891708L, 4143436129840869576L, -3411623459852687238L, -1026352410626214551L, -8324492521276276327L, 6707891355510602429L, 5715986277202524800L, -393206988093480487L, 4600951196636466039L, -4593511655318796512L, 9065747437067558111L, -8901650410637853864L, 2592076422926394627L, 228032410479194937L, 6667480117540136779L, 588648581915253038L, -2336950474993240516L, 3634608293302267354L, 1202024298738736502L, 6299068367672194603L, 1932346445954743183L, 7573861666572117031L, -61815566784892605L, 3549459440654955014L, 8158286332358861718L, -7670372790848096527L, -515956617046547146L, -3963219078081420846L, 8464707252757847009L, 397230465775035974L, -4957137534187579283L, 675316509725923312L, 2628613740627889320L, -2532211618462009391L, 5345232712238813773L, -4776658006885916949L, 3062009004852183467L, -2381228231588757251L, 74184876899443393L, -1882978417976974457L, 9131956796466541322L, 8604540880985875509L, 22099178757704754L, -1755823172185693422L, -7115222264497037070L, 2945473010562318822L, -3264392033958139096L, 2789803412788518275L, -5023951698716947073L, -2879016497062593138L, 1017933909609308228L, -2136777458168640962L, 8230916861376446652L, -4050239832011059757L, 8983610917420146076L, 8543542228473779244L, 1721876046845854392L, -2252284190053484385L, 5559864569757380000L, 4937681992884682033L, -5441254327629638811L, -9066842030330493037L, 5670390740934713304L, 2219071780988037499L, 7008521987288882964L, 6028345117330418825L, -7500176903196747008L, 7071075452076274675L, -1604175089662029304L, 1445978213955986826L, -7979034942316814172L, 951333080223670799L, 6099155138413436065L, -4305900099056973791L, -6236769450809946705L, -2912898243239114769L, -2065740773420267803L, -3827177893057145596L, 1340472571717533606L, -3648363291767490877L, -5756567784146095673L, 4461163794677446508L, -5848717005041324781L, 3341940384398866564L, -4882598382547103543L, 3829921822543532494L, 899996630714791418L, 6478536468284266291L, 2994597028103565543L, 6124895672834828926L, -8376542604899771579L, -4412652237062246342L, -7724700941812371646L, 728866099714851926L, 339635816873858970L, -1153572816294167456L, -592215260546165052L, -7150089944179092253L, 8700134485486622004L, -5552633324984327062L, -1298517758115136471L, 8749621007278605595L, -6133576477421907076L, 4199955888901663150L, -5341432795218012713L, -239890188217778377L, 8106773277103211697L, -2229320058079270256L, 5930619164422717276L, 4368075505682949467L, 4623369983466747106L, 8403817438537116875L, -5327756068839670070L, 1151085119119418028L, 6933250016240323664L, 6814675599201764477L, -2995490164984896514L, 5778917359701360712L, -7334472845550608018L, -9212347808668562614L, -7786744047088363785L, 4025584697920591189L, 5446500518121291045L, -7866665254384488512L, -352887593087136842L, 8290028954029701554L, -9087549732707247512L, 7234639242841923679L, 2860911103167493259L, -3716770017321781837L, 7444204691177324181L, 8012224255291120002L, 6549509778060988165L, -4656265058823564969L, -1532696805485516055L, 4993489137437819341L, 4727924503904151836L, -3180601338503688336L, 7858325008468642462L};
    private static final long[] t4 = {6561287832113134677L, 1893413629145602549L, -6205320776685678598L, 7334764389497132503L, 421942495471316930L, -9085229951450268347L, 5948965432456907277L, -6872877502453521409L, 4831763938021002582L, -4272888574428519313L, 5678704711006605406L, 4536654317168965104L, 802439540090739142L, 1728614842704535657L, 7852250862810361152L, -2970083550513149273L, 6999787169451700297L, 327545298748531618L, -2764213178345403342L, 9213801181845131435L, -5950018878971805109L, -2186876610533351532L, -3100863505161590557L, -194921935069456237L, 2629011484744925146L, 679658461659738748L, -3068808746888436091L, 2845612796809381245L, -7722098226173915145L, 7273530125705028225L, 4410076014410041819L, -2304212329100317967L, -45936371244098582L, -5712723046817425393L, 8922873767131958175L, -3382299200423854708L, -3236816455951139535L, -4036747678298392505L, 5226125132195873799L, 2940247444995640068L, -4418018165041970817L, 6671397049608501367L, 8821388386505911040L, -3580187736799586652L, -1447046360908978430L, 2147098610462912262L, -1956265881574637814L, -2856917834249223582L, 5141735866072457044L, 3265027362719053310L, -6450920645962515936L, 6017965846669640613L, 4287051124723328232L, 8655371236021312991L, -1156847972119148173L, 2365060307249772354L, 1630631832073154105L, 1828719980936758421L, 2674037562503248056L, -7295616781251116690L, -1363141094472255887L, 204405347605452144L, 5797523068258732423L, 8122903338174012641L, 8739821670855295734L, 961841682317282412L, 3487881148722869326L, -7995384159388863717L, 7665614591556333409L, -7831409025227614873L, -822907162794399275L, -1691135090558933875L, 3797048810173566205L, -2578904300750297763L, -3410711173298709536L, 577633178325057199L, -7379212936790430923L, -9035774148364232240L, 2754939666238358593L, 8444132705799138470L, -7894221632442939675L, 3065464070595795438L, -6610449357786147779L, 3184382822055416328L, 5740274767717360273L, 6179930651821454089L, -4826152258144849421L, 5115645765347262247L, 4602739923119569497L, -3465801151231271281L, -6359599548771540712L, -1926152657970122275L, -8468989295385802946L, -6500580506154635033L, 4125629484990072616L, -6834670983768857044L, -4845179353893108027L, 4230689665262407186L, -1849684427061896393L, 9047540561879224854L, 1112218670439199625L, 8426162753992594376L, -5990769681480860131L, -2503790423972405993L, 4028912247909671416L, -409156412951274838L, -8377831951645714695L, -1152570669068554652L, -6327418252815316840L, -3725559206061705268L, 1964465731879646024L, -2441760721249263597L, 6946242362685775318L, -3298979752616086841L, -7236283555339513389L, -1419193050620496778L, -93735727476260563L, -5905399081030416230L, 2507248404937789251L, 7581261321693772141L, -8836566033099333598L, 520172056875071564L, 3738403388662150470L, -2357506837776452040L, -5002739851233418934L, 930169001927683533L, 6889748805645999668L, -1031349426815687751L, 7941113837267854943L, -1243211017071393764L, -2154628650105719635L, 6332043450707792835L, 3386824618901547762L, 7130458179308482168L, 1271522336860346025L, -997034324337437613L, 4823850509807911142L, 3107332511049695348L, 5437793788182680416L, -8315628002795417155L, 1494290439970088554L, -8609438560643873897L, -8207953325454440687L, -5432621302919780015L, 1159256241058966379L, 1026141471931805870L, -8215608786054685932L, -609691062749569444L, 7511556330643118785L, -3915792337899679783L, 3932170512244996561L, 6834333685245251200L, 4355290964656419152L, 6487547078612259600L, 6267880520331323438L, -1545475867304599653L, 8190919284549556346L, 3366895789332200348L, 2444540809879438627L, 6459524513146455969L, 4077716903750958194L, -6168929569432701476L, -6973483665415634802L, -5197441416039796052L, 7734160491610189202L, 7910254887717195099L, 3836881802794822270L, 8311228008842563790L, 730509642500215940L, -650400159804944995L, -5124223765383482859L, 3579688877020158541L, 8591780283260295173L, 5028082178778891827L, -498814760953987530L, -2709709455026140056L, 5487541034902828271L, 8530400576707172340L, -7604535187505054453L, -869656751120750718L, 4656569414526204412L, 491061932033469878L, 8035458231926703496L, 137019260109594401L, 7421708309958176805L, 8223709417363553275L, 5401705824239018731L, -7162608250562934562L, 5308870500428712900L, -5508949737295341638L, 1376856236535589493L, -5655908917112005032L, -7100674984259216372L, 1332977380922036690L, 3015788518022419172L, -6718854486329987908L, 6396540069380292132L, 2034188120276215631L, -1655134238111203034L, -509741179510489141L, 3623665942510192329L, -9164935270648710301L, 1765784450088366494L, 5837777785993897047L, 1564973338399864744L, -2605395199060435761L, 4964475598524693274L, -5312043978489901415L, 6706291041494563888L, -789946623649963734L, -8091303779971721549L, 7456716478970921562L, -335263357675197259L, -8515348892102079999L, -7048796562806032069L, -233028078259189719L, 284725780453796946L, -3832073186324226638L, -4921235094493811069L, -5089093504863659344L, -5607539644671350465L, -8911681616096439592L, -4743899514573401058L, -7664321526450198170L, -4599281686566632149L, 2560491659082246267L, 8971180328015050686L, 2265540171276805379L, 6093561527083620308L, 12169565841013306L, 9128413284208255679L, -4178722056535276608L, -8960148414521589626L, -4216952774774654326L, -5374970407177951367L, -6668788646589711127L, -2946910590031425822L, -8674853389405194592L, -7535980417822448849L, -6115357923114297461L, -8065837346967928004L, -7487037274649424496L, -2061373546992596293L, -5783192355322733388L, 7153300451507295513L, -8779488031786375734L, 2187906506867626476L, 5612681432830855607L, -4653220181978985551L, 4688837593722596333L, -3815667051463559517L, -1779743783662362556L, -3650491565905270770L, -4529053496248414107L, -4021111997381021802L, -4350414089199835873L};
    private long a;
    private long b;
    private int bOff;
    private byte[] buf;
    private long byteCount;
    private long c;
    private long[] x;
    private int xOff;

    public TigerDigest() {
        this.buf = new byte[8];
        this.bOff = 0;
        this.x = new long[8];
        this.xOff = 0;
        reset();
    }

    public TigerDigest(TigerDigest tigerDigest) {
        this.buf = new byte[8];
        this.bOff = 0;
        this.x = new long[8];
        this.xOff = 0;
        reset(tigerDigest);
    }

    private void finish() {
        long j = this.byteCount << 3;
        byte b = 1;
        while (true) {
            update(b);
            if (this.bOff == 0) {
                processLength(j);
                processBlock();
                return;
            }
            b = 0;
        }
    }

    private void keySchedule() {
        long[] jArr = this.x;
        jArr[0] = jArr[0] - (jArr[7] ^ (-6510615555426900571L));
        jArr[1] = jArr[1] ^ jArr[0];
        jArr[2] = jArr[2] + jArr[1];
        jArr[3] = jArr[3] - (jArr[2] ^ ((~jArr[1]) << 19));
        jArr[4] = jArr[4] ^ jArr[3];
        jArr[5] = jArr[5] + jArr[4];
        jArr[6] = jArr[6] - (((~jArr[4]) >>> 23) ^ jArr[5]);
        jArr[7] = jArr[7] ^ jArr[6];
        jArr[0] = jArr[0] + jArr[7];
        jArr[1] = jArr[1] - (((~jArr[7]) << 19) ^ jArr[0]);
        jArr[2] = jArr[2] ^ jArr[1];
        jArr[3] = jArr[3] + jArr[2];
        jArr[4] = jArr[4] - (jArr[3] ^ ((~jArr[2]) >>> 23));
        jArr[5] = jArr[4] ^ jArr[5];
        jArr[6] = jArr[6] + jArr[5];
        jArr[7] = jArr[7] - (jArr[6] ^ 81985529216486895L);
    }

    private void processBlock() {
        long j = this.a;
        long j2 = this.b;
        long j3 = this.c;
        roundABC(this.x[0], 5L);
        roundBCA(this.x[1], 5L);
        roundCAB(this.x[2], 5L);
        roundABC(this.x[3], 5L);
        roundBCA(this.x[4], 5L);
        roundCAB(this.x[5], 5L);
        roundABC(this.x[6], 5L);
        roundBCA(this.x[7], 5L);
        keySchedule();
        roundCAB(this.x[0], 7L);
        roundABC(this.x[1], 7L);
        roundBCA(this.x[2], 7L);
        roundCAB(this.x[3], 7L);
        roundABC(this.x[4], 7L);
        roundBCA(this.x[5], 7L);
        roundCAB(this.x[6], 7L);
        roundABC(this.x[7], 7L);
        keySchedule();
        roundBCA(this.x[0], 9L);
        roundCAB(this.x[1], 9L);
        roundABC(this.x[2], 9L);
        roundBCA(this.x[3], 9L);
        roundCAB(this.x[4], 9L);
        roundABC(this.x[5], 9L);
        roundBCA(this.x[6], 9L);
        roundCAB(this.x[7], 9L);
        this.a = j ^ this.a;
        this.b -= j2;
        this.c += j3;
        int i = 0;
        this.xOff = 0;
        while (true) {
            long[] jArr = this.x;
            if (i != jArr.length) {
                jArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    private void processLength(long j) {
        this.x[7] = j;
    }

    private void processWord(byte[] bArr, int i) {
        long[] jArr = this.x;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        jArr[i2] = (bArr[i + 0] & 255) | ((bArr[i + 7] & 255) << 56) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
        if (this.xOff == jArr.length) {
            processBlock();
        }
        this.bOff = 0;
    }

    private void roundABC(long j, long j2) {
        this.c = j ^ this.c;
        long j3 = this.a;
        long[] jArr = t1;
        long j4 = this.c;
        long j5 = jArr[((int) j4) & 255];
        long[] jArr2 = t2;
        long j6 = j5 ^ jArr2[((int) (j4 >> 16)) & 255];
        long[] jArr3 = t3;
        long j7 = j6 ^ jArr3[((int) (j4 >> 32)) & 255];
        long[] jArr4 = t4;
        this.a = j3 - (j7 ^ jArr4[((int) (j4 >> 48)) & 255]);
        this.b += ((jArr4[((int) (j4 >> 8)) & 255] ^ jArr3[((int) (j4 >> 24)) & 255]) ^ jArr2[((int) (j4 >> 40)) & 255]) ^ jArr[((int) (j4 >> 56)) & 255];
        this.b *= j2;
    }

    private void roundBCA(long j, long j2) {
        this.a = j ^ this.a;
        long j3 = this.b;
        long[] jArr = t1;
        long j4 = this.a;
        long j5 = jArr[((int) j4) & 255];
        long[] jArr2 = t2;
        long j6 = j5 ^ jArr2[((int) (j4 >> 16)) & 255];
        long[] jArr3 = t3;
        long j7 = j6 ^ jArr3[((int) (j4 >> 32)) & 255];
        long[] jArr4 = t4;
        this.b = j3 - (j7 ^ jArr4[((int) (j4 >> 48)) & 255]);
        this.c += ((jArr4[((int) (j4 >> 8)) & 255] ^ jArr3[((int) (j4 >> 24)) & 255]) ^ jArr2[((int) (j4 >> 40)) & 255]) ^ jArr[((int) (j4 >> 56)) & 255];
        this.c *= j2;
    }

    private void roundCAB(long j, long j2) {
        this.b = j ^ this.b;
        long j3 = this.c;
        long[] jArr = t1;
        long j4 = this.b;
        long j5 = jArr[((int) j4) & 255];
        long[] jArr2 = t2;
        long j6 = j5 ^ jArr2[((int) (j4 >> 16)) & 255];
        long[] jArr3 = t3;
        long j7 = j6 ^ jArr3[((int) (j4 >> 32)) & 255];
        long[] jArr4 = t4;
        this.c = j3 - (j7 ^ jArr4[((int) (j4 >> 48)) & 255]);
        this.a += ((jArr4[((int) (j4 >> 8)) & 255] ^ jArr3[((int) (j4 >> 24)) & 255]) ^ jArr2[((int) (j4 >> 40)) & 255]) ^ jArr[((int) (j4 >> 56)) & 255];
        this.a *= j2;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new TigerDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.a, bArr, i);
        unpackWord(this.b, bArr, i + 8);
        unpackWord(this.c, bArr, i + 16);
        reset();
        return 24;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Tiger";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 24;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.a = 81985529216486895L;
        this.b = -81985529216486896L;
        this.c = -1110518062304271993L;
        this.xOff = 0;
        int i = 0;
        while (true) {
            long[] jArr = this.x;
            if (i == jArr.length) {
                break;
            }
            jArr[i] = 0;
            i++;
        }
        this.bOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i2 == bArr.length) {
                this.byteCount = 0L;
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        TigerDigest tigerDigest = (TigerDigest) memoable;
        this.a = tigerDigest.a;
        this.b = tigerDigest.b;
        this.c = tigerDigest.c;
        long[] jArr = tigerDigest.x;
        System.arraycopy(jArr, 0, this.x, 0, jArr.length);
        this.xOff = tigerDigest.xOff;
        byte[] bArr = tigerDigest.buf;
        System.arraycopy(bArr, 0, this.buf, 0, bArr.length);
        this.bOff = tigerDigest.bOff;
        this.byteCount = tigerDigest.byteCount;
    }

    public void unpackWord(long j, byte[] bArr, int i) {
        bArr[i + 7] = (byte) (j >> 56);
        bArr[i + 6] = (byte) (j >> 48);
        bArr[i + 5] = (byte) (j >> 40);
        bArr[i + 4] = (byte) (j >> 32);
        bArr[i + 3] = (byte) (j >> 24);
        bArr[i + 2] = (byte) (j >> 16);
        bArr[i + 1] = (byte) (j >> 8);
        bArr[i] = (byte) j;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bOff;
        this.bOff = i + 1;
        bArr[i] = b;
        if (this.bOff == bArr.length) {
            processWord(bArr, 0);
        }
        this.byteCount++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 8) {
            processWord(bArr, i);
            i += 8;
            i2 -= 8;
            this.byteCount += 8;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
