package com.example.myapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.example.myapp.Controller.teacher;

public class teacherController {
    @FXML
    private Button scheduleTButton;
    @FXML
    private Button gradeTButton;

    //ведомость
    @FXML
    private TableView<tGrade> attTable;
    @FXML
    private TextField groupNumberTextField;
    @FXML
    private ComboBox<String> studentComboBox;
    @FXML
    private ComboBox<String> subjBox;
    @FXML
    private Button showButton;
    @FXML
    private TableColumn<tGrade, String> first_col;
    @FXML
    private TableColumn<tGrade, String> second_col;
    @FXML
    private TableColumn<tGrade, String> third_col;
    @FXML
    private TableColumn<tGrade, String> test_col;
    @FXML
    private TableColumn<tGrade, String> exam_col;
    private final ObservableList<String> nameList = FXCollections.observableArrayList();
    private final ObservableList<String> subjList = FXCollections.observableArrayList();
    private final ObservableList<tGrade> grData = FXCollections.observableArrayList();

    //расписание
    @FXML
    private TableView<tSchedule> sch_Tab;
    @FXML
    private TableColumn<tSchedule, String> group_col;
    @FXML
    private TableColumn<tSchedule, String> lesType_col;
    @FXML
    private TableColumn<tSchedule, String> buil_col;
    @FXML
    private TableColumn<tSchedule, String> room_col;
    @FXML
    private TableColumn<tSchedule, String> day_col;
    @FXML
    private TableColumn<tSchedule, String> sub_col;
    @FXML
    private TableColumn<tSchedule, String> time_col;
    private final ObservableList<tSchedule> schData = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        //grades
        forGrade tmp = new forGrade();
        Callback<TableColumn<tGrade, String>, TableCell<tGrade,
                String>> cellFactory = p -> new editingCell();
        first_col.setOnEditCommit(
                (TableColumn.CellEditEvent<tGrade, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setF_att(t.getNewValue());
                    editGrade(t.getRowValue().getPk(), t.getNewValue(), 1);
                });
        first_col.setCellFactory(cellFactory);
        second_col.setOnEditCommit(
                (TableColumn.CellEditEvent<tGrade, String> t) -> {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setS_att(t.getNewValue());
                    editGrade(t.getRowValue().getPk(), t.getNewValue(), 2);
                });
        second_col.setCellFactory(cellFactory);
        third_col.setOnEditCommit(
                (TableColumn.CellEditEvent<tGrade, String> t) -> {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setT_att(t.getNewValue());
                    editGrade(t.getRowValue().getPk(), t.getNewValue(), 3);
                });
        third_col.setCellFactory(cellFactory);
        test_col.setOnEditCommit(
                (TableColumn.CellEditEvent<tGrade, String> t) -> {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTest(t.getNewValue());
                    editGrade(t.getRowValue().getPk(), t.getNewValue(), 4);
                });
        test_col.setCellFactory(cellFactory);
        exam_col.setOnEditCommit(
                (TableColumn.CellEditEvent<tGrade, String> t) -> {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setExam(t.getNewValue());
                    editGrade(t.getRowValue().getPk(), t.getNewValue(), 5);
                });
        exam_col.setCellFactory(cellFactory);




        gradeTButton.setOnAction(actionEvent -> {
            sch_Tab.setVisible(false);

            groupNumberTextField.setVisible(true);

            attTable.getItems().clear();

            groupNumberTextField.setOnKeyPressed(event -> {
                if( event.getCode() == KeyCode.ENTER ) {
                    String groupNum = groupNumberTextField.getText().trim();
                    studentComboBox.setVisible(true);
                    subjBox.setVisible(true);
                    initNames(groupNum);
                    initSubjs();
                    studentComboBox.getItems().addAll(nameList);
                    subjBox.getItems().addAll(subjList);
                    showButton.setVisible(true);


                }
            });
        });
        showButton.setOnAction(actionEvent -> {
            attTable.getItems().clear();
            tmp.setName(studentComboBox.getValue());
            tmp.setSubject(subjBox.getValue());
            initAtt(tmp.getName(), tmp.getSubject());
            first_col.setCellValueFactory(new PropertyValueFactory<>("f_att"));
            second_col.setCellValueFactory(new PropertyValueFactory<>("s_att"));
            third_col.setCellValueFactory(new PropertyValueFactory<>("t_att"));
            test_col.setCellValueFactory(new PropertyValueFactory<>("test"));
            exam_col.setCellValueFactory(new PropertyValueFactory<>("exam"));
            attTable.setItems(grData);
            attTable.setVisible(true);
        });

