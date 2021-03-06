package com.whenling.shop.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.whenling.castle.repo.domain.Defaultable;
import com.whenling.shop.entity.DeliveryCenter;

public class DeliveryCenterRepositoryImpl implements DeliveryCenterRepositoryCustom {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public DeliveryCenter findDefault() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeliveryCenter> query = criteriaBuilder.createQuery(DeliveryCenter.class);
		Root<DeliveryCenter> root = query.from(DeliveryCenter.class);
		query.where(criteriaBuilder.isTrue(root.get(Defaultable.PROPERTY_NAME)));
		List<DeliveryCenter> deliveryCenters = entityManager.createQuery(query).getResultList();
		return deliveryCenters != null && deliveryCenters.size() > 0 ? deliveryCenters.get(0) : null;
	}

}
