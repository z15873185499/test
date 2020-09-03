package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class HeadServlet
 */
public class HeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HeadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		try {
			String r = reciveImageAndParams(req);
			response.getWriter().append(r);
		} catch (Exception e) {
			response.getWriter().append("Fail:").append(e.getMessage());
		}
	}

	public String reciveImageAndParams(HttpServletRequest request) throws Exception {
		String savePath = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "img"
				+ File.separator;
		String URL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

		// Map<String, String> uri = new HashMap<String, String>();

		String tempPathDir = "";
		File tempPathDirFile = new File(tempPathDir);

		// 创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓冲区大小，这里是400kb
		factory.setSizeThreshold(4096 * 100);
		// 设置缓冲区目录
		factory.setRepository(tempPathDirFile);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的大小 12M
		upload.setSizeMax(4194304 * 3);
		// 创建解析器
		// 得到所有的文件
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> i = items.iterator();
		// 图片地址拼接
		// StringBuffer buf=new StringBuffer();
		String img_urls = "Fail:PNG file not found";
		// int j=0;//计数
		while (i.hasNext()) {
			FileItem fi = i.next();
			// false表示文件 否则字段
			if (!fi.isFormField()) {
				String fileName = fi.getName();
				// System.out.println("fileName=" + fileName);
				if (fileName != null) {
					// 这里加一个限制，如果不是图片格式，则提示错误. (gif,jpg,jpeg,bmp,png)
					String suffixName = FilenameUtils.getExtension(fileName);
					// System.out.println("suffixName=" + suffixName);
					if ("png".equalsIgnoreCase(suffixName)) {

						// j++;
						String imageName = System.currentTimeMillis() + "." + suffixName;

						
						
						img_urls = URL+"/img/" + imageName;
						System.out.println("img_urls = " + img_urls);
						
						// System.out.println(imageName);
						String path = savePath + imageName;
						System.out.println("path = " + path);
						// String imgPath = "e:" + imageName;// 本机
						// ConfUtil cf = new ConfUtil();
						// String imgPath = cf.getConfig("imgconfig.properties",
						// "imgPath") + imageName;//服务器

						// 图片地址拼接
						// String img_url=cf.getConfig("imgconfig.properties",
						// "imgurl") + imageName;
						// img_urls=buf.append(img_url).append(",").toString();
						// 定义图片流
						InputStream fin = fi.getInputStream();
						// 定义图片输出流
						FileOutputStream fout = new FileOutputStream(path);
						// 写文件
						byte[] b = new byte[1024];
						int length = 0;
						while ((length = fin.read(b)) > 0) {
							fout.write(b, 0, length);
						}
						// 关闭数据流
						fin.close();
						fout.close();
					}
					// else {
					// uri.put("error", "文件格式不正确");
					// return "1";
					// }
				}
			}
			// else {//请求中的非文件数据
			// fi.getString("UTF-8");
			// uri.put(new String(fi.getFieldName()), new
			// String(fi.getString()));
			// }
		}
		// if(img_urls!="") {
		// img_urls=img_urls.substring(0,img_urls.length()-1);
		// }
		// uri.put("img_urls", img_urls);
		return img_urls;

	}

}
