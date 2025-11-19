/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.unidad;

import es.nebsyst.sarlabinit.main.StageManager;
import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 */
public class StageManagerTest extends BaseFXTest {

  @Test
  @DisplayName("PT-U1 — StageManager: registrar y recuperar Stage por clave")
  void registerAndGetStage(FxRobot robot) {
    onFx(robot, () -> {
      var smgr = StageManager.getInstance();
      var stage = new Stage();
      smgr.registerStage("PanelEJSAppList", stage);
      assertSame(stage, smgr.getStage("PanelEJSAppList"));
    });
  }

  @Test
  @DisplayName("PT-U2 — StageManager: sobrescritura de clave")
  void overwriteStage(FxRobot robot) {
    onFx(robot, () -> {
      var smgr = StageManager.getInstance();
      var s1 = new Stage();
      var s2 = new Stage();
      smgr.registerStage("PanelClientServerName", s1);
      smgr.registerStage("PanelClientServerName", s2);
      assertSame(s2, smgr.getStage("PanelClientServerName"));
    });
  }
}

