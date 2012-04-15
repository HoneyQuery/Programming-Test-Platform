package judge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adm.dao.AdmImpl;
import adm.entity.Lang;

import stu.dao.StuImpl;
import stu.entity.Excises;
import stu.entity.Exinfor;
import stu.entity.Submits;
import teac.dao.TeacImpl;

/**
 * <font face="���Ŀ���" color="red">���������������ģ��֮��������<br>
 * �����̣߳������������<br>
 * �����������ȡ�����񣬵��ñ��������롢���г���</font>
 * 
 * @author shawzt
 * @version 1.5
 * @build 2012/3/12
 * 
 */
public class PtpJudge extends Thread {
	private String[] curTask;
	private HashMap<Integer, String> hashMap;

	public PtpJudge() {
		this.start();
	}

	/**
	 * <font face="���Ŀ���" color="red">�����������߳�<br>
	 * ��ѭ������ȡ����������б������� </font>
	 */
	public void run() {
		while (true) {
			curTask = PtpTask.getTask();
			if (curTask != null) { // ������������
				System.out.println("Success to get Task!");
				check(curTask);
			}
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">��������������Ե�HashMap</font>
	 * 
	 */
	private void HashMapOfLang() {
		AdmImpl aImpl = new AdmImpl();
		List<Lang> list = aImpl.findAllLangs();
		hashMap = new HashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			hashMap.put(list.get(i).getLangId(), list.get(i).getLname());
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">�������</font>
	 * 
	 * @param String
	 *            [] {file,lang,subNo,stuNo}
	 */
	protected void check(String[] task) {
		HashMapOfLang();
		// ��ʼ����
		int result = 0;
		String compileInfor = ""; // ������Ϣ ����Compile(task) �޴���"success"

		compileInfor = Compile(task);
		if (!compileInfor.endsWith("success")) { // �����д�
			result = Constant.Compile_error;
			// ��������Խ��д��excises��:�������,��������
			StuImpl stuImpl = new StuImpl();
			String sql = "SELECT Ex_Id,Sub_Id FROM excises WHERE Stu_No= ";
			sql += task[3];
			sql += " AND Sub_Id = ";
			sql += Integer.parseInt(task[2]);
			sql += " AND Lang_Id = ";
			sql += Integer.parseInt(task[1]);
			List<Object[]> hasList = stuImpl.findBySql(sql);
			if (hasList.isEmpty()) {
				Excises excise = new Excises();
				excise.setPass("F");
				excise.setSbts(1);
				excise.setSubId(Integer.parseInt(task[2]));
				excise.setStuNo(task[3]);
				excise.setLangId(Integer.parseInt(task[1]));
				stuImpl.add(excise);
			} else {
				sql = "UPDATE excises SET ";
				sql += "Sbts = Sbts+1 WHERE Ex_Id = ";
				sql += (Integer) hasList.get(0)[0];
				stuImpl.update(sql);
			}
			// ��������Խ��д��submits��:�������,��������
			hasList.clear();
			sql = "SELECT Sbt_Id,Sub_Id FROM submits WHERE Sub_Id= ";
			sql += Integer.parseInt(task[2]);
			hasList = stuImpl.findBySql(sql);
			if (hasList.isEmpty()) {
				Submits submit = new Submits();
				submit.setSbts(1);
				submit.setAccepts(0);
				submit.setSubId(Integer.parseInt(task[2]));
				stuImpl.add(submit);
			} else {
				sql = "UPDATE submits SET Sbts = Sbts+1, ";
				sql += "Accepts = Accepts+0";
				sql += " WHERE Sbt_Id = ";
				sql += (Integer) hasList.get(0)[0];
				stuImpl.update(sql);
			}
			System.out.println("Compile Infor:" + compileInfor);
		} else {
			System.out.println("Success To Compile!");
			// ����Ϊ������������ִ���ύ�ĳ���������׼�𰸱ȶ�
			System.out.println(DoJudge(task));
		}
	}

	/**
	 * <font face="���Ŀ���" color="red">���ñ���������</font>
	 * 
	 * @param String
	 *            [] {file,lang,subNo,stuNo}
	 * @return String {������Ϣ}
	 */
	protected String Compile(String[] task) {
		StringBuilder errorInfor = new StringBuilder(); // ������Ϣ
		String cmpCmd = ""; // ��������

		/* String[] task = {file,lang,subNo,stuNo}; */
		/* ������� */
		String file = task[0];
		String name = hashMap.get(Integer.parseInt(task[1]));

		if (name.equals("C")) {
			String cp = Constant.C_PATH;
			cmpCmd = cp + file + " -o " + Constant.CODE_CMP + "/" + task[3]
					+ "/" + task[3] + ".exe ";
			System.out.println("Compile Command:" + cmpCmd);

			try {
				Process proc = Runtime.getRuntime().exec(cmpCmd);
				BufferedReader brd = new BufferedReader(new InputStreamReader(
						proc.getErrorStream()));
				String str;
				while ((str = brd.readLine()) != null) {
					errorInfor.append(str);
				}
				brd.close();
				proc.waitFor();
				if (proc.exitValue() == 0) { // ���������˳�
					errorInfor.append(" -->success");
				} else {
					System.out.println("Compile Error:" + proc.exitValue());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (name.equals("C++")) {
			String cpp = Constant.CP_PATH;
			cmpCmd = cpp + file + " -o " + Constant.CODE_CMP + "/" + task[3]
					+ "/" + task[3] + ".exe ";
			System.out.println("Compile Command:" + cmpCmd);

			try {
				Process proc = Runtime.getRuntime().exec(cmpCmd);
				BufferedReader brd = new BufferedReader(new InputStreamReader(
						proc.getErrorStream()));
				String str;
				while ((str = brd.readLine()) != null) {
					errorInfor.append(str);
				}
				brd.close();
				proc.waitFor();
				if (proc.exitValue() == 0) { // ���������˳�
					errorInfor.append("-->success");
				} else {
					System.out.println("Compile Error:" + proc.exitValue());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			String javac = Constant.JV_PATH;
			cmpCmd = javac + file;
			System.out.println("Compile Command:" + cmpCmd);

			try {
				Process proc = Runtime.getRuntime().exec(cmpCmd);
				BufferedReader brd = new BufferedReader(new InputStreamReader(
						proc.getErrorStream()));
				String str;
				while ((str = brd.readLine()) != null) {
					errorInfor.append(str);
				}
				brd.close();
				proc.waitFor();
				if (proc.exitValue() == 0) { // ���������˳�
					errorInfor.append("-->success");
				} else {
					System.out.println("Compile Error:" + proc.exitValue());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return errorInfor.toString();
	}

	/**
	 * <font face="���Ŀ���" color="red">���ݲ����������г��򲢽�������</font>
	 * 
	 * @param String
	 *            [] {file,lang,subNo,stuNo}
	 * @return String {������Ϣ}
	 */
	protected String DoJudge(String[] task) {
		String result = ""; // ������Ϣ
		String runCmd = ""; // ��������

		/* String[] task = {file,lang,subNo,stuNo}; */
		/* ������� */
		String file = task[0];
		String name = hashMap.get(Integer.parseInt(task[1]));

		/* ����C\C++ֻ��Ҫ��������Ϊ�����������ɵ�exe�ļ����� */
		/* ����Java ��Ҫ��������Ϊ��������JVMִ���ɵ�.class�ļ� */

		// ���������趨��������
		if (name.equals("C")) {
			runCmd = Constant.CODE_CMP + "/" + task[3] + "/" + task[3]
					+ ".exe ";
			System.out.println("Run Command:" + runCmd);
		} else if (name.equals("C++")) {
			runCmd = Constant.CODE_CMP + "/" + task[3] + "/" + task[3]
					+ ".exe ";
			System.out.println("Run Command:" + runCmd);
		} else {
			String java = Constant.JV_RUN;
			int index = file.lastIndexOf("/");
			runCmd = java + file.substring(0, index) + " "
					+ file.substring(index + 1, file.length() - 5);
			System.out.println("Run Command:" + runCmd);
		}
		// ���в���C\C++����
		try {
			/*
			 * �˴���Ҫ�������task[2] subNo���Ӻ�̨���ݿ�testsets����ȡ����صĲ�������(���롢���)
			 * Ȼ��������������������Ϊ�������г��򣬽����������ݱȽ�
			 */
			List<Object[]> datas = getTestDatas(task[2]);
			boolean pass = true; // ��ʶ�Ƿ�ͨ�����в���
			Date testTime = new Date(System.currentTimeMillis()); // һ���ύ�����в���ʱ���Ϊһ�������ڲ�ѯ
			for (int i = 0; i < datas.size(); i++) {
				result = "";
				Process proc = Runtime.getRuntime().exec(runCmd);
				// PrintWriter��������������еĳ���д����������
				PrintWriter pw = new PrintWriter(proc.getOutputStream(), true);
				pw.println(datas.get(i)[1].toString());
				BufferedReader brd = new BufferedReader(new InputStreamReader(
						proc.getInputStream())); // ��ȡ���н��
				String infor = "";
				while ((infor = brd.readLine()) != null) {
					result += infor;
				}
				brd.close();
				pw.close();
				proc.waitFor();
				if (proc.exitValue() == 0) { // ���������˳�
					result += "";
					// ���ò��Խ��
					if (!result.equals(datas.get(i)[2].toString())) {
						pass = false;
						// �����Խ������exinfor��
						Exinfor exinfor = new Exinfor();
						exinfor.setTestId((Integer) datas.get(i)[0]);
						exinfor.setPass("F");
						exinfor.setGetScore(0);
						exinfor.setStuNo(task[3]);
						exinfor.setSubId(Integer.parseInt(task[2]));
						exinfor.setLangId(Integer.parseInt(task[1]));
						exinfor.setTestTime(testTime);
						StuImpl stuImpl = new StuImpl();
						stuImpl.add(exinfor);
					} else {
						// ������ȷ�����Խ������exinfor��
						Exinfor exinfor = new Exinfor();
						exinfor.setTestId((Integer) datas.get(i)[0]);
						exinfor.setPass("T");
						exinfor.setGetScore((Integer) datas.get(i)[3]);
						exinfor.setStuNo(task[3]);
						exinfor.setSubId(Integer.parseInt(task[2]));
						exinfor.setLangId(Integer.parseInt(task[1]));
						exinfor.setTestTime(testTime);
						StuImpl stuImpl = new StuImpl();
						stuImpl.add(exinfor);
					}
				} else {
					System.out.println("Run Error:" + proc.exitValue());
				}
			} // ���Խ���
				// ��������Խ��д��excises��:�������,��������
			StuImpl stuImpl = new StuImpl();
			String sql = "SELECT Ex_Id,Sub_Id FROM excises WHERE Stu_No= ";
			sql += task[3];
			sql += " AND Sub_Id = ";
			sql += Integer.parseInt(task[2]);
			sql += " AND Lang_Id = ";
			sql += Integer.parseInt(task[1]);
			List<Object[]> hasList = stuImpl.findBySql(sql);
			if (hasList.isEmpty()) {
				Excises excise = new Excises();
				excise.setPass(pass == true ? "T" : "F");
				excise.setSbts(1);
				excise.setSubId(Integer.parseInt(task[2]));
				excise.setStuNo(task[3]);
				excise.setLangId(Integer.parseInt(task[1]));
				stuImpl.add(excise);
			} else {
				sql = "UPDATE excises SET Pass = ( ";
				sql += "CASE Pass WHEN  'F' THEN ";
				sql += (pass == true ? "\'T\'" : "\'F\'");
				sql += " ELSE Pass END )";
				sql += ",Sbts = Sbts+1 WHERE Ex_Id = ";
				sql += (Integer) hasList.get(0)[0];
				stuImpl.update(sql);
			}
			// ��������Խ��д��submits��:�������,��������
			hasList.clear();
			sql = "SELECT Sbt_Id,Sub_Id FROM submits WHERE Sub_Id= ";
			sql += Integer.parseInt(task[2]);
			hasList = stuImpl.findBySql(sql);
			if (hasList.isEmpty()) {
				Submits submit = new Submits();
				submit.setSbts(1);
				submit.setAccepts(pass == true ? 1 : 0);
				submit.setSubId(Integer.parseInt(task[2]));
				stuImpl.add(submit);
			} else {
				sql = "UPDATE submits SET Sbts = Sbts+1, ";
				sql += "Accepts = Accepts+";
				sql += (pass == true ? 1 : 0);
				sql += " WHERE Sbt_Id = ";
				sql += (Integer) hasList.get(0)[0];
				stuImpl.update(sql);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result.toString();
	}

	/**
	 * <font face="���Ŀ���" color="red">���ݴ������Ŀ��subNo��testsets����ȡ��������صĲ�������</font>
	 * 
	 * @param String
	 * @return List< Object[] >
	 */
	protected List<Object[]> getTestDatas(String subNo) {
		List<Object[]> datas = new ArrayList<Object[]>(5);
		String sql = "SELECT Test_Id,TData,Result,Score FROM testsets  WHERE Sub_Id = ";
		sql += Integer.parseInt(subNo);
		TeacImpl tImpl = new TeacImpl();
		datas = tImpl.findBySql(sql);
		return datas;
	}
}
