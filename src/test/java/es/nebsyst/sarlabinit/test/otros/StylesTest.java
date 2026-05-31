/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.otros;

import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.main.StyleManager;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import java.net.URL;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 * 
 * Verifica la aplicacion de la hoja de estilos global a una escena.
 * 
 */

public class StylesTest extends BaseFXTest 

{

    private static final String CSS_PATH = "/es/styles/sarlab-main-blue.css";

    @Test
    @DisplayName("PT-E1 — CSS global: StyleManager aplica una hoja de estilos correctamente")
    void cssCheck(FxRobot robot) {
        URL cssPath = getClass().getResource(CSS_PATH);
        assertNotNull(cssPath,"No se ha encontrado la hoja " + CSS_PATH + " en el classpath de pruebas");
        String hojaCss = cssPath.toExternalForm();
       
        onFx(robot, () -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelMain.fxml"));
                Parent root = loader.load();
                assertNotNull(loader.getController(), "El controlador de la vista no ha sido inyectado");
                Scene scene = StyleManager.getInstance().createSceneCss(root);
                // Aplicar estilo y mostrar hoja
                primaryStage.setScene(scene);
                primaryStage.show();
                // Comprobacioens sobre la hoja de estilo
                List<String> stylesheets = scene.getStylesheets(); 
                assertFalse(stylesheets.isEmpty(), "No existen hojas de estilo aplicadas");
                assertEquals(1, stylesheets.size(),"La escena debe contener exactamente una hoja CSS");
                assertEquals(hojaCss, stylesheets.get(0), "La hoja cargada no coincide con la hoja esperada");   
            } catch (Exception e) {
                System.err.println("Error al ejecutar el test: " + e.getMessage());
            }
        });
    }
}
