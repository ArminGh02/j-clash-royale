package view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogShower {
  public static void show(String dialogTitle, String dialogContentText) {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle(dialogTitle);
    dialog.setContentText(dialogContentText);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.showAndWait();
  }
}
