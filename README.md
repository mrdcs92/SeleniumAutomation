<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h1>Selenium Automation Framework</h1>
      <h2 margin-top: 0px>Java • Selenium • TestNG • Cucumber • Maven • Jenkins • MySQL </h2>
    </summary>
  </ul>
</div>


<p>A scalable web automation framework demonstrating modern QA automation practices including Page Object Model architecture, data-driven testing, BDD, database validation, automated reporting, cross-browser execution, and CI/CD integration.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📌 Project Overview </h2>
    </summary>
  </ul>
</div>

<p>The framework was designed to demonstrate real-world Software Development Engineer in Test (SDET) practices by separating test logic from page interactions, supporting multiple test suites, external test data, browser configuration, reporting, database validation, BDD scenarios, and continuous integration.</p>

<p>The automated scenarios exercise an e-commerce workflow and include:</p>

<ul>
  <li>User authentication</li>
  <li>Product selection</li>
  <li>Shopping cart validation</li>
  <li>Checkout and order submission</li>
  <li>Order confirmation</li>
  <li>Negative/error validation</li>
  <li>Data-driven testing</li>
  <li>Database-backed validation</li>
  <li>BDD scenarios with Cucumber</li>
  <li>Automated HTML reporting</li>
  <li>Screenshot capture on test failure</li>
  <li>Retry handling</li>
  <li>Cross-browser execution</li>
  <li>Jenkins CI execution</li>
</ul>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🛠 Technology Stack </h2>
    </summary>
  </ul>
</div>

| Technology | Purpose |
| :--- | :--- |
| Java 17 | Primary programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution, assertions, suites, listeners, and data providers |
| Maven | Dependency management, build lifecycle, profiles, and test execution |
| Cucumber | Behavior-Driven Development (BDD) |
| Extent Reports | HTML test execution reporting |
| Jackson | JSON test-data deserialization |
| JDBC / MySQL | Database integration and validation |
| Jenkins | Continuous Integration pipeline execution |
| Git / GitHub | Source control and CI webhook integration |
| Ngrok | Receive and test incoming webhook callbacks |

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🏗 Framework Architecture </h2>
    </summary>
  </ul>
</div>

<p>The project follows the <b>Page Object Model (POM)</b> design pattern to separate application-specific UI interactions from test logic.</p>

```text
SeleniumAutomation
|
│   Jenkinsfile
│   pom.xml
│
├───reports
│       index.html
│
├───resources
│       GlobalData.properties
│       PurchaseOrder.json
│
├───src
│   ├───main
│   │   └───java
│   │       ├───components
│   │       │       AbstractComponent.java
│   │       │
│   │       ├───data
│   │       │       DataReader.java
│   │       │
│   │       └───pageobjects
│   │               CartPage.java
│   │               CheckoutPage.java
│   │               ConfirmationPage.java
│   │               LandingPage.java
│   │               OrderPage.java
│   │               ProductCatalogue.java
│   │
│   └───test
│       └───java
│           ├───components
│           │       BaseTest.java
│           │       DatabaseUtils.java
│           │       ExtentReporterNG.java
│           │       ExtentTestManager.java
│           │       Listeners.java
│           │       Retry.java
│           │
│           ├───cucumber
│           │       StepDefinitions.java
│           │       TestNGTestRunner.java
│           │
│           ├───data
│           │       DatabaseDataProvider.java
│           │
│           ├───resources
│           │       ErrorValidations.feature
│           │       PurchaseOrder.feature
│           │
│           └───tests
│                   DbOrderTest.java
│                   ErrorValidation.java
│                   SubmitOrderTest.java
│
└───testSuites
        CucumberTests.xml
        DatabaseTests.xml
        ErrorValidation.xml
        Purchase.xml
        testng.xml
```

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🧩 Framework Design </h2>
    </summary>
  </ul>
</div>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h3>Page Object Model </h3>
    </summary>
  </ul>
</div>

