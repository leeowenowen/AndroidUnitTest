# Android Unit Test Sample
All Sample code is under [app/src/test/java/](https://github.com/leeowenowen/AndroidUnitTest/tree/master/app/src/test/java), code structure is as follows:    


![structure](https://raw.githubusercontent.com/wiki/leeowenowen/AndroidUnitTest/unittest.png)

#Android自动化测试（1）---最佳实践总结

如果想获得较高质量的代码和可交付产品，又不想为单元测试付出过多精力，那么恭喜你，这篇文章是为你准备的。

我知道大家讨厌枯燥的教条和繁杂的理论，所以把所有的总结归纳为几个原则，在设计和写代码的时候只要你多花几分钟时间把这几条原则过一遍，这些原则既不系统，也不全面，但是相信你会获得质量与成本的更高性价比。

**单元测试：** 顾名思义,是针对某个单元进行测试,单元可大可小,可能是一个框架的核心处理流程,一个模块的控制逻辑,一个类的实现或者一个函数。在这里你只需要关心一点，那就是单元测试的单元是有边界的，在任何时候你要开始单元测试，请确定已经划分好你的单元。

相对于集成测试，灰度测试，单元测试是测试体系中最贴近代码实现的测试，其测试方式主要是以白盒为主，由开发人员完成。毫无疑问，最接近开发的测试是可以最早发现问题的测试，在这一步发现问题，就可以有效避免问题在后期被的无限放大。

##学习质量与测试理论
这条原则尤其是为从来不写测试代码，偶然误入本文的人准备。

你是否经常告诉别人你只关心产品，只关心功能研发，质量对你无关紧要，
你是否自信心爆棚，相信自己写的代码从来都不会出问题，那么 

- 请确认你的代码从来没有出现逻辑bug.(非系统相关，非语言相关，非第三方库相关）。
- 请确认你每一次重构都可以很轻松且没有引起新的问题
- 请确认每一个新人都可以很轻松的阅读和理解你的代码
- 请确认你自己了解你都写了什么代码。

单元测试很多时候体现程序员对产品和代码的责任心，线上bug很少是靠人力能自测测出来的，它们往往是人力最容易忽略的分支条件或者边界值，如果单靠人力调试走过主流程，显然是不够的。
我们常会遇到这种情景：调通了主流程A,但是流程B不通，在修改代码调通主流程B之后，主流程A已经不通了，但是却没有被发现。 人力测试的一个大问题是在对原有代码做部分改动之后无法简单的做全流程测试，因为成本太高。而使用单元测试，你往往只需要几秒到一两分钟就可以做到。

没有单元测试的代码往往是代码结构和质量都比较差的代码，这些代码往往是“堆”成的---“Only God know who wrote this line..."诸如此类注释往往会在这种代码中出现，当项目组进入新人的时候，就无法很快的通过代码目录结构了解业务和当前实现，我们往往会用新人对业务和代码的熟悉速度和程度来评价新人，但是遇到这种项目代码，新人的结局----想想都知道很悲催。

写了几个月的代码回过头来连自己都不认识，这仿佛是一个常态，但显然是开发者没有深入总结业务和审视自身代码逻辑引起的。习惯单元测试的人往往对业务的理解更深入，对代码逻辑流程记忆的更清晰，因为达不到这两点，你往往无法写出单元测试代码。

以上点点，只是为了说明单元测试的优势，虽然产品在催，虽然很多bug需要解，如果有时间，开始单元测试吧。



##让你的代码容易测试

“可测试性”常常被我们用来作为评价代码的某种标准，我这里没有用“可”，而是“容易”测试，因为当前的各种单元测试框架越来越强大，即使你的代码结构再怎么不合理，代码逻辑写的再怎么乱，也是可以测试的，只是不那么容易测试，不仅写测试代码痛苦，遇到重构或者逻辑变更维护单元测试将会是一场噩梦。 

所以，在你设计一个类，写下一个函数的时候，思考下，如果将来要对这个类，这个函数测试的时候，你是否能很快的写出来，如果能，那么就先这么写，否则，请重新思考设计你的类和函数。

##为消耗性调试做测试
所谓消耗性调试时指费时费力或者阻碍进度的调试。

比如我们在移动设备上测试某个逻辑，这个逻辑有几个分支（不算多的分支），用调试的方式测试所有分支需要多次启动IDE调试，并通过复杂手工操作才能到达某些分支，甚至为了调试到某些分支需要修改正常的逻辑代码，这个时候，就需要为该逻辑添加单元测试了，调试各个分支的情况最多在所有单元测试跑过之后做一次用于验证。

再比如需要测试网络返回的数据，可能网络的另一端还未开发完成，或者网络数据的不同状况不容易直接在真实状况下配置，就需要使用单元测试。

>单元测试的最大特点是模拟输入输出，任何时候获取输入或者输出的成本太高的时候，都可以考虑使用单元测试。

##为稳定的部分做单元测试

单元测试带给程序员最多痛苦的地方不是开发它，而是维护它，尤其是维护易变的程序，维护单元测试耗时耗力，痛苦不堪的同时也打击了开发者的信心。

所以，最好的方式就是选择比较稳定的模块或代码逻辑做单元测试：

- 基础模块：一般每个项目代码库都会有自己的基础功能模块，基础模块的代码往往是经久累积且变动较少的，所以对基础模块写单元测试其维护成本是相对较低的。同时，基础模块往往是在项目中被大范围使用的，如果基础模块出现bug,整个程序可能会有更大范围的问题，所以为基础模块写单元测试，对于保障整个产品的质量至关重要。
- 核心业务逻辑： 我们的大多数开发是围绕业务发展的，虽然业务在不断变化，不断改进，但业务的核心逻辑往往是变动比较小的，所以为这些核心业务做单元测试是值得的且维护成本较低的。

##调试主干，测试分支
做一件事情，信心是个很重要的成功因素。

开始一个新的sprint之后，最大的任务是做完这个sprint里的开发需求，在需求还没完成之前，程序员往往很难投入精力写单元测试，因为信心， 他们担心单元测试占用太多时间导致最后没有足够的时间完成业务相关的代码。毫无疑问，在任何时候分清主次，是合理的，也是绝对没有错的。

所以我的建议是按照快速原型的思路走通主流程，通常走通整个流程之后我们就开始**调试自测其他的非主流程**，但我的建议是把这个工作尽量以单元测试的形式完成，因为主流程已经走通了，这个时候你对自己的代码整体上已经有了些信心了（因为它完成了主要功能），你需要做的是覆盖更多的分支，给自己和团队其他人更多的信心。

虽然我不建议任何事情都用量化标准衡量，但是量化标准的确从某些方面可以衡量一件事情，单元测试就是这样，如果覆盖了主流程和90%的分支流程，那么你可以自信的把这些数据给产品，测试和你的主管，这不仅体现你做事的态度，也能给他们更多的信心。


##复杂逻辑的测试越早越好

在开发的过程中，我们总会遇到复杂的逻辑，比如三个以上的输入条件，内部if-else一大堆，自己写完代码往往都要回过头来走查确认好几遍逻辑是否有问题，对于这种复杂逻辑（分支比较多），用单元测试是最高效的。

单元测试让你在测试的时候只关心被测试单元，而不必为其输入输出分散精力，想想吧，使用调试的方式来测试一个有8个分支的逻辑，你要启动八次（大约）程序，然后进行一系列操作，这么多事情，最终只是为某个用例创造输入，那么为什么不在单元测试里直接指定输入呢？


##逻辑实现代码只在接口处有静态函数

我看到很多代码是用全局变量和静态函数堆砌起来的，当问起为什么这么做的时候，得到的答复很多：   
* **这样写最直接，最简单**。 我想说大多数时候这个理由是一种敷衍，任何时候，你的代码都应当是模块化的，模块的对外交互部分应该是有限且确定的，而不是模块A可以随便调用模块B的任意函数（除了像StringUtil这样的基础模块），也就是说，除了工具函数或者基础库函数， 对于业务逻辑，静态函数应当多数出现在模块接口级别，模块内部实现则应当尽量以成员对象函数调用的形式。
* **你没用过C吗，C语言从来都是全局变量+函数的形式写代码的**。 还好我就是写C/C++出身的程序员，这样答复的人往往是对C/C++一知半解只懂得用它实现功能的人，我觉得应该去看看Nginx源码或者JNIEnv结构体的实现，任何时候模块内部的代码都是结构化的，不同层次之间调用都是有局部上下文（context)传递的， 其体现形式往往是结构体封装的接口集。更重要的，C的函数指针本身是可赋值替换的，这与java等语言是完全不同的，至少也得是一个Runnable才算得上与之等价。

抛开这些回答不说，由于静态语义是可传递的（一个静态函数只能直接调用另一个静态函数，而不能不依赖对象调用成员函数），且只能访问全局变量或者静态变量， 所以如果一个模块内部代码（非接口）充满静态函数和全局变量，基本上其设计是有问题的。
在适当的层次你应当使用单例或者构建局部上下文主动阻断它，比如一个发起Banner广告请求的API:
~~~
class API{
  public static Banner loadBanner(Request request);
}
~~~
如果你不去阻断它，那么你只能继续调用静态函数，然后你必须把需要的参数全局化。

以单例形式阻断：
~~~
class API{
  public static Banner loadBanner(Request request) {
     return Singleton.of(APIImpl.class).loadBanner(request);
  }
}
~~~

构建局部上下文的方式阻断：
~~~
class API{
  public static Banner loadBanner(Request request) {
     return new BannerJob().loadBanner(request);
  }
}
~~~
##简单才是真的少

一直以来，我们都在说“越少越简单”，最简单的例子是10行代码一定比30行代码更少出错且更容易维护，这个理论用在一个小函数上没有什么问题。但是，当你写的是一个模块，是一个框架的时候，可能就不那么合适了。

100行挤在一起的的函数和300行分成5个60行代码的简单类，你觉得哪个更容易出错，哪个更容易维护呢？ 
100行代码的函数一屏都看不完，且内部错综复杂，你需要关注的真的是个100行的大家伙。
300行的5个类，每个类中的每个函数可能只有5六行，都很简单，过一遍就可以看出是否有问题，你需要关注的就只有这5个类。

回过头来再是100行的大家伙容易出错，还是5个60行的小家伙容易出错呢？
最重要的是这5个小家伙是相互关联，非常容易理解的，而且拆分逻辑之后，更容易测试了，其质量和稳定性更容易保障了。面向对象的特点就是让对象处理自身事务，调用者只需要组织调用顺序等逻辑，使用多个对象拆分之后，你就只需要组织对象的逻辑以及每个对象中的内部逻辑是正确的就可以了。

也许你会说代码量增多，代码层次增多会让代码执行速度变慢，代码体积变大，那么我想说的是，你所担心的都只是你以为的。首先代码层次增多带来的效率损失是可能的，但是近乎可以忽略的，对于体积是否增大，则往往由你程序的优化器决定，大多数时候我们是没有苛刻到去考量这两个因素的，如果要考量，你应当先找一个比较好的优化器（比如java的proguard).

##警惕技术债恶性循环

不得不承认，我们有很多项目没有单元测试，且已经发展的很大了，大到随便修改都有可能出错了，越容易出错，开发人员就需要花费越多的时间在bug修复上，然后又会引起更多的其他的错误，这就是技术债，它成了一个恶心循环的怪圈，笼罩在很多项目团队的头上。

对于这种已经存在的项目直接加单元测试是不适当的。首先代码逻辑并没有被梳理清晰，被测试单元如果没有清晰的边界，测试代码就会陷入无尽Mock的深渊。

我的建议是在开始写单元测试前先重新梳理逻辑，划分单元，由点及面的执行单元测试，当然，你需要从解bug的时间里分出一些来用户重构和完成单元测试，不过这是值得的，因为你的bug会越来越少，你只是把以前修bug的时间用在了重构和单元测试上了。

##不要存在侥幸心理

不写单元测试的程序员有充满自信的，但也有很多是存在侥幸心理的，他们确定自己实现的功能有没有问题的标准就是是否出现明显的错误或者问题，如果没有，则认为自己所开发的功能是没有问题的。

这是最危险的，因为很多问题是因为量或者概率问题不容易被暴露，一旦功能上线，就会引起大批量的用户问题，接下来，紧急修复，再次上线，当然，也少不了用户投诉与流式

要克服这种心理，我想最重要的是培养责任心，要么就是用严厉的制度来规范了。

##让测试自动化

任何工作都会有低效的部分，上文我们讲了如何尽量让单元测试代替人力调试或者测试中的消耗性部分，这里我们要说说消除单元测试中的低效的部分。

单元测试中低效的部分很多，你需要让单元测试更自动化智能化，要学会使用更多更优秀的单元测试框架，持续集成框架，不断审视自己的测试方式和测试代码，以找到性价比最高的测试方法。

我的唯一建议是，如果有一刻你有”写单元测试比我直接调试测试还费劲“的时候，预估下未来你测试该段代码将会花费的时间和精力，如果最终单元测试还是费力的，那么考虑如下三个选择：
- 审视下你的代码结构是否有问题
- 查找是否有解脱的自动化工具
- 抛弃单元测试，直接人工调试测试吧


##让测试变成一种习惯

写单元测试的过程其实是从使用者的角度审视自己代码的过程，让别人容易理解我们的代码，让我们的代码质量更高，更容易维护，写完一个函数，想一下，如果自己的代码真的被别人使用，他们会愿意使用吗，他们会对你的做法有意见吗， 所以如果想成为优秀的程序员，开始以测试代码为镜吧。

单元测试就像重构一样，很多时候是独立且小范围的，你可以花几分钟时间随手就完成了，不一定需要在项目周期中特别的安排时间，如果你可以良好的掌握一些经验和技巧，你单元测试+业务代码的实现时间会比其他人只写业务代码用的要少，而且质量更高（想想吧，他们花费数小时苦逼调试的时候你花几秒钟时间就可以完成了）。

所以，把测试变成一种习惯吧。

#Android自动化测试（2） Gradle+JUnit+Mockito+Robolectric测试环境搭建
>本节中所有测试工程代码都可以在github[这里]()获取。对于程序员来讲，语言总是苍白的，源码才是真理，以下内容可以作为辅助手册，在写单元测试的时候，可以来查阅。

##理论
**Gradle**: Android Studio默认的构建工具集，其缺点是慢，革命性的优点是足够灵活，足够强大，支持Java+Groovy混合编写。
**JUnit**: Java单元测试基础框架，负责数据校验，参数匹配，异常抛出，批量运行，自定义运行规则等。
**Mockito**:  Java Mock框架，用于模拟Java类的行为。
**PowerMock**: Mockito等流行的Mock框架不支持Mock 静态函数，私有函数，构造函数，final函数等特性，所以而PowerMock则可以弥补以上不足。

>乍一看，PowerMock似乎很强大，但PowerMock使用的越多，表示被测试的代码抽象层次越低，代码质量和结构也越差。

**Robolectric**: android.jar的stub库，有了它，你就可以脱离Android os执行单元测试了，这大大加快了单元测试的执行速度，它唯一的问题是不能有效的测试出机型相关以及实际Android环境相关的问题。
>Robolectric目前的最新版本是3.0.其支持的最高Android SDK版本为21.

##实操
这部分主要讲解Gradle单元测试环境的配置以及JUnit，Mockito, PowerMock, Robolectric的使用。

###Gradle构建单元测试环境
通过编写Gradle构建脚本，我们可以方便的配置工程依赖并通过简单的命令进行构建，同时我们也可以使用Android Studio方便的编写的调试代码。如下是单元测试工程的主build.gradle:
~~~
apply plugin: 'com.android.application'

ext.versions = [
        // Unit test
        junit      : '4+',
        robolectric: '3.0',
        powermock  : '1.6.2',
        mockito    : '1.10.19',
]

android {
    compileSdkVersion 21
    buildToolsVersion "21.0.1"

    compileOptions {
        sourceCompatibility 1.7
        targetCompatibility 1.7
    }
    defaultConfig {
        applicationId "owo.com.androidunittest"
        minSdkVersion 15
        targetSdkVersion 21
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }


    dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        compile 'com.android.support:appcompat-v7:21.0.+'
        // Unit test
        testCompile 'junit:junit:${versions.junit}'
        testCompile "org.robolectric:robolectric:${versions.robolectric}"
        //testCompile "org.robolectric:robolectric:3.1-SNAPSHOT"
        testCompile "org.powermock:powermock-module-junit4:${versions.powermock}"
        testCompile "org.powermock:powermock-module-junit4-rule:${versions.powermock}"
        testCompile "org.powermock:powermock-api-mockito:${versions.powermock}"
        testCompile "org.powermock:powermock-classloading-xstream:${versions.powermock}"
        testCompile "org.mockito:mockito-all:${versions.mockito}"

        testCompile group: 'org.hamcrest', name: 'hamcrest-core', version: '1.3'
        testCompile group: 'org.hamcrest', name: 'hamcrest-library', version: '1.3'
    }
}

