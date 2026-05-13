/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.validacion;

import static org.junit.jupiter.api.Assertions.assertThrows;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

/**
 *
 * @author alumno
 * 
 * Verifica excepcion controlada por FXML inexistente o invalido
 * 
 */
public class RobustTest extends BaseFXTest 
{

  @Test
  void invalidFxmlCheck(FxRobot robot) {
    onFx(robot, () -> {
      // se fuerza ruta de recurso inexistente
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src_indefinido/sin_definir.fxml"));
      assertThrows(Exception.class, () -> {
        try { 
            fxmlLoader.load(); 
        } catch (Exception e) { 
            throw new RuntimeException("Error al cargar fichero FXML: ", e);
        }
      });
    });
  }
        
}

