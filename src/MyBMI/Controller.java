/*  一度削除してしまったため
    Jarファイルから.classファイルを抽出して
    デコンパイルしたソース
*/

package MyBMI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    MyBMI.bmi b = new MyBMI.bmi();
    MyBMI.About About = new About();

    public Controller() {
    }
    //BT1をクリックしたときの処理（計算ボタン）
    public void BT1CLICKED() {
        try {
            //TextField１・２からデータを取得
            String s = this.tf1.getText();
            String t = this.tf2.getText();
            //Matcharで正規表現と比較して、数字じゃなければエラー
            String regex = "^([0-9]+)(\\.?[0-9]*)$";
            Pattern p = Pattern.compile(regex);
            Matcher mat1 = p.matcher(s);
            Matcher mat2 = p.matcher(t);
            if (!s.equals("") && !t.equals("")) {               //身長と体重が空白じゃなければ
                if (!mat1.matches() && !mat2.matches()) {       //数字じゃなければ
                    this.lb1.setText("数字を入力↓");
                } else {
                    this.b.setBmiData(s, t);                    //bmiクラスで計算して
                    this.lb1.setText(this.b.getBmiData());      //Label1に計算結果
                    this.lb2.setText(this.b.getDate());         //Label2にDate
                }
            } else {
                this.lb1.setText("身長と体重を入力↓");
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    //BT2をクリックしたときの処理（クリアボタン）
    public void BT2CLICKED() {
        this.lb1.setText("");
        this.lb2.setText("");
        this.tf1.setText("");
        this.tf2.setText("");
    }

    /*------------------------------
            ここからはメニュー内の処理
     -------------------------------*/
    //閉じるときの処理
    public void exit() {
        Platform.exit();
    }

    //保存ボタンを押したとき
    public void saveCLICKED() {
        this.b.Save();
    }

    //編集ボタンを押したとき
    public void editCLICKED() throws Exception {
        this.showSecondWindow();
    }

    //編集ボタンを押したときに新しいWindowを憑依
    private void showSecondWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SecondWindow.fxml"));
        BorderPane bp = (BorderPane)loader.load();
        Scene sc = new Scene(bp);
        Stage stage = new Stage();
        stage.setTitle("編集");
        stage.setScene(sc);
        stage.showAndWait();
    }

    //グラフボタンを押したときに新しいWindowを憑依
    public void graphCLICKED() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane bp = (BorderPane)loader.load(this.getClass().getResourceAsStream("Graph.fxml"));
        Scene sc = new Scene(bp);
        Stage stage = new Stage();
        stage.setScene(sc);
        stage.setTitle("グラフ");
        stage.showAndWait();
    }

    public void showAbout() {
        this.About.showAboutAlert();
    }
}
