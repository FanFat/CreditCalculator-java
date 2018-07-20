package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class DifferentiatedController {
    @FXML
    private TableView<Line> table;
    @FXML
    private TableColumn<Line, Integer> number;
    @FXML
    private TableColumn<Line, String> sum_plat;
    @FXML
    private TableColumn<Line, String> osn_dolg;
    @FXML
    private TableColumn<Line, String> nach_percent;
    @FXML
    private TableColumn<Line, String> ostatok;

    @FXML
    private Label total_sum_plat;
    @FXML
    private Label total_osn_dolg;
    @FXML
    private Label total_nach_percent;

    private double CK, KP, PC, OD, NP, monthly_percentage, OK;

    private double T_N_P, T_S_P;

    private void initVar() {
        this.CK = Controller.CK;
        this.KP = Controller.KP;
        this.PC = Controller.PC;
        this.OD = CK / KP;
        this.NP = 0;
        this.monthly_percentage = PC / 12;
        this.OK = Controller.CK;
    }

    private ObservableList<Line> items = FXCollections.observableArrayList();


    public void initialize() {
        initVar();

        for (int i = 0; i < KP; i++) {

            NP = OK * (PC / 12 / 100);
            items.add(new Line(i + 1, String.format(Locale.ENGLISH, "%.2f", NP + OD), String.format(Locale.ENGLISH,"%.2f", OD), String.format(Locale.ENGLISH,"%.2f", NP), String.format(Locale.ENGLISH,"%.2f", CK - OD)));
            OK -= OD;
            CK -= OD;

            T_S_P += NP + OD;
            T_N_P += NP;

        }

        table.itemsProperty().setValue(items);


        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Line, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getNumber());
            }
        });

        sum_plat.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getSum_plat()));
        osn_dolg.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getOsn_dolg()));
        nach_percent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Line, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getNach_percent());
            }
        });
        ostatok.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getOstatok()));

        total_sum_plat.setText(String.valueOf(new BigDecimal(T_S_P).setScale(2, RoundingMode.UP).doubleValue()));
        total_osn_dolg.setText(String.valueOf(new BigDecimal(OD * KP).setScale(2, RoundingMode.UP).doubleValue()));
        total_nach_percent.setText(String.valueOf(new BigDecimal(T_N_P).setScale(2, RoundingMode.UP).doubleValue()));

    }











}
