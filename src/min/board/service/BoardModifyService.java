package min.board.service;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardModifyService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multipartRequest.getParameter("num"));
			boolean usercheck = boardDAO.isBoardWriter(num, multipartRequest.getParameter("pass"));
			if (usercheck == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./BoardList.do';");
				out.println("</script>");
				out.close();
				return null;
			}
			boardDTO.setNum(num);
			boardDTO.setName(multipartRequest.getParameter("name"));
			boardDTO.setSubject(multipartRequest.getParameter("subject"));
			boardDTO.setContent(multipartRequest.getParameter("content"));
			boardDTO.setAttached_file(
					multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
			boardDTO.setOld_file(multipartRequest.getParameter("old_file"));
			result = boardDAO.boardModify(boardDTO);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetailService.do?num=" + boardDTO.getNum());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}