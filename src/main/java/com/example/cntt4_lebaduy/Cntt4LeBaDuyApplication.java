package com.example.cntt4_lebaduy;

import com.example.cntt4_lebaduy.model.Artifact;
import com.example.cntt4_lebaduy.repository.IArtifactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Cntt4LeBaDuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cntt4LeBaDuyApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(IArtifactRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.saveAll(List.of(


                        new Artifact("Hiện vật 1", "Nguồn gốc A", LocalDate.now()),
                        new Artifact("Hiện vật 2", "Nguồn gốc B", LocalDate.now()),
                        new Artifact("Hiện vật 3", "Nguồn gốc C", LocalDate.now()),
                        new Artifact("Hiện vật 4", "Nguồn gốc D", LocalDate.now()),
                        new Artifact("Hiện vật 5", "Nguồn gốc E", LocalDate.now()),
                        new Artifact("Hiện vật 6", "Nguồn gốc F", LocalDate.now()),
                        new Artifact("Hiện vật 7", "Nguồn gốc G", LocalDate.now()),
                        new Artifact("Hiện vật 8", "Nguồn gốc H", LocalDate.now()),
                        new Artifact("Hiện vật 9", "Nguồn gốc I", LocalDate.now()),
                        new Artifact("Hiện vật 10", "Nguồn gốc J", LocalDate.now()),
                        new Artifact("Hiện vật 11", "Nguồn gốc K", LocalDate.now()),
                        new Artifact("Hiện vật 12", "Nguồn gốc L", LocalDate.now())
                ));
            }
        };
    }
}
