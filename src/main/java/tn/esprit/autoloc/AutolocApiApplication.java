package tn.esprit.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "tn.esprit.autoloc.repository")
@EntityScan(basePackages = "tn.esprit.autoloc.domain")
public class AutolocApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(VehiculeRepository vehiculeRepository) {
        return args -> {
            if (vehiculeRepository.count() == 0) {
                Vehicule v1 = new Vehicule(null, "200-TN-1234", "Peugeot", "208",
                        CategorieVehicule.CITADINE, new BigDecimal("80.00"), StatutVehicule.DISPONIBLE);
                Vehicule v2 = new Vehicule(null, "205-TN-5678", "Volkswagen", "Golf 8",
                        CategorieVehicule.BERLINE, new BigDecimal("130.00"), StatutVehicule.DISPONIBLE);
                Vehicule v3 = new Vehicule(null, "210-TN-9999", "Toyota", "RAV4",
                        CategorieVehicule.SUV, new BigDecimal("180.00"), StatutVehicule.MAINTENANCE);
                vehiculeRepository.saveAll(List.of(v1, v2, v3));
            }
        };
    }
}