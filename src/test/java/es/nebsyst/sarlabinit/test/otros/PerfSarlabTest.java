/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.otros;

import static org.junit.jupiter.api.Assertions.assertTrue;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.testfx.api.FxRobot;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author alumno
 * 
 * Calcula tiempo de apertura de vista PanelEJSAppList y valida limite de 2 segundos
 * 
 */
public class PerfSarlabTest extends BaseFXTest 

{

  @DisplayName("PT-R1 — Rendimiento: apertura SARLABINIT < 2.0 s")
  @RepeatedTest(5) // realiza 5 iteraciones
          
  void sarLabInitOpensInTime(FxRobot robot) {
    long[] ms = new long[1];
    onFx(robot, () -> {
      try {
        long t0 = System.nanoTime();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
        WaitForAsyncUtils.waitForFxEvents();
        ms[0] = (System.nanoTime() - t0) / 1_000_000;
      } catch (Exception e) { 
          throw new RuntimeException(e); 
      }
    });
    
    assertTrue(ms[0] < 2000, "La apertura de PanelEJSAppList (SARLABINIT) ha tardado: " + ms[0] + " ms");
  }
  
}