<p>UI elements and application interactions are encapsulated within dedicated page classes.</p>

```text
Test
  │
  ▼
LandingPage
  │
  ▼
ProductCatalogue
  │
  ▼
CartPage
  │
  ▼
CheckoutPage
  │
  ▼
ConfirmationPage
```

<p>This keeps test cases focused on business behavior rather than low-level Selenium operations.</p>
<p>The design improves:</p>

<ul>
  <li>Code readability</li>
  <li>Reusability</li>
  <li>Maintainability</li>
  <li>Separation of concerns</li>
  <li>Scalability as test coverage increases</li>
</ul>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>♻️ Reusable Selenium Components </h2>
    </summary>
  </ul>
</div>

<p><i>AbstractComponent.java</i> provides shared functionality used by page objects.</p>
<p>Common browser interactions are centralized instead of being duplicated across individual pages.</p>
<p>This design allows page objects to inherit reusable functionality while remaining focused on page-specific behavior.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🧪 TestNG Test Architecture </h2>
    </summary>
  </ul>
</div>

<p>TestNG serves as the primary test execution framework.</p>
<p>The project uses TestNG capabilities including:</p>

<ul>
  <li>Test suites</li>
  <li>Assertions</li>
  <li>Data providers</li>
  <li>Test listeners</li>
  <li>Retry handling</li>
  <li>Suite-based execution</li>
  <li>Maven-integrated execution</li>
  <li>Test suites</li>
</ul>

<p>The test suite is divided into specialized execution groups so different categories of tests can be run independently.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📦 Maven Profiles & Test Suites </h2>
    </summary>
  </ul>
</div>

<p>Maven profiles provide a convenient way to select a specific TestNG suite from the command line or CI pipeline.</p>

| Maven Profile | TestNG Suite | Purpose |
| :--- | :--- | :--- |
| _Regression_ | _testng.xml_ | Main regression suite |
| _Purchase_ | _Purchase.xml_ | Purchase/order workflow |
| _ErrorValidation_ | _ErrorValidation.xml_ | Negative validation scenarios |
| _Cucumber_ | _CucumberTests.xml_ | BDD/Cucumber scenarios |
| _DbTest_ | _DatabaseTests.xml_ | Database-backed tests |

### Run the Regression Suite
_mvn clean test -PRegression_
### Run Purchase Tests
_mvn clean test -PPurchase_
### Run Error Validation Tests
_mvn clean test -PErrorValidation_
### Run Cucumber Tests
_mvn clean test -PCucumber_
### Run Database Tests
_mvn clean test -PDbTest_

### If no profile is supplied, the framework defaults to:

_testSuites/testng.xml_

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🛒 End-to-End Purchase Testing </h2>
    </summary>
  </ul>
</div>

<p><i>SubmitOrderTest.java</i> exercises an end-to-end e-commerce workflow using the framework's Page Objects.</p>
<p>A typical automated flow follows:</p>

```text
Launch Browser
 ↓
Login
 ↓
Product Catalogue
 ↓
Select Product
 ↓
Shopping Cart
 ↓
Checkout
 ↓
Submit Order
 ↓
Confirmation
 ↓
Validation
```

<p>The test layer coordinates the workflow while the individual Page Objects handle browser interactions.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>❌ Negative Testing </h2>
    </summary>
  </ul>
</div>

<p><i>ErrorValidation.java</i> provides dedicated negative test coverage.</p>
<p>Separating positive purchase scenarios from error-validation scenarios allows the framework to execute targeted test suites and makes failures easier to diagnose.</p>
<p>Negative scenarios can be executed independently using:</p>

_mvn clean test -PErrorValidation_

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📊 Data-Driven Testing </h2>
    </summary>
  </ul>
</div>

<p>The framework supports external test data to reduce hard-coded values within test classes.</p>

### JSON Test Data

<p>Purchase data is stored in:</p>
<p><i>resources/PurchaseOrder.json</i></p>
<p><i>DataReader.java</i> uses Jackson to deserialize JSON test data for use during automated test execution.</p>
<p>This architecture separates:</p>

