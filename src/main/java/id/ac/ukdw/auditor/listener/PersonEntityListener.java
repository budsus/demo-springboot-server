package id.ac.ukdw.auditor.listener;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import id.ac.ukdw.auditor.Action;
import id.ac.ukdw.demo.model.Person;
import id.ac.ukdw.demo.model.PersonHistory;
import id.ac.ukdw.demo.service.BeanUtil;

import static javax.transaction.Transactional.TxType.MANDATORY;

public class PersonEntityListener {
	@PrePersist
	public void prePersist(Person target) {
		perform(target, Action.INSERTED);
	}
	
	@PreUpdate
	public void preUpdate(Person target) {
		perform(target, Action.UPDATED);
	}
	
	@PreRemove
	public void preRemove(Person target) {
		perform(target, Action.DELETED);
	}
	
	@Transactional(MANDATORY)
	private void perform(Person target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		entityManager.persist(new PersonHistory(target, action));
	}
}
