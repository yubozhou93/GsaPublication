package com.sg.springmvc.data;

import com.sg.springmvc.models.Omix;

import java.util.List;

public interface OmixDao {
    Omix getOmixProjectByOmixAccession(String omixAccession);
    List isExistgetOmixProjectByOmixAccession(String omixAccession);
    Omix getOmixProjectByOmixId(int id);
    Omix getOmixProjectByOmixTitle(String title);
    List<String> getOmixAccessionListByPrjId(int prj_id);
    List<String> getOmixAccessionListByPrjAccession(String accession);
    List isExistGetEditorialOmixCodeByOmixCode(String omix_ocde);
}
