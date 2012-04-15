package common.hiber;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model<br>
 * <font face="���Ŀ���" color="red"> Hibernate֮���ݿ���������� </font>
 * 
 * @author shawzt
 * @version 2.0
 * @build 2012/3/24
 */
public class BaseHibernateImpl {

	private Session session = null;
	private Transaction tran = null;

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * <font face="���Ŀ���" color="red"> ͨ��SQL���� </font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySql(String sql) {
		List<Object[]> list = null;
		try {
			session = getSession();
			tran = session.beginTransaction();
			list = session.createSQLQuery(sql).list();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * <font face="���Ŀ���" color="red"> ͨ��SQL�������޸ġ�ɾ�� </font>
	 * 
	 * @param String
	 * @return int [��Ӱ��ļ�¼����]
	 */
	public int IUD(String sql) {
		int result = 0;
		try {
			session = getSession();
			tran = session.beginTransaction();
			result = session.createSQLQuery(sql).executeUpdate();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����Id���� </font>
	 * 
	 * @param Class< ? > ��Integer
	 * @return Object
	 */
	public Object findById(Class<?> objClass, Integer id) {
		Object instance = new Object();
		try {
			session = getSession();
			tran = session.beginTransaction();
			instance = objClass.cast(session.get(objClass, id));
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return instance;
	}
	/**
	 * <font face="���Ŀ���" color="red"> ����Object���� </font>
	 * 
	 * @param Class< ? > ��Object
	 * @return List< ? >
	 */
	public List<?> findByExample(Class<?> objClass, Object instance) {
		List<?> results = null;
		try {
			session = getSession();
			tran = session.beginTransaction();
			results = session.createCriteria(objClass)
					.add(create(objClass.cast(instance))).list();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return results;
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����Object���� </font>
	 * 
	 * @param Class< ? > ��Object
	 * @return boolean [true:�����ɹ���false:����ʧ��]
	 */
	public boolean insert(Class<?> objClass, Object object) {
		try {
			session = getSession();
			tran = session.beginTransaction();
			session.save(objClass.cast(object));
			tran.commit();
			return true;
		} catch (HibernateException e) {
			tran.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����Object���� </font>
	 * 
	 * @param Class< ? > ��Object
	 * @return boolean [true:���³ɹ���false:����ʧ��]
	 */
	public boolean update(Class<?> objClass, Object object) {
		try {
			session = getSession();
			tran = session.beginTransaction();
			session.update(objClass.cast(object));
			tran.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * <font face="���Ŀ���" color="red"> ����Objectɾ�� </font>
	 * 
	 * @param Class< ? > ��Object
	 * @return boolean [true:ɾ���ɹ���false:ɾ��ʧ��]
	 */
	public boolean delete(Class<?> objClass, Object object) {
		try {
			session = getSession();
			tran = session.beginTransaction();
			session.delete(objClass.cast(object));
			tran.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	/**
	 * <font face="���Ŀ���" color="red"> ����Table�������� </font>
	 * 
	 * @param String
	 * @return List< ? >
	 */
	public List<?> findAll(String tabel) {
		List<?> list = null;
		try {
			String queryString = "from " + tabel;
			session = getSession();
			tran = session.beginTransaction();	
			list = session.createQuery(queryString).list();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}