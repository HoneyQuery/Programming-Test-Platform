package teac.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import adm.dao.AdmImpl;
import adm.entity.Lang;
import adm.entity.Teacher;

import teac.dao.TeacImpl;
import teac.entity.Course;
import teac.entity.Examinations;
import teac.entity.Examsub;
import teac.entity.Subjects;
import teac.entity.Testsets;

/**
 * <font face="���Ŀ���" color="red">��ʦģ��ҵ������</font>
 * 
 * @author shawzt
 * @version 2.0
 * @build 2012/3/25
 * 
 */
public class TeacAction extends ActionSupport {
	/*
	 * �����ֶ����ƶ�Ӧҵ��������ڸ�action�е����Jspҳ����nameһ�µĿؼ� 
	 * get()����:��ȡJspҳ��nameһ�µĿؼ�ֵ
	 * set()����:����Jspҳ��nameһ�µĿؼ���ʾ����
	 */
	private String courid;
	private String cname;
	private String teacid; // ������
	private String lgid;

	public void setCourid(String courid) {
		this.courid = courid;
	}

	public String getCourid() {
		return courid;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCname() {
		return cname;
	}

	public void setTeacid(String teacid) {
		this.teacid = teacid;
	}

	public String getTeacid() {
		return teacid;
	}

	public void setLgid(String lgid) {
		this.lgid = lgid;
	}

	public String getLgid() {
		return lgid;
	}

	private String subno;
	private String title;
	private String desc;
	private String input;
	private String output;
	private String spinput;
	private String spoutput;
	private String cate;
	private String visible;

	public void setSubno(String subno) {
		this.subno = subno;
	}

	public String getSubno() {
		return subno;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setSpinput(String spinput) {
		this.spinput = spinput;
	}

	public String getSpinput() {
		return spinput;
	}

	public void setSpoutput(String spoutput) {
		this.spoutput = spoutput;
	}

	public String getSpoutput() {
		return spoutput;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getCate() {
		return cate;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getVisible() {
		return visible;
	}

	private String sbId;
	private String[] score;
	private String hasIn;
	private File[] inputs;
	private File[] outputs;
	private String[] inputsFileName;
	private String[] outputsFileName;

	public void setScore(String[] score) {
		this.score = score;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	public void setHasIn(String hasIn) {
		this.hasIn = hasIn;
	}

	public void setInputs(File[] inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(File[] outputs) {
		this.outputs = outputs;
	}

	public void setInputsFileName(String[] inputsFileName) {
		this.inputsFileName = inputsFileName;
	}

	public void setOutputsFileName(String[] outputsFileName) {
		this.outputsFileName = outputsFileName;
	}

	private String examId;
	private String examName;
	private String category;
	private String startTime;
	private String endTime;
	private String last;
	private String pass;

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamName() {
		return examName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getLast() {
		return last;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	private String examNo;
	private String examNa;
	private String[] select;

	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}

	public String getExamNa() {
		return examNa;
	}

	public void setSelect(String[] select) {
		this.select = select;
	}

	private String exId;
	private File stufile;
	private String fileName;
	private String contentType;

	public void setExId(String exId) {
		this.exId = exId;
	}

	public void setStufile(File stufile) {
		this.stufile = stufile;
	}

	public void setStufileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setStufileContentType(String contentType) {
		this.contentType = contentType;
	}

	/*
	 * ����List<>��Ӧҵ��������ڸ�action�е����Jspҳ����<s:iterator>����Ҫ������
	 * get()����:����Jspҳ��nameһ�µĿؼ���ʾ����
	 */
	private List<Object[]> courses;
	private List<Lang> langs;
	private List<Object[]> ques;
	private List<Object[]> exams;
	private List<Object[]> subjects;

	public List<Object[]> getCourses() {
		return courses;
	}

	public List<Lang> getLangs() {
		return langs;
	}

	public List<Object[]> getQues() {
		return ques;
	}

	public List<Object[]> getExams() {
		return exams;
	}

	public List<Object[]> getSubjects() {
		return subjects;

	}

	/*
	 * ����Ϊҵ���߼��������
	 */
	/**
	 * <font face="���Ŀ���" color="red">Ϊ�����γ�׼����������б�</font>
	 * 
	 * @return SUCCESS
	 */
	public String addPre() {
		AdmImpl admImpl = new AdmImpl();
		langs = new ArrayList<Lang>();
		langs = admImpl.findAllLangs();
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">�����γ�</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String addCourse() {
		Course course = new Course();
		course.setCourId(Integer.parseInt(this.courid));
		course.setCname(this.cname);
		course.setLangId(Integer.parseInt(this.lgid));
		course.setTeacherId(Integer.parseInt(this.teacid));

		TeacImpl tImpl = new TeacImpl();
		boolean flag = tImpl.addCourse(course);
		if (flag == true) {
			listCourse();
			return SUCCESS;
		} else
			return ERROR;
	}

	/**
	 * <font face="���Ŀ���" color="red">���¿γ�ʱ����Ϣ����</font>
	 * 
	 * @return SUCCESS
	 */
	public String mdPre() {
		addPre();
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(request.getParameter("cors")
					.getBytes("iso8859-1"), "utf-8"); 
			Course course;
			TeacImpl tImpl = new TeacImpl();
			course = tImpl.findCourseById(Integer.parseInt(pars));
			if (course != null) {
				courid = course.getCourId().toString();
				cname = course.getCname();
				lgid = course.getLangId().toString();
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">���¿γ�</font>
	 * 
	 * @return SUCCESS
	 */
	public String mdCourse() {
		Course course = new Course();
		course.setCourId(Integer.parseInt(courid));
		course.setCname(cname);
		course.setLangId(Integer.parseInt(lgid));
		course.setTeacherId(Integer.parseInt(teacid));

		TeacImpl tImpl = new TeacImpl();
		boolean flag = tImpl.update(course);
		if (flag == true)
			return listCourse();
		else{
		listCourse();
		return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ���γ�</font>
	 * 
	 * @return SUCCESS
	 */
	public String delCourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(request.getParameter("cors")
					.getBytes("iso8859-1"), "utf-8"); 
			Course course;
			TeacImpl tImpl = new TeacImpl();
			course = tImpl.findCourseById(Integer.parseInt(pars));
			if (course != null) {
				boolean flag = tImpl.delCourse(course);
				if (flag == true) {
					return listCourse();
				} else {
					listCourse();
					return ERROR;
				}
			}else{
				listCourse();
				return ERROR;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			listCourse();
			return ERROR;
		}
	}
	/**
	 * <font face="���Ŀ���" color="red">��ʾ���пγ�</font>
	 * 
	 * @return SUCCESS
	 */
	public String listCourse() {
		AdmImpl admImpl = new AdmImpl();
		langs = new ArrayList<Lang>();
		langs = admImpl.findAllLangs();

		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher teacher = (Teacher) request.getSession().getAttribute("teac");
		int id = teacher.getTeacherId();
		TeacImpl tImpl = new TeacImpl();
		String sqlString = "SELECT Cour_Id,cname,lname FROM course AS c,Lang AS l WHERE c.lang_Id=l.lang_Id AND Teacher_Id=";
		sqlString += id;
		courses = tImpl.findAllCourse(sqlString);

		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">������Ŀ</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String addSubject() {
		Subjects subject = new Subjects();
		subject.setTitle(this.title);
		subject.setDescription(this.desc);
		subject.setInput(this.input);
		subject.setOutput(this.output);
		subject.setSampleInput(this.spinput);
		subject.setSampleOutput(this.spoutput);
		subject.setCategory(this.cate);
		subject.setVisible(this.visible);
		subject.setTeacherId(Integer.parseInt(this.teacid));

		TeacImpl tImpl = new TeacImpl();
		boolean flag = tImpl.addSubject(subject);
		if (flag == true) {
			return listSubject();
		} else{
			listSubject();
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">�޸���Ŀʱ����Ϣ����</font>
	 * 
	 * @return SUCCESS
	 */
	public String mdPreSbj() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(
					request.getParameter("sbj").getBytes("iso8859-1"), "utf-8")
					.split(" ")[0];
			Subjects subject;
			TeacImpl tImpl = new TeacImpl();
			subject = tImpl.findSubjectById(Integer.parseInt(pars));
			if (subject != null) {
				subno = subject.getSubId().toString();
				title = subject.getTitle();
				desc = subject.getDescription();
				input = subject.getInput();
				output = subject.getOutput();
				spinput = subject.getSampleInput();
				spoutput = subject.getSampleOutput();
				cate = subject.getCategory();
				visible = subject.getVisible();
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">�޸���Ŀ</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String mdSubject() {
		TeacImpl tImpl = new TeacImpl();

		Subjects subject = tImpl.findSubjectById(Integer.parseInt(this.subno));
		subject.setTitle(this.title);
		subject.setDescription(this.desc);
		subject.setInput(this.input);
		subject.setOutput(this.output);
		subject.setSampleInput(this.spinput);
		subject.setSampleOutput(this.spoutput);
		subject.setCategory(this.cate);
		subject.setVisible(this.visible);
		subject.setTeacherId(Integer.parseInt(this.teacid));

		boolean flag = tImpl.update(subject);
		if (flag == true)
			return listSubject();
		else{
			listSubject();
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">������������</font>
	 * 
	 * @return SUCCESS
	 */
	public String addDatas() throws Exception {
		TeacImpl tImpl = new TeacImpl();

		if (hasIn.equals("have")) { // ����������
			for (int i = 0; i < inputs.length; i++) {
				// �����ļ�д�����ݿ�
				InputStream input = new FileInputStream(inputs[i]);
				byte[] b = new byte[input.available()];
				input.read(b, 0, b.length);
				String in = new String(b); // ��������
				input = new FileInputStream(outputs[i]);
				b = new byte[input.available()];
				input.read(b, 0, b.length);
				String out = new String(b); // �������
				Testsets test = new Testsets();
				test.setTdata(in);
				test.setResult(out);
				test.setScore(Integer.parseInt(this.score[i]));
				test.setSubId(Integer.parseInt(this.sbId));
				tImpl.addTestData(test);
			}
		} else if (hasIn.equals("none")) { // ����������
			for (int i = 0; i < outputs.length; i++) {
				// ����ļ�д�����ݿ� �����ֶ�Ϊnull
				InputStream input = new FileInputStream(outputs[i]);
				byte[] b = new byte[input.available()];
				input.read(b, 0, b.length);
				String out = new String(b); // �������
				Testsets test = new Testsets();
				test.setResult(out);
				test.setScore(Integer.parseInt(this.score[i]));
				test.setSubId(Integer.parseInt(this.sbId));
				tImpl.addTestData(test);
			}
		}
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">��ʾ������Ŀ</font>
	 * 
	 * @return SUCCESS
	 */
	public String listSubject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher teacher = (Teacher) request.getSession().getAttribute("teac");
		int id = teacher.getTeacherId();
		TeacImpl tImpl = new TeacImpl();
		String sql = "SELECT Sub_Id,Title,Category,Visible,(SELECT COUNT(*) FROM testsets WHERE testsets.Sub_Id=subjects.Sub_Id) AS Num FROM subjects WHERE subjects.Teacher_Id=";
		sql += id;
		subjects = tImpl.findAllSubjects(sql);
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ����Ŀ</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String delSubject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(
					request.getParameter("sbj").getBytes("iso8859-1"), "utf-8")
					.split(" ")[0]; 
			Subjects subject;
			TeacImpl tImpl = new TeacImpl();
			subject = tImpl.findSubjectById(Integer.parseInt(pars));
			if (subject != null) {
				boolean flag = tImpl.delSubject(subject);
				if (flag == true) {
					return listSubject();
				} else {
					listSubject();
					return ERROR;
				}
			}
			else{
				listSubject();
				return ERROR;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			listSubject();
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">��������</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String addExam() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Examinations exam = new Examinations();
		Date sDate = null, eDate = null;
		String result = "error";
		try {
			sDate = df.parse(this.startTime);
			eDate = df.parse(this.endTime);
			exam.setExamName(this.examName);
			exam.setCate(this.category);
			exam.setStartTime(sDate);
			exam.setEndTime(eDate);
			exam.setLast(Integer.parseInt(this.last));
			exam.setPass(this.pass);
			exam.setTeacherId(Integer.parseInt(this.teacid));

			TeacImpl tImpl = new TeacImpl();
			boolean flag = tImpl.addExam(exam);
			if (flag == true) {
				listCourse();
				listExam();
				return SUCCESS;
			} else{
				listCourse();
				listExam();
				return ERROR;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			listCourse();
			listExam();
			return ERROR;
		}
	}
	/**
	 * <font face="���Ŀ���" color="red">�޸Ŀ���ʱ����Ϣ����</font>
	 * 
	 * @return SUCCESS
	 */
	public String mdPreExam() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(request.getParameter("exam")
					.getBytes("iso8859-1"), "utf-8").split(" ")[0]; 
			Examinations exam;
			TeacImpl tImpl = new TeacImpl();
			exam = tImpl.findExamById(Integer.parseInt(pars));
			if (exam != null) {
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				examId = exam.getExamId().toString();
				examName = exam.getExamName();
				category = exam.getCate();
				startTime = df.format(exam.getStartTime());
				endTime = df.format(exam.getEndTime());
				last = exam.getLast().toString();
				pass = exam.getPass();
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">���¿���</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String mdExam() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		TeacImpl tImpl = new TeacImpl();
		try {
			Examinations exam = tImpl.findExamById(Integer
					.parseInt(this.examId));
			exam.setExamName(this.examName);
			exam.setCate(this.category);
			exam.setStartTime(df.parse(this.startTime));
			exam.setEndTime(df.parse(this.endTime));
			exam.setLast(Integer.parseInt(this.last));
			exam.setPass(this.pass);

			boolean flag = tImpl.update(exam);
			if (flag == true)
				return listExam();
			else{
				listExam();
				return ERROR;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			listExam();
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ������</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String delExam() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(request.getParameter("exam")
					.getBytes("iso8859-1"), "utf-8").split(" ")[0]; 
			Examinations exam;
			TeacImpl tImpl = new TeacImpl();
			exam = tImpl.findExamById(Integer.parseInt(pars));
			if (exam != null) {
				boolean flag = tImpl.delExam(exam);
				if (flag == true)
					return listExam();
				else{
				listExam();
				return ERROR;
				}
			}
			else{
				listExam();
				return ERROR;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			listExam();
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">��ʾ���п���</font>
	 * 
	 * @return SUCCESS
	 */
	public String listExam() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Teacher teacher = (Teacher) request.getSession().getAttribute("teac");
		int id = teacher.getTeacherId();
		TeacImpl tImpl = new TeacImpl();
		// ��ʾ�����б�ʱ����Ҫ��鿼�ԵĽ���ʱ��
		// �����п��ԵĽ���ʱ����ϵͳ��ǰʱ��֮ǰ�Ķ��޸ĳɡ��ѽ�����
		String sql = "UPDATE examinations SET Pass = (";
		sql += "CASE WHEN (Start_Time<NOW() && End_Time<NOW()) THEN \"�ѽ���\"";
		sql += "WHEN (Start_Time<=NOW() && End_Time>NOW()) THEN \"������\"";
		sql += "WHEN (Start_Time>NOW() && End_Time>NOW()) THEN \"δ��ʼ\"";
		sql += "ENd)";
		tImpl.update(sql);
		sql = "SELECT Exam_Id,Exam_Name,Start_Time,End_Time,Cate,Pass FROM examinations WHERE Teacher_Id=";
		sql += id;
		exams = tImpl.findAllExams(sql);
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">��ʾ������Ŀ</font>
	 * 
	 * @return SUCCESS
	 */
	public String allSbjs() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pars;
		try {
			/*
			 * Ĭ��Ϊget��ʽ�������󣬲���iso8859-1���� ����������Ҫת��
			 */
			pars = new String(request.getParameter("exam")
					.getBytes("iso8859-1"), "utf-8").split(" ")[0]; 
			Examinations exam;
			TeacImpl tImpl = new TeacImpl();
			exam = tImpl.findExamById(Integer.parseInt(pars));
			if (exam != null) {
				String sql = "SELECT Sub_Id,Title,Description,Category,(SELECT SUM(Score) FROM testsets WHERE testsets.Sub_Id=subjects.Sub_Id) AS Num FROM subjects";
				subjects = tImpl.findAllSubjects(sql);
				sql = "SELECT Sub_Id FROM examsub WHERE Exam_Id=";
				sql += Integer.parseInt(pars);
				ques = tImpl.findBySql(sql);

				this.examNo = exam.getExamId().toString();
				this.examNa = exam.getExamName();
				return SUCCESS;
			} else
				return ERROR;
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">����Ŀ</font>
	 * 
	 * @return SUCCESS
	 */
	public String bindSbjs() {
		// ��ɾ��ԭ���󶨵���Ŀ�������°�
		TeacImpl tImpl = new TeacImpl();
		String sql = "DELETE FROM examsub WHERE Exam_Id=";
		sql += Integer.parseInt(this.examNo);
		tImpl.IUD(sql);

		Examsub examsub = new Examsub();
		examsub.setExamId(Integer.parseInt(this.examNo));
		for (int i = 0; i < this.select.length; i++) {
			examsub.setSubId(Integer.parseInt(this.select[i]));
			tImpl.bindSbj(examsub);
		}
		listExam();
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">���ϴ���EXCEL�ļ��е���Ϣ�������ݿ�</font>
	 * 
	 */
	public String importData() throws Exception {
		int examNo = Integer.parseInt(this.exId);
		Workbook workbook = Workbook.getWorkbook(this.stufile);
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();
		TeacImpl tImpl = new TeacImpl();
		for (int i = 0; i < rows; i++) {
			Cell cell = sheet.getCell(1, i); // ��ȡ��i�е�2�е�Ԫ��
			String value = cell.getContents(); // ���������
			String sql = "INSERT INTO examscore (Exam_Id,Stu_No,Score) VALUES ";
			sql += "(" + examNo + ",\"" + value + "\"," + 0 + ")";
			int result = tImpl.IUD(sql);
		}
		return SUCCESS;
	}

	/**
	 * <font face="���Ŀ���" color="red">��ʦ�û��˳�</font>
	 * 
	 * @return SUCCESS/ERROR
	 */
	public String Logout() {
		Date date = new Date(System.currentTimeMillis());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("teac");
		teacher.setLastLogOn(date);
		TeacImpl tImpl = new TeacImpl();
		boolean flag = tImpl.update(teacher);
		if (flag == true)
			return SUCCESS;
		else
			return ERROR;
	}
}
