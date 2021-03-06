package com.api.tod.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tod.db.models.Tod;

public interface TodRepo extends JpaRepository<Tod, Integer>, BaseTodRepo<Tod> {

}