```text
Test Logic
 │
 ▼
Data Reader
 │
 ▼
External JSON Test Data
```
<p>This makes adding additional test-data combinations easier without duplicating test logic.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🗄 Database Testing </h2>
    </summary>
  </ul>
</div>

<p>The framework also demonstrates database-backed automated testing using JDBC and MySQL.</p>
<p>Database-related components include:</p>

<ul>
  <li><i>DatabaseUtils.java</i></li>
  <li><i>DatabaseDataProvider.java</i></li>
  <li><i>DbOrderTest.java</i></li>
  <li><i>DatabaseTests.xml</i></li>
</ul>

<p>This demonstrates the ability to combine UI automation with backend data validation and database-sourced test data.</p>
<p>The dedicated database suite can be executed with:</p>

_mvn clean test -PDbTest_

### Database Environment Requirement

<p>Database tests require access to the expected MySQL database and are intentionally separated from the standard regression workflow.</p>
<p>A developer cloning this repository will need to configure the required local database/environment before executing the DbTest profile.</p>
<p>This keeps environment-dependent database tests isolated from the portable UI regression suite.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🥒 BDD with Cucumber </h2>
    </summary>
  </ul>
</div>

<p>The framework includes a separate Cucumber BDD implementation alongside the standard TestNG tests.</p>
<p>Components include:</p>

<ul>
  <li><i>StepDefinitions.java</i></li>
  <li><i>TestNGTestRunner.java</i></li>
  <li><i>CucumberTests.xml</i></li>
</ul>

<p>Cucumber scenarios translate business-readable Gherkin steps into reusable Java automation code.</p>
<p>The BDD suite can be executed with:</p>

_mvn clean test -PCucumber_

<p>This demonstrates experience working with both traditional automated test architecture and Behavior-Driven Development.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🌐 Cross-Browser Execution </h2>
    </summary>
  </ul>
</div>

<p>Browser initialization is centralized through the framework's test setup rather than being duplicated within individual test cases.</p>
<p><b>WebDriverManager</b> is included to manage browser driver dependencies automatically.</p>
<p>The framework is designed to support browser selection through runtime configuration, allowing the same test suite to be executed against multiple supported browsers without changing the test code.</p>
<p>This is particularly useful for CI execution where browser configuration can be supplied as a build parameter.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>⚡ Parallel-Safe Driver Management </h2>
    </summary>
  </ul>
</div>

<p>The framework uses centralized WebDriver management to prevent test classes from directly owning browser lifecycle logic.</p>
<p>This architecture provides a foundation for safe parallel execution by isolating driver instances when multiple tests execute concurrently.</p>
<p>It also keeps browser initialization and cleanup responsibilities out of individual tests.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📈 Extent Reports </h2>
    </summary>
  </ul>
</div>

<p>Test execution results are captured using Extent Reports.</p>
<p>Reporting infrastructure includes:</p>

<ul>
  <li><i>ExtentReporterNG.java</i></li>
  <li><i>ExtentTestManager.java</i></li>
  <li><i>Listeners.java</i></li>
</ul>

<p>The framework generates HTML reports that provide a readable summary of test execution.</p>
<p>Reports can contain:</p>

<ul>
  <li>Test name</li>
  <li>Pass/fail status</li>
  <li>Execution details</li>
  <li>Failure information</li>
  <li>Screenshots associated with failed tests</li>
</ul>

<p>Generated reports are stored in the project's reporting directory and can also be published as Jenkins build artifacts/reports.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📸 Automatic Failure Screenshots </h2>
    </summary>
  </ul>
</div>

<p>When an automated UI test fails, the framework can capture a browser screenshot and associate the failure evidence with the test report.</p>
<p>This provides immediate visual context when diagnosing failures from local or CI executions.</p>
<p>The reporting flow is:</p>

