package com.hgs.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러에다가 직접적으로 기능 하나하나에 대한 코드를 작성할수도 있지만
// 그렇게 되면 기능이 많아졌을 때 코그 관리가 어려워지는 문제 발생
// 따라서 기능을 하나하나 Service라는 단위로 나누어 관리
public interface IBoardService {
	
	// 서비스 인터페이스 내에는 실행 메서드 하나만 생성
	void execute(HttpServletRequest request, HttpServletResponse response);
	
}
