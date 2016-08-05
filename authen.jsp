<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.awt.*,java.awt.image.*,java.util.*,com.sun.image.codec.jpeg.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!//根据提供的ab产生随机的颜色变化范围
	Color getColor(int a, int b) {
		int n = b - a;
		Random random = new Random();
		int cr = a + random.nextInt(n);
		int cg = a + random.nextInt(n);
		int cb = a + random.nextInt(n);
		return new Color(cr, cg, cb);
	}%>
	<%
		try {
			//取消客户端浏览器缓存验证码的功能
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			int width = 60, height = 20;
			//在内存中生成一个图像
			BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			OutputStream os = response.getOutputStream();

			Graphics graphics = bImage.getGraphics();

			Random random = new Random();

			graphics.setColor(getColor(200, 250));
			graphics.fillRect(0, 0, width, height);

			graphics.setFont(new Font("Times New Roman", Font.BOLD, 18));
			graphics.setColor(getColor(160, 200));
			for (int i = 0; i < 160; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				graphics.drawLine(x, y, x + xl, y + yl);
			}

			String number = String.valueOf(1000 + random.nextInt(8999));
			String name = request.getParameter("name");
			session.setAttribute(name, number);

			graphics.setColor(getColor(20, 130));
			int x = (int) (width * 0.2);
			int y = (int) (height * 0.8);
			graphics.drawString(number, x, y);
			graphics.dispose();

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(bImage);

			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
			out.clear();
			out = pageContext.pushBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/* out.close(); */
	%>
</body>
</html>