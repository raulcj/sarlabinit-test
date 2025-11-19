package es.nebsyst.sarlabinit.test.base;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 *
 * @author alumno
 */
@ExtendWith(ApplicationExtension.class)
public abstract class BaseFXTest {

  protected Stage primaryStage;

  @Start
  public void start(Stage stage) {
    this.primaryStage = stage;
  }

  
  @BeforeEach
  void crearPrimaryStage() throws Exception {
    if (this.primaryStage == null) {
      this.primaryStage = FxToolkit.registerPrimaryStage();
    }
  }
  
  /** 
   * Habilita ejecucción segura en el hilo de JavaFX  
   *
   */
  protected void onFx(FxRobot robot, Runnable action) {
    robot.interact(action);
  }

  @AfterEach
  void cleanup() throws Exception {
    FxToolkit.cleanupStages();
  }
}
