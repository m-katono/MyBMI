package MyBMI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class SecondController implements Initializable {

    @FXML private TableView<MyBMI.csvData> tv=new TableView<>();
    @FXML private TableColumn<MyBMI.csvData,String> DATECOLUMN;
    @FXML private TableColumn<MyBMI.csvData,String> SHINCHOCOLUMN;
    @FXML private TableColumn<MyBMI.csvData,String> TAIJUCOLUMN;
    @FXML private TableColumn<MyBMI.csvData,String> BMICOLUMN;
    @FXML private TextField tf21,tf22,tf23;
    private ObservableList<MyBMI.csvData> tabledata;

        public void initialize(URL url, ResourceBundle rb) {
            //TableViewにデータをセット
            tabledata = FXCollections.observableArrayList();
            tv.itemsProperty().setValue(tabledata);
            tv.setItems(tabledata);
        //TableViewとコントロールとデータクラスを関連付ける
        DATECOLUMN.setCellValueFactory(new PropertyValueFactory<MyBMI.csvData, String>("date"));
        SHINCHOCOLUMN.setCellValueFactory(new PropertyValueFactory<MyBMI.csvData, String>("shincho"));
        TAIJUCOLUMN.setCellValueFactory(new PropertyValueFactory<MyBMI.csvData, String>("taiju"));
        BMICOLUMN.setCellValueFactory(new PropertyValueFactory<MyBMI.csvData, String>("bmi"));
        //TextViewを内蔵して、編集できるようにする
        DATECOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        SHINCHOCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        TAIJUCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        BMICOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        }

    //編集画面の読込ボタンを押したとき
    public void chooseCLICKED(ActionEvent actionEvent){
        //CSVファイルに限定して読み込む
        FileChooser fc=new FileChooser();
        fc.setTitle("ファイル選択");
        fc.getExtensionFilters().add((new FileChooser.ExtensionFilter("CSVファイル","*.csv","*.CSV")));
        File file=fc.showOpenDialog(null);

        try{        //１行ずつ読み込む
                    File f=new File(file.getPath());
                    BufferedReader br=new BufferedReader(new FileReader(f));
                    String s;
                    //データが無くなるまでTableViewにデータをセット
                    while((s=br.readLine()) != null){
                        String[] a=s.split(",");
                        //nullがなければ読込
                        int a_length=a.length;
                        if(a_length==4){tabledata.add(new MyBMI.csvData(a[0],a[1],a[2],a[3]));}
                    }
            br.close();
            }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //保存ボタンを押したとき
    public void saveCLICKED(ActionEvent actionEvent) {
        try {    //SaveDialogを表示してファイルを指定
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSVファイル", "*.csv", "*.CSV"));
            File fls = fc.showSaveDialog(new Stage());

                if(fls!=null) {
                    //保存用にTableColumnのデータを取得
                    String savedata;                                //上書き保存用に
                    File deletefile = new File(String.valueOf(fls));  //一旦削除して
                    if (deletefile.exists()) {
                        deletefile.delete();
                    }   //同じファイル名で
                    deletefile.createNewFile();                     //空のファイルを作る
                    //TableViewの表示内容を保存
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fls, true));
                    for (int i = 0; i < tabledata.size(); i++) {                 //tabledata(ObservableList)のsizeまで
                        String newDate = tabledata.get(i).getDate();        //データクラスのgetterからデータを取得
                        String newShincho = tabledata.get(i).getShincho();  //繰り返しデータを取得→保存を繰り返す
                        String newTaiju = tabledata.get(i).getTaiju();
                        String newBmi = tabledata.get(i).getBmi();
                        savedata = newDate + "," + newShincho + "," + newTaiju + "," + newBmi + "\n";
                        bw.write(savedata);
                    }
                    //ActionEventがらSceneを読み込んでhide
                    Scene sc = ((Node) actionEvent.getSource()).getScene();//保存したら自動で画面を閉じる
                    Window window = sc.getWindow();
                    window.hide();
                    bw.close();
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //閉じるボタンを押したとき
    public void closeCLICKED(ActionEvent actionEvent){
        //ActionEventがらSceneを読み込んでhide
        Scene sc=((Node)actionEvent.getSource()).getScene();
        Window window=sc.getWindow();
        window.hide();
    }

    //データ追加欄のaddボタンを押したとき
    public void addCLICKED() {
        try {   //TextFieldのデータを取得
            String adddate = tf21.getText();
            String addshincho = tf22.getText();
            String addtaiju = tf23.getText();
            //正規表現を使って入力をチェック
            String regex="^([0-9]+)(\\.?[0-9]*)$";  //身長と体重を
            Pattern p=Pattern.compile(regex);       //数字だけで限定
            Matcher mat1=p.matcher(addshincho);
            Matcher mat2=p.matcher(addtaiju);
            String regexdate="^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$";    //日付を
            Pattern p1=Pattern.compile(regexdate);                  //数字４桁ー２桁ー２桁
            Matcher matdate=p1.matcher(adddate);                    //に制限 [yyyy-MM-dd]
            //Matcherで正規表現と比較して入力を制限する
            if(mat1.matches() && mat2.matches() && matdate.matches()) {
                //Doubleにして計算してStringにして桁数を制限
                Double addbmi = parseDouble(addtaiju) / parseDouble(addshincho) / parseDouble(addshincho) * 10000;
                String stringbmi = String.format("%.1f", addbmi);
                //ObservableListにデータを追加
                tabledata.addAll(new MyBMI.csvData(adddate, addshincho, addtaiju, stringbmi));
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("エラー");
                alert.getDialogPane().setHeaderText("不正な入力です。");
                alert.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //編集画面からグラフボタンでLineChartを作成
    public void graphCLICKED() throws IOException {
        //新しい画面を表示
        FXMLLoader loader = new FXMLLoader();
        BorderPane bp = loader.load(getClass().getResourceAsStream("Graph.fxml"));
        Scene sc = new Scene(bp);
        Stage stage = new Stage();
        stage.setScene(sc);
        stage.setTitle("グラフ");
        stage.showAndWait();
    }
}
