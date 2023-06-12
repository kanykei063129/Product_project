package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse saveFavorite(FavoriteRequest favoriteRequest);
    FavoriteResponse getById(Long id);
    List<FavoriteResponse> getAll();
    FavoriteResponse update(Long id,FavoriteRequest favoriteRequest);
    SimpleResponse delete(Long id);
}
