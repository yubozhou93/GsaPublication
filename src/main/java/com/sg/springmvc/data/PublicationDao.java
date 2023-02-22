package com.sg.springmvc.data;

import com.sg.springmvc.models.GsaProject;
import com.sg.springmvc.models.Publication;

import java.util.List;

public interface PublicationDao {

    Publication getPublicationByTitle(String title);

    Publication getPublicationByPmid(String Pmid);
    Publication getPublicationById(int id);
    List<String> getArticleTitleByGsaLinked();
    Publication getPublicationByArticleTitle(String articleTitle);

    void addPublication(Publication publication);
}
