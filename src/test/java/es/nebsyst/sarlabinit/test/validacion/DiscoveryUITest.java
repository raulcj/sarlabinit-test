/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.validacion;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.StageManager;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
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
public class DiscoveryUITest extends BaseFXTest {

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

  /** 
   * 
   * Muestra la vista de PanelConsole
   * 
   * PRE: instanciacion del controlador de PanelClientServerName 
   *
   */
  private void showConsole(FxRobot robot) {
    onFx(robot, () -> {
      try {
        // requerido indirectamente por PanelClientServerName
        loadPanelMain(); 
        // cargamos y registramos también PanelClientServerName para que el controlador Console lo encuentre
        FXMLLoader discoveryLoader = new FXMLLoader(getClass().getResource("/es/uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
        FXMLLoaderManager.getInstance().registerLoader("PanelClientServerName", discoveryLoader);
        discoveryLoader.load();
        // carga de PanelConsole
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/uhu/sarlab/discovery/view/PanelConsole.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  @DisplayName("PT-09 — DISCOVERY: botones Desktop/VNC/Telnet/SSH/Actualizar visibles")
  void buttonsVisible(FxRobot robot) {
    showDiscovery(robot);
    verifyThat("#jButtonDesktop",    isVisible());
    verifyThat("#jButtonVNC",        isVisible());
    verifyThat("#jButtonTelnet",     isVisible());
    verifyThat("#jButtonSsh",        isVisible());
    verifyThat("#jButtonActualizar", isVisible());
  }

  @Test
  @DisplayName("PT-11 — DISCOVERY: acceso al panel Consola")
  void checkConsoleView(FxRobot robot) {
    showConsole(robot);
    verifyThat("#jTextConsole", isVisible());
  }
  
}
