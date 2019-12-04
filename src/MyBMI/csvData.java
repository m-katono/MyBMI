package MyBMI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class csvData {


    private StringProperty date;
    private StringProperty shincho;
    private StringProperty taiju;
    private StringProperty bmi;

    public csvData(String date,String shincho,String taiju,String bmi){
        this.date=new SimpleStringProperty(date);
        this.shincho=new SimpleStringProperty(shincho);
        this.taiju=new SimpleStringProperty(taiju);
        this.bmi=new SimpleStringProperty(bmi);
    }

    public StringProperty dateProperty(){ return date; }
    public StringProperty shinchoProperty(){ return shincho; }
    public StringProperty taijuProperty(){ return taiju; }
    public StringProperty bmiProperty(){ return bmi; }


    public void setDate(String date){this.date.set(date);}
    public void setShincho(String shincho){this.shincho.set(shincho);}
    public void setTaiju(String taiju){this.taiju.set(taiju);}
    public void setBmi(String bmi){this.bmi.set(bmi);}
    public String getDate(){return date.get();}
    public String getShincho(){return shincho.get();}
    public String getTaiju(){return taiju.get();}
    public String getBmi(){return bmi.get();}

}
