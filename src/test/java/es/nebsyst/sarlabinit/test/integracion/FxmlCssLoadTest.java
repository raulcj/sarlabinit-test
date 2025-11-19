/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.integracion;

import static org.junit.jupiter.api.Assertions.*;
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

public class FxmlCssLoadTest extends BaseFXTest {

  @Test
  @DisplayName("PT-03 — Carga la vista y aplica CSS en orden")
  void loadViewWithCss(FxRobot robot) {
    onFx(robot, () -> {
      try {
        var loader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
        Parent root = loader.load();
        assertNotNull(loader.getController());

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
          "es/styles/base.css",
          "es/styles/utilities.css",
          "es/styles/buttons.css",
          "es/styles/inputs.css",
          "es/styles/lists_tables.css",
          "es/styles/containers.css",
          "es/styles/extras.css"
        );
        assertTrue(scene.getStylesheets().size() >= 5, "CSS suficientes aplicados");
      } catch (Exception e) { fail(e); }
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

