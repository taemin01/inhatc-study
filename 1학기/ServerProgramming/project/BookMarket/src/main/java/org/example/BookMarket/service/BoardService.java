package org.example.BookMarket.service;

import org.example.BookMarket.domain.Board;
import org.example.BookMarket.domain.BoardFormDto;
import org.example.BookMarket.repository.BoardRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import jakarta.transaction.Transactional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    // 게시글 등록/수정하기
    @Transactional
    public Long savePost(BoardFormDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    // 전체 게시글 가져오기
    @Transactional
    public List<BoardFormDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardFormDto> boardDtoList = new ArrayList< >();
        for(Board board : boardList) {
            BoardFormDto boardDto = BoardFormDto.builder()
                    .id(board.getId())
                    .writerid(board.getWriterid())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    // 게시글 가져오기
    public Board getBoardById(Long id) {
        Board board = boardRepository.findById(id).get();
        return board;
    }
    // 게시글 삭제하기
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);

    }
    // 전체 게시글의 목록 개수, 정렬 가져오기
    public Page<Board> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.
                equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).
                descending());
        return boardRepository.findAll(pageable);
    }
}