~~~

以上主要配置单元测试所有依赖的库及其版本。由于Robolectric3.0目前最高只支持SDK 21，这里也是使用的21作为测试工程。

###使用JUnit
####测试数组相等
~~~
  @Test
  public void testAssertArrayEquals() {
    byte[] expected = "trial".getBytes();
    byte[] actual = "trial".getBytes();
    Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
  }
~~~
####测试相等
~~~
  @Test
  public void testAssertEquals() {
    Assert.assertEquals("failure - strings are not equal", "text", "text");
  }
~~~
####测试条件（True/False)
~~~
  @Test
  public void testAssertFalse() {
    Assert.assertFalse("failure - should be false", false);
  }
  @Test
  public void testAssertTrue() {
    Assert.assertTrue("failure - should be true", true);
  }
~~~
####测试空值（Null/Not Null)
~~~
  @Test
  public void testAssertNotNull() {
    Assert.assertNotNull("should not be null", new Object());
  }
  @Test
  public void testAssertNull() {
    Assert.assertNull("should be null", null);
  }
~~~
####测试相同
~~~
  @Test
  public void testAssertNotSame() {
    Assert.assertNotSame("should not be same Object", new Object(), new Object());
  }
  @Test
  public void testAssertSame() {
    Integer aNumber = Integer.valueOf(768);
    Assert.assertSame("should be same", aNumber, aNumber);
  }
