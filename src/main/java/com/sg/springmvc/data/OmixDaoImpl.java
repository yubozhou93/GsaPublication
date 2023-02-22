package com.sg.springmvc.data;

import com.sg.springmvc.models.Omix;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OmixDaoImpl implements OmixDao {

    @Resource(name = "thirdJdbcTemplate")
    private final JdbcTemplate thirdJdbcTemplate;

    public OmixDaoImpl(JdbcTemplate thirdJdbcTemplate) {
        this.thirdJdbcTemplate = thirdJdbcTemplate;
    }
    public static final class OmixMapper implements RowMapper<Omix> {

        @Override
        public Omix mapRow(ResultSet rs, int rowNum) throws SQLException {
            Omix omix =new Omix();
            omix.setUser_id(rs.getInt("user_id"));
            omix.setDescription(rs.getString("description"));
            omix.setAccess_type(rs.getInt("access_type"));
            omix.setPrj_id(rs.getInt("prj_id"));
            omix.setIs_agree(rs.getInt("is_agree"));
            omix.setPrj_accession(rs.getString("prj_accession"));
            omix.setStatus(rs.getInt("status"));
            omix.setTitle(rs.getString("title"));
            omix.setIs_deleted(rs.getInt("is_deleted"));
            omix.setType_id(rs.getInt("type_id"));
            omix.setOmix_code(rs.getString("omix_code"));
            omix.setRelease_type(rs.getInt("release_type"));
            omix.setRegis_no(rs.getString("regis_no"));
            omix.setBackup_no(rs.getString("backup_no"));
            omix.setTax_id(rs.getInt("tax_id"));
            omix.setTips(rs.getString("tips"));
            omix.setIs_most(rs.getInt("is_most"));
            omix.setIs_checked(rs.getInt("is_checked"));
            omix.setOwner_download(rs.getInt("owner_download"));
            return omix;
        }
    }

    @Override
    public Omix getOmixProjectByOmixAccession(String omixAccession) {
        final String sql= "SELECT * FROM omix WHERE omix_code=?;";
        return thirdJdbcTemplate.queryForObject(sql,new OmixMapper(),omixAccession);
    }

    @Override
    public List isExistgetOmixProjectByOmixAccession(String omixAccession) {
        final String sql = "SELECT * FROM omix WHERE omix_code=?;";
        return thirdJdbcTemplate.query(sql,new OmixMapper(),omixAccession);
    }

    @Override
    public Omix getOmixProjectByOmixId(int id) {
        final String sql = "SELECT * FROM omix WHERE omix_id=?;";
        return thirdJdbcTemplate.queryForObject(sql,new OmixMapper(),id);
    }

    @Override
    public Omix getOmixProjectByOmixTitle(String title) {
        final String sql = "SELECT * FROM omix WHERE title=?;";
        return thirdJdbcTemplate.queryForObject(sql,new OmixMapper(),title);
    }

    @Override
    public List<String> getOmixAccessionListByPrjId(int prj_id) {
        final String sql = "SELECT omix_code FROM omix WHERE prj_id=?;";
        return thirdJdbcTemplate.queryForList(sql, String.class,prj_id);
    }

    @Override
    public List<String> getOmixAccessionListByPrjAccession(String prj_accession) {
        final String sql = "SELECT omix_code FROM omix WHERE prj_accession=?;";
        return thirdJdbcTemplate.queryForList(sql, String.class,prj_accession);
    }

    /**
     * @param omix_ocde
     * @return
     */
    @Override
    public List isExistGetEditorialOmixCodeByOmixCode(String omix_ocde) {
        final String sql = "SELECT * FROM omix WHERE omix_code=? and status <>3;";
        return thirdJdbcTemplate.query(sql,new OmixMapper(),omix_ocde);
    }
}
