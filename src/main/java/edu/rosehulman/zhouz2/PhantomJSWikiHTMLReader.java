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
  private boolean fileSet;

  public PhantomJSWikiHTMLReader() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setJavascriptEnabled(true);
    driver = new PhantomJSDriver(capabilities);
    fileSet = false;
  }

  @Override
  public void setWikiHTMLFile(File htmlFile) {
    try {
      driver.get(htmlFile.toURI().toURL().toString());
      fileSet = true;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTitle() throws IllegalStateException {
    if (!fileSet) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    return driver.getTitle();
  }

  @Override
  public String getBody() throws IllegalStateException {
    if (!fileSet) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    return null;
  }

  @Override
  public Map<String, String> getVCardMap() throws IllegalStateException {
    if (!fileSet) {
      throw new IllegalStateException("HTML file path hasn't been set!");
    }
    List<WebElement> rows = driver.findElements(By.cssSelector("table.vcard tbody tr"));
    return null;
  }
}