~~~
####测试条件匹配（Matcher)
~~~
  // JUnit Matchers assertThat
  @Test
  public void testAssertThatBothContainsString() {
    assertThat("albumen", allOf(containsString("a"), containsString("b")));
  }
  @Test
  public void testAssertThatHasItems() {
    assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
  }
  @Test
  public void testAssertThatEveryItemContainsString() {
    assertThat(Arrays.asList(new String[] {"fun", "ban", "net"}), everyItem(containsString("n")));
  }
  // Core Hamcrest Matchers with assertThat
  @Test
  public void testAssertThatHamcrestCoreMatchers() {
    assertThat("good", allOf(equalTo("good"), startsWith("good")));
    assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
    assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
    assertThat(7, not(CombinableMatcher.<Integer>either(equalTo(3)).or(equalTo(4))));
    assertThat(new Object(), not(sameInstance(new Object())));
  }
~~~
####测试异常
~~~
  @Test(expected= IndexOutOfBoundsException.class)
  public void empty() { new ArrayList<Object>().get(0); }
~~~
####测试忽略
~~~
  @Ignore("Test is ignored as a demonstration")
  @Test
  public void testSame() {assertThat(1, is(1));}
~~~
####测试超时
~~~
  @Test(timeout=1000)
  public void testWithTimeout() {
//    while(true){
//
//    }
  }
