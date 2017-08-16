package min.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardDetailService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int num = Integer.parseInt(request.getParameter("num"));
		boardDAO.setReadCountUpdate(num);
		boardDTO = boardDAO.getDetail(num);
		if (boardDTO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board_view.jsp");
		return actionCommand;
	}
}