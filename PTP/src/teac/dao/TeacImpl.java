package teac.dao;

import java.util.List;

import adm.entity.Teacher;

import teac.entity.Course;
import teac.entity.Examinations;
import teac.entity.Examsub;
import teac.entity.Subjects;
import teac.entity.Testsets;

import common.hiber.BaseHibernateImpl;

/**
 * <font face="���Ŀ���" color="red">��ʦģ�����ݿ������</font>
 * 
 * @author shawzt
 * @version 2.0
 * @build 2012/3/25
 * 
 */
public class TeacImpl extends BaseHibernateImpl {
	private BaseHibernateImpl bImpl = new BaseHibernateImpl();

	/**
	 * <font face="���Ŀ���" color="red">����Id ���ҽ�ʦTeacher����</font>
	 * 
	 * @param Integer
	 * @return Teacher
	 */
	public Teacher findTeacherById(Integer id) {
		Class<? extends Teacher> objClass = Teacher.class;
		return (Teacher) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ʵ�����ҽ�ʦTeacher����</font>
	 * 
	 * @param Teacher
	 * @return List< Teacher >
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> findTeacherByExample(Teacher teacher) {
		Class<? extends Teacher> objClass = Teacher.class;
		return (List<Teacher>) bImpl.findByExample(objClass, teacher);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����ȫ����ʦTeacher����</font>
	 * 
	 * @return List< Teacher >
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeachers() {
		String tabel = "Teacher";
		return (List<Teacher>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">���½�ʦTeacher����</font>
	 * 
	 * @param Teacher
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Teacher teacher) {
		return bImpl.update(Teacher.class, teacher);
	}

	/**
	 * <font face="���Ŀ���" color="red">�����γ�Course����</font>
	 * 
	 * @param Course
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean addCourse(Course course) {
		Class<? extends Course> objClass = Course.class;
		return bImpl.insert(objClass, course);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ���¿γ�Course����</font>
	 * 
	 * @param Course
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Course course) {
		return bImpl.update(Course.class, course);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ���γ�Course����</font>
	 * 
	 * @param Course
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean delCourse(Course course) {
		Class<? extends Course> objClass = Course.class;
		return bImpl.delete(objClass, course);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ���ҿγ�Course����</font>
	 * 
	 * @param Integer
	 * @return Course
	 */
	public Course findCourseById(Integer id) {
		Class<? extends Course> objClass = Course.class;
		return (Course) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ʵ�����ҿγ�Course����</font>
	 * 
	 * @param Course
	 * @return List< Course >
	 */
	@SuppressWarnings("unchecked")
	public List<Course> findCourseByExample(Course course) {
		Class<? extends Course> objClass = Course.class;
		return (List<Course>) bImpl.findByExample(objClass, course);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ʵ���������пγ�Course����</font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	public List<Object[]> findAllCourse(String sql) {
		return bImpl.findBySql(sql);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ĿSubjects����</font>
	 * 
	 * @param Subjects
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean addSubject(Subjects subject) {
		Class<? extends Subjects> objClass = Subjects.class;
		return bImpl.insert(objClass, subject);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ���Ҷ���</font>
	 * 
	 * @param Integer
	 * @return Subject
	 */
	public Subjects findSubjectById(Integer id) {
		Class<? extends Subjects> objClass = Subjects.class;
		return (Subjects) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ��ѯ������Ŀ</font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	public List<Object[]> findAllSubjects(String sql) {
		return bImpl.findBySql(sql);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ĿSubjects����</font>
	 * 
	 * @param Subjects
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Subjects subject) {
		return bImpl.update(Subjects.class, subject);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ɾ����ĿSubjects����</font>
	 * 
	 * @param Subjects
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean delSubject(Subjects subject) {
		Class<? extends Subjects> objClass = Subjects.class;
		return bImpl.delete(objClass, subject);
	}

	/**
	 * <font face="���Ŀ���" color="red">����SQl��ѯ���п���</font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	public List<Object[]> findAllExams(String sql) {
		return bImpl.findBySql(sql);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ���ҿ���Examination����</font>
	 * 
	 * @param Integer
	 * @return Examinations
	 */
	public Examinations findExamById(Integer id) {
		Class<? extends Examinations> objClass = Examinations.class;
		return (Examinations) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">��������Examination</font>
	 * 
	 * @param Examination
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean addExam(Examinations exam) {
		Class<? extends Examinations> objClass = Examinations.class;
		return bImpl.insert(objClass, exam);
	}

	/**
	 * <font face="���Ŀ���" color="red">���¿���Examination����</font>
	 * 
	 * @param Examinations
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Examinations exam) {
		return bImpl.update(Examinations.class, exam);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ������Examination����</font>
	 * 
	 * @param Examinations
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean delExam(Examinations exam) {
		Class<? extends Examinations> objClass = Examinations.class;
		return bImpl.delete(objClass, exam);
	}

	/**
	 * <font face="���Ŀ���" color="red"> Ϊ���԰���Ŀ</font>
	 * 
	 * @param Examsub
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean bindSbj(Examsub examsub) {
		Class<? extends Examsub> objClass = Examsub.class;
		return bImpl.insert(objClass, examsub);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ������������</font>
	 * 
	 * @param Testsets
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean addTestData(Testsets data) {
		Class<? extends Testsets> objClass = Testsets.class;
		return bImpl.insert(objClass, data);
	}

	/**
	 * <font face="���Ŀ���" color="red">����SQL���¶���</font>
	 * 
	 * @param String
	 * @return int [��Ӱ��ļ�¼����]
	 */
	public int update(String sql) {
		return bImpl.IUD(sql);
	}
}
