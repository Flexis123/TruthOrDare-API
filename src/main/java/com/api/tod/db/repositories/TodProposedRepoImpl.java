package com.api.tod.db.repositories;

import javax.persistence.EntityManager;

import com.api.tod.db.models.TodProposed;

public class TodProposedRepoImpl extends BaseTodRepoImpl<TodProposed>{
	
	
	public TodProposedRepoImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Class<TodProposed> getType() {
		return TodProposed.class;
	}

}
