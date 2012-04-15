package judge;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <font face="���Ŀ���" color="red">���������������ģ��֮�������<br>
 * 	�û��ύ���벢�ں�̨����Ϊ��Ӧ�������ļ�<br>
 * 	��ģ�齫�û�����Ĵ������ļ�·����Ϊ��Ϣ���������������</font>
 * @author shawzt
 * @version 1.0
 *@build 2011/12/27
 */
public class PtpTask{
	/**
	 * <font face="���Ŀ���" color="red">�������</font>
	 */
	private static LinkedBlockingQueue<String[]> que = new LinkedBlockingQueue<String[]>();
	
	/**
	 * <font face="���Ŀ���" color="red">������������</font>
	 */
	public static void addTask(String file,String lang,String subNo,String stuNo){
		try{
			String[] task = {file,lang,subNo,stuNo};
			que.put(task);
			System.out.println("Success to add Task!");
		}catch(Exception e){
			System.out.println("Fail to add Task!");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * <font face="���Ŀ���" color="red">ȡ����������</font>
	 * @return String[] {file,lang,subNo,stuNo}
	 */
	public static String[] getTask(){
		try{
			return que.poll(2L, TimeUnit.SECONDS);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}

