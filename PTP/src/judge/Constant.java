/*
 * ������
 * �洢ϵͳʹ�õĳ�����Ϣ
 * ����:Ф����
 */
package judge;

import java.util.Properties;

/**
 * <font face="���Ŀ���" color="red">������</font>
 * 
 * @author shawzt
 * @version 1.0
 * @build 2011/12/27
 * 
 */
public class Constant {
	private String information;
	private static Properties properties;
	private static String osName;

	// ����Ϊϵͳʹ�õı�����·������ [ע��ĩβ�Ŀո���ʡ�ԣ��漰��������]
	public static String C_PATH;
	public static String CP_PATH;
	public static String JV_PATH;
	public static String JV_RUN;
	// ����Ϊ����������ļ������ɿ�ִ���ļ��洢·�������뱸��Ŀ¼·��
	public static String CODE_CMP;
	public static String CODE_BCK;
	// ����Ϊ���뷵����Ϣ
	public static final int Waiting = 0;
	public static final int Judging = 1;
	public static final int Compile_error = 2;

	// ����Ϊ���з�����Ϣ
	public static final int Runtime_error = 0;
	public static final int Present_error = 1;
	public static final int Time_limit_exceed = 2;
	public static final int Memory_limit_exceed = 3;
	public static final int Output_limit_exceed = 4;
	public static final int Restrict_function = 5;

	public Constant() {
		this.information = "This is a constant class for initialization of variables";
		System.out.println(information);		
	}
	
	static{
		properties = System.getProperties();
		osName = properties.getProperty("os.name").toUpperCase();
		
		if (osName.startsWith("WINDOWS")) { // windowsƽ̨�µ���Ŀʹ��Ŀ¼��ʼ��
			C_PATH = "E:/gcc/bin/gcc.exe ";
			CP_PATH = "E:/gcc/bin/g++.exe ";
			JV_PATH = "C:/Program Files/Java/jdk1.6.0_31/bin/javac ";
			JV_RUN = "C:/Program Files/Java/jdk1.6.0_31/bin/java -classpath ";
			CODE_CMP = "E:/CODES";
			CODE_BCK = "E:/BackUp";
			System.out.println("init Constant on Windows");
		} else  if(osName.startsWith("LINUX")){
			C_PATH = "/usr/bin/gcc ";
			CP_PATH = "/usr/bin/g++ ";
			JV_PATH = "/usr/bin/javac ";
			JV_RUN = "/usr/bin/java -classpath ";
			CODE_CMP = "/home/shawzt/Documents/PTP/CODES";
			CODE_BCK = "/home/shawzt/Documents/PTP/BackUp";
			System.out.println("init Constant on Linux");
		}
	}

}