```text
Test Execution
 ↓
TestNG Listener
 ↓
Failure Detected
 ↓
Capture Screenshot
 ↓
Attach to Extent Report
```
<p>This reduces the amount of manual investigation required when a regression test fails.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🔁 Automatic Retry Handling </h2>
    </summary>
  </ul>
</div>

<p><i>Retry.java</i> provides retry functionality for failed tests.</p>
<p>Retry handling demonstrates how the framework can account for temporary automation instability while still recording final execution results.</p>
<p>The retry implementation is kept separate from individual tests so retry behavior can be managed at the framework level rather than duplicated throughout the test suite.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>⚙️ Configuration Management </h2>
    </summary>
  </ul>
</div>

<p>Runtime configuration is separated from test logic through:</p>

_resources/GlobalData.properties_

<p>This provides a centralized location for environment and browser-related configuration.</p>
<p>Separating configuration from test code makes the framework easier to execute in different environments and from CI systems.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🧹 Automated Report Cleanup </h2>
    </summary>
  </ul>
</div>

<p>The Maven Clean Plugin is configured to clean the generated reports directory when executing the Maven clean lifecycle.</p>
<p>Therefore:</p>

_mvn clean test_

<p>removes reports from the previous execution before generating results for the new test run.</p>
<p>This prevents stale screenshots and reports from being confused with results from the current build.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🔄 Continuous Integration with Jenkins </h2>
    </summary>
  </ul>
</div>

<p>The project is designed to integrate with Jenkins Pipeline for automated test execution.</p>
<p>The CI workflow supports parameterized execution so Jenkins can control which Maven test suite and browser configuration should be used for a build.</p>
<p>Conceptually, the pipeline performs:</p>

```text
GitHub Push
 │
 ▼
GitHub Webhook
 │
 ▼
Jenkins
 │
 ▼
Checkout Source
 │
 ▼
Maven Test Execution
 │
 ▼
Selected TestNG Suite
 │
 ▼
Extent Report
 │
 ▼
Screenshots / Build Artifacts
```

### GitHub Webhook Integration

<p>The Jenkins environment can be triggered automatically from GitHub through a webhook.</p>
<p>For local Jenkins development, <b>ngrok</b> can expose the locally hosted Jenkins webhook endpoint so GitHub can deliver push events to the Jenkins server.</p>
<p>This creates an automated CI workflow where source-control changes can trigger regression execution without manually starting a Jenkins build.</p>

### Parameterized Builds

<p>The pipeline can expose parameters for selecting execution configuration, allowing the same CI job to run different Maven profiles instead of maintaining separate Jenkins jobs for every suite.</p>
<p>For example:</p>

<ul>
  <li><i>Regression</i></li>
  <li><i>Purchase</i></li>
  <li><i>ErrorValidation</i></li>
  <li><i>Cucumber</i></li>
  <li><i>DbTest</i></li>
</ul>

<p>The selected Jenkins parameter can then be passed to Maven:</p>

_mvn clean test -P<PROFILE>_

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>📄 Jenkins Report Publishing </h2>
    </summary>
  </ul>
</div>

<p>Extent HTML reports generated during CI execution can be published through Jenkins using the <b>HTML Publisher Plugin</b>.</p>
<p>This allows test results to be reviewed directly from an individual Jenkins build.</p>
<p>Screenshots generated during failed tests can be retained alongside the HTML report, providing CI-accessible failure evidence.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🔧 Maven Build Management </h2>
    </summary>
  </ul>
</div>

<p>The project uses Maven for:</p>

<ul>
  <li>Dependency management</li>
  <li>Test execution</li>
  <li>Build lifecycle management</li>
  <li>Test-suite selection</li>
  <li>Report-directory cleanup</li>
  <li>Compiler configuration</li>
  <li>Maven profile management</li>
</ul>

<p>The Maven Surefire Plugin connects Maven test execution to the appropriate TestNG XML suite:</p>

