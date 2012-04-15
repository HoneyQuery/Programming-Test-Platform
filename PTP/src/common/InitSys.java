package common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import judge.Constant;
import judge.PtpJudge;
/**
 * <font face="���Ŀ���" color="red"> ϵͳ��ʼ��ʱ���������м�������</font>
 * @author shawzt
 * @version 1.0
 * @build 2011/1/1
 *
 */
public class InitSys extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PtpJudge judge;
	private Constant constant;

	public InitSys() {
		super();
		constant = new Constant();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * Initialization of the servlet. <br>
	 * <font face="���Ŀ���" color="red"> �������м������� </font>
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		try {
			this.judge = new PtpJudge();
			System.out.println("Listener is running!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
