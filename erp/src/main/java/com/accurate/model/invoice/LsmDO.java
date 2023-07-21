package com.accurate.model.invoice;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class LsmDO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer lsmId;
	
	@OneToMany
	@JoinColumn(name="lsmDO")
	List<CompanyDO> companyDO;

	public Integer getLsmId() {
		return lsmId;
	}

	public void setLsmId(Integer lsmId) {
		this.lsmId = lsmId;
	}

	public List<CompanyDO> getCompanyDO() {
		return companyDO;
	}

	public void setCompanyDO(List<CompanyDO> companyDO) {
		this.companyDO = companyDO;
	}

	

}
