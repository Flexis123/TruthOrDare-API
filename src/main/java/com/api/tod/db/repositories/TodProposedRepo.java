package com.api.tod.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.tod.db.models.TodProposed;

public interface TodProposedRepo extends JpaRepository<TodProposed, Integer>{

}
