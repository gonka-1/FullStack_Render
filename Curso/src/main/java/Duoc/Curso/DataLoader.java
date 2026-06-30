package Duoc.Curso;

import Duoc.Curso.model.Curso;
import Duoc.Curso.repository.CursoRepository;
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
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();

            curso.setNombre(faker.educator().course());
            curso.setProfesorId((long) faker.number().numberBetween(1, 10));
            curso.setCarreraId((long) faker.number().numberBetween(1, 10));

            cursoRepository.save(curso);
        }
    }
}