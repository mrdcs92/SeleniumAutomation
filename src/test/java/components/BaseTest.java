package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LandingPage;

public class BaseTest {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public LandingPage landingPage;
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver webDriver) {
		driver.set(webDriver);
	}

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//resources//GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		
		WebDriver webDriver = null;

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--window-size=2560,1440");
			}

			webDriver = new ChromeDriver(options);
			// driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
		}

		setDriver(webDriver);
		if (!browserName.toLowerCase().contains("headless")) {
            getDriver().manage().window().maximize();
        }

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return getDriver();

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert string to HashMap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver webDriver) throws IOException {

		if (webDriver == null) {
            throw new IllegalStateException(
                    "Cannot take screenshot because WebDriver is null.");
        }
		
		TakesScreenshot ts = (TakesScreenshot) webDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		initializeDriver();
		landingPage = new LandingPage(getDriver());
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		WebDriver webDriver = getDriver();
		if (webDriver != null) {
			try {
				webDriver.quit();
			} finally {
				driver.remove();
			}
			
		}
	}
}
