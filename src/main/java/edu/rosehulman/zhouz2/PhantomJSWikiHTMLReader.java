package edu.rosehulman.zhouz2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhou Zhou on 5/19/17.
 */
public class PhantomJSWikiHTMLReader implements IWikiHTMLReader {
  private static int INITIAL_BUFFER_SIZE = 50000;

  private WebDriver driver;
  private String currentHTMLFileURL;

  private static Object removeElement(WebElement ele, WebDriver driver) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    return executor.executeScript("arguments[0].parentNode.removeChild(arguments[0])", ele);
  }

  public PhantomJSWikiHTMLReader() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setJavascriptEnabled(true);
    driver = new PhantomJSDriver(capabilities);
    currentHTMLFileURL = null;
  }

  @Override
  public void setWikiHTMLFile(File htmlFile) {
    try {
      String url = htmlFile.toURI().toURL().toString();
      driver.get(url);
      currentHTMLFileURL = url;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTitle() throws IllegalStateException {
    if (currentHTMLFileURL == null) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    driver.get(currentHTMLFileURL);
    return driver.getTitle();
  }

  @Override
  public String getBody() throws IllegalStateException {
    if (currentHTMLFileURL == null) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    driver.get(currentHTMLFileURL);
    List<WebElement> sups = driver.findElements(By.cssSelector("#mw-content-text p sup"));
    for (WebElement sup : sups) {
      removeElement(sup, driver);
    }
    List<WebElement> paragraphs = driver.findElements(By.cssSelector("#mw-content-text p"));
    StringBuffer buffer;
    if (!paragraphs.isEmpty()) {
      buffer = new StringBuffer(INITIAL_BUFFER_SIZE);
      for (WebElement paragraph : paragraphs) {
        String paragraphText = paragraph.getText();
        buffer.append(paragraphText);
        buffer.append('\n');
      }
      String result = buffer.substring(0, buffer.length() - 1);
      return result;
    } else {
      return "";
    }
  }

  @Override
  public Map<String, String> getVCardMap() throws IllegalStateException {
    if (currentHTMLFileURL == null) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    driver.get(currentHTMLFileURL);
    WebElement content = driver.findElement(By.id("mw-content-text"));

    List<WebElement> rows = driver.findElements(By.cssSelector("table.vcard tbody tr"));
    return null;
  }
}
