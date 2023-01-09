package task.ibris.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.ibris.entity.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
    Page<Source> findAll(Pageable pageable);
    Source findByName(String name);
}
