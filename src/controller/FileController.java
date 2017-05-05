package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import annotation.Permission;
import bean.Response;
import global.constant.Constant;

@Controller
public class FileController extends AbsController {

	
	@RequestMapping(value = Constant.RequestPath.FOOD_IMAGE_UPLOAD, method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String foodImageUpload(HttpServletRequest request, Response response)
        throws Exception
    {
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = mreq.getFiles("file");
        System.out.println("files.size = " + files.size());
        System.out.println("attr = " + mreq.getParameter("attr"));
        System.out.println("attribute = " + mreq.getParameter("attribute"));
        String path = request.getSession().getServletContext().getRealPath("/") + "upload/food_image/";
        File dir = new File(path);
        if(!dir.exists()){
        	dir.mkdirs();
        }
        List<String> paths = new ArrayList<String>();
        for (MultipartFile file : files)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(new Date()) + "_" + file.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(path + fileName);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            paths.add("http://localhost:8080/OrderingSystem/upload/food_image/" + fileName);
        }
        response.setData(paths);
        return response.toJsonString();
    }
}
