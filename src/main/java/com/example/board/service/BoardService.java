package com.example.board.service;

import com.example.board.domain.entity.BoardEntity;
import com.example.board.domain.repository.BoardRepository;
import com.example.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;


    //게시글 저장하기
    //게시글 수정하기
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    //게시글 목록보기
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<BoardEntity> boardEntityList = boardRepository.findAllByOrderByCreatedDateDesc();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    //게시글 보기
    @Transactional
    public BoardDto getBoard(Long id) {
        //repository를 이용해서 id가 일치하는 것 찾기
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        //optional 타입 객체에서 엔티티 가져오기
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDto;
    }

    //게시글 삭제하기
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

}
