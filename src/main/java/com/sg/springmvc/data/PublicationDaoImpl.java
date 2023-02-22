package com.sg.springmvc.data;

import com.sg.springmvc.models.Publication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublicationDaoImpl implements PublicationDao {
    @Qualifier("primaryJdbcTemplate")
    private final JdbcTemplate primaryJdbcTemplate;

    public PublicationDaoImpl(JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }

    public static final class PublicationMapper implements RowMapper<Publication> {

        @Override
        public Publication mapRow(ResultSet rs, int rowNum) throws SQLException {
            Publication publication = new Publication();
            publication.setTitle(rs.getString("article_title"));
            publication.setPmid(rs.getString("pubmed_id"));
            publication.setJournalName(rs.getString("journal_title"));
            publication.setYear(rs.getString("year"));
            publication.setMonth(rs.getString("month"));
            publication.setIssue(rs.getString("issue"));
            publication.setVolume(rs.getString("volume"));
            publication.setGsaAccession(rs.getString("gsa_accession"));
            publication.setIsUnique(rs.getInt("is_unique"));
            publication.setDoi(rs.getString("doi"));
            publication.setPrj_id(rs.getInt("prj_id"));
            publication.setGsa_linked(rs.getInt("gsa_linked"));
            publication.setJournal_id(rs.getInt("journal_id"));
            return publication;
        }
    }
    @Override
    public Publication getPublicationByTitle(String title) {
        if (title == null) {
            return null;
        } else {
            String sql = "SELECT *  FROM publication WHERE article_title=?";
            Publication publication = primaryJdbcTemplate.queryForObject(sql, new PublicationDaoImpl.PublicationMapper(), title);
            return publication;
        }
    }

    @Override
    public Publication getPublicationByPmid(String Pmid) {
        return null;
    }

    @Override
    public Publication getPublicationById(int id) {
        final String sql = "SELECT * FROM publication WHERE publication_id = ?;";
        Publication publication = primaryJdbcTemplate.queryForObject(sql, new PublicationMapper(), id);
        return publication;
    }
    @Override
    public List<String> getArticleTitleByGsaLinked() {
        final String sql ="SELECT DISTINCT article_title FROM publication WHERE gsa_linked>0;";
        List<String> articleList = primaryJdbcTemplate.queryForList(sql, String.class);
        return articleList;
    }
    @Override
    public Publication getPublicationByArticleTitle(String articleTitle) {
        final String sql = "SELECT * FROM publication WHERE article_title =?;";
        Publication publication = primaryJdbcTemplate.queryForObject(sql,new PublicationMapper(),articleTitle);
        return publication;
    }

    @Override
    public void addPublication(Publication publication) {
        final String INSERT_PUBLICATION = "INSERT INTO publication(journal_title,journal_id,doi,volume,issue,prj_id,article_title,pubmed_id,year,month,gsa_linked,is_unique,gsa_accession)values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        primaryJdbcTemplate.update(INSERT_PUBLICATION,
                publication.getJournalName(),
                publication.getJournal_id(),
                publication.getDoi(),
                publication.getVolume(),
                publication.getIssue(),
                publication.getPrj_id(),
                publication.getTitle(),
                publication.getPmid(),
                publication.getYear(),
                publication.getMonth(),
                publication.getGsa_linked(),
                publication.getIsUnique(),
                publication.getGsaAccession());

    }
}
