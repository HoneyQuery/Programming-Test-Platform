package common.hiber;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * <font face="���Ŀ���" color="red">��չHibernate��MYSQL һЩ�����������͵�֧��</font>
 * @author shawzt
 *
 */
public class MySQLDialect extends MySQL5InnoDBDialect{
	public MySQLDialect(){
		super();
		registerHibernateType(-1,Hibernate.TEXT.getName()); //��չ��Text���͵�֧��
	}
}
