package duoc.Seccion;

import duoc.Seccion.model.Seccion;
import duoc.Seccion.repository.SeccionRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        if (seccionRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Seccion seccion = new Seccion();

                seccion.setCodigoSeccion(faker.regexify("[A-Z]{3}-[0-9]{4}"));

                seccion.setSemestre(String.valueOf(random.nextInt(2) + 1));

                seccion.setAnio(2026);

                seccion.setCupoMaximo(20 + random.nextInt(26));

                seccion.setCursoId((long) (random.nextInt(5) + 1));
                seccion.setProfesorId((long) (random.nextInt(5) + 1));

                seccionRepository.save(seccion);
            }
            System.out.println(">> ¡DataLoader de Seccion ejecutado con éxito! 10 registros creados.");
        }
    }
}