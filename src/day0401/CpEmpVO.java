package day0401;

import java.sql.Date;

public class CpEmpVO {
	private int empno;
	private String ename;
	private String job;
	private int sal;
	private int comm;
	private Date hiredate;

	public CpEmpVO() {
		super();
	}

	public CpEmpVO(int empno, String ename, String job, int sal, int comm, Date hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
		this.hiredate = hiredate;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "CpEmpVO [empno=" + empno + ", ename=" + ename + ", job=" + job + ", sal=" + sal + ", comm=" + comm
				+ ", hiredate=" + hiredate + "]";
	}
	
} //class
