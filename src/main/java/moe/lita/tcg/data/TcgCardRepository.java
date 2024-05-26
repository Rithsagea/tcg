package moe.lita.tcg.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcgCardRepository extends JpaRepository<TcgCard, Long> {

}
