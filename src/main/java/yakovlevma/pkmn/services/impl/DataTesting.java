package yakovlevma.pkmn.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import yakovlevma.pkmn.controllers.CardController;
import yakovlevma.pkmn.controllers.StudentController;
import yakovlevma.pkmn.entities.CardEntity;
import yakovlevma.pkmn.entities.StudentEntity;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataTesting {

    private final ObjectMapper objectMapper;
    private final CardController cardController;
    private final StudentController studentController;

    @PostConstruct
    @SneakyThrows
    public void init() {
        System.out.println("Post construct init");

        // Чтение данных из JSON файлов
        File jsonFile = new File("D:\\JavaProject\\pkmn_6-master\\src\\main\\resources\\cards.json");
        CardEntity card = objectMapper.readValue(jsonFile, CardEntity.class);

        File jsonFile1 = new File("D:\\JavaProject\\pkmn_6-master\\src\\main\\resources\\students.json");
        StudentEntity student = objectMapper.readValue(jsonFile1, StudentEntity.class);


        System.out.println("Student by Full Name: " + studentController.getStudentByFullName(student));
    }
}