package com.sg.springmvc.data;

import com.sg.springmvc.models.Study;

import java.util.List;

public interface GsaHumanStudyDao {
    Study getStudyByStudyId(int id);
    Study getStudyByAccession(String accession);
    Study getStudyByProjectAccession(String projectAccession);
    int getPrjIdByPrjAccession(String projectAccession);
    List<String> getGsaAccessionsByPrjAccession(String projectAccession);
    List isExistByAccession(String accession);
    List isExistGetEditorialGsaHumanStudyByAccession(String accession);
    List isExistById(int id);
    List<String> getStudyAccessionByPrjId(int prj_Id);
}
