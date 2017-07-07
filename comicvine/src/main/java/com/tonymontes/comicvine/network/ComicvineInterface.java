
package com.tonymontes.comicvine.network;

import com.tonymontes.comicvine.responses.ComicvineIssuesResponse;
import com.tonymontes.comicvine.responses.ComicvineVolumesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ComicvineInterface {

    @GET
    Observable<ComicvineVolumesResponse> getVolumes(@Url String url);

    @GET
    Observable<ComicvineIssuesResponse> getIssues(@Url String url);
}
