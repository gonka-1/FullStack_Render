package duoc.Estudiante;

import duoc.Estudiante.model.Estudiante;
import duoc.Estudiante.repository.EstudianteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            Estudiante estudiante = new Estudiante();

            estudiante.setRun(faker.idNumber().valid());
            estudiante.setNombre(faker.name().fullName());
            estudiante.setEmail(faker.internet().emailAddress());
            estudiante.setCarreraId((long) random.nextInt(5) + 1);

            estudianteRepository.save(estudiante);
        }
    }
}

