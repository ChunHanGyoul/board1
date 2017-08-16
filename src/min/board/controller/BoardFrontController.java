package min.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.service.BoardAddService;
import min.board.service.BoardDeleteService;
import min.board.service.BoardDetailService;
import min.board.service.BoardDownloadService;
import min.board.service.BoardListService;
import min.board.service.BoardModifyDetailService;
import min.board.service.BoardModifyService;
import min.board.service.BoardReplyMoveService;
import min.board.service.BoardReplyService;
import min.board.service.BoardSearchListService;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		ActionCommand actionCommand = null;
		Action action = null;
		System.out.println(pathURL);
		if (pathURL.equals("/BoardWrite.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board_write.jsp");
		} else if (pathURL.equals("/BoardReply.do")) {
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardDelete.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board_delete.jsp");
		} else if (pathURL.equals("/BoardDeleteService.do")) {
			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardModify.do")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardAdd.do")) {
			action = new BoardAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardReplyMove.do")) {
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardModify.do")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardDelete.do")) {
			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardList.do")) {
			action = new BoardListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardDetail.do")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardSearchList.do")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardDownload.do")) {
			action = new BoardDownloadService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (actionCommand != null) {
			if (actionCommand.isRedirect()) {
				System.out.println("1");
				response.sendRedirect(actionCommand.getPath());
			} else {
				System.out.println("2");
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
}