~~~
####测试Assume(满足条件才会执行测试）
~~~
  @Test
  public void filenameIncludesUsername() throws Exception {
    boolean flag = false;
    assumeTrue(flag);
    throw new Exception("flag is true!");
  }
~~~
####测试Rule
~~~
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  @Test
  public void countsAssets() throws IOException {
    File icon = tempFolder.newFile("icon.png");
    Assert.assertTrue(icon.exists());
  }
~~~
####自定义Rule
Rule的作用是你不必在每一个类的测试代码中写@Before, @After而仅仅需要定义一个对象。在代码中，你可以自定义Rule，请参考github代码中的IgnoreLogRule例子,用于在单元测试的时候忽略所有的日志相关的函数。


###Mockito
测试Mock判断
~~~
  @Test
  public void testJudgeMock() {
    A a = mock(A.class);
    A b = spy(new A());
    Assert.assertTrue(Mockito.mockingDetails(a).isMock());
    Assert.assertFalse(Mockito.mockingDetails(a).isSpy());
    Assert.assertTrue(Mockito.mockingDetails(b).isMock());
    Assert.assertTrue(Mockito.mockingDetails(b).isSpy());
  }
~~~
####测试对象Mock
~~~
  @Test
  public void testMock() {
    A a = mock(A.class);
    Mockito.when(a.publicMethod()).thenReturn("mockA");
    Assert.assertEquals("mockA", a.publicMethod());
    Mockito.verify(a, times(1)).publicMethod();
    Mockito.verify(a, never()).foo();
  }
