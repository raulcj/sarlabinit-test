/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.test.otros;

import static org.junit.jupiter.api.Assertions.*;
import es.nebsyst.sarlabinit.test.base.BaseFXTest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author alumno
 */
public class StylesTest extends BaseFXTest {

  @Test
  @DisplayName("PT-E1 — CSS: el componente Scene contiene las hojas de estilo en orden estricto (base→utilities→buttons→inputs→list_tables→containers→extras)")
  void cssFilesCheck(FxRobot robot) {
    // Cargar la escena (Scene) objeto de estudio en la ventana (Stage) contenedora
    onFx(robot, () -> {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/nebsyst/sarlabinit/view/PanelMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Aplicar CSS a la escena en caso de que no exista
        addCss(scene);
        primaryStage.setScene(scene);
        primaryStage.show();  
      } catch (Exception e) {
        fail("No se pudo crear o mostrar la escena de prueba: " + e.getMessage());
      }
    });
    // Comprobar existencia de CSS y orden estricto
    List<String> urls = new ArrayList<>(primaryStage.getScene().getStylesheets());
    assertFalse(urls.isEmpty(), "No existen hojas de estilo aplicadas");

    int iBase       = indexOf(urls, "base.css");
    int iUtil       = indexOf(urls, "utilities.css");
    int iButtons    = indexOf(urls, "buttons.css");
    int iInputs     = indexOf(urls, "inputs.css");
    int iListTables = indexOf(urls, "lists_tables.css"); 
    int iContainers = indexOf(urls, "containers.css");
    int iExtras     = indexOf(urls, "extras.css"); 

    assertAll("Todas las hojas deben estar presentes",
        () -> assertTrue(iBase       >= 0, "Falta base.css"),
        () -> assertTrue(iUtil       >= 0, "Falta utilities.css"),
        () -> assertTrue(iButtons    >= 0, "Falta buttons.css"),
        () -> assertTrue(iInputs     >= 0, "Falta inputs.css"),
        () -> assertTrue(iListTables >= 0, "Falta lists_tables.css"),
        () -> assertTrue(iContainers >= 0, "Falta containers.css"),
        () -> assertTrue(iExtras     >= 0, "Falta extras.css")
    );

    assertAll("Orden estricto requerido (base→utilities→buttons→inputs→lists_tables→containers→extras)",
        () -> assertTrue(iBase       < iUtil,       ordenMsj("base.css",        "utilities.css", urls)),
        () -> assertTrue(iUtil       < iButtons,    ordenMsj("utilities.css",   "buttons.css",   urls)),
        () -> assertTrue(iButtons    < iInputs,     ordenMsj("buttons.css",     "inputs.css",    urls)),
        () -> assertTrue(iInputs     < iListTables, ordenMsj("inputs.css",      "lists_tables.css", urls)),
        () -> assertTrue(iListTables < iContainers, ordenMsj("lists_tables.css","containers.css", urls)),
        () -> assertTrue(iContainers < iExtras,     ordenMsj("containers.css",  "extras.css", urls))
    );
  }

  // Aplicar CSS
  private void addCss(Scene scene) {
      if (scene.getStylesheets().isEmpty()) {
          String[] cssPaths = new String[] {
               "/es/styles/base.css",
               "/es/styles/utilities.css",
               "/es/styles/buttons.css",
               "/es/styles/inputs.css",
               "/es/styles/lists_tables.css",
               "/es/styles/containers.css",
               "/es/styles/extras.css",
          };
          for (String p : cssPaths) {
              URL url = getClass().getResource(p);
              if (url != null) {
                  scene.getStylesheets().add(url.toExternalForm());
              }
          }         
      }     
  }
  // Devuelve el indice de la primera hoja de estilos cuya URL termina con cualquiera de los sufijos dados
  private int indexOf(List<String> urls, String... suffixes) {
    for (int i = 0; i < urls.size(); i++) {
      String u = urls.get(i);
      for (String s : suffixes) {
        if (u != null && (u.endsWith("/" + s) || u.endsWith(s))) {
          return i;
        }
      }
    }
    return -1;
  }

  // Devuelve un mensaje si el orden de aplicacion de las hojas no es correcto
  private String ordenMsj(String h1, String h2, List<String> urls) {
    return "Orden incorrecto: se esperaba " + h1 + " antes que " + h2 + "\nCSS cargado:\n" + String.join("\n", urls);
  }
  
}
