# Maven 

## 參考資料
- https://www.youtube.com/playlist?list=PLmOn9nNkQxJE-ga0zAQKTi5IHuGEtK1es

## Maven介紹
- Maven 是 Apache 專門為 Java項目提供構建與依賴管體支援的工具

### 1. 建構
- 原材料
    - java 原代碼
    - 基於 HTML 的 Thymeleaf
    - Images
    - 配置文件
    - ......

- 產品
    - 可以在Server 上運行的項目

構建過程包含的主要環節:
    - 清理:刪除上一次建構的結果，為下一次構建做準備
    - 編譯:Jave源程序編譯成 *.class 字節碼文件
    - 測試:運行提前準備好的測試程序
    - 報告:針對測試的結果生成一個全面的信息
    - 打包
        - Java工程: jar
        - Web工程: war
    - 安裝:把一個Maven工程經過打包操作生成的jar包或 war包存入Maven 倉庫
    - 部屬
        - 部屬jar:把一個jar部屬到 Nexus 私服Server上
        - 部屬war:借助相關Maven插件 (例如 cargo),將war包部屬到Tomcat 伺服器上

### 2. 依賴
如果A工程裡用到B工程的類、接口、配置文件等資源-> A依賴B, 例如:
- 依賴管理中要解決的具體問題:
    - jar包的下載:使用Maven後，jar包回從規範的遠程倉庫下載到本地
    - jar包之間的依賴: 通過依賴的傳遞性自動完成
    - jar包之間的衝突:通過對依賴的配置進行調整，讓某些jar包不會被導入

### 3. Maven的工作機制
- image:https://www.youtube.com/watch?v=Ttzrp0MmWzo&list=PLmOn9nNkQxJE-ga0zAQKTi5IHuGEtK1es&index=6

## Maven 核心程序解壓與配置
- Maven 官網地址:https://maven.apache.org/
- 檔案下載地址: https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip

### 解壓縮Maven核心程序
- 解壓目錄必須非中文、沒有空格的目錄
- 在解壓目錄中，需要關注 Marven的核心配置文件:conf/settings.xml

#### 1. 指定本地倉庫
- 預設在 用戶目錄下的 ~{user.Home}/.m2/repository
- conf/settings.xml:
```
    <localRepository>d:\mvnrepo</localRepository>
```
#### 2. 配置遠程鏡像倉庫
Maven 下載jar包默認訪問境外的中央倉庫，而國外網站速度很慢，改成速度較快遠程鏡像倉庫，可以讓Maven下載jar包時速度更快，配置的方式為:

1. 將原有的範例配置注釋掉:
```
<mirror>
      <id>maven-default-http-blocker</id>
      <mirrorOf>external:http:*</mirrorOf>
      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
      <url>http://0.0.0.0/</url>
      <blocked>true</blocked>
</mirror>
```
2. 加入自己的配置
- 必須放置在 mirrors 標籤內部

#### 3. 配置 Marven 工程的基礎JDK 版本
如果按照默認配置運行，Jave工程使用的默認JDK版本是1.5，若要修改，到setting.xml 文件的profiles 標籤內修改。
```

```
### 配置還經變量
#### 1. 檢查JAVA_HOME配置是否正確
- 可用 cmd 驗證

#### 2. 配置 MAVEN_HOME
1. win + R -> sysdm.cpl
2. 進階 -> 環境變數 -> 系統變數 -> 新增
3. 變數名稱:MAVEN_HOME , 變數值:D:\software\apache-maven-3.9.6

#### 配置 path 變量
1. 進階 -> 環境變數 -> 系統變數 -> path
2. 新增 -> %MAVEN_HOME%\bin

#### 驗證
- cmd: mvn -v (若有出現版本號，就代表配置成功)

## Maven 入門、原理與實戰

### 根據座標創建 Maven工程
#### 1. 向量說明
- groupId:公司或組織的ID
- artifactId:一個項目或者是項目中一個模塊的ID
- version:版本號
- 舉例:
```
    <groupId>com.cosen</groupId>
    <artifactId>mechalogix</artifactId>
    <version>1.0.0</version>
```
- 上方座標所對應的jar包在Maven本地倉庫的位置:
        1. Maven本地倉庫根目錄\javax\...

#### 實驗操作
1. 創建目錄作為後面操作空間
    - ex. D:\web\
- 此時已經有三個目錄:
    - Maven核心程序:中軍大帳
    - Maven本地倉庫:兵營
    - 本地工作空間:戰場

2. 進入操作空間資料夾

3. 使用命令生成Maven工程
```
mvn archetype:generate
```
- Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 2115: 【直接Enter，使用默認值】

4. 調整
- 打開 pom.xml

