package com.platform.business.model;

public class AdviceModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String crops_id = "";

	private String period_id = "";
	
	private String advice_relevant_id = "";
	private String advice_analysis_id = "";
	private String advice_farming_id = "";
	private String advice_plant_id = "";
	private String advice_deficiency_id = "";
	private String advice_cultivation_id = "";
	private String advice_reference_id = "";
	
	private String content = "";
	private String element = "";
	private String factor = "";
	private String content1 = "";
	private String content2 = "";
	private String name = "";
	private String conditions = "";
	private String symptom = "";
	private String measures = "";

	public String getAdvice_reference_id() {
		return advice_reference_id;
	}

	public void setAdvice_reference_id(String advice_reference_id) {
		this.advice_reference_id = advice_reference_id;
	}

	public String getAdvice_analysis_id() {
		return advice_analysis_id;
	}

	public void setAdvice_analysis_id(String advice_analysis_id) {
		this.advice_analysis_id = advice_analysis_id;
	}

	public String getAdvice_farming_id() {
		return advice_farming_id;
	}

	public void setAdvice_farming_id(String advice_farming_id) {
		this.advice_farming_id = advice_farming_id;
	}

	public String getAdvice_plant_id() {
		return advice_plant_id;
	}

	public void setAdvice_plant_id(String advice_plant_id) {
		this.advice_plant_id = advice_plant_id;
	}

	public String getAdvice_deficiency_id() {
		return advice_deficiency_id;
	}

	public void setAdvice_deficiency_id(String advice_deficiency_id) {
		this.advice_deficiency_id = advice_deficiency_id;
	}

	public String getAdvice_cultivation_id() {
		return advice_cultivation_id;
	}

	public void setAdvice_cultivation_id(String advice_cultivation_id) {
		this.advice_cultivation_id = advice_cultivation_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public String getAdvice_relevant_id() {
		return advice_relevant_id;
	}

	public void setAdvice_relevant_id(String advice_relevant_id) {
		this.advice_relevant_id = advice_relevant_id;
	}

	public String getCrops_id() {
		return crops_id;
	}

	public void setCrops_id(String crops_id) {
		this.crops_id = crops_id;
	}

	public String getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(String period_id) {
		this.period_id = period_id;
	}
	
}
