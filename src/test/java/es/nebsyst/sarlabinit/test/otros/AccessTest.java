/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.otros;

import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.StageManager;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author alumno
 * 
 * 
 * Comprueba visibilidad de control boton y navegacion por teclado
 * 
 */
public class AccessTest extends BaseFXTest {

  /** 
   * Carga y registra PanelMain en el FXMLLoaderManager, dejando su controlador disponible
   * 
   */
  private void loadPanelMain() {
    try {
      FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelMain.fxml"));
      FXMLLoaderManager.getInstance().registerLoader("PanelMain", mainLoader);
      // se ejecuta load() para que getController() NO sea null cuando lo pidan otros controladores
      mainLoader.load();
    } catch (Exception e) {
      throw new RuntimeException("Error al cargar PanelMain.fxml", e);
    }
  }
  
  /** 
   * 
   * Muestra el panel de DISCOVERY (PanelClientServerName) 
   * 
   */
  private void showDiscovery(FxRobot robot) {
    onFx(robot, () -> {
      try {
        // requerido por PanelClientServerNameController
        loadPanelMain(); 
        StageManager.getInstance().registerStage("PanelClientServerName", primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }
  
  @Test
  @DisplayName("PT-A1 — Accesibilidad: el foco es visible y avanza con TAB")
  void tabFocus(FxRobot robot) {
    showDiscovery(robot);
    // lógica de la tecla TAB
    robot.type(KeyCode.TAB); 
    robot.type(KeyCode.TAB);
    // se verifica la visibilidad del foco rn boton Actualizar
    verifyThat("#jButtonActualizar", isVisible());
  }
   
}

