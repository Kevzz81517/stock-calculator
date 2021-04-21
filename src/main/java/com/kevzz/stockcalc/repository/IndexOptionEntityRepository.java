package com.kevzz.stockcalc.repository;

import com.kevzz.stockcalc.nse1.model.IndexOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndexOptionEntityRepository extends JpaRepository<IndexOptionEntity, Long> {
}
