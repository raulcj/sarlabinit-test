/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.integracion;

import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.main.StageManager;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 */
public class NavigationTest extends BaseFXTest {

  @Test
  @DisplayName("PT-01 — Flujo 'Restaurar': Stage registrado + carga de Scene + show() de Stage")
  void restoreAppSarlabOnRegisteredStage(FxRobot robot) {
    onFx(robot, () -> {
      try {
        Stage mainStage = new Stage();
        StageManager.getInstance().registerStage("PanelEJSAppList", mainStage);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
        Parent root = fxmlLoader.load();

        mainStage.setScene(new Scene(root));
        mainStage.show();

        assertTrue(mainStage.isShowing());
        assertNotNull(fxmlLoader.getController());
      } catch (Exception e) { 
          fail(e); 
      }
    });
  }
}

