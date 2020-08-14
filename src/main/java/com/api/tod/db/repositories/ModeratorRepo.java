package com.api.tod.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.tod.db.models.Moderator;

public interface ModeratorRepo extends JpaRepository<Moderator, String>{
	
}
