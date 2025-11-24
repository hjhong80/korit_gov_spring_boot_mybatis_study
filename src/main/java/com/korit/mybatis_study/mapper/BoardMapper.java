package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    Optional<Board> findBoardByTitle(String title);
    int addBoard(Board board);
    List<Board> findAll();
    Optional<Board> findBoardByBoardId(Integer boardId);
    Optional<List<Board>> findBoardByKeyword(String keyword);
    int editBoard(Board board);
    int removeBoard(Integer boardId);

}
