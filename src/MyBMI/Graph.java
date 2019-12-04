package MyBMI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Graph {
    @FXML
    LineChart gr1;

    //グラフ画面のグラフボタンを押したとき
    public void showGraph(){
        XYChart.Series<String,Double> series=new XYChart.Series<String,Double>();

        //FileChooserでCSVファイルを読み込む
        FileChooser fc=new FileChooser();
        fc.setTitle("ファイルを選択");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSVファイル","*.csv","*.CSV"));
        File file=fc.showOpenDialog(null);
        try {
            File f = new File(file.getPath());
            series.setName(f.getName());
            BufferedReader br = new BufferedReader(new FileReader(f));
            //readLine()がnullじゃなければデータを処理
            String s;
            while((s=br.readLine())!=null) {
                //１行づつ読み込んで、カンマを目印にデータを配列にする
                String[] brdata = s.split(",");
                //XYChartのSeriesに配列の１個目と４個目のデータを入れる
                series.getData().add(new XYChart.Data<>(brdata[0], Double.parseDouble(brdata[3])));
            }
            br.close();
            //LineChartにSeriesをセットする
            gr1.getData().add(series);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //クリアボタン
    public void clearCLICKED(){
        gr1.getData().clear();
    }
    //閉じるボタン
    public void closeCLICKED(ActionEvent actionEvent) {
        //window の　Sceneをhide
        Scene sc=((Node)actionEvent.getSource()).getScene();
        Window window=sc.getWindow();
        window.hide();
    }
}
