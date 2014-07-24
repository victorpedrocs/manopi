package test.interfaces;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFazerPedido {
	
	@Test
	public void fazerPedido(){
		
		/*Logar*/
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/manopi/login.jsp");
		WebElement form = driver.findElement(By.tagName("form"));
		driver.findElement(By.name("login")).sendKeys("dev");
		driver.findElement(By.name("senha")).sendKeys("dev");
		form.submit();
		
		/*Fazer Pedido*/
		driver.findElement(By.linkText("Fazer Pedido")).click();
		driver.findElement(By.id("addPizza")).click();
		
		/*Adicionar Pizza*/
		WebElement formPizza = driver.findElement(By.id("formPizza"));
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantidade")));
		driver.findElement(By.name("quantidade")).sendKeys("3");
		formPizza.submit();

		/*Remover Pizza*/
		WebElement formRemover = driver.findElement(By.id("formRemover"));
		formRemover.submit();
		
		/*Adicionar Pizza de novo*/
		WebElement formPizza2 = driver.findElement(By.id("formPizza"));
		driver.findElement(By.id("addPizza")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantidade")));		
		driver.findElement(By.name("quantidade")).sendKeys("10");
		formPizza2.submit();
		
		/*Fechar Pedido*/
		WebElement formFecharPedido = driver.findElement(By.id("formFecharPedido"));
		driver.findElement(By.name("valorTroco")).sendKeys("200");
		formFecharPedido.submit();
		
		
		
	}

}
