package com.sg.springmvc.data;

import com.sg.springmvc.models.Journal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class JournalDaoImpl implements JournalDao {
    @Qualifier("primaryJdbcTemplate")
    private final JdbcTemplate primaryJdbcTemplate;

    public JournalDaoImpl(JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }

    public static final class JournalMapper implements RowMapper<Journal>{

        /**
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public Journal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Journal journal=new Journal();
            journal.setId(rs.getInt("id"));
            journal.setJournal(rs.getString("journal"));
            journal.setPublisher(rs.getString("publisher"));
            journal.setLatest_publisher(rs.getString("latest_publisher"));
            return journal;
        }
    }
    /**
     * @param id
     * @return
     */
    @Override
    public Journal getJournalById(int id) {
        final String sql="SELECT * FROM journal WHERE id=?;";
        return primaryJdbcTemplate.queryForObject(sql,new JournalMapper(),id);
    }

    /**
     * @param journalTitle
     * @return
     */
    @Override
    public Journal getJournalByJournalTitle(String journalTitle) {
        final String sql="SELECT * FROM journal WHERE journal=?;";
        return primaryJdbcTemplate.queryForObject(sql,new JournalMapper(),journalTitle);
    }

    /**
     * @param publisher
     * @return
     */
    @Override
    public Journal getJournalByJournalPublisher(String publisher) {
        final String sql="SELECT * FROM journal WHERE publisher=?;";
        return primaryJdbcTemplate.queryForObject(sql,new JournalMapper(),publisher);
    }

    /**
     * @param latest_publisher
     * @return
     */
    @Override
    public Journal getJournalByJournalLastPublisher(String latest_publisher) {
        final String sql="SELECT * FROM journal WHERE latest_publisher=?;";
        return primaryJdbcTemplate.queryForObject(sql,new JournalMapper(),latest_publisher);
    }

    /**
     * @return
     */
    @Override
    public List<String> getAllJournalTitle() {
        final String sql="SELECT journal FROM journal;";
        return primaryJdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * @param letter
     * @return
     */
    @Override
    public List<String> getJournalTitleByFirstLetter(String letter) {
        final String sql ="SELECT journal FROM journal WHERE journal LIKE '"+letter.charAt(0)+" %';";
        return primaryJdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * @return
     */
    @Override
    public List<Integer> getAllJournalId() {
        final String sql="SELECT id FROM journal;";
        return primaryJdbcTemplate.queryForList(sql, Integer.class);
    }

    /**
     * @return
     */
    @Override
    public List<Journal> getAllJournal() {
        final String sql = "SELECT * FROM journal;";
        return primaryJdbcTemplate.query(sql,new JournalMapper());
    }

    /**
     * @param journal_id
     * @return
     */

}
