package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse saveComment(CommentRequest commentRequest);
    CommentResponse getById(Long userId);
    List<CommentResponse> getAll();
    CommentResponse update(Long id,CommentRequest commentRequest);
    SimpleResponse delete(Long id);
}
