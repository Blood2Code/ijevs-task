package task.ibris.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.ibris.dto.ResponseDto;
import task.ibris.entity.Thematic;

import java.util.List;

@Repository
public interface ThematicRepository extends JpaRepository<Thematic, Integer> {
    Page<Thematic> findAll(Pageable pageable);
    Thematic findByName(String name);
}
