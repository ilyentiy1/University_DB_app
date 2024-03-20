package com.example.myapp;

import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.myapp.Controller.student;


public class studentController {


    //ведомость
    @FXML
    private Button gradeButton;
    @FXML
    private TableView<Grade> gradeTable;
    @FXML
    public TableColumn<Grade, String> subjectColumn;
    @FXML
    private TableColumn<Grade, Integer> firstattColumn;
    @FXML
    private TableColumn<Grade, Integer> secondattColumn;
    @FXML
    private TableColumn<Grade, Integer> thirdattColumn;
    @FXML
    private TableColumn<Grade, Integer> testColumn;
    @FXML
    private TableColumn<Grade,Integer> examColumn;
    @FXML
    private TableColumn<Grade,Integer> sumColumn;
    private final ObservableList<Grade> gradeData = FXCollections.observableArrayList();


    //расписание
    @FXML
    private Button scheduleButton;
    @FXML
    private TableView<Schedule> scheduleTable;
    @FXML
    private TableColumn<Schedule, String> subj_col;
    @FXML
    private TableColumn<Schedule, String> buildingColumn;
    @FXML
    private TableColumn<Schedule, String> departmentColumn;
    @FXML
    private TableColumn<Schedule, String> lessonTypeColumn;
    @FXML
    private TableColumn<Schedule, String> roomColumn;
    @FXML
    private TableColumn<Schedule, String> timeColumn;
    @FXML
    private TableColumn<Schedule, String> weekColumn;
    @FXML
    private TableColumn<Schedule, String> dayColumn;
    @FXML
    private TableColumn<Schedule, String> teacherColumn;
    private final ObservableList<Schedule> scheduleData = FXCollections.observableArrayList();


    //Преподаватели
    @FXML
    private Button teacherInfoButton;
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, String> teacherNameColumn;
    @FXML
    private TableColumn<Teacher, String> subjectTColumn;
    @FXML
    private TableColumn<Teacher, String> lessonTypeTColumn;
    @FXML
    private TableColumn<Teacher, String> teacherEmailColumn;
    private final ObservableList<Teacher> teacherData = FXCollections.observableArrayList();

    //Группа
    @FXML
    private Button sgroupButton;
    @FXML
    private TableView<sgroup> groupInfoTable;
    @FXML
    private TableColumn<sgroup, String> studentNameColumn;
    @FXML
    public TableColumn<sgroup, String> StudentEmailColumn;
    private final ObservableList<sgroup> groupData = FXCollections.observableArrayList();

    dbHandler handler = new dbHandler();

