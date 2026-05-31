/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.validacion;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 * 
 * Verifica la visibilidad de controles en dos vistas diferentes.
 * 
 */
public class SarlabUITest extends BaseFXTest 
{
    
  @Test
  @DisplayName("PT-04 — PanelMain: comprueba controles básicos visibles")
  void mainPanelControls(FxRobot robot) 
  {
    onFx(robot, () -> {
      try 
      {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelMain.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
      } catch (Exception e) { 
          throw new RuntimeException(e); 
      }
    });
    verifyThat("#langCombo", isVisible());
    verifyThat("#cssCombo", isVisible());
  }
  
  @Test
  @DisplayName("PT-05 — PanelJarProcess: comprueba componente visible")
  void jarProcessCheck(FxRobot robot) {
    onFx(robot, () -> {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelJarProcess.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
      } catch (Exception e) { 
          throw new RuntimeException(e); 
      }
    });
    verifyThat("#jList1", isVisible());
  }

}

