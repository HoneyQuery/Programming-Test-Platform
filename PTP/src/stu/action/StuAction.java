package stu.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import adm.dao.AdmImpl;
import adm.entity.Teacher;

import stu.dao.StuImpl;
import stu.entity.Student;
import teac.dao.TeacImpl;

/**
 * <font face="���Ŀ���" color="red">�û�ע��\��¼\�˳�������</font>
 * 
 * @author shawzt
 * @version 1.5
 * @build 2012/3/12
 */
public class StuAction extends ActionSupport {
	/*
	 * �����ֶ����ƶ�Ӧҵ��������ڸ�action�е����Jspҳ����nameһ�µĿؼ� 
	 * get()����:��ȡJspҳ��nameһ�µĿؼ�ֵ
	 * set()����:����Jspҳ��nameһ�µĿؼ���ʾ����
	 */
	private String uname;
	private String upwd;
	private String stuNo;
	private String rname;
	private String sex;
	private String grade;
	private String major;
	private String motto;
	private String cate; // �����б��¼����
	private String time; // ��¼ʱ�� ��ȫ����

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * ����List<>��Ӧҵ��������ڸ�action�е����Jspҳ����<s:iterator>����Ҫ������
	 * get()����:����Jspҳ��nameһ�µĿؼ���ʾ����
	 */
	private List<Object[]> newsList;

	public List<Object[]> getNewsList() {
		return newsList;
	}

	/*
	 * ����Ϊҵ���߼��������
	 */
	/**
	 * <font face="���Ŀ���" color="red">��ʾ������Ϣ</font>
	 * 
	 * @return SUCCESS
	 */
	public String showNews() {
		AdmImpl aImpl = new AdmImpl();
		String sql = "SELECT * FROM news ORDER BY Public_Time DESC";
		this.newsList = aImpl.findNewsBySql(sql);
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">�û���¼����<br>
	 * ���� [Ϊ��ֹ�û��˳����ٵ����������ذ�ť��������Ϣ���ԣ�<br>
	 * ʹ�����ݿ��¼���˳�ʱ����ǰ̨���͵�ʱ��ȽϽ����жϡ�<br>
	 * �����͵�ʱ��������ݿ��¼ʱ�䣬��Ϊ��Ч��¼��<br>
	 * ����Ϊ�Ƿ�����!]</font>
	 * 
	 * @return result
	 *         [success_adm:����Ա��¼;success_teac:��ʦ��¼;success_stu:ѧ����¼;ERROR]
	 */
	public String Login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Date date = new Date(Long.parseLong(this.time));
		int prior = Integer.parseInt(this.cate);
		String result = "";

		switch (prior) {
		// ��Ϊ����Ա�ͽ�ʦ����ͬһ�ű�Teacher�У����Դ˴��������ϲ�
		case 1:
		case 2: {
			Teacher teac = new Teacher();
			teac.setRegName(this.uname);
			teac.setPwd(this.upwd);
			TeacImpl teacImpl = new TeacImpl();
			List<Teacher> teachers = teacImpl.findTeacherByExample(teac);
			if (teachers.size() == 1) {
				System.out.println("Find User!");
				if (prior == 1) {
					/*
					 * ���ݿ��¼������¼ʱ��ȵ�ǰʱ���� �����߼�
					 */
					if (teachers.get(0).getLastLogOn().before(date)) {
						System.out.println("Success To Log on!");
						result = "success_adm";
						session.setAttribute("adm", teachers.get(0));
					} else { // �����������ذ�ťʱ �Ƿ�����
						result = "success_adm";
						session.setAttribute("fail", "session ended");
					}
				} else if (prior == 2) {
					if (teachers.get(0).getLastLogOn().before(date)) {
						System.out.println("Success To Log on!");
						session.setAttribute("teac", teachers.get(0));
						result = "success_teac";
					} else { // �����������ذ�ťʱ �ж�
						result = "success_teac";
						session.setAttribute("fail", "session ended");
					}
				}
			} else {
				System.out.println("No User,Fail To Log on!");
				result = ERROR;
			}
			break;
		}
		case 3: {
			Student stu = new Student();
			stu.setUname(this.uname);
			stu.setPwd(this.upwd);
			StuImpl stuImpl = new StuImpl();
			List<Student> stus = stuImpl.findStudentByExample(stu);
			if (stus.size() == 1) {
				System.out.println("Find User");
				if (stus.get(0).getLastLogOn().before(date)) {
					System.out.println("Success To Log on!");
					result = "success_stu";
					session.setAttribute("stu", stus.get(0));
				} else {
					result = "success_stu";
					session.setAttribute("fail", "session ended");
				}
			} else {
				System.out.println("No User,Fail To Log on!");
				result = ERROR;
			}
			break;
		}
		}
		return result;
	}

	/**
	 * <font face="���Ŀ���" color="red">ѧ���û�ע��<br>
	 * [ע:ѧ���û���Ҫҳ��ע�ᣬ��ʦ�˺����ɹ���Ա����.] </font>
	 * 
	 * @return
	 */
	public String Register() {
		StuImpl stuImpl = new StuImpl();

		Student tester = new Student();
		tester.setUname(this.uname);

		Student stu = new Student();
		stu.setUname(this.uname);
		stu.setPwd(this.upwd);
		stu.setStuNo(this.stuNo);
		stu.setRname(this.rname);
		stu.setSex(this.sex);
		stu.setGrade(Integer.parseInt(this.grade));
		stu.setMajor(this.major);
		stu.setLastLogOn(new Date(System.currentTimeMillis()));
		stu.setMotto(this.motto);
		stu.setPrior("C"); // ע���û�Ȩ��Ϊѧ��,ǰ̨���ṩȨ��ѡ����ɴ����趨!

		if (stuImpl.findStudentByExample(tester).size() <= 0) { // ����û����Ƿ�Ψһ
			// ���ظ��û���
			boolean flag = stuImpl.add(stu);
			if (flag == true) {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("register", "success");
				return "success";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">ѧ���û��˳�ϵͳ</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String Logout() {
		Date date = new Date(System.currentTimeMillis());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stu");
		student.setLastLogOn(date);
		StuImpl sImpl = new StuImpl();
		boolean flag = sImpl.update(student);
		if (flag == true)
			return SUCCESS;
		else
			return ERROR;
	}
}
