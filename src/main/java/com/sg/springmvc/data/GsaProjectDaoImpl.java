package com.sg.springmvc.data;

import com.sg.springmvc.models.Project;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GsaProjectDaoImpl implements GsaProjectDao {
    @Qualifier("primaryJdbcTemplate")
    private final JdbcTemplate primaryJdbcTemplate;

    public GsaProjectDaoImpl(JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }
    public static final class ProjectMapper implements RowMapper<Project>{
        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            Project project =new Project();
            project.setPrj_id(rs.getInt("prj_id"));
            project.setAccession(rs.getString("accession"));
            project.setTitle(rs.getString("title"));
            project.setSubmitter_id(rs.getInt("submitter_id"));
            project.setUser_id(rs.getInt("user_id"));
            project.setStatus(rs.getInt("status"));
            project.setDescription(rs.getString("description"));
            project.setRelevance(rs.getString("relevance"));
            project.setIs_released(rs.getInt("is_released"));
            project.setRelease_state(rs.getInt("release_state"));
            return project;
        }
    }
    @Override
    public Project getProjectByAccession(String accession) {
        final String sql= "SELECT * FROM project WHERE accession = ?;";
        return primaryJdbcTemplate.queryForObject(sql,new ProjectMapper(),accession);
    }

    @Override
    public List isExistByAccession(String accession) {
        final String sql ="SELECT * FROM project WHERE accession=?;";
        return primaryJdbcTemplate.query(sql,new ProjectMapper(),accession);
    }

    @Override
    public String getProjectByPrjId(int id) {
        final String sql = "SELECT accession FROM project where prj_id=?;";
        return primaryJdbcTemplate.queryForObject(sql, String.class,id);
    }
}
