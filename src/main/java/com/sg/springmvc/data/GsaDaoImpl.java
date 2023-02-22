/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.springmvc.data;

import com.sg.springmvc.models.GsaProject;
import com.sg.springmvc.models.Project;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class GsaDaoImpl implements GsaDao {

@Qualifier("primaryJdbcTemplate")
    private final JdbcTemplate primaryJdbcTemplate;
    public GsaDaoImpl(JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }

    public static final class GsaMapper implements RowMapper<GsaProject> {

        /**
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public GsaProject mapRow(ResultSet rs, int rowNum) throws SQLException {
            GsaProject gsaProject = new GsaProject();
            gsaProject.setAlias(rs.getString("alias"));
            gsaProject.setAccession(rs.getString("accession"));
            gsaProject.setUser_id(rs.getInt("user_id"));
            gsaProject.setSubmit_id(rs.getInt("submit_id"));
            gsaProject.setStatus(rs.getInt("submit_id"));
            gsaProject.setRelease_state(rs.getInt("release_state"));
            gsaProject.setIs_controlled(rs.getInt("is_controlled"));
            gsaProject.setTitle(rs.getString("title"));
            gsaProject.setDescription(rs.getString("description"));
            gsaProject.setPrj_id(rs.getInt("prj_id"));
            return gsaProject;
        }
    }

    /**
     * @param accession
     * @return
     */
    @Override
    public GsaProject getGsaProjectByAccession(String accession) {
        final String sql = " SELECT * FROM cra WHERE accession = ? and status=3;";
        GsaProject gsaProject = primaryJdbcTemplate.queryForObject(sql,new GsaMapper(),accession);
        return gsaProject;
    }

    @Override
    public List isExistGsaProjectByAccession(String accession) {
        final String sql = " SELECT * FROM cra WHERE accession = ? and status=3;";
        return primaryJdbcTemplate.query(sql,new GsaMapper(),accession);
    }

    @Override
    public GsaProject getGsaProjectById(int id) {
        final String sql = " SELECT * FROM cra WHERE cra_id = ?;";
        return primaryJdbcTemplate.queryForObject(sql,new GsaMapper(),id);
    }

    @Override
    public GsaProject getGsaProjectByPrjId(int prj_id) {
        final String sql = "SELECT accession FROM cra WHERE prj_id=? and status=3;";
        return primaryJdbcTemplate.queryForObject(sql,new GsaMapper(),prj_id);
    }

    @Override
    public String getLastGsaProjectAccession() {
        final String sql= "SELECT MAX(accession) FROM cra;";
        return primaryJdbcTemplate.queryForObject(sql, String.class);
    }

    @Override
    public List isExistByAccession(String accession) {
        final String sql = " SELECT * FROM cra WHERE accession = ?;";
        return primaryJdbcTemplate.query(sql,new GsaMapper(),accession);
    }

    @Override
    public List isExistById(int id) {
        final String sql = " SELECT * FROM cra WHERE cra_id = ?;";
        return primaryJdbcTemplate.query(sql,new GsaMapper(),id);
    }

    @Override
    public List<String> getAccessionListByPrjId(int prj_id) {
        final String sql = "SELECT accession FROM cra WHERE prj_id=? and status=3;";
        return primaryJdbcTemplate.queryForObject(sql, List.class,prj_id);
    }
//好像重复了
    @Override
    public List<String> getProjectByPrjId(int prjId) {
        final String sql = "SELECT accession FROM cra WHERE prj_id = ? and status=3;";
        return primaryJdbcTemplate.queryForList(sql, String.class,prjId);
    }
    @Override
    public List isExistgetGsaProjectByPrjId(int prj_id) {
        final String sql = "SELECT accession FROM cra WHERE prj_id=? and status=3;";
        return primaryJdbcTemplate.query(sql,new GsaMapper(),prj_id);
    }
}
