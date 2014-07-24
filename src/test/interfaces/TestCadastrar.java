package test.interfaces;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCadastrar {

	@Test
	public void Cadastrar() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/manopi/cadastro.jsp");
		//driver.findElement(By.linkText("aqui.")).click();
		
		WebElement form = driver.findElement(By.tagName("form"));
		
		driver.findElement(By.name("login")).sendKeys("loginTeste");
		driver.findElement(By.name("senha")).sendKeys("loginTeste");
		driver.findElement(By.name("nome")).sendKeys("loginTeste");
		driver.findElement(By.name("telefone")).sendKeys("loginTeste");
		driver.findElement(By.name("endereco")).sendKeys("loginTeste");
		
		
		form.submit();
		
		String sair = driver.findElement(By.linkText("Sair")).getText();
		assertEquals("Sair", sair);
		
	}
	
}
