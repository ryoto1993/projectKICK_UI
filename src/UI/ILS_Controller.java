package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.net.URL;
import java.util.ResourceBundle;

public class ILS_Controller implements Initializable {
    @FXML
    ToggleButton att_btn_a, att_btn_b, att_btn_c, att_btn_d, att_btn_e, att_btn_f;
    @FXML
    Label att_lbl_a, att_lbl_b, att_lbl_c, att_lbl_d, att_lbl_e, att_lbl_f;
    @FXML
    Button dill_btn_a, dill_btn_b, dill_btn_c, dill_btn_d, dill_btn_e, dill_btn_f;
    @FXML
    Button uill_btn_a, uill_btn_b, uill_btn_c, uill_btn_d, uill_btn_e, uill_btn_f;
    @FXML
    Label ill_lbl_a, ill_lbl_b, ill_lbl_c, ill_lbl_d, ill_lbl_e, ill_lbl_f;
    @FXML
    Label till_lbl_a, till_lbl_b, till_lbl_c, till_lbl_d, till_lbl_e, till_lbl_f;
    @FXML
    Canvas canvas;
    @FXML
    AnchorPane left_pane;
    @FXML
    SplitPane split_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        left_pane.widthProperty().addListener((observableValue, o, n) -> resizeCanvas());
        left_pane.heightProperty().addListener((observableValue, o, n) -> resizeCanvas());
    }


    // 在離席更新
    public void changeAttendance() {
        // ToDo: 在離席を更新するメソッド呼び出し
        if(att_btn_a.isSelected()) {att_lbl_a.setText("在席");} else {att_lbl_a.setText("離席");}
        if(att_btn_b.isSelected()) {att_lbl_b.setText("在席");} else {att_lbl_b.setText("離席");}
        if(att_btn_c.isSelected()) {att_lbl_c.setText("在席");} else {att_lbl_c.setText("離席");}
        if(att_btn_d.isSelected()) {att_lbl_d.setText("在席");} else {att_lbl_d.setText("離席");}
        if(att_btn_e.isSelected()) {att_lbl_e.setText("在席");} else {att_lbl_e.setText("離席");}
        if(att_btn_f.isSelected()) {att_lbl_f.setText("在席");} else {att_lbl_f.setText("離席");}
    }

    // ディジタル照度センサによる在離席更新
    public void changeAttendanceBySensor() {

    }

    // 目標照度設定
    public void changeTargetIlluminance(ActionEvent event) {
        // ToDo: 目標照度を更新するメソッド呼び出し
        if(event.getSource() == dill_btn_a) {till_lbl_a.setText(String.valueOf(Integer.parseInt(till_lbl_a.getText())-50));}
        if(event.getSource() == dill_btn_b) {till_lbl_b.setText(String.valueOf(Integer.parseInt(till_lbl_b.getText())-50));}
        if(event.getSource() == dill_btn_c) {till_lbl_c.setText(String.valueOf(Integer.parseInt(till_lbl_c.getText())-50));}
        if(event.getSource() == dill_btn_d) {till_lbl_d.setText(String.valueOf(Integer.parseInt(till_lbl_d.getText())-50));}
        if(event.getSource() == dill_btn_e) {till_lbl_e.setText(String.valueOf(Integer.parseInt(till_lbl_e.getText())-50));}
        if(event.getSource() == dill_btn_f) {till_lbl_f.setText(String.valueOf(Integer.parseInt(till_lbl_f.getText())-50));}

        if(event.getSource() == uill_btn_a) {till_lbl_a.setText(String.valueOf(Integer.parseInt(till_lbl_a.getText())+50));}
        if(event.getSource() == uill_btn_b) {till_lbl_b.setText(String.valueOf(Integer.parseInt(till_lbl_b.getText())+50));}
        if(event.getSource() == uill_btn_c) {till_lbl_c.setText(String.valueOf(Integer.parseInt(till_lbl_c.getText())+50));}
        if(event.getSource() == uill_btn_d) {till_lbl_d.setText(String.valueOf(Integer.parseInt(till_lbl_d.getText())+50));}
        if(event.getSource() == uill_btn_e) {till_lbl_e.setText(String.valueOf(Integer.parseInt(till_lbl_e.getText())+50));}
        if(event.getSource() == uill_btn_f) {till_lbl_f.setText(String.valueOf(Integer.parseInt(till_lbl_f.getText())+50));}
    }

    // ディジタル照度センサによる目標照度更新
    public void changeTargetIlluminanceBySensor() {

    }

    // キャンバスをリサイズ
    public void resizeCanvas() {
        double pane_w = left_pane.getWidth();
        double pane_h = left_pane.getHeight()-110;
        double size = pane_h < pane_w ? pane_h : pane_w;
        canvas.setHeight(size);
        canvas.setWidth(size);
        canvas.setLayoutX((pane_w-size)/2);
        canvas.setLayoutY((pane_h-size)/2);

        updateCanvas();
    }

    // キャンバスをアップデート
    public void updateCanvas() {
        GraphicsContext gc= canvas.getGraphicsContext2D();

        double size = canvas.getWidth();
        double sx = 0.05, sy = 0.05;  // グリッドの右上
        double ex = 0.95, ey = 0.95;  // グリッドの左下

        // 初期化
        gc.setTransform(new Affine(1f, 0f, 0f, 0f, 1f, 0));
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // グリッド描画
        gc.setFill(Color.WHITE);
        gc.fillRect(sx*size, sy*size, (ex-sx)*size, (ey-sy)*size);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3.0);
        gc.strokeRect(sx*size, sy*size, (ex-sx)*size, (ey-sy)*size);

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.5);
        for(int y=0; y<9; y++) gc.strokeLine(sx*size, (sy+(ey-sy)/9*(y+1))*size, ex*size, (sy+(ey-sy)/9*(y+1))*size);
        for(int x=0; x<9; x++) gc.strokeLine((sx+(ex-sx)/9*(x+1))*size, sy*size, (sx+(ex-sx)/9*(x+1))*size, ey*size);

        // 照明を描画
        int lightPos[][] = {{1, 1}, {4, 1}, {7, 1},
                            {1, 4}, {4, 4}, {7, 4},
                            {1, 7}, {4, 7}, {7, 7}};
        for(int[] pos: lightPos) {
            gc.setFill(Color.WHITE);
            gc.fillRect((sx+(ex-sx)/9*(pos[0]))*size, (sy+(ey-sy)/9*(pos[1]))*size, (ex-sx)/9*size, (ey-sy)/9*size);
            gc.setFill(Color.ORANGE);
            gc.fillRect((sx+(ex-sx)/9*(pos[0]))*size, (sy+(ey-sy)/9*(pos[1]))*size, (ex-sx)/9*size, (ey-sy)/9/3*size);
            gc.fillRect((sx+(ex-sx)/9*(pos[0]))*size, (sy+(ey-sy)/9*(pos[1]))*size + 2*(ey-sy)/9/3*size, (ex-sx)/9*size, (ey-sy)/9/3*size);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1.5);
            gc.strokeRect((sx+(ex-sx)/9*(pos[0]))*size, (sy+(ey-sy)/9*(pos[1]))*size, (ex-sx)/9*size, (ey-sy)/9*size);
        }

        // 点灯パターンを描画

        // センサを描画
    }

}