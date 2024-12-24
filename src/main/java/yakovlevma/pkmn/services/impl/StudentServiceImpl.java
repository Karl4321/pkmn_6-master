package yakovlevma.pkmn.services.impl;

import lombok.RequiredArgsConstructor;
import yakovlevma.pkmn.clients.PokemonCardResponse;
import yakovlevma.pkmn.clients.RestClient;
import yakovlevma.pkmn.dao.StudentDao;
import yakovlevma.pkmn.entities.StudentEntity;
import yakovlevma.pkmn.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentDao studentDao;

    @Autowired
    private RestClient restClient;

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public StudentEntity getStudentById(UUID id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public StudentEntity saveStudent(StudentEntity student) {
        if (studentDao.findByFullName(student.getFirstName(), student.getSurName(), student.getFamilyName()).stream().findFirst().isPresent()) {
            throw new RuntimeException("Student already exists.");
        }
        return studentDao.save(student);
    }

    @Override
    public StudentEntity updateStudent(UUID id, StudentEntity student) {
        if (!studentDao.findById(id).isPresent()) {
            throw new RuntimeException("Student not found.");
        }
        student.setId(id);
        return studentDao.save(student);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.findByGroup(group);
    }

    @Override
    public Optional<StudentEntity> getStudentByFullName(String firstName, String surName, String familyName) {
        List<StudentEntity> students = studentDao.findByFullName(firstName, surName, familyName);
        return students.stream().findFirst();
    }

}
