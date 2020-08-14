package com.api.tod.db.repositories;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

public abstract class BaseTodRepoImpl<EN> implements BaseTodRepo<EN>{
	
	protected EntityManager em;
	
	public BaseTodRepoImpl(EntityManager em) {
		this.em = em;
	}
	
	private List<EN> getPage(List<EN> res, Pageable p){
		int startIndex = p.getPageNumber() * p.getPageSize();
		
		List<EN> results = new ArrayList<>();
		for(int i = startIndex; i < startIndex + p.getPageSize(); i++) {
			try {
				results.add(res.get(i));
			}catch(IndexOutOfBoundsException e) {
				break;
			}
		}
		return results;
	}

	@Override
	public List<EN> getBy(EN e, Pageable p){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EN> cr = cb.createQuery(getType());
		Root<EN> r = cr.from(getType());
		
		Class<?> cls = getType();
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		while(cls != null) {
			for(Field f : cls.getDeclaredFields()) {
				if(!Modifier.isTransient(f.getModifiers())
						&&
					!Modifier.isFinal(f.getModifiers())
						&&
					!Modifier.isStatic(f.getModifiers())
				) {
					f.setAccessible(true);
					
					Object obj = null;
					try {
						obj = f.get(e);
					} catch (IllegalArgumentException | IllegalAccessException e1) {}
					
					if(obj instanceof String) {
						Path<String> prop = r.get(f.getName());
						predicates.add(cb.like(prop, "%" + obj + "%"));
					}
				}
			}
			cls = cls.getSuperclass();
		}
		
		CriteriaQuery<EN> q = cr.select(r).where(predicates.toArray(new Predicate[] {}));
		return getPage(em.createQuery(q).getResultList(), p);
	}

	public abstract Class<EN> getType();
}
