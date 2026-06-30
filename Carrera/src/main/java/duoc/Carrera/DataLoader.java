package duoc.Carrera;


import duoc.Carrera.model.Carrera;
import duoc.Carrera.repository.CarreraRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public void run(String... args) throws Exception {
        // Configuramos Faker en español
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();
        String[] estados = {"Activa", "En Cierre", "Inactiva"};

        // Solo poblar si la tabla está vacía
        if (carreraRepository.count() == 0) {
            for (int i = 0; i < 15; i++) { // Generaremos 15 carreras aleatorias
                Carrera carrera = new Carrera();

                // Faker tiene un módulo 'educator' que genera nombres de cursos/carreras
                carrera.setNombre(faker.educator().course());
                carrera.setCodigoCarrera(faker.number().numberBetween(1000, 9999));
                // Generamos una duración aleatoria entre 4 y 10 semestres
                carrera.setDuracionSemestre(faker.number().numberBetween(4, 10) + " Semestres");
                // Seleccionamos un estado aleatorio de nuestro arreglo
                carrera.setEstadoCarrera(estados[random.nextInt(estados.length)]);

                carreraRepository.save(carrera);
            }
            System.out.println("✅ Base de datos poblada con 15 carreras generadas por Faker.");
        }
    }
}
