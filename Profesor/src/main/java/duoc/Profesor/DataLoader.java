package duoc.Profesor;

import duoc.Profesor.Repository.ProfesorRepository;
import duoc.Profesor.model.Profesor;
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
    private ProfesorRepository profesorRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Profesor profesor = new Profesor();

            profesor.setRun(faker.idNumber().valid());
            profesor.setNombre(faker.name().fullName());
            profesor.setEspecialidad(faker.educator().course());

            profesorRepository.save(profesor);
        }
    }
}