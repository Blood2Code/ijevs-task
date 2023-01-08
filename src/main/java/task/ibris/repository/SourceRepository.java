package task.ibris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.ibris.entity.Source;

import java.util.List;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
    List<Source> findAll();
    Source findByName(String name);
}