5. 解讀 pom.xml
```
<!-- Project 根標籤 : 對當前工程進行配置 -->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <!-- ModelVersion 標籤 : 當前 pom.xml 所採用的標籤結構 -->
  <modelVersion>4.0.0</modelVersion>
  
  <!-- 座標訊息 -->
  <!-- groupId 標籤 : 代表公司或組織 (項目) -->
  <groupId>com.cosen</groupId>
  <!-- artifactId 標籤 : 座標向量之一，代表項目下某個模塊 -->
  <artifactId>mechalogix</artifactId>
  <!-- version 標籤 : 座標向量之一，代表當前模塊的版本 -->
  <version>1.0.0</version>
  <!-- packaging 標:打包方式 -->
  <!-- 取值 jar : 生成 jar 檔， 說明這是一個Java 工程 -->
  <!-- 取值 war : 生成 war 檔， 說明這是一個web 工程 -->
  <!-- 取值 pom : 說明這個工程是用來管理其他工程的工程 -->
  <packaging>war</packaging>
  
  <name>mechalogix</name>
  <url>http://maven.apache.org</url>
  
  <!-- 在 Maven 中定義屬性值 -->
  <properties>
        <!-- 構建過程中讀取原碼時使用的字符集 -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
  </properties>

  <!-- dependencies 標籤 : 配置具體依賴信息 -->
  <dependencies>
    <!-- dependency 標籤 : 配置一個具體的依賴信息 -->
    <dependency>
      <!-- 具體依賴的座標信息 -->
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <!-- scope 標籤 : 配置當前依賴的範圍 -->
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>

```

#### Maven 核心改念:POM
1. 含意:
- POM:Project Object Model，項目對象模型。 和DOM(Document Object Model)類似，都是模型化思想具體體現。

2. 模型化思想:
- POM 表示將工程抽象為一個模型，再用程序中的對象來描述模型。這樣我們就可以用程序來管理項目。在開發的過程中，最基本的作法就是將現實生活中的事物抽象為模型，然後封裝模型相關的數據做為一個對象，這樣就可以在程序中計算與實現事物相關的數據。

3. 對應的配置文件
- POM 理念集中體現在Maven工程跟目錄下 pom.xml這個配置文件中。所以這個 pom.xml配置文件就是 Maven 工程的核心配置文件。其實學習Maven就是學這個文件怎麼配置各個配置有甚麼用。

#### Maven核心概念: 約定的目錄結構
1. 各個目錄的作用

2. 約定目錄結構的意義
- Maven 為了讓構建過程能夠盡可能自動化完成，所以必須約定目錄結構的作用。例如: Maven 執行編譯操作，必須先去jave原程序目錄讀取java原代碼，然後執行編譯，最後把編譯結果存放在target 目錄。

3. 約定大於配置
- Marven對於目錄結構這個問題，沒有採用配置的方式，而是基於約定。這樣會讓我們開發過程中非常方便， 如果每次創建Maven工程後，還需要針對各自目錄位置進行詳細的配置，肯定非常麻煩。
- 目前開發領域的技術發展趨勢就是: 約定大於配置，配置大於原碼。

### 在 Maven 工程中編寫代碼
#### 1. 主體程序
- 主體程序指的是被測試的程序，同時也是將來在項目中真正要使用的程序。
```
package com.maventest;

public class Calculator {

    public int sum(int i, int j){
        return i+j;
    }
}
```

#### 2. 測試程序
```
package com.cosen.mechalogix.core;

import org.junit.Test;
import com.cosen.mechalogix.core;

// 靜態導入的效果是將 Accert類中的靜態資源導入當前類
// 這樣一來，在當前類中就可以直接使用Assert類中的靜態資源，不須要寫類名
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testSum(){
        // 1. 創建calculator對象
        Calculator calculator = new Calculator();

        // 2. 調用Calcultor對象的方式，獲取道程序運行實際的結果
        int  actualResult = calculator.sum(5, 2);

        // 3. 聲明一個變量，表示程序運行期待的結果
        int expectedResult = 8;

        // 4. 使用斷言來判斷實際結果和期待結果是否一致
        // 如果一致:測試通過， 不會拋出異常
        // 如果不一致: 拋出異常，測試失敗
        assertEquals(expectedResult, actualResult);
    }
}
```

### 執行Maven的構建命令
#### 1. 要求
- 運行Maven中和構建操作相關的命令時，必須進入到 pom.xml所在的目錄。如果沒有在 pom.xml所在的目錄運行 Maven的建構命令，會看到以下錯誤訊息:
```
The goal you specified requires a project to execute but there is no POM in this directory 
```
- mvn -v 命令和建構操作無關。只要配置正確的PATH，可以在任何目錄下執行。而建構相關的命令要在 pom.xml所在的目錄下運行-->要操作哪個工程，就進入該工程的 pom.xml 目錄。

#### 2. 清理操作
```
mvn clean
```
-  效果: 刪除 target目錄

#### 3. 編譯操作
- 主程序編譯: mvn compile
- 測試程序編譯: mvn test-compile
- 主體程序編譯結果存放目錄: target/classes
- 測試程序編譯結果存放目錄: target/test-classes

#### 4. 測試操作
```
mvn test
```
- 測試的報告存放的目錄: target/surefilre-reports

#### 5. 打包操作
```
mvn package
```
- 打包結果 : 應用 -> jar, Web -> war
- 存放目錄: target

#### 6. 安裝操作
- 安裝指令:
    ```
    mvn install
    ```
