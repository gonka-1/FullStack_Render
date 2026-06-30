package duoc.Sala;

import duoc.Sala.model.Sala;
import duoc.Sala.repository.SalaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        String[] tipos = {"Laboratorio de Computación", "Aula de Clases", "Taller de Redes", "Auditorio"};
        String[] estados = {"Disponible", "Ocupada"};

        if (salaRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Sala sala = new Sala();

                sala.setNombreSala("Sala " + (100 + random.nextInt(400)) + "-" + i);

                sala.setCapacidad(20 + random.nextInt(41));

                sala.setUbicacion("Sede DuocUC, Torre " + (char) ('A' + random.nextInt(3)) + ", Piso " + (random.nextInt(5) + 1));

                sala.setTipoSala(tipos[random.nextInt(tipos.length)]);
                sala.setEstadoSala(estados[random.nextInt(estados.length)]);

                salaRepository.save(sala);
            }
            System.out.println(">> ¡DataLoader de Sala ejecutado con éxito! 10 registros creados.");
        }
    }
}