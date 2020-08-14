package com.api.tod.db.repositories;

import javax.persistence.EntityManager;

import com.api.tod.db.models.Tod;

public class TodRepoImpl extends BaseTodRepoImpl<Tod>{
	
	
	public TodRepoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Tod> getType() {
		return Tod.class;
	}

}
