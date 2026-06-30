package duoc.Evaluacion;

import duoc.Evaluacion.model.Evaluacion;
import duoc.Evaluacion.repository.EvaluacionRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        String[] tipos = {"Examen", "Control", "Tarea", "Proyecto", "Presentación"};

        if (evaluacionRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Evaluacion evaluacion = new Evaluacion();

                evaluacion.setNombreEvaluacion(faker.educator().course() + " - Evaluación " + (random.nextInt(3) + 1));
                evaluacion.setTipoEvaluacion(tipos[random.nextInt(tipos.length)]);
                evaluacion.setPonderacion((double) (random.nextInt(4) + 1) * 10);
                evaluacion.setFechaEvaluacion(LocalDate.now().plusDays(random.nextInt(30) + 1));
                evaluacion.setCursoId((long) (random.nextInt(5) + 1));

                evaluacionRepository.save(evaluacion);
            }
            System.out.println(">> ¡DataLoader de Evaluacion ejecutado con éxito! 10 registros creados.");
        }
    }
}