```text
Maven Profile
 ↓
suiteXmlFile
 ↓
Maven Surefire
 ↓
TestNG XML
 ↓
Automated Tests
```
<p>This allows the same framework to support multiple execution strategies without changing source code.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🚀 Getting Started </h2>
    </summary>
  </ul>
</div>

<p>Install the following:</p>

<ul>
  <li><i>Java JDK 17+</i></li>
  <li><i>Apache Maven</i></li>
  <li><i>Git</i></li>
  <li><i>Chrome / Edge / Firefox</i></li>
</ul>

<p>Database tests additionally require:</p>

<ul>
  <li><i>MySQL</i></li>
  <li><i>Configured test database</i></li>
  <li><i>Valid JDBC connection configuration</i></li>
</ul>

<p>Jenkins is optional for local execution.</p>

### Clone the Repository

_git clone https://github.com/mrdcs92/SeleniumAutomation.git_

_cd SeleniumAutomation_

### Verify Java
_java -version_

### Verify Maven
_mvn -version_

### Execute the Regression Suite
_mvn clean test -PRegression_

<p>Maven will automatically download the required dependencies.</p>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🧠 Skills Demonstrated </h2>
    </summary>
  </ul>
</div>

<p>This project demonstrates practical experience with:</p>

### Test Automation Engineering

<ul>
  <li>Selenium WebDriver</li>
  <li>Java automation development</li>
  <li>End-to-end browser testing</li>
  <li>Positive and negative test scenarios</li>
  <li>Cross-browser testing</li>
  <li>Data-driven automation</li>
</ul>

### Framework Design

<ul>
  <li>Page Object Model</li>
  <li>Reusable components</li>
  <li>Separation of concerns</li>
  <li>Centralized WebDriver management</li>
  <li>Externalized configuration</li>
  <li>External test data</li>
  <li>Automated failure handling</li>
</ul>

### Testing Frameworks

<ul>
  <li>TestNG</li>
  <li>TestNG XML suites</li>
  <li>DataProviders</li>
  <li>Listeners</li>
  <li>Retry mechanisms</li>
  <li>Cucumber BDD</li>
</ul>

### Backend Validation

<ul>
  <li>JDBC</li>
  <li>MySQL</li>
  <li>Database-driven tests</li>
  <li>UI/backend validation architecture</li>
</ul>

### Reporting

<ul>
  <li>Extent Reports</li>
  <li>Failure screenshots</li>
  <li>TestNG listeners</li>
  <li>Jenkins HTML report publishing</li>
</ul>

### Build & CI

<ul>
  <li>Maven</li>
  <li>Maven profiles</li>
  <li>Maven Surefire</li>
  <li>Jenkins Pipeline</li>
  <li>Parameterized CI builds</li>
  <li>GitHub webhooks</li>
  <li>Automated CI execution</li>
</ul>

### Source Control

<ul>
  <li>Git</li>
  <li>GitHub</li>
  <li>CI-triggered source control workflows</li>
</ul>

<hr>

<div id="toc">
  <ul style="list-style: none">
    <summary>
      <h2>🔮 Potential Future Enhancements </h2>
    </summary>
  </ul>
</div>

<p>The framework is intentionally structured so additional automation capabilities can be incorporated as it evolves.</p>
<p>Potential future enhancements include:</p>

### REST API Automation

<p>Add <b>REST Assured</b> to provide API-level testing alongside the existing UI and database layers.</p>

```text
UI Tests        →  Selenium
API Tests       →  REST Assured
Database Tests  →  JDBC / MySQL
```

<p>This would allow validation across multiple layers of an application and expand the project into a more complete end-to-end quality engineering framework.</p>

### Containerized Test Execution

<p>Introduce <b>Docker</b> to provide a consistent, reproducible execution environment across local development and CI.</p>
<p>Potential additions include:</p>

<ul>
  <li>Dockerized test execution</li>
  <li>Selenium Grid</li>
  <li>Remote WebDriver</li>
  <li>Parallel browser containers</li>
</ul>

<hr>
