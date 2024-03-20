package com.example.myapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.input.KeyCode;


public class adminController {
    //changing schedule
    @FXML
    private MenuItem ChangeScheduleItem;
    @FXML
    private TableView<Schedule> schTable;
    @FXML
    private TableColumn<Schedule, String> time_col;
    @FXML
    private TableColumn<Schedule, String> type_col;
    @FXML
    private TableColumn<Schedule, String> week_col;
    @FXML
    private TableColumn<Schedule, String> subject_col;
    @FXML
    private TableColumn<Schedule, String> schgroup_col;
    @FXML
    private TableColumn<Schedule, String> room_col;
    @FXML
    private TableColumn<Schedule, String> day_col;
    @FXML
    private TableColumn<Schedule, String> schTeacher_col;
    private final ObservableList<Schedule> schList = FXCollections.observableArrayList();



    @FXML
    private Button refreshButton;
    @FXML
    private Button deleteButton;
    @FXML
    private MenuItem changeStudentInfo;
    //изменить информацию о студентах либо удалить запись
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> group_col;
    @FXML
    private TableColumn<Student, String> email_col;
    @FXML
    private TableColumn<Student, String> fio_col;
    @FXML
    private TextField groupNumField;
    @FXML
    private TableColumn<Student, String> login_col;
    @FXML
    private TableColumn<Student, String> pass_col;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    private MenuItem changeTeacherInfo;
    //изменить информацию о преподавателях либо удалить запись
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, String> degree_col;
    @FXML
    private TableColumn<Teacher, String> dep_col;
    @FXML
    private TableColumn<Teacher, String> post_col;
    @FXML
    private TableColumn<Teacher, String> tEmail_col;
    @FXML
    private TableColumn<Teacher, String> tFIO_col;
    @FXML
    private TableColumn<Teacher, String> tLogin_col;
    @FXML
    private TableColumn<Teacher, String> tPass_col;
    private final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();


    @FXML
    private MenuItem createStudentItem;

    @FXML
    private MenuItem createTeacherItem;


    //create student
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField groupField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField patronimicField;
    private final ObservableList<sgroup> groupList = FXCollections.observableArrayList();


    //create teacher
    @FXML
    private TextField tEmailField;
    @FXML
    private TextField tLoginField;
    @FXML
    private TextField tNameField;
    @FXML
    private PasswordField tPasswordField;
    @FXML
    private TextField tPatronimicField;
    @FXML
    private TextField tSurnameField;
    @FXML
    private TextField postField;
    @FXML
    private TextField degreeField;
    @FXML
    private ComboBox<String> departmentBox;
    private final ObservableList<Department> depList = FXCollections.observableArrayList();

    @FXML
    private Button createButton;

