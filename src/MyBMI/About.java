package MyBMI;

import javafx.scene.control.Alert;

public class About {
    public void showAboutAlert(){
        String title="MyBMI";
        String header="MyBMI";
        String message="BMI表示アプリ（テスト作成）\n" +
                "ver.0.1β\n" +
                "made by Masatoshi Katono";

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.getDialogPane().setHeaderText(header);
        alert.getDialogPane().setContentText(message);
        alert.showAndWait();
    }
}
