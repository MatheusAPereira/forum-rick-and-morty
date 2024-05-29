package com.rickandmorty.forum.services.Impl;

import com.rickandmorty.forum.converters.comment.CommentRequestDtoToCommentMapper;
import com.rickandmorty.forum.converters.comment.CommentToCommentResponseDtoMapper;
import com.rickandmorty.forum.dtos.CommentRequestDTO;
import com.rickandmorty.forum.models.Comment;
import com.rickandmorty.forum.dtos.PaginatedInfoDTO;
import com.rickandmorty.forum.dtos.PaginatedDTO;
import com.rickandmorty.forum.respositories.CommentRepository;
import com.rickandmorty.forum.services.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentRequestDtoToCommentMapper commentRequestDtoToCommentMapper;

    @Autowired
    private final CommentToCommentResponseDtoMapper commentToCommentResponseDtoMapper;

    public CommentServiceImpl(CommentRepository repository,
                              CommentRequestDtoToCommentMapper commentRequestDtoToCommentMapper,
                              CommentToCommentResponseDtoMapper commentToCommentResponseDtoMapper){
        this.commentRepository = repository;
        this.commentRequestDtoToCommentMapper = commentRequestDtoToCommentMapper;
        this.commentToCommentResponseDtoMapper = commentToCommentResponseDtoMapper;
    }

    @Transactional
    @Override
    public void create(CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRequestDtoToCommentMapper.map(commentRequestDTO, new Comment());
        commentRepository.save(comment);
    }

    @Override
    public PaginatedDTO<Comment> listByEpisodeId(Long episodeId, int pageNumber, int pageSize) {
        Page<Comment> comments = commentRepository.findByEpisodeId(episodeId, PageRequest.of(pageNumber, pageSize));
        return new PaginatedDTO<>(
                new PaginatedInfoDTO(
                    comments.getTotalPages(),
                    comments.getTotalElements(),
                    comments.getNumber(),
                    comments.getSize(),
                    comments.hasPrevious(),
                    comments.hasNext()
                ),
                comments.getContent()
        );
    }

    @Override
    public List<Comment> listAllByEpisodeId(Long episodeId) {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Transactional
    @Override
    public CommentRequestDTO update(CommentRequestDTO commentRequestDTO) {
        return null;
    }

    @Override
    public void delete(CommentRequestDTO commentRequestDTO) {

    }
}
