package de.tub.ise.anwsys.repositories;

import de.tub.ise.anwsys.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
