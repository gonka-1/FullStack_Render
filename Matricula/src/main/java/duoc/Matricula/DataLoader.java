package duoc.Matricula;

import duoc.Matricula.model.Matricula;
import duoc.Matricula.repository.MatriculaRepository;
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
    private MatriculaRepository matriculaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Matricula matricula = new Matricula();

            matricula.setEstudianteId((long) faker.number().numberBetween(1, 10));
            matricula.setCursoId((long) faker.number().numberBetween(1, 10));
            matricula.setCalificacion(faker.options().option("1.0", "2.0", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "7.0"));

            matriculaRepository.save(matricula);
        }
    }
}