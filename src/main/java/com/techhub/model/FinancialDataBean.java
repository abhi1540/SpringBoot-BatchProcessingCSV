package com.techhub.model;

public class FinancialDataBean {


	private String series_reference;
	private String period;	
	private String data_value;	
	private String suppressed;	
	private String status;	
	private String units;	
	private String magnitude;
	private String subject;	
	private String group;	
	private String series_title_1;	
	private String series_title_2;	
	private String series_title_3;

	public String getSeries_reference() {
		return series_reference;
	}
	public void setSeries_reference(String series_reference) {
		this.series_reference = series_reference;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getData_value() {
		return data_value;
	}
	public void setData_value(String data_value) {
		this.data_value = data_value;
	}
	public String getSuppressed() {
		return suppressed;
	}
	public void setSuppressed(String sppressed) {
		this.suppressed = sppressed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getSeries_title_1() {
		return series_title_1;
	}
	public void setSeries_title_1(String series_title_1) {
		this.series_title_1 = series_title_1;
	}
	public String getSeries_title_2() {
		return series_title_2;
	}
	public void setSeries_title_2(String series_title_2) {
		this.series_title_2 = series_title_2;
	}
	public String getSeries_title_3() {
		return series_title_3;
	}
	public void setSeries_title_3(String series_title_3) {
		this.series_title_3 = series_title_3;
	}
	
	@Override
	public String toString() {
		return "FinancialDataBean [series_reference=" + series_reference + ", period=" + period + ", data_value="
				+ data_value + ", suppressed=" + suppressed + ", status=" + status + ", units=" + units + ", magnitude="
				+ magnitude + ", subject=" + subject + ", group=" + group + ", series_title_1=" + series_title_1
				+ ", series_title_2=" + series_title_2 + ", series_title_3=" + series_title_3 + "]";
	}
	
	
}