~~~
####测试对象部分Mock(Spy)
~~~
  @Test
  public void testSpy() {
    A a = new A();
    a = Mockito.spy(a);
    Mockito.when(a.publicMethod()).thenReturn("mockA");
    Assert.assertEquals("mockA", a.publicMethod()); // mocked
    Assert.assertEquals("publicMethod2", a.publicMethod2()); // not mocked
    Mockito.verify(a, times(1)).publicMethod();
  }
~~~
####测试异常抛出
~~~
  @Test(expected = Exception.class)
  public void testThrowException() {
    A a = mock(A.class);
    Mockito.doThrow(new Exception("exception")).when(a).foo();
    Mockito.verify(a).foo();
  }
~~~
####测试按序执行
~~~
  @Test
  public void testInOrder() {
    {
      List singleMock = mock(List.class);
      singleMock.add("was added first");
      singleMock.add("was added second");
      InOrder inOrder = Mockito.inOrder(singleMock);
      inOrder.verify(singleMock).add("was added first");
      inOrder.verify(singleMock).add("was added second");
    }
    {
      List firstMock = mock(List.class);
      List secondMock = mock(List.class);
      firstMock.add("was called first");
      secondMock.add("was called second");
      InOrder inOrder = Mockito.inOrder(firstMock, secondMock);
      inOrder.verify(firstMock).add("was called first");
      inOrder.verify(secondMock).add("was called second");
    }
  }
