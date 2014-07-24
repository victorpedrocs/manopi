package test.interfaces;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestLogout {
	
	@Test
	public void Logout(){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/manopi/login.jsp");
		WebElement form = driver.findElement(By.tagName("form"));
		
		driver.findElement(By.name("login")).sendKeys("dev");
		driver.findElement(By.name("senha")).sendKeys("dev");
		
		form.submit();
		
		driver.findElement(By.linkText("Sair")).click();
		
		String login = driver.findElement(By.linkText("Login")).getText();
		
		assertEquals("Login", login);

	}
}
