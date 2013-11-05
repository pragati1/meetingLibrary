package com.webdriver.recorder.handlers;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		RemoteWebDriver d = new FirefoxDriver();
		CFactoryMigrator CF = new CFactoryMigrator(d);
		CF.start();
		d.get("http://www.open2test.org/");
		d.findElement(By.id("name")).sendKeys("testing");
		d.findElement(By.id("emailID")).sendKeys("testing@test.com");
		d.findElement(By.id("Agreement")).click();
		d.findElementByLinkText("Comment").click();
		d.findElement(By.id("cname")).sendKeys("CommentName");
		d.findElement(By.id("cemailID")).sendKeys("CommentEmail");
		d.close();
		d.quit();
	}

	
}
