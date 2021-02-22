package com.sparta.week2prac;




import com.sparta.week2prac.domain.Person;
import com.sparta.week2prac.domain.PersonDTO;
import com.sparta.week2prac.domain.PersonRepository;
import com.sparta.week2prac.sevice.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week2pracApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week2pracApplication.class, args);
    }

        @Bean
        public CommandLineRunner demo(PersonRepository personRepository, PersonService personService){
            return (args) -> {
                List<Person> personList = new ArrayList<>();
                Person person = new Person("이우길","서울시 구로구");
                Person person1 = new Person("임은하","서울시 양천구");
                personList.add(person);
                personList.add(person1);
                personRepository.saveAll(personList);

                List<Person> findPerson = personRepository.findAll();
                for(Person p : findPerson){
                    System.out.println(p.getId());
                    System.out.println(p.getName());
                    System.out.println(p.getAddr());
                }

                personService.update(person.getId(),new PersonDTO("임은하 남편","서울시 양천구"));
                List<Person> findPerson2 = personRepository.findAll();
                for(Person p : findPerson2){
                    System.out.println(p.getId());
                    System.out.println(p.getName());
                    System.out.println(p.getAddr());
                }
            };

        }

    }
