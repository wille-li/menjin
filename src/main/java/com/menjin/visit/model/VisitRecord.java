package com.menjin.visit.model;

import java.util.Date;

import com.menjin.company.model.Company;
import com.menjin.company.model.Department;
import com.menjin.company.model.Employee;

/**
 * 
 * @author Lin
 *
 */
public class VisitRecord {

	private Integer id;

	/**
	 * 访问单号
	 */
	private String matterTxnNum;
	
	/**
	 * 访客信息
	 */
	private Visitor visitor;
	
	/**
	 * 被拜访公司
	 */
	private Company company;
	
	/**
	 * 被拜访部门
	 */
	private Department department;
	
	/**
	 * 被拜访人
	 */
	private Employee employee;
	
	/**
	 * 被拜访人姓名
	 */
	private String employeeName;
	
	/**
	 * 被拜访人电话
	 */
	private String employeePhone;

	/**
	 * 随行人数
	 */
	private Integer peopleSum;
	
	/**
	 * 随行车牌
	 */
	private String licensePlate;
	
	/**
	 * 拜訪原因
	 */
	private Matter matter;
	
	/**
	 * 預約時間
	 */
	private Date appointmentTime;
	
	/**
	 * 拜訪過期時間
	 */
	private Date expireTime;
	
	/**
	 * 實際拜訪時間
	 */
	private Date actualTime;
	
	/**
	 * 過機照片地址
	 */
	private String machineRecordUrl;
	
	/**
	 * 離開時間
	 */
	private Date leaveTime;
	
	/**
	 * 拜访单的状态
	 * 1.未拜访
	 * 2.正在拜访
	 * 3.离开
	 * 4.过期
	 */
	private String status;
	
	/**
	 * 拜访类型
	 * 1.预约
	 * 2.现场登记
	 */
	private String recordType;
	
	/**
	 * 检验方式
	 * 1.自动检验
	 * 2.手动检验
	 */
	private String validateMode;
	
	private String createBy;

	private Date modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatterTxnNum() {
		return matterTxnNum;
	}

	public void setMatterTxnNum(String matterTxnNum) {
		this.matterTxnNum = matterTxnNum;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getPeopleSum() {
		return peopleSum;
	}

	public void setPeopleSum(Integer peopleSum) {
		this.peopleSum = peopleSum;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Matter getMatter() {
		return matter;
	}

	public void setMatter(Matter matter) {
		this.matter = matter;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getActualTime() {
		return actualTime;
	}

	public void setActualTime(Date actualTime) {
		this.actualTime = actualTime;
	}

	public String getMachineRecordUrl() {
		return machineRecordUrl;
	}

	public void setMachineRecordUrl(String machineRecordUrl) {
		this.machineRecordUrl = machineRecordUrl;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	public String getValidateMode() {
		return validateMode;
	}

	public void setValidateMode(String validateMode) {
		this.validateMode = validateMode;
	}

	@Override
	public String toString() {
		return "VisitRecord [id=" + id + ", matterTxnNum=" + matterTxnNum
				+ ", visitor=" + visitor + ", company=" + company
				+ ", department=" + department + ", employee=" + employee
				+ ", employeeName=" + employeeName + ", employeePhone="
				+ employeePhone + ", peopleSum=" + peopleSum
				+ ", licensePlate=" + licensePlate + ", matter=" + matter
				+ ", appointmentTime=" + appointmentTime + ", expireTime="
				+ expireTime + ", actualTime=" + actualTime
				+ ", machineRecordUrl=" + machineRecordUrl + ", leaveTime="
				+ leaveTime + ", status=" + status + ", recordType="
				+ recordType + ", validateMode=" + validateMode + ", createBy="
				+ createBy + ", modifiedDate=" + modifiedDate + "]";
	}

}
