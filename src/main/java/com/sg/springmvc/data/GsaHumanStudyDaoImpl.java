package com.sg.springmvc.data;

import com.sg.springmvc.models.Study;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GsaHumanStudyDaoImpl implements GsaHumanStudyDao {
    @Resource(name = "secondaryJdbcTemplate")
    private final JdbcTemplate secondaryJdbcTemplate;

    public GsaHumanStudyDaoImpl(JdbcTemplate secondaryJdbcTemplate) {
        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
    }

    public static final class GsaHumanStudyMapper implements RowMapper<Study>{

        @Override
        public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
            Study study =new Study();
            study.setStudy_id(rs.getInt("study_id"));
            study.setAccession(rs.getString("accession"));
            study.setTitle(rs.getString("title"));
            study.setPrj_id(rs.getInt("prj_id"));
            study.setPrj_accession(rs.getString("prj_accession"));
            return study;
        }
    }
    @Override
    public Study getStudyByStudyId(int id) {
        final String sql = " SELECT * FROM study WHERE study_id = ?;";
        Study study = secondaryJdbcTemplate.queryForObject(sql,new GsaHumanStudyMapper(),id);
        return study;
    }

    @Override
    public Study getStudyByAccession(String accession) {
        final String sql ="SELECT * FROM study WHERE accession=?;";
        Study study= secondaryJdbcTemplate.queryForObject(sql,new GsaHumanStudyMapper(),accession);
        return study;
    }

    @Override
    public Study getStudyByProjectAccession(String projectAccession) {
        final String sql = "SELECT * FROM study WHERE prj_accession=? and status<>5;";
        Study study = secondaryJdbcTemplate.queryForObject(sql,new GsaHumanStudyMapper(),projectAccession);
        return study;
    }

    @Override
    public int getPrjIdByPrjAccession(String projectAccession) {
        final String sql = "SELECT DISTINCT prj_id FROM study WHERE prj_accession=? and status<>5; ";
        int prj_id = secondaryJdbcTemplate.queryForObject(sql,Integer.class,projectAccession);
        return prj_id;
    }

    @Override
    public List<String> getGsaAccessionsByPrjAccession(String projectAccession) {
        final String sql="SELECT accession FROM study WHERE prj_accession=? and status<>5;";
        List<String> gsaAccessions = secondaryJdbcTemplate.queryForList(sql,String.class,projectAccession);
        return gsaAccessions;
    }

    @Override
    public List isExistByAccession(String accession) {
        final String sql = " SELECT * FROM study WHERE accession = ?;";
        return secondaryJdbcTemplate.query(sql,new GsaHumanStudyMapper(),accession);
    }

    @Override
    public List isExistGetEditorialGsaHumanStudyByAccession(String accession) {
        final String sql = " SELECT * FROM study WHERE accession = ? and status=3;";
        return secondaryJdbcTemplate.query(sql,new GsaHumanStudyMapper(),accession);
    }

    @Override
    public List isExistById(int id) {
        final String sql = " SELECT * FROM study WHERE study_id = ?;";
        return secondaryJdbcTemplate.query(sql,new GsaHumanStudyMapper(),id);
    }

    @Override
    public List<String> getStudyAccessionByPrjId(int prj_Id) {
        final String sql = "SELECT accession FROM study WHERE prj_id=?;";
        return secondaryJdbcTemplate.queryForList(sql, String.class,prj_Id);
    }


}
