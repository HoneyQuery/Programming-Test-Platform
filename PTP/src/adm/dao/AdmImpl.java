package adm.dao;

import java.util.List;

import adm.entity.Lang;
import adm.entity.Logs;
import adm.entity.News;
import adm.entity.Power;
import adm.entity.Teacher;

import common.hiber.BaseHibernateImpl;

/**
 * <font face="���Ŀ���" color="red">����Աģ�����ݿ������</font>
 * 
 * @author shawzt
 * @version 2.0
 * @build 2012/3/25
 * 
 */
public class AdmImpl {

	private BaseHibernateImpl bImpl = new BaseHibernateImpl();

	/**
	 * <font face="���Ŀ���" color="red"> ��������Lang����</font>
	 * 
	 * @return boolean [true:�����ɹ���false:����ʧ��]
	 */
	public boolean add(Lang lang) {
		Class<? extends Lang> objClass = Lang.class;
		return bImpl.insert(objClass, lang);
	}

	/**
	 * <font face="���Ŀ���" color="red">��������Lang����</font>
	 * 
	 * @return boolean  [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Lang lang) {
		return bImpl.update(Lang.class, lang);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ������Lang����</font>
	 * 
	 * @return boolean [true:ɾ���ɹ���false:ɾ��ʧ��]
	 */
	public boolean delete(Lang lang) {
		return bImpl.delete(Lang.class, lang);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ȫ������Lang����</font>
	 * 
	 * @return List< Lang >
	 */
	@SuppressWarnings("unchecked")
	public List<Lang> findAllLangs() {
		String tabel = "Lang";
		return (List<Lang>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Ȩ��Power����</font>
	 * 
	 * @return boolean  [true:�����ɹ���false:����ʧ��]
	 */
	public boolean add(Power power) {
		Class<? extends Power> objClass = Power.class;
		return bImpl.insert(objClass, power);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Ȩ��Power����</font>
	 * 
	 * @return boolean  [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Power power) {
		return bImpl.update(Power.class, power);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ��Ȩ��Power����</font>
	 * 
	 * @return boolean  [true:ɾ���ɹ���false:ɾ��ʧ��]
	 */
	public boolean delete(Power power) {
		return bImpl.delete(Power.class, power);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ȫ��Ȩ��Power����</font>
	 * 
	 * @return List< Power >
	 */
	@SuppressWarnings("unchecked")
	public List<Power> findAllPowers() {
		String tabel = "Power";
		return (List<Power>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ʦTeacher����</font>
	 * 
	 * @return boolean [true:�����ɹ���false:����ʧ��]
	 */
	public boolean add(Teacher teacher) {
		Class<? extends Teacher> objClass = Teacher.class;
		return bImpl.insert(objClass, teacher);
	}

	/**
	 * <font face="���Ŀ���" color="red">���½�ʦTeacher����</font>
	 * 
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Teacher teacher) {
		return bImpl.update(Teacher.class, teacher);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ����ʦTeacher����</font>
	 * 
	 * @return boolean [true:ɾ���ɹ���false:ɾ��ʧ��]
	 */
	public boolean delete(Teacher teacher) {
		return bImpl.delete(Teacher.class, teacher);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ���ҽ�ʦTeacher����</font>
	 * 
	 * @return Teacher
	 */
	public Teacher findTeacherById(Integer id) {
		Class<? extends Teacher> objClass = Teacher.class;
		return (Teacher) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ʵ�����ҽ�ʦTeacher����</font>
	 * 
	 * @return List< Teacher >
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> findTeacherByExample(Teacher teacher) {
		Class<? extends Teacher> objClass = teacher.getClass();
		return (List<Teacher>) bImpl.findByExample(objClass, teacher);
	}

	/**
	 *<font face="���Ŀ���" color="red"> ����ȫ����ʦTeacher����</font>
	 * 
	 * @return List< Teacher >
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeac() {
		String tabel = "Teacher";
		return (List<Teacher>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ȫ����־Logs����</font>
	 * 
	 * @return List< Logs >
	 */
	@SuppressWarnings("unchecked")
	public List<Logs> findAllLogs() {
		String tabel = "Logs";
		return (List<Logs>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">����ȫ����ϢNews����</font>
	 * 
	 * @return List< News >
	 */
	@SuppressWarnings("unchecked")
	public List<News> findAllNews() {
		String tabel = "News";
		return (List<News>) bImpl.findAll(tabel);
	}

	/**
	 * <font face="���Ŀ���" color="red">����Id ������ϢNews����</font>
	 * 
	 * @return News
	 */
	public News findNewById(Integer id) {
		Class<? extends News> objClass = News.class;
		return (News) bImpl.findById(objClass, id);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ϢNews����</font>
	 * 
	 * @return boolean [true:�����ɹ���false:����ʧ��]
	 */
	public boolean add(News news) {
		Class<? extends News> objClass = News.class;
		return bImpl.insert(objClass, news);
	}

	/**
	 * <font face="���Ŀ���" color="red">������ϢNews����</font>
	 * 
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(News news) {
		return bImpl.update(News.class, news);
	}

	/**
	 * <font face="���Ŀ���" color="red">ɾ����ϢNews����</font>
	 * 
	 * @return boolean [true:ɾ���ɹ���false:ɾ��ʧ��]
	 */
	public boolean delete(News news) {
		return bImpl.delete(News.class, news);
	}
	/**
	 * <font face="���Ŀ���" color="red">����SQLִ��</font>
	 * 
	 * @return List< Object[] >
	 */
	public List<Object[]> findNewsBySql(String sql){
		return bImpl.findBySql(sql);
	}
}
