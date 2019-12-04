package MyBMI;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.parseDouble;

public class bmi {

    //保存用に変数
    private String bmi;
    private String shincho;
    private String taiju;
    private String date;

    //日付を取得
    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  //フォーマットを設定
        LocalDate ld = LocalDate.now(); //現在の日時
        this.date = dtf.format(ld);

        return date;
    }

    //BMIを計算、変数に設定
    public void setBmiData(String s,String t){
        this.shincho=s;
        this.taiju=t;
        Double s1=parseDouble(s);   //Double型で計算
        Double t1=parseDouble(t);
        Double b=t1/s1/s1*10000;
        this.bmi=String.format("%.1f",b);   //String型に変換、小数点以下１桁
    }

    //BMIを返す
    public String getBmiData() { return bmi; }

    //BMIデータを保存
    public void Save(){
        try {
            //nullチェック
            if(date != null && shincho != null && taiju!=null && bmi != null) {
                //ファイル名を指定して保存
                FileChooser  fc=new FileChooser();
                //フィルターでＣＳＶファイルに限定
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSVファイル","*.csv","*.CSV"));
                File fls = fc.showSaveDialog(new Stage());
                    if (fls != null) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fls,true));

                        String str =date + "," + shincho + "," + taiju + "," + bmi+"\n";

                        bw.write(str);
                        bw.close();
                    }
                }
            else {
                    //入力項目が不足している場合にAlert
                    Alert  alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("エラー");
                    alert.getDialogPane().setHeaderText("入力が不足しています。" +
                            "BMIを計算してから保存してください。");
                    alert.show();
            }
        }
            catch(Exception e){
                e.printStackTrace();
            }


    }

}
