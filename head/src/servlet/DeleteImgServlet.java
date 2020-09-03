package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import utils.ConfigUtils;

/**
 * Servlet implementation class DeleteImgServlet
 */
public class DeleteImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ɾ��ԭ����ͼƬ��ʼ");
		String prefixPath = ConfigUtils.getConfig("deletePath");//"/root/software/imgServer/apache-tomcat-9.0.35/webapps/img/";
		String suffixPath = ".png";
		
		String fileName = request.getParameter("fileName");
		if (StringUtils.isNotBlank(fileName)) {
			System.out.println("ɾ����ͼƬ��" + prefixPath + fileName + suffixPath);
			File file = new File(prefixPath + fileName + suffixPath);
			if (file.exists()) {
				file.delete();
				System.out.println("ɾ���ɹ�");
			} 
		}
		System.out.println("ɾ��ԭ����ͼƬ����ʼ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
