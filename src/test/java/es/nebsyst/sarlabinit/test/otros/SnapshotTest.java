/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.otros;

import static org.junit.jupiter.api.Assertions.assertTrue;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import java.io.File;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 */
public class SnapshotTest extends BaseFXTest {

  @Test
  @DisplayName("PT-SNAP1 — Snapshot: guardar captura de la vista principal")
  void saveMainSnapshot(FxRobot robot) {
    onFx(robot, () -> {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelEJSAppList.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
        WritableImage img = primaryStage.getScene().snapshot(null);
        File out = new File("target/screenshots/sarlabinit_main.png");
        out.getParentFile().mkdirs();
        ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", out);
      } catch (Exception e) { throw new RuntimeException(e); }
    });
    assertTrue(new File("target/screenshots/sarlabinit_main.png").exists());
  }
}

