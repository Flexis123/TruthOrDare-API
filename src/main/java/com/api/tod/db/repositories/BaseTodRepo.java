package com.api.tod.db.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BaseTodRepo<EN>{
	List<EN> getBy(EN e, Pageable p);
}
