package perfilcarbdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@GerenciarPerfil", features = "classpath:perfilcarbdd" , monochrome = true, dryRun = false)
public class CadastroPerfilTests {

}
