package Duoc.Nota;

import Duoc.Nota.models.Nota;
import Duoc.Nota.repository.NotaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private NotaRepository notaRepository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        if (notaRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Nota nota = new Nota();

                nota.setMatriculaId((long) (i + 1));

                double calificacionAleatoria = 1.0 + (random.nextDouble() * 6.0);
                double calificacionRedondeada = Math.round(calificacionAleatoria * 10.0) / 10.0;

                nota.setCalificacion(calificacionRedondeada);

                notaRepository.save(nota);
            }
            System.out.println(">> ¡DataLoader de Nota ejecutado con éxito! 10 registros creados.");
        }
    }
}