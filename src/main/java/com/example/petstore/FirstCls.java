package com.example.petstore;


import com.example.petstore.repo.PetRepo;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.hibernate.service.spi.InjectService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class FirstCls {


 @Inject
 PetRepo petRepo;

    void onStart(@Observes StartupEvent ev) {
        Pet pet1 = new Pet("Cat", "Sisi", 4);
        Pet pet2 = new Pet("Dog", "Boola", 6);
        Pet pet3 = new Pet("Parrot", "Peththappu", 1);
        Pet pet4 = new Pet("Rabbit", "Sudu", 2);
        petRepo.save(pet1);
        petRepo.save(pet2);
        petRepo.save(pet3);
        petRepo.save(pet4);


        System.out.println(pet1.getPetName());

    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("Application Shutdown...");
    }


}

