package org.example.BookMarket.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.example.BookMarket.domain.Board;
import org.example.BookMarket.domain.BoardFormDto;
import org.example.BookMarket.domain.Member;
import org.example.BookMarket.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    // 전체 게시글 목록 가져오기
    @GetMapping("/list")
    public String viewHomePage(Model model) {
        return viewPage( 1, "id", "desc",model );
    }
    // 전체 게시글 가져오기
    @GetMapping("/page")
    public String viewPage(@RequestParam("pageNum") int pageNum,
                           @RequestParam("sortField") String sortField, @RequestParam("sortDir")
                           String sortDir, Model model) {
        Page<Board> page = boardService.listAll(pageNum, sortField, sortDir);
        List<Board> listBoard = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("boardList", listBoard);
        return "board/list";
    }
    // 게시글 글쓰기 페이지 출력하기
    @GetMapping("/write")
    public String post() {
        return "board/write";
    }
    // 게시글 글쓰기 저장하기
    @PostMapping("/write")
    public String write(BoardFormDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }
    // 게시글 상세 보기
    @GetMapping("/view/{id}")
    public String requestUpdateMemberForm(@PathVariable(name = "id") Long id,
                                          HttpServletRequest httpServletRequest, Model model){
        Board board = boardService.getBoardById(id);
        model.addAttribute("boardFormDto", board);
        HttpSession session = httpServletRequest.getSession(true);
        Member member = (Member) session.getAttribute("userLoginInfo");
        model.addAttribute("buttonOk", false);
        if(member != null && board.getWriterid().equals(member.getMemberId())) {
            model.addAttribute("buttonOk", true);
        }
        return "board/view";
    }
    // 게시글 수정하기
    @PostMapping("/update")
    public String submitUpdateMember(@Valid BoardFormDto boardDto, BindingResult
            bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "board/view";
        try {
            boardService.savePost(boardDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "board/view";
        }
        return "redirect:/board/list";
    }
    // 게시글 삭제하기
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/board/list";
    }
}