- 結果
    ```
    [INFO] Installing d:\Maven\core\pom.xml to d:\mvnrepo\com\maventest\core\1.0\core-1.0.pom
    [INFO] Installing d:\Maven\core\target\core-1.0.jar to d:\mvnrepo\com\maventest\core\1.0\core-1.0.jar
    ```
- 安裝的效果是將本地建構過程中生成的jar包存入Maven本地倉庫。這個jar包在Maven倉庫中的路徑是根據他的座標生成的。
- 座標信息:
    ```
    <groupId>com.maventest</groupId>
    <artifactId>core</artifactId>
    <version>1.0</version>
    ```
- 在Maven倉庫中生成的路徑:
    ```
    {倉庫路徑}\com\maventest\{artifactId}\{version}\{jar包}
    ```
- 另外，安裝操作還會將 pom.xml文件轉換為 xxx.pom文件一起存入本地倉庫。所以我們在Maven的本地倉庫中想看一個jar包原始的pom.xml文件時，查看對應的xxx.pom文件即可，他們是名字改變，本質是同一個文件。

### 建構Maven版的web工程
#### 1. 說明
- 使用 mvn archetype:generate 命令生成web工程時，需使用一個專門的archetype。這個專門生成web工程骨架的archetype可以參照官網看到他的用法:
- 參考網址:https://maven.apache.org/archetypes/maven-archetype-webapp/
```
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4
```
#### 2. 操作
- 注意:執行命令時，必須在工作空間目錄操作。
- 後續操作按照提示執行:

#### 3. 生成 pom.xml
- 確認打包方式為 war
```
<packaging>war</packaging>
```

#### 4. 生成的web工程的目錄結構
- webapp目錄下有 index.jsp
- WEB-INF目錄下有 web.xml

#### 5. 創建 Servlet
1. 在main目錄下創建java目錄
2. 在java目錄下創建Servlet類所在的包目錄
3. 在包下創建 Servlet類
```
package com.cosen.maven;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jaxa.io.IOException;


public class Helloservlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException {
        response.getWriter().write("Hello Maven Web")
    }
    
}
```
4. 在web.xml中註冊 Servlet
```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <servlet>
    <servlet-name>helloservlet</servlet-name>
    <servlet-class>com.cosen.maven.Helloservlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>helloservlet</servlet-name>
    <url-pattern>/helloservlet</url-pattern>
  </servlet-mapping>
</web-app>
```

#### 6. 在index.jsp頁面編寫超連結
```
<html>
<body>
<h2>Hello World!</h2>
<a href="helloServlet">Access Servlet</a>
</body>
</html>
```
-tip: jsp全稱 Jave Server Page, 和Thymeleaf一樣，是伺服器端頁面渲染技術，這裡不必關心 jsp語法細節，編寫一個超連結標籤即可。

#### 7. 編譯
此時直接執行 mvn compile

#### 8. 配置對 servlet-api.jar 包的依賴
- https://mvnrepository.com/

#### 9. 將 Web 工程打包為 war包
- 運行 mvn package 命令，生成war包的位置:
    -> /target/{project}.war

#### 10. 將war包部屬到 Tomcat 上運行
1. 將 war包複製到 Tomcat/webapp 目錄下
2. 啟動 Tomcat
    - cmd
    ```
    cd Tomcat/bin
    startup.bat
    ```

### 讓Web工程依賴Java工程
#### 1. 觀念
- 從來只有web工程依賴jave工程，沒有反過來java工程依賴Web工程，本質上來說，Web工程依賴的java工程其實就是Web工程裡導入的jar包，最終java工程會變成jar包，放在Web工程的WEB-INF/lib目錄下。

#### 2. 操作
在 maven-web 工程的 pom.xml中，在 dependencies標籤中配置:

#### 3. 在Web工程中，編寫測試代碼
1. 補充創建目錄
    - \scr\test\java\com\cosen\maven

2. 確認web工程依賴 junit
```
<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
</dependency>
```

3. 創建測試類
將java工程的 CalculatorTest.java 類複製到 web 工程下\scr\test\java\com\cosen\maven 資料夾內

#### 4. 執行 Maven 命令
1. 測試命令
```
mvn test
```
- 說明:測試操作中會提前自動執行編譯操作，測試成功就說明編譯也是成功的。

2. 打包命令
```
mvn package
```
- 通過查看 war包內的結構，我們看到被web工程依賴的java工程卻是會變成web工程的 WEBINF/lib 目錄下的jar 包

3. 查看當前web 工程所依賴的jar包列表
列表顯示:
```
mvn dependency:list
```
執行命令後:
```
[INFO] The following files have been resolved:
[INFO]    junit:junit:jar:4.11:test -- module junit (auto)
[INFO]    org.hamcrest:hamcrest-core:jar:1.3:test -- module hamcrest.core (auto)
[INFO]    javax.servlet:javax.servlet-api:jar:3.1.0:provided -- module javax.servlet.api (auto)
[INFO]    com.maventest:core:jar:1.0:compile -- module core (auto)
```
樹狀圖顯示:
```
mvn dependency:tree
```