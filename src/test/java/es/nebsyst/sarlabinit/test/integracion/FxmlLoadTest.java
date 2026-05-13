/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.integracion;

import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.main.StyleManager;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 */

public class FxmlLoadTest extends BaseFXTest {
    
  @Test
  @DisplayName("PT-03 — Carga la vista y aplica la hoja CSS global mediante StyleManager")
  void loadView(FxRobot robot) { 
      onFx(robot, () -> {
        try {
            // Comprobar raiz y controlador NO nulos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
            Parent root = loader.load();
            assertNotNull(root, "La raiz del fichero FXML no puede ser nula");
            assertNotNull(loader.getController(),"El controlador debe inyectarse correctamente.");        
            // Comprobar Scene no nula
            Scene scene = StyleManager.createSceneCss(root);
            assertNotNull(scene, "La Scene creada no puede ser nula");
            // Comprobar Scene incluida en Stage
            primaryStage.setScene(scene);
            primaryStage.show();
            assertSame(scene, primaryStage.getScene(), "El Stage de prueba debe contener la escena");          
      } catch (Exception e) { 
          System.err.println("Error al ejecutar el test: " + e.getMessage());
      }
    });
  }

  @Test
  @DisplayName("PT-I18N — Bundle de la App DISCOVERY contiene la clave titulo del Stage")
  void titleBundle() {
    ResourceBundle rb = ResourceBundle.getBundle("es/uhu/sarlab/discovery/view/bundle/Bundle_PanelClientServerName");
    assertAll(
      () -> assertNotNull(rb.getString("PanelClientServerName.title")),
      () -> assertFalse(rb.getString("PanelClientServerName.title").isBlank())
    );
  }
  
}

