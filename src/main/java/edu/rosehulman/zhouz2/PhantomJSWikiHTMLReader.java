package edu.rosehulman.zhouz2;

import org.openqa.selenium.By;
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
  private WebDriver driver;
  private String currentHTMLFileURL;

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
    return null;
  }

  @Override
  public Map<String, String> getVCardMap() throws IllegalStateException {
    if (currentHTMLFileURL == null) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    driver.get(currentHTMLFileURL);
    List<WebElement> rows = driver.findElements(By.cssSelector("table.vcard tbody tr"));
    return null;
  }
}
