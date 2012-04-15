package stu.dao;

import java.util.List;

import stu.entity.Excises;
import stu.entity.Exinfor;
import stu.entity.Student;
import stu.entity.Submits;
import teac.entity.Subjects;
import common.hiber.BaseHibernateImpl;

/**
 * <font face="���Ŀ���" color="red">ѧ��ģ�����ݿ������</font>
 * 
 * @author shawzt
 * @version 2.0
 * @build 2012/3/26
 * 
 */
public class StuImpl extends BaseHibernateImpl {
	private BaseHibernateImpl bImpl = new BaseHibernateImpl();

	/**
	 * <font face="���Ŀ���" color="red">����ѧ��Student����</font>
	 * 
	 * @param Student
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean add(Student stu) {
		return bImpl.insert(Student.class, stu);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ѧ��Student����</font>
	 * 
	 * @param Student
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Student stu) {
		return bImpl.update(Student.class, stu);

	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ��ѧ��Student����</font>
	 * 
	 * @param Student
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean delete(Student stu) {
		return bImpl.delete(Student.class, stu);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ����ѧ��Student����</font>
	 * 
	 * @param Integer
	 * @return Student
	 */
	public Student findStudentById(Integer id) {
		Class<? extends Student> objClass = Student.class;
		return (Student) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����ʵ������ѧ��Student����</font>
	 * 
	 * @param Student
	 * @return List< Student >
	 */
	@SuppressWarnings("unchecked")
	public List<Student> findStudentByExample(Student stu) {
		Class<? extends Student> objClass = stu.getClass();
		return (List<Student>) bImpl.findByExample(objClass, stu);
	}

	/**
	 * <font face="���Ŀ���" color="red">��������ѧ��Student����</font>
	 * 
	 * @return List< Student >
	 */
	@SuppressWarnings("unchecked")
	public List<Student> findAllStudents() {
		String tabel = "Student";
		return (List<Student>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ������ĿSubject����</font>
	 * 
	 * @param Integer
	 * @return Subjects
	 */
	public Subjects findSubById(Integer id) {
		Class<? extends Subjects> objClass = Subjects.class;
		return (Subjects) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ϰ��¼Exinfor����</font>
	 * 
	 * @param Exinfor
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean add(Exinfor exinfor) {
		return bImpl.insert(Exinfor.class, exinfor);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ϰExcises����</font>
	 * 
	 * @param Excises
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean add(Excises excise) {
		return bImpl.insert(Excises.class, excise);
	}

	/**
	 * <font face="���Ŀ���" color="red">�����ύSubmits����</font>
	 * 
	 * @param Submits
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean add(Submits submit) {
		return bImpl.insert(Submits.class, submit);
	}

	/**
	 * <font face="���Ŀ���" color="red">����SQL����</font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	public List<Object[]> findListBySQL(String sql) {
		return bImpl.findBySql(sql);
	}

	/**
	 * <font face="���Ŀ���" color="red">����SQL����</font>
	 * 
	 * @param String
	 * @return int [��Ӱ��ļ�¼����]
	 */
	public int update(String sql) {
		return bImpl.IUD(sql);
	}

}