        //schedule
        initSch();
        lesType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        buil_col.setCellValueFactory(new PropertyValueFactory<>("building"));
        room_col.setCellValueFactory(new PropertyValueFactory<>("room"));
        day_col.setCellValueFactory(new PropertyValueFactory<>("day"));
        sub_col.setCellValueFactory(new PropertyValueFactory<>("subj"));
        group_col.setCellValueFactory(new PropertyValueFactory<>("grpNum"));
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        sch_Tab.setItems(schData);
        scheduleTButton.setOnAction(actionEvent -> {
            sch_Tab.setVisible(true);
            attTable.setVisible(false);
            groupNumberTextField.setVisible(false);
            studentComboBox.setVisible(false);

        });

    }
    private void initNames(String num) {
        dbHandler handler = new dbHandler();
        ResultSet res = handler.getItem("SELECT fio FROM student JOIN sgroup ON groupNumber=" + num + " ORDER BY studentpk");
        try{
            while (res.next()) {
                nameList.add(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void initSubjs() {
        dbHandler handler = new dbHandler();
        ResultSet res = handler.getItem("SELECT name from subject JOIN grade ON subjectfk=subjectpk AND teacherfk=" + teacher.getTeacherpk());
        try{
           while(res.next()) {
               if(!subjList.contains(res.getString(1)))
               subjList.add(res.getString(1));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void initAtt(String name, String subject) {
        tGrade grade;
        dbHandler handler = new dbHandler();
        ResultSet res = handler.getItem("SELECT gradepk, firstatt, secondatt, thirdatt, test, " +
                "exam FROM grade JOIN student ON studentfk=studentpk AND fio=\"" + name
                + "\""  +" JOIN subject ON name=\"" + subject +
                "\"" +" WHERE teacherfk=" + teacher.getTeacherpk());
        try{
            while (res.next()) {

                grade = new tGrade(res.getString(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5),
                        res.getString(6));
                grData.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void initSch() {
        tSchedule schedule;
        dbHandler handler = new dbHandler();
        ResultSet result = handler.getItem("SELECT day, time, week, sgroup.groupNumber," +
                " type, subject.name, room.number, room.building" +
                " FROM lesson, subject, room, sgroup WHERE roomfk=roompk AND" +
                " subjectpk=subjectfk AND sgroupfk=sgrouppk AND teacherfk=" + teacher.getTeacherpk());

        try {
            while(result.next()) {
                schedule = new tSchedule(result.getString(1), result.getString(2) +
                        " " + result.getString(3),
                        result.getString(4), result.getString(5),
                        result.getString(6), result.getString(7),
                        result.getString(8));
                schData.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void editGrade(String pk, String value, int column) {
        dbHandler handler = new dbHandler();
        if(column == 1) {
            handler.editCell("UPDATE grade SET firstatt=" + Integer.parseInt(value) + " WHERE gradepk=" + pk);
        }
        if(column == 2) {
            handler.editCell("UPDATE grade SET secondatt=" + Integer.parseInt(value) + " WHERE gradepk=" + pk);
        }
        if(column == 3) {
            handler.editCell("UPDATE grade SET thirdatt=" + Integer.parseInt(value) + " WHERE gradepk=" + pk);
        }
        if(column == 4) {
            handler.editCell("UPDATE grade SET test=" + Integer.parseInt(value) + " WHERE gradepk=" + pk);
        }
        if(column == 5) {
            handler.editCell("UPDATE grade SET exam=" + Integer.parseInt(value) + " WHERE gradepk=" + pk);
        }
    }
}
