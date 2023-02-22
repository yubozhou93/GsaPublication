package com.sg.springmvc.data;

import com.sg.springmvc.models.Project;

import java.util.List;

public interface GsaProjectDao {
        Project getProjectByAccession(String accession);
        List isExistByAccession(String accession);
        String getProjectByPrjId(int id);
}
