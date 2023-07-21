package com.accurate.model.invoice;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class CompanyDO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer companyId;
	
	@ManyToOne
	LsmDO lsmDO;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public LsmDO getLsmDO() {
		return lsmDO;
	}

	public void setLsmDO(LsmDO lsmDO) {
		this.lsmDO = lsmDO;
	}
	
	
}