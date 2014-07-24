package test.interfaces;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.ConnectionFactory;

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
	
	@AfterClass
	public static void limparDados() throws SQLException{
		Connection con = ConnectionFactory.getConnection();
		String sql = "DELETE FROM cliente WHERE login = ? AND senha = ? AND nome = ? AND telefone = ? AND endereco = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		
		statement.setString(1, "loginTeste");
		statement.setString(2, "loginTeste");
		statement.setString(3, "loginTeste");
		statement.setString(4, "loginTeste");
		statement.setString(5, "loginTeste");
		
		statement.executeUpdate();
		
	}
	
}
