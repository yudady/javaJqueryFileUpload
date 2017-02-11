package org.lyy.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;




	/**
	 * delete file
	 * <pre>
	 * Method Name : doGet
	 * Description : doGet
	 * </pre>
	 * @author tommy E-mail:tommylin@foyatech.com
	 * @create 創建時間：2017年2月11日 下午3:45:08
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deletefile = request.getParameter("file");

		System.out.println("delete file => " + deletefile);

		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		JSONObject jsonobj = new JSONObject();
		JSONArray json = new JSONArray();
		try {

			jsonobj.put("files", json);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			writer.write(jsonobj.toString());
			writer.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*"); // set headers
		// for cross-domain
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition");
		//set headers for chunk upload
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}
		System.out.println("UploadServlet.uploadHandler");
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		System.out.println("UploadServlet.ok");

		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		JSONObject jsonobj = new JSONObject();
		JSONArray json = new JSONArray();
		try {
			List<FileItem> items = uploadHandler.parseRequest(request);

			System.out.println("UploadServlet.items.size()=" + items.size());

			for (FileItem item : items) {
				if (!item.isFormField()) { // 判断是否是普通表单域还是文件上传表单域
					File file = new File(
							request.getSession().getServletContext().getRealPath("/") + "/" + item.getName());

					System.out.println("UploadServlet.file=" + file.getAbsolutePath());

					item.write(file); // 将内容写到文件中
					JSONObject jsono = new JSONObject();
					jsono.put("name", item.getName()); // 文件名称
					jsono.put("size", item.getSize()); // 文件大小
					jsono.put("url", item.getName());
					jsono.put("image", item.getName());
					jsono.put("thumbnailUrl", item.getName());

					jsono.put("deleteUrl", "upload?file=" + item.getName());
					jsono.put("deleteType", "GET");
					jsono.put("deleteWithCredentials", true);

					json.put(jsono);
				} else {

					System.out.println("Field => " + item.isFormField());
					System.out.println(item.getFieldName() + "  : " + item.getString());

				}
			}
			jsonobj.put("files", json);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			writer.write(jsonobj.toString());
			writer.close();
		}

	}

}