~~~
####测试自定义行为（Answer)
~~~
  @Test
  public void testAnswer() {
    A a = mock(A.class);
    Mockito.doAnswer(new Answer<Void>() {
      public Void answer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        Object mock = invocation.getMock();
        return null;
      }

    }).when(a).foo();
    a.foo();
    Mockito.verify(a).foo();
  }
~~~
####测试参数捕捉
~~~
  @Test
  public void testArgumentCaptor() {
    A a = mock(A.class);
    ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
    a.foo("abc");
    Mockito.verify(a).foo(argument.capture());
    Assert.assertEquals("abc", argument.getValue());
  }
~~~

###PowerMock
Power Mock主要测试静态函数调用，私有函数调用和构造函数调用。

PowerMock需要配置一下几点：
1. 配置PowerMockIgnore，自动化Mock的原理为替换类加载器，所以为了保证某些类不被替换，你需要显示声明。
2. @PrepareForTest声明PowerMock所要测试的目标类。
3. 定义PowerMockRule.

~~~
@PowerMockIgnore({
  "org.mockito.*", "org.robolectric.*", "org.json.*", "owo.com.androidunittest.targets.A"
})
@PrepareForTest({A.class})
public class PowerMockTest {
  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Test
  public void testStaticMethod() {
    PowerMockito.mockStatic(A.class);
    PowerMockito.when(A.publicStaticMethod()).thenReturn("abc");
    Assert.assertEquals("abc", A.publicStaticMethod());

    PowerMockito.verifyStatic();
    A.publicStaticMethod();
  }

  @Test
  public void testPrivateMethod() throws Exception {
    PowerMockito.spy(A.class);
    PowerMockito.when(A.class, "privateStaticMethod").thenReturn("abc");

    String real = A.callPrivateStaticMethod();
    Assert.assertEquals("callabc", real);

    PowerMockito.verifyPrivate(A.class).invoke("privateStaticMethod");
  }

  @Test
  public void testConstructor() throws Exception {
    A a = new A();
    PowerMockito.whenNew(A.class).withNoArguments().thenReturn(a);

    A a2 = A.create();
    Assert.assertEquals(a, a2);

    PowerMockito.verifyNew(A.class).withNoArguments();
  }
}
~~~

###Robolectric
如果你的单元测试代码用到了Android类，则需要使用Robolectric类为android.jar打桩，这样你的单元测试代码就可以脱离android系统运行了。使用Robolectric需要注意两点：
1. 使用@RunWith标识你所使用的Runner类。
2. 使用@Config配置Android相关的配置信息，注意其中的resourceDir和assetDir是相对manifest父目录的。
~~~
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/AndroidManifest.xml", sdk = 21,//
  constants = BuildConfig.class, resourceDir = "../test/res", assetDir = "assets")
public class RobolectricTest {
  @Test
  public void testIsTextEmpty() {
    Assert.assertTrue(AndroidTarget.isTextEmpty(""));
    Assert.assertFalse(AndroidTarget.isTextEmpty("abc"));
  }
  @Test
  public void testCreateView() {
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    View v = AndroidTarget.makeView(activity);
    Assert.assertNotNull(v);
  }
}
~~~






