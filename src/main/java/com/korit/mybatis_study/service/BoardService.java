package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddBoardReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditBoardReqDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public ApiRespDto<?> addBoard(AddBoardReqDto addBoardReqDto) {
        // title 중복 검사
        // title 조회
        // 중복이 되지 않으면 보드 추가
        Optional<Board> foundBoard = boardRepository.findBoardByTitle(addBoardReqDto.getTitle());
        if(foundBoard.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("제목이 중복되었습니다.")
                    .build();
        }
        Optional<Board> board = boardRepository.addBoard(addBoardReqDto.toEntity());
        if (board.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("게시물 추가에 실패하였습니다.")
                    .build();
        }
        return ApiRespDto.<Board>builder()
                .status("success")
                .message("게시물 추가에 성공하였습니다.")
                .data(board.get())
                .build();
    }

    public ApiRespDto<?> getAll() {
        return ApiRespDto.<List<Board>>builder()
                .status("success")
                .message("조회에 성공하였습니다.")
                .data(boardRepository.findAll())
                .build();
    }

    public ApiRespDto<?> getBoardByBoardId(Integer boardId) {
        if (boardRepository.findBoardByBoardId(boardId).isPresent()) {
            return ApiRespDto.<Board>builder()
                    .status("success")
                    .message("조회에 성공하였습니다.")
                    .data(boardRepository.findBoardByBoardId(boardId).get())
                    .build();
        } else {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .data(boardId)
                    .build();
        }
    }

    public ApiRespDto<?> getBoardByKeyword(String keyword) {
        Optional<List<Board>> foundBoard = boardRepository.findBoardByKeyword(keyword);
        if (foundBoard.isPresent()) {
            return ApiRespDto.<List<Board>>builder()
                    .status("success")
                    .message("조회에 성공하였습니다.")
                    .data(foundBoard.get())
                    .build();
        } else {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .build();
        }
    }

    public ApiRespDto<?> editBoard(EditBoardReqDto editBoardReqDto) {
        Optional<Board> boardToEdit = boardRepository.findBoardByBoardId(editBoardReqDto.getBoardId());
        if (boardToEdit.isEmpty()) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("아이디가 잘못 입력되었습니다.")
                    .build();

        }
        int result = boardRepository.editBoard(editBoardReqDto.toEntity());

        if (result != 1) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("게시물 수정에 실패하였습니다.")
                    .build();
        }
        return ApiRespDto.<String>builder()
                .status("success")
                .message("게시물 수정에 성공하였습니다.")
                .build();
    }

    public ApiRespDto<?> removeBoard(Integer boardId) {
        Optional<Board> boardToEdit = boardRepository.findBoardByBoardId(boardId);
        if (boardToEdit.isEmpty()) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("아이디가 잘못 입력되었습니다.")
                    .build();
        }
        int result = boardRepository.removeBoard(boardId);
        if (result != 1) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("게시물 삭제에 실패하였습니다.")
                    .build();
        }
        return ApiRespDto.<String>builder()
                .status("success")
                .message("게시물 삭제에 성공하였습니다.")
                .build();
    }
}
