package model.dao;

import model.dto.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class Dao {
    private SessionFactory sessionFactory;

    private static Dao dao;

    public Dao() {
        Configuration configuration = new Configuration();
        Properties properties=new Properties();
        properties.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
        properties.put(Environment.URL,"jdbc:mysql://localhost:3306/collegemanagement");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "forzaeu1987");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "false");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(DateModel.class);
        configuration.addAnnotatedClass(PrezentModel.class);
        configuration.addAnnotatedClass(GroupModel.class);
        configuration.addAnnotatedClass(StudentModel.class);
        configuration.addAnnotatedClass(ProfessorModel.class);
        configuration.addAnnotatedClass(SubgroupModel.class);
        configuration.addAnnotatedClass(ClassroomModel.class);
        configuration.addAnnotatedClass(CourseModel.class);
        configuration.addAnnotatedClass(EvidenceModel.class);
        configuration.addAnnotatedClass(ScheduleModel.class);
        configuration.addAnnotatedClass(AdminModel.class);
        sessionFactory=configuration.buildSessionFactory();

    }

    public static Dao getInstance(){
        if (dao==null){
            dao=new Dao();
        }
        return dao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
