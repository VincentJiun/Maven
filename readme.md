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