    @FXML
    void initialize() {
        //аттестация
        initGradeData();
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        firstattColumn.setCellValueFactory(new PropertyValueFactory<>("firstatt"));
        secondattColumn.setCellValueFactory(new PropertyValueFactory<>("secondatt"));
        thirdattColumn.setCellValueFactory(new PropertyValueFactory<>("thirdatt"));
        testColumn.setCellValueFactory(new PropertyValueFactory<>("test"));
        examColumn.setCellValueFactory(new PropertyValueFactory<>("exam"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("endSum"));
        gradeTable.setItems(gradeData);

        //расписание
        initSchedule();
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));
        subj_col.setCellValueFactory(new PropertyValueFactory<>("subject"));
        lessonTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));

        scheduleTable.setItems(scheduleData);

        //teachers
        initTeacher();
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        lessonTypeTColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        subjectTColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        teacherEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        teacherTable.setItems(teacherData);

        //groupinfo
        initGroup();
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        StudentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        groupInfoTable.setItems(groupData);
        groupInfoTable.setItems(groupData);
        scheduleButton.setOnAction(actionEvent -> {
            gradeTable.setVisible(false);
            groupInfoTable.setVisible(false);
            teacherTable.setVisible(false);
            scheduleTable.setVisible(!scheduleTable.isVisible());

        });

        gradeButton.setOnAction(actionEvent -> {
            groupInfoTable.setVisible(false);
            teacherTable.setVisible(false);
            scheduleTable.setVisible(false);
            gradeTable.setVisible(!gradeTable.isVisible());
        });

        teacherInfoButton.setOnAction(actionEvent -> {
            gradeTable.setVisible(false);
            groupInfoTable.setVisible(false);
            scheduleTable.setVisible(false);
            teacherTable.setVisible(!teacherTable.isVisible());
        });

        sgroupButton.setOnAction(actionEvent -> {
            scheduleTable.setVisible(false);
            teacherTable.setVisible(false);
            gradeTable.setVisible(false);
            groupInfoTable.setVisible(!groupInfoTable.isVisible());
        });
    }
    private void initGradeData() {

        Grade grade;
        ResultSet result = handler.getItem("SELECT subjectfk, firstatt, secondatt, thirdatt, test, exam" +
                "  FROM grade WHERE studentfk=" + student.getStudentpk());

        try {
            while(result.next()) {
                String subj = null;
                ResultSet result2 = handler.getItem("SELECT name FROM subject WHERE subjectpk=" + result.getString(1));
                while(result2.next()) {
                    subj = result2.getString(1);
                }
                grade = new Grade(subj,
                        result.getInt(2),
                        result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getInt(6), result.getInt(2)+
                        result.getInt(3)+ result.getInt(4)+
                        result.getInt(5)+ result.getInt(6));
                gradeData.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initSchedule() {
        Schedule schedule;
        dbHandler handler = new dbHandler();
        ResultSet result = handler.getItem("SELECT * FROM lesson WHERE sgroupfk=" + student.getSgroupfk());
        try {
            while(result.next()) {
                String department = null;
                String subject = null;
                String room = null;
                String building = null;
                String teacher = null;
                ResultSet result2 = handler.getItem("SELECT fio, departmentfk FROM teacher WHERE teacherpk=" +
                        result.getString(4));
                while(result2.next()) {
                    teacher = result2.getString(1);
                    ResultSet result3 = handler.getItem("SELECT name FROM department WHERE departmentpk=" +
                            result2.getString(2));
                    while (result3.next()) {
                        department = result3.getString(1);
                    }
                }
                ResultSet result5 = handler.getItem("SELECT name FROM subject WHERE subjectpk=" +
                        result.getString(2));
                while (result5.next()) {
                    subject = result5.getString(1);
                }
                ResultSet res6 = handler.getItem("SELECT number, building FROM room WHERE roompk=" +
                        result.getString(3));
                while(res6.next()) {
                    room = res6.getString(1);
                    building = res6.getString(2);
                }
                schedule = new Schedule(subject, room,
                        teacher, result.getString(5),
                        result.getString(6), result.getString(7),
                        result.getString(8), result.getString(9), department, building );
                scheduleData.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTeacher() {
        Teacher teacher;
        dbHandler handler = new dbHandler();
        ResultSet result = handler.getItem("SELECT * FROM lesson");
        try{
            while(result.next()) {
                String FIO = null;
                String subj = null;
                String email = null;
                ResultSet result2 = handler.getItem("SELECT fio, email FROM teacher WHERE teacherpk=" + result.getString(4));
                while(result2.next()) {
                    FIO = result2.getString(1);
                    email = result2.getString(2);
                }
                ResultSet result3 = handler.getItem("SELECT name FROM subject WHERE subjectpk=" + result.getString(2));
                while(result3.next()) {
                    subj = result3.getString(1);
                }
                teacher = new Teacher(FIO, subj, result.getString(6), email);
                if(teacherData.size() == 0) {
                    teacherData.add(teacher);
                }
                if (isUnique(teacher, teacherData)) {
                    teacherData.add(teacher);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initGroup() {
        sgroup group;
        dbHandler handler = new dbHandler();
        ResultSet result = handler.getItem("SELECT * FROM student WHERE sgroupfk=" + student.getSgroupfk());
        try{
            while(result.next()) {
                group = new sgroup(result.getString(3), result.getString(4));
                groupData.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUnique(Teacher teacher, ObservableList<Teacher> list) {
        boolean flag = true;
        for(Teacher i : list) {
            if (i.getSubject().equals(teacher.getSubject()) && i.getType().equals(teacher.getType())) {
                flag = false;
                break;
            }
        }
        return flag;
    }


}

