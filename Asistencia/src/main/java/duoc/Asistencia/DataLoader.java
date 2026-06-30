package duoc.Asistencia;

import duoc.Asistencia.model.Asistencia;
import duoc.Asistencia.repository.AsistenciaRepository;
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
    private AsistenciaRepository asistenciaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Asistencia asistencia = new Asistencia();

            asistencia.setFecha(new java.util.Date(System.currentTimeMillis() - (long)(random.nextInt(365)) * 24 * 60 * 60 * 1000));
            asistencia.setEstadoAsistencia(faker.options().option("Presente", "Ausente", "Justificado"));
            asistencia.setEstudianteId((long) faker.number().numberBetween(1, 10));
            asistencia.setCursoId((long) faker.number().numberBetween(1, 10));

            asistenciaRepository.save(asistencia);
        }
    }
}