    @FXML
    void initialize() {

        Callback<TableColumn<Student, String>, TableCell<Student,
                String>> cellFactory = p -> new editingStudent();
        group_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setGroupNumber(t.getNewValue());
                    //schData.get(0);
                    editSTable(t.getRowValue().getStudentpk(), t.getNewValue(), 1);
                });
        group_col.setCellFactory(cellFactory);

        fio_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setFio(t.getNewValue());
                    editSTable(t.getRowValue().getStudentpk(), t.getNewValue(), 2);
                });
        fio_col.setCellFactory(cellFactory);
        email_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    editSTable(t.getRowValue().getStudentpk(), t.getNewValue(), 3);
                });
        email_col.setCellFactory(cellFactory);
        login_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setLogin(t.getNewValue());
                    editSTable(t.getRowValue().getStudentpk(), t.getNewValue(), 4);
                });
        login_col.setCellFactory(cellFactory);
        pass_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassword(t.getNewValue());
                    editSTable(t.getRowValue().getStudentpk(), t.getNewValue(), 5);
                });
        pass_col.setCellFactory(cellFactory);

        //changing teacher cells
        Callback<TableColumn<Teacher, String>, TableCell<Teacher,
                String>> tcellFactory = p -> new editingTeacher();
        dep_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDepartment(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 1);
                });
        dep_col.setCellFactory(tcellFactory);

        tFIO_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setFIO(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 2);
                });
        tFIO_col.setCellFactory(tcellFactory);

        degree_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDegree(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 3);
                });
        degree_col.setCellFactory(tcellFactory);

        post_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPost(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 4);
                });
        post_col.setCellFactory(tcellFactory);

        tEmail_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 5);
                });
        tEmail_col.setCellFactory(tcellFactory);

        tLogin_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setLogin(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 6);
                });
        tLogin_col.setCellFactory(tcellFactory);

        tPass_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Teacher, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassword(t.getNewValue());
                    editTTable(t.getRowValue().getTeacherpk(), t.getNewValue(), 7);
                });
        tPass_col.setCellFactory(tcellFactory);

        //changing schedule
        Callback<TableColumn<Schedule, String>, TableCell<Schedule,
                String>> scellFactory = p -> new editingSchedule();
        schgroup_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setSgroup(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 1);
                });
        schgroup_col.setCellFactory(scellFactory);

        schTeacher_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setTeacher(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 2);
                });
        schTeacher_col.setCellFactory(scellFactory);

        time_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setTime(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 3);
                });
        time_col.setCellFactory(scellFactory);

        type_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setType(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 4);
                });
        type_col.setCellFactory(scellFactory);

        day_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDay(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 5);
                });
        day_col.setCellFactory(scellFactory);

        week_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setWeek(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 6);
                });
        week_col.setCellFactory(scellFactory);

        subject_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setSubject(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 7);
                });
        subject_col.setCellFactory(scellFactory);

        room_col.setOnEditCommit(
                (TableColumn.CellEditEvent<Schedule, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setRoom(t.getNewValue());
                    editSch(t.getRowValue().getLessonpk(), t.getNewValue(), 8);
                });
        room_col.setCellFactory(scellFactory);




        createStudentItem.setOnAction(actionEvent -> {
            tSurnameField.setVisible(false);
            tNameField.setVisible(false);
            tPatronimicField.setVisible(false);
            tEmailField.setVisible(false);
            degreeField.setVisible(false);
            postField.setVisible(false);
            departmentBox.setVisible(false);
            tPasswordField.setVisible(false);
            tLoginField.setVisible(false);
            studentTable.setVisible(false);
            teacherTable.setVisible(false);
            schTable.setVisible(false);
            deleteButton.setVisible(false);
            refreshButton.setVisible(false);

            fillGroupList();
            surnameField.setVisible(true);
            nameField.setVisible(true);
            patronimicField.setVisible(true);
            groupField.setVisible(true);
            emailField.setVisible(true);
            loginField.setVisible(true);
            passwordField.setVisible(true);
            createButton.setVisible(true);

        });
        createTeacherItem.setOnAction(actionEvent -> {
            surnameField.setVisible(false);
            nameField.setVisible(false);
            patronimicField.setVisible(false);
            groupField.setVisible(false);
            emailField.setVisible(false);
            loginField.setVisible(false);
            passwordField.setVisible(false);
            studentTable.setVisible(false);
            teacherTable.setVisible(false);
            schTable.setVisible(false);
            deleteButton.setVisible(false);
            refreshButton.setVisible(false);

            tSurnameField.setVisible(true);
            tNameField.setVisible(true);
            tPatronimicField.setVisible(true);
            tEmailField.setVisible(true);
            degreeField.setVisible(true);
            postField.setVisible(true);
            departmentBox.setVisible(true);
            tPasswordField.setVisible(true);
            tLoginField.setVisible(true);
            createButton.setVisible(true);
            fillDepBox();

        });
        createButton.setOnAction(actionEvent -> {
            if(surnameField.isVisible()) {
                createStudent();
                surnameField.clear();
                nameField.clear();
                passwordField.clear();
                patronimicField.clear();
                groupField.clear();
                emailField.clear();
                loginField.clear();
            }
            if(tSurnameField.isVisible()) {
                createTeacher();
                tSurnameField.clear();
                tNameField.clear();
                tPatronimicField.clear();
                tEmailField.clear();
                degreeField.clear();
                postField.clear();
                tPasswordField.clear();
                tLoginField.clear();
            }
        });

        changeStudentInfo.setOnAction(actionEvent -> {
            tSurnameField.setVisible(false);
            tNameField.setVisible(false);
            tPatronimicField.setVisible(false);
            tEmailField.setVisible(false);
            degreeField.setVisible(false);
            postField.setVisible(false);
            departmentBox.setVisible(false);
            tPasswordField.setVisible(false);
            tLoginField.setVisible(false);
            createButton.setVisible(false);
            surnameField.setVisible(false);
            nameField.setVisible(false);
            patronimicField.setVisible(false);
            groupField.setVisible(false);
            emailField.setVisible(false);
            loginField.setVisible(false);
            passwordField.setVisible(false);
            teacherTable.setVisible(false);
            schTable.setVisible(false);

            refreshButton.setVisible(true);
            deleteButton.setVisible(true);
            studentTable.setVisible(true);
            groupNumField.setVisible(true);

            group_col.setCellValueFactory(new PropertyValueFactory<>("groupNumber"));
            fio_col.setCellValueFactory(new PropertyValueFactory<>("fio"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            login_col.setCellValueFactory(new PropertyValueFactory<>("login"));
            pass_col.setCellValueFactory(new PropertyValueFactory<>("password"));


        });
        groupNumField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER ) {
                studentTable.getItems().clear();
                initStudent(groupNumField.getText().trim());
                studentTable.setItems(studentList);
            }
        });

        changeTeacherInfo.setOnAction(actionEvent -> {
            groupNumField.setVisible(false);
            tSurnameField.setVisible(false);
            tNameField.setVisible(false);
            tPatronimicField.setVisible(false);
            tEmailField.setVisible(false);
            degreeField.setVisible(false);
            postField.setVisible(false);
            departmentBox.setVisible(false);
            tPasswordField.setVisible(false);
            tLoginField.setVisible(false);
            createButton.setVisible(false);
            surnameField.setVisible(false);
            nameField.setVisible(false);
            patronimicField.setVisible(false);
            groupField.setVisible(false);
            emailField.setVisible(false);
            loginField.setVisible(false);
            passwordField.setVisible(false);
            studentTable.setVisible(false);
            schTable.setVisible(false);

            teacherTable.getItems().clear();
            refreshButton.setVisible(true);
            deleteButton.setVisible(true);
            teacherTable.setVisible(true);
            initTeacher();
            dep_col.setCellValueFactory(new PropertyValueFactory<>("department"));
            tFIO_col.setCellValueFactory(new PropertyValueFactory<>("FIO"));
            degree_col.setCellValueFactory(new PropertyValueFactory<>("degree"));
            post_col.setCellValueFactory(new PropertyValueFactory<>("post"));
            tEmail_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            tLogin_col.setCellValueFactory(new PropertyValueFactory<>("login"));
            tPass_col.setCellValueFactory(new PropertyValueFactory<>("password"));
            teacherTable.setItems(teacherList);
        });

        refreshButton.setOnAction(actionEvent -> {
            if(teacherTable.isVisible()) {
                teacherTable.getItems().clear();
                teacherList.clear();
                initTeacher();
                teacherTable.setItems(teacherList);
            }
            if(studentTable.isVisible()) {
                studentTable.getItems().clear();
                studentList.clear();
                initStudent(groupNumField.getText().trim());
                studentTable.setItems(studentList);
            }
            if(schTable.isVisible()) {
                schTable.getItems().clear();
                schList.clear();
                initSchedule();
                schTable.setItems(schList);
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            if(studentTable.isVisible()) {

            }
            if(teacherTable.isVisible()) {

            }
            if(schTable.isVisible()) {

            }
        });

        ChangeScheduleItem.setOnAction(actionEvent -> {
            groupNumField.setVisible(false);
            tSurnameField.setVisible(false);
            tNameField.setVisible(false);
            tPatronimicField.setVisible(false);
            tEmailField.setVisible(false);
            degreeField.setVisible(false);
            postField.setVisible(false);
            departmentBox.setVisible(false);
            tPasswordField.setVisible(false);
            tLoginField.setVisible(false);
            createButton.setVisible(false);
            surnameField.setVisible(false);
            nameField.setVisible(false);
            patronimicField.setVisible(false);
            groupField.setVisible(false);
            emailField.setVisible(false);
            loginField.setVisible(false);
            passwordField.setVisible(false);
            studentTable.setVisible(false);
            teacherTable.setVisible(false);

            schTable.setVisible(true);
            deleteButton.setVisible(true);
            refreshButton.setVisible(true);
            initSchedule();
            schgroup_col.setCellValueFactory(new PropertyValueFactory<>("sgroup"));
            room_col.setCellValueFactory(new PropertyValueFactory<>("room"));
            subject_col.setCellValueFactory(new PropertyValueFactory<>("subject"));
            time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
            type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
            week_col.setCellValueFactory(new PropertyValueFactory<>("week"));
            day_col.setCellValueFactory(new PropertyValueFactory<>("day"));
            schTeacher_col.setCellValueFactory(new PropertyValueFactory<>("teacher"));

            schTable.setItems(schList);
        });

    }
    private void fillGroupList() {
        dbHandler handler = new dbHandler();
        sgroup group;
        ResultSet res = handler.getItem("SELECT sgrouppk, groupNumber FROM sgroup");
        try {
            while (res.next()) {
                group = new sgroup(res.getInt(2), res.getString(1));
                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createStudent() {
        dbHandler handler = new dbHandler();
        Student student = new Student();
        student.setFio(surnameField.getText() + " " + nameField.getText() + " " + patronimicField.getText());
        student.setLogin(loginField.getText());
        student.setPassword(passwordField.getText());
        student.setEmail(emailField.getText());
        student.setGroupNumber(groupField.getText());
        handler.insertStudent(student, groupList);
    }
    private void fillDepBox() {
        dbHandler handler = new dbHandler();
        Department department;
        ResultSet res1 = handler.getItem("SELECT departmentpk, facultyfk, name FROM department");
        try {
            while (res1.next()) {
                department = new Department(res1.getString(1), res1.getString(2), res1.getString(3));
                depList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Department i : depList) {
            departmentBox.getItems().add(i.getName());
        }
    }
    private void createTeacher()  {
        dbHandler handler = new dbHandler();
        Teacher teacher = new Teacher();
        teacher.setFIO(tSurnameField.getText() + " " + tNameField.getText() + " " + tPatronimicField.getText());
        teacher.setDegree(degreeField.getText());
        teacher.setPost(postField.getText());
        teacher.setDepartment(departmentBox.getValue());
        teacher.setPassword(tPasswordField.getText());
        teacher.setLogin(tLoginField.getText());
        teacher.setDepartment(departmentBox.getValue());
        teacher.setEmail(tEmailField.getText());
        handler.insertTeacher(teacher, depList);
    }

    private void initStudent(String grpNum) {
        dbHandler handler = new dbHandler();
        Student student;
        ResultSet res = handler.getItem("SELECT student.studentpk, sgroup.groupNumber, student.fio," +
                " student.email, student.login, student.password FROM" +
                " student, sgroup WHERE sgrouppk=sgroupfk AND groupNumber=" + grpNum + ";");
        try{
            while(res.next()) {
                student = new Student(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), 0);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTeacher() {
        dbHandler handler = new dbHandler();
        ResultSet res = handler.getItem("SELECT department.name, teacher.teacherpk, teacher.fio," +
                " teacher.post, teacher.degree, teacher.email, teacher.login, teacher.password FROM teacher," +
                " department WHERE departmentpk=departmentfk;");
        Teacher teacher;
        try{
            while(res.next()) {
                teacher = new Teacher(res.getString(1), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8));
                teacherList.add(teacher);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }


    }
    private void editSTable(String pk, String value, int column) {
        dbHandler handler = new dbHandler();
        //должен происходить апдейт внешнего ключа группы в сущности Студент
        if(column == 1) {
            handler.editCell("UPDATE student SET groupfk=" + value + " WHERE studentpk=" + pk);
        }
        if(column == 2) {
            handler.editCell("UPDATE student SET fio=" + value + " WHERE studentpk=" + pk);
        }
        if(column == 3) {
            handler.editCell("UPDATE student SET email=" + value + " WHERE studentpk=" + pk);
        }
        if(column == 4) {
            handler.editCell("UPDATE student SET login=" + value + " WHERE studentpk=" + pk);
        }
        if(column == 5) {
            handler.editCell("UPDATE student SET password=" + value + " WHERE studentpk=" + pk);
        }
    }
    private void editTTable(String pk, String value, int column) {
        dbHandler handler = new dbHandler();
        //что-то сделать с изменением кафедры
        if(column == 1) {
            handler.editCell("UPDATE teacher SET fio=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 2) {
            handler.editCell("UPDATE teacher SET departmentfk=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 3) {
            handler.editCell("UPDATE teacher SET post=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 4) {
            handler.editCell("UPDATE teacher SET degree=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 5) {
            handler.editCell("UPDATE teacher SET email=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 6) {
            handler.editCell("UPDATE teacher SET login=" + value + " WHERE teacherpk=" + pk);
        }
        if(column == 7) {
            handler.editCell("UPDATE teacher SET password=" + value + " WHERE teacherpk=" + pk);
        }
    }
    private void deleteItem() {
        dbHandler handler = new dbHandler();

    }
    private void initSchedule() {
        dbHandler handler = new dbHandler();
        Schedule schedule;
        ResultSet res = handler.getItem("SELECT lesson.lessonpk, lesson.subjectfk, lesson.roomfk," +
                " lesson.teacherfk, lesson.sgroupfk, lesson.type, lesson.week, lesson.time, lesson.day," +
                " room.number, subject.name, teacher.fio, sgroup.groupNumber, room.building  from lesson, room, sgroup," +
                " subject, teacher WHERE roompk=roomfk AND sgrouppk=sgroupfk AND subjectpk=subjectfk" +
                " AND teacherpk=teacherfk;");
        try{
            while(res.next()) {
                schedule = new Schedule(res.getString(1), res.getString(5),
                        res.getString(3), res.getString(4),res.getString(2),
                        res.getString(11), res.getString(10), res.getString(12),
                        res.getString(13), res.getString(6), res.getString(7),
                        res.getString(8), res.getString(9), res.getString(14));
                schList.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void editSch(String pk, String value, int column) {
        dbHandler handler = new dbHandler();
        if(column == 1) {
            handler.editCell("UPDATE lesson SET subjectfk=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 2) {
            handler.editCell("UPDATE lesson SET roomfk=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 3) {
            handler.editCell("UPDATE lesson SET teacherfk=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 4) {
            handler.editCell("UPDATE lesson SET sgroupfk=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 5) {
            handler.editCell("UPDATE lesson SET type=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 6) {
            handler.editCell("UPDATE lesson SET week=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 7) {
            handler.editCell("UPDATE lesson SET time=" + value + " WHERE lessonpk=" + pk);
        }
        if(column == 8) {
            handler.editCell("UPDATE lesson SET day=" + value + " WHERE lessonpk=" + pk);
        }
    }
}
