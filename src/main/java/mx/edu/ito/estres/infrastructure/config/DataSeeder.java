package mx.edu.ito.estres.infrastructure.config;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Role;
import mx.edu.ito.estres.domain.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final StudentRepositoryPort studentRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            StudentRepositoryPort studentRepositoryPort,
            PasswordEncoder passwordEncoder
    ) {
        this.studentRepositoryPort = studentRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        createAdmin();

        createDemoStudent();
    }

    private void createAdmin() {

        String adminEmail =
                "admin@stressmonitor.com";

        if(studentRepositoryPort
                .findByEmail(adminEmail)
                .isEmpty()) {

            Student admin = new Student(
                    null,
                    adminEmail,
                    passwordEncoder.encode("admin123"),
                    0,
                    "SYSTEM",
                    0,
                    true,
                    Role.ADMIN
            );

            studentRepositoryPort.save(admin);

            System.out.println("""
                    
========================
ADMIN USER CREATED
email: admin@stressmonitor.com
password: admin123
========================
                    
""");
        }
    }

    private void createDemoStudent() {

        String studentEmail =
                "student@test.com";

        if(studentRepositoryPort
                .findByEmail(studentEmail)
                .isEmpty()) {

            Student student = new Student(
                    null,
                    studentEmail,
                    passwordEncoder.encode("student123"),
                    4,
                    "LOCAL",
                    7,
                    true,
                    Role.STUDENT
            );

            studentRepositoryPort.save(student);

            System.out.println("""
                    
========================
DEMO STUDENT CREATED
email: student@test.com
password: student123
========================
                    
""");
        }
    }
}
