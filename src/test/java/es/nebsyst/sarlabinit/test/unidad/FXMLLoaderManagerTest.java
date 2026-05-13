/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.unidad;

import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 * 
 * Comprueba el registro de cargadores FXML. Verifica carga de la vista y de su controlador asociado.
 * 
 */
public class FXMLLoaderManagerTest extends BaseFXTest {

  @Test
  @DisplayName("PT-02 — FXMLLoaderManager: carga de FXML válido e inyecta controlador")
  void checkLoaderLoadAndController(FxRobot robot) {
    onFx(robot, () -> 
    {
      FXMLLoaderManager fmgr = FXMLLoaderManager.getInstance();
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
      fmgr.registerLoader("PanelEJSAppList", fxmlLoader);
      Parent root = assertDoesNotThrow(() -> {
        try { 
            return fxmlLoader.load(); 
        } catch (Exception e) { 
            throw new RuntimeException(e); 
        }
      });
      assertNotNull(root);
      assertNotNull(fxmlLoader.getController(), "Inyeccion del controlador");
    });